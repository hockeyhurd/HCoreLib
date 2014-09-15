package com.hockeyhurd.api.math;

public class Vector4Helper<N> {

	private N x, y, z;
	private int sideHit;
	
	public static Vector4Helper zero = new Vector4Helper();
	
	public Vector4Helper() {
		this((N) (Number) 0, (N) (Number) 0, (N) (Number) 0, -1);
	}
	
	public Vector4Helper(N x, N y, N z) {
		this(x, y, z, -1);
	}
	
	public Vector4Helper(N x, N y, N z, int sideHit) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sideHit = sideHit;
	}
	
	public boolean hasSideHit() {
		return this.sideHit > -1 ? true : false;
	}
	
	public void setX(N x) {
		this.x = x;
	}
	
	public void setY(N y) {
		this.y = y;
	}
	
	public void setZ(N z) {
		this.z = z;
	}
	
	public void setSideHit(int sideHit) {
		this.sideHit = sideHit;
	}
	
	public N getX() {
		return this.x;
	}
	
	public N getY() {
		return this.y;
	}
	
	public N getZ() {
		return this.z;
	}
	
	public int getSideHit() {
		return this.sideHit;
	}
	
	public Vector4Helper<N> add(Vector4Helper<N> vec, boolean changeSideHit) {
		float xx = ((Number)this.getX()).floatValue() + ((Number) vec.getX()).floatValue();
		float yy = ((Number)this.getY()).floatValue() + ((Number) vec.getY()).floatValue();
		float zz = ((Number)this.getZ()).floatValue() + ((Number) vec.getZ()).floatValue();
		
		setX((N) (Number) xx);
		setY((N) (Number) yy);
		setZ((N) (Number) zz);
		
		return this;
	}
	
	public Vector4Helper<N> subtract(Vector4Helper<N> vec, boolean changeSideHit) {
		float xx = ((Number)this.getX()).floatValue() - ((Number) vec.getX()).floatValue();
		float yy = ((Number)this.getY()).floatValue() - ((Number) vec.getY()).floatValue();
		float zz = ((Number)this.getZ()).floatValue() - ((Number) vec.getZ()).floatValue();
		
		setX((N) (Number) xx);
		setY((N) (Number) yy);
		setZ((N) (Number) zz);
		
		return this;
	}
	
	public Vector4Helper<N> getDifference(Vector4Helper<N> vec1, Vector4Helper<N> vec2) {
		float xx0 = toNumber(vec1.getX()).floatValue();
		float yy0 = toNumber(vec1.getY()).floatValue();
		float zz0 = toNumber(vec1.getZ()).floatValue();
		
		float xx1 = toNumber(vec2.getX()).floatValue();
		float yy1 = toNumber(vec2.getY()).floatValue();
		float zz1 = toNumber(vec2.getZ()).floatValue();
		
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
	
	public boolean equals(Object object) {
		if (!(object instanceof Vector4Helper)) return false;
		Vector4Helper vec = (Vector4Helper) object;
		if (vec.getX() == this.getX() && vec.y == this.getY() && vec.getZ() == this.getZ()) return true;
		return false;
	}
	
}
