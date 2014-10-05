package com.hockeyhurd.mod.block.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.hockeyhurd.api.renderer.AbstractBlockRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WhiteBlockRenderer extends AbstractBlockRenderer {

	public WhiteBlockRenderer(int renderID, int renderPass, IIcon icon) {
		super(renderID, renderPass, icon);
	}
	
}
