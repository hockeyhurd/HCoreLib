package com.hockeyhurd.hcorelib.api.math.pathfinding;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;

/**
 * General purpose Pathing Tile with added features over a
 * BasicPathTile.
 *
 * @see com.hockeyhurd.hcorelib.api.math.pathfinding.BasicPathTile
 * @see com.hockeyhurd.hcorelib.api.math.pathfinding.IPathTile
 *
 * @author hockeyhurd
 * @version 3/13/16
 */
public class AdvancedPathTile extends BasicPathTile {

    protected boolean hazardousTile = false;

    /**
     * @param x x-pos.
     * @param y y-pos.
     * @param z z-pos.
     * @param cost transition cost.
     * @param solid solidity i.e. can traverse over block.
     */
    public AdvancedPathTile(int x, int y, int z, float cost, boolean solid) {
        super(x, y, z, cost, solid);
    }

    /**
     * @param vec Vector3i pos.
     * @param cost transition cost.
     * @param solid solidity i.e. can traverse over block.
     */
    public AdvancedPathTile(Vector3<Integer> vec, float cost, boolean solid) {
        super(vec, cost, solid);
    }

    /**
     * Sets the traversing cost.
     *
     * @param cost float cost.
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * Sets the solidity flag.
     *
     * @param solid boolean flag.
     */
    public void setSolidity(boolean solid) {
        this.solid = solid;
    }

    /**
     * Attempts to set the tile's location to new position.
     * <br><bold>NOTE: </bold>doesn't move the block associated at the current location.
     *
     * @param x x-pos.
     * @param y y-pos.
     * @param z z-pos.
     * @return New Vector3i location.
     */
    public Vector3<Integer> setLocation(int x, int y, int z) {
        vec.x = x;
        vec.y = y;
        vec.z = z;

        return vec;
    }

    /**
     * Attempts to set the tile's location to new position.
     * <br><bold>NOTE: </bold>doesn't move the block associated at the current location.
     *
     * @param vec Vector3i location to set.
     * @return New Vector3i location.
     */
    public Vector3<Integer> setLocation(Vector3<Integer> vec) {
        if (vec == null) HCoreLibMain.logHelper.severe("Error attempting to set vector3!");
        else this.vec = vec;

        return vec;
    }

    /**
     * Sets flag for hazardous material. i.e. poison, lava, etc.
     *
     * @param hazardousTile boolean flag.
     */
    public void setHazardousTile(boolean hazardousTile) {
        this.hazardousTile = hazardousTile;
    }

    /**
     * Gets if hazardous material. i.e. poison, lava, etc.
     *
     * @return boolean flag.
     */
    public boolean isHazardousTile() {
        return hazardousTile;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (hazardousTile ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdvancedPathTile)) return false;
        if (!super.equals(obj)) return false;

        return hazardousTile == ((AdvancedPathTile) obj).hazardousTile;

    }

}
