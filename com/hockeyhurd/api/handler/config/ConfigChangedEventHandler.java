package com.hockeyhurd.api.handler.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Event handler for config changes.
 *
 * @author hockeyhurd
 * @version 4/26/2016.
 */
public class ConfigChangedEventHandler {

	protected final String modID;
	protected final AbstractConfigHandler configHandler;

	/**
	 * @param modID ModID of current mod.
	 * @param configHandler AbstractConfigHandler associated with mod.
	 */
	public ConfigChangedEventHandler(String modID, AbstractConfigHandler configHandler) {
		this.modID = modID;
		this.configHandler = configHandler;
	}

	@SubscribeEvent
	public void onConfigChangedEvent(ConfigChangedEvent event) {
		if (event.modID.equals(modID)) configHandler.saveConfig();
	}

}
