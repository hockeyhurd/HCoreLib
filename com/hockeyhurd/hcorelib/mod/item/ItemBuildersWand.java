package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.handler.client.IWorldDrawable;
import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.api.math.Color4f;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author hockeyhurd
 * @version 11/23/17
 */
public final class ItemBuildersWand extends AbstractHCoreItem implements ICraftableRecipe, IWorldDrawable {

    private static RecipePattern[] recipePatterns;
    protected int MAX_RADII = 3;

    // @SideOnly(Side.CLIENT)
    protected static Color4f color = new Color4f(1.0f, 1.0f, 1.0f, 1.0f);

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
            // player.addChatComponentMessage(ChatUtils.createComponent("Right click @: " + vec.toString()));
            // player.addChatComponentMessage(ChatUtils.createComponent("Facing: " + facing.getName()));
            // player.addChatComponentMessage(ChatUtils.createComponent("Offset X: " + facing.getFrontOffsetX()));
            // player.addChatComponentMessage(ChatUtils.createComponent("Offset Y: " + facing.getFrontOffsetY()));
            // player.addChatComponentMessage(ChatUtils.createComponent("Offset Z: " + facing.getFrontOffsetZ()));
            // player.addChatComponentMessage(ChatUtils.createComponent("Axis : " + facing.getAxis().getName()));

            return placeBlocksAlongAxis(stack, world, vec, facing);
        }

        player.swingArm(hand);

        return placeBlocksAlongAxis(stack,  world, VectorHelper.toVector3i(pos), facing);

        // return EnumActionResult.PASS;
    }

    private EnumActionResult placeBlocksAlongAxis(ItemStack stack, World world, Vector3<Integer> pos, EnumFacing facing) {
        final IBlockState blockLookingAt = BlockUtils.getBlock(world, pos);
        final Vector3<Integer> currentPos = pos.copy();
        final Map<BlockPos, IBlockState> blocksToPlace = new TreeMap<BlockPos, IBlockState>();

        switch (facing.getAxis()) {
            case X:
                for (int y = -MAX_RADII; y <= MAX_RADII; y++) {
                    for (int z = -MAX_RADII; z <= MAX_RADII; z++) {
                        currentPos.y = pos.y + y;
                        currentPos.z = pos.z + z;

                        final IBlockState currentBlock = BlockUtils.getBlock(world, currentPos);

                        if (currentBlock.equals(blockLookingAt)) {
                            blocksToPlace.put(new BlockPos(currentPos.x + facing.getFrontOffsetX(), currentPos.y, currentPos.z),
                                    currentBlock);
                        }
                    }
                }

                break;
            case Y:
                for (int x = -MAX_RADII; x <= MAX_RADII; x++) {
                    for (int z = -MAX_RADII; z <= MAX_RADII; z++) {

                        currentPos.x = pos.x + x;
                        currentPos.z = pos.z + z;

                        final IBlockState currentBlock = BlockUtils.getBlock(world, currentPos);

                        if (currentBlock.equals(blockLookingAt)) {
                            blocksToPlace.put(new BlockPos(currentPos.x, currentPos.y + facing.getFrontOffsetY(), currentPos.z),
                                    currentBlock);
                        }
                    }
                }

                break;
            case Z:
                for (int y = -MAX_RADII; y <= MAX_RADII; y++) {
                    for (int x = -MAX_RADII; x <= MAX_RADII; x++) {
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
            if (!world.isRemote) {
                final List<BlockPos> placePositions = new ArrayList<BlockPos>(blocksToPlace.size());

                for (Entry<BlockPos, IBlockState> entry : blocksToPlace.entrySet()) {
                    if (!BlockUtils.blockExists(world, entry.getKey())) {
                        BlockUtils.setBlock(world, entry.getKey(), entry.getValue());
                        // world.notifyBlockOfStateChange(entry.getKey(), entry.getValue().getBlock());
                        world.notifyBlockUpdate(entry.getKey(), BlockUtils.getBlock(world, entry.getKey()), entry.getValue(), 3);

                        placePositions.add(entry.getKey());
                    }
                }

                if (!placePositions.isEmpty()) {
                    writeToNBT(stack, placePositions);
                }
            }

            else {
                for (Entry<BlockPos, IBlockState> entry : blocksToPlace.entrySet()) {
                    if (!BlockUtils.blockExists(world, entry.getKey())) {
                        BlockUtils.setBlock(world, entry.getKey(), entry.getValue());
                        // world.notifyBlockOfStateChange(entry.getKey(), entry.getValue().getBlock());
                        world.notifyBlockUpdate(entry.getKey(), BlockUtils.getBlock(world, entry.getKey()), entry.getValue(), 3);
                    }
                }
            }
        }

        return EnumActionResult.PASS;
    }

    private static void writeToNBT(ItemStack stack, List<BlockPos> blockPositions) {
        NBTTagCompound comp = stack.getTagCompound();

        if (comp == null) {
            comp = new NBTTagCompound();
            stack.setTagCompound(comp);
        }

        comp.setInteger("length", blockPositions.size());

        int count = 0;
        for (BlockPos pos : blockPositions) {
            comp.setInteger(String.format("%d:x", count), pos.getX());
            comp.setInteger(String.format("%d:y", count), pos.getY());
            comp.setInteger(String.format("%d:z", count), pos.getZ());

            count++;
        }
    }

    private static BlockPos[] readFromNBT(ItemStack stack) {
        if (!stack.hasTagCompound())
            return null;

        final NBTTagCompound comp = stack.getTagCompound();
        final int length = comp.getInteger("length");

        if (length == 0)
            return null;

        final BlockPos[] blockPositions = new BlockPos[length];

        for (int i = 0; i < blockPositions.length; i++) {
            final int x = comp.getInteger(String.format("%d:x", i));
            final int y = comp.getInteger(String.format("%d:y", i));
            final int z = comp.getInteger(String.format("%d:z", i));

            blockPositions[i] = new BlockPos(x, y, z);
        }

        return blockPositions;
    }

    @Override
    public int getRadii() {
        return MAX_RADII;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Color4f getColor() {
        return color;
    }

}
