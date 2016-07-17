package com.hockeyhurd.hcorelib.mod.tileentity.multiblock.managers;

import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;

/**
 * @author hockeyhurd
 * @version 7/17/2016.
 */
public class TestMultiblockManager extends GenericMultiblockManager {

	@SuppressWarnings("unchecked")
	private static final Class<? extends IMultiblockable>[] classes = new Class[] { MultiblockController.class };

	public TestMultiblockManager(IMultiblockable masterTile) {
		super(masterTile);
	}

	@Override
	public Class<? extends IMultiblockable>[] getAcceptedTiles() {
		return classes;
	}

	@Override
	public boolean isCompleteMultiblock() {
		return false;
	}

}
