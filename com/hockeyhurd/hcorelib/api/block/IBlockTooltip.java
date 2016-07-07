package com.hockeyhurd.hcorelib.api.block;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Interfacing for blocks that require additional
 * tooltip information.
 *
 * @author hockeyhurd
 * @version 7/7/2016.
 */
public interface IBlockTooltip {

	/**
	 * Get the IHBlock instance.
	 *
	 * @return IHBlock instance.
	 */
	IHBlock getIHBlock();

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
