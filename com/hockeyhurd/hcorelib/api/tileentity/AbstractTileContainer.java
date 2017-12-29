package com.hockeyhurd.hcorelib.api.tileentity;

import com.hockeyhurd.hcorelib.api.util.StringUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

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
    protected NonNullList<ItemStack> slots;

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

    @Override
    public String getName() {
        return customName;
    }

    @Override
    public boolean hasCustomName() {
        return StringUtils.nullCheckString(customName);
    }

    /**
     * Method used for initializing the contents array and size of container.
     */
    protected void initContentsArray() {
        if (getSizeInventory() > 0) {
            slots = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
        }
    }

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
        return slots != null ? slots.size() : 0;
    }

    protected boolean isInventoryEmpty() {
        for (ItemStack stack : slots) {
            if (!stack.isEmpty())
                return false;
        }

        return true;
    }

    @Override
    public boolean isEmpty() {
        return isInventoryEmpty();
    }

    /**
     * Get the itemstack in slot defined.
     *
     * @param slot slot id.
     * @return itemstack in slot.
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot >= 0 && slot < this.slots.size() ? this.slots.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.slots.get(slot) != ItemStack.EMPTY) {
            ItemStack itemstack = ItemStack.EMPTY;

            if (this.slots.get(slot).getCount() <= amount) {
                itemstack = this.slots.get(slot);
                this.slots.set(slot, ItemStack.EMPTY);
                return itemstack;
            }
            else {
                itemstack = this.slots.get(slot).splitStack(amount);

                if (this.slots.get(slot).getCount() == 0)
                    this.slots.set(slot, ItemStack.EMPTY);

                return itemstack;
            }
        }
        else return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.slots.set(slot, stack);

        if (stack != ItemStack.EMPTY && stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
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
        if (slots != null && slot >= 0 && slot < slots.size()) {
            final ItemStack heldStack = slots.get(slot);
            slots.set(slot, ItemStack.EMPTY);

            return heldStack;
        }

        return ItemStack.EMPTY;
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
            for (int i = 0; i < slots.size(); i++) {
                slots.set(i, ItemStack.EMPTY);
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
        // this.slots = new ItemStack[getSizeInventory()];
        slots = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < slots.size(); i++) {
            // slots[i] = ItemStack.EMPTY;
            slots.set(i, ItemStack.EMPTY);
        }

        NBTTagList tagList = comp.getTagList("Items", 10);

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound temp = tagList.getCompoundTagAt(i);
            byte b0 = temp.getByte("Slot");

            if (b0 >= 0 && b0 < slots.size())
                slots.set(b0, new ItemStack(temp));
        }

        if (comp.hasKey("CustomName"))
            this.customName = comp.getString("CustomName");
    }

    @Override
    public void saveNBT(NBTTagCompound comp) {
        if (slots != null && !slots.isEmpty()) {
            NBTTagList tagList = comp.getTagList("Items", 10);

            for (int i = 0; i < slots.size(); i++) {
                if (slots.get(i) != ItemStack.EMPTY) {
                    NBTTagCompound temp = new NBTTagCompound();
                    temp.setByte("Slot", (byte) i);
                    slots.get(i).writeToNBT(temp);
                    tagList.appendTag(temp);
                }
            }

            comp.setTag("Items", tagList);
        }

        if (hasCustomInventoryName())
            comp.setString("CustomName", this.customName);
    }

}
