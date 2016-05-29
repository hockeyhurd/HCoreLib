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
	private final List<ConfigData> dataList;

	public ConfigChannel(String channelID, ConfigData... configDatas) {
		this.channelID = channelID;
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

	public void readBuf(ByteBuf buf) {
		if (!validate()) return;

		for (ConfigData data : dataList)
			data.readBuf(buf);
	}

	public void writeBuf(ByteBuf buf) {
		if (!validate()) return;

		for (ConfigData data : dataList)
			data.writeBuf(buf);
	}

}
