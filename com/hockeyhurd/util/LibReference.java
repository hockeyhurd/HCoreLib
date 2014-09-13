package com.hockeyhurd.util;

public class LibReference extends AbstractReference {

	/** Current build number. */
	public static final short BUILD = 1;
	
	/** Current version with included build number. */
	public static final String VERSION = "v1.1." + BUILD;
	
	/** Current mod name. */
	public static final String MOD_NAME = "HCoreLib";
	
	/** Not required but is available. NOTE: if not using it, set to null! */
	public static final String MOD_URL = "http://75.68.113.97:8080/downloads/versions/HCoreLib-1.1.";
	
	public LibReference() {
	}

}
