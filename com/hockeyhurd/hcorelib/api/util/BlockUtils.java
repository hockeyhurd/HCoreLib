package com.hockeyhurd.hcorelib.api.util;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Largely static class block utility class.
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
	public static IBlockState getBlock(World world, int x, int y, int z) {
		return world.getBlockState(createBlockPos(x, y, z));
	}

	/**
	 * Gets block from world coordinates.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @return block in world at coordinate.
	 */
	public static IBlockState getBlock(World world, Vector3<Integer> vec) {
		return getBlock(world, vec.x, vec.y, vec.z);
	}

	/**
	 * Gets block from world coordinates.
	 *
	 * @param world World.
	 * @param blockPos BlockPos.
	 * @return IBlockState.
	 */
	public static IBlockState getBlock(World world, BlockPos blockPos) {
		return world.getBlockState(blockPos);
	}

	/**
	 * Gets block metadata.
	 *
	 * @param world World.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @return int metadata.
	 */
	public static int getBlockMetadata(World world, int x, int y, int z) {
		return getBlockMetadata(world, createBlockPos(x, y, z));
	}

	/**
	 * Gets block metadata.
	 *
	 * @param world World.
	 * @param vec Vector3i position.
	 * @return int metadata.
	 */
	public static int getBlockMetadata(World world, Vector3<Integer> vec) {
		return getBlockMetadata(world, createBlockPos(vec));
	}

	/**
	 * Gets block metadata.
	 *
	 * @param world World.
	 * @param blockPos Block position.
	 * @return int metadata.
	 */
	public static int getBlockMetadata(World world, BlockPos blockPos) {
		IBlockState blockState = getBlock(world, blockPos);
		return blockState.getBlock().getMetaFromState(blockState);
	}

	/**
	 * Gets the TileEntity of type 'T' if successful else may return 'NULL'.
	 *
	 * @param world World object to reference.
	 * @param x     x-pos.
	 * @param y     y-pos.
	 * @param z     z-pos.
	 * @param clazz Class instance to check.
	 * @param <T>   Type of TileEntity to (potentially) return.
	 * @return TileEntity of type 'T' if successful else may return 'NULL'.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends TileEntity> T getTileEntity(World world, int x, int y, int z, Class<T> clazz) {
		if (world == null || clazz == null) return null;

		final TileEntity te = world.getTileEntity(createBlockPos(x, y, z));
		return clazz.isInstance(te) ? (T) te : null;
	}

	/**
	 * Gets the TileEntity of type 'T' if successful else may return 'NULL'.
	 *
	 * @param world World object to reference.
	 * @param vec   World vector3i to reference.
	 * @param clazz Class instance to check.
	 * @param <T>   Type of TileEntity to (potentially) return.
	 * @return TileEntity of type 'T' if successful else may return 'NULL'.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends TileEntity> T getTileEntity(World world, Vector3<Integer> vec, Class<T> clazz) {
		if (world == null || vec == null || clazz == null) return null;

		final TileEntity te = world.getTileEntity(createBlockPos(vec.x, vec.y, vec.z));
		return clazz.isInstance(te) ? (T) te : null;
	}

	/**
	 * Gets the TileEntity of type 'T' if successful else may return 'NULL'.
	 *
	 * @param world World object to reference.
	 * @param blockPos BlockPos.
	 * @param clazz Class instance to check.
	 * @param <T>   Type of TileEntity to (potentially) return.
	 * @return TileEntity of type 'T' if successful else may return 'NULL'.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends TileEntity> T getTileEntity(World world, BlockPos blockPos, Class<T> clazz) {
		if (world == null || blockPos == null || clazz == null) return null;

		final TileEntity te = world.getTileEntity(blockPos);
		return clazz.isInstance(te) ? (T) te : null;
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
		return world.canMineBlockBody(player, createBlockPos(x, y, z));
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
		return player.getEntityWorld().canMineBlockBody(player, createBlockPos(x, y, z));
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
	 * Checks if given player can mine/break block at player-world coordinate.
	 *
	 * @param player player to reference.
	 * @param blockPos BlockPos.
	 * @return true if can be mined by provided player, else returns false.
	 */
	public static boolean canMineBlock(EntityPlayer player, BlockPos blockPos) {
		return player.getEntityWorld().canMineBlockBody(player, blockPos);
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
	 * Gets block material from block at world coordinate.
	 *
	 * @param world World.
	 * @param blockPos BlockPos.
	 * @return Material.
	 */
	public static Material getBlockMaterial(World world, BlockPos blockPos) {
		return getBlock(world, blockPos).getMaterial();
	}

	/**
	 * Gets block material from provided block.
	 *
	 * @param block block to reference.
	 * @return block material.
	 */
	public static Material getBlockMaterial(IBlockState block) {
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
		world.destroyBlock(createBlockPos(x, y, z), drop);
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
	 * Destroys block at world coordinate.
	 *
	 * @param world World.
	 * @param blockPos BlockPos.
	 * @param drop boolean.
	 */
	public static void destroyBlock(World world, BlockPos blockPos, boolean drop) {
		world.destroyBlock(blockPos, drop);
	}

	/**
	 * Destroys block at world coordinate.
	 *
	 * @param world World.
	 * @param blockPos BlockPos.
	 */
	public static void destroyBlock(World world, BlockPos blockPos) {
		world.destroyBlock(blockPos, true);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 * @param block block to set.
	 * @param notify notification level, default should be '3' to notify and update client.
	 */
	public static void setBlock(World world, int x, int y, int z, IBlockState block, int notify) {
		boolean flag = false;
		IBlockState checkBlock = getBlock(world, x, y, z);
		// int tempData = getBlockMetadata(world, x, y, z);
		if (checkBlock == block) flag = true;
		if (!flag) world.setBlockState(createBlockPos(x, y, z), block, notify);
		else HCoreLibMain.logHelper.warn("Couldn't place block:", getUnlocalized(block.getBlock()), "into world at", x, y, z);
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
	public static void setBlock(World world, int x, int y, int z, IBlockState block) {
		setBlock(world, x, y, z, block, 3);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @param block block to set.
	 * @param notify notification level, default should be '3' to notify and update client.
	 */
	public static void setBlock(World world, Vector3<Integer> vec, IBlockState block, int notify) {
		setBlock(world, vec.x, vec.y, vec.z, block, notify);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 * @param block block to set.
	 */
	public static void setBlock(World world, Vector3<Integer> vec, IBlockState block) {
		setBlock(world, vec.x, vec.y, vec.z, block);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world World.
	 * @param blockPos BlockPos.
	 * @param block IBlockState.
	 * @param notify int.
	 */
	public static void setBlock(World world, BlockPos blockPos, IBlockState block, int notify) {
		world.setBlockState(blockPos, block, notify);
	}

	/**
	 * Attempts to set block at world coordinate.
	 *
	 * @param world World.
	 * @param blockPos BlockPos.
	 * @param block IBlockState.
	 */
	public static void setBlock(World world, BlockPos blockPos, IBlockState block) {
		setBlock(world, blockPos, block, 3);
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
		world.setBlockToAir(createBlockPos(x, y, z));
	}

	/**
	 * Sets block to air at given world coordinate.
	 *
	 * @param world world object to reference.
	 * @param vec vector3 to check (x, y, z).
	 */
	public static void setBlockToAir(World world, Vector3<Integer> vec) {
		world.setBlockToAir(createBlockPos(vec.x, vec.y, vec.z));
	}

	/**
	 * Sets block to air at given world coordinate.
	 *
	 * @param world World.
	 * @param blockPos BlockPos.
	 */
	public static void setBlockToAir(World world, BlockPos blockPos) {
		world.setBlockToAir(blockPos);
	}

	/**
	 * Gets if block exists.
	 *
	 * @param world World.
	 * @param x int.
	 * @param y int.
	 * @param z int.
	 * @return boolean result.
	 */
	public static boolean blockExists(World world, int x, int y, int z) {
		final Block block = getBlock(world, x, y, z).getBlock();

		return block != Blocks.AIR;
	}

	/**
	 * Gets if block exists.
	 *
	 * @param world World.
	 * @param vec vector3i.
	 * @return boolean result.
	 */
	public static boolean blockExists(World world, Vector3<Integer> vec) {
		return vec != null && blockExists(world, vec.x, vec.y, vec.z);
	}

	/**
	 * Gets if block exists.
	 *
	 * @param world World.
	 * @param blockPos BlockPos.
	 * @return boolean result.
	 */
	public static boolean blockExists(World world, BlockPos blockPos) {
		return blockPos != null && blockExists(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
	}

	/**
	 * Method used to verify block is registered.
	 *
	 * @param id ID of block to check.
	 * @return true if verified, else may return false.
	 */
	@Deprecated
	public static boolean blockListContains(int id) {
		Block block = Block.getBlockById(id);
		return block != null && block != Blocks.AIR;
	}

	/**
	 * Method used to verify block is registered.
	 *
	 * @param block Block to check.
	 * @return true if verified, else may return false.
	 */
	public static boolean blockListContains(IBlockState block) {
		int id = Block.getIdFromBlock(block.getBlock());
		Block b = Block.getBlockById(id);
		return b != null && b != Blocks.AIR;
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

	/**
	 * Marks block for update.
	 *
	 * @param world World.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
     * @param newBlockState IBlockState.
	 */
	public static void markBlockForUpdate(World world, int x, int y, int z, IBlockState newBlockState) {
		markBlockForUpdate(world, createBlockPos(x, y, z), newBlockState);
	}

	/**
	 * Marks block for update.
	 *
	 * @param world World.
	 * @param vec Block position.
     * @param newBlockState IBlockState.
	 */
	public static void markBlockForUpdate(World world, Vector3<Integer> vec, IBlockState newBlockState) {
		markBlockForUpdate(world, createBlockPos(vec), newBlockState);
	}

	/**
	 * Marks block for update.
	 *
	 * @param world World.
	 * @param blockPos Block position.
     * @param newBlockState IBlockState.
	 */
	public static void markBlockForUpdate(World world, BlockPos blockPos, IBlockState newBlockState) {
		IBlockState oldBlockState = getBlock(world, blockPos);
		markBlockForUpdate(world, blockPos, oldBlockState, newBlockState);
	}

	/**
	 * Marks block for update.
	 *
	 * @param world World.
	 * @param blockPos Block position.
	 * @param oldBlockState IBlockState.
     * @param oldBlockState IBlockState.
	 */
	private static void markBlockForUpdate(World world, BlockPos blockPos, IBlockState oldBlockState, IBlockState newBlockState) {
		// world.notifyBlockOfStateChange(blockPos, oldBlockState.getBlock());
		world.notifyBlockUpdate(blockPos, oldBlockState, newBlockState, 3);
	}

	/**
	 * Notifies neighbors of block update.
	 *
	 * @param world World.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 */
	public static void notifyNeighborsOfBlockUpdate(World world, int x, int y, int z) {
		notifyNeighborsOfBlockUpdate(world, createBlockPos(x, y, z));
	}

	/**
	 * Notifies neighbors of block update.
	 *
	 * @param world World.
	 * @param vec Block position.
	 */
	public static void notifyNeighborsOfBlockUpdate(World world, Vector3<Integer> vec) {
		notifyNeighborsOfBlockUpdate(world, createBlockPos(vec));
	}

	/**
	 * Notifies neighbors of block update.
	 *
	 * @param world World.
	 * @param blockPos Block position.
	 */
	public static void notifyNeighborsOfBlockUpdate(World world, BlockPos blockPos) {
		IBlockState blockState = getBlock(world, blockPos);
		notifyNeighborsOfBlockUpdate(world, blockPos, blockState);
	}

	/**
	 * Notifies neighbors of block update.
	 *
	 * @param world World.
	 * @param blockPos Block position.
	 * @param blockState IBlockState.
	 */
	private static void notifyNeighborsOfBlockUpdate(World world, BlockPos blockPos, IBlockState blockState) {
		world.notifyNeighborsOfStateChange(blockPos, blockState.getBlock(), true);
	}

	/**
	 * Marks block for update and notifies neighbors in a single function call.
	 *
	 * @param world World.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @param z z-coordinate.
	 */
	public static void updateAndNotifyNeighborsOfBlockUpdate(World world, int x, int y, int z) {
		updateAndNotifyNeighborsOfBlockUpdate(world, createBlockPos(x, y, z));
	}

	/**
	 * Marks block for update and notifies neighbors in a single function call.
	 *
	 * @param world World.
	 * @param vec Block position.
	 */
	public static void updateAndNotifyNeighborsOfBlockUpdate(World world, Vector3<Integer> vec) {
		updateAndNotifyNeighborsOfBlockUpdate(world, createBlockPos(vec));
	}

	/**
	 * Marks block for update and notifies neighbors in a single function call.
	 *
	 * @param world World.
	 * @param blockPos Block position.
	 */
	public static void updateAndNotifyNeighborsOfBlockUpdate(World world, BlockPos blockPos) {
		IBlockState blockState = getBlock(world, blockPos);
		markBlockForUpdate(world, blockPos, blockState);
		notifyNeighborsOfBlockUpdate(world, blockPos, blockState);
	}

	/**
	 * Creates a BlockPos.
	 *
	 * @param x int.
	 * @param y int.
	 * @param z int.
	 * @return BlockPos.
	 */
	public static BlockPos createBlockPos(int x, int y, int z) {
		return new BlockPos(x, y, z);
	}

	/**
	 * Creates a BlockPos.
	 *
	 * @param vec vector3i.
	 * @return BlockPos.
	 */
	public static BlockPos createBlockPos(Vector3<Integer> vec) {
		return new BlockPos(vec.x, vec.y, vec.z);
	}

	@Override
	public String toString() {
		return "Trololololol :)";
	}

}
