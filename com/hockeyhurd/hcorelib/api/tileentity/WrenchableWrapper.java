package com.hockeyhurd.hcorelib.api.tileentity;

import com.hockeyhurd.hcorelib.api.item.IWrenchable;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hockeyhurd
 * @version 12/11/2017.
 */
public class WrenchableWrapper implements IWrenchable {

    private static final PropertyDirection FACING = BlockHorizontal.FACING;

    private TileEntity tileEntity;
    private boolean hasInv;
    private EnumFacing frontFacing;

    public WrenchableWrapper(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
        frontFacing = BlockUtils.getBlock(tileEntity.getWorld(), tileEntity.getPos()).getValue(FACING);
        hasInv = tileEntity instanceof IInventory;
    }

    @Override
    public EnumFacing getRotatedState(EnumFacing facingDir, IBlockState currentState) {
        if (facingDir == EnumFacing.DOWN || facingDir == EnumFacing.UP)
            return frontFacing;

        // return (frontFacing = frontFacing.rotateY());
        return (frontFacing = facingDir.getOpposite());
    }

    @Override
    public EnumFacing getCurrentFacing() {
        return frontFacing;
    }

    @Override
    public void setFrontFacing(EnumFacing face) {
        this.frontFacing = face;
    }

    @Override
    public boolean canRotateTE() {
        return true;
    }

    @Override
    public void onInteract(ItemStack stack, EntityPlayer player, World world, Vector3<Integer> vec) {

    }

    @Override
    public boolean canSaveDataOnPickup() {
        return false;
    }

    @Override
    public void readNBT(NBTTagCompound comp) {
        if (hasInv) {
            final IInventory inv = (IInventory) tileEntity;

            // this.slots = new ItemStack[inv.getSizeInventory()];
            NBTTagList tagList = comp.getTagList("Items", 10);

            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound temp = (NBTTagCompound) tagList.getCompoundTagAt(i);
                byte b0 = temp.getByte("Slot");

                if (b0 >= 0 && b0 < inv.getSizeInventory())
                    inv.setInventorySlotContents(b0, ItemStack.loadItemStackFromNBT(temp));
            }
        }
    }

    @Override
    public void saveNBT(NBTTagCompound comp) {
        if (hasInv && ((IInventory) tileEntity).getSizeInventory() > 0) {
            final IInventory inv = (IInventory) tileEntity;

            NBTTagList tagList = comp.getTagList("Items", 10);

            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (inv.getStackInSlot(i) != null) {
                    NBTTagCompound temp = new NBTTagCompound();
                    temp.setByte("Slot", (byte) i);
                    inv.getStackInSlot(i).writeToNBT(temp);
                    tagList.appendTag(temp);
                }
            }

            comp.setTag("Items", tagList);
        }
    }

    @Override
    public Vector3<Integer> worldVec() {
        return VectorHelper.toVector3i(tileEntity.getPos());
    }

    public static class WrenchableWrapperChecker {

        private static WrenchableWrapperChecker instance = new WrenchableWrapperChecker();

        private final Set<Class<? extends TileEntity>> validTileEntities;

        private WrenchableWrapperChecker() {
            validTileEntities = new HashSet<Class<? extends TileEntity>>(0x20, 2.0f / 3.0f);

            validTileEntities.add(TileEntityChest.class);
            validTileEntities.add(TileEntityFurnace.class);
        }

        public static WrenchableWrapperChecker getInstance() {
            return instance;
        }

        public boolean checkTileEntity(final Class<? extends TileEntity> clazz) {
            return clazz != null && validTileEntities.contains(clazz);
        }

        public WrenchableWrapper getWrenchable(TileEntity tileEntity) {
            if (tileEntity != null && validTileEntities.contains(tileEntity.getClass())) {
                return new WrenchableWrapper(tileEntity);
            }

            return null;
        }

    }

}
