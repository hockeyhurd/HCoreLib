package com.hockeyhurd.hcorelib.api.util.interfaces;

import com.hockeyhurd.hcorelib.api.handler.RecipePattern;

/**
 * Interfacing for Items/Blocks that have a
 * craftable recipe.
 *
 * @deprecated Do not use in 1.12.x+, kept for compatibility reasons but will be removed!
 *
 * @author hockeyhurd
 * @version 2/14/17
 */
@Deprecated
public interface ICraftableRecipe {

    /**
     * Gets the crafting recipe patterns.
     *
     * @return RecipePattern array.
     */
    RecipePattern[] getRecipePatterns();

}
