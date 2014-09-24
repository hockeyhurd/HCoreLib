package com.hockeyhurd.mod.handler;

import com.hockeyhurd.api.handler.AbstractConfigHandler;
import com.hockeyhurd.api.util.AbstractReference;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler extends AbstractConfigHandler {

	private boolean updateCheck;
	
	public ConfigHandler(FMLPreInitializationEvent event, Class<? extends AbstractReference> classRef) {
		super(event, classRef);
	}

	public void handleConfiguration() {
		this.loadConfig();
		
		this.updateCheck = this.getSuggestedConfig().getBoolean("update-check", "General", true, "Ability to turn off update checking.");
		
		this.saveConfig();
	}
	
	public boolean allowUpdating() {
		return this.updateCheck;
	}

}
