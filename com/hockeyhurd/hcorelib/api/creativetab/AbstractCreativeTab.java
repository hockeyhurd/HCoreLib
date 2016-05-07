package com.hockeyhurd.hcorelib.api.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Generalized abstract class for templating Creative Tab implementaions.
 *
 * @see net.minecraft.creativetab.CreativeTabs
 *
 * @author hockeyhurd
 * @version 4/25/16
 */
public abstract class AbstractCreativeTab extends CreativeTabs {

	protected final String text;

	/**
	 * @param id Creative tab ID.
	 * @param text Name of text.
     */
	public AbstractCreativeTab(int id, String text) {
		super(id, text);
		this.text = text;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Items.diamond;
	}

	@Override
	public String getTranslatedTabLabel() {
		return this.text;
	}

	@Override
	public boolean hasSearchBar() {
		return false;
	}

}
