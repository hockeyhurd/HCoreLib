package com.hockeyhurd.hcorelib.api.block;

import com.hockeyhurd.hcorelib.api.creativetab.AbstractCreativeTab;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Generic abstract block container class with added features for getting started.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class AbstractHCoreBlockContainer extends BlockContainer implements IHBlock {

    protected final String assetDir;
    protected String name;
    protected ItemBlock itemBlock;
    protected final ResourceLocation resourceLocation;

    /**
     * @param material Material of block.
     * @param creativeTab Creative tab to initialize to.
     * @param assetDir String assets directory.
     * @param name String name of block.
     */
    public AbstractHCoreBlockContainer(Material material, AbstractCreativeTab creativeTab, String assetDir, String name) {
        super(material);

        this.assetDir = assetDir;
        this.name = name;

        if (creativeTab != null) setCreativeTab(creativeTab);
        setUnlocalizedName(name);
        setRegistryName(name);
        // setDefaultState(blockState.getBaseState());
        setHardness(getBlockHardness());
        setHarvestLevel(getHarvestLevel().getTypeName(), getHarvestLevel().getLevel());

        resourceLocation = new ResourceLocation(assetDir, name);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemBlock getItemBlock() {
        return itemBlock != null ? itemBlock : (itemBlock = new ItemBlock(this));
    }

    @Override
    public abstract float getBlockHardness();

    @Override
    public abstract EnumHarvestLevel getHarvestLevel();

    /**
     * Gets the created instance of AbstractTile child object.
     *
     * @return AbstractTile child object instance.
     */
    public abstract AbstractTile getTileEntity();

    @Override
    public AbstractTile createNewTileEntity(World world, int id) {
        return getTileEntity();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState blockState) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public abstract void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase placer, ItemStack stack);

    @Override
    public abstract boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumHand hand,
            ItemStack stack, EnumFacing sideHit, float hitX, float hitY, float hitZ);

}
