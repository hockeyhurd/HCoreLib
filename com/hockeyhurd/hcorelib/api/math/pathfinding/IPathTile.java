package com.hockeyhurd.hcorelib.api.math.pathfinding;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

/**
 * Interfacing for all pathing tiles.
 *
 * @author hockeyhurd
 * @version 1/25/2016.
 */
public interface IPathTile {

	/**
	 * Distance to Vector3i location.
	 *
	 * @param vec Vector3i to reference.
	 * @return distance to.
     */
	double distanceTo(Vector3<Integer> vec);

	/**
	 * Gets Vector3i of tile.
	 *
	 * @return Vector3i.
     */
	Vector3<Integer> worldVec();

	/**
	 * Gets a Vector3i offset by values.
	 *
	 * @param x x-offset.
	 * @param y y-offset.
	 * @param z z-offset.
     * @return Offset Vector3i.
     */
	Vector3<Integer> getOffsetVec(int x, int y, int z);

	/**
	 * Gets the cost of traversing this tile.
	 *
	 * @return float based cost.
     */
	float getCost();

	/**
	 * Solidity of tile.
	 *
	 * @return Solidity flag.
     */
	boolean isSolid();

	/**
	 * Gets the block from the tile.
	 *
	 * @param world World to reference.
	 * @return Block at tile.
     */
	IBlockState getTile(World world);

	@Override
	int hashCode();

	@Override
	boolean equals(Object obj);

}
