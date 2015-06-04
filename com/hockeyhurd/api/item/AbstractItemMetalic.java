package com.hockeyhurd.api.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Generic class for creating but not limited to metalic items i.e. ingots, dusts, etc. 
 * 
 * @author hockeyhurd
 * @version Jun 4, 2015
 */
public abstract class AbstractItemMetalic extends Item {
	
	protected String fileName;
	
	public AbstractItemMetalic(String name, String assetDir) {
		super();
		this.setUnlocalizedName(name);
		this.fileName = assetDir + name;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		itemIcon = reg.registerIcon(this.fileName);
	}

}
