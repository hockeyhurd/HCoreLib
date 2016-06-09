package com.hockeyhurd.hcorelib.api.client.util;

import com.hockeyhurd.hcorelib.api.block.IHBlock;
import com.hockeyhurd.hcorelib.api.item.IHItem;
import com.hockeyhurd.hcorelib.api.util.StringUtils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.hockeyhurd.hcorelib.api.util.ItemUtils.getItem;

/**
 * Class used exclusively for registering models and textures.
 *
 * @author hockeyhurd
 * @version 5/20/2016.
 */
@SideOnly(Side.CLIENT)
public final class ModelRegistry {

	public static final String DEFAULT_TAG = "inventory";

	private ModelRegistry() {
	}

	/**
	 * Registers a block's model.
	 *
	 * @param block Block to register.
	 */
	public static void registerBlock(IHBlock block) {
		if (block != null /*&& !block.hasSpecialRenderer()*/) registerBlock(block, DEFAULT_TAG);
	}

	/**
	 * Registers a block's model.
	 *
	 * @param block Block to register.
	 * @param tag String tag to register with.
	 */
	public static void registerBlock(IHBlock block, String tag) {
		if (block != null && StringUtils.nullCheckString(tag)) {
			ModelLoader.setCustomModelResourceLocation(getItem(block.getBlock()), 0, getModelResourceLocation(block.getResourceLocation(), tag));
		}
	}

	/**
	 * Registers a item's model.
	 *
	 * @param item Item to register.
	 */
	public static void registerItem(IHItem item) {
		if (item != null) {
			if (!item.getItem().getHasSubtypes()) registerItem(item, DEFAULT_TAG);
			else registerMetaItem(item, DEFAULT_TAG);
		}
	}

	/**
	 * Registers a item's model.
	 *
	 * @param item Item to register.
	 * @param tag String tag to register with.
	 */
	public static void registerItem(IHItem item, String tag) {
		if (item != null && StringUtils.nullCheckString(tag))
			ModelLoader.setCustomModelResourceLocation(item.getItem(), 0, getModelResourceLocation(item.getResourceLocation(0), tag));
	}

	public static void registerMetaItem(IHItem item, String tag) {
		if (item != null && StringUtils.nullCheckString(tag)) {
			if (!item.getItem().getHasSubtypes()) return;

			final ResourceLocation[] locations = new ResourceLocation[item.getSizeOfSubItems()];

			for (int i = 0; i < locations.length; i++) {
				locations[i] = item.getResourceLocation(i);
				ModelLoader.setCustomModelResourceLocation(item.getItem(), i, getModelResourceLocation(locations[i], tag));
			}

			ModelLoader.registerItemVariants(item.getItem(), locations);
		}
	}

	/**
	 * Factory function to generate ModelResourceLocation.
	 *
	 * @param resourceLocation ResourceLocation.
	 * @param tag String tag.
	 * @return Generated ModelResourceLocation.
	 */
	private static ModelResourceLocation getModelResourceLocation(ResourceLocation resourceLocation, String tag) {
		return new ModelResourceLocation(resourceLocation, tag);
	}

}
