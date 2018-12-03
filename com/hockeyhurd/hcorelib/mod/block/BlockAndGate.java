package com.hockeyhurd.hcorelib.mod.block;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlockContainer;
import com.hockeyhurd.hcorelib.api.block.IHBlock;
import com.hockeyhurd.hcorelib.api.handler.tooltip.IBlockTooltip;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.tileentity.TileAndGate;
import com.hockeyhurd.hcorelib.mod.tileentity.TileFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

/**
 * @author hockeyhurd
 * @version 4/1/18
 */
public class BlockAndGate extends AbstractHCoreBlockContainer implements IBlockTooltip {

    protected static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    protected static final PropertyBool POWERED = PropertyBool.create("powered");

    public BlockAndGate(Material material, String name) {
        super(material, HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
    }

    @Override
    public IHBlock getType() {
        return this;
    }

    @Override
    public boolean isBlock() {
        return true;
    }

    @Override
    public boolean isItem() {
        return false;
    }

    @Override
    public boolean hasShiftInformation() {
        return false;
    }

    @Override
    public boolean hasControlInformation() {
        return false;
    }

    @Override
    public void addInformation(List<String> list, ItemStack itemStack) {
        list.add("I am and And Gate!");
    }

    @Override
    public void addShiftInformation(List<String> list, ItemStack itemStack) {

    }

    @Override
    public void addControlInformation(List<String> list, ItemStack itemStack) {

    }

    @Override
    public Block getBlock() {
        return this;
    }

    @Override
    public float getBlockHardness() {
        return 1.0f;
    }

    @Override
    public EnumHarvestLevel getHarvestLevel() {
        return EnumHarvestLevel.PICKAXE_STONE;
    }

    @Override
    public AbstractTile getTileEntity() {
        return new TileAndGate();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase player, ItemStack stack) {
        final TileEntity tileEntity = world.getTileEntity(blockPos);
        if (!(tileEntity instanceof TileAndGate))
            return;

        final TileAndGate te = (TileAndGate) tileEntity;
        final boolean isServerSide = !world.isRemote;

        te.setFrontFacing(player.getHorizontalFacing().getOpposite());

        if (isServerSide) {
            if (stack.hasTagCompound()) {
                te.readNBT(stack.getTagCompound());
                te.markDirty();
            }

            if (stack.hasDisplayName())
                te.setCustomName(stack.getDisplayName());

            boolean output = false;

            for (EnumFacing face : EnumFacing.HORIZONTALS) {
                final BlockPos offsetPos = blockPos.offset(face);
                final IBlockState blockAt = BlockUtils.getBlock(world, offsetPos);
                final int signalStrength = blockAt.getWeakPower(world, offsetPos, face);

                output = te.setSignalFromSide(signalStrength, face);
            }

            updateState(output, world, blockPos);
            notifyNeighbors(world, blockPos, blockState);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return false;

        final TileAndGate tileAndGate = (TileAndGate) world.getTileEntity(pos);

        for (EnumFacing side : EnumFacing.HORIZONTALS)
            HCoreLibMain.logHelper.info(side, tileAndGate.getSignalFromSide(side));

        return false;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
        EnumFacing side = EnumFacing.UP;
        BlockPos offset = pos.subtract(fromPos);

        for (EnumFacing cur : EnumFacing.HORIZONTALS) {
            if (cur.getDirectionVec().equals(offset)) {
                side = cur;
                break;
            }
        }

        if (side == EnumFacing.UP || side == EnumFacing.DOWN)
            return;

        final TileAndGate tile = (TileAndGate) world.getTileEntity(pos);
        final IBlockState blockAtPos = BlockUtils.getBlock(world, fromPos);
        final int signalStrength = blockAtPos.getWeakPower(world, fromPos, side);

        final boolean output = tile.setSignalFromSide(signalStrength, side);

        updateState(output, world, pos);
        notifyNeighbors(world, pos, state);
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return getWeakPower(blockState, blockAccess, pos, side);
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        int output = 0;
        final TileAndGate tile = (TileAndGate) blockAccess.getTileEntity(pos);

        if (tile != null && side == tile.getFrontFacing()) {
            output = tile.getSignalFromSide(tile.getFrontFacing());
        }

        return output;
    }

    private void notifyNeighbors(World worldIn, BlockPos pos, IBlockState state) {
        final EnumFacing enumfacing = state.getValue(FACING);
        final BlockPos blockpos = pos.offset(enumfacing.getOpposite());

        if (net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(worldIn, pos, worldIn.getBlockState(pos), EnumSet.of(enumfacing.getOpposite()), false).isCanceled())
            return;

        worldIn.neighborChanged(blockpos, this, pos);
        worldIn.notifyNeighborsOfStateExcept(blockpos, this, enumfacing);
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side) {
        for (EnumFacing face : EnumFacing.HORIZONTALS) {
            if (face == side)
                return true;
        }

        return false;
    }

    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public EnumFacing[] getValidRotations(World world, BlockPos blockPos) {
        final TileEntity tileEntity = world.getTileEntity(blockPos);

        if (tileEntity instanceof TileFurnace)
            return EnumFacing.HORIZONTALS;

        return super.getValidRotations(world, blockPos);
    }

    @Override
    public IBlockState getActualState(IBlockState blockState, IBlockAccess world, BlockPos blockPos) {
        final TileEntity tileEntity = world.getTileEntity(blockPos);
        if (tileEntity instanceof TileAndGate) {
            final TileAndGate tile = (TileAndGate) tileEntity;
            return blockState.withProperty(FACING, tile.getFrontFacing()).withProperty(POWERED, tile.getSignalFromSide(tile.getFrontFacing()) > 7);
        }

        return blockState.withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false);
    }

    @Override
    public int getMetaFromState(IBlockState blockState) {
        return blockState.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta & 7);

        if (facing.getAxis() == EnumFacing.Axis.Y)
            facing = EnumFacing.NORTH;

        return getDefaultState().withProperty(FACING, facing).withProperty(POWERED, false);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING))).withProperty(POWERED, state.getValue(POWERED));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING))).withProperty(POWERED, state.getValue(POWERED));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, POWERED);
    }

    public static void updateState(boolean active, World world, BlockPos blockPos) {
        IBlockState blockState = world.getBlockState(blockPos);
        TileAndGate tileAndGate = (TileAndGate) world.getTileEntity(blockPos);

        world.setBlockState(blockPos, blockState.getBlock().getDefaultState().withProperty(FACING, blockState.getValue(FACING)).withProperty(POWERED, active));

        if (tileAndGate != null) {
            tileAndGate.validate();
            world.setTileEntity(blockPos, tileAndGate);
        }
    }

}
