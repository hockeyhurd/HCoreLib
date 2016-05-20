package com.hockeyhurd.hcorelib.api.util;

import net.minecraft.block.Block;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static int getSizeInventory(Container container) {
        return container != null ? container.inventorySlots.size() : 0;
    }

    public static ItemStack[] getStacksInContainer(Container container) {
        final int size = getSizeInventory(container);

        if (size == 0) return new ItemStack[0];

        List<ItemStack> list = new ArrayList<ItemStack>(size);

        for (int i = 0; i < size; i++) {
            Slot slot = (Slot) container.inventorySlots;
            if (slot.getHasStack()) list.add(slot.getStack());
        }

        return list.toArray(new ItemStack[list.size()]);
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
     * @return int quantity of block.
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
     * Attempts to add an ItemStack to a container's inventory.
     *
     * @param container Container to reference.
     * @param addStack ItemStack to add to container.
     * @param mergeFirst boolean flag whether we can merge itemstacks (true) or
     *                   simply place in first empty slot (false).
     * @return int displaced after attempt.
     */
    public static int addByStack(Container container, ItemStack addStack, boolean mergeFirst) {
        if (container == null || addStack == null || addStack.stackSize < 1) return 0;

        final int invSize = getSizeInventory(container);
        final int startSize = addStack.stackSize;
        int amountLeft = startSize;

        // If allowed to merge:
        if (mergeFirst) {
            final HashMap<Integer, ItemStack> map = getMapOfItemStacks(container, addStack);

            // First pass, see if we can add to existing stack:
            for (Entry<Integer, ItemStack> entry : map.entrySet()) {
                if (amountLeft <= 0) break;

                ItemStack currentStack = entry.getValue();
                int stackSize = currentStack.stackSize;

                if (stackSize < currentStack.getMaxStackSize()) {
                    final int dif = currentStack.getMaxStackSize() - stackSize;
                    amountLeft -= dif;
                    stackSize += dif;
                    currentStack.stackSize = stackSize;

                    container.putStackInSlot(entry.getKey(), currentStack);
                }
            }
        }

        // Second pass, try to add remainder to empty slot:
        for (int i = 0; i < invSize; i++) {
            Slot slot = (Slot) container.inventorySlots.get(i);

            if (!slot.getHasStack()) {
                addStack.stackSize = amountLeft;
                amountLeft = 0;
                container.putStackInSlot(i, addStack);
                break;
            }
        }

        return startSize - amountLeft;
    }

    /**
     * Adds an array of ItemStacks to container.
     *
     * @param container Container to reference.
     * @param mergeFirst boolean flag whether we can merge itemstacks (true) or
     *                   simply place in first empty slot (false).
     * @param stacks ItemStacks to add to container.
     * @return int[] result where int[i] is the result of operation addByStack(..., ..., stacks[i]).
     */
    public static int[] addByStacks(Container container, boolean mergeFirst, ItemStack... stacks) {
        if (container == null || stacks == null || stacks.length == 0) return new int[0];

        int[] results = new int[stacks.length];

        for (int i = 0; i < stacks.length; i++) {
            results[i] = addByStack(container, stacks[i], mergeFirst);
        }

        return results;
    }

    /**
     * Attempts to remove n number of items or block contained in ItemStack to remove.
     *
     * @param container Container to reference.
     * @param stackToRemove ItemStack to remove.
     * @return int number of items/block removed.
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
     * Attempts to remove n number of block contained to remove.
     *
     * @param container Container to reference.
     * @param block Block to remove.
     * @param blocksToRemove int n number of block to remove.
     * @return int number of block removed.
     */
    public static int removeByBlock(Container container, Block block, int blocksToRemove) {
        if (container == null || block == null) return 0;
        if (blocksToRemove < 1) blocksToRemove = 1;

        return removeByStack(container, ItemUtils.createStack(block, blocksToRemove));
    }

}
