package com.hockeyhurd.hcorelib.api.util;

public abstract class AbstractReference {

	/** Current build number. */
	public static short BUILD;
	
	/** Current version with included build number. */
	public static String VERSION;

	/** Current Minecraft version. */
	public static final String MINECRAFT_VERSION = "1.9.4";

	/** Current mod name. */
	public static String MOD_NAME;
	
	/** Not required but is available. NOTE: if not using it, set to null! */
	public static String MOD_URL;

	/** Not required but is available. NOTE: if not using it, set to null! */
	public static String CHANGELOG_URL;
	
	/**
	 * To use this class referencing, simple extend this class with your own and plug-in your own values.
	 * NOTE: Class is mostly provided as a framework for this type of localized management.
	 */
	public AbstractReference() {
	}

}
