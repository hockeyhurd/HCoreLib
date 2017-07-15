package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
public final class ItemCalculator extends AbstractHCoreItem implements ICraftableRecipe {

    private RecipePattern[] recipePatterns;

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

    @Override
    public RecipePattern[] getRecipePatterns() {
        if (recipePatterns == null) {
            recipePatterns = new RecipePattern[1];

            recipePatterns[0] = new RecipePattern("ili", "iri", "ili", true).addAssociation('i', "ingotIron").addAssociation('l',
                    "dyeBlue").addAssociation('r', Items.REDSTONE).setResultStack(new ItemStack(this, 1));
        }

        return recipePatterns;
    }
}
