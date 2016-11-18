package com.hockeyhurd.hcorelib.mod.tileentity.multiblock;

import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockManager;
import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTileContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

/**
 * @author hockeyhurd
 * @version 7/13/2016.
 */
public class MultiblockController extends AbstractTileContainer implements IMultiblockable<MultiblockController> {

	private IMultiblockManager multiblockManager;
	private IMultiblockable<MultiblockController> master;

	public MultiblockController() {
		super("multiblockController");
	}

	@Override
	protected void initContentsArray() {
	}

	@Override
	protected void initSlotsArray() {
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[0];
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, EnumFacing side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing side) {
		return false;
	}

	@Override
	public MultiblockController getTile() {
		return this;
	}

	@Override
	public IMultiblockManager getManager() {
		return multiblockManager;
	}

	@Override
	public boolean setManager(IMultiblockManager manager) {
		this.multiblockManager = manager;
		return manager != null;
	}

	@Override
	public boolean canBeMaster() {
		return true;
	}

	@Override
	public boolean isMaster() {
		return canBeMaster() && master != null && master.getTile().worldVec().equals(worldVec());
	}

	@Override
	public void setMaster(IMultiblockable<MultiblockController> tile) {
		this.master = tile;
	}

	@Override
	public IMultiblockable<MultiblockController> getMaster() {
		return master;
	}

	@Override
	public int getRequiredAmount() {
		return 1;
	}

}