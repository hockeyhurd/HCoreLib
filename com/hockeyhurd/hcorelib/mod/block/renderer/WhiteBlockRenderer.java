package com.hockeyhurd.hcorelib.mod.block.renderer;

import com.hockeyhurd.hcorelib.api.client.renderer.AbstractBlockRenderer;
import com.hockeyhurd.hcorelib.api.math.Color4i;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

@SideOnly(Side.CLIENT)
public class WhiteBlockRenderer extends AbstractBlockRenderer {

	public WhiteBlockRenderer(int renderID, IIcon icon, Color4i color) {
		super(renderID, icon, color);
	}
	
}
