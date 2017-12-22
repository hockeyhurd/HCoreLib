package com.hockeyhurd.hcorelib.mod.client.gui;

import com.hockeyhurd.hcorelib.mod.tileentity.TileFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Gui for TileFurnace.
 *
 * @author hockeyhurd
 * @version 3/14/17
 */
@SideOnly(Side.CLIENT)
public class GuiFurnace extends GuiContainer {

    private final TileFurnace te;
    private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/furnace.png");
    private String stringToDraw;

    public GuiFurnace(InventoryPlayer inv, TileFurnace te) {
        super(new ContainerFurnace(inv, te));

        this.te = te;
    }

    @Override
    public void drawGuiContainerForegroundLayer(int x, int y) {
        stringToDraw = !this.te.hasCustomInventoryName() ? this.te.getInventoryName() : I18n.format(this.te.getInventoryName(), new Object[0]);

        this.fontRenderer.drawString(stringToDraw, (this.xSize >> 1) - (this.fontRenderer.getStringWidth(stringToDraw) >> 1), 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        final int xStart = (width - xSize) >> 1;
        final int yStart = (height - ySize) >> 1;

        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        int progress;
        if (TileFurnace.isBurning(te)) {
            progress = te.getBurnTime();
            if (progress == 0) progress = 200;
            progress = te.getBurnTime() * 13 / progress;
            drawTexturedModalRect(xStart + 56, yStart + 36 + 12 - progress, 176, 12 - progress, 14, progress + 1);
        }

        progress = te.getCookProgressScaled(24);
        drawTexturedModalRect(xStart + 79, yStart + 34, 176, 14, progress + 1, 16);
    }

}
