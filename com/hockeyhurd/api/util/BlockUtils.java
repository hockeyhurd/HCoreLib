package com.hockeyhurd.api.util;

import com.hockeyhurd.api.math.Vector3;
import com.hockeyhurd.mod.HCoreLibMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Largely static class to further improve upon deprecated BlockHelper class.
 * @see com.hockeyhurd.api.util.BlockHelper
 *
 * @author hockeyhurd
 * @version 9/6/15
 */
public final class BlockUtils {

	private BlockUtils() {
	}

	/**
	 * Gets block from provided item.
	 *
	 * @param item to reference.
	 * @return block from item if permissible, else may return NULL!
	 */
	public static Block getBlockFromItem(Item item) {
		return Block.getBlockFromItem(item);
	}

	/**
	 * Gets block from itemstack derived from getBlockFromItem(stack.getItem()).
	 *
	 * @param stack stack to reference item.
	 * @return block from itemstack if permissible, else may return NULL!
	 */
	public static Block getBlockFromItem(ItemStack stack) {
		return Block.getBlockFromItem(stack.getItem());
	}

	/**
	 * Gets block from integer ID.
	 *
	 * @param id to check/get from.
	 * @return block if found, else may return NULL!
	 */
	public static Block getBlock(int id) {
		return Block.getBlockById(id);
	}

	/**
	 * Gets block from world coordinates.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @return block in world at coordinate.
	 */
	public static Block getBlock(World world, int x, int y, int z) {
		return world.getBlock(x, y, z);
	}

	/**
	 * Gets block from world coordinates.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @return block in world at coordinate.
	 */
	public static Block getBlock(World world, Vector3<Integer> vec) {
		return getBlock(world, vec.x, vec.y, vec.z);
	}

	/**
	 * Gets block metadata from world coordinates.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @return block in world at coordinate.
	 */
	public static int getBlockMetadata(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	/**
	 * Gets block metadata from world coordinates.
	 *
	 * @param world world object to reference.
	 * @param vec  vector3 to check (x, y, z).
	 * @return block in world at coordinate.
	 */
	public static int getBlockMetadata(World world, Vector3<Integer> vec) {
		return getBlockMetadata(world, vec.x, vec.y, vec.z);
	}

	/**
	 * Checks if given player can mine/break block at world coordinate.
	 *
	 * @param player player to reference.
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @return true if can be mined by provided player, else returns false.
	 */
	public static boolean canMineBlock(EntityPlayer player, World world, int x, int y, int z) {
		return world.canMineBlock(player, x, y, z);
	}

	/**
	 * Checks if given player can mine/break block at world coordinate.
	 *
	 * @param player player to reference.
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @return true if can be mined by provided player, else returns false.
	 */
	public static boolean canMineBlock(EntityPlayer player, World world, Vector3<Integer> vec) {
		return canMineBlock(player, world, vec.x, vec.y, vec.z);
	}

	/**
	 * Checks if given player can mine/break block at player-world coordinate.
	 *
	 * @param player player to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @return true if can be mined by provided player, else returns false.
	 */
	public static boolean canMineBlock(EntityPlayer player, int x, int y, int z) {
		return player.worldObj.canMineBlock(player, x, y, z);
	}

	/**
	 * Checks if given player can mine/break block at player-world coordinate.
	 *
	 * @param player player to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @return true if can be mined by provided player, else returns false.
	 */
	public static boolean canMineBlock(EntityPlayer player, Vector3<Integer> vec) {
		return canMineBlock(player, vec.x, vec.y, vec.z);
	}

	/**
	 * Checks if block exists (i.e. <block> != Blocks.air) at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @return true if block exists, else returns false.
	 */
	public static boolean blockExists(World world, int x, int y, int z) {
		return world.blockExists(x, y, z);
	}

	/**
	 * Checks if block exists (i.e. <block> != Blocks.air) at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @return true if block exists, else returns false.
	 */
	public static boolean blockExists(World world, Vector3<Integer> vec) {
		return blockExists(world, vec.x, vec.y, vec.z);
	}

	/**
	 * Gets block material from block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @return block material.
	 */
	public static Material getBlockMaterial(World world, int x, int y, int z) {
		return getBlock(world, x, y, z).getMaterial();
	}

	/**
	 * Gets block material from block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @return block material.
	 */
	public static Material getBlockMaterial(World world, Vector3<Integer> vec) {
		return getBlockMaterial(world, vec.x, vec.y, vec.z);
	}

	/**
	 * Gets block material from provided block.
	 *
	 * @param block block to reference.
	 * @return block material.
	 */
	public static Material getBlockMaterial(Block block) {
		return block.getMaterial();
	}

	/**
	 * Destroys block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @param drop set to true if should drop destroyed block, else set to false to disable.
	 */
	public static void destroyBlock(World world, int x, int y, int z, boolean drop) {
		world.func_147480_a(x, y, z, drop);
	}

	/**
	 * Destroys block at world coordinate with block drops.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 */
	public static void destroyBlock(World world, int x, int y, int z) {
		destroyBlock(world, x, y, z, true);
	}

	/**
	 * Destroys block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @param drop set to true if should drop destroyed block, else set to false to disable.
	 */
	public static void destroyBlock(World world, Vector3<Integer> vec, boolean drop) {
		destroyBlock(world, vec.x, vec.y, vec.z, drop);
	}

	/**
	 * Destroys block at world coordinate with block drops.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 */
	public static void destroyBlock(World world, Vector3<Integer> vec) {
		destroyBlock(world, vec.x, vec.y, vec.z, true);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @param block block to set.
	 * @param metadata metadata of block to set.
	 * @param notify notification level, default should be '3' to notify and update client.
	 */
	public static void setBlock(World world, int x, int y, int z, Block block, int metadata, int notify) {
		boolean flag = false;
		Block checkBlock = getBlock(world, x, y, z);
		int tempData = getBlockMetadata(world, x, y, z);
		if (checkBlock == block && tempData == metadata) flag = true;
		if (!flag) world.setBlock(x, y, z, block, metadata, notify);
		else HCoreLibMain.logHelper.warn("Couldn't place block:", getUnlocalized(block), "into world at", x, y, z, "with metadata:", metadata);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @param block block to set.
	 * @param metadata metadata of block to set.
	 */
	public static void setBlock(World world, int x, int y, int z, Block block, int metadata) {
		setBlock(world, x, y, z, block, metadata, 3);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @param block block to set.
	 */
	public static void setBlock(World world, int x, int y, int z, Block block) {
		setBlock(world, x, y, z, block, 0, 3);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @param block block to set.
	 * @param metadata metadata of block to set.
	 * @param notify notification level, default should be '3' to notify and update client.
	 */
	public static void setBlock(World world, Vector3<Integer> vec, Block block, int metadata, int notify) {
		setBlock(world, vec.x, vec.y, vec.z, block, metadata, notify);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @param block block to set.
	 * @param metadata metadata of block to set.
	 */
	public static void setBlock(World world, Vector3<Integer> vec, Block block, int metadata) {
		setBlock(world, vec.x, vec.y, vec.z, block, metadata);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @param block block to set.
	 */
	public static void setBlock(World world, Vector3<Integer> vec, Block block) {
		setBlock(world, vec.x, vec.y, vec.z, block, 0);
	}

	/**
	 * Sets block to air at given world coordinate.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 */
	public static void setBlockToAir(World world, int x, int y, int z) {
		world.setBlockToAir(x, y, z);
	}

	/**
	 * Sets block to air at given world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 */
	public static void setBlockToAir(World world, Vector3<Integer> vec) {
		world.setBlockToAir(vec.x, vec.y, vec.z);
	}

	/**
	 * Method used to verify block is registered.
	 *
	 * @param id ID of block to check.
	 * @return true if verified, else may return false.
	 */
	public static boolean blockListContains(int id) {
		Block block = Block.getBlockById(id);
		return block != null && block != Blocks.air;
	}

	/**
	 * Method used to verify block is registered.
	 *
	 * @param block Block to check.
	 * @return true if verified, else may return false.
	 */
	public static boolean blockListContains(Block block) {
		int id = Block.getIdFromBlock(block);
		Block b = Block.getBlockById(id);
		return b != null && b != Blocks.air;
	}

	/**
	 * Gets localized name of block block.
	 *
	 * @param block block to reference.
	 * @return localized name of block as string.
	 */
	public static String getLocalized(Block block) {
		return block != null ? block.getLocalizedName() : "This is not a block!";
	}

	/**
	 * Gets unlocalized name of block block.
	 *
	 * @param block block to reference.
	 * @return unlocalized name of block as string.
	 */
	public static String getUnlocalized(Block block) {
		return block != null ? block.getUnlocalizedName() : "This is not a block!";
	}

	@Override
	public String toString() {
		return "Trololololol :)";
	}

}
