package com.hockeyhurd.api.math;

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
	public static final Vector3 down = new Vector3(0, -1, 0);

	/** (0, 1, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector3 up = new Vector3(0, 1, 0);

	/** (1, 0, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector3 east = new Vector3(1, 0, 0);

	/** (-1, 0, 0) */
	@SuppressWarnings("unchecked")
	public static final Vector3 west = new Vector3(-1, 0, 0);

	/** (0, 0, -1) */
	@SuppressWarnings("unchecked")
	public static final Vector3 north = new Vector3(0, 0, -1);

	/** (0, 0, 1) */
	@SuppressWarnings("unchecked")
	public static final Vector3 south = new Vector3(0, 0, 1);

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
		double sum = (dif.x * dif.x) + (dif.y * dif.y) + (dif.z * dif.z);
		return Math.sqrt(sum);
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
