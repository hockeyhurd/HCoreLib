package com.hockeyhurd.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import com.hockeyhurd.api.util.TessellatorHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class AbstractItemRenderer implements IItemRenderer {

	protected static final boolean testFlagColor = false;
	protected TessellatorHelper tessHelp;
	protected IIcon icon;
	
	public AbstractItemRenderer(IIcon icon) {
		this.icon = icon;
		tessHelp = new TessellatorHelper(icon);
	}

	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		tessHelp.renderItem(type, item, true);
	}

}
