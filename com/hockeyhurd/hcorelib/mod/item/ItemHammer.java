package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.IMasterBlock;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author hockeyhurd
 * @version 12/28/2017.
 */
public class ItemHammer extends AbstractHCoreItem implements ICraftableRecipe {

    private static RecipePattern[] recipePatterns;

    public ItemHammer(String name) {
        super(HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);

        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos blockPos,
            EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {

        player.swingArm(hand);
        IBlockState blockState = BlockUtils.getBlock(world, blockPos);
        if (blockState.getBlock() != Blocks.AIR) {
            if (world.isRemote)
                return EnumActionResult.PASS;

            // final ItemStack stack = player.getHeldItem(hand);
            final TileEntity tileEntity = world.getTileEntity(blockPos);

            if (tileEntity == null || !(tileEntity instanceof IMasterBlock))
                return EnumActionResult.FAIL;

            final IMasterBlock<?> masterBlock = (IMasterBlock<?>) tileEntity;
            masterBlock.checkIsCompleteMultiblock();
            masterBlock.notifyChildren();
            masterBlock.updateState(masterBlock.getMultiblockState());

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }

    @Override
    public RecipePattern[] getRecipePatterns() {
        if (recipePatterns == null) {
            recipePatterns = new RecipePattern[1];

            recipePatterns[0] = new RecipePattern("iii", "isi", " s ", true).addAssociation('i', "ingotIron").addAssociation('s',
                    "stick").setResultStack(new ItemStack(this, 1));
        }

        return recipePatterns;
    }
}
