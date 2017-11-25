package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.ChatUtils;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author hockeyhurd
 * @version 11/23/17
 */
public final class ItemBuildersWand extends AbstractHCoreItem implements ICraftableRecipe {

    private static RecipePattern[] recipePatterns;

    /**
     * @param name String name of Item.
     */
    public ItemBuildersWand(String name) {
        super(HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);
    }

    @Override
    public RecipePattern[] getRecipePatterns() {
        if (recipePatterns == null) {
            recipePatterns = new RecipePattern[1];

            recipePatterns[0] = new RecipePattern(" dd", " sd", "s  ", true).addAssociation('s', "stickWood").addAssociation('d',
                    "gemDiamond").setResultStack(new ItemStack(this, 1));
        }

        return recipePatterns;
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
                                      EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {
            final Vector3<Integer> vec = VectorHelper.toVector3i(pos);
            player.addChatComponentMessage(ChatUtils.createComponent("Right click @: " + vec.toString()));
            player.addChatComponentMessage(ChatUtils.createComponent("Facing: " + facing.getName()));
            // player.addChatComponentMessage(ChatUtils.createComponent("Offset X: " + facing.getFrontOffsetX()));
            // player.addChatComponentMessage(ChatUtils.createComponent("Offset Y: " + facing.getFrontOffsetY()));
            // player.addChatComponentMessage(ChatUtils.createComponent("Offset Z: " + facing.getFrontOffsetZ()));
            player.addChatComponentMessage(ChatUtils.createComponent("Axis : " + facing.getAxis().getName()));

            return placeBlocksAlongAxis(world, vec, facing);
        }

        return placeBlocksAlongAxis(world, VectorHelper.toVector3i(pos), facing);

        // return EnumActionResult.PASS;
    }

    private static EnumActionResult placeBlocksAlongAxis(World world, Vector3<Integer> pos, EnumFacing facing) {
        final IBlockState blockLookingAt = BlockUtils.getBlock(world, pos);
        final int MAX_DIST = 3;
        final Map<BlockPos, IBlockState> blocksToPlace = new TreeMap<BlockPos, IBlockState>();

        switch (facing.getAxis()) {
            case X:
                break;
            case Y:
                break;
            case Z:
                final Vector3<Integer> currentPos = pos.copy();

                for (int y = -MAX_DIST; y <= MAX_DIST; y++) {
                    for (int x = -MAX_DIST; x <= MAX_DIST; x++) {
                        currentPos.x = pos.x + x;
                        currentPos.y = pos.y + y;

                        final IBlockState currentBlock = BlockUtils.getBlock(world, currentPos);

                        if (currentBlock.equals(blockLookingAt)) {
                            blocksToPlace.put(new BlockPos(currentPos.x, currentPos.y, currentPos.z + facing.getFrontOffsetZ()),
                                    currentBlock);
                        }
                    }
                }

                break;
            default:
                HCoreLibMain.logHelper.severe("Critical error! Invalid operation for builder's wand!!");
                return EnumActionResult.FAIL;
        }

        if (!blocksToPlace.isEmpty()) {
            for (Entry<BlockPos, IBlockState> entry : blocksToPlace.entrySet()) {
                // world.setBlockState(entry.getKey(), entry.getValue(), 3);
                BlockUtils.setBlock(world, entry.getKey(), entry.getValue());
                // BlockUtils.markBlockForUpdate(world, entry.getKey());
            }
        }

        return EnumActionResult.PASS;
    }

}
