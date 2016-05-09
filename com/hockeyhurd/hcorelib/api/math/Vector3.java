package com.hockeyhurd.hcorelib.api.math;

import net.minecraft.util.EnumFacing;

/**
 * Class used to store world co-ordinate data by specified number.
 * 
 * @author hockeyhurd
 * @version May 21, 2015
 */
public class Vector3<N> {

	public N x, y, z;
	
	/** Identity function used for setting a vector to its 'zero'. */
	public static final Vector3 zero = new Vector3();

	/** (0, -1, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector3 DOWN = new Vector3(0, -1, 0);

	/** (0, 1, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector3 UP = new Vector3(0, 1, 0);

	/** (1, 0, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector3 EAST = new Vector3(1, 0, 0);

	/** (-1, 0, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector3 west = new Vector3(-1, 0, 0);

	/** (0, 0, -1) */
	@SuppressWarnings("unchecked")
	public static final Vector3 NORTH = new Vector3(0, 0, -1);

	/** (0, 0, 1) */
	@SuppressWarnings("unchecked")
	public static final Vector3 SOUTH = new Vector3(0, 0, 1);

	public static final Vector3[] VALID_DIRECTIONS = new Vector3[] {
			DOWN, UP, NORTH, SOUTH, west, EAST
	};

	/**
	 * Constructor creates new instance of this class with default 
	 * co-ordinate value of (0, 0, 0).
	 */
	@SuppressWarnings("unchecked")
	public Vector3() {
		this((N) (Number) 0, (N) (Number) 0, (N) (Number) 0);
	}
	
	/**
	 * Most generalized constructor creates new instance of this class
	 * through setting to (x, y, z).
	 * 
	 * @param x = xPos
	 * @param y = yPos
	 * @param z = zPos
	 */
	public Vector3(N x, N y, N z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Adds current vector from referenced vector.
	 * 
	 * @param vec vector to add from this.
	 * @return added vector.
	 */
	public Vector3<N> add(Vector3<N> vec) {
		float xx = ((Number)this.x).floatValue() + ((Number) vec.x).floatValue();
		float yy = ((Number)this.y).floatValue() + ((Number) vec.y).floatValue();
		float zz = ((Number)this.z).floatValue() + ((Number) vec.z).floatValue();
		
		this.x = (N) (Number) xx;
		this.y = (N) (Number) yy;
		this.z = (N) (Number) zz;
		
		return this;
	}
	
	/**
	 * Subtracts current vector from referenced vector.
	 * 
	 * @param vec vector to subtract from this.
	 * @return subtracted vector.
	 */
	public Vector3<N> subtract(Vector3<N> vec) {
		float xx = ((Number)this.x).floatValue() - ((Number) vec.x).floatValue();
		float yy = ((Number)this.y).floatValue() - ((Number) vec.y).floatValue();
		float zz = ((Number)this.z).floatValue() - ((Number) vec.z).floatValue();
		
		this.x = (N) (Number) xx;
		this.y = (N) (Number) yy;
		this.z = (N) (Number) zz;
		
		return this;
	}
	
	/**
	 * Gets the difference net difference of each individual element.
	 * 
	 * @param other point '2'.
	 * @return differenced vectors.
	 */
	public Vector3<N> getDifference(Vector3<N> other) {
		float xx0 = toNumber(this.x).floatValue();
		float yy0 = toNumber(this.y).floatValue();
		float zz0 = toNumber(this.z).floatValue();
		
		float xx1 = toNumber(other.x).floatValue();
		float yy1 = toNumber(other.y).floatValue();
		float zz1 = toNumber(other.z).floatValue();
		
		float xTot = xx0 - xx1;
		float yTot = yy0 - yy1;
		float zTot = zz0 - zz1;
		
		return new Vector3<N>((N) (Number) xTot, (N) (Number) yTot, (N) (Number) zTot);
	}
	
	/**
	 * Gets the net difference between current vector<sub>1</sub> (point).
	 * 
	 * @param vec vector<sub>2</sub>
	 * @return difference as a double.
	 */
	public double getNetDifference(Vector3<N> vec) {
		Vector3<Double> dif = getDifference(vec).getVector3d();
		return Math.sqrt((dif.x * dif.x) + (dif.y * dif.y) + (dif.z * dif.z));
	}

	/**
	 * Calculates linear interpolation between two vectors.
	 *
	 * @param v0
	 * @param v1
	 * @return linear interpolation between two vectors.
	 */
	public static Vector3<Double> lerp(Vector3<Double> v0, Vector3<Double> v1) {
		return lerp(v0, v1, 0.5d);
	}

	/**
	 * Calculates linear interpolation between two vectors.
	 *
	 * @param v0
	 * @param v1
	 * @param weight weighting between two vectors.
	 * @return linear interpolation between two vectors.
	 */
	public static Vector3<Double> lerp(Vector3<Double> v0, Vector3<Double> v1, double weight) {
		Vector3<Double> ret = new Vector3<Double>();
		ret.x = Mathd.lerp(v0.x, v1.x, weight);
		ret.y = Mathd.lerp(v0.y, v1.y, weight);
		ret.z = Mathd.lerp(v0.z, v1.z, weight);

		return ret;
	}

	/**
	 * Gets forge direction from referenced vector3<int>.
	 *
	 * @param vec vector3<int> to reference.
	 * @return Appropriated forge direction, else may return EnumFacing.UNKNOWN.
	 */
	public static EnumFacing getDirection(Vector3<Integer> vec) {
		if (vec.x == 0 && vec.y <= -1 && vec.z == 0) return EnumFacing.VALUES[0];
		else if (vec.x == 0 && vec.y >= 1 && vec.z == 0) return EnumFacing.VALUES[1];
		else if (vec.x <= -1 && vec.y == 0 && vec.z == 0) return EnumFacing.VALUES[2];
		else if (vec.x >= 1 && vec.y == 0 && vec.z == 0) return EnumFacing.VALUES[3];
		else if (vec.x == 0 && vec.y == 0 && vec.z <= -1) return EnumFacing.VALUES[4];
		// else if (vec.x == 0 && vec.y == 0 && vec.z >= 1) return EnumFacing.VALUES[5];
		else return EnumFacing.VALUES[5];
	}

	/**
	 * Rotates vector3 around x axis.
	 *
	 * @param vec vector3 to rotate.
	 * @param angle angle (in degrees) to rotate by.
	 * @return rotated vector3.
	 */
	public static Vector3<Float> rotateAroundXAxis(Vector3<Float> vec, float angle) {
		final float radians = (float) Mathd.toRadians(angle);
		final float cos = (float) Mathd.cos(radians);
		final float sin = (float) Mathd.sin(radians);

		final float y = vec.y * cos + vec.z * sin;
		final float z = vec.z * cos - vec.y * sin;

		vec.y = y;
		vec.z = z;

		return vec;
	}

	/**
	 * Rotates vector3 around x axis.
	 *
	 * @param vec vector3 to rotate.
	 * @param angle angle (in degrees) to rotate by.
	 * @return rotated vector3.
	 */
	public static Vector3<Double> rotateAroundXAxis(Vector3<Double> vec, double angle) {
		final double radians = Mathd.toRadians(angle);
		final double cos = Mathd.cos(radians);
		final double sin = Mathd.sin(radians);

		final double y = vec.y * cos + vec.z * sin;
		final double z = vec.z * cos - vec.y * sin;

		vec.y = y;
		vec.z = z;

		return vec;
	}

	/**
	 * Rotates vector3 around y axis.
	 *
	 * @param vec vector3 to rotate.
	 * @param angle angle (in degrees) to rotate by.
	 * @return rotated vector3.
	 */
	public static Vector3<Float> rotateAroundYAxis(Vector3<Float> vec, float angle) {
		final float radians = (float) Mathd.toRadians(angle);
		final float cos = (float) Mathd.cos(radians);
		final float sin = (float) Mathd.sin(radians);

		final float x = vec.x * cos + vec.z * sin;
		final float z = vec.z * cos - vec.x * sin;

		vec.x = x;
		vec.z = z;

		return vec;
	}

	/**
	 * Rotates vector3 around y axis.
	 *
	 * @param vec vector3 to rotate.
	 * @param angle angle (in degrees) to rotate by.
	 * @return rotated vector3.
	 */
	public static Vector3<Double> rotateAroundYAxis(Vector3<Double> vec, double angle) {
		final double radians = Mathd.toRadians(angle);
		final double cos = Mathd.cos(radians);
		final double sin = Mathd.sin(radians);

		final double x = vec.x * cos + vec.z * sin;
		final double z = vec.z * cos - vec.x * sin;

		vec.x = x;
		vec.z = z;

		return vec;
	}

	/**
	 * Rotates vector3 around z axis.
	 *
	 * @param vec vector3 to rotate.
	 * @param angle angle (in degrees) to rotate by.
	 * @return rotated vector3.
	 */
	public static Vector3<Float> rotateAroundZAxis(Vector3<Float> vec, float angle) {
		final float radians = (float) Mathd.toRadians(angle);
		final float cos = (float) Mathd.cos(radians);
		final float sin = (float) Mathd.sin(radians);

		final float x = vec.x * cos + vec.y * sin;
		final float y = vec.y * cos - vec.x * sin;

		vec.x = x;
		vec.y = y;

		return vec;
	}

	/**
	 * Rotates vector3 around z axis.
	 *
	 * @param vec vector3 to rotate.
	 * @param angle angle (in degrees) to rotate by.
     * @return rotated vector3.
     */
	public static Vector3<Double> rotateAroundZAxis(Vector3<Double> vec, double angle) {
		final double radians = Mathd.toRadians(angle);
		final double cos = Mathd.cos(radians);
		final double sin = Mathd.sin(radians);

		final double x = vec.x * cos + vec.y * sin;
		final double y = vec.y * cos - vec.x * sin;

		vec.x = x;
		vec.y = y;

		return vec;
	}

	private Number toNumber(N num) {
		return (Number) num;
	}
	
	/**
	 * @return vector as integer.
	 */
	public Vector3<Integer> getVector3i() {
		int xx = ((Number)this.x).intValue();
		int yy = ((Number)this.y).intValue();
		int zz = ((Number)this.z).intValue();
		
		return new Vector3<Integer>(xx, yy, zz);
	}
	
	/**
	 * @return vector as float.
	 */
	public Vector3<Float> getVector3f() {
		float xx = toNumber(this.x).floatValue();
		float yy = toNumber(this.y).floatValue();
		float zz = toNumber(this.z).floatValue();
		
		return new Vector3<Float>(xx, yy, zz);
	}
	
	/**
	 * @return vector as double.
	 */
	public Vector3<Double> getVector3d() {
		double xx = toNumber(this.x).doubleValue();
		double yy = toNumber(this.y).doubleValue();
		double zz = toNumber(this.z).doubleValue();
		
		return new Vector3<Double>(xx, yy, zz);
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector3<Byte> absoluteByte(Vector3<Byte> vec) {
		if (vec.x < 0) vec.x = (byte) -vec.x;
		if (vec.y < 0) vec.y = (byte) -vec.y;
		if (vec.z < 0) vec.z = (byte) -vec.z;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector3<Short> absoluteShort(Vector3<Short> vec) {
		if (vec.x < 0) vec.x = (short) -vec.x;
		if (vec.y < 0) vec.y = (short) -vec.y;
		if (vec.z < 0) vec.z = (short) -vec.z;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector3<Integer> absoluteInteger(Vector3<Integer> vec) {
		if (vec.x < 0) vec.x = -vec.x;
		if (vec.y < 0) vec.y = -vec.y;
		if (vec.z < 0) vec.z = -vec.z;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector3<Long> absoluteLong(Vector3<Long> vec) {
		if (vec.x < 0L) vec.x = -vec.x;
		if (vec.y < 0L) vec.y = -vec.y;
		if (vec.z < 0L) vec.z = -vec.z;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector3<Float> absoluteFloat(Vector3<Float> vec) {
		if (vec.x < 0f) vec.x = -vec.x;
		if (vec.y < 0f) vec.y = -vec.y;
		if (vec.z < 0f) vec.z = -vec.z;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector3<Double> absoluteDouble(Vector3<Double> vec) {
		if (vec.x < 0d) vec.x = -vec.x;
		if (vec.y < 0d) vec.y = -vec.y;
		if (vec.z < 0d) vec.z = -vec.z;

		return vec;
	}

	/**
	 * @return new copied instance of this object.
	 */
	public Vector3<N> copy() {
		return new Vector3<N>(x, y, z);
	}
	
	/**
	 * @return new Vector2 object of same numerical type 'N'.
	 */
	public Vector2<N> toVector2() {
		return new Vector2<N>(x, y);
	}
	
	/**
	 * @return new Vector4 object of same numerical type 'N'.
	 */
	public Vector4<N> toVector4() {
		return new Vector4<N>(x, y, z);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		result = prime * result + ((z == null) ? 0 : z.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Vector3 other = (Vector3) obj;
		if (x == null) {
			if (other.x != null) return false;
		}
		else if (!x.equals(other.x)) return false;
		if (y == null) {
			if (other.y != null) return false;
		}
		else if (!y.equals(other.y)) return false;
		if (z == null) {
			if (other.z != null) return false;
		}
		else if (!z.equals(other.z)) return false;
		return true;
	}
	
}
