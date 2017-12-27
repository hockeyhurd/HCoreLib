package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.client.util.ModelRegistry;
import com.hockeyhurd.hcorelib.api.handler.NotifyPlayerOnJoinHandler;
import com.hockeyhurd.hcorelib.api.handler.RecipeGen;
import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.handler.UpdateHandler;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.api.util.interfaces.IProxy;
import com.hockeyhurd.hcorelib.mod.common.ModRegistry;
import com.hockeyhurd.hcorelib.mod.handler.GuiHandler;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTESRTest;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTest;
import com.hockeyhurd.hcorelib.mod.tileentity.TileFurnace;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockComponent;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.actors.migration.pattern;

import java.util.HashMap;

public class CommonProxy implements IProxy {

	protected UpdateHandler updateHandler;
	protected HashMap<String, String> updateMap;
	protected static GuiHandler guiHandler;
	protected RecipeGen recipeGen;
	public boolean updateFlag;
	
	public CommonProxy() {
        recipeGen = new RecipeGen(LibReference.MOD_ID);
	}

	@Override
	public void registerRenderInformation() {
	}

	@Override
	public void registerInputHandlers() {
	}

	@Override
	public void init() {
		registerMCForgeEventHandlers();
		registerBlocks();
		registerTileEntities();
		// registerItems();
		registerGuiHandler();
		registerEventHandlers();
	}
	
	protected void registerMCForgeEventHandlers() {
	}
	
	protected void registerBlocks() {
		// GameRegistry.registerBlock(HCoreLibMain.white, "HiddenWhite");
		if (!HCoreLibMain.configHandler.isDebugMode())
			return;

		for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModBlocks.testBlock.getBlock()).getRecipePatterns()) {
			if (pattern != null)
				pattern.registerRecipe(recipeGen);
		}
	}

	protected void registerTileEntities() {
		if (!HCoreLibMain.configHandler.isDebugMode())
			return;

		GameRegistry.registerTileEntity(TileEntityTest.class, "testTile");
		GameRegistry.registerTileEntity(TileEntityTESRTest.class, "testTESRTile");
		GameRegistry.registerTileEntity(TileFurnace.class, "testFurnaceTile");
		GameRegistry.registerTileEntity(MultiblockController.class, "multiblockController");
		GameRegistry.registerTileEntity(MultiblockComponent.class, "multiblockComponent");
	}

	protected void registerItems() {
		for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.itemCalculator.getItem()).getRecipePatterns()) {
			if (pattern != null)
				pattern.registerRecipe(recipeGen);
		}

		for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.itemMeasureTape.getItem()).getRecipePatterns()) {
			if (pattern != null)
				pattern.registerRecipe(recipeGen);
		}

        for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.wrench.getItem()).getRecipePatterns()) {
            if (pattern != null)
                pattern.registerRecipe(recipeGen);
        }

		for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.witchHat.getItem()).getRecipePatterns()) {
			if (pattern != null)
				pattern.registerRecipe(recipeGen);
		}

		if (HCoreLibMain.configHandler.isDebugMode()) {
            for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.buildersWand.getItem()).getRecipePatterns()) {
                if (pattern != null)
                    pattern.registerRecipe(recipeGen);
            }
		}
	}

	protected void registerGuiHandler() {
		if (guiHandler != null) NetworkRegistry.INSTANCE.registerGuiHandler(HCoreLibMain.instance, guiHandler);
		else {
			guiHandler = new GuiHandler();
			NetworkRegistry.INSTANCE.registerGuiHandler(HCoreLibMain.instance, guiHandler);
		}
	}

	public void registerCraftingRecipes() {

	}

	@Override
	public void registerUpdateHandler() {
		updateHandler = new UpdateHandler(LibReference.MOD_NAME, LibReference.VERSION, LibReference.MOD_URL, LibReference.CHANGELOG_URL);
		updateHandler.check();
		updateMap = updateHandler.getMap();
		updateFlag = updateHandler.getUpToDate();
		
		MinecraftForge.EVENT_BUS.register(new NotifyPlayerOnJoinHandler(updateHandler, updateMap, LibReference.MOD_NAME, updateFlag, true,
				HCoreLibMain.configHandler.allowUpdating()));
	}

	@Override
	public void registerEventHandlers() {
	}

	@Override
	public boolean isClient() {
		return false;
	}

}
