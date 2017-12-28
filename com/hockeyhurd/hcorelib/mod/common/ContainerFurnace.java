package com.hockeyhurd.hcorelib.mod.common;

import com.hockeyhurd.hcorelib.mod.tileentity.TileFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

/**
 * Container for TileFurnace.
 *
 * @author hockeyhurd
 * @version 3/14/17
 */
public class ContainerFurnace extends Container {

    private final InventoryPlayer playerInventory;
    private final TileFurnace tileFurnace;

    public ContainerFurnace(InventoryPlayer playerInventory, TileFurnace tileFurnace) {
        this.playerInventory = playerInventory;
        this.tileFurnace = tileFurnace;

        addSlots();
    }

    private void addSlots() {
        this.addSlotToContainer(new Slot(tileFurnace, 0, 56, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(tileFurnace, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, tileFurnace, 2, 116, 35));

        // Adds the player inventory to furnace's gui.
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Adds the player hotbar slots to the gui.
        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142)); // 198
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    /**
     * Player shift-clicking a slot.
     * @see net.minecraft.inventory.Container#transferStackInSlot(net.minecraft.entity.player.EntityPlayer, int)
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            if (index < tileFurnace.getSizeInventory()) {
                if (!this.mergeItemStack(slotStack, tileFurnace.getSizeInventory(), this.inventorySlots.size(), false))
                    return ItemStack.EMPTY;
            }

            else {
                if (!this.getSlot(0).isItemValid(slotStack) || !this.mergeItemStack(slotStack, 0, tileFurnace.getSizeInventory(), false))
                    return ItemStack.EMPTY;
            }

            if (slotStack.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (slotStack.getCount() == stack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(player, slotStack);
        }

        return stack;
    }

}
