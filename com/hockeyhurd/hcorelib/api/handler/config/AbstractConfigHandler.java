package com.hockeyhurd.hcorelib.api.handler.config;

import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.util.*;

/**
 * Abstract class for creating configs.
 *
 * @author hockeyhurd
 * @version 4/25/16
 */
public abstract class AbstractConfigHandler implements ISynchableConfig {

	protected final FMLPreInitializationEvent event;
	protected Configuration config;
	protected ConfigChannel configChannel;
	protected static final Map<String, long[]> configCodeMap = new HashMap<String, long[]>();
	public final String PATH;
	protected String modID;

	/**
	 * Abstract constructor used to initialize configs, paths, and mod related identification.
	 *
	 * @param event event to load from.
	 * @param modID string mod id.
	 */
	public AbstractConfigHandler(FMLPreInitializationEvent event, String modID) {
		this.event = event;
		this.modID = modID;

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
	public AbstractConfigHandler(FMLPreInitializationEvent event, String modID, String name) {
		this.event = event;
		this.modID = modID;

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

	@Override
	public AbstractConfigHandler getInstance() {
		return this;
	}

	@Override
	public ConfigChannel getConfigChannel() {
		return configChannel;
	}

	@Override
	public boolean isConfigChannelValid(ConfigChannel configChannel) {
		if (configChannel == null || !configChannel.validate() ||
				this.configChannel == null || !this.configChannel.validate() ||
				configChannel.keyCode != this.configChannel.keyCode ||
				!configChannel.channelID.equals(this.configChannel.channelID)) return false;

		else if (configCodeMap.isEmpty()) return false;
		else if (!configCodeMap.containsKey(configChannel.channelID)) return false;
		else {
			final long[] codeArr = configCodeMap.get(configChannel.channelID);

			if (codeArr == null || codeArr.length == 0) return false;
			else {
				for (long l : codeArr) {
					if (l == configChannel.keyCode) return true;
				}

				return false;
			}
		}
	}

}
