package com.hockeyhurd.hcorelib.api.client.renderer;

import com.hockeyhurd.hcorelib.api.math.Color4i;
import com.hockeyhurd.hcorelib.api.util.TessellatorHelper;
import com.hockeyhurd.hcorelib.mod.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class AbstractBlockRenderer implements ISimpleBlockRenderingHandler {

	protected double scale = 0.9d;
	protected int renderID;
	protected IIcon icon;
	private Color4i color;
	protected TessellatorHelper tess;
	
	public AbstractBlockRenderer(int renderID, IIcon icon, Color4i color) {
		this.renderID = renderID;
		this.icon = icon;
		this.color = color;
		
		tess = new TessellatorHelper(icon);
	}

	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (ClientProxy.renderPass == 0) drawCuboid(x, y, z);
		else renderer.renderStandardBlock(block, x, y, z);
		return true;
	}

	public void drawCuboid(int x, int y, int z) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		
		tess.drawCuboid(x, y, z, scale, color);
		
		GL11.glPopMatrix();
	}

	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	public int getRenderId() {
		return renderID;
	}

}
