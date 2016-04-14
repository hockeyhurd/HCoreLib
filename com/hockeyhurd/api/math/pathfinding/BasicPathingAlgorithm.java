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
    protected final List<IPathTile> pathTiles;
    protected final List<IPathTile> visitedTiles;
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
        pathTiles = new LinkedList<IPathTile>();
        visitedTiles = new LinkedList<IPathTile>();
    }

    /**
     * Finds a generic path from start to end point if applicable.
     *
     * @param world World to reference.
     * @return boolean result.
     */
    public boolean findPath(World world) {
        pathTiles.clear();
        visitedTiles.clear();
        lastPathMarked = true;

        if (startTile.equals(endTile)) {
            pathTiles.add(startTile);
            return true;
        }

        return findPath(world, startTile);
    }

    /**
     * Inner recursive calling function to find a generic path.
     *
     * @param world World to reference.
     * @param origin IPathTile origin.
     * @return boolean result.
     */
    protected boolean findPath(World world, IPathTile origin) {
        if (visitedTiles.contains(origin)) return false; // Already visited, return.
        visitedTiles.add(origin);

        for (IPathTile neighbor : getAdjacentTiles(world, origin, useDiagonals)) {
            if (neighbor.equals(endTile)) {
                pathTiles.add(neighbor);
                return true;
            }

            else if (findPath(world, neighbor)) {
            // else if (!visitedTiles.contains(neighbor) && findPath(world, neighbor)) {
                pathTiles.add(neighbor);
                return true;
            }

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
            lastPath = new Vector3[pathTiles.size()];

            int index = 0;
            for (IPathTile tile : pathTiles) {
                lastPath[index++] = tile.worldVec();
            }
        }

        return lastPath;
    }

}
