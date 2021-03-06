package com.hockeyhurd.hcorelib.mod.handler.config;

import com.hockeyhurd.hcorelib.api.handler.config.AbstractConfigHandler;
import com.hockeyhurd.hcorelib.api.handler.config.ConfigChannel;
import com.hockeyhurd.hcorelib.api.handler.config.ConfigData;
import com.hockeyhurd.hcorelib.mod.LibReference;
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
	
	public ConfigHandler(FMLPreInitializationEvent event, String modId) {
		super(event, modId);

		final long keyCode = System.currentTimeMillis();
		this.configChannel = new ConfigChannel(LibReference.MOD_ID, keyCode, new ConfigData<Boolean>(true, "debugMode", debugMode));
		configCodeMap.put(modID, new long[] { keyCode });
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
