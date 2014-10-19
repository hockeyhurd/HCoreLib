package com.hockeyhurd.api.util;

import net.minecraft.world.World;

/**
 * Class used to help find the side of the world.
 * 
 * @author hockeyhurd
 * @version Oct 19, 2014
 */
public class SidedHelper {

	/**
	 * Alternate function used to find if the side 
	 * is server from calling world.isRemote
	 * @param side = boolean var obtained from calling world.isRemote.
	 * @return true if side is server, else false if client.
	 */
	public static boolean isServer(boolean side) {
		return side ? false : true;
	}
	
	/**
	 * Gets the side of the world from world object.
	 * 
	 * @param world = world object.
	 * @return true if server, else false if client.
	 */
	public static boolean isServer(World world) {
		return world.isRemote ? false : true;
	}
	
	/**
	 * Gets the side of the world from world object.
	 * 
	 * @param world = world object.
	 * @return true if client, else false if server.
	 */
	public static boolean isClient(World world) {
		return world.isRemote;
	}
	
}
