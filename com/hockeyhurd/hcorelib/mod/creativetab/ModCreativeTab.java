package com.hockeyhurd.hcorelib.mod.creativetab;

import com.hockeyhurd.hcorelib.api.creativetab.AbstractCreativeTab;
import com.hockeyhurd.hcorelib.mod.common.ModRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Standard implementation of abstract creative tab.
 *
 * @author hockeyhurd
 * @version 4/25/16
 * @see com.hockeyhurd.hcorelib.api.creativetab.AbstractCreativeTab
 * @see net.minecraft.creativetab.CreativeTabs
 */
public final class ModCreativeTab extends AbstractCreativeTab {

    public ModCreativeTab(int id, String text) {
        super(id, text);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(ModRegistry.ModItems.itemCalculator.getItem().getItem(), 1);
    }

}
