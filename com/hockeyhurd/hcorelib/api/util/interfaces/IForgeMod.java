package com.hockeyhurd.hcorelib.api.util.interfaces;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * Simple interface for forge mods.
 *
 * @author hockeyhurd
 * @version 3/23/2016.
 */
public interface IForgeMod {

	/**
	 * Pre-Init phase.
	 *
	 * @param event FMLPreInitializationEvent.
	 */
	void preInit(FMLPreInitializationEvent event);

	/**
	 * Init phase.
	 *
	 * @param event FMLInitializationEvent
	 */
	void init(FMLInitializationEvent event);

	/**
	 * Post-Init phase.
	 *
	 * @param event FMLPostInitializationEvent.
	 */
	void postInit(FMLPostInitializationEvent event);

	/**
	 * Server starting event.
	 *
	 * @param event FMLServerStartingEvent.
	 */
	void serverStartingEvent(FMLServerStartingEvent event);

	/**
	 * Server started event.
	 *
	 * @param event FMLServerStartedEvent.
	 */
	void serverStartedEvent(FMLServerStartedEvent event);

}
