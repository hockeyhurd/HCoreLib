package com.hockeyhurd.hcorelib.api.math;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

/**
 * Helper class for converting common vectors.
 *
 * @author hockeyhurd
 * @version 5/12/2016.
 */
public final class VectorHelper {

	private VectorHelper() {
	}

	/**
	 * Function to get Vector3i from Vec3i.
	 *
	 * @param vec3i Vec3i.
	 * @return Vector3i.
	 */
	public static Vector3<Integer> toVector3i(Vec3i vec3i) {
		return vec3i != null ? new Vector3<Integer>(vec3i.getX(), vec3i.getY(), vec3i.getZ()) : new Vector3<Integer>();
	}

	/**
	 * Function to get Vector3i from BlockPos.
	 *
	 * @param pos BlockPos.
	 * @return Vector3i.
	 */
	public static Vector3<Integer> toVector3i(BlockPos pos) {
		return pos != null ? new Vector3<Integer>(pos.getX(), pos.getY(), pos.getZ()) : null;
	}

	/**
	 * Function to get Vector3i from x, y, z.
	 *
	 * @param x int.
	 * @param y int.
	 * @param z int.
	 * @return Vector3i.
	 */
	public static Vector3<Integer> toVector3i(int x, int y, int z) {
		return new Vector3<Integer>(x, y, z);
	}

	/**
	 * Function to get Vec3i from Vector3i.
	 *
	 * @param vec Vector3i.
	 * @return Vec3i.
	 */
	public static Vec3i toVec3i(Vector3<Integer> vec) {
		return vec != null ? new Vec3i(vec.x, vec.y, vec.z) : Vec3i.NULL_VECTOR;
	}

	/**
	 * Function to get Vec3i from BlockPos.
	 *
	 * @param pos BlockPos.
	 * @return Vec3i.
	 */
	public static Vec3i toVec3i(BlockPos pos) {
		return pos != null ? pos : Vec3i.NULL_VECTOR;
	}

	/**
	 * Function to get Vec3i from x, y, z.
	 *
	 * @param x int.
	 * @param y int.
	 * @param z int.
	 * @return Vec3i.
	 */
	public static Vec3i toVec3i(int x, int y, int z) {
		return new Vec3i(x, y, z);
	}

	/**
	 * Function to get BlockPos from Vector3i.
	 *
	 * @param vec Vector3i.
	 * @return BlockPos.
	 */
	public static BlockPos toBlockPos(Vector3<Integer> vec) {
		return vec != null ? new BlockPos(vec.x, vec.y, vec.z) : new BlockPos(0, 0, 0);
	}

	/**
	 * Function to get BlockPos from Vec3i.
	 *
	 * @param vec3i Vec3i.
	 * @return BlockPos.
	 */
	public static BlockPos toBlockPos(Vec3i vec3i) {
		return vec3i != null ? new BlockPos(vec3i) : new BlockPos(0, 0, 0);
	}

	/**
	 * Function to get BlockPos from x, y, z.
	 *
	 * @param x int.
	 * @param y int.
	 * @param z int.
	 * @return BlockPos.
	 */
	public static BlockPos toBlockPos(int x, int y, int z) {
		return new BlockPos(x, y, z);
	}

}
