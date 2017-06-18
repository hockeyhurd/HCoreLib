package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.handler.NotifyPlayerOnJoinHandler;
import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.handler.UpdateHandler;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.api.util.interfaces.IProxy;
import com.hockeyhurd.hcorelib.mod.handler.GuiHandler;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTESRTest;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTest;
import com.hockeyhurd.hcorelib.mod.tileentity.TileFurnace;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockComponent;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;

public class CommonProxy implements IProxy {

	protected UpdateHandler updateHandler;
	protected HashMap<String, String> updateMap;
	protected static GuiHandler guiHandler;
	public boolean updateFlag;
	
	public CommonProxy() {
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
		registerItems();
		registerGuiHandler();
		registerEventHandlers();
	}
	
	protected void registerMCForgeEventHandlers() {
	}
	
	protected void registerBlocks() {
		// GameRegistry.registerBlock(HCoreLibMain.white, "HiddenWhite");
		GameRegistry.register(HCoreLibMain.testBlock);
		GameRegistry.register(HCoreLibMain.testBlock.getItemBlock().setRegistryName(HCoreLibMain.testBlock.getRegistryName()));

		for (RecipePattern pattern : ((ICraftableRecipe) HCoreLibMain.testBlock).getRecipePatterns()) {
			if (pattern != null) pattern.registerRecipe();
		}

		GameRegistry.register(HCoreLibMain.testTile);
		GameRegistry.register(HCoreLibMain.testTESRTile);
		GameRegistry.register(HCoreLibMain.testFurnace);
		GameRegistry.register(HCoreLibMain.testTile.getItemBlock().setRegistryName(HCoreLibMain.testTile.getRegistryName()));
		GameRegistry.register(HCoreLibMain.testTESRTile.getItemBlock().setRegistryName(HCoreLibMain.testTESRTile.getRegistryName()));
		GameRegistry.register(HCoreLibMain.testFurnace.getItemBlock().setRegistryName(HCoreLibMain.testFurnace.getRegistryName()));
		GameRegistry.register(HCoreLibMain.multiblockController);
		GameRegistry.register(HCoreLibMain.multiblockController.getItemBlock().setRegistryName(HCoreLibMain.multiblockController.getRegistryName()));
		GameRegistry.register(HCoreLibMain.multiblockComponent);
		GameRegistry.register(HCoreLibMain.multiblockComponent.getItemBlock().setRegistryName(HCoreLibMain.multiblockComponent.getRegistryName()));
	}

	protected void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityTest.class, "testTile");
		GameRegistry.registerTileEntity(TileEntityTESRTest.class, "testTESRTile");
		GameRegistry.registerTileEntity(TileFurnace.class, "testFurnaceTile");
		GameRegistry.registerTileEntity(MultiblockController.class, "multiblockController");
		GameRegistry.registerTileEntity(MultiblockComponent.class, "multiblockComponent");
	}

	protected void registerItems() {
		GameRegistry.register(HCoreLibMain.testItem);
		GameRegistry.register(HCoreLibMain.testMetaItem);
		GameRegistry.register(HCoreLibMain.itemCalculator);
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
