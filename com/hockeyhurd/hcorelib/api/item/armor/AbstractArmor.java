package com.hockeyhurd.hcorelib.api.item.armor;

import com.hockeyhurd.hcorelib.api.util.enums.EnumArmorType;
import net.minecraft.item.ItemArmor;

/**
 * Helper abstract claas for more easily creating item armor.
 * 
 * @author hockeyhurd
 * @version Jun 4, 2015
 */
public abstract class AbstractArmor extends ItemArmor {

	public final String ASSET_DIR, PATH_MAT;
	protected final EnumArmorType armorType;

	/**
	 * @param material armor material to use.
	 * @param renderIndex render index of item.
	 * @param armorType armor type.
	 */
	public AbstractArmor(ArmorMaterial material, int renderIndex, EnumArmorType armorType, String assetDir, String name, String pathMat) {
		super(material, renderIndex, armorType.getEquipmentSlot());
		this.ASSET_DIR = assetDir;
		this.PATH_MAT = pathMat;
		this.armorType = armorType;

		final String actualName = createRegistryName(name, armorType);
		setUnlocalizedName(actualName);
		setRegistryName(actualName);
	}

	/**
	 * Creates the corrected registry name.
	 *
	 * @param name Base name of armor set.
	 * @param armorType EnumArmorType.
	 * @return Corrected registry name.
	 */
	protected static String createRegistryName(String name, EnumArmorType armorType) {
		return name + '_' + armorType.name().charAt(0) + armorType.name().substring(1).toLowerCase();
	}
	
	/**
	 * @return suffix i.e. item.zPlatedHelmet -> Helmet
	 */
	protected String getSuffix() {
		return getUnlocalizedName().substring(PATH_MAT.length());
	}
	
}
