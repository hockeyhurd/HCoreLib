package com.hockeyhurd.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import com.hockeyhurd.api.math.Vector3;
import com.hockeyhurd.mod.HCoreLibMain;

public class BlockHelper {

	private World world;
	private EntityPlayer player;
	
	public BlockHelper(World world, EntityPlayer player) {
		this.world = world;
		this.player = player;
	}
	
	public BlockHelper(World world) {
		this(world, null);
	}
	
	// Only use this constructor if there is no need with world or player interaction! i.e. block parameters.
	@Deprecated
	public BlockHelper() {
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}
	
	public void setWorldPlayer(World world, EntityPlayer player) {
		setWorld(world);
		setPlayer(player);
	}
	
	public Block getBlock(int id) {
		// return id > 0 && id < Block.blockRegistry. ? Block.blocksList[id] : null;
		return (Block) Block.blockRegistry.getObjectById(id);
	}
	
	public Block getBlock(int x, int y, int z) {
		return world.getBlock(x, y, z);
	}
	
	public Block getBlock(Vector3<Integer> vec) {
		return getBlock(vec.x, vec.y, vec.z);
	}
	
	public Block getBlockFromID(int id) {
		return (blockListContains(id) ? (Block) Block.blockRegistry.getObjectById(id) : null);
	}
	
	public boolean canMineBlock(int x, int y, int z) {
		return canMineBlock(this.player, x, y, z);
	}
	
	public boolean canMineBlock(Vector3<Integer> vec) {
		return canMineBlock(vec.x, vec.y, vec.z);
	}
	
	public boolean canMineBlock(EntityPlayer player, Vector3<Integer> vec) {
		return canMineBlock(player, vec.x, vec.y, vec.z);
	}
	
	public boolean canMineBlock(EntityPlayer player, int x, int y, int z) {
		return world.canMineBlock(player, x, y, z);
	}
	
	public boolean blockExists(int x, int y, int z) {
		return world.blockExists(x, y, z);
	}
	
	public boolean blockExists(Vector3<Integer> vec) {
		return blockExists(vec.x, vec.y, vec.z);
	}
	
	public int getBlockMetaData(int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
	
	public int getBlockMetaData(Vector3<Integer> vec) {
		return getBlockMetaData(vec.x, vec.y, vec.z);
	}
	
	// Returns the block's material
	public Material getBlockMaterial(int x, int y, int z) {
		return world.getBlock(x, y, z).getMaterial();
	}
	
	public Material getBlockMaterial(Vector3<Integer> vec) {
		return getBlockMaterial(vec.x, vec.y, vec.z);
	}
	
	// Set to depreciated until further tested, however is likely it works.
	@Deprecated
	public Material getBlockMaterial(Block block) {
		return block.getMaterial();
	}
	
	public String getLocalized(Block block) {
		return block != null ? block.getLocalizedName() : "This is not a block!";
	}
	
	public String getUnlocalized(Block block) {
		return block != null ? block.getUnlocalizedName() : "This is not a block!";
	}
	
	public boolean blockListContains(int id) {
		Block block = Block.getBlockById(id);
		return block != null && block != Blocks.air ? true : false;
	}
	
	public boolean blockListContains(Block block) {
		int id = Block.getIdFromBlock(block);
		Block b = Block.getBlockById(id); 
		return b != null && b != Blocks.air ? true : false; 
	}
	
	public void destroyBlock(int x, int y, int z) {
		destroyBlock(x, y, z, true);
	}
	
	public void destroyBlock(int x, int y, int z, boolean drop) {
		world.func_147480_a(x, y, z, drop);
	}
	
	public void destroyBlock(Vector3<Integer> vec, boolean drop) {
		destroyBlock(vec.x, vec.y, vec.z, drop);
	}
	
	public void destroyBlock(Vector3 vec) {
		destroyBlock(vec, true);
	}
	
	public void setBlock(int x, int y, int z, Block block, int metaData) {
		boolean flag = false;
		Block checkBlock = getBlock(x, y, z);
		int tempData = getBlockMetaData(x, y, z);
		if (checkBlock == block && tempData == metaData) flag = true;
		if (!flag) world.setBlock(x, y, z, block, metaData, 3);
		else HCoreLibMain.lh.warn("Couldn't place block:", getUnlocalized(block), "into world at", x, y, z, "with metadata:", metaData);
	}
	
	public void setBlock(int x, int y, int z, Block block) {
		setBlock(x, y, z, block, 0);
	}
	
	public void setBlock(Vector3<Integer> vec, Block block, int metaData) {
		setBlock(vec.x, vec.y, vec.z, block, metaData);
	}
	
	public void setBlock(Vector3<Integer> vec, Block block) {
		setBlock(vec, block, 0);
	}
	
	public void setBlockToAir(int x, int y, int z) {
		world.setBlockToAir(x, y, z);
	}
	
	public void setBlockToAir(Vector3<Integer> vec) {
		setBlockToAir(vec.x, vec.y, vec.z);
	}
}
