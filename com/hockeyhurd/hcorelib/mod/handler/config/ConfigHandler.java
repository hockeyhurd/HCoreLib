package com.hockeyhurd.hcorelib.mod.handler.config;

import com.hockeyhurd.hcorelib.api.handler.config.AbstractConfigHandler;
import com.hockeyhurd.hcorelib.api.util.AbstractReference;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
	}

	public void handleConfiguration() {
		this.loadConfig();
		
		this.updateCheck = this.getSuggestedConfig().getBoolean("update-check", "General", true,
				"Ability to turn off update checking.");
		this.debugMode = this.getSuggestedConfig().getBoolean("debug mode", "General", false,
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
