package com.hockeyhurd.mod.item;

import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import com.hockeyhurd.api.item.AbstractItemRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemRendererHiddenWhite extends AbstractItemRenderer implements IItemRenderer {

	public ItemRendererHiddenWhite(IIcon icon) {
		super(icon);
	}

}
