package com.hockeyhurd.hcorelib.api.math.pathfinding;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * General purpose BasicPathTile with parameterized values.
 *
 * @see com.hockeyhurd.hcorelib.api.math.pathfinding.IPathTile
 *
 * @author hockeyhurd
 * @version 1/25/2016.
 */
public class BasicPathTile implements IPathTile {

	protected Vector3<Integer> vec;
	protected float cost;
	protected boolean solid;

	/**
	 * @param x x-pos.
	 * @param y y-pos.
	 * @param z z-pos.
	 * @param cost transition cost.
     * @param solid solidity i.e. can traverse over block.
     */
	public BasicPathTile(int x, int y, int z, float cost, boolean solid) {
		vec = new Vector3<Integer>(x, y, z);
		this.cost = cost;
		this.solid = solid;
	}

	/**
	 * @param vec Vector3i pos.
	 * @param cost transition cost.
	 * @param solid solidity i.e. can traverse over block.
     */
	public BasicPathTile(Vector3<Integer> vec, float cost, boolean solid) {
		if (vec == null) throw new NullPointerException("Vector is null... You should really get this checked out!");

		this.vec = vec;
		this.cost = cost;
		this.solid = solid;
	}

	/**
	 * Factory function.
	 *
	 * @param x x-pos.
	 * @param y y-pos.
	 * @param z z-pos.
	 * @param cost transition cost.
	 * @param solid solidity i.e. can traverse over block.
     * @return BasicPathTile.
     */
	public static BasicPathTile createBasicPathTile(int x, int y, int z, float cost, boolean solid) {
		return new BasicPathTile(x, y, z, cost, solid);
	}

	/**
	 * Factory function.
	 *
	 * @param vec Vector3i pos.
	 * @param cost transition cost.
	 * @param solid solidity i.e. can traverse over block.
     * @return BasicPathTile.
     */
	public static BasicPathTile createBasicPathTile(Vector3<Integer> vec, float cost, boolean solid) {
		return new BasicPathTile(vec, cost, solid);
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
	public float getCost() {
		return cost;
	}

	@Override
	public boolean isSolid() {
		return solid;
	}

	@Override
	public Block getTile(World world) {
		return world != null ? world.getBlock(vec.x, vec.y, vec.z) : null;
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
