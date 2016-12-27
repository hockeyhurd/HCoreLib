package com.hockeyhurd.hcorelib.api.handler.tooltip;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Generic interfacing for blocks/items that require additional
 * tooltip information.
 *
 * @author hockeyhurd
 * @version 12/26/2016.
 */
public interface ITooltip<T> {

	/**
	 * Gets the type.
	 *
	 * @return Object type.
	 */
	T getType();

	/**
	 * Gets if this is a block instance.
	 *
	 * @return boolean result.
	 */
	boolean isBlock();

	/**
	 * Gets if this is a item instance.
	 *
	 * @return boolean result.
	 */
	boolean isItem();

	/**
	 * Flag whether this block has shift information.
	 *
	 * @return boolean result.
	 */
	boolean hasShiftInformation();

	/**
	 * Flag whether this block has control information.
	 *
	 * @return boolean result.
	 */
	boolean hasControlInformation();

	/**
	 * Adds tooltip information.
	 *
	 * @param list List of Strings to append.
	 * @param itemStack ItemStack instance.
	 */
	void addInformation(List<String> list, ItemStack itemStack);

	/**
	 * Adds tooltip information when 'Shift' key is pressed.
	 *
	 * @param list List of Strings to append.
	 * @param itemStack ItemStack instance.
	 */
	void addShiftInformation(List<String> list, ItemStack itemStack);

	/**
	 * Adds tooltip information when 'Ctrl' key is pressed.
	 *
	 * @param list List of Strings to append.
	 * @param itemStack ItemStack instance.
	 */
	void addControlInformation(List<String> list, ItemStack itemStack);

}
