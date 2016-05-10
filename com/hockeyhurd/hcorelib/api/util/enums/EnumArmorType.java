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

}
