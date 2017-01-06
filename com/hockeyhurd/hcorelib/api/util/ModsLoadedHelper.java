package com.hockeyhurd.hcorelib.api.util;

import net.minecraftforge.fml.common.Loader;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Helper class used to detect if mod 'x' is/was loaded.
 *
 * @author hockeyhurd
 * @version 1/6/2017.
 */
public final class ModsLoadedHelper {

	private Map<String, Boolean> map;

	private static final ModsLoadedHelper clazzInstance = new ModsLoadedHelper();

	private ModsLoadedHelper() {
	}

	/**
	 * Gets the helper's instance.
	 *
	 * @return ModsLoadedHelper.
	 */
	public static ModsLoadedHelper getInstance() {
		return clazzInstance;
	}

	/**
	 * Checks if mod is loaded.
	 *
	 * @param modID ModID to check.
	 * @return boolean result.
	 */
	private static boolean isLoaded(String modID) {
		return Loader.isModLoaded(modID);
	}

	/**
	 * Initializes the local mapping.
	 */
	private void initLocalMapping() {
		addModLocal("ThermalExpansion");
		addModLocal("CoFHCore");
		addModLocal("IC2");
		addModLocal("NotEnoughItems");
		addModLocal("JEI");
		addModLocal("ProjectZed");
	}

	/**
	 * Initializes mapping.
	 */
	public void init() {
		if (map == null) {
			map = new TreeMap<String, Boolean>();

			initLocalMapping();
		}
	}

	/**
	 * Adds a mod to the mapping and checks to see
	 * if it is loaded or not.
	 *
	 * @param modID String modID.
	 */
	public void addMod(String modID) {
		if (!map.containsKey(modID))
			addModLocal(modID);
	}

	/**
	 * Local method for loading a modID.
	 *
	 * @param modID String modID.
	 */
	private void addModLocal(String modID) {
		map.put(modID, isLoaded(modID));
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();

		for (Entry<String, Boolean> entry : map.entrySet()) {
			builder.append(String.format("%s: %s%s", entry.getKey(), entry.getValue(), System.lineSeparator()));
		}

		return builder.toString();
	}
}
