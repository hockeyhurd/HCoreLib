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

	public static Block getBlockFromItem(Item item) {
		return Block.getBlockFromItem(item);
	}

	public static Block getBlockFromItem(ItemStack stack) {
		return Block.getBlockFromItem(stack.getItem());
	}

	public static Block getBlock(int id) {
		return Block.getBlockById(id);
	}

	public static Block getBlock(World world, int x, int y, int z) {
		return world.getBlock(x, y, z);
	}

	public static Block getBlock(World world, Vector3<Integer> vec) {
		return getBlock(world, vec.x, vec.y, vec.z);
	}

	public static int getBlockMetadata(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	public static int getBlockMetadata(World world, Vector3<Integer> vec) {
		return getBlockMetadata(world, vec.x, vec.y, vec.z);
	}

	public static boolean canMineBlock(EntityPlayer player, World world, int x, int y, int z) {
		return world.canMineBlock(player, x, y, z);
	}

	public static boolean canMineBlock(EntityPlayer player, World world, Vector3<Integer> vec) {
		return canMineBlock(player, world, vec.x, vec.y, vec.z);
	}

	public static boolean canMineBlock(EntityPlayer player, int x, int y, int z) {
		return player.worldObj.canMineBlock(player, x, y, z);
	}

	public static boolean canMineBlock(EntityPlayer player, Vector3<Integer> vec) {
		return canMineBlock(player, vec.x, vec.y, vec.z);
	}

	public static boolean blockExists(World world, int x, int y, int z) {
		return world.blockExists(x, y, z);
	}

	public static boolean blockExists(World world, Vector3<Integer> vec) {
		return blockExists(world, vec.x, vec.y, vec.z);
	}

	public static Material getBlockMaterial(World world, int x, int y, int z) {
		return getBlock(world, x, y, z).getMaterial();
	}

	public static Material getBlockMaterial(World world, Vector3<Integer> vec) {
		return getBlockMaterial(world, vec.x, vec.y, vec.z);
	}

	public static Material getBlockMaterial(Block block) {
		return block.getMaterial();
	}

	public static void destroyBlock(World world, int x, int y, int z, boolean drop) {
		world.func_147480_a(x, y, z, drop);
	}

	public static void destroyBlock(World world, int x, int y, int z) {
		destroyBlock(world, x, y, z, true);
	}

	public static void destroyBlock(World world, Vector3<Integer> vec, boolean drop) {
		destroyBlock(world, vec.x, vec.y, vec.z, drop);
	}

	public static void destroyBlock(World world, Vector3<Integer> vec) {
		destroyBlock(world, vec.x, vec.y, vec.z, true);
	}

	public static void setBlock(World world, int x, int y, int z, Block block, int metadata, int notify) {
		boolean flag = false;
		Block checkBlock = getBlock(world, x, y, z);
		int tempData = getBlockMetadata(world, x, y, z);
		if (checkBlock == block && tempData == metadata) flag = true;
		if (!flag) world.setBlock(x, y, z, block, metadata, notify);
		else HCoreLibMain.lh.warn("Couldn't place block:", getUnlocalized(block), "into world at", x, y, z, "with metadata:", metadata);
	}

	public static void setBlock(World world, int x, int y, int z, Block block, int metadata) {
		setBlock(world, x, y, z, block, metadata, 3);
	}

	public static void setBlock(World world, int x, int y, int z, Block block) {
		setBlock(world, x, y, z, block, 0, 3);
	}

	public static void setBlock(World world, Vector3<Integer> vec, Block block, int metadata, int notify) {
		setBlock(world, vec.x, vec.y, vec.z, block, metadata, notify);
	}

	public static void setBlock(World world, Vector3<Integer> vec, Block block, int metadata) {
		setBlock(world, vec.x, vec.y, vec.z, block, metadata);
	}

	public static void setBlock(World world, Vector3<Integer> vec, Block block) {
		setBlock(world, vec.x, vec.y, vec.z, block, 0);
	}

	public static void setBlockToAir(World world, int x, int y, int z) {
		world.setBlockToAir(x, y, z);
	}

	public static void setBlockToAir(World world, Vector3<Integer> vec) {
		world.setBlockToAir(vec.x, vec.y, vec.z);
	}

	public static boolean blockListContains(int id) {
		Block block = Block.getBlockById(id);
		return block != null && block != Blocks.air;
	}

	public static boolean blockListContains(Block block) {
		int id = Block.getIdFromBlock(block);
		Block b = Block.getBlockById(id);
		return b != null && b != Blocks.air;
	}

	public static String getLocalized(Block block) {
		return block != null ? block.getLocalizedName() : "This is not a block!";
	}

	public static String getUnlocalized(Block block) {
		return block != null ? block.getUnlocalizedName() : "This is not a block!";
	}

	@Override
	public String toString() {
		return "Trololololol :)";
	}

}
