package com.hockeyhurd.hcorelib.mod.block.multiblock;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlockContainer;
import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.WorldUtils;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.api.util.interfaces.IStateUpdate;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockComponent;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Test class for testing multiblocks components.
 *
 * @author hockeyhurd
 * @version 8/4/2016.
 */
public class BlockMultiblockComponent extends AbstractHCoreBlockContainer implements IStateUpdate {

	private static final PropertyBool IS_MULTIBLOCK = PropertyBool.create("multiblock");

	public BlockMultiblockComponent(Material material, String name) {
		super(material, HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);

		setDefaultState(blockState.getBaseState().withProperty(IS_MULTIBLOCK, false));
	}

	@Override
	public void updateState(AbstractTile tileEntity, World world, Vector3<Integer> vec, Object... data) {
		if (tileEntity != null && tileEntity instanceof MultiblockComponent && world != null && !world.isRemote & vec != null &&
				data != null && data.length == 1 && data[0] != null && data[0] instanceof ItemStack) {

			final MultiblockComponent comp = (MultiblockComponent) tileEntity;
			final ItemStack stack = (ItemStack) data[0];
			if (comp.canInsertItem(0, stack, EnumFacing.UP)) {
				final ItemStack contStack = comp.getStackInSlot(0);

				if (contStack != null) {
					HCoreLibMain.logHelper.info("Before:", contStack.stackSize, stack.stackSize);
					contStack.stackSize += stack.stackSize;
					comp.setInventorySlotContents(0, contStack);
					HCoreLibMain.logHelper.info("After:", contStack.stackSize);
				}

				else {
					comp.setInventorySlotContents(0, stack.copy());
					HCoreLibMain.logHelper.info("Set stack:", stack.getDisplayName(), stack.stackSize);
				}
			}
		}
	}

	@Override
	public BlockMultiblockComponent getBlock() {
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
	public MultiblockComponent getTileEntity() {
		return new MultiblockComponent();
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase placer, ItemStack stack) {
		if (!world.isRemote) {
			final MultiblockComponent component = (MultiblockComponent) world.getTileEntity(blockPos);
			if (component == null) return;

			boolean hasManager = component.getManager() != null;

			if (!hasManager) {
				HCoreLibMain.logHelper.severe("Manager is null! Fixing!");

				for (EnumFacing dir : EnumFacing.VALUES) {
					final IMultiblockable adjTile = (IMultiblockable) world.getTileEntity(VectorHelper.toBlockPos(blockPos.getX() + dir.getFrontOffsetX(),
							blockPos.getY() + dir.getFrontOffsetY(), blockPos.getZ() + dir.getFrontOffsetZ()));

					if (adjTile != null && adjTile.getManager() != null) {
						adjTile.getManager().addTile(component);
						hasManager = true;
						break;
					}
				}
			}

			blockState = blockState.withProperty(IS_MULTIBLOCK, hasManager && component.getManager().isCompleteMultiblock());
			BlockUtils.setBlock(world, blockPos, blockState);
			BlockUtils.updateAndNotifyNeighborsOfBlockUpdate(world, blockPos);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos blockPos, IBlockState blockState) {
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, BlockPos blockPos, Explosion explosion) {
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumHand hand, ItemStack stack,
			EnumFacing sideHit, float hitX, float hitY, float hitZ) {

		if (!world.isRemote) {
			final MultiblockComponent component = (MultiblockComponent) world.getTileEntity(blockPos);
			if (component == null) return false;

			boolean hasManager = component.getManager() != null;

			if (!hasManager) {
				HCoreLibMain.logHelper.severe("Manager is null! Fixing!");

				for (EnumFacing dir : EnumFacing.VALUES) {
					final IMultiblockable adjTile = (IMultiblockable) world.getTileEntity(VectorHelper.toBlockPos(blockPos.getX() + dir.getFrontOffsetX(),
							blockPos.getY() + dir.getFrontOffsetY(), blockPos.getZ() + dir.getFrontOffsetZ()));

					if (adjTile != null && adjTile.getManager() != null) {
						adjTile.getManager().addTile(component);
						hasManager = true;
						break;
					}
				}
			}

			else if (component.getStackInSlot(0) != null) {
				ItemStack compStack = component.getStackInSlot(0);
				if (compStack.stackSize - 1 > 0) compStack.stackSize--;
				else component.setInventorySlotContents(0, null);

				WorldUtils.spawnItemEntity(world, VectorHelper.toVector3i(blockPos), compStack);
			}

			blockState = blockState.withProperty(IS_MULTIBLOCK, hasManager && component.getManager().isCompleteMultiblock());
			BlockUtils.setBlock(world, blockPos, blockState);
			BlockUtils.updateAndNotifyNeighborsOfBlockUpdate(world, blockPos);
		}

		return true;
	}

	@Override
	public IBlockState getActualState(IBlockState blockState, IBlockAccess world, BlockPos blockPos) {
		final MultiblockComponent tile = (MultiblockComponent) world.getTileEntity(blockPos);

		if (tile != null && tile.getManager() != null)
			return blockState.withProperty(IS_MULTIBLOCK, tile.getManager().isCompleteMultiblock());

		return blockState;
	}

	@Override
	public int getMetaFromState(IBlockState blockState) {
		return blockState.getValue(IS_MULTIBLOCK) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return blockState.getBaseState().withProperty(IS_MULTIBLOCK, meta == 1);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, IS_MULTIBLOCK);
	}

}
