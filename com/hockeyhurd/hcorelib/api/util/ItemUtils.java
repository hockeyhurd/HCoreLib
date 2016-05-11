package com.hockeyhurd.hcorelib.api.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Largely static class to further improve upon deprecated ItemHelper class.
 * @see com.hockeyhurd.hcorelib.api.util.ItemHelper
 *
 * @author hockeyhurd
 * @version 9/7/15
 */
public final class ItemUtils {

	private ItemUtils() {
	}

	/**
	 * Gets item from block.
	 *
	 * @param block block to reference.
	 * @return item object from provided block.
	 */
	public static Item getItem(Block block) {
		return Item.getItemFromBlock(block);
	}

	/**
	 * Gets item from item ID.
	 *
	 * @param id item's ID.
	 * @return item from item's ID.
	 */
	public static Item getItem(int id) {
		return Item.getItemById(id);
	}

	/**
	 * Gets item from provided itemstack.
	 *
	 * @param stack itemstack to reference.
	 * @return item object from itemstack.
	 */
	public static Item getItem(ItemStack stack) {
		return stack.getItem();
	}

	/**
	 * Helper method to create itemstack.
	 *
	 * @param item item to add to stack.
	 * @param amount amount of
	 * @param metadata metadata for itemstack.
	 * @return newly created itemstack object.
	 */
	public static ItemStack createStack(Item item, int amount, int metadata) {
		if (amount <= 0) amount = 1;
		amount = Math.min(amount, item.getItemStackLimit());

		return new ItemStack(item, amount, metadata);
	}

	/**
	 * Helper method to create itemstack.
	 *
	 * @param item item to add to stack.
	 * @param amount amount of
	 * @return newly created itemstack object.
	 */
	public static ItemStack createStack(Item item, int amount) {
		return createStack(item, amount, 0);
	}

	/**
	 * Helper method to create itemstack.
	 *
	 * @param item item to add to stack.
	 * @return newly created itemstack object.
	 */
	public static ItemStack createStack(Item item) {
		return createStack(item, 1, 0);
	}

	/**
	 * Helper method to create itemstack.
	 *
	 * @param block block to add to stack.
	 * @param amount amount of
	 * @param metadata metadata for itemstack.
	 * @return newly created itemstack object.
	 */
	public static ItemStack createStack(Block block, int amount, int metadata) {
		return new ItemStack(block, Math.min(amount, getItem(block).getItemStackLimit()), metadata);
	}

	/**
	 * Helper method to create itemstack.
	 *
	 * @param block block to add to stack.
	 * @param amount amount of
	 * @return newly created itemstack object.
	 */
	public static ItemStack createStack(Block block, int amount) {
		return createStack(block, amount, 0);
	}

	/**
	 * Helper method to create itemstack.
	 *
	 * @param block block to add to stack.
	 * @return newly created itemstack object.
	 */
	public static ItemStack createStack(Block block) {
		return createStack(block, 1, 0);
	}

	/**
	 * Function used to verify item ID is registered properly in item registry.
	 *
	 * @param id item ID to reference.
	 * @return true if registered, else may return false.
	 */
	public static boolean itemListContains(int id) {
		return getItem(id) != null;
	}

	/**
	 * Function used to verify item ID is registered properly in item registry.
	 *
	 * @param item item to reference.
	 * @return true if registered, else may return false.
	 */
	public static boolean isAnItem(Item item) {
		// return item != null && Item.itemRegistry.containsKey(item);
		return item != null;
	}

	/**
	 * Gets unlocalized name of provided item.
	 *
	 * @param item item to reference.
	 * @return unlocalized name of item.
	 */
	public static String getUnlocalizedName(Item item) {
		return item != null ? item.getUnlocalizedName() : "This is not an item!";
	}

	@Override
	public String toString() {
		return "Trololololol :)";
	}

}
