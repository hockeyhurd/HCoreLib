package com.hockeyhurd.api.util;

import net.minecraft.block.Block;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Class for centralized methods commonly used in get inventory data.
 *
 * @author hockeyhurd
 * @version 4/19/16
 */
public final class InventoryUtils {

    private InventoryUtils() {
    }

    /**
     * Gets the quantity of a particular itemstack in a container.
     *
     * @param container Container to reference.
     * @param itemStack ItemStack to find.
     * @return int quantity of itemstacks.
     */
    public static int getQuantityOfStack(Container container, ItemStack itemStack) {
        if (container == null || itemStack == null || itemStack.stackSize < 1) return 0;

        int sum = 0;

        for (Object obj : container.inventoryItemStacks) {
            if (obj instanceof ItemStack) {
                ItemStack stack = (ItemStack) obj;
                if (stack.isItemEqual(itemStack)) sum += stack.stackSize;
            }
        }

        return sum;
    }

    /**
     * Gets the quantity of a particular item in a container.
     *
     * @param container Container to reference.
     * @param item Item to find.
     * @return int quantity of items.
     */
    public static int getQuantityOfItem(Container container, Item item) {
        return container != null && item != null ? getQuantityOfStack(container, ItemUtils.createStack(item)) : 0;
    }

    /**
     * Gets the quantity of a particular block in a container.
     *
     * @param container Container to reference.
     * @param block Block to find.
     * @return int quantity of blocks.
     */
    public static int getQuantityOfBlock(Container container, Block block) {
        return container != null && block != null ? getQuantityOfStack(container, ItemUtils.createStack(block)) : 0;
    }

    /**
     * Gets a mapping of the current container with itemstack indexes and stack reference.
     *
     * @param container Container to reference.
     * @param itemStack ItemStack to find.
     * @return HashMap of current container.
     */
    public static HashMap<Integer, ItemStack> getMapOfItemStacks(Container container, ItemStack itemStack) {
        if (container == null || itemStack == null || itemStack.stackSize < 1) return null;

        HashMap<Integer, ItemStack> map = new HashMap<Integer, ItemStack>(container.inventorySlots.size());

        for (int i = 0; i < container.inventorySlots.size(); i++) {
            Slot slot = (Slot) container.inventorySlots.get(i);
            ItemStack stack = slot.getStack();
            if (itemStack.isItemEqual(stack)) map.put(i, stack);
        }

        return map;
    }

    /**
     * Attempts to remove n number of items or blocks contained in ItemStack to remove.
     *
     * @param container Container to reference.
     * @param stackToRemove ItemStack to remove.
     * @return int number of items/blocks removed.
     */
    public static int removeByStack(Container container, ItemStack stackToRemove) {
        if (container == null || stackToRemove == null || stackToRemove.stackSize < 1) return 0;

        HashMap<Integer, ItemStack> map = getMapOfItemStacks(container, stackToRemove);

        final int itemsAtStart = stackToRemove.stackSize;
        int itemsLeft = itemsAtStart;

        for (Entry<Integer, ItemStack> entry : map.entrySet()) {
            if (itemsLeft <= 0) break;

            int stackSize = entry.getValue().stackSize;

            if (itemsLeft >= stackSize) {
                container.putStackInSlot(entry.getKey(), null);
                itemsLeft -= stackSize;
            }

            else {
                stackSize -= itemsLeft;
                itemsLeft = 0;

                entry.getValue().stackSize = stackSize;
                container.putStackInSlot(entry.getKey(), entry.getValue());
            }
        }

        return itemsAtStart - itemsLeft;
    }

    /**
     * Attempts to remove n number of items contained to remove.
     *
     * @param container Container to reference.
     * @param item Item to remove.
     * @param itemsToRemove int n number of items to remove.
     * @return int number of items removed.
     */
    public static int removeByItem(Container container, Item item, int itemsToRemove) {
        if (container == null || item == null) return 0;
        if (itemsToRemove < 1) itemsToRemove = 1;

        return removeByStack(container, ItemUtils.createStack(item, itemsToRemove));
    }

    /**
     * Attempts to remove n number of blocks contained to remove.
     *
     * @param container Container to reference.
     * @param block Block to remove.
     * @param blocksToRemove int n number of blocks to remove.
     * @return int number of blocks removed.
     */
    public static int removeByBlock(Container container, Block block, int blocksToRemove) {
        if (container == null || block == null) return 0;
        if (blocksToRemove < 1) blocksToRemove = 1;

        return removeByStack(container, ItemUtils.createStack(block, blocksToRemove));
    }

}
