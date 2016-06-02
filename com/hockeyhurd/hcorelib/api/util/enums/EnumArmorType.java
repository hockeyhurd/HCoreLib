package com.hockeyhurd.hcorelib.api.util.enums;

import net.minecraft.inventory.EntityEquipmentSlot;

import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * Enumeration for Armor.
 *
 * @author hockeyhurd
 * @version 5/9/2016.
 */
public enum EnumArmorType {

	HELMET, CHEST, LEGGINGS, BOOTS, SHIELD;

	private static final EntityEquipmentSlot[] slots = {
			HEAD, EntityEquipmentSlot.CHEST, LEGS,
			FEET, OFFHAND
	};

	/**
	 * Gets the equipment slot.
	 *
	 * @return EntityEquipmentSlot.
	 */
	public EntityEquipmentSlot getEquipmentSlot() {
		return slots[ordinal()];
	}

	/**
	 * Gets the formatted name typically used in resource locations.
	 *
	 * @return String formatted name.
	 */
	public String getFormattedName() {
		final String name = name();

		return name.charAt(0) + name.substring(1).toLowerCase();
	}

}
