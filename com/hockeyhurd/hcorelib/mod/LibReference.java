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
	public static final short BUILD = 37;
	
	/** Current version with included build number. */
	public static final String VERSION = "v1.1." + BUILD;
	
	/** Current mod name. */
	public static final String MOD_NAME = "HCoreLib";

	/** Not required but is available. NOTE: if not using it, set to null! */
	// public static final String MOD_URL = "http://73.17.180.186:8080/downloads/" + MOD_NAME.toLowerCase() + "/versions/HCoreLib-1.1.";
	// public static final String MOD_URL = "https://www.dropbox.com/home/public/minecraft/mods/" + MOD_NAME + "/versions/HCoreLib-1.1.";
	public static final String MOD_URL = "https://dl.dropboxusercontent.com/u/276611945/minecraft/mods/" + MOD_NAME + "/version.txt";

	/** Not required but is available. NOTE: if not using it, set to null! */
	public static final String CHANGELOG_URL = "https://dl.dropboxusercontent.com/u/276611945/minecraft/mods/" + MOD_NAME + "/changelog.txt";

	private LibReference() {
	}

}
