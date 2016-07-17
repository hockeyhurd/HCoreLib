package com.hockeyhurd.hcorelib.mod.block.multiblock;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlockContainer;
import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockManager;
import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.api.util.interfaces.IStateUpdate;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.managers.TestMultiblockManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

/**
 * Test class for testing multiblocks.
 *
 * @author hockeyhurd
 * @version 7/13/2016.
 */
public class BlockMultiblockController extends AbstractHCoreBlockContainer implements IStateUpdate {

	private static final PropertyBool IS_MULTIBLOCK = PropertyBool.create("multiblock");

	public BlockMultiblockController(Material material, String name) {
		super(material, HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);

		setDefaultState(blockState.getBaseState().withProperty(IS_MULTIBLOCK, false));
	}

	@Override
	public void updateState(AbstractTile tileEntity, World world, Vector3<Integer> vec, Object... data) {
		if (!(tileEntity instanceof MultiblockController)) return;

		final boolean status = (Boolean) data[0];
		IBlockState blockState = BlockUtils.getBlock(world, vec);
		blockState = blockState.withProperty(IS_MULTIBLOCK, status);

		BlockUtils.setBlock(world, vec, blockState, 2);
	}

	@Override
	public BlockMultiblockController getBlock() {
		return this;
	}

	@Override
	public float getBlockHardness() {
		return 2.0f;
	}

	@Override
	public EnumHarvestLevel getHarvestLevel() {
		return EnumHarvestLevel.PICKAXE_WOOD;
	}

	@Override
	public MultiblockController getTileEntity() {
		return new MultiblockController();
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase placer, ItemStack stack) {
		final MultiblockController tile = (MultiblockController) world.getTileEntity(blockPos);
		if (tile == null) return;

		tile.setManager(new TestMultiblockManager(tile));
		tile.setMaster(tile);
		final List<IMultiblockManager> adjacentManagers = new LinkedList<IMultiblockManager>();

		for (EnumFacing dir : EnumFacing.VALUES) {
			final BlockPos atPos = VectorHelper.toBlockPos(blockPos.getX() + dir.getFrontOffsetX(), blockPos.getY() + dir.getFrontOffsetY(),
					blockPos.getZ() + dir.getFrontOffsetZ());
			final TileEntity tileEntity = world.getTileEntity(atPos);

			if (tileEntity != null && tileEntity instanceof IMultiblockable)
				adjacentManagers.add(((IMultiblockable) tileEntity).getManager());
		}

		for (IMultiblockManager otherManager : adjacentManagers)
			tile.getManager().mergeSubMultiblocks(otherManager);

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumHand hand, ItemStack stack,
			EnumFacing sideHit, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public IBlockState getActualState(IBlockState blockState, IBlockAccess world, BlockPos blockPos) {
		final MultiblockController tile = (MultiblockController) world.getTileEntity(blockPos);

		if (tile != null && tile.getManager() != null)
			return blockState.withProperty(IS_MULTIBLOCK, tile.getManager().isCompleteMultiblock());

		return blockState;
	}

	@Override
	public int getMetaFromState(IBlockState blockState) {
		return blockState.getValue(IS_MULTIBLOCK) ? 1 : 0;
	}

	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return blockState.getBaseState().withProperty(IS_MULTIBLOCK, meta == 1);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, IS_MULTIBLOCK);
	}

}
