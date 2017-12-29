package com.hockeyhurd.hcorelib.mod.tileentity.multiblock;

import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTileContainer;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.EnumMultiblockState;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.IMasterBlock;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.mod.block.multiblock.BlockMultiblockController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author hockeyhurd
 * @version 7/13/2016.
 */
public class MultiblockController extends AbstractTileContainer implements IMasterBlock<MultiblockController>, ITickable {

    private EnumMultiblockState multiblockState;
    private Map<BlockPos, IMultiblockable<?>> childrenComponents;

	public MultiblockController() {
		super("multiblockController");

        multiblockState = EnumMultiblockState.IN_COMPLETE;
        childrenComponents = new TreeMap<BlockPos, IMultiblockable<?>>();
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
	public boolean canBeMaster() {
		return true;
	}

	@Override
	public boolean isMaster() {
		return canBeMaster();
	}

	@Override
	public void setMaster(IMasterBlock<?> tile) {
	}

	@Override
	public IMasterBlock<MultiblockController> getMaster() {
		return this;
	}

	@Override
	public int getRequiredAmount() {
		return 1;
	}

    @Override
    public void updateState(EnumMultiblockState multiblockState) {
        final IBlockState blockState = BlockUtils.getBlock(world, pos);

        ((BlockMultiblockController) blockState.getBlock()).updateState(this, world, worldVec(), multiblockState == EnumMultiblockState.COMPLETE);
    }

    @Override
    public boolean checkIsCompleteMultiblock() {
	    for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
            for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++) {
                final BlockPos blockPos = VectorHelper.toBlockPos(x, pos.getY(), z);

                if (x == pos.getX() && z == pos.getZ())
                    continue;

                final TileEntity tileEntity = world.getTileEntity(blockPos);
                if (!(tileEntity instanceof IMultiblockable<?>)) {
                    multiblockState = EnumMultiblockState.IN_COMPLETE;
                    return false;
                }

                else
                    childrenComponents.put(blockPos, (IMultiblockable<?>) tileEntity);
            }
        }

        multiblockState = EnumMultiblockState.COMPLETE;
        return true;
    }

    @Override
    public EnumMultiblockState getMultiblockState() {
        return multiblockState;
    }

    @Override
    public void setMultiblockState(EnumMultiblockState state) {
	    this.multiblockState = state;
    }

    @Override
    public void notifyChildren() {
        /*for (IMultiblockable<?> multiblockable : childrenComponents.values()) {
            multiblockable.updateState(multiblockState);
        }*/

        for (Map.Entry<BlockPos, IMultiblockable<?>> entry : childrenComponents.entrySet()) {
            final TileEntity tileEntity = world.getTileEntity(entry.getKey());

            if (tileEntity != null && tileEntity instanceof IMultiblockable<?>)
                ((IMultiblockable) tileEntity).updateState(multiblockState);
        }

        // updateState(multiblockState);
    }

    @Override
    public void addChild(IMultiblockable<?> child) {
        childrenComponents.put(child.getTile().getPos(), child);
    }

    @Override
    public void removeChild(IMultiblockable<?> child) {
        childrenComponents.remove(child.getTile().getPos());
    }

    @Override
    public Map<BlockPos, IMultiblockable<?>> getChildren() {
        return childrenComponents;
    }

    @Override
    public void update() {
	    if (multiblockState == EnumMultiblockState.COMPLETE) {
	        checkIsCompleteMultiblock();

	        if (multiblockState == EnumMultiblockState.IN_COMPLETE) {
	            notifyChildren();
	            updateState(multiblockState);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readNBT(NBTTagCompound comp) {
        super.readNBT(comp);

        multiblockState = EnumMultiblockState.getStateFromBool(comp.getBoolean("MultiblockComplete"));

        if (multiblockState == EnumMultiblockState.COMPLETE) {
            if (childrenComponents == null)
                childrenComponents = new TreeMap<BlockPos, IMultiblockable<?>>();
            else if (!childrenComponents.isEmpty())
                childrenComponents.clear();

            for (int count = comp.getInteger("Count"); count >= 0; count--) {
                BlockPos blockPos = new BlockPos(0, 0, 0);

                blockPos.add(comp.getInteger(String.format("ChildCompX:%d", count)), comp.getInteger(String.format("ChildCompY:%d", count)),
                        comp.getInteger(String.format("ChildCompZ:%d", count)));

                final TileEntity tileEntity = world.getTileEntity(blockPos);

                if (tileEntity != null && tileEntity instanceof IMultiblockable<?>)
                    childrenComponents.put(blockPos, (IMultiblockable<MultiblockComponent>) tileEntity);
            }

            checkIsCompleteMultiblock();
            notifyChildren();
            updateState(multiblockState);
        }
    }

    @Override
    public void saveNBT(NBTTagCompound comp) {
        super.saveNBT(comp);

        comp.setBoolean("MultiblockComplete", multiblockState == EnumMultiblockState.COMPLETE);

        if (multiblockState == EnumMultiblockState.COMPLETE) {
            int count = 0;

            for (BlockPos blockPos : childrenComponents.keySet()) {
                comp.setInteger(String.format("ChildCompX:%d", count), blockPos.getX());
                comp.setInteger(String.format("ChildCompY:%d", count), blockPos.getY());
                comp.setInteger(String.format("ChildCompZ:%d", count), blockPos.getZ());
                count++;
            }

            comp.setInteger("Count", count);
        }
    }
}
