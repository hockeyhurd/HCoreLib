package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.handler.NotifyPlayerOnJoinHandler;
import com.hockeyhurd.hcorelib.api.handler.UpdateHandler;
import com.hockeyhurd.hcorelib.api.util.interfaces.IProxy;
import com.hockeyhurd.hcorelib.mod.block.TileEntityTest;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;

public class CommonProxy implements IProxy {

	protected UpdateHandler updateHandler;
	protected HashMap<String, String> updateMap;
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
	}
	
	protected void registerMCForgeEventHandlers() {
	}
	
	protected void registerBlocks() {
		// GameRegistry.registerBlock(HCoreLibMain.white, "HiddenWhite");
		GameRegistry.register(HCoreLibMain.testBlock);
		GameRegistry.register(HCoreLibMain.testBlock.getItemBlock().setRegistryName(HCoreLibMain.testBlock.getRegistryName()));

		GameRegistry.register(HCoreLibMain.testTile);
		GameRegistry.register(HCoreLibMain.testTile.getItemBlock().setRegistryName(HCoreLibMain.testTile.getRegistryName()));
	}

	protected void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityTest.class, "testTile");
	}

	protected void registerItems() {
		GameRegistry.register(HCoreLibMain.testItem);
	}

	@Override
	public void registerUpdateHandler() {
		updateHandler = new UpdateHandler(LibReference.class);
		updateHandler.check();
		updateMap = updateHandler.getMap();
		updateFlag = updateHandler.getUpToDate();
		
		MinecraftForge.EVENT_BUS.register(new NotifyPlayerOnJoinHandler(updateHandler, updateMap, LibReference.class, updateFlag, true, HCoreLibMain.configHandler.allowUpdating()));
	}

	@Override
	public boolean isClient() {
		return false;
	}

}
