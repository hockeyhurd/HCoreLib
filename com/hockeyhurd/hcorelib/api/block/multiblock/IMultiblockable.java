package com.hockeyhurd.hcorelib.api.block.multiblock;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;

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
	 * Gets the associated manger responsible
	 * for handling the structures' behavior.
	 *
	 * @return The IMultiblockManager manager.
	 */
	IMultiblockManager getManager();

	/**
	 * Attempts to assign a manger to this tile.
	 *
	 * @param manager IMultiblockManager to assign.
	 */
	boolean setManager(IMultiblockManager manager);

	/**
	 * Gets if this tile can be a master.
	 *
	 * @return boolean result.
	 */
	boolean canBeMaster();

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
	void setMaster(IMultiblockable<T> tile);

	/**
	 * Gets the 'master' tile.
	 * @return
	 */
	IMultiblockable<T> getMaster();

	/**
	 * Gets the required number of tiles this multiblock
	 * needs for this to be accepted.
	 *
	 * <br><bold>NOTE: </bold>Use '-1' for any amount
	 * is applicable.
	 *
	 * @return int required amount.
	 */
	int getRequiredAmount();

}
