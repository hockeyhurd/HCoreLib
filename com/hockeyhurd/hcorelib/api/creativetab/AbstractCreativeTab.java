package com.hockeyhurd.hcorelib.api.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
