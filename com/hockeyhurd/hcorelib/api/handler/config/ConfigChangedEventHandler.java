package com.hockeyhurd.hcorelib.api.handler.config;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
		if (event.getModID().equals(modID)) configHandler.saveConfig();
	}

}
