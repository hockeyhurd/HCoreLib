package com.hockeyhurd.hcorelib.api.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Class for creating recipe patterns.
 *
 * @author hockeyhurd
 * @version 2/14/17
 */
public class RecipePattern {

    public static final String EMPTY_ROW = "   ";
    protected boolean shapedRecipe;
    protected ItemStack resultStack;
    protected String[] pattern;
    protected Map<Character, Object> associativeMap;

    protected RecipePattern() {
        shapedRecipe = true;
        pattern = new String[3];
        associativeMap = new TreeMap<Character, Object>();
    }

    public RecipePattern(String top, String middle, String bottom, boolean shapedRecipe) {
        this.shapedRecipe = shapedRecipe;
        pattern = new String[3];

        pattern[0] = top != null ? top : EMPTY_ROW;
        pattern[1] = middle != null ? middle : EMPTY_ROW;
        pattern[2] = bottom != null ? bottom : EMPTY_ROW;

        associativeMap = new TreeMap<Character, Object>();
    }

    public RecipePattern(RecipePattern other) {
        shapedRecipe = other.shapedRecipe;
        pattern = new String[3];

        for (int i = 0; i < pattern.length; i++)
            pattern[i] = other.pattern[i];

        associativeMap = new TreeMap<Character, Object>();
    }

    public RecipePattern copy() {
        return new RecipePattern(this);
    }

    public boolean validateRecipe() {
        if (pattern == null || pattern.length != 3 || associativeMap.isEmpty() ||
                resultStack == null || resultStack.stackSize == 0) return false;

        int emptyRowCount = 0;

        for (String str : pattern) {
            if (str.isEmpty() || str.length() > 3) return false;
            else if (str.equals(EMPTY_ROW)) emptyRowCount++;
        }

        return emptyRowCount <= 1;
    }

    public RecipePattern addAssociation(char c, Object obj) {
        if (obj instanceof String || obj instanceof Item || obj instanceof Block) {
            associativeMap.put(c, obj);

            // return true;
        }

        // return false;
        return this;
    }

    public RecipePattern addResultStack(ItemStack stack) {
        if (stack != null && stack.stackSize > 0) resultStack = stack.copy();

        return this;
    }

    public void registerRecipe() {
        if (validateRecipe()) {
            Object[] objects = new Object[associativeMap.size() << 1];
            int counter = 0;

            for (Entry<Character, Object> e : associativeMap.entrySet()) {
                objects[counter++] = e.getKey();
                objects[counter++] = e.getValue();
            }

            if (shapedRecipe)
                GameRegistry.addRecipe(createShapedRecipe(resultStack, pattern[0], pattern[1], pattern[2], objects));
            else
                GameRegistry.addRecipe(createShapelessRecipe(resultStack, pattern[0], pattern[1], pattern[2], objects));
        }
    }

    protected static ShapedOreRecipe createShapedRecipe(ItemStack result, String top, String middle, String bottom, Object... objects) {
        if (objects == null || objects.length == 0 || objects.length % 2 != 0) return null;

        return new ShapedOreRecipe(result, top, middle, bottom, objects);
    }

    protected static ShapelessOreRecipe createShapelessRecipe(ItemStack resultStack, String top, String middle, String bottom, Object... objects) {
        if (objects == null || objects.length == 0 || objects.length % 2 != 0) return null;

        return new ShapelessOreRecipe(resultStack, top, middle, bottom, objects);
    }

}
