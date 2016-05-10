package com.hockeyhurd.hcorelib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * @author hockeyhurd
 * @version 5/10/2016.
 */
public class MovingObjectPosition {
	/** What type of ray trace hit was this? 0 = block, 1 = entity */
	public MovingObjectPosition.MovingObjectType typeOfHit;
	/** x coordinate of the block ray traced against */
	public int blockX;
	/** y coordinate of the block ray traced against */
	public int blockY;
	/** z coordinate of the block ray traced against */
	public int blockZ;
	/**
	 * Which side was hit. If its -1 then it went the full length of the ray trace. Bottom = 0, Top = 1, East = 2, West
	 * = 3, North = 4, South = 5.
	 */
	public int sideHit;
	/** The vector position of the hit */
	public Vec3d hitVec;
	/** The hit entity */
	public Entity entityHit;
	private static final String __OBFID = "CL_00000610";

	/** Used to determine what sub-segment is hit */
	public int subHit = -1;

	/** Used to add extra hit info */
	public Object hitInfo = null;

	public MovingObjectPosition(int blockX, int blockY, int blockZ, int sideHit, Vec3d vec) {
		this(blockX, blockY, blockZ, sideHit, vec, true);
	}

	public MovingObjectPosition(int blockX, int blockY, int blockZ, int sideHit, Vec3d vec, boolean typeOfHit) {
		this.typeOfHit = typeOfHit ? MovingObjectPosition.MovingObjectType.BLOCK : MovingObjectPosition.MovingObjectType.MISS;
		this.blockX = blockX;
		this.blockY = blockY;
		this.blockZ = blockZ;
		this.sideHit = sideHit;
		this.hitVec = new Vec3d(vec.xCoord, vec.yCoord, vec.zCoord);
	}

	public MovingObjectPosition(Entity entity) {
		this(entity, new Vec3d(entity.posX, entity.posY, entity.posZ));
	}

	public MovingObjectPosition(Entity entity, Vec3d vec) {
		this.typeOfHit = MovingObjectPosition.MovingObjectType.ENTITY;
		this.entityHit = entity;
		this.hitVec = vec;
	}

	public static MovingObjectPosition func_147447_a(World world, Vec3d start, Vec3d end, boolean p_147447_3_, boolean p_147447_4_, boolean p_147447_5_) {
		if (!Double.isNaN(start.xCoord) && !Double.isNaN(start.yCoord) && !Double.isNaN(start.zCoord)) {
			if (!Double.isNaN(end.xCoord) && !Double.isNaN(end.yCoord) && !Double.isNaN(end.zCoord)) {
				int i = MathHelper.floor_double(end.xCoord);
				int j = MathHelper.floor_double(end.yCoord);
				int k = MathHelper.floor_double(end.zCoord);
				int l = MathHelper.floor_double(start.xCoord);
				int i1 = MathHelper.floor_double(start.yCoord);
				int j1 = MathHelper.floor_double(start.zCoord);
				IBlockState block = BlockUtils.getBlock(world, l, i1, j1);
				int k1 = block.getBlock().getMetaFromState(block);

				if ((!p_147447_4_ || block.getBlock().getCollisionBoundingBox(block, world,
						new BlockPos(l, i1, j1)) != null) && block.getBlock().canCollideCheck(block, p_147447_3_)) {
					MovingObjectPosition movingobjectposition = block.getBlock().collisionRayTrace(block, world, new BlockPos(l, i1, j1), start, end);

					if (movingobjectposition != null) {
						return movingobjectposition;
					}
				}

				MovingObjectPosition movingobjectposition2 = null;
				k1 = 200;

				while (k1-- >= 0) {
					if (Double.isNaN(start.xCoord) || Double.isNaN(start.yCoord) || Double.isNaN(start.zCoord)) {
						return null;
					}

					if (l == i && i1 == j && j1 == k) {
						return p_147447_5_ ? movingobjectposition2 : null;
					}

					boolean flag6 = true;
					boolean flag3 = true;
					boolean flag4 = true;
					double d0 = 999.0D;
					double d1 = 999.0D;
					double d2 = 999.0D;

					if (i > l) {
						d0 = (double) l + 1.0D;
					}
					else if (i < l) {
						d0 = (double) l + 0.0D;
					}
					else {
						flag6 = false;
					}

					if (j > i1) {
						d1 = (double) i1 + 1.0D;
					}
					else if (j < i1) {
						d1 = (double) i1 + 0.0D;
					}
					else {
						flag3 = false;
					}

					if (k > j1) {
						d2 = (double) j1 + 1.0D;
					}
					else if (k < j1) {
						d2 = (double) j1 + 0.0D;
					}
					else {
						flag4 = false;
					}

					double d3 = 999.0D;
					double d4 = 999.0D;
					double d5 = 999.0D;
					double d6 = end.xCoord - start.xCoord;
					double d7 = end.yCoord - start.yCoord;
					double d8 = end.zCoord - start.zCoord;

					if (flag6) {
						d3 = (d0 - start.xCoord) / d6;
					}

					if (flag3) {
						d4 = (d1 - start.yCoord) / d7;
					}

					if (flag4) {
						d5 = (d2 - start.zCoord) / d8;
					}

					boolean flag5 = false;
					byte b0;

					if (d3 < d4 && d3 < d5) {
						if (i > l) {
							b0 = 4;
						}
						else {
							b0 = 5;
						}

						start.xCoord = d0;
						start.yCoord += d7 * d3;
						start.zCoord += d8 * d3;
					}
					else if (d4 < d5) {
						if (j > i1) {
							b0 = 0;
						}
						else {
							b0 = 1;
						}

						start.xCoord += d6 * d4;
						start.yCoord = d1;
						start.zCoord += d8 * d4;
					}
					else {
						if (k > j1) {
							b0 = 2;
						}
						else {
							b0 = 3;
						}

						start.xCoord += d6 * d5;
						start.yCoord += d7 * d5;
						start.zCoord = d2;
					}

					Vec3 vec32 = Vec3.createVectorHelper(start.xCoord, start.yCoord, start.zCoord);
					l = (int) (vec32.xCoord = (double) MathHelper.floor_double(start.xCoord));

					if (b0 == 5) {
						--l;
						++vec32.xCoord;
					}

					i1 = (int) (vec32.yCoord = (double) MathHelper.floor_double(start.yCoord));

					if (b0 == 1) {
						--i1;
						++vec32.yCoord;
					}

					j1 = (int) (vec32.zCoord = (double) MathHelper.floor_double(start.zCoord));

					if (b0 == 3) {
						--j1;
						++vec32.zCoord;
					}

					Block block1 = this.getBlock(l, i1, j1);
					int l1 = this.getBlockMetadata(l, i1, j1);

					if (!p_147447_4_ || block1.getCollisionBoundingBoxFromPool(this, l, i1, j1) != null) {
						if (block1.canCollideCheck(l1, p_147447_3_)) {
							MovingObjectPosition movingobjectposition1 = block1.collisionRayTrace(this, l, i1, j1, start, end);

							if (movingobjectposition1 != null) {
								return movingobjectposition1;
							}
						}
						else {
							movingobjectposition2 = new MovingObjectPosition(l, i1, j1, b0, start, false);
						}
					}
				}

				return p_147447_5_ ? movingobjectposition2 : null;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "HitResult{type=" + this.typeOfHit + ", x=" + this.blockX + ", y=" + this.blockY + ", z=" + this.blockZ + ", f=" + this.sideHit
				+ ", pos=" + this.hitVec + ", entity=" + this.entityHit + '}';
	}

	public static enum MovingObjectType {
		MISS,
		BLOCK,
		ENTITY;

		private static final String __OBFID = "CL_00000611";
	}
}
