package com.hockeyhurd.api.math.pathfinding;

import com.hockeyhurd.api.math.Vector3;

/**
 * Simple node structure for storing relevant node data.
 *
 * @author hockeyhurd
 * @version 4/16/2016.
 */
public class PathNode {

	public IPathTile tile;
	public PathNode parent;
	public Vector3<Integer> vec;
	public double gCost;
	public double hCost;

	/**
	 * Constructs and initializes with given parameters.
	 *
	 * @param tile IPathTile.
	 * @param parent PathNode.
	 * @param gCost double.
	 * @param hCost double.
	 */
	public PathNode(IPathTile tile, PathNode parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.hCost = hCost;
		this.gCost = gCost;
		this.vec = tile.worldVec();
	}

	/**
	 * Constructs and initializes with given parameters.
	 *
	 * @param tile IPathTile.
	 * @param parent PathNode.
	 */
	public PathNode(IPathTile tile, PathNode parent) {
		this(tile, parent, tile.getCost(), 0.0d);
	}

	/**
	 * Function to check if this node has a parent node.
	 *
	 * @return boolean result.
     */
	public boolean hasParentNode() {
		return parent != null;
	}

}
