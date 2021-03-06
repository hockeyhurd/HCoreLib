package com.hockeyhurd.hcorelib.mod.block;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlockContainer;
import com.hockeyhurd.hcorelib.api.block.IHBlock;
import com.hockeyhurd.hcorelib.api.handler.tooltip.IBlockTooltip;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTest;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author hockeyhurd
 * @version 5/23/2016.
 */
public class TestTile extends AbstractHCoreBlockContainer implements IBlockTooltip {

	protected static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final PropertyBool WORKING = PropertyBool.create("working");

	public TestTile() {
		super(Material.ROCK, HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, "tileTest");
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
		return EnumHarvestLevel.PICKAXE_STONE;
	}

	@Override
	public TileEntityTest getTileEntity() {
		return new TileEntityTest();
	}

	@Override
	public EnumFacing[] getValidRotations(World world, BlockPos blockPos) {
		final TileEntity tileEntity = world.getTileEntity(blockPos);
		if (tileEntity instanceof TileEntityTest)
			return EnumFacing.HORIZONTALS;

		return super.getValidRotations(world, blockPos);
	}

	@Override
	public IBlockState getActualState(IBlockState blockState, IBlockAccess world, BlockPos blockPos) {
		final TileEntity tileEntity = world.getTileEntity(blockPos);
		if (tileEntity instanceof TileEntityTest)
			return blockState.withProperty(FACING, ((TileEntityTest) tileEntity).getFrontFacing()).withProperty(WORKING,
					((TileEntityTest) tileEntity).isActive());

		return blockState.withProperty(FACING, EnumFacing.NORTH).withProperty(WORKING, false);
	}

	@Override
	public int getMetaFromState(IBlockState blockState) {
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, WORKING);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase placer, ItemStack stack) {
		TileEntity tileEntity = world.getTileEntity(blockPos);

		if (tileEntity instanceof TileEntityTest) ((TileEntityTest) tileEntity).setFrontFacing(placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumHand hand,
			EnumFacing sideHit, float hitX, float hitY, float hitZ) {
		return false;
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
		return true;
	}

	@Override
	public boolean hasControlInformation() {
		return true;
	}

	@Override
	public void addInformation(List<String> list, ItemStack itemStack) {
		list.add(TextFormatting.RED + "This is random information!");
	}

	@Override
	public void addShiftInformation(List<String> list, ItemStack itemStack) {
		list.add(TextFormatting.WHITE + "This is shift information!");
	}

	@Override
	public void addControlInformation(List<String> list, ItemStack itemStack) {
		list.add(TextFormatting.BLUE + "This is control information!");
	}
}
