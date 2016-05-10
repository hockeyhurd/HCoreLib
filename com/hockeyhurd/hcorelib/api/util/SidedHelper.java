package com.hockeyhurd.hcorelib.api.util;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Class used to help find the side of the world.
 * 
 * @author hockeyhurd
 * @version Oct 19, 2014
 */
public final class SidedHelper {

	private static final FMLCommonHandler commonHandler = FMLCommonHandler.instance();

	private SidedHelper() {
	}

	/**
	 * Alternate function used to find if the side 
	 * is server from calling world.isRemote
	 *
	 * @deprecated Not guranteed to work.
	 *
	 * @param side Boolean var obtained from calling world.isRemote.
	 * @return True if side is server, else false if client.
	 */
	@Deprecated
	public static boolean isServer(boolean side) {
		return !side;
	}
	
	/**
	 * Gets the side of the world from world object.
	 *
	 * @deprecated Although is guaranteed working (provided world is NOT null), use parameter-less function call
	 * for better stability.
	 *
	 * @param world World object.
	 * @return True if server, else false if client.
	 */
	@Deprecated
	public static boolean isServer(World world) {
		return !world.isRemote;
	}
	
	/**
	 * Gets the side of the world from world object.
	 *
	 * @deprecated Although is guaranteed working (provided world is NOT null), use parameter-less function call
	 * for better stability.
	 *
	 * @param world World object.
	 * @return True if client, else false if server.
	 */
	@Deprecated
	public static boolean isClient(World world) {
		return world.isRemote;
	}

	/**
	 * Gets if the side is on the server.
	 *
	 * @return True if server side, else returns false.
	 */
	public static boolean isServer() {
		return commonHandler.getEffectiveSide().isServer();
	}

	/**
	 * Gets if the side is on the client.
	 *
	 * @return True if client side, else returns false.
	 */
	public static boolean isClient() {
		return commonHandler.getEffectiveSide().isClient();
	}

	/**
	 * Gets the effective side.
	 *
	 * @return Effective side.
	 */
	public static Side getSide() {
		return commonHandler.getEffectiveSide();
	}

}
