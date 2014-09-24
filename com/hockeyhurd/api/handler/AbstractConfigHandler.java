package com.hockeyhurd.api.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.hockeyhurd.api.util.AbstractReference;
import com.hockeyhurd.mod.HCoreLibMain;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class AbstractConfigHandler {

	private FMLPreInitializationEvent event;
	private Configuration config;
	private final String PATH;
	private String modID;
	
	/**
	 * Abstract constructor used to initialize configs, paths, and mod related identification.
	 * @param event =  event to load from.
	 * @param classRef = class refernce to your pre-made Reference.class in which extends AbstractReference appropriately.
	 */
	public AbstractConfigHandler(FMLPreInitializationEvent event, Class<? extends AbstractReference> classRef) {
		this.event = event;
		
		try {
			this.modID = classRef.getDeclaredField("MOD_NAME").get(classRef).toString();
		}
		catch (Exception e) {
			HCoreLibMain.lh.severe("Could not find MOD_NAME! Please make sure you have an appropriate reference class!");
			this.PATH = null;
			return;
		}
		
		this.PATH = event.getModConfigurationDirectory() + File.separator + modID + File.separator;
		this.config = new Configuration(new File(PATH + event.getSuggestedConfigurationFile().getName()));
		this.config.load();
		this.config.save();
	}
	
	/**
	 * Returns the appropriate config file used in conjunction of handlingConfiguration() method.
	 * @return suggested config file.
	 */
	public Configuration getSuggestedConfig() {
		return this.config;
	}
	
	/**
	 * Provides loading of config.
	 */
	protected void loadConfig() {
		if (this.config != null) this.config.load();
	}
	
	/**
	 * Provides saving of config.
	 */
	protected void saveConfig() {
		if (this.config != null) this.config.save();
	}
	
	/**
	 * Class you can use to call and set-up own way of handling your config file.
	 */
	public abstract void handleConfiguration();

}
