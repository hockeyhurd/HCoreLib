package com.hockeyhurd.api.math.pathfinding;

import com.hockeyhurd.api.math.Vector3;

/**
 * General purpose BasicPathTile with parameterized values.
 *
 * @author hockeyhurd
 * @version 1/25/2016.
 */
public class BasicPathTile implements IPathTile {

	private Vector3<Integer> vec;
	private boolean solid;

	public BasicPathTile(int x, int y, int z, boolean solid) {
		vec = new Vector3<Integer>(x, y, z);
		this.solid = solid;
	}

	public BasicPathTile(Vector3<Integer> vec, boolean solid) {
		this.vec = vec;
		this.solid = solid;
	}

	@Override
	public double distanceTo(Vector3<Integer> vec) {
		return this.vec.getNetDifference(vec);
	}

	@Override
	public Vector3<Integer> worldVec() {
		return vec;
	}

	@Override
	public Vector3<Integer> getOffsetVec(int x, int y, int z) {
		final Vector3<Integer> ret = vec.copy();

		ret.x += x;
		ret.y += y;
		ret.z += z;

		return ret;
	}

	@Override
	public boolean isSolid() {
		return solid;
	}

	@Override
	public int hashCode() {
		int result = vec.hashCode();
		result = 31 * result + (solid ? 1 : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		final BasicPathTile other = (BasicPathTile) obj;

		if (solid != other.solid) return false;
		return vec.equals(other.vec);
	}

}
