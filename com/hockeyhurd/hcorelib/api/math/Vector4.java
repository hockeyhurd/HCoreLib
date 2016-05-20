package com.hockeyhurd.hcorelib.api.math;

/**
 * Class used to store world co-ordinate data by specified number.
 * 
 * @author hockeyhurd
 * @version Oct 18, 2014
 */
public class Vector4<N> {

	// Local vars
	public N x, y, z, w;
	
	/** Identity function used for setting a vector to its 'zero'. */
	public static final Vector4 zero = new Vector4();

	/** (0, -1, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector4 down = new Vector4(0, -1, 0);

	/** (0, 1, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector4 up = new Vector4(0, 1, 0);

	/** (1, 0, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector4 east = new Vector4(1, 0, 0);

	/** (-1, 0, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector4 west = new Vector4(-1, 0, 0);

	/** (0, 0, -1) */
	@SuppressWarnings("unchecked")
	public static final Vector4 north = new Vector4(0, 0, -1);

	/** (0, 0, 1) */
	@SuppressWarnings("unchecked")
	public static final Vector4 south = new Vector4(0, 0, 1);
	
	/**
	 * Constructor creates new instance of this class with default 
	 * co-ordinate value of (0, 0, 0, -1).
	 */
	public Vector4() {
		this((N) (Number) 0, (N) (Number) 0, (N) (Number) 0);
	}
	
	/**
	 * Alternative constructor creates new instance of this class
	 * through setting to (x, y, z, 0).
	 * 
	 * @param x = xPos
	 * @param y = yPos
	 * @param z = zPos
	 */
	public Vector4(N x, N y, N z) {
		this(x, y, z, (N) (Number) 0);
	}
	
	/**
	 * Alternative constructor creates new instance of this class
	 * through setting to (x, y, z, val).
	 * 
	 * @param x = xPos
	 * @param y = yPos
	 * @param z = zPos
	 * @param w = Mainly used for storing sides of block however
	 * 	can be re-purposed if desired.
	 */
	public Vector4(N x, N y, N z, N w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/**
	 * Adds current vector from referenced vector.
	 * 
	 * @param vec vector to add from this.
	 * @return added vector.
	 */
	public Vector4<N> add(Vector4<N> vec) {
		float xx = ((Number)this.x).floatValue() + ((Number) vec.x).floatValue();
		float yy = ((Number)this.y).floatValue() + ((Number) vec.y).floatValue();
		float zz = ((Number)this.z).floatValue() + ((Number) vec.z).floatValue();
		float ww = ((Number) this.w).floatValue() + ((Number) vec.w).floatValue();
		
		this.x = (N) (Number) xx;
		this.y = (N) (Number) yy;
		this.z = (N) (Number) zz;
		this.w = (N) (Number) ww;
		
		return this;
	}
	
	/**
	 * Subtracts current vector from referenced vector.
	 * 
	 * @param vec vector to subtract from this.
	 * @return subtracted vector.
	 */
	public Vector4<N> subtract(Vector4<N> vec) {
		float xx = ((Number)this.x).floatValue() - ((Number) vec.x).floatValue();
		float yy = ((Number)this.y).floatValue() - ((Number) vec.y).floatValue();
		float zz = ((Number)this.z).floatValue() - ((Number) vec.z).floatValue();
		float ww = ((Number)this.w).floatValue() - ((Number) vec.w).floatValue();
		
		this.x = (N) (Number) xx;
		this.y = (N) (Number) yy;
		this.z = (N) (Number) zz;
		this.w = (N) (Number) ww;
		
		return this;
	}
	
	/**
	 * Gets the difference net difference of each individual element.
	 * 
	 * @param other point '2'.
	 * @return differenced vectors.
	 */
	public Vector4<N> getDifference(Vector4<N> other) {
		float xx0 = toNumber(this.x).floatValue();
		float yy0 = toNumber(this.y).floatValue();
		float zz0 = toNumber(this.z).floatValue();
		float ww0 = toNumber(this.w).floatValue();
		
		float xx1 = toNumber(other.x).floatValue();
		float yy1 = toNumber(other.y).floatValue();
		float zz1 = toNumber(other.z).floatValue();
		float ww1 = toNumber(other.w).floatValue();
		
		float xTot = xx0 - xx1;
		float yTot = yy0 - yy1;
		float zTot = zz0 - zz1;
		float wTot = ww0 - ww1;
		
		return new Vector4<N>((N) (Number) xTot, (N) (Number) yTot, (N) (Number) zTot, (N) (Number) wTot);
	}
	
	/**
	 * Gets the net difference between current vector<sub>1</sub> (point).
	 * 
	 * @param vec vector<sub>2</sub>
	 * @return difference as a double.
	 */
	public double getNetDifference(Vector4<N> vec) {
		Vector4<Double> dif = getDifference(vec).getVector4d();
		double sum = (dif.x * dif.x) + (dif.y * dif.y) + (dif.z * dif.z) + (dif.w * dif.w);
		return Math.sqrt(sum);
	}

	/**
	 * Calculates linear interpolation between two vectors.
	 *
	 * @param v0
	 * @param v1
	 * @return linear interpolation between two vectors.
	 */
	public static Vector4<Double> lerp(Vector4<Double> v0, Vector4<Double> v1) {
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
	public static Vector4<Double> lerp(Vector4<Double> v0, Vector4<Double> v1, double weight) {
		Vector4<Double> ret = new Vector4<Double>();
		ret.x = Mathd.lerp(v0.x, v1.x, weight);
		ret.y = Mathd.lerp(v0.y, v1.y, weight);
		ret.z = Mathd.lerp(v0.z, v1.z, weight);
		ret.w = Mathd.lerp(v0.w, v1.w, weight);

		return ret;
	}
	
	private Number toNumber(N num) {
		return (Number) num;
	}
	
	/**
	 * @return vector as integer.
	 */
	public Vector4<Integer> getVector4i() {
		int xx = ((Number)this.x).intValue();
		int yy = ((Number)this.y).intValue();
		int zz = ((Number)this.z).intValue();
		int ww = ((Number)this.w).intValue();
		
		return new Vector4<Integer>(xx, yy, zz, ww);
	}
	
	/**
	 * @return vector as float.
	 */
	public Vector4<Float> getVector4f() {
		float xx = toNumber(this.x).floatValue();
		float yy = toNumber(this.y).floatValue();
		float zz = toNumber(this.z).floatValue();
		float ww = toNumber(this.w).floatValue();
		
		return new Vector4<Float>(xx, yy, zz, ww);
	}
	
	/**
	 * @return vector as double.
	 */
	public Vector4<Double> getVector4d() {
		double xx = toNumber(this.x).doubleValue();
		double yy = toNumber(this.y).doubleValue();
		double zz = toNumber(this.z).doubleValue();
		double ww = toNumber(this.w).doubleValue();
		
		return new Vector4<Double>(xx, yy, zz, ww);
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector4<Byte> absoluteByte(Vector4<Byte> vec) {
		if (vec.x < 0) vec.x = (byte) -vec.x;
		if (vec.y < 0) vec.y = (byte) -vec.y;
		if (vec.z < 0) vec.z = (byte) -vec.z;
		if (vec.w < 0) vec.w = (byte) -vec.w;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector4<Short> absoluteShort(Vector4<Short> vec) {
		if (vec.x < 0) vec.x = (short) -vec.x;
		if (vec.y < 0) vec.y = (short) -vec.y;
		if (vec.z < 0) vec.z = (short) -vec.z;
		if (vec.w < 0) vec.w = (short) -vec.w;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector4<Integer> absoluteInteger(Vector4<Integer> vec) {
		if (vec.x < 0) vec.x = -vec.x;
		if (vec.y < 0) vec.y = -vec.y;
		if (vec.z < 0) vec.z = -vec.z;
		if (vec.w < 0) vec.w = -vec.w;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector4<Long> absoluteLong(Vector4<Long> vec) {
		if (vec.x < 0L) vec.x = -vec.x;
		if (vec.y < 0L) vec.y = -vec.y;
		if (vec.z < 0L) vec.z = -vec.z;
		if (vec.w < 0L) vec.w = -vec.w;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector4<Float> absoluteFloat(Vector4<Float> vec) {
		if (vec.x < 0f) vec.x = -vec.x;
		if (vec.y < 0f) vec.y = -vec.y;
		if (vec.z < 0f) vec.z = -vec.z;
		if (vec.w < 0f) vec.w = -vec.w;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector4<Double> absoluteDouble(Vector4<Double> vec) {
		if (vec.x < 0d) vec.x = -vec.x;
		if (vec.y < 0d) vec.y = -vec.y;
		if (vec.z < 0d) vec.z = -vec.z;
		if (vec.w < 0d) vec.w = -vec.w;

		return vec;
	}
	
	/**
	 * @return new copied instance of this object.
	 */
	public Vector4<N> copy() {
		return new Vector4<N>(x, y, z, w);
	}

	/**
	 * @return new Vector2 object of same numerical type 'N'.
	 */
	public Vector2<N> toVector2() {
		return new Vector2<N>(x, y);
	}
	
	/**
	 * @return new Vector3 object of same numerical type 'N'.
	 */
	public Vector3<N> toVector3() {
		return new Vector3<N>(x, y, z);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
	}
	
	/**
	 * Checks 4th element for equality.
	 * 
	 * @param object object to reference.
	 * @param includeW set to true, just do it.
	 * @return
	 */
	public boolean equals(Object object, boolean includeW) {
		return includeW && equals(object) && object instanceof Vector4 && ((Vector4) object).w.equals(this.w);
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
		Vector4 other = (Vector4) obj;
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
