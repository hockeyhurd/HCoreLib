package com.hockeyhurd.hcorelib.mod.tileentity.multiblock;

import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockManager;
import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTileContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

/**
 * @author hockeyhurd
 * @version 8/4/2016.
 */
public class MultiblockComponent extends AbstractTileContainer implements IMultiblockable<MultiblockComponent> {

	private IMultiblockManager multiblockManager;
	private int internalSize;

	public MultiblockComponent() {
		super("multiblockComponent");
	}

	@Override
	protected void initContentsArray() {
	}

	@Override
	protected void initSlotsArray() {
		slots = new ItemStack[1];
	}

	@Override
	public int getInventoryStackLimit() {
		return 0x40;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 0 && (slots[0] == null || (stack != null && slots[0].stackSize + stack.stackSize <= slots[0].getMaxStackSize() &&
				slots[0].isItemEqual(stack)));
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
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing side) {
		return slot == 0 && slots[0] != null && slots[0].stackSize > 0;
	}

	/*@Override
	public ItemStack getStackInSlot(int slot) {
		return slot == 0 ? slots[0] : null;
	}*/

	public boolean putStack(ItemStack stack) {
		if (stack == null || !canInsertItem(0, stack, EnumFacing.UP)) return false;
		if (slots[0] == null) {
			slots[0] = stack.copy();
			return true;
		}

		else if (stack.isItemEqual(slots[0])) {
			int left = slots[0].getMaxStackSize() - slots[0].stackSize;
			left -= stack.stackSize;
			slots[0].stackSize += stack.stackSize;

			internalSize = slots[0].stackSize + left;
			return true;
		}

		return false;
	}

	public ItemStack pullStack(int amount) {
		if (amount < 0 || slots[0] == null) return null;
		final ItemStack copy = slots[0].copy();

		amount = Math.min(amount, slots[0].getMaxStackSize());

		int amountToPull = Math.min(slots[0].stackSize, amount);
		amountToPull = Math.min(amountToPull, internalSize);

		if (amountToPull < internalSize) {
			internalSize -= amountToPull;
			slots[0].stackSize = internalSize;
		}

		else if (amountToPull == internalSize) {
			internalSize = 0;
			slots[0] = null;
		}

		else return null; // Error should have occurred if this is reached!

		// Adjust stack size according to the amount of was pulled.
		copy.stackSize = amountToPull;

		return copy;
	}

	@Override
	public MultiblockComponent getTile() {
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
		return false;
	}

	@Override
	public boolean isMaster() {
		return false;
	}

	@Override
	public void setMaster(IMultiblockable<MultiblockComponent> tile) {
	}

	@Override
	public IMultiblockable getMaster() {
		return multiblockManager != null ? multiblockManager.getMasterTile() : null;
	}

	@Override
	public int getRequiredAmount() {
		return -1;
	}
}
