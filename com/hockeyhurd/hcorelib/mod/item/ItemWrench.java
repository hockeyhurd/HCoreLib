package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.api.item.IWrench;
import com.hockeyhurd.hcorelib.api.item.IWrenchable;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
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
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing side,
                                           float hitX, float hitY, float hitZ, EnumHand hand) {

        player.swingArm(hand);
        IBlockState blockState = BlockUtils.getBlock(world, blockPos);
        if (blockState.getBlock() != Blocks.AIR) {
            if (world.isRemote)
                return EnumActionResult.PASS;

            final IWrenchable wrenchable = (IWrenchable) world.getTileEntity(blockPos);
            if (wrenchable == null)
                return EnumActionResult.FAIL;
        }

        return EnumActionResult.PASS;
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
