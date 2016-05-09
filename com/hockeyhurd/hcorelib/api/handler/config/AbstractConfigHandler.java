package com.hockeyhurd.hcorelib.api.handler.config;

import com.hockeyhurd.hcorelib.api.util.AbstractReference;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Abstract class for creating configs.
 *
 * @author hockeyhurd
 * @version 4/25/16
 */
public abstract class AbstractConfigHandler {

	protected final FMLPreInitializationEvent event;
	protected Configuration config;
	public final String PATH;
	protected String modID;

	/**
	 * Abstract constructor used to initialize configs, paths, and mod related identification.
	 *
	 * @param event event to load from.
	 * @param classRef class refernce to your pre-made Reference.class in which extends AbstractReference appropriately.
	 */
	public AbstractConfigHandler(FMLPreInitializationEvent event, Class<? extends AbstractReference> classRef) {
		this.event = event;
		
		try {
			this.modID = classRef.getDeclaredField("MOD_NAME").get(classRef).toString();
		}

		catch (Exception e) {
			HCoreLibMain.logHelper.severe("Could not find MOD_NAME! Please make sure you have an appropriate reference class!");
			this.PATH = null;
			return;
		}
		
		this.PATH = event.getModConfigurationDirectory() + File.separator + modID + File.separator;
		this.config = new Configuration(new File(PATH + event.getSuggestedConfigurationFile().getName()));
		this.config.load();
		this.config.save();
	}
	
	/**
	 * Allows for more than one config. 
	 * <br><bold>NOTE:</bold> This should not be used for default config but rather additional configs.
	 *
	 * @param name name of file with extension. ex: "wrenchables.cfg".
	 */
	public AbstractConfigHandler(FMLPreInitializationEvent event, Class<? extends AbstractReference> classRef, String name) {
		this.event = event;
		
		try {
			this.modID = classRef.getDeclaredField("MOD_NAME").get(classRef).toString();
		}

		catch (Exception e) {
			HCoreLibMain.logHelper.severe("Could not find MOD_NAME! Please make sure you have an appropriate reference class!");
			this.PATH = null;
			return;
		}
		
		this.PATH = event.getModConfigurationDirectory() + File.separator + modID + File.separator;
		this.config = new Configuration(new File(this.PATH + name));
		
		if (this.config != null) {
			this.config.load();
			this.config.save();
		}
		
		else HCoreLibMain.logHelper.severe("Error generation config at path:\t" + (this.PATH + name));
	}

	/**
	 * Get String categories.
	 *
	 * @return String[].
     */
	public String[] getCategoryNames() {
		final Set<String> categoryNames = config.getCategoryNames();

		return categoryNames.toArray(new String[categoryNames.size()]);
	}

	/**
	 * Gets the categories in the config.
	 *
	 * @return ConfigCategory[].
	 */
	public ConfigCategory[] getCategories() {
		final String[] names = getCategoryNames();
		final List<ConfigCategory> list = new ArrayList<ConfigCategory>(names.length);

		for (String string : names) {
			list.add(config.getCategory(string));
		}

		return list.toArray(new ConfigCategory[list.size()]);
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
	 * Class you can use to call and set-UP own way of handling your config file.
	 */
	public abstract void handleConfiguration();

}
