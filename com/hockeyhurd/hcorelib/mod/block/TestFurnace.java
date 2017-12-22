package com.hockeyhurd.hcorelib.mod.block;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlockContainer;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
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
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

/**
 * Block class for a test example furnace
 *
 * @author hockeyhurd
 * @version 3/14/17
 */
public class TestFurnace extends AbstractHCoreBlockContainer {

    protected static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    protected static final PropertyBool WORKING = PropertyBool.create("working");

    public TestFurnace() {
        super(Material.ROCK, HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, "testFurnace");
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(WORKING, false));
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
        return EnumHarvestLevel.PICKAXE_WOOD;
    }

    @Override
    public TileFurnace getTileEntity() {
        return new TileFurnace();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState block, EntityLivingBase player, ItemStack stack) {
        final TileEntity tileEntity = world.getTileEntity(pos);
        if (!(tileEntity instanceof TileFurnace)) return;

        final TileFurnace te = (TileFurnace) tileEntity;
        final boolean isServerSide = !world.isRemote;

        te.setFrontFacing(player.getHorizontalFacing().getOpposite());

        if (isServerSide) {
            if (stack.hasTagCompound()) {
                te.readNBT(stack.getTagCompound());
                te.markDirty();
            }

            if (stack.hasDisplayName()) te.setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing sideHit, float hitX, float hitY, float hitZ) {
        if (world.isRemote) return true;

        TileFurnace te = (TileFurnace) world.getTileEntity(blockPos);
        if (te != null) {
                FMLNetworkHandler.openGui(player, HCoreLibMain.instance, 2, world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }

        return true;
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
        if (tileEntity instanceof TileFurnace)
            return blockState.withProperty(FACING, ((TileFurnace) tileEntity).getFrontFacing()).withProperty(WORKING,
                    ((TileFurnace) tileEntity).isActive());

        return blockState.withProperty(FACING, EnumFacing.NORTH).withProperty(WORKING, false);
    }

    @Override
    public int getMetaFromState(IBlockState blockState) {
        int meta = blockState.getValue(WORKING) ? 1 : 0;
        meta <<= 3;
        meta |= blockState.getValue(FACING).getIndex();

        return meta;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        final boolean active = (meta & 8) != 0;
        EnumFacing facing = EnumFacing.getFront(meta & 7);
        if (facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;

        return getDefaultState().withProperty(FACING, facing).withProperty(WORKING, active);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING))).withProperty(WORKING, state.getValue(WORKING));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withProperty(WORKING, state.getValue(WORKING)).withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, WORKING);
    }

    public static void updateState(boolean active, World world, BlockPos blockPos) {
        IBlockState blockState = world.getBlockState(blockPos);
        TileFurnace tileFurnace = (TileFurnace) world.getTileEntity(blockPos);

        world.setBlockState(blockPos, blockState.getBlock().getDefaultState().withProperty(FACING, blockState.getValue(FACING)).withProperty(WORKING, active), 3);

        if (tileFurnace != null) {
            tileFurnace.validate();
            world.setTileEntity(blockPos, tileFurnace);
        }
    }

}
