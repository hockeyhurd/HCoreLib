package com.hockeyhurd.api.block;

import com.hockeyhurd.api.creativetab.AbstractCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Generic abstract block class with added features for getting started.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class AbstractHCoreBlock extends Block {

    protected final String assetDir;
    protected String name;

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

}