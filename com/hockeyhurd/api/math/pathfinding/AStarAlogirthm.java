package com.hockeyhurd.api.math.pathfinding;

import com.hockeyhurd.api.math.Vector3;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

/**
 * General purpose A* Alogorithm implementation.
 *
 * @author hockeyhurd
 * @version 1/25/2016.
 */
public class AStarAlogirthm {

	protected static final float DEFAULT_COST = 1.0f;

	protected final IPathTile startTile, endTile;
	protected List<IPathTile> pathTiles;
	protected double distanceLeft;

	/**
	 * @param startTile IPathTile start.
	 * @param endTile   IPathTile end.
	 */
	public AStarAlogirthm(IPathTile startTile, IPathTile endTile) {
		this.startTile = startTile;
		this.endTile = endTile;
		pathTiles = new LinkedList<IPathTile>();
		distanceLeft = endTile.distanceTo(startTile.worldVec());
	}

	/**
	 * Gets the adjacent tiles with ability to toggle diagonals.
	 *
	 * @param world        World to reference.
	 * @param origin       Origin IPathTile object.
	 * @param useDiagonals Boolean flag to toggle use of diagonals.
	 * @return Adjacent tiles' array.
	 */
	public static IPathTile[] getAdjacentTiles(World world, IPathTile origin, boolean useDiagonals) {
		if (world == null || origin == null) return new IPathTile[0];

		IPathTile[] tiles;

		if (useDiagonals) {
			tiles = new IPathTile[3 * 3 * 3 - 1];

			int counter = 0;
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					for (int y = -1; y < 2; y++) {

						if (x == 0 && y == 0 && z == 0) continue;

						TileEntity te = world.getTileEntity(x, y, z);
						if (te instanceof IPathTile) tiles[counter++] = (IPathTile) te;
						else tiles[counter++] = new BasicPathTile(x, y, z, DEFAULT_COST, false);
					}
				}

			}
		}

		else {
			tiles = new IPathTile[6];

			Vector3<Integer> currentVec = origin.getOffsetVec(-1, 0, 0);
			TileEntity te = world.getTileEntity(currentVec.x, currentVec.y, currentVec.z);
			if (te instanceof IPathTile) tiles[0] = (IPathTile) te;
			else tiles[0] = new BasicPathTile(currentVec, DEFAULT_COST, false);

			currentVec = origin.getOffsetVec(1, 0, 0);
			te = world.getTileEntity(currentVec.x, currentVec.y, currentVec.z);
			if (te instanceof IPathTile) tiles[1] = (IPathTile) te;
			else tiles[1] = new BasicPathTile(currentVec, DEFAULT_COST, false);

			currentVec = origin.getOffsetVec(0, 0, -1);
			te = world.getTileEntity(currentVec.x, currentVec.y, currentVec.z);
			if (te instanceof IPathTile) tiles[2] = (IPathTile) te;
			else tiles[2] = new BasicPathTile(currentVec, DEFAULT_COST, false);

			currentVec = origin.getOffsetVec(0, 0, 1);
			te = world.getTileEntity(currentVec.x, currentVec.y, currentVec.z);
			if (te instanceof IPathTile) tiles[3] = (IPathTile) te;
			else tiles[3] = new BasicPathTile(currentVec, DEFAULT_COST, false);

			currentVec = origin.getOffsetVec(0, -1, 0);
			te = world.getTileEntity(currentVec.x, currentVec.y, currentVec.z);
			if (te instanceof IPathTile) tiles[4] = (IPathTile) te;
			else tiles[4] = new BasicPathTile(currentVec, DEFAULT_COST, false);

			currentVec = origin.getOffsetVec(0, 1, 0);
			te = world.getTileEntity(currentVec.x, currentVec.y, currentVec.z);
			if (te instanceof IPathTile) tiles[5] = (IPathTile) te;
			else tiles[5] = new BasicPathTile(currentVec, DEFAULT_COST, false);
		}

		return tiles;
	}

}
