package com.hockeyhurd.hcorelib.api.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Class used to convert shaped/shapeless recipes to json files using the RecipePattern interface
 *
 * @see com.hockeyhurd.hcorelib.api.handler.RecipePattern
 *
 * @author hockeyhurd
 * @version 12/26/2017.
 */
public class RecipeGen {

    private static final Gson GSON_BUILDER = new GsonBuilder().setPrettyPrinting().create();
    private final File RECIPE_DIRECTORY;

    public RecipeGen(final String modID) {
        RECIPE_DIRECTORY = new File("recipeoutput" + File.separator + modID);

        if (!RECIPE_DIRECTORY.exists()) {
            final boolean result = RECIPE_DIRECTORY.mkdirs();

            if(!result)
                throw new IllegalStateException("Cannot create recipe output dir!");
        }
    }

    public void addShapedRecipe(final RecipePattern pattern) {
        final Map<String, Object> json = new TreeMap<String, Object>();

        json.put("type", "minecraft:crafting_shaped");
        json.put("pattern", pattern.getPattern());
        json.put("group", pattern.getResultStack().getItem().getRegistryName().toString());

        // fill key section.
        getKeyMap(pattern.getAssociativeMap(), json);

        // fill result section.
        getResultMap(pattern.getResultStack(), json);

        final String suffix = pattern.getResultStack().getItem().getHasSubtypes() ? "_" + pattern.getResultStack().getItemDamage() : "";
        String fileName = pattern.getResultStack().getItem().getRegistryName().getResourcePath() + suffix + ".json";
        File outputJsonFile = new File(RECIPE_DIRECTORY, fileName);
        // File outputJsonFile;

        while (outputJsonFile.exists()) {
            // fileName += "_alt";
            fileName = fileName.substring(0, fileName.length() - ".json".length()) + "_alt.json";
            outputJsonFile = new File(RECIPE_DIRECTORY, fileName);
        }

        try (FileWriter fileWriter = new FileWriter(outputJsonFile)) {
            GSON_BUILDER.toJson(json, fileWriter);
        }

        catch (IOException e) {
            HCoreLibMain.logHelper.severe("Failed to write JSON", e);
        }
    }

    public void addShapelessRecipe(final RecipePattern pattern) {

    }

    private static Map<String, Object> getKeyMap(Map<Character, Object> associativeMap, Map<String, Object> json) {
        final Map<String, Object> keyMap = new TreeMap<String, Object>();

        for (Entry<Character, Object> entry : associativeMap.entrySet()) {
            final Map<String, Object> innerMapping = new TreeMap<String, Object>();

            // Is ore dictionary.
            if (entry.getValue() instanceof String) {
                innerMapping.put("type", "forge:ore_dict");
                innerMapping.put("ore", entry.getValue());
            }

            // Is block.
            else if (entry.getValue() instanceof Block) {
                innerMapping.put("item", ((Block) entry.getValue()).getRegistryName().toString());
            }

            // Is item.
            else if (entry.getValue() instanceof Item) {
                innerMapping.put("item", ((Item) entry.getValue()).getRegistryName().toString());
            }

            // Else can't figure out what it is!!
            else {
                HCoreLibMain.logHelper.severe("Can't inject data into keymap!", entry.getValue() != null ? entry.getValue() : "<null>");
                return null;
            }

            keyMap.put(entry.getKey().toString(), innerMapping);
        }

        json.put("key", keyMap);

        return keyMap;
    }

    private static Map<String, Object> getResultMap(ItemStack resultStack, Map<String, Object> json) {
        final Map<String, Object> resultMap = new TreeMap<String, Object>();

        resultMap.put("item", resultStack.getItem().getRegistryName().toString());
        resultMap.put("count", resultStack.getCount());
        json.put("result", resultMap);

        return resultMap;
    }

}
