/* Note:
 * 	This class has nothing to do and/or
 * 	is associated with the mod WAILA.
 *  Just a simple class I made to help me
 *  figure out What Am I Looking At? :)
 */

package com.hockeyhurd.hcorelib.api.util;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Partially re-written and should be slightly more modular.
 * Difference between previous is more checks should be placed 
 * when making method calls rather than this class handling that
 * automatically for you. 
 */
public class Waila {

	private ItemStack stack;
	private World world;
	private EntityPlayer player;
	private IBlockState blockPlace;
	private int metaData;

	private List<Block> blockBlackList;
	private Material[] matWhiteList;
	private EnumFacing sideHit;
	private int offset;
	private Vector3<Integer> vec;
	private boolean returnState = false;

	/**
	 * Default Constructor call, adds relavent information that will be of primary use in provided
	 * methods.
	 * @param stack stack currently in use/hand.
	 * @param world world object.
	 * @param player player object
	 * @param blockPlace = block to place NOTE: set to null if not placing anything (IMPORTANT!).
	 * @param metaData = metaData value, just set to '0' if not using please.
	 */
	public Waila(ItemStack stack, World world, EntityPlayer player, IBlockState blockPlace, int metaData) {
		this.stack = stack;
		this.world = world;
		this.player = player;
		this.blockPlace = blockPlace;
		this.metaData = metaData;

		blockBlackList = new ArrayList<Block>();
		addBlockBlackList();
		matWhiteList = new Material[] {};
	}

	private void addBlockBlackList() {
		// add(ExtraTools.glowTorch);
		add(Blocks.torch);
		add(Blocks.rail);
		add(Blocks.activator_rail);
		add(Blocks.detector_rail);
		add(Blocks.golden_rail);
		add(Blocks.bedrock);
		add(Blocks.mob_spawner);

		// TODO: Find solution to determine if a block has a hardness of -1 (unbreakable).
		/*
		 * Iterator iter = Block.blockRegistry.iterator(); while (iter.hasNext()) { Block block = (Block) iter.next(); if (block != null && !blockBlackList.contains(block)) add(block); }
		 */

	}

	/**
	 * Allows for adding additional blocks to black list through class call.
	 * @param block block to add to black list.
	 */
	public void add(Block block) {
		blockBlackList.add(block);
	}

	/**
	 * Sets material whitelist to this instance of class.
	 * @param mats
	 */
	public void setMatWhiteList(Material[] mats) {
		this.matWhiteList = mats;
	}

	/**
	 * Sets the offset. NOTE: If a instanceof hammer or such, set this to '1'
	 * so that we can offset it by and mine 3x3 area.
	 * @param offset
	 */
	public void setOffset(int offset) {
		// If the setOffsetVal > 0 set this.offSet = offset else, set this.offset = 0, (removing offset to faulty val).
		this.offset = (offset > 0 ? offset : 0);
	}

	/**
	 * Calls finder(boolean handler) method but sets it by default to true!
	 */
	public void finder() {
		finder(true);
	}

	/**
	 * Whether to use the provided place block handler or not.
	 * @param handler boolean flag
	 */
	public void finder(boolean handler) {
		float f = 1.0F;

		// Get the avgCurrent rotational pitch (left, right)
		float rotPitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;

		// Get the avgCurrent rotational yaw (UP, DOWN)
		float rotYaw = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;

		// Get the avgCurrent position X.
		double posX = player.prevPosX + (player.posX - player.prevPosX) * (double) f;

		// Get the avgCurrent position Y and offset the yCorrd to the
		// player's head level (camera level).
		double posY = (player.prevPosY + (player.posY - player.prevPosY) * (double) f + player.height) - (double) player.height;

		// Get the avgCurrent position Z.
		double posZ = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;

		// Get the vector represented by the combination of the three above
		// world coordinates.
		Vec3d vec3d = new Vec3d(posX, posY, posZ);

		// Get the -rotYaw and represent deltaDegrees about the y-axis;
		// calculate cos('x').
		float f3 = MathHelper.cos(-rotYaw * 0.01745329F - 3.141593F);

		// Get the -rotYaw and represent the deltaDegrees about the y-axis;
		// calculate sin('x').
		float f4 = MathHelper.sin(-rotYaw * 0.01745329F - 3.141593F);

		// Get the -rotPitch and represent the deltaDegrees about the
		// x-axis; calculate cos('y').
		float f5 = -MathHelper.cos(-rotPitch * 0.01745329F);

		// Get the -rotPitch and represent the deltaDegrees about the
		// x-axis; calculate sin('y').
		float f6 = MathHelper.sin(-rotPitch * 0.01745329F);

		// Get absoulute 'x' value calculated via cos('x') and sin('x').
		float f7 = f4 * f5;
		// Represent null deltaZ.
		float f8 = f6;
		// Get the absolute 'y' value calculated via cos('y') and sin('y').
		float f9 = f3 * f5;
		// Get the distance the vector ray should extend to.
		// double d3 = 5000D;
		double d3 = 5000D;

		// Get the above calculations and represent this in a vector3
		// format.
		Vec3d vec3d1 = vec3d.addVector((double) f7 * d3, (double) f8 * d3 + 1, (double) f9 * d3);

			/*
			 * Combine vector rotations and vector absolute world positions and throw it through a vector ray to calculate the direction and block the entity (player) is currently looking at in the given instance.
			 */
		// MovingObjectPosition movingObjectPos = world.func_147447_a(vec3d, vec3d1, false, true, false);
		RayTraceResult rayTraceResult = world.rayTraceBlocks(vec3d, vec3d1, false, true, false);

		// Make sure there is no possibility the entity (player) is not
		// looking at 'null'.
		if (rayTraceResult == null) return;

		// Check if the vector ray intersects with some sort of TILE
		// if (movingObjectPos.typeOfHit == MovingObjectType.TILE) {
		if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {

			// Get the position of the TILE intersected as represented in
			// 3D space.
			BlockPos rayTracePos = rayTraceResult.getBlockPos();
			int xx = rayTracePos.getX();
			int yy = rayTracePos.getY();
			int zz = rayTracePos.getZ();

			// Get the side of which the vector ray intersects with.
			// int sideHit = rayTraceResult.sideHit;
			EnumFacing sideHit = rayTraceResult.sideHit;
			// print("Side: " + sideHit);

			if (handler && stack != null) placeBlockHandler(world, xx, yy, zz, sideHit);
			else {
				setSideHit(sideHit);
				setVector3i(xx, yy, zz, sideHit);
			}
		}
	}

	private void setSideHit(EnumFacing side) {
		this.sideHit = side;
	}

	/**
	 * Get the side hit. NOTE: must make method finder() call
	 * for this to return any relavent information!
	 *
	 * @return int side of impact.
	 */
	public EnumFacing getSideHit() {
		return this.sideHit;
	}

	private void setVector3i(int x, int y, int z, EnumFacing sideHit) {
		this.vec = new Vector3<Integer>(x, y, z);
		this.sideHit = sideHit;
	}

	public Vector3<Integer> getVector3i() {
		return vec;
	}

	private void placeBlockHandler(World world, int xx, int yy, int zz, EnumFacing side) {
		if (blockPlace != null) {
			final int sideHit = side.ordinal();
			if (sideHit == 0) return;
			else if (sideHit == 1) BlockUtils.setBlock(world, xx, yy + this.offset, zz, blockPlace, metaData);
			else if (sideHit == 2) BlockUtils.setBlock(world, xx, yy, zz - this.offset, blockPlace, metaData);
			else if (sideHit == 3) BlockUtils.setBlock(world, xx, yy, zz + this.offset, blockPlace, metaData);
			else if (sideHit == 4) BlockUtils.setBlock(world, xx - this.offset, yy, zz, blockPlace, metaData);
			else if (sideHit == 5) BlockUtils.setBlock(world, xx + this.offset, yy, zz, blockPlace, metaData);
		}
	}

	/** Method used that can mine a 2-Dimensional area based off a given side hit.
	 *
	 * @param x xPos
	 * @param y yPos
	 * @param z zPos
	 */
	private void mineArea(int x, int y, int z, EnumFacing side) {
		/*
		 * sideHit == 0, bottom sideHit == 1, top sideHit == 2, front sideHit == 3, back sideHit == 4, left sideHit == 5, right
		 */
		final int deltaPos = 6;
		final int sideHit = side.ordinal();

		for (int i = -offset; i <= offset; i++) {
			for (int j = -offset; j <= offset; j++) {
				if (sideHit == 0 || sideHit == 1) setBlockAir(x + i, y, z + j, deltaPos, true);
				else if (sideHit == 2 || sideHit == 3) setBlockAir(x + i, y + j, z, deltaPos, true);
				else if (sideHit == 4 || sideHit == 5) setBlockAir(x, y + i, z + j, deltaPos, true);
			}
		}

	}

	/** Setting material to null disregards check for like material blocks.
	 * NOTE: Benefit of using this over BlockHelper's method is the additional
	 * checks for whether the block looking at is allowed to be interacted with.
	 * Example of this might be when "trying" to break bedrock which should clearly
	 * not be allowed
	 * @param x xPos
	 * @param y yPos
	 * @param z zPos
	 * @param deltaPos range of interaction
	 * @param matSp material specific
	 */
	public void setBlockAir(int x, int y, int z, int deltaPos, boolean matSp) {

		// How far should the player be able to 'reach'.
		boolean xCheck = false, yCheck = false, zCheck = false;

		/*
		 * Check the reach distance relative to the player and desired block placement.
		 */
		if ((x - deltaPos) < player.posX && player.posX < (x + deltaPos)) xCheck = true;
		if ((y - deltaPos) < player.posY && player.posY < (y + deltaPos)) yCheck = true;
		if ((z - deltaPos) < player.posZ && player.posZ < (z + deltaPos)) zCheck = true;

		/*
		 * If said block is something and the player can reach the block they are looking at, place the said block.
		 */
		if (xCheck && yCheck && zCheck) {
			if (BlockUtils.blockExists(world, x, y, z) &&
					!blockBlackList.contains(BlockUtils.getBlock(world, x, y, z).getBlock())) {
				// If the block trying to be placed is equal to block at the coordinate, return;

				// Set true for par4 if destroyed block should drop, item-drops.
				// if (!matSp) world.func_147480_a(x, y, z, true);
				if (!matSp) BlockUtils.destroyBlock(world, x, y, z, true);
				else {
					Material currentMat = BlockUtils.getBlockMaterial(world, x, y, z);
					boolean contains = false;

					for (int i = 0; i < matWhiteList.length; i++) {
						if (matWhiteList[i] == currentMat) contains = true;
					}

					if (!contains) return;
					else BlockUtils.destroyBlock(world, x, y, z, true);
				}

				// world.setBlockToAir(x, y, z);
				BlockUtils.setBlockToAir(world, x, y, z);
			}
		}
	}
	
	/**
	 * Iterates through 2-D space to till land. Although yPos should 
	 * not be necessary, please provide for that is how we get the block
	 * looking at in a given location in the world.
	 * @param x xPos
	 * @param y yPos
	 * @param z zPos
	 */
	public void tillLand(int x, int y, int z) {
		// Get all needed block ids'
		Block tilDir = Blocks.farmland;
		Block dirt = Blocks.dirt;
		Block grass = Blocks.grass;

		/*
		 * Scan through blocks on the x and z axis, check if they can be tilled, till the land!
		 */
		for (int xx = x - 1; xx < x + 2; xx++) {
			for (int zz = z - 1; zz < z + 2; zz++) {
				Block currentBlock = BlockUtils.getBlock(world, xx, y, zz).getBlock();

				// Note: Last check below shouldn't be necessary as it should already be tilled! (in theory).
				if (currentBlock == dirt || currentBlock == grass /* || currentBlock == tilledDirtID */) {
					BlockUtils.setBlock(world, xx, y, zz, tilDir.getDefaultState());
				}
			}
		}
	}
	
	private void setResult(boolean result) {
		this.returnState = result;
	}
	
	/**
	 * Whether the result of a previous action used should
	 * return something to the player.
	 * @return
	 */
	public boolean getResult() {
		return this.returnState;
	}

}
