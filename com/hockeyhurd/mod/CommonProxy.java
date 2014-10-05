package com.hockeyhurd.mod;

import java.util.HashMap;

import net.minecraftforge.common.MinecraftForge;

import com.hockeyhurd.api.handler.NotifyPlayerOnJoinHandler;
import com.hockeyhurd.api.handler.UpdateHandler;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	protected UpdateHandler updateHandler;
	protected HashMap<Short, String> updateMap;
	public boolean updateFlag;
	
	public CommonProxy() {
	}
	
	public void registerRenderInformation() {
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
