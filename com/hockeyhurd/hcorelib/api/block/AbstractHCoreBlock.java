package com.hockeyhurd.hcorelib.api.block;

import com.hockeyhurd.hcorelib.api.creativetab.AbstractCreativeTab;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

/**
 * Generic abstract block class with added features for getting started.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class AbstractHCoreBlock extends Block {

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
    public AbstractHCoreBlock(Material material, AbstractCreativeTab creativeTab, String assetDir, String name) {
        super(material);

        this.assetDir = assetDir;
        this.name = name;

        if (creativeTab != null) this.setCreativeTab(creativeTab);
        setUnlocalizedName(name);
        setRegistryName(name);
        setDefaultState(blockState.getBaseState());
        setHardness(getBlockHardness());
        setHarvestLevel(getHarvestLevel().getTypeName(), getHarvestLevel().getLevel());

        resourceLocation = new ResourceLocation(assetDir, name);
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public String getName() {
        return name;
    }

    public ItemBlock getItemBlock() {
        return itemBlock != null ? itemBlock : (itemBlock = new ItemBlock(this));
    }

    /**
     * Gets the block hardness.
     *
     * @return float block hardness.
     */
    public abstract float getBlockHardness();

    /**
     * Gets the block's harvest level.
     *
     * @return EnumHarvestLevel data.
     */
    public abstract EnumHarvestLevel getHarvestLevel();

}
