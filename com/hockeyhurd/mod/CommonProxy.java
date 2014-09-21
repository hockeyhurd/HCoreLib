package com.hockeyhurd.mod;

import java.util.HashMap;

import net.minecraftforge.common.MinecraftForge;

import com.hockeyhurd.api.handler.NotifyPlayerOnJoinHandler;
import com.hockeyhurd.api.handler.UpdateHandler;

public class CommonProxy {

	protected UpdateHandler updateHandler;
	protected HashMap<Short, String> updateMap;
	public boolean updateFlag;
	
	public CommonProxy() {
	}
	
	public void init() {
		registerMCForgeEventHandlers();
	}
	
	protected void registerMCForgeEventHandlers() {
	}
	
	public void registerUpdateHandler() {
		updateHandler = new UpdateHandler(LibReference.class);
		updateHandler.check();
		updateMap = updateHandler.getMap();
		updateFlag = updateHandler.getUpToDate();
		
		MinecraftForge.EVENT_BUS.register(new NotifyPlayerOnJoinHandler(updateHandler, updateMap, LibReference.class, updateFlag));
	}
	
}
