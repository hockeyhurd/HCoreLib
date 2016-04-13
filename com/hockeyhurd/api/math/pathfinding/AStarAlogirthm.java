package com.hockeyhurd.api.math.pathfinding;

import java.util.LinkedList;
import java.util.List;

import static com.hockeyhurd.api.math.pathfinding.PathUtils.distanceTo;

/**
 * General purpose A* Alogorithm implementation.
 *
 * @author hockeyhurd
 * @version 1/25/2016.
 */
public class AStarAlogirthm {

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
		distanceLeft = distanceTo(startTile, endTile);
	}

}
