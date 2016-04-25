package com.hockeyhurd.mod.handler;

import com.hockeyhurd.api.handler.AbstractConfigHandler;
import com.hockeyhurd.api.util.AbstractReference;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * HCoreLib's config handler.
 *
 * @author hockeyhurd
 * @version 4/25/16
 */
public class ConfigHandler extends AbstractConfigHandler {

	private boolean updateCheck;
	private boolean debugMode;
	
	public ConfigHandler(FMLPreInitializationEvent event, Class<? extends AbstractReference> classRef) {
		super(event, classRef);

		categories = new String[] { "General" };
	}

	public void handleConfiguration() {
		this.loadConfig();
		
		this.updateCheck = this.getSuggestedConfig().getBoolean("update-check", categories[0], true,
				"Ability to turn off update checking.");
		this.debugMode = this.getSuggestedConfig().getBoolean("debug mode", categories[0], false,
				"Toggles debugging mode.");
		
		this.saveConfig();
	}
	
	public boolean allowUpdating() {
		return this.updateCheck;
	}

	public boolean isDebugMode() {
		return this.debugMode;
	}

}
