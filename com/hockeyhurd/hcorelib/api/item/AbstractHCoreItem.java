package com.hockeyhurd.hcorelib.api.item;

import com.hockeyhurd.hcorelib.api.creativetab.AbstractCreativeTab;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Generalized item class.
 *
 * @author hockeyhurd
 * @version 4/25/16
 */
public abstract class AbstractHCoreItem extends Item implements IHItem {

    protected final String assetDir;
    protected String name;
    protected final ResourceLocation resourceLocation;
    protected final AbstractCreativeTab creativeTab;

    /**
     * @param creativeTab Creative tab to place item in.
     * @param assetDir Asset directory for item texture.
     * @param name Name of Item.
     */
    public AbstractHCoreItem(AbstractCreativeTab creativeTab, String assetDir, String name) {
        this.creativeTab = creativeTab;
        this.name = name;
        this.assetDir = assetDir;

        setUnlocalizedName(name);
        setRegistryName(name);
        if (creativeTab != null) setCreativeTab(creativeTab);

        resourceLocation = new ResourceLocation(assetDir, name);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public int getSizeOfSubItems() {
        return 0;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    @Override
    public String getName() {
        return name;
    }

}
