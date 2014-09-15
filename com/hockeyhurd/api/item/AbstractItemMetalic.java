package com.hockeyhurd.api.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.hockeyhurd.mod.HCoreLibMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AbstractItemMetalic extends Item {
	
	protected String fileName;
	
	public AbstractItemMetalic(String name, String assetDir) {
		super();
		this.setUnlocalizedName(name);
		this.fileName = assetDir + name;
		this.setCreativeTab(HCoreLibMain.myCreativeTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		itemIcon = reg.registerIcon(this.fileName);
	}

}
