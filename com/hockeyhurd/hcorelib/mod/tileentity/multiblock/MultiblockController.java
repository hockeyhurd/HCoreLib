package com.hockeyhurd.hcorelib.mod.tileentity.multiblock;

import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTileContainer;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.EnumMultiblockState;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.IMasterBlock;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.block.multiblock.BlockMultiblockController;
import com.hockeyhurd.hcorelib.mod.handler.packet.PacketController;
import com.hockeyhurd.hcorelib.mod.handler.packet.PacketHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
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
        // A list for checking if a child doesn't belong in our internal mapping.
        final List<BlockPos> removeList = new ArrayList<>(childrenComponents.size());

        for (Map.Entry<BlockPos, IMultiblockable<?>> entry : childrenComponents.entrySet()) {
            final TileEntity tileEntity = world.getTileEntity(entry.getKey());

            if (tileEntity != null && tileEntity instanceof IMultiblockable<?>) {

                // Finds the euclidian distance to block.
                final double distance = VectorHelper.toVector3i(tileEntity.getPos()).getNetDifference(worldVec());

                // If the |distance| is within 2.0, we know it is sqrt(2) which is allowed.
                if (Math.abs(distance) < 2.0d)
                    ((IMultiblockable) tileEntity).updateState(multiblockState);

                // Else we must remove the invalid child from the mapping via a remove list.
                else
                    removeList.add(tileEntity.getPos());
            }
        }

        // Remove invalid children from list of block positions pointing to invalid tile entities.
        for (BlockPos blockPos : removeList) {
            childrenComponents.remove(blockPos);
        }
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

	        // Too many children!!
	        if (childrenComponents.size() > 8) {
                HCoreLibMain.logHelper.warn("Too many children in internal mapping!", childrenComponents.size());
            }

	        checkIsCompleteMultiblock();

	        if (multiblockState == EnumMultiblockState.IN_COMPLETE) {
	            notifyChildren();
	            updateState(multiblockState);
            }

            else if (world.getTotalWorldTime() % 10 == 0) {
	            world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5d, pos.getY() + 1.0d, pos.getZ() + 0.5d, 0.0d, 1.0d, 0.0d, null);
                world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.getX() - 1.0d, pos.getY() + 1.0d, pos.getZ() - 1.0d, 0.0d, 0.5d, 0.0d, null);
	            world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.getX() + 2.0d, pos.getY() + 1.0d, pos.getZ() - 1.0d, 0.0d, 0.5d, 0.0d, null);
                world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.getX() - 1.0d, pos.getY() + 1.0d, pos.getZ() + 2.0d, 0.0d, 0.5d, 0.0d, null);
                world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.getX() + 2.0d, pos.getY() + 1.0d, pos.getZ() + 2.0d, 0.0d, 0.5d, 0.0d, null);
            }

            if (!world.isRemote) {
                PacketHandler.NETWORK_WRAPPER.sendToAllAround(new PacketController(this),
                        new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64.0d));
            }
        }
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        PacketHandler.NETWORK_WRAPPER.getPacketFrom(new PacketController(this));
        final NBTTagCompound comp = getUpdateTag();
        saveNBT(comp);

        return new SPacketUpdateTileEntity(pos, 1, comp);
    }

    @Override
    public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet) {
	    readNBT(packet.getNbtCompound());
	    BlockUtils.markBlockForUpdate(world, pos, BlockUtils.getBlock(world, pos));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readNBT(NBTTagCompound comp) {
        super.readNBT(comp);

        multiblockState = EnumMultiblockState.getStateFromBool(comp.getBoolean("MultiblockComplete"));

        if (childrenComponents == null)
            childrenComponents = new TreeMap<BlockPos, IMultiblockable<?>>();
    }

    @Override
    public void saveNBT(NBTTagCompound comp) {
        super.saveNBT(comp);

        comp.setBoolean("MultiblockComplete", multiblockState == EnumMultiblockState.COMPLETE);
    }
}
