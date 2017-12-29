package com.hockeyhurd.hcorelib.api.tileentity.multiblock;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;

/**
 * Interfacing for multiblockable tileentities.
 *
 * @author hockeyhurd
 * @version 7/13/2016.
 */
public interface IMultiblockable<T extends AbstractTile> {

    /**
     * Gets the tile's instance.
     *
     * @return Tile instance.
     */
    T getTile();

    /**
     * Gets if this tile can be a master.
     *
     * @return boolean result.
     */
    default boolean canBeMaster() {
        return false;
    }

    /**
     * Gets if this tile is to be the master
     * tile of the multiblock.
     *
     * @return boolean result.
     */
    boolean isMaster();

    /**
     * Assigns master for tracking purposes.
     *
     * @param tile Tile to set.
     */
    void setMaster(IMasterBlock<?> tile);

    /**
     * Gets the 'master' tile.
     *
     * @return
     */
    <T extends IMasterBlock> T getMaster();

    /**
     * Gets the required number of tiles this multiblock
     * needs for this to be accepted.
     * <p>
     * <br><bold>NOTE: </bold>Use '-1' for any amount
     * is applicable.
     *
     * @return int required amount.
     */
    int getRequiredAmount();

    void updateState(EnumMultiblockState multiblockState);

}
