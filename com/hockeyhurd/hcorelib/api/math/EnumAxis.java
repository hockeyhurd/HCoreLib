package com.hockeyhurd.hcorelib.api.math;

/**
 * Enumeration for handling axis with a vec3 format.
 *
 * @author hockeyhurd
 * @version 6/29/2016.
 */
public enum EnumAxis {
	X(new Vector3<Integer>(1, 0, 0)), Y(new Vector3<Integer>(0, 1, 0)), Z(new Vector3<Integer>(0, 0, 1));

	final Vector3<Integer> vec;

	EnumAxis(Vector3<Integer> vec) {
		this.vec = vec;
	}

	public boolean isEqual(int x, int y, int z) {
		return isEqual(new Vector3<Integer>(x, y, z));
	}

	/**
	 * Checks by vec3i whether it is equal to this axis' vec3i.
	 *
	 * @param vec Vector3i to compare.
	 * @return boolean result.
	 */
	public boolean isEqual(Vector3<Integer> vec) {
		if (vec == null) return false;

		if (vec.x > 1 || vec.x <= -1) vec.x = 1;
		if (vec.y > 1 || vec.y <= -1) vec.y = 1;
		if (vec.z > 1 || vec.z <= -1) vec.z = 1;

		return this.vec.equals(vec);
	}

	/**
	 * Checks by vec3i whether it is equal to this axis' vec3i.
	 *
	 * @param axis EnumAxis.
	 * @param vec Vector3i to compare.
	 * @return boolean result.
	 */
	public static boolean isEqual(EnumAxis axis, Vector3<Integer> vec) {
		return vec != null && axis.isEqual(vec);
	}

	/**
	 * Compares input to vec3i axis.
	 *
	 * @param axis EnumAxis.
	 * @param x int.
	 * @param y int.
	 * @param z int.
	 * @return boolean result.
	 */
	public static boolean isEqual(EnumAxis axis, int x, int y, int z) {
		return axis.isEqual(x, y, z);
	}

	/**
	 * Gets a copy of the vector3 represented axis.
	 * 
	 * @return Vector3i.
	 */
	public Vector3<Integer> getVector3() {
		return vec.copy();
	}

}
