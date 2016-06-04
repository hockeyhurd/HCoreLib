package com.hockeyhurd.hcorelib.api.item.armor;

import com.hockeyhurd.hcorelib.api.item.IHItem;
import com.hockeyhurd.hcorelib.api.util.enums.EnumArmorType;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;

/**
 * Helper abstract claas for more easily creating item armor.
 * 
 * @author hockeyhurd
 * @version Jun 4, 2015
 */
public abstract class AbstractArmor extends ItemArmor implements IHItem {

	public final String ASSET_DIR;
	protected final EnumArmorType armorType;
	protected final String actualName;
	protected ResourceLocation resourceLocation;

	/**
	 * @param material armor material to use.
	 * @param renderIndex render index of item.
	 * @param armorType armor type.
	 */
	public AbstractArmor(ArmorMaterial material, int renderIndex, EnumArmorType armorType, String assetDir, String name) {
		super(material, renderIndex, armorType.getEquipmentSlot());
		this.ASSET_DIR = assetDir;
		this.armorType = armorType;

		this.actualName = createRegistryName(name, armorType);
		this.resourceLocation = new ResourceLocation(assetDir, actualName);
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
		return name + armorType.getFormattedName();
	}
	
	@Override
	public AbstractArmor getItem() {
		return this;
	}

	@Override
	public int getSizeOfSubItems() {
		return 0;
	}

	@Override
	public ResourceLocation getResourceLocation(int meta) {
		return resourceLocation;
	}

	@Override
	public String getName() {
		return actualName;
	}
	
}
