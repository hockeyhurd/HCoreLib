package com.hockeyhurd.api.math;

/**
 * Class used to store world co-ordinate data by specified number.
 * 
 * @author hockeyhurd
 * @version May 21, 2015
 */
public class Vector2<N> {

	public N x, y;
	
	/** Identity function used for setting a vector to its 'zero'. */
	public static final Vector2 zero = new Vector2();
	
	/**
	 * Constructor creates new instance of this class with default 
	 * co-ordinate value of (0, 0, 0).
	 */
	public Vector2() {
		this((N) (Number) 0, (N) (Number) 0);
	}

	/**
	 * Most generalized constructor creates new instance of this class
	 * through setting to (x, y, z).
	 * 
	 * @param x = xPos
	 * @param y = yPos
	 */
	public Vector2(N x, N y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Adds current vector from referenced vector.
	 * 
	 * @param vec vector to add from this.
	 * @return added vector.
	 */
	public Vector2<N> add(Vector2<N> vec) {
		float xx = ((Number)this.x).floatValue() + ((Number) vec.x).floatValue();
		float yy = ((Number)this.y).floatValue() + ((Number) vec.y).floatValue();
		
		this.x = (N) (Number) xx;
		this.y = (N) (Number) yy;
		
		return this;
	}
	
	/**
	 * Subtracts current vector from referenced vector.
	 * 
	 * @param vec vector to subtract from this.
	 * @return subtracted vector.
	 */
	public Vector2<N> subtract(Vector2<N> vec) {
		float xx = ((Number)this.x).floatValue() - ((Number) vec.x).floatValue();
		float yy = ((Number)this.y).floatValue() - ((Number) vec.y).floatValue();
		
		this.x = (N) (Number) xx;
		this.y = (N) (Number) yy;
		
		return this;
	}
	
	/**
	 * Gets the difference net difference of each individual element.
	 * 
	 * @param other point '2'.
	 * @return differenced vectors.
	 */
	public Vector2<N> getDifference(Vector2<N> other) {
		float xx0 = toNumber(this.x).floatValue();
		float yy0 = toNumber(this.y).floatValue();
		
		float xx1 = toNumber(other.x).floatValue();
		float yy1 = toNumber(other.y).floatValue();
		
		float xTot = xx0 - xx1;
		float yTot = yy0 - yy1;
		
		return new Vector2<N>((N) (Number) xTot, (N) (Number) yTot);
	}
	
	/**
	 * Gets the net difference between current vector<sub>1</sub> (point).
	 * 
	 * @param vec vector<sub>2</sub>
	 * @return difference as a double.
	 */
	public double getNetDifference(Vector2<N> vec) {
		Vector2<Double> dif = getDifference(vec).getVector2d();
		double sum = (dif.x * dif.x) + (dif.y * dif.y);
		return Math.sqrt(sum);
	}

	/**
	 * Calculates linear interpolation between two vectors.
	 *
	 * @param v0
	 * @param v1
	 * @return linear interpolation between two vectors.
	 */
	public static Vector2<Double> lerp(Vector2<Double> v0, Vector2<Double> v1) {
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
	public static Vector2<Double> lerp(Vector2<Double> v0, Vector2<Double> v1, double weight) {
		Vector2<Double> ret = new Vector2<Double>();
		ret.x = Mathd.lerp(v0.x, v1.x, weight);
		ret.y = Mathd.lerp(v0.y, v1.y, weight);

		return ret;
	}

	/**
	 * Rotates this vector about another vector by said angle in degrees.
	 * 
	 * @param ref point of which to rotate about.
	 * @param angle angle in degrees to rotate.
	 * @return rotated vector.
	 */
	public Vector2<N> rotate(Vector2<Double> ref, double angle) {
		double radians = Mathd.toDegrees(angle);
		Vector2<Double> copy = this.getVector2d();
		
		copy.x = ref.x + (copy.x - ref.x) * Mathd.cos(radians) + (copy.y - ref.y) * Mathd.sin(radians);
		copy.y = ref.y - (copy.x - ref.x) * Mathd.sin(radians) + (copy.y - ref.y * Mathd.cos(radians));
		
		this.x = (N) copy.x;
		this.y = (N) copy.y;
		
		return this;
	}
	
	private Number toNumber(N num) {
		return (Number) num;
	}
	
	/**
	 * @return vector as integer.
	 */
	public Vector2<Integer> getVector2i() {
		int xx = ((Number)this.x).intValue();
		int yy = ((Number)this.y).intValue();
		
		return new Vector2<Integer>(xx, yy);
	}
	
	/**
	 * @return vector as float.
	 */
	public Vector2<Float> getVector2f() {
		float xx = toNumber(this.x).floatValue();
		float yy = toNumber(this.y).floatValue();
		
		return new Vector2<Float>(xx, yy);
	}
	
	/**
	 * @return vector as double.
	 */
	public Vector2<Double> getVector2d() {
		double xx = toNumber(this.x).doubleValue();
		double yy = toNumber(this.y).doubleValue();
		
		return new Vector2<Double>(xx, yy);
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector2<Byte> absoluteByte(Vector2<Byte> vec) {
		if (vec.x < 0) vec.x = (byte) -vec.x;
		if (vec.y < 0) vec.y = (byte) -vec.y;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector2<Short> absoluteShort(Vector2<Short> vec) {
		if (vec.x < 0) vec.x = (short) -vec.x;
		if (vec.y < 0) vec.y = (short) -vec.y;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector2<Integer> absoluteInteger(Vector2<Integer> vec) {
		if (vec.x < 0) vec.x = -vec.x;
		if (vec.y < 0) vec.y = -vec.y;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector2<Long> absoluteLong(Vector2<Long> vec) {
		if (vec.x < 0L) vec.x = -vec.x;
		if (vec.y < 0L) vec.y = -vec.y;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector2<Float> absoluteFloat(Vector2<Float> vec) {
		if (vec.x < 0f) vec.x = -vec.x;
		if (vec.y < 0f) vec.y = -vec.y;

		return vec;
	}

	/**
	 * Makes vector in terms of absolute magnitude.
	 *
	 * @param vec vector to reference.
	 */
	public static Vector2<Double> absoluteDouble(Vector2<Double> vec) {
		if (vec.x < 0d) vec.x = -vec.x;
		if (vec.y < 0d) vec.y = -vec.y;

		return vec;
	}

	/**
	 * @return new copied instance of this object.
	 */
	public Vector2<N> copy() {
		return new Vector2<N>(x, y);
	}
	
	/**
	 * @return new Vector3 object of same numerical type 'N'.
	 */
	public Vector3<N> toVector3() {
		return new Vector3<N>(x, y, (N) (Number) 0);
	}
	
	/**
	 * @return new Vector4 object of same numerical type 'N'.
	 */
	public Vector4<N> toVector4() {
		return new Vector4<N>(x, y, (N) (Number) 0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
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
		Vector2 other = (Vector2) obj;
		if (x == null) {
			if (other.x != null) return false;
		}
		else if (!x.equals(other.x)) return false;
		if (y == null) {
			if (other.y != null) return false;
		}
		else if (!y.equals(other.y)) return false;
		return true;
	}

}
