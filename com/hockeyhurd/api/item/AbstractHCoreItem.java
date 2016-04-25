package com.hockeyhurd.api.item;

import com.hockeyhurd.api.creativetab.AbstractCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Generalized item class.
 *
 * @author hockeyhurd
 * @version 4/25/16
 */
public abstract class AbstractHCoreItem extends Item {

    protected final AbstractCreativeTab creativeTab;
    protected final String name;
    protected final String assetDir;

    /**
     * @param creativeTab Creative tab to place item in.
     * @param name Name of Item.
     * @param assetDir Asset directory for item texture.
     */
    public AbstractHCoreItem(AbstractCreativeTab creativeTab, String name, String assetDir) {
        this.creativeTab = creativeTab;
        this.name = name;
        this.assetDir = assetDir;

        setUnlocalizedName(name);
        setCreativeTab(creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        itemIcon = reg.registerIcon(assetDir + name);
    }

    /**
     * Gets the name of the Item.
     *
     * @return String name.
     */
    public String getName() {
        return name;
    }

}
