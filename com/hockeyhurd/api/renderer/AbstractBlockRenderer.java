package com.hockeyhurd.api.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.hockeyhurd.api.util.TessellatorHelper;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class AbstractBlockRenderer implements ISimpleBlockRenderingHandler {

	protected double scale = 0.9d;
	protected int renderID, renderPass;
	protected IIcon icon;
	protected TessellatorHelper tess;
	
	public AbstractBlockRenderer(int renderID, int renderPass, IIcon icon) {
		this.renderID = renderID;
		this.renderPass = renderPass;
		this.icon = icon;
		
		tess = new TessellatorHelper(icon);
	}

	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (renderPass == 0) drawCuboid(block, x, y, z);
		else renderer.renderStandardBlock(Blocks.glass, x, y, z);
		return true;
	}

	public void drawCuboid(Block block, int x, int y, int z) {
		tess.drawCuboid(block, x, y, z, scale);
	}

	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	public int getRenderId() {
		return 0;
	}

}
