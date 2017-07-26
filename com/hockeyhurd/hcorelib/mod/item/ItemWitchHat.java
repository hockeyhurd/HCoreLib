package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.item.armor.AbstractArmor;
import com.hockeyhurd.hcorelib.api.util.enums.EnumArmorType;

/**
 * @author hockeyhurd
 * @version 7/26/17
 */
public class ItemWitchHat extends AbstractArmor {

    public static final String NAME = "witchHat";

    /**
     * @param material    armor material to use.
     * @param renderIndex render index of item.
     * @param armorType   armor type.
     * @param assetDir
     */
    public ItemWitchHat(ArmorMaterial material, int renderIndex, EnumArmorType armorType, String assetDir) {
        super(material, renderIndex, armorType, assetDir, NAME);
    }

}
