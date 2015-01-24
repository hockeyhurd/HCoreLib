package com.hockeyhurd.api.math;

/**
 * Class used to store world co-ordinate data by specified number.
 * 
 * @author hockeyhurd
 * @version Oct 18, 2014
 */
public class Vector4Helper<N> {

	// Local vars
	public N x, y, z;
	private int sideHit;
	
	/** Identity function used for setting a vector to its 'zero'. */
	public static Vector4Helper zero = new Vector4Helper();
	
	/**
	 * Constructor creates new instance of this class with default 
	 * co-ordinate value of (0, 0, 0, -1).
	 */
	public Vector4Helper() {
		this((N) (Number) 0, (N) (Number) 0, (N) (Number) 0, -1);
	}
	
	/**
	 * Alternative constructor creates new instance of this class
	 * through setting to (x, y, z, -1).
	 * 
	 * @param x = xPos
	 * @param y = yPos
	 * @param z = zPos
	 */
	public Vector4Helper(N x, N y, N z) {
		this(x, y, z, -1);
	}
	
	/**
	 * Alternative constructor creates new instance of this class
	 * through setting to (x, y, z, val).
	 * 
	 * @param x = xPos
	 * @param y = yPos
	 * @param z = zPos
	 * @param sideHit = Mainly used for storing sides of blocks however 
	 * 	be re-purposed if desired.
	 */
	public Vector4Helper(N x, N y, N z, int sideHit) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sideHit = sideHit;
	}
	
	/**
	 * Determines whether has a value for sideHit of 4th value.
	 * 
	 * @return
	 */
	public boolean hasSideHit() {
		return this.sideHit > -1 ? true : false;
	}
	
	public void setSideHit(int sideHit) {
		this.sideHit = sideHit;
	}
	
	public int getSideHit() {
		return this.sideHit;
	}
	
	public Vector4Helper<N> add(Vector4Helper<N> vec, boolean changeSideHit) {
		float xx = ((Number)this.x).floatValue() + ((Number) vec.x).floatValue();
		float yy = ((Number)this.y).floatValue() + ((Number) vec.y).floatValue();
		float zz = ((Number)this.z).floatValue() + ((Number) vec.z).floatValue();
		
		this.x = (N) (Number) xx;
		this.y = (N) (Number) yy;
		this.z = (N) (Number) zz;
		
		return this;
	}
	
	public Vector4Helper<N> subtract(Vector4Helper<N> vec, boolean changeSideHit) {
		float xx = ((Number)this.x).floatValue() - ((Number) vec.x).floatValue();
		float yy = ((Number)this.y).floatValue() - ((Number) vec.y).floatValue();
		float zz = ((Number)this.z).floatValue() - ((Number) vec.z).floatValue();
		
		this.x = (N) (Number) xx;
		this.y = (N) (Number) yy;
		this.z = (N) (Number) zz;
		
		return this;
	}
	
	public Vector4Helper<N> getDifference(Vector4Helper<N> vec1, Vector4Helper<N> vec2) {
		float xx0 = toNumber(vec1.x).floatValue();
		float yy0 = toNumber(vec1.y).floatValue();
		float zz0 = toNumber(vec1.z).floatValue();
		
		float xx1 = toNumber(vec2.x).floatValue();
		float yy1 = toNumber(vec2.y).floatValue();
		float zz1 = toNumber(vec2.z).floatValue();
		
		float xTot = xx0 - xx1;
		float yTot = yy0 - yy1;
		float zTot = zz0 - zz1;
		
		return new Vector4Helper<N>((N) (Number) xTot, (N) (Number) yTot, (N) (Number) zTot);
	}
	
	private Number toNumber(N num) {
		return (Number) num;
	}
	
	public Vector4Helper<Integer> getVector4i() {
		int xx = ((Number)this.x).intValue();
		int yy = ((Number)this.y).intValue();
		int zz = ((Number)this.z).intValue();
		return new Vector4Helper<Integer>(xx, yy, zz);
	}
	
	public Vector4Helper<Float> getVector4f() {
		float xx = toNumber(this.x).floatValue();
		float yy = toNumber(this.y).floatValue();
		float zz = toNumber(this.z).floatValue();
		return new Vector4Helper<Float>(xx, yy, zz);
	}
	
	public Vector4Helper<Double> getVector4d() {
		double xx = toNumber(this.x).doubleValue();
		double yy = toNumber(this.y).doubleValue();
		double zz = toNumber(this.z).doubleValue();
		return new Vector4Helper<Double>(xx, yy, zz);
	}
	
	public Vector4Helper<N> copy() {
		return this;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Vector4Helper)) return false;
		Vector4Helper vec = (Vector4Helper) object;
		if (vec.x == this.x && vec.y == this.y && vec.z == this.z) return true;
		return false;
	}
	
}
