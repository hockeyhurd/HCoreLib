package com.hockeyhurd.hcorelib.mod.client.gui;

import com.hockeyhurd.hcorelib.api.client.util.ModelRegistry;
import com.hockeyhurd.hcorelib.api.math.expressions.Expression;
import com.hockeyhurd.hcorelib.api.math.expressions.GlobalConstants;
import com.hockeyhurd.hcorelib.api.math.expressions.Interpreter;
import com.hockeyhurd.hcorelib.api.math.expressions.InterpreterResult;
import com.hockeyhurd.hcorelib.mod.ClientProxy;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.block.ModRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

/**
 * Gui interface for ItemCalculator.
 *
 * @author hockeyhurd
 * @version 11/10/16
 */
@SideOnly(Side.CLIENT)
public final class GuiCalculator extends GuiScreen {

    private static final ResourceLocation texture = new ResourceLocation(HCoreLibMain.assetDir,
            "textures/gui/GuiCalculator.png");

    private int xSize, ySize;
    private int guiLeft, guiTop;
    private String drawString;
    private char[] drawBuffer;
    private int charIndex;

    private GuiButton[] numPad;
    private GuiButton deleteButton, clearButton;
    private Map<String, GuiButton> buttonMap;
    private MemoryBuffer memoryBuffer;
    private InterpreterResult lastResult;

    public GuiCalculator() {
        this.xSize = 248;
        this.ySize = 166;
        this.drawBuffer = new char[0x20];
        this.charIndex = 0;
        this.drawString = "";

        buttonMap = new HashMap<String, GuiButton>();
        memoryBuffer = new MemoryBuffer();
    }

    @Override
    public void initGui() {
        super.initGui();

        final ItemStack calcStack = mc.player.getHeldItem(EnumHand.MAIN_HAND);
        if (calcStack != null && calcStack.getCount() > 0 && calcStack.getItem() == ModRegistry.ModItems.itemCalculator.getItem().getItem()) {
            NBTTagCompound comp = calcStack.getTagCompound();
            if (comp != null) {
                drawString = comp.getString("CalculatorInput");
                charIndex = comp.getInteger("CalculatorInputCharCount");

                memoryBuffer.store(comp.getDouble("CalculatorMemoryBuffer"));
                if (lastResult == null) lastResult = new InterpreterResult();
                lastResult.updateResult(comp.getString("CalculatorLastResultString"), comp.getDouble("CalculatorLastResult"));

                // comp.setDouble("CalculatorMemoryBuffer", memoryBuffer.read());
                // comp.setDouble("CalculatorLastResult", lastResult.getResult());
                // comp.setString("CalculatorLastResultString", lastResult.getExpressionString());

                for (int i = 0; i < charIndex; i++)
                    drawBuffer[i] = drawString.charAt(i);
            }
        }

        this.guiLeft = (this.width - this.xSize) >> 1;
        this.guiTop = (this.height - this.ySize) >> 1;

        // if (numPad == null) numPad = new GuiButton[9];
        if (numPad == null) numPad = new GuiButton[12];

        // final int bw = xSize / 10;
        // final int bh = ySize / 10;
        final int bw = 0x14;
        final int bh = 0x14;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                GuiButton button = new GuiButton(x + y * 3, guiLeft + ((x + 1) * (bw + 4)) + (bw >> 1),
                        height - guiTop - ((4 - y) * (bh + 4)) - (bh >> 2), bw, bh, Integer.toString(1 + x + y * 3));

                numPad[x + y * 3] = button;
                buttonList.add(button);
            }
        }

        numPad[9] = new GuiButton(9, numPad[6].x, numPad[6].y + bh + 4, bw, bh, "(");
        numPad[10] = new GuiButton(10, numPad[7].x, numPad[7].y + bh + 4, bw, bh, Integer.toString(0));
        numPad[11] = new GuiButton(11, numPad[8].x, numPad[8].y + bh + 4, bw, bh, ")");

        buttonList.add(numPad[9]);
        buttonList.add(numPad[10]);
        buttonList.add(numPad[11]);

        clearButton = new GuiButton(buttonList.size(), numPad[0].x - bw - 4, numPad[4].y, bw, bh, "C");
        buttonList.add(clearButton);
        deleteButton = new GuiButton(buttonList.size(), clearButton.x, numPad[1].y, bw, bh, "<-");
        buttonList.add(deleteButton);

        GuiButton bufferButton;

        // equalsButtons = new GuiButton(buttonList.size(), numPad[11].xPosition + bw + 4, numPad[11].yPosition, bw, bh, "=");
        // buttonList.add(equalsButtons);
        buttonMap.put("=", new GuiButton(buttonList.size(), clearButton.x, numPad[7].y, bw, bh, "="));
        buttonMap.put(".", new GuiButton(buttonList.size(), clearButton.x, numPad[11].y, bw, bh, "."));
        buttonMap.put("+", new GuiButton(buttonList.size(), numPad[11].x + bw + 4, numPad[11].y, bw, bh, "+"));
        buttonMap.put("-", new GuiButton(buttonList.size(), numPad[8].x + bw + 4, numPad[8].y, bw, bh, "-"));
        buttonMap.put("*", new GuiButton(buttonList.size(), numPad[5].x + bw + 4, numPad[5].y, bw, bh, "*"));
        buttonMap.put("/", new GuiButton(buttonList.size(), numPad[2].x + bw + 4, numPad[2].y, bw, bh, "/"));
        buttonMap.put("M+", bufferButton = new GuiButton(buttonList.size(), numPad[11].x + (bw + 4 << 1), numPad[11].y, bw, bh, "M+"));
        buttonMap.put("M-", new GuiButton(buttonList.size(), numPad[8].x + (bw + 4 << 1), numPad[8].y, bw, bh, "M-"));
        buttonMap.put("M*", new GuiButton(buttonList.size(), numPad[5].x + (bw + 4 << 1), numPad[5].y, bw, bh, "M*"));
        buttonMap.put("M/", new GuiButton(buttonList.size(), numPad[2].x + (bw + 4 << 1), numPad[2].y, bw, bh, "M/"));
        buttonMap.put("MC", new GuiButton(buttonList.size(), numPad[0].x, numPad[0].y - bh - 4, bw, bh, "MC"));
        buttonMap.put("MR", new GuiButton(buttonList.size(), numPad[1].x, numPad[1].y - bh - 4, bw, bh, "MR"));
        buttonMap.put("MS", new GuiButton(buttonList.size(), numPad[2].x, numPad[2].y - bh - 4, bw, bh, "MS"));
        buttonMap.put("^", new GuiButton(buttonList.size(), numPad[2].x + bw + 4, numPad[2].y - bh - 4, bw, bh, "^"));
        buttonMap.put("" + GlobalConstants.SQ_ROOT_CHAR, new GuiButton(buttonList.size(), numPad[2].x + (bw + 4 << 1), numPad[2].y - bh - 4, bw, bh, "" + GlobalConstants.SQ_ROOT_CHAR));
        buttonMap.put("e", bufferButton = new GuiButton(buttonList.size(), bufferButton.x + bw + 4, numPad[11].y, bw, bh, "e"));
        buttonMap.put("" + GlobalConstants.PI_CHAR, new GuiButton(buttonList.size(), bufferButton.x, numPad[8].y, bw, bh, "" + GlobalConstants.PI_CHAR));

        for (GuiButton button : buttonMap.values()) {
            button.id = buttonList.size();
            buttonList.add(button);
        }

    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        final ItemStack calcStack = mc.player.getHeldItem(EnumHand.MAIN_HAND);
        if (calcStack != null && calcStack.getCount() > 0 && calcStack.getItem() == ModRegistry.ModItems.itemCalculator.getItem().getItem()) {
            NBTTagCompound comp = calcStack.getTagCompound();
            if (comp == null) calcStack.setTagCompound((comp = new NBTTagCompound()));

            comp.setString("CalculatorInput", drawString);
            comp.setInteger("CalculatorInputCharCount", charIndex);
            comp.setDouble("CalculatorMemoryBuffer", memoryBuffer.read());

            if (lastResult != null) {
                comp.setDouble("CalculatorLastResult", lastResult.getResult());
                comp.setString("CalculatorLastResultString", lastResult.getExpressionString());
            }

            else {
                comp.setDouble("CalculatorLastResult", 0.0d);
                comp.setString("CalculatorLastResultString", "0.0");
            }
        }
    }

    public void drawGuiContainerForegroundLayer(int x, int y) {
        // fontRendererObj.drawString(drawString, xSize - (width >> 1) - 8, guiTop, 0xffffffff);
        fontRenderer.drawString(drawString, (xSize >> 3) - (fontRenderer.getStringWidth("00") >> 1), (ySize >> 3), 0xffffffff);
    }

    public void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        /*final int size = fontRendererObj.getCharWidth('0');
        drawRect(guiLeft + xSize - (width >> 1) - (size >> 1) - 8, (guiTop << 1) - (size >> 1), size * 53 - 8,
                ((guiTop << 1) + (size << 1)) - (size >> 1), 0xff000000);*/
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) i, (float) j, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableRescaleNormal();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawGuiContainerForegroundLayer(mouseX, mouseY);

        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
    }

    @Override
    public void actionPerformed(GuiButton button) {
        // Is num key:
        if (button.id < numPad.length || (!button.displayString.equals("=")
                && !button.displayString.startsWith("M") && !button.displayString.startsWith("\u221A")
                && buttonMap.containsKey(button.displayString))) {

            if (charIndex < drawBuffer.length) {
                // drawBuffer[charIndex++] = (char) ('0' + button.id + 1);
                drawBuffer[charIndex++] = button.displayString.charAt(0);
                drawString = new String(drawBuffer);
            }
        }

        else if (button.id == clearButton.id) {
            for (int i = 0; i < charIndex; i++)
                drawBuffer[i] = 0;

            charIndex = 0;
            drawString = "";
        }

        else if (button.id == deleteButton.id) {
            if (charIndex > 0) {
                drawBuffer[--charIndex] = 0;
                drawString = new String(drawBuffer);
            }
        }

        // else if (button.id == equalsButtons.id) {
        else if (button.id == buttonMap.get("=").id) {
            // if (drawString.contains("=")) return;

            Interpreter interpreter = new Interpreter();
            lastResult = interpreter.processExpressionString(new Expression(drawString.substring(0, charIndex)), ClientProxy.getPlayer().getUniqueID().hashCode());

            if (!lastResult.isEmpty()) {
                // drawString = lastResult.getExpressionString();
                drawString = Double.toString(lastResult.getResult());
                charIndex = drawString.length();

                syncStringBuffer();
            }
        }

        else if (button.displayString.startsWith("\u221A")) {
            drawBuffer[charIndex++] = '^';
            drawBuffer[charIndex++] = '(';
            drawBuffer[charIndex++] = '1';
            drawBuffer[charIndex++] = '/';
            drawBuffer[charIndex++] = '2';
            drawBuffer[charIndex++] = ')';

            drawString = new String(drawBuffer);
        }

        else if (button.displayString.startsWith("M")) {
            final char secondChar = button.displayString.charAt(1);
            final double lastValue = lastResult != null ? lastResult.getResult() : 0.0d;

            switch (secondChar) {
                case '+':
                    memoryBuffer.add(lastValue);
                    break;

                case '-':
                    memoryBuffer.subtract(lastValue);
                    break;

                case '*':
                    memoryBuffer.multiply(lastValue);
                    break;

                case '/':
                    if (lastValue != 0.0d) memoryBuffer.divide(lastValue);
                    break;

                case 'C':
                    memoryBuffer.clear();
                    break;

                case 'R':
                    drawString = Double.toString(memoryBuffer.read());
                    charIndex = drawString.length();

                    syncStringBuffer();

                    break;

                case 'S':
                    memoryBuffer.store(lastValue);
                    break;

                default:

            }
        }
    }

    /**
     * Synchronizes the drawBuffer with the drawString.
     */
    private void syncStringBuffer() {
        if (drawString == null || drawString.isEmpty()) return;

        int i;
        for (i = 0; i < drawString.length(); i++)
            drawBuffer[i] = drawString.charAt(i);

        for ( ; i < drawBuffer.length; i++)
            drawBuffer[i] = '\0';
    }

    /**
     * Static class for storing values and emulating the
     * memory buffer function of calculators.
     *
     * @author hockeyhurd
     * @version 12/19/16
     */
    private static class MemoryBuffer {
        private double value;

        MemoryBuffer() {
            this(0.0d);
        }

        MemoryBuffer(double value) {
            if (value != Double.NaN)
                this.value = value;
        }

        void add(double value) {
            if (value != Double.NaN)
                this.value += value;
        }

        void subtract(double value) {
            if (value != Double.NaN)
                this.value -= value;
        }

        void multiply(double value) {
            if (value != Double.NaN)
                this.value *= value;
        }

        void divide(double value) {
            if (value != Double.NaN)
                this.value /= value;
        }

        void store(double value) {
            if (value != Double.NaN)
                this.value = value;
        }

        double read() {
            return value;
        }

        void clear() {
            value = 0.0d;
        }
    }

}
