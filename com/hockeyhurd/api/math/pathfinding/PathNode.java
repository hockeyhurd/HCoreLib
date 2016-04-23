package com.hockeyhurd.api.math.pathfinding;

/**
 * Simple node structure for storing relevant node data.
 * With added features typically used with A* path finding.
 *
 * @author hockeyhurd
 * @version 4/16/2016.
 */
public class PathNode extends BasicPathNode {

	public PathNode parent;
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
		super(tile, tile.worldVec());

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
