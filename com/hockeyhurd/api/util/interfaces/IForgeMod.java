package com.hockeyhurd.api.util.interfaces;

import cpw.mods.fml.common.event.*;

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
