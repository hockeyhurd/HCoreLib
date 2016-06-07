package com.hockeyhurd.hcorelib.api.handler.config;

/**
 * Interfacing for injecting synchable data.
 *
 * @author hockeyhurd
 * @version 5/29/2016.
 */
public interface ISynchableConfig {

	/**
	 * Gets the config's instance.
	 *
	 * @return Config instance.
     */
	ISynchableConfig getInstance();

	/**
	 * Gets the config channel.
	 *
	 * @return ConfigChannel.
     */
	ConfigChannel getConfigChannel();

	/**
	 * Helper function verify config channel is compatible and
	 * can write data to/from this particular config.
	 *
	 * @param configChannel ConfigChannel.
	 * @return boolean result.
     */
	boolean isConfigChannelValid(ConfigChannel configChannel);

}
