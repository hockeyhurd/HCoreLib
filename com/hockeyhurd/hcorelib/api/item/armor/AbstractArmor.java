package com.hockeyhurd.hcorelib.api.item.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Helper abstract claas for more easily creating item armor.
 * 
 * @author hockeyhurd
 * @version Jun 4, 2015
 */
public abstract class AbstractArmor extends ItemArmor {

	public final String ASSET_DIR, PATH_MAT;
	
	/**
	 * @param material armor material to use.
	 * @param renderIndex render index of item.
	 * @param armorType armor type ordinal.
	 */
	public AbstractArmor(ArmorMaterial material, int renderIndex, int armorType, String assetDir, String pathMat) {
		super(material, renderIndex, armorType);
		this.ASSET_DIR = assetDir;
		this.PATH_MAT = pathMat;
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.minecraft.item.Item#getArmorTexture(net.minecraft.item.ItemStack, net.minecraft.entity.Entity, int, java.lang.String)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public abstract String getArmorTexture(ItemStack stack, Entity e, int slot, String type);

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.item.ItemArmor#registerIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		itemIcon = reg.registerIcon(this.ASSET_DIR + getUnlocalizedName().substring(5));
	}

	/**
	 * @return suffix i.e. item.zPlatedHelmet -> Helmet
	 */
	protected String getSuffix() {
		return getUnlocalizedName().substring(PATH_MAT.length());
	}
	
}
