package com.hockeyhurd.hcorelib.api.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;

/**
 * Abstract tile entity class for containers.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class AbstractTileContainer extends AbstractTile implements ISidedInventory {

    /**
     * Include only slots in the UI and specifically not the player's inventory.
     */
    protected ItemStack[] slots;

    /**
     * Initializes custom name to provided String.
     *
     * @param customName String custom name.
     */
    public AbstractTileContainer(String customName) {
        super(customName);
        this.customName = customName;

        initContentsArray();
        initSlotsArray();
    }

    /**
     * Initializes with custom name set to "container.generic".
     */
    public AbstractTileContainer() {
        this.customName = "container.container_generic";

        initContentsArray();
        initSlotsArray();
    }

    /**
     * Method used for initializing the contents array and size of container.
     */
    protected abstract void initContentsArray();

    /**
     * Method used for initializing slots in the container. <br>
     * NOTE: This is a helper method with its only purpose is to assist the dev and therefore may be left blank. See example below. <br>
     * <br>
     * EX. For a vanilla furnace, this would be: <br>
     * this.slots_bottom new int[] { 2, 1 }; <br>
     * this.slots_top new int[] { 0 }; <br>
     * this.slots_sides new int[] { 1 };
     */
    protected abstract void initSlotsArray();

    /**
     * Get the defined size of the inventory.
     *
     * @return inventory size as integer.
     */
    @Override
    public int getSizeInventory() {
        return slots != null ? slots.length : 0;
    }

    /**
     * Get the itemstack in slot defined.
     *
     * @param slot slot id.
     * @return itemstack in slot.
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot >= 0 && slot < this.slots.length ? this.slots[slot] : null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.slots[slot] != null) {
            ItemStack itemstack;

            if (this.slots[slot].stackSize <= amount) {
                itemstack = this.slots[slot];
                this.slots[slot] = null;
                return itemstack;
            }
            else {
                itemstack = this.slots[slot].splitStack(amount);

                if (this.slots[slot].stackSize == 0) this.slots[slot] = null;
                return itemstack;
            }
        }
        else return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.slots[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) stack.stackSize = this.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    /**
     * Method called when user opens inventory.
     */
    @Override
    public void openInventory(EntityPlayer player) {
    }

    /**
     * Method called when user closes inventory.
     */
    @Override
    public void closeInventory(EntityPlayer player) {
    }

    /**
     * Function determines if stated itemstack can be placed in slot defined.
     *
     * @param slot slot id.
     * @param stack stack to be placed.
     */
    public abstract boolean isItemValidForSlot(int slot, ItemStack stack);

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        if (slots != null && slot >= 0 && slot < slots.length) {
            ItemStack heldStack = slots[slot];
            slots[slot] = null;
            return heldStack;
        }

        return null;
    }

    @Override
    public abstract int getField(int id);

    @Override
    public abstract void setField(int id, int value);

    @Override
    public abstract int getFieldCount();

    @Override
    public void clear() {
        if (slots != null) {
            for (int i = 0; i < slots.length; i++) {
                slots[i] = null;
            }
        }
    }

    /**
     * Determines if side of block is accessible from side of said block.
     *
     * @param side side id.
     */
    @Override
    public abstract int[] getSlotsForFace(EnumFacing side);

    /**
     * Determines if said itemstack can be placed in slot from side defined.
     *
     * @param slot slot id.
     * @param stack stack to insert.
     * @param side side id.
     */
    @Override
    public abstract boolean canInsertItem(int slot, ItemStack stack, EnumFacing side);

    /**
     * Determines if said itemstack can be pulled from in slot to side defined.
     *
     * @param slot slot id.
     * @param stack stack to insert.
     * @param side side id.
     */
    @Override
    public abstract boolean canExtractItem(int slot, ItemStack stack, EnumFacing side);

    @Override
    public void readNBT(NBTTagCompound comp) {
        this.slots = new ItemStack[getSizeInventory()];
        NBTTagList tagList = comp.getTagList("Items", 10);

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound temp = (NBTTagCompound) tagList.getCompoundTagAt(i);
            byte b0 = temp.getByte("Slot");

            if (b0 >= 0 && b0 < this.slots.length) this.slots[b0] = ItemStack.loadItemStackFromNBT(temp);
        }

        if (comp.hasKey("CustomName")) this.customName = comp.getString("CustomName");
    }

    @Override
    public void saveNBT(NBTTagCompound comp) {
        if (this.slots != null && this.slots.length > 0) {
            NBTTagList tagList = comp.getTagList("Items", 10);

            for (int i = 0; i < this.slots.length; i++) {
                if (this.slots[i] != null) {
                    NBTTagCompound temp = new NBTTagCompound();
                    temp.setByte("Slot", (byte) i);
                    this.slots[i].writeToNBT(temp);
                    tagList.appendTag(temp);
                }
            }

            comp.setTag("Items", tagList);
        }

        if (this.hasCustomInventoryName()) comp.setString("CustomName", this.customName);
    }

}
