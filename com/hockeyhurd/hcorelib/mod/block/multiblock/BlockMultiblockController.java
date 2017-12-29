package com.hockeyhurd.hcorelib.mod.block.multiblock;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlockContainer;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import com.hockeyhurd.hcorelib.api.tileentity.multiblock.EnumMultiblockState;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.api.util.interfaces.IStateUpdate;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;
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
 * Test class for testing multiblock controllers.
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
        if (tileEntity != null && tileEntity instanceof MultiblockController && world != null && !world.isRemote & vec != null &&
                data != null && data[0] instanceof Boolean) {

            // final MultiblockController controller = (MultiblockController) tileEntity;
            final BlockPos blockPos = VectorHelper.toBlockPos(vec);
            IBlockState blockState = BlockUtils.getBlock(world, vec);

            blockState = blockState.withProperty(IS_MULTIBLOCK, (Boolean) data[0]);
            BlockUtils.setBlock(world, blockPos, blockState);
            BlockUtils.updateAndNotifyNeighborsOfBlockUpdate(world, blockPos);
        }
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
        if (!world.isRemote) {
            final MultiblockController tile = (MultiblockController) world.getTileEntity(blockPos);

            if (tile == null)
                return;

            // blockState = blockState.withProperty(IS_MULTIBLOCK, false);
            blockState = blockState.withProperty(IS_MULTIBLOCK, false);
            BlockUtils.setBlock(world, blockPos, blockState);
            BlockUtils.updateAndNotifyNeighborsOfBlockUpdate(world, blockPos);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing sideHit,
            float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {
            // final MultiblockController controller = (MultiblockController) world.getTileEntity(blockPos);
        }

        return true;
    }

    @Override
    protected void onBlockDestroyed(World world, AbstractTile tileEntity, BlockPos blockPos, IBlockState blockState) {
        final MultiblockController tile = (MultiblockController) world.getTileEntity(blockPos);

        if (tile != null) {
            tile.setMultiblockState(EnumMultiblockState.IN_COMPLETE);
            tile.notifyChildren();
        }
    }

    @Override
    public IBlockState getActualState(IBlockState blockState, IBlockAccess world, BlockPos blockPos) {
        final MultiblockController tile = (MultiblockController) world.getTileEntity(blockPos);

        if (tile != null)
            return blockState.withProperty(IS_MULTIBLOCK, tile.getMultiblockState() == EnumMultiblockState.COMPLETE);

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
