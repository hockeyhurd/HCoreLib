package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.item.AbstractItemRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemRendererHiddenWhite extends AbstractItemRenderer implements IItemRenderer {

	public ItemRendererHiddenWhite(IIcon icon) {
		super(icon);
	}

}
