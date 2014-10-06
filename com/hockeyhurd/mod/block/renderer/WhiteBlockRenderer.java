package com.hockeyhurd.mod.block.renderer;

import net.minecraft.util.IIcon;

import com.hockeyhurd.api.renderer.AbstractBlockRenderer;
import com.hockeyhurd.api.renderer.Color4i;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WhiteBlockRenderer extends AbstractBlockRenderer {

	public WhiteBlockRenderer(int renderID, IIcon icon, Color4i color) {
		super(renderID, icon, color);
	}
	
}
