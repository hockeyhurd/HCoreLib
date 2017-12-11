package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.api.item.IWrench;
import com.hockeyhurd.hcorelib.api.item.IWrenchable;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.WorldUtils;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author hockeyhurd
 * @version 12/5/17
 */
public class ItemWrench extends AbstractHCoreItem implements ICraftableRecipe, IWrench {

    private static RecipePattern[] recipePatterns;

    /**
     * @param name String name of Item.
     */
    public ItemWrench(String name) {
        super(HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);

        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos,
        EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {

        player.swingArm(hand);
        IBlockState blockState = BlockUtils.getBlock(world, blockPos);
        if (blockState.getBlock() != Blocks.AIR) {
            if (world.isRemote)
                return EnumActionResult.PASS;

            final IWrenchable wrenchable = (IWrenchable) world.getTileEntity(blockPos);
            if (wrenchable == null)
                return EnumActionResult.FAIL;

            if (!player.isSneaking()) {
                normalWrench(stack, player, world, blockPos, blockState, wrenchable, side);
            }

            else if (player.isSneaking()) {
                sneakWrench(stack, player, world, blockPos, blockState, wrenchable, side);
            }

            wrenchable.onInteract(stack, player, world, VectorHelper.toVector3i(blockPos));

            return EnumActionResult.PASS;
        }

        return EnumActionResult.FAIL;
    }

    protected static Rotation getRotation(EnumFacing ref, EnumFacing currentFacing) {
        if (ref == currentFacing)
            return Rotation.CLOCKWISE_180;

        final int refIndex = ref.getHorizontalIndex();
        final int currentFacingIndex = currentFacing.getHorizontalIndex();
        final int netDif = Math.abs(refIndex - currentFacingIndex);

        if (refIndex > currentFacingIndex) {
            if (netDif == 1)
                return Rotation.COUNTERCLOCKWISE_90;
            else if (netDif == 2)
                return Rotation.CLOCKWISE_180;

            return Rotation.CLOCKWISE_90;
        }

        else {
            if (netDif == 1)
                return Rotation.CLOCKWISE_90;
            else if (netDif == 2)
                return Rotation.CLOCKWISE_180;

            return Rotation.COUNTERCLOCKWISE_90;
        }
    }

    @Override
    public void sneakWrench(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, IBlockState blockState,
                            IWrenchable wrenchable, EnumFacing sideHit) {

        ItemStack itemToDrop = new ItemStack(blockState.getBlock(), 1);

        if (wrenchable.canSaveDataOnPickup()) {
            NBTTagCompound comp;
            if (itemToDrop.hasTagCompound())
                comp = itemToDrop.getTagCompound();
            else {
                comp = new NBTTagCompound();
                itemToDrop.setTagCompound(comp);
            }

            wrenchable.saveNBT(comp);
        }

        BlockUtils.setBlockToAir(world, blockPos);
        WorldUtils.addItemDrop(itemToDrop, world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    @Override
    public void normalWrench(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, IBlockState blockState,
                             IWrenchable wrenchable, EnumFacing sideHit) {

        // TODO: Update rotation code, again...
        if ((blockState = blockState.getBlock().withRotation(blockState, getRotation(sideHit.getOpposite(),
                wrenchable.getCurrentFacing()))) != null) {

            BlockUtils.setBlock(world, blockPos, blockState, 2);
            BlockUtils.updateAndNotifyNeighborsOfBlockUpdate(world, blockPos);
        }

        wrenchable.setFrontFacing(sideHit.getOpposite());
    }

    @Override
    public RecipePattern[] getRecipePatterns() {
        if (recipePatterns == null) {
            recipePatterns = new RecipePattern[0];

            recipePatterns[0] = new RecipePattern("i i", "iii", " i ", true).addAssociation('i',
                    "ingotIron").setResultStack(new ItemStack(this, 1));
        }

        return recipePatterns;
    }
}
