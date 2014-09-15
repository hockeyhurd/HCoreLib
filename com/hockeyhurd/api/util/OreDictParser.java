package com.hockeyhurd.api.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.hockeyhurd.mod.HCoreLibMain;

/**
 * Simple parsing class for checking ore dictionary for a given item and getting it by an ItemStack.
 * 
 * @author hockeyhurd
 */
public class OreDictParser {

	public OreDictParser() {
	}

	/**
	 * Attempts to search OreDictionary through the 'ores' key. If finds it, returns ItemStack from this key with its specified size, var size (defaulted to 1).
	 * 
	 * @param objectName
	 *            = name to find.
	 * @return ItemStack if found, else returns null.
	 */
	public static ItemStack getFromDict(String objectName) {
		return getFromDict(objectName, 1);
	}

	/**
	 * Attempts to search OreDictionary through the 'ores' key. If finds it, returns ItemStack from this key with its specified size, var size.
	 * 
	 * @param objectName
	 *            = name to find.
	 * @param size
	 *            = size of created ItemStack if found.
	 * @return ItemStack if found, else returns null.
	 */
	public static ItemStack getFromDict(String objectName, int size) {
		// If object name isn't valid or size is < 0 || > 64 return with information.
		if (!LogicHelper.nullCheckString(objectName) || size <= 0 || size > 64) {
			HCoreLibMain.lh.warn("Could not find object by name:", objectName, "Size:", size);
			return (ItemStack) null;
		}

		// Else proceed to iteration.
		else {
			ItemStack temp = null;
			String current = "";
			boolean flag = false;

			// Iterating through the ore dictionary searching for by name.
			for (int i = 0; i < OreDictionary.getOreNames().length; i++) {
				if (current.equals(OreDictionary.getOreNames()[i])) {
					HCoreLibMain.lh.info("Found object by name:", current + "!");
					flag = true;
					break;
				}
			}

			// If not found, return nothing.
			if (!flag) return (ItemStack) null;
			// Else, let's get some more information before returning.
			else {
				Block block = null;
				Item item = null;

				/*
				 * Checks if the stack is instance of Block or instance of Item. In theory, only one of the two objects should be null at a given instance; hence returning the correct stack size below.
				 */
				if (current.contains("ore") || current.contains("plank") || current.contains("wood") || current.contains("glass")) block = Block.getBlockById(OreDictionary.getOreID(current));
				else if (current.contains("ingot") || current.contains("dust") || current.contains("gem") || current.contains("sapling") || current.contains("quartz") || current.contains("dye")) item = Item.getItemById(OreDictionary.getOreID(current));
				temp = OreDictionary.getOres(current).get(0);

				/*
				 * Attempts to apply requested stack size. But first, let's make sure we know what type it is i.e. item or block. Create ItemStack object, temp, and spit out our findings.
				 */
				temp.stackSize = block != null && item == null ? 2 : (block == null && item != null ? 1 : 1);

				return flag && temp != null ? temp : (ItemStack) null;
			}
		}
	}

}
