package com.hockeyhurd.hcorelib.mod.block.multiblock;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlockContainer;
import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockManager;
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
					HCoreLibMain.logHelper.info("Before:", contStack.getCount(), stack.getCount());
					contStack.setCount(contStack.getCount() + stack.getCount());
					comp.setInventorySlotContents(0, contStack);
					HCoreLibMain.logHelper.info("After:", contStack.getCount());
				}

				else {
					comp.setInventorySlotContents(0, stack.copy());
					HCoreLibMain.logHelper.info("Set stack:", stack.getDisplayName(), stack.getCount());
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
	protected void onBlockDestroyed(World world, AbstractTile tileEntity, BlockPos blockPos, IBlockState blockState) {
		if (!world.isRemote && tileEntity instanceof MultiblockComponent) {

			final MultiblockComponent tile = (MultiblockComponent) tileEntity;

			IMultiblockManager manager = tile.getManager();
			tile.getManager().removeTile(tile);

			for (IMultiblockable comp : manager.getMultiblockTiles()) {
				HCoreLibMain.logHelper.info("Comp @:", comp.getTile().worldVec());
			}
		}

		super.onBlockDestroyedByPlayer(world, blockPos, blockState);
	}

	// Right click on block.
	@Override
	public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumHand hand,
			EnumFacing sideHit, float hitX, float hitY, float hitZ) {

		if (!world.isRemote) {
			// HCoreLibMain.logHelper.info("Hand:", hand.name());

			final MultiblockComponent component = (MultiblockComponent) world.getTileEntity(blockPos);
			if (component == null) return false;

			boolean hasManager = component.getManager() != null;

			if (!hasManager) {

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

			/*else if (component.getStackInSlot(0) != null) {
				ItemStack compStack = component.getStackInSlot(0);
				HCoreLibMain.logHelper.info("Here!", compStack.stackSize);
				if (compStack.stackSize - 1 > 0) compStack.stackSize--;
				else component.setInventorySlotContents(0, null);

				// WorldUtils.spawnItemEntity(world, VectorHelper.toVector3i(blockPos), compStack);
			}*/

			else {
				final ItemStack stack = player.getHeldItem(hand);

				if (stack != null && stack.getCount() > 0) {
					// HCoreLibMain.logHelper.info("Here!", stack, stack.stackSize);
					component.pushStack(stack, false);
					// HCoreLibMain.logHelper.info("After put stack:", component.getStackInSlot(0));
				}

				else if (component.getStackInSlot(0) != null) HCoreLibMain.logHelper.info(component.getStackInSlot(0));
			}

			blockState = blockState.withProperty(IS_MULTIBLOCK, hasManager && component.getManager().isCompleteMultiblock());
			BlockUtils.setBlock(world, blockPos, blockState);
			BlockUtils.updateAndNotifyNeighborsOfBlockUpdate(world, blockPos);
		}

		return true;
	}

	// Left click on block.
	@Override
	public void onBlockClicked(World world, BlockPos blockPos, EntityPlayer player) {

		if (!world.isRemote) {
			// HCoreLibMain.logHelper.info("onBlockClicked");

			final MultiblockComponent component = (MultiblockComponent) world.getTileEntity(blockPos);
			if (component == null) return;

			// If the tileentity has something contained:
			final ItemStack stack = component.getStackInSlot(0);
			if (stack != null) {
				// final ItemStack stackPull = component.pullStack(Math.min(component.size(), stack.getMaxStackSize()), false);
				final ItemStack stackPull = component.pullStack(Math.min(component.size(), stack.getMaxStackSize()), true);

				HCoreLibMain.logHelper.info(stack, component.getTile().size());
				WorldUtils.spawnItemEntity(world, VectorHelper.toVector3i(blockPos), stackPull);
			}
		}

		super.onBlockClicked(world, blockPos, player);
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
