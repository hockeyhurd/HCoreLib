package com.hockeyhurd.hcorelib.mod.handler.packet;

import com.hockeyhurd.hcorelib.mod.LibReference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Packet handling class
 *
 * @author hockeyhurd
 * @version 5/29/2016.
 */
public final class PacketHandler {

	public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(LibReference.MOD_NAME);
	private static int id = 0;

	private static final PacketHandler clazzInstance = new PacketHandler();

	private PacketHandler() {
	}

	/**
	 * Instance of this class.
	 *
	 * @return PacketHandler instance.
	 */
	public static PacketHandler getInstance() {
		return clazzInstance;
	}

	public void init() {
		registerMessageHandler(PacketFurnace.class, PacketFurnace.class, getNextID(), Side.CLIENT);
	}

	private static <REQ extends IMessage, REPLY extends IMessage> void registerMessageHandler(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler,
			Class<REQ> requestMessageType, int discriminator, Side side) {

		NETWORK_WRAPPER.registerMessage(messageHandler, requestMessageType, discriminator, side);
	}

	private static int getNextID() {
		return id++;
	}

}
