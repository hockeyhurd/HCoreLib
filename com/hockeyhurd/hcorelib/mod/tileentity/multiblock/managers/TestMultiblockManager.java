package com.hockeyhurd.hcorelib.mod.tileentity.multiblock.managers;

import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;

import java.util.HashMap;

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
		if (masterTile == null) return false;

		final HashMap<String, Integer> multiblockMap = new HashMap<String, Integer>(getMaxSize() << 1, 2.0f / 3.0f);

		for (IMultiblockable block : blockList) {
			final String name = block.getTile().getInventoryName();

			if (multiblockMap.containsKey(name)) {
				final int count = multiblockMap.get(name) + 1;

				if (block.getRequiredAmount() != -1 && count > block.getRequiredAmount()) return false;
				else multiblockMap.put(name, count);
			}

			else multiblockMap.put(name, 1);
		}

		return !blockList.isEmpty();
	}

	@Override
	public int getMaxSize() {
		return 0x10;
	}

}
