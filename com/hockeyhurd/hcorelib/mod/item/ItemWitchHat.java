package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.item.armor.AbstractArmor;
import com.hockeyhurd.hcorelib.api.util.enums.EnumArmorType;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.client.renderer.WitchHatModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author hockeyhurd
 * @version 7/26/17
 */
public class ItemWitchHat extends AbstractArmor implements ICraftableRecipe {

    public static final String NAME = "witchHat";
    private static RecipePattern[] recipePatterns;

    /**
     * @param material    armor material to use.
     * @param renderIndex render index of item.
     * @param armorType   armor type.
     * @param assetDir
     */
    public ItemWitchHat(ArmorMaterial material, int renderIndex, EnumArmorType armorType, String assetDir) {
        super(material, renderIndex, armorType, assetDir, NAME, HCoreLibMain.myCreativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        return new WitchHatModel();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        // return super.getArmorTexture(stack, entity, slot, type);
        return "textures/entity/witch.png";
    }

    @Override
    public RecipePattern[] getRecipePatterns() {
        if (recipePatterns == null) {
            recipePatterns = new RecipePattern[1];

            recipePatterns[0] = new RecipePattern("sls", "l l", "sns", true).addAssociation('s', Items.STRING).addAssociation('l',
                    Items.LEATHER).addAssociation('n', "nuggetGold").setResultStack(new ItemStack(this, 1));
        }

        return recipePatterns;
    }
}
