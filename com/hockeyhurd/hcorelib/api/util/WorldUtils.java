package com.hockeyhurd.hcorelib.api.util;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * General purpose utility class for common interactions with the world.
 *
 * @author hockeyhurd
 * @version 8/4/2016.
 */
public final class WorldUtils {

	private static final Random random = new Random(new Random().nextInt());

	private WorldUtils() {
	}

	private static EntityItem createEntityItem(World world, Vector3<Integer> vec, ItemStack stack) {
		return new EntityItem(world, vec.x, vec.y, vec.z, stack);
	}

	public static void spawnItemEntity(World world, Vector3<Integer> vec, ItemStack stack) {
		if (world != null && !world.isRemote && vec != null && stack != null && stack.stackSize > 0)
			world.spawnEntityInWorld(createEntityItem(world, vec, stack));
	}

	public static void spawnItemEntityList(World world, Vector3<Integer> vec, List<ItemStack> list) {
		if (world != null && !world.isRemote && vec != null && list != null && !list.isEmpty()) {
			for (ItemStack stack : list) {
				if (stack != null && stack.stackSize > 0)
					world.spawnEntityInWorld(createEntityItem(world, vec, stack));
			}
		}
	}

}
