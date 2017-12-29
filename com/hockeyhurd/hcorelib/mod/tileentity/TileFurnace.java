package com.hockeyhurd.hcorelib.mod.tileentity;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTileContainer;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.mod.block.TestFurnace;
import com.hockeyhurd.hcorelib.mod.handler.packet.PacketFurnace;
import com.hockeyhurd.hcorelib.mod.handler.packet.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * @author hockeyhurd
 * @version 3/14/17
 */
public class TileFurnace extends AbstractTileContainer implements ITickable {

    private EnumFacing frontFacing = EnumFacing.NORTH;
    private boolean active;

    private int[] slotTop, slotBottom, slotInput, slotRight;
    private int cookTime;
    private int currentCookTime;
    private int burnTime;
    private int currentBurnTime;
    public static final int defaultCookTime = 200;
    private int scaledTime = (defaultCookTime / 10) * 5;
    private int originalScaledTime = scaledTime;

    public TileFurnace() {
        super("container.tileFurnace");
        active = false;
    }

    @Override
    protected void initContentsArray() {
        slots = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    }

    @Override
    protected void initSlotsArray() {
        this.slotTop = new int[] { 0
        };
        this.slotRight = new int[] { 1
        };
        this.slotBottom = new int[] { 2
        };
    }

    @Override
    public int getInventoryStackLimit() {
        return 0x40;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot == 2)
            return false;
        else if (slot != 1)
            return true;

        else {
            final ItemStack itemStack = slots.get(1);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && (itemStack == ItemStack.EMPTY || itemStack.getItem() != Items.BUCKET);
        }
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return burnTime;
            case 1:
                return currentBurnTime;
            case 2:
                return cookTime;
            case 3:
                return currentCookTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                burnTime = value;
                break;
            case 1:
                currentBurnTime = value;
                break;
            case 2:
                cookTime = value;
                break;
            case 3:
                currentCookTime = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
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
        return slot > 0 && slot < slots.size();
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int i) {
        // return cookTime > 0 && scaledTime > 0 ? cookTime * i / scaledTime : cookTime == 0 ? 0 : cookTime > 0 ? 1 : 0;
        return cookTime > 0 && currentCookTime > 0 ? (int) (cookTime * i * 10 / (float) currentCookTime) : 0;
    }

    private boolean canSmelt() {
        if (slots.get(0) == ItemStack.EMPTY)
            return false;
        else {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(slots.get(0));
            if (itemstack == ItemStack.EMPTY)
                return false;
            if (slots.get(2) == ItemStack.EMPTY)
                return true;
            if (!slots.get(2).isItemEqual(itemstack))
                return false;

            int result = slots.get(2).getCount() + itemstack.getCount();
            return result <= getInventoryStackLimit() && result <= slots.get(2).getMaxStackSize(); // Forge BugFix: Make it respect stack sizes properly.
        }
    }

    private void smeltItem() {
        if (canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(slots.get(0));

            if (slots.get(2) == ItemStack.EMPTY)
                slots.set(2, itemstack.copy());
            else if (slots.get(2).isItemEqual(itemstack))
                slots.get(2).setCount(slots.get(2).getCount() + itemstack.getCount());

            slots.get(0).setCount(slots.get(0).getCount() - 1);

            if (slots.get(0).getCount() <= 0) {
                slots.set(0, ItemStack.EMPTY);
            }
        }
    }

    public static int getItemBurnTime(ItemStack stack) {
        if (stack == ItemStack.EMPTY) {
            return 0;
        }
        else {
            Item item = stack.getItem();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
                Block block = Block.getBlockFromItem(item);
                if (block == Blocks.WOODEN_SLAB) {
                    return 150;
                }

                if (block.getDefaultState().getMaterial() == Material.WOOD) {
                    return 300;
                }

                if (block == Blocks.COAL_BLOCK) {
                    return 16000;
                }
            }

            return item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName()) ?
                    200 :
                    (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName()) ?
                            200 :
                            (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName()) ?
                                    200 :
                                    (item == Items.STICK ?
                                            100 :
                                            (item == Items.COAL ?
                                                    1600 :
                                                    (item == Items.LAVA_BUCKET ?
                                                            20000 :
                                                            (item == Item.getItemFromBlock(Blocks.SAPLING) ?
                                                                    100 :
                                                                    (item == Items.BLAZE_ROD ? 2400 : GameRegistry.getFuelValue(stack))))))));
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    @Override
    public void update() {
        boolean flag = isActive();
        boolean flag1 = false;

        if (isActive()) {
            --burnTime;
        }

        if (!world.isRemote) {
            ItemStack itemstack = slots.get(1);

            if (isActive() || !itemstack.isEmpty() && !((ItemStack) slots.get(0)).isEmpty()) {
                if (!isActive() && canSmelt()) {
                    burnTime = getItemBurnTime(itemstack);
                    currentBurnTime = burnTime;

                    if (isActive()) {
                        flag1 = true;

                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                slots.set(1, item1);
                            }
                        }
                    }
                }

                if (isActive() && canSmelt()) {
                    ++cookTime;

                    if (cookTime == scaledTime) {
                        cookTime = 0;
                        currentCookTime = getCookTime();
                        smeltItem();
                        flag1 = true;
                    }
                }
                else {
                    cookTime = 0;
                }
            }
            else if (!isActive() && cookTime > 0) {
                cookTime = MathHelper.clamp(cookTime - 2, 0, currentCookTime);
            }

            if (flag != isActive()) {
                flag1 = true;
                ((TestFurnace) blockType).updateState(isActive(), world, pos);
            }
        }

        if (flag1) {
            markDirty();
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos blockPos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    /**
     * Gets the cook time.
     *
     * @return int.
     */
    public int getCookTime() {
        return scaledTime; // cookTime;
    }

    /**
     * Gets the burn time.
     *
     * @return int.
     */
    public int getBurnTime() {
        return burnTime;
    }

    /**
     * Sets the cooktime.
     *
     * @param cookTime int cook time.
     */
    @SideOnly(Side.CLIENT)
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        PacketHandler.NETWORK_WRAPPER.getPacketFrom(new PacketFurnace(this));
        final NBTTagCompound comp = getUpdateTag();
        saveNBT(comp);
        return new SPacketUpdateTileEntity(pos, 1, comp);
    }

    @Override
    public void onDataPacket(NetworkManager manger, SPacketUpdateTileEntity packet) {
        readNBT(packet.getNbtCompound());
        BlockUtils.markBlockForUpdate(world, pos, BlockUtils.getBlock(world, pos));
        // world.notifyBlockUpdate(pos, bl);
    }

    @Override
    public void readNBT(NBTTagCompound comp) {
        super.readNBT(comp);

        frontFacing = EnumFacing.getFront(comp.getInteger("frontFacing"));
        cookTime = comp.getInteger("cookTime");
        currentCookTime = comp.getInteger("currentCookTime");
        burnTime = comp.getInteger("burnTime");
        currentBurnTime = comp.getInteger("currentBurnTime");
        scaledTime = comp.getInteger("scaledTime");
        originalScaledTime = comp.getInteger("originalScaledTime");
        active = comp.getBoolean("active");
    }

    @Override
    public void saveNBT(NBTTagCompound comp) {
        super.saveNBT(comp);

        comp.setInteger("frontFacing", frontFacing.getIndex());
        comp.setInteger("cookTime", cookTime);
        comp.setInteger("currentCookTime", currentCookTime);
        comp.setInteger("burnTime", burnTime);
        comp.setInteger("currentBurnTime", currentBurnTime);
        comp.setInteger("scaledTime", scaledTime);
        comp.setInteger("originalScaledTime", originalScaledTime);
        comp.setBoolean("active", active);
    }

    public EnumFacing getFrontFacing() {
        return frontFacing;
    }

    public void setFrontFacing(EnumFacing frontFacing) {
        this.frontFacing = frontFacing;
    }

    public boolean isActive() {
        return (active = getBurnTime() > 0);
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory) {
        return inventory.getField(0) > 0;
    }

}
