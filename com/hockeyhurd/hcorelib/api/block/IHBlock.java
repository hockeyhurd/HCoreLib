package com.hockeyhurd.hcorelib.api.block;

import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

/**
 * Simple interface that all blocks should implement.
 *
 * @author hockeyhurd
 * @version 5/20/2016.
 */
public interface IHBlock {

	/**
	 * Gets the block instance.
	 *
	 * @return Block instance.
	 */
	Block getBlock();

	/**
	 * Gets whether this block has a special renderer.
	 *
	 * @return boolean result.
	 */
	boolean hasSpecialRenderer();

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

	/**
	 * Gets the itemblock of this block.
	 *
	 * @return ItemBlock object.
	 */
	ItemBlock getItemBlock();

	/**
	 * Gets the block hardness.
	 *
	 * @return float block hardness.
	 */
	float getBlockHardness();

	/**
	 * Gets the block's harvest level.
	 *
	 * @return EnumHarvestLevel data.
	 */
	EnumHarvestLevel getHarvestLevel();

}
