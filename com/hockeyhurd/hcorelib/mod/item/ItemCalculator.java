package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Item class for calculator.
 *
 * @author hockeyhurd
 * @version 11/10/16
 */
public final class ItemCalculator extends AbstractHCoreItem {

    public ItemCalculator(String name) {
        super(HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

        // Client side only item!
        if (world.isRemote) {
            final int x = (int) player.posX;
            final int y = (int) player.posY;
            final int z = (int) player.posZ;

            player.openGui(HCoreLibMain.instance, -1, world, x, y, z);
        }

        return super.onItemRightClick(stack, world, player, hand);
    }
}
