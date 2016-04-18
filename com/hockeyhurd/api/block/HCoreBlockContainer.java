package com.hockeyhurd.api.block;

import com.hockeyhurd.api.creativetab.ModCreativeTab;
import com.hockeyhurd.api.tileentity.AbstractTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Generic abstract block container class with added features for getting started.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class HCoreBlockContainer extends BlockContainer {

    protected final String assetDir;
    protected String name;

    /**
     * @param material Material of block.
     * @param creativeTab Creative tabl to initialize to.
     * @param assetDir String assets directory.
     * @param name String name of block.
     */
    public HCoreBlockContainer(Material material, ModCreativeTab creativeTab, String assetDir, String name) {
        super(material);

        this.assetDir = assetDir;
        this.name = name;

        this.setBlockName(name);
        this.setHardness(getBlockHardness());
        if (creativeTab != null) this.setCreativeTab(creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(assetDir + name);
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
