package com.hockeyhurd.api.math.pathfinding;

import com.hockeyhurd.api.math.Vector3;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.hockeyhurd.api.math.pathfinding.PathUtils.distanceTo;

/**
 * General purpose A* Algorithm implementation.
 *
 * @author hockeyhurd
 * @version 1/25/2016.
 */
public class AStarAlogirthm {

	protected IPathTile startTile, endTile;
	protected IPathTile[] pathTiles;
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
	public IPathTile[] findPath(World world) {
		List<PathNode> openList = new LinkedList<PathNode>();
		List<PathNode> closedList = new LinkedList<PathNode>();
		PathNode current = new PathNode(startTile, null, startTile.getCost(), 0.0d);
		PathNode node;
		// final Vector3<Integer> startVec = startTile.worldVec();
		final Vector3<Integer> endVec = endTile.worldVec();

		openList.add(current);

		while (!openList.isEmpty()) {
			Collections.sort(openList, tileSorter);
			current = openList.get(0);

			if (current.vec.equals(endVec)) {
				List<IPathTile> path = new LinkedList<IPathTile>();

				// while (current.parent != null) {
				while (current.hasParentNode()) {
					path.add(current.tile);
					current = current.parent;
				}

				PathUtils.toArray(path, pathTiles);
				// openList.clear();
				// closedList.clear();
				return pathTiles;
			}

			openList.remove(current);
			closedList.add(current);

			IPathTile[] adjacents = PathUtils.getAdjacentTiles(world, current.tile, useDiagonals);

			for (int i = 0; i < adjacents.length; i++) {
				IPathTile at = adjacents[i];
				if (at.isSolid()) continue;

				Vector3<Integer> atVec = at.worldVec();
				// double gCost = current.gCost + current.tile.distanceTo(atVec);
				double gCost = current.gCost + PathUtils.distanceTo(current.tile, at);
				// double hCost = atVec.getNetDifference(endVec);
				double hCost = PathUtils.distanceTo(at, endTile);
				node = new PathNode(at, current, gCost, hCost);

				if (contains(closedList, atVec) && gCost >= node.gCost) continue;
				if (!contains(openList, atVec) || gCost < node.gCost) openList.add(node);
			}
		}

		closedList.clear();

		return null;
	}

	/**
	 * Gets the last calculated path.
	 *
	 * @return IPathTile[].
     */
	public IPathTile[] getLastPath() {
		return pathTiles != null ? pathTiles : new IPathTile[0];
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
