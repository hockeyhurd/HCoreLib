package com.hockeyhurd.hcorelib.api.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Simple interface that all items should implement.
 *
 * @author hockeyhurd
 * @version 5/20/2016.
 */
public interface IHItem {

	/**
	 * Gets the item instance.
	 *
	 * @return Item instance.
	 */
	Item getItem();

	/**
	 * Gets the number of sub-items.
	 *
	 * @return number of sub-items.
	 */
	int getSizeOfSubItems();

	/**
	 * Gets the resouce location.
	 *
	 * @return ResourceLocation.
	 */
	ResourceLocation getResourceLocation();

	/**
	 * Gets the name of the block.
	 *
	 * @return String name.
	 */
	String getName();

}
