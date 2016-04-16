package com.hockeyhurd.api.math.pathfinding;

import com.hockeyhurd.api.math.Vector3;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.hockeyhurd.api.math.pathfinding.PathUtils.distanceTo;
import static com.hockeyhurd.api.math.pathfinding.PathUtils.getAdjacentTiles;

/**
 * General purpose A* Algorithm implementation.
 *
 * @author hockeyhurd
 * @version 1/25/2016.
 */
public class AStarAlogirthm {

	protected IPathTile startTile, endTile;
	// protected List<IPathTile> pathTiles;
	protected double distanceLeft;
	protected boolean useDiagonals;

	protected Comparator<PathNode> tileSorter = new Comparator<PathNode>() {
		@Override
		public int compare(PathNode tile, PathNode other) {
			final Vector3<Integer> endVec = endTile.worldVec();

			double tileHCost = tile.tile.distanceTo(endVec);
			double otherHCost = other.tile.distanceTo(endVec);

			double tileFCost = tile.gCost + tileHCost;
			double otherFCost = other.gCost + otherHCost;

			return otherFCost < tileFCost ? 1 : otherFCost > tileFCost ? -1 : 0;
		}
	};

	/**
	 * @param startTile IPathTile start.
	 * @param endTile   IPathTile end.
	 */
	public AStarAlogirthm(IPathTile startTile, IPathTile endTile) {
		this.startTile = startTile;
		this.endTile = endTile;
		// pathTiles = new LinkedList<IPathTile>();
		distanceLeft = distanceTo(startTile, endTile);
		this.useDiagonals = false;
	}

	/**
	 * Finds and returns a path if available.
	 *
	 * @param world World to reference.
	 * @return List of path tiles to follow if found, else may return NULL!
	 */
	public List<IPathTile> findPath(World world) {
		List<PathNode> openList = new LinkedList<PathNode>();
		List<PathNode> closedList = new LinkedList<PathNode>();
		PathNode current = new PathNode(startTile, null, startTile.getCost(), 0.0d);
		IPathTile[] adjacents;
		IPathTile at;
		// Node at;
		PathNode node;
		Vector3<Integer> atVec;
		final Vector3<Integer> startVec = startTile.worldVec();
		final Vector3<Integer> endVec = endTile.worldVec();
		double gCost;
		double hCost;
		// double hCost = current.distanceTo(endVec);
		// double fCost;

		openList.add(current);

		while (!openList.isEmpty()) {
			Collections.sort(openList, tileSorter);
			current = openList.get(0);

			if (current.vec.equals(endVec)) {
				List<IPathTile> path = new LinkedList<IPathTile>();

				while (current.parent != null) {
					path.add(current.tile);
					current = current.parent;
				}

				openList.clear();
				closedList.clear();
				return path;
			}

			openList.remove(current);
			closedList.add(current);

			adjacents = PathUtils.getAdjacentTiles(world, current.tile, useDiagonals);

			for (int i = 0; i < adjacents.length; i++) {
				at = adjacents[i];
				if (at.isSolid()) continue;
				atVec = at.worldVec();
				gCost = current.gCost + current.tile.distanceTo(atVec);
				hCost = atVec.getNetDifference(endVec);
				node = new PathNode(at, current, gCost, hCost);
				if (contains(closedList, atVec) && gCost >= node.gCost) continue;
				if (!contains(openList, atVec) || gCost < node.gCost) openList.add(node);
			}
		}

		closedList.clear();

		return null;
	}

	/**
	 * Checks if vector is in PathNode list.
	 *
	 * @param list List to check.
	 * @param search Vector3 to search for.
	 * @return boolean result.
	 */
	protected boolean contains(List<PathNode> list, Vector3<Integer> search) {
		if (list == null || list.isEmpty() || search == null) return false;

		for (PathNode node : list) {
			if (search.equals(node.vec)) return true;
		}

		return false;
	}

}
