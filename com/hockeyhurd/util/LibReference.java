package com.hockeyhurd.util;

/**
 * Example class, used directly for this core mode.
 * DO NOT: change this!
 * DO Not: use this!
 * DO: Make your own reference class
 * WELCOMED: to copy this layout as an example.
 * @author Nick
 */
public class LibReference extends AbstractReference {

	/** Current build number. */
	public static final short BUILD = 2;
	
	/** Current version with included build number. */
	public static final String VERSION = "v1.1." + BUILD;
	
	/** Current mod name. */
	public static final String MOD_NAME = "HCoreLib";
	
	/** Not required but is available. NOTE: if not using it, set to null! */
	public static final String MOD_URL = "http://75.68.113.97:8080/downloads/" + MOD_NAME.toLowerCase() + "/versions/HCoreLib-1.1.";
	
	public LibReference() {
	}

}
