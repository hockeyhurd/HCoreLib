package com.hockeyhurd.hcorelib.api.block;

import com.hockeyhurd.hcorelib.api.creativetab.AbstractCreativeTab;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Generic abstract block container class with added features for getting started.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class AbstractHCoreBlockContainer extends BlockContainer {

    protected final String assetDir;
    protected String name;

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

        this.setRegistryName(name);
        this.setHardness(getBlockHardness());
        if (creativeTab != null) this.setCreativeTab(creativeTab);
    }

    /**
     * Gets the block hardness.
     *
     * @return float block hardness.
     */
    public abstract float getBlockHardness();

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

}
