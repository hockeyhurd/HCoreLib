package com.hockeyhurd.hcorelib.mod.block;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlockContainer;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTESRTest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author hockeyhurd
 * @version 6/11/2016.
 */
public class TestTESRTile extends AbstractHCoreBlockContainer {

	public TestTESRTile() {
		super(Material.ROCK, HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, "testTESRTile");
	}

	@Override
	public boolean hasSpecialRenderer() {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState blockState) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}

	@Override
	public Block getBlock() {
		return this;
	}

	@Override
	public float getBlockHardness() {
		return 2.0f;
	}

	@Override
	public EnumHarvestLevel getHarvestLevel() {
		return EnumHarvestLevel.PICKAXE_STONE;
	}

	@Override
	public AbstractTile getTileEntity() {
		return new TileEntityTESRTest();
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase placer, ItemStack stack) {
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumHand hand, ItemStack stack,
			EnumFacing sideHit, float hitX, float hitY, float hitZ) {
		return false;
	}
}
