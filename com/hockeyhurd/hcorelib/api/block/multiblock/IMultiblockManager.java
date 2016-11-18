package com.hockeyhurd.hcorelib.api.block.multiblock;

import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

/**
 * @author hockeyhurd
 * @version 7/13/2016.
 */
public interface IMultiblockManager extends Comparable<IMultiblockManager> {

	// IMultiblockable[] getAcceptedTiles();
	Class<? extends IMultiblockable>[] getAcceptedTiles();

	boolean canAddTile(IMultiblockable tile);

	boolean containsTile(IMultiblockable tile);

	void addTile(IMultiblockable tile);

	void addAllTiles(IMultiblockable[] tiles);

	void removeTile(IMultiblockable tile);

	List<IMultiblockable> getMultiblockTiles();

	boolean mergeSubMultiblocks(IMultiblockManager other);

	boolean isCompleteMultiblock();

	IMultiblockable getMasterTile();

	int size();

	int getMaxSize();

	@Override
	int compareTo(IMultiblockManager other);

	void readNBT(NBTTagCompound comp, IMultiblockable tile);

	void saveNBT(NBTTagCompound comp, IMultiblockable tile);

}