package com.hockeyhurd.mod;

import com.hockeyhurd.api.handler.NotifyPlayerOnJoinHandler;
import com.hockeyhurd.api.handler.UpdateHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;

public class CommonProxy {

	protected UpdateHandler updateHandler;
	protected HashMap<String, String> updateMap;
	public boolean updateFlag;
	
	public CommonProxy() {
	}

	public void registerRenderInformation() {
	}

	public void registerInputHandlers() {
	}
	
	public void init() {
		registerMCForgeEventHandlers();
		registerBlocks();
	}
	
	protected void registerMCForgeEventHandlers() {
	}
	
	protected void registerBlocks() {
		GameRegistry.registerBlock(HCoreLibMain.white, "HiddenWhite");
	}
	
	public void registerUpdateHandler() {
		updateHandler = new UpdateHandler(LibReference.class);
		updateHandler.check();
		updateMap = updateHandler.getMap();
		updateFlag = updateHandler.getUpToDate();
		
		MinecraftForge.EVENT_BUS.register(new NotifyPlayerOnJoinHandler(updateHandler, updateMap, LibReference.class, updateFlag, true, HCoreLibMain.configHandler.allowUpdating()));
	}
	
}
