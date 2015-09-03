package com.hockeyhurd.mod.block.renderer;

import com.hockeyhurd.api.math.Color4i;
import com.hockeyhurd.api.renderer.AbstractBlockRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

@SideOnly(Side.CLIENT)
public class WhiteBlockRenderer extends AbstractBlockRenderer {

	public WhiteBlockRenderer(int renderID, IIcon icon, Color4i color) {
		super(renderID, icon, color);
	}
	
}
