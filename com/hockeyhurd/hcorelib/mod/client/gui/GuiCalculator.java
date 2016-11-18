package com.hockeyhurd.hcorelib.mod.client.gui;

import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
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

    public GuiCalculator() {
        this.xSize = 248;
        this.ySize = 166;
        this.drawBuffer = new char[0x20];
        this.charIndex = 0;
        this.drawString = "";
    }

    @Override
    public void initGui() {
        super.initGui();

        final ItemStack calcStack = mc.thePlayer.getHeldItem(EnumHand.MAIN_HAND);
        if (calcStack != null && calcStack.stackSize > 0 && calcStack.getItem() == HCoreLibMain.itemCalculator) {
            NBTTagCompound comp = calcStack.getTagCompound();
            if (comp != null) {
                drawString = comp.getString("CalculatorInput");
                charIndex = comp.getInteger("CalculatorInputCharCount");

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

        numPad[9] = new GuiButton(9, numPad[6].xPosition, numPad[6].yPosition + bh + 4, bw, bh, "(");
        numPad[10] = new GuiButton(10, numPad[7].xPosition, numPad[7].yPosition + bh + 4, bw, bh, Integer.toString(0));
        numPad[11] = new GuiButton(11, numPad[8].xPosition, numPad[8].yPosition + bh + 4, bw, bh, ")");

        buttonList.add(numPad[9]);
        buttonList.add(numPad[10]);
        buttonList.add(numPad[11]);

        clearButton = new GuiButton(buttonList.size(), numPad[0].xPosition - bw - 4, numPad[7].yPosition, bw, bh, "C");
        buttonList.add(clearButton);
        deleteButton = new GuiButton(buttonList.size(), clearButton.xPosition, numPad[4].yPosition, bw, bh, "<-");
        buttonList.add(deleteButton);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        final ItemStack calcStack = mc.thePlayer.getHeldItem(EnumHand.MAIN_HAND);
        if (calcStack != null && calcStack.stackSize > 0 && calcStack.getItem() == HCoreLibMain.itemCalculator) {
            NBTTagCompound comp = calcStack.getTagCompound();
            if (comp == null) calcStack.setTagCompound((comp = new NBTTagCompound()));

            comp.setString("CalculatorInput", drawString);
            comp.setInteger("CalculatorInputCharCount", charIndex);
        }
    }

    public void drawGuiContainerForegroundLayer(int x, int y) {
        // fontRendererObj.drawString(drawString, xSize - (width >> 1) - 8, guiTop, 0xffffffff);
        fontRendererObj.drawString(drawString, (xSize >> 3) - (fontRendererObj.getStringWidth("00") >> 1), (guiTop - (ySize >> 4)) >> 1, 0xffffffff);
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
        if (button.id < numPad.length) {
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
    }

}
