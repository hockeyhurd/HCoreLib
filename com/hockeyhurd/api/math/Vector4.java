package com.hockeyhurd.api.math;

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
	public static Vector4 zero = new Vector4();
	
	/**
	 * Constructor creates new instance of this class with default 
	 * co-ordinate value of (0, 0, 0, -1).
	 */
	public Vector4() {
		this((N) (Number) 0, (N) (Number) 0, (N) (Number) 0);
	}
	
	/**
	 * Alternative constructor creates new instance of this class
	 * through setting to (x, y, z, -1).
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
	 * @param w = Mainly used for storing sides of blocks however 
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
	 * @param vec1 point 1.
	 * @param vec2 point 2.
	 * @return differenced vectors.
	 */
	public Vector4<N> getDifference(Vector4<N> vec1, Vector4<N> vec2) {
		float xx0 = toNumber(vec1.x).floatValue();
		float yy0 = toNumber(vec1.y).floatValue();
		float zz0 = toNumber(vec1.z).floatValue();
		float ww0 = toNumber(vec1.w).floatValue();
		
		float xx1 = toNumber(vec2.x).floatValue();
		float yy1 = toNumber(vec2.y).floatValue();
		float zz1 = toNumber(vec2.z).floatValue();
		float ww1 = toNumber(vec2.w).floatValue();
		
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
	public Double getDifference(Vector4<N> vec) {
		Vector4<Double> dif = getDifference(vec, this).getVector4d();
		double sum = (dif.x * dif.x) + (dif.y * dif.y) + (dif.z * dif.z) + (dif.w * dif.w);
		return Math.sqrt(sum);
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
	
	public Vector4<N> copy() {
		return new Vector4<N>(x, y, z, w);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
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
