package com.hockeyhurd.api.util;

import com.hockeyhurd.mod.HCoreLibMain;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple parsing class for checking ore dictionary for a given item and getting it by an ItemStack.
 * 
 * @author hockeyhurd
 */
public class OreDictParser {

	/** Internal mapping to use for caching data from ore dictionary. */
	private static final Map<String, List<ItemStack>> internalMapByString = new HashMap<String, List<ItemStack>>();

	/**
	 * Internal mapping to use for caching data from ore dictionary.
	 */
	private static final Map<ItemStack, String> internalMapByStack = new HashMap<ItemStack, String>();

	private OreDictParser() {
	}

	/**
	 * Method to push ore dictionary info into internal mapping.
	 *
	 * @param key ore dictionary key.
	 * @param list list of oredictionary itemstacks.
	 */
	private static void pushListToMap(String key, List<ItemStack> list) {
		if (list != null && !list.isEmpty()) {
			List<ItemStack> putList = new ArrayList<ItemStack>(list.size());
			ItemStack buffer;

			for (ItemStack stack : list) {
				buffer = stack.copy();
				buffer.stackSize = 1;

				putList.add(buffer);
			}

			internalMapByString.put(key, putList);
		}
	}

	/**
	 * Method to push ore dictionary into into internal mapping.
	 *
	 * @param key   itemstack key.
	 * @param value oredictionaried string value.
	 */
	private static void pushItemStackToMap(ItemStack key, String value) {
		if (key != null && key.stackSize > 0 && LogicHelper.nullCheckString(value)) internalMapByStack.put(key, value);
	}

	/**
	 * Attempts to search OreDictionary through the 'ores' key. If finds it, returns ItemStack from this key with its specified size, var size (defaulted to 1).
	 * 
	 * @param objectName
	 *            = name to find.
	 * @return ItemStack if found, else returns null.
	 */
	@Deprecated
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
	@Deprecated
	public static ItemStack getFromDict(String objectName, int size) {
		// If object name isn't valid or size is < 0 || > 64 return with information.
		if (!LogicHelper.nullCheckString(objectName) || size <= 0 || size > 64) {
			HCoreLibMain.lh.warn("Could not find object by name:", objectName, "Size:", size);
			return null;
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

				return flag && temp != null ? temp : null;
			}
		}
	}

	/**
	 * Function to get list of itemstacks by name from ore dictionary.
	 *
	 * @param objectName name to find.
	 * @param size size to set stacks to.
	 * @return list of itemstacks if found in ore dictionary, else can return NULL.
	 */
	public static List<ItemStack> getFromOreDict(String objectName, int size) {
		return getFromOreDict(objectName, size, true);
	}

	/**
	 * Function to get list of itemstacks by name from ore dictionary.
	 *
	 * @param objectName name to find.
	 * @param size size to set stacks to.
	 * @param useInternal flag when set to true will attempt to use internal mappings.
	 * @return list of itemstacks if found in ore dictionary, else can return NULL.
	 */
	public static List<ItemStack> getFromOreDict(String objectName, int size, boolean useInternal) {
		if (!LogicHelper.nullCheckString(objectName) || size <= 0 || size > 0x40) {
			HCoreLibMain.lh.warn("Could not find object by name:", objectName, "Size:", size);
			return null;
		}

		else {
			List<ItemStack> list = null;
			// List<ItemStack> retList = OreDictionary.getOres(objectName);
			List<ItemStack> retList;

			if (useInternal && !internalMapByString.isEmpty() && internalMapByString.containsKey(objectName)) retList = internalMapByString.get(objectName);

			else retList = OreDictionary.getOres(objectName);

			if (retList != null && !retList.isEmpty()) {
				list = new ArrayList<ItemStack>(retList.size());
				ItemStack buffer;

				for (ItemStack stack : retList) {
					buffer = stack.copy();
					buffer.stackSize = size;

					list.add(buffer);
				}

				if (!internalMapByString.containsKey(objectName)) pushListToMap(objectName, retList);
			}

			return list;
		}
	}

	/**
	 * Helper method to get name of itemstack from ore dictionary if found, else will return empty string.
	 *
	 * @param stack stack to check.
	 * @return ore dict name if found, else returns empty string.
	 */
	public static String getOreDictName(final ItemStack stack) {
		if (stack == null || stack.stackSize == 0) return "";

		if (internalMapByStack.containsKey(stack)) return internalMapByStack.get(stack);
		else {
			String ret = OreDictionary.getOreIDs(stack).length > 0 ? OreDictionary.getOreName(OreDictionary.getOreIDs(stack)[0]) : "";
			if (ret.length() > 0) pushItemStackToMap(stack, ret);

			return ret;
		}
	}

}
