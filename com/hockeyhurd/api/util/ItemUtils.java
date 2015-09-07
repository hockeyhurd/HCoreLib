package com.hockeyhurd.api.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Largely static class to further improve upon deprecated ItemHelper class.
 * @see com.hockeyhurd.api.util.ItemHelper
 *
 * @author hockeyhurd
 * @version 9/7/15
 */
public final class ItemUtils {

	private ItemUtils() {
	}

	public static Item getItem(Block block) {
		return Item.getItemFromBlock(block);
	}

	public static Item getItem(int id) {
		return Item.getItemById(id);
	}

	public static Item getItem(ItemStack stack) {
		return stack.getItem();
	}

	public static ItemStack createStack(Item item, int amount, int metadata) {
		return new ItemStack(item, Math.min(amount, item.getItemStackLimit()), metadata);
	}

	public static ItemStack createStack(Item item, int amount) {
		return createStack(item, amount, 0);
	}

	public static ItemStack createStack(Item item) {
		return createStack(item, 1, 0);
	}

	public static ItemStack createStack(Block block, int amount, int metadata) {
		return new ItemStack(block, Math.min(amount, getItem(block).getItemStackLimit()), metadata);
	}

	public static ItemStack createStack(Block block, int amount) {
		return createStack(block, amount, 0);
	}

	public static ItemStack createStack(Block block) {
		return createStack(block, 1, 0);
	}

	public static boolean itemListContains(int id) {
		return getItem(id) != null;
	}

	public static boolean isAnItem(Item item) {
		return item != null && Item.itemRegistry.containsKey(item);
	}

	public static String getUnlocalizedName(Item item) {
		return item != null ? item.getUnlocalizedName() : "This is not an item!";
	}

	@Override
	public String toString() {
		return "Trololololol :)";
	}

}
