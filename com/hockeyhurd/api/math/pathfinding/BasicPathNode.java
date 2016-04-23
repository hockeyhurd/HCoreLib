package com.hockeyhurd.api.math.pathfinding;

import com.hockeyhurd.api.math.Vector3;

/**
 * Simple node structure for storing relevant node data.
 *
 * @author hockeyhurd
 * @version 4/22/16
 */
public class BasicPathNode {

    public IPathTile tile;
    public Vector3<Integer> vec;
    public boolean visited;

    /**
     * @param tile IPathTile.
     */
    public BasicPathNode(IPathTile tile) {
        this(tile, tile.worldVec());
    }

    /**
     * @param tile IPathTile.
     * @param vec Vector3i.
     */
    public BasicPathNode(IPathTile tile, Vector3<Integer> vec) {
        this.tile = tile;
        this.vec = vec;
    }

    @Override
    public int hashCode() {
        int result = tile != null ? tile.hashCode() : 0;
        result = 31 * result + (vec != null ? vec.hashCode() : 0);
        result = 31 * result + (visited ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BasicPathNode)) return false;

        BasicPathNode that = (BasicPathNode) obj;

        if (visited != that.visited) return false;
        if (tile != null ? !tile.equals(that.tile) : that.tile != null) return false;
        return vec != null ? vec.equals(that.vec) : that.vec == null;

    }

}
