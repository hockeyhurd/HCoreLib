package com.hockeyhurd.api.math.pathfinding;

import com.hockeyhurd.api.math.Vector3;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

import static com.hockeyhurd.api.math.pathfinding.PathUtils.getAdjacentTiles;

/**
 * Simple path finding with only goal is to find a path
 * rather than shortest path.
 *
 * @author hockeyhurd
 * @version 4/13/16
 */
public class BasicPathingAlgorithm {

    protected IPathTile startTile, endTile;
    protected boolean useDiagonals;
    protected final List<BasicPathNode> pathTiles;
    protected Vector3<Integer>[] lastPath;
    protected boolean lastPathMarked = false;

    /**
     * @param useDiagonals boolean flag for diagonals.
     */
    public BasicPathingAlgorithm(boolean useDiagonals) {
        this(null, null, useDiagonals);
    }

    /**
     * @param startTile Initial start tile.
     * @param endTile Initial end tile.
     * @param useDiagonals boolean flag for diagonals.
     */
    public BasicPathingAlgorithm(IPathTile startTile, IPathTile endTile, boolean useDiagonals) {
        this.startTile = startTile;
        this.endTile = endTile;
        this.useDiagonals = useDiagonals;
        pathTiles = new LinkedList<BasicPathNode>();
    }

    /**
     * Finds a generic path from start to end point if applicable.
     *
     * @param world World to reference.
     * @return boolean result.
     */
    public boolean findPath(World world) {
        pathTiles.clear();
        lastPathMarked = true;

        if (startTile.equals(endTile)) {
            pathTiles.add(new BasicPathNode(startTile));
            return true;
        }

        return findPath(world, new BasicPathNode(startTile));
    }

    /**
     * Inner recursive calling function to find a generic path.
     *
     * @param world World to reference.
     * @param origin IPathTile origin.
     * @return boolean result.
     */
    protected boolean findPath(World world, BasicPathNode origin) {
        if (origin.visited) return false;
        origin.visited = true;

        final int indexInserted = pathTiles.size();
        pathTiles.add(origin);

        for (IPathTile neighbor : getAdjacentTiles(world, origin.tile, useDiagonals)) {
            BasicPathNode neighborNode = new BasicPathNode(neighbor);

            if (neighbor.equals(endTile)) {
                pathTiles.add(neighborNode);
                return true;
            }

            else if (!neighborNode.visited && findPath(world, neighborNode)) {
                pathTiles.add(neighborNode);
                return true;
            }

        }

        origin.visited = false;
        pathTiles.remove(indexInserted);

        return false;
    }

    /**
     * Searches over a given list for a specific IPathTile.
     *
     * @see com.hockeyhurd.api.math.pathfinding.IPathTile
     *
     * @param list List to search through.
     * @param search Vector3 to search for.
     * @return result of search.
     */
    protected boolean contains(List<BasicPathNode> list, Vector3<Integer> search) {
        if (list == null || list.isEmpty()) return false;

        for (BasicPathNode node : list) {
            if (search.equals(node.vec)) return true;
        }

        return false;
    }

    /**
     * Gets the last stored path as an array of vector3i's.
     *
     * @return Vector3i array.
     */
    @SuppressWarnings("unchecked")
    public Vector3<Integer>[] getPath() {
        if (lastPathMarked) {
            lastPath = (Vector3<Integer>[]) new Vector3[pathTiles.size()];

            int index = 0;
            for (BasicPathNode node : pathTiles) {
                lastPath[index++] = node.vec;
            }
        }

        return lastPath;
    }

}
