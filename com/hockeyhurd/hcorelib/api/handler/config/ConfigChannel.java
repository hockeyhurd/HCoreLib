package com.hockeyhurd.hcorelib.api.handler.config;

import com.hockeyhurd.hcorelib.api.util.StringUtils;
import io.netty.buffer.ByteBuf;

import java.util.LinkedList;
import java.util.List;

/**
 * Class intended to contain necessary requirements
 * for sending config data from client to server.
 *
 * @author hockeyhurd
 * @version 5/29/2016.
 */
public final class ConfigChannel {

	public final String channelID;
	public final long keyCode;
	private final List<ConfigData> dataList;

	/**
	 * Constructs a ConfigChannel.
	 *
	 * @param channelID String channel ID.
	 * @param configDatas ConfigData to import.
     */
	public ConfigChannel(String channelID, long keyCode, ConfigData... configDatas) {
		this.channelID = channelID;
		this.keyCode = keyCode;
		dataList = new LinkedList<ConfigData>();

		for (ConfigData configData : configDatas) {
			if (configData != null && configData.validate()) dataList.add(configData);
		}
	}

	/**
	 * Checks and validates this channel has sufficient data.
	 *
	 * @return boolean result.
	 */
	public boolean validate() {
		return StringUtils.nullCheckString(channelID) && !dataList.isEmpty();
	}

	public static boolean validate(ConfigChannel configChannel) {
		return configChannel != null && configChannel.validate();
	}

	/**
	 * Handles reading of config data through channel.
	 *
	 * @param buf ByteBuf.
     */
	public void readBuf(ByteBuf buf) {
		if (!validate()) return;

		for (ConfigData data : dataList)
			data.readBuf(buf);
	}

	/**
	 * Handles writing of config data through channel.
	 *
	 * @param buf ByteBuf.
     */
	public void writeBuf(ByteBuf buf) {
		if (!validate()) return;

		for (ConfigData data : dataList)
			data.writeBuf(buf);
	}

}
