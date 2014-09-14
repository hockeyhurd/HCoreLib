package com.hockeyhurd.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public abstract class AbstractCreativeTab extends CreativeTabs {

	public AbstractCreativeTab(int par1, String par2) {
		super(par1, par2);
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Items.diamond;
	}
	
	public String getTranslatedTabLabel() {
		return "HCoreLib";
	}
	
	public boolean hasSearchBar() {
		return false;
	}

}