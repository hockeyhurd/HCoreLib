package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.api.math.Mathd;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.util.ChatUtils;
import com.hockeyhurd.hcorelib.api.util.NumberFormatter;
import com.hockeyhurd.hcorelib.api.util.Waila;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author hockeyhurd
 * @version 8/20/17
 */
public final class ItemMeasureTape extends AbstractHCoreItem implements ICraftableRecipe {

    private static RecipePattern[] recipePatterns;

    /**
     * @param name        Name of Item.
     */
    public ItemMeasureTape(String name) {
        super(HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);
    }

    @Override
    public RecipePattern[] getRecipePatterns() {
        if (recipePatterns == null) {
            recipePatterns = new RecipePattern[1];

            recipePatterns[0] = new RecipePattern("iyi", "ysy", "iyi", true).addAssociation('i', "ingotIron").addAssociation('y',
                    "dyeYellow").addAssociation('s', Items.STRING).setResultStack(new ItemStack(this, 1));
        }

        return recipePatterns;
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
                                      EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {
            final Waila waila = new Waila(null, world, player, null, 3);
            waila.finder(false);

            final Vector3<Integer> vec = waila.getVector3i();
            player.addChatComponentMessage(ChatUtils.createComponent("Set: " + vec.toString()));

            NBTTagCompound comp = null;

            if (!stack.hasTagCompound()) {
                comp = new NBTTagCompound();
                stack.setTagCompound(comp);

                comp.setInteger("tapePosX", vec.x);
                comp.setInteger("tapePosY", vec.y);
                comp.setInteger("tapePosZ", vec.z);
            }

            else {
                comp = stack.getTagCompound();
                final Vector3<Integer> savedVec = new Vector3<Integer>();
                savedVec.x = comp.getInteger("tapePosX");
                savedVec.y = comp.getInteger("tapePosY");
                savedVec.z = comp.getInteger("tapePosZ");

                player.addChatComponentMessage(ChatUtils.createComponent(true, "Euclidian distance: " +
                        NumberFormatter.format((Mathd.round(vec.getNetDifference(savedVec)))),
                        "Manhattan distance:" + vec.getDifference(savedVec).toString()));

                stack.setTagCompound(null);
            }

        }

        return EnumActionResult.PASS;
    }
}
