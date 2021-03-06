package com.hockeyhurd.hcorelib.api.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;
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

    private boolean shapedRecipe;
    private ItemStack resultStack;
    private String[] pattern;
    private Map<Character, PatternObject> associativeMap;

    protected RecipePattern() {
        shapedRecipe = true;
        pattern = new String[3];
        associativeMap = new TreeMap<Character, PatternObject>();
    }

    /**
     * Constructs a RecipePattern.
     * <br>null strings are treated as 3-empty spaced string.
     *
     * @param top String top row.
     * @param middle String middle row.
     * @param bottom String bottom row.
     * @param shapedRecipe boolean recipe type.
     */
    public RecipePattern(String top, String middle, String bottom, boolean shapedRecipe) {
        this.shapedRecipe = shapedRecipe;
        pattern = new String[3];

        pattern[0] = top != null ? top : EMPTY_ROW;
        pattern[1] = middle != null ? middle : EMPTY_ROW;
        pattern[2] = bottom != null ? bottom : EMPTY_ROW;

        associativeMap = new TreeMap<Character, PatternObject>();
    }

    /**
     * Copy constructor.
     * <br><bold>NOTE:</bold> result ItemStack is <bold>NOT</bold> copied.
     *
     * @param other RecipePattern to copy.
     */
    public RecipePattern(RecipePattern other) {
        shapedRecipe = other.shapedRecipe;
        pattern = new String[3];

        for (int i = 0; i < pattern.length; i++)
            pattern[i] = other.pattern[i];

        associativeMap = new TreeMap<Character, PatternObject>();
    }

    /**
     * Creates a copy of the current RecipePattern.
     *
     * @return RecipePattern copy.
     */
    public RecipePattern copy() {
        return new RecipePattern(this);
    }

    public boolean isShapedRecipe() {
        return shapedRecipe;
    }

    public ItemStack getResultStack() {
        return resultStack;
    }

    public String[] getPattern() {
        return pattern;
    }

    public Map<Character, PatternObject> getAssociativeMap() {
        return associativeMap;
    }

    /**
     * Validates the crafting pattern.
     *
     * @return boolean result.
     */
    public boolean validateRecipe() {
        if (pattern == null || pattern.length != 3 || associativeMap.isEmpty() ||
                resultStack == null || resultStack.getCount() == 0)
            return false;

        int emptyRowCount = 0;

        for (String str : pattern) {
            if (str.isEmpty() || str.length() > 3)
                return false;
            else if (str.equals(EMPTY_ROW))
                emptyRowCount++;
        }

        return emptyRowCount <= 1;
    }

    /**
     * Associates a char value with an object (either
     * an Item or Block).
     *
     * @param c char.
     * @param obj Object (Item or Block).
     * @return Recipe pattern instance.
     */
    public RecipePattern addAssociation(char c, PatternObject obj) {
        associativeMap.put(c, obj);

        return this;
    }

    public RecipePattern addAssociation(char c, String obj) {
        return addAssociation(c, obj, 1, 0);
    }

    public RecipePattern addAssociation(char c, String obj, int count, int metadata) {
        associativeMap.put(c, new PatternObject(PatternObject.ObjectType.ORE_DICT, obj, count, metadata));

        return this;
    }

    public RecipePattern addAssociation(char c, Block block) {
        return addAssociation(c, block, 1, 0);
    }

    public RecipePattern addAssociation(char c, Block block, int count, int metadata) {
        associativeMap.put(c, new PatternObject(PatternObject.ObjectType.BLOCK, block, count, metadata));

        return this;
    }

    public RecipePattern addAssociation(char c, Item item) {
        return addAssociation(c, item, 1, 0);
    }

    public RecipePattern addAssociation(char c, Item item, int count, int metadata) {
        associativeMap.put(c, new PatternObject(PatternObject.ObjectType.ITEM, item, count, metadata));

        return this;
    }

    /**
     * Sets the resulting ItemStack to the crafting recipe.
     *
     * @param stack ItemStack result.
     * @return Recipe pattern instance.
     */
    public RecipePattern setResultStack(ItemStack stack) {
        if (stack != null && stack.getCount() > 0)
            resultStack = stack.copy();

        return this;
    }

    /**
     * Checks if recipe pattern is valid and registers
     * crafting pattern accordingly.
     */
    public void registerRecipe(RecipeGen recipeGen) {
        if (validateRecipe()) {
            if (shapedRecipe)
                recipeGen.addShapedRecipe(this);
            else
                recipeGen.addShapelessRecipe(this);
        }
    }

    /**
     * @deprecated Doesn't register anything actually in 1.12.x+
     *
     * @param result
     * @param objects
     * @return
     */
    @Deprecated
    protected static ShapedOreRecipe createShapedRecipe(ItemStack result, Object... objects) {
        // if (objects == null || objects.length == 0) return null;

        // return new ShapedOreRecipe(result, objects);
        return null;
    }

    /**
     * @deprecated Doesn't register anything actually in 1.12.x+
     *
     * @param resultStack
     * @param objects
     * @return
     */
    @Deprecated
    protected static ShapelessOreRecipe createShapelessRecipe(ItemStack resultStack, Object... objects) {
        // if (objects == null || objects.length == 0) return null;

        // return new ShapelessOreRecipe(resultStack, objects);
        return null;
    }

    public static class PatternObject {

        public enum ObjectType {
            INVALID, BLOCK, ITEM, ORE_DICT;

            public static ObjectType decodeType(@Nonnull final Object object) {
                if (object instanceof String)
                    return ORE_DICT;
                else if (object instanceof Block)
                    return BLOCK;
                else if (object instanceof Item)
                    return ITEM;

                return INVALID;
            }
        }

        private ObjectType objectType;
        private Object object;
        private int count;
        private int metadata;

        public PatternObject(@Nonnull Object object, int count, int metadata) {
            this.object = object;
            this.count = count;
            this.metadata = metadata;

            this.objectType = ObjectType.decodeType(object);
        }

        protected PatternObject(ObjectType objectType, Object object, int count, int metadata) {
            this.objectType = objectType;
            this.object = object;
            this.count = count;
            this.metadata = metadata;
        }

        public ObjectType getObjectType() {
            return objectType;
        }

        public Object getObject() {
            return object;
        }

        public int getCount() {
            return count;
        }

        public int getMetadata() {
            return metadata;
        }
    }

}
