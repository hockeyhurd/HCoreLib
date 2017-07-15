package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.util.AbstractReference;

/**
 * Example class, used directly for this core mode.
 * DO NOT: change this!
 * DO NOT: use this!
 * DO: Make your own reference class
 * WELCOMED: to copy this layout as an example.
 * 
 * @author hockeyhurd
 */
public class LibReference extends AbstractReference {

	/** Current build number. */
	public static final int BUILD = 5;

	/** Current Subversion */
	public static final int SUB_VERSION = 2;

	/** Current major version. */
	public static final int MAJOR_VERSION = 1;

	/** Current version with included build number. */
	public static final String VERSION = "v" + MAJOR_VERSION + '.' + SUB_VERSION + '.' + BUILD;

	/** Current Minecraft version. */
	public static final String MINECRAFT_VERSION = "[1.10.2]";

	/** Current mod name. */
	public static final String MOD_NAME = "HCoreLib";

	/** Not required but is available. NOTE: if not using it, set to null! */
	public static final String MOD_URL = "https://dl.dropboxusercontent.com/u/276611945/minecraft/mods/" + MOD_NAME + "/versions/1.10.2/version.txt";

	/** Not required but is available. NOTE: if not using it, set to null! */
	public static final String CHANGELOG_URL = "https://dl.dropboxusercontent.com/u/276611945/minecraft/mods/" + MOD_NAME + "/versions/1.10.2/changelog.txt";

	/**
	 * To use this class referencing, simple extend this class with your own and plug-in your own values.
	 * NOTE: Class is mostly provided as a framework for this type of localized management.
	 */
	protected LibReference() {
	}

}
