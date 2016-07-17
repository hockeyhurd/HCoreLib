package com.hockeyhurd.hcorelib.mod.tileentity.multiblock.managers;

import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockManager;
import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.math.Vector3;

import java.util.LinkedList;
import java.util.List;

/**
 * @author hockeyhurd
 * @version 7/13/2016.
 */
public abstract class GenericMultiblockManager implements IMultiblockManager {

	protected IMultiblockable masterTile;
	protected final List<IMultiblockable> blockList;

	public GenericMultiblockManager(IMultiblockable masterTile) {
		if (masterTile == null) throw new NullPointerException("Master tile to be set is NULL!");

		this.masterTile = masterTile;
		blockList = new LinkedList<IMultiblockable>();
		blockList.add(masterTile);
	}

	@Override
	public abstract Class<? extends IMultiblockable>[] getAcceptedTiles();

	@Override
	public boolean canAddTile(IMultiblockable tile) {
		for (Class<? extends IMultiblockable> clazz : getAcceptedTiles())
			if (clazz.isInstance(tile)) return true;


		return false;
	}

	@Override
	public boolean containsTile(IMultiblockable tile) {
		if (tile == null || blockList.isEmpty()) return false;

		final Vector3<Integer> tileVec = tile.getTile().worldVec();

		for (IMultiblockable other : blockList)
			if (other.getTile().worldVec().equals(tileVec)) return true;

		return false;
	}

	@Override
	public void addTile(IMultiblockable tile) {
		if (tile != null) blockList.add(tile);
	}

	@Override
	public void addAllTiles(IMultiblockable[] tiles) {
		if (tiles == null || tiles.length == 0) return;

		for (IMultiblockable tile : tiles)
			addTile(tile);
	}

	@Override
	public void removeTile(IMultiblockable tile) {
		if (tile == null || blockList.isEmpty()) return;

		final Vector3<Integer> tileVec = tile.getTile().worldVec();
		final boolean needsNewMaster = masterTile == tile && masterTile.getTile().worldVec().equals(tileVec);

		int counter = 0;
		for (IMultiblockable other : blockList) {
			if (other.getTile().worldVec().equals(tileVec)) {
				blockList.remove(counter);
				break;
			}

			counter++;
		}

		// Handle assigning a new master:
		if (needsNewMaster) {
			masterTile = null;

			// Find new master:
			for (IMultiblockable tileInList : blockList) {
				if (tileInList.canBeMaster()) {
					masterTile = tileInList;
					break;
				}
			}

			// Assign new master:
			if (masterTile != null) {
				for (IMultiblockable tileInList : blockList)
					tileInList.setMaster(masterTile);
			}

			// No applicable master :(
			// else return; // Will this work?
		}
	}

	@Override
	public List<IMultiblockable> getMultiblockTiles() {
		return blockList;
	}

	@Override
	public boolean mergeSubMultiblocks(IMultiblockManager other) {
		if (other == null) return false;

		for (IMultiblockable tile : other.getMultiblockTiles()) {
			if (tile == null) return false;

			blockList.add(tile);
			tile.setMaster(masterTile);
			tile.setManager(this);
		}

		return true;
	}

	@Override
	public abstract boolean isCompleteMultiblock();

	@Override
	public int size() {
		return blockList.size();
	}

	@Override
	public int compareTo(IMultiblockManager other) {
		if (this == other || equals(other)) return 0;
		if (other instanceof GenericMultiblockManager)
			return blockList.size() > other.size() ? 1 : blockList.size() < other.size() ? -1 : 0;

		return other.compareTo(this);
	}

}
