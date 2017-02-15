package com.hockeyhurd.hcorelib.api.util.interfaces;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;

/**
 * Interfacing for Items/Blocks that have a
 * craftable recipe.
 *
 * @author hockeyhurd
 * @version 2/14/17
 */
public interface ICraftableRecipe {

    RecipePattern[] getRecipePatterns();

}
