package com.hockeyhurd.hcorelib.mod.block;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlock;
import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Example class on how to implement AbstractHCoreBlock.
 *
 * @author hockeyhurd
 * @version 5/19/2016.
 */
public class TestBlock extends AbstractHCoreBlock implements ICraftableRecipe {

	private RecipePattern[] recipePatterns;

	public TestBlock() {
		super(Material.ROCK, HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, "testBlock");
	}

	@Override
	public float getBlockHardness() {
		return 1.0f;
	}

	@Override
	public EnumHarvestLevel getHarvestLevel() {
		return EnumHarvestLevel.PICKAXE_WOOD;
	}

	@Override
	public RecipePattern[] getRecipePatterns() {
		if (recipePatterns == null) {
			recipePatterns = new RecipePattern[2];
			recipePatterns[0] = new RecipePattern("xxx", "xxx", "xxx", true).addAssociation('x',
					"stone").setResultStack(new ItemStack(this, 1));

			recipePatterns[1] = recipePatterns[0].copy().addAssociation('x', Blocks.SOUL_SAND).setResultStack(new ItemStack(this, 1));
		}

		return recipePatterns;
	}

}
