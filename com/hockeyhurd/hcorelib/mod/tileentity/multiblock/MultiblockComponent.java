package com.hockeyhurd.hcorelib.mod.tileentity.multiblock;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTileContainer;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.EnumMultiblockState;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.IMasterBlock;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.mod.block.multiblock.BlockMultiblockComponent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

/**
 * @author hockeyhurd
 * @version 8/4/2016.
 */
public class MultiblockComponent extends AbstractTileContainer implements IMultiblockable<MultiblockComponent> {

	private IMasterBlock<MultiblockController> masterBlock;

	public MultiblockComponent() {
		super("multiblockComponent");
	}

	@Override
	protected void initContentsArray() {
        slots = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	}

	@Override
	protected void initSlotsArray() {

	}

	@Override
	public int getInventoryStackLimit() {
		return 0x40;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 0 && (slots.get(0) == ItemStack.EMPTY || (stack != ItemStack.EMPTY && slots.get(0).getCount() + stack.getCount() <= slots.get(0).getMaxStackSize()
                && slots.get(0).isItemEqual(stack)));
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
		return slot == 0 && slots.get(0) != ItemStack.EMPTY && slots.get(0).getCount() > 0;
	}

	@Override
	public void readNBT(NBTTagCompound comp) {
		super.readNBT(comp);

		final boolean hasMaster = comp.getBoolean("HasMaster");

		if (hasMaster) {
            final Vector3<Integer> vec = getMaster().getTile().worldVec();

            vec.x = comp.getInteger("MasterX");
            vec.y = comp.getInteger("MasterY");
            vec.z = comp.getInteger("MasterZ");

            final TileEntity tileEntity = world.getTileEntity(VectorHelper.toBlockPos(vec));

            if (tileEntity != null && tileEntity instanceof IMasterBlock<?>)
                setMaster((IMasterBlock<MultiblockController>) tileEntity);
        }
	}

	@Override
    public void saveNBT(NBTTagCompound comp) {
	    super.saveNBT(comp);

	    final boolean hasMaster = getMaster() != null;
	    comp.setBoolean("HasMaster", hasMaster);

	    if (hasMaster) {
	        final Vector3<Integer> vec = getMaster().getTile().worldVec();

	        comp.setInteger("MasterX", vec.x);
            comp.setInteger("MasterY", vec.y);
            comp.setInteger("MasterZ", vec.z);
        }
    }

	@Override
	public MultiblockComponent getTile() {
		return this;
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
    @SuppressWarnings("unchecked")
	public void setMaster(IMasterBlock<?> tile) {
	    masterBlock = (IMasterBlock<MultiblockController>) tile;
	}

	@Override
    @SuppressWarnings("unchecked")
	public IMasterBlock<MultiblockController> getMaster() {
		return masterBlock;
	}

	@Override
	public int getRequiredAmount() {
		return 8;
	}

    @Override
    public void updateState(EnumMultiblockState multiblockState) {
        final IBlockState blockState = BlockUtils.getBlock(world, pos);

        ((BlockMultiblockComponent) blockState.getBlock()).updateState(this, world, worldVec(), multiblockState == EnumMultiblockState.COMPLETE);
    }

}
