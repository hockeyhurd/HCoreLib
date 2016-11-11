package com.hockeyhurd.hcorelib.mod.client.gui;

import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
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
            "textures/items/itemCalculator.png");

    private int xSize, ySize;
    private int guiLeft, guiTop;
    private String drawString;
    private char[] drawBuffer;
    private int charIndex;

    private GuiButton[] numPad;

    public GuiCalculator() {
        this.xSize = 128;
        this.ySize = 128;
        this.drawBuffer = new char[0x20];
        this.charIndex = 0;
        this.drawString = "";
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        if (numPad == null) numPad = new GuiButton[9];

        final int bw = xSize / 10;
        final int bh = ySize / 10;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                GuiButton button = new GuiButton(x + y * 3, guiLeft + x * bw + x * (bw << 1),
                        guiTop + y * bh + y * (bh << 1), Integer.toString(1 + x + y * 3));
                button.width = bw;
                button.height = bh;

                numPad[x + y * 3] = button;
                buttonList.add(button);
            }
        }
    }

    public void drawGuiContainerForegroundLayer(int x, int y) {
        fontRendererObj.drawString(drawString, width >> 2, height >> 2, 0xafafafaf);
    }

    public void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
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
                drawBuffer[charIndex++] = (char) ('0' + button.id + 1);
                drawString = new String(drawBuffer);
            }
        }
    }

}
