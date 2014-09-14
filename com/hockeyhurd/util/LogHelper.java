package com.hockeyhurd.util;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {

	private String mod_name;
	
	public LogHelper(Class<? extends AbstractReference> reference) {
		try {
			mod_name = reference.getDeclaredField("MOD_NAME").get(reference).toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		};
	}
	
	/**
	 * Static ref method used by all other methods in containing class.
	 * @param logLevel = level of logging
	 * @param object = object to print
	 */
	public void log(Level logLevel, Object object) {
		FMLLog.log(mod_name, logLevel, String.valueOf(object));
	}

	/**
	 * Static ref method used by all other methods in containing class in which uses object array.
	 * @param level = level of logging
	 * @param objects = object array to print
	 */
	public void log(Level level, Object... objects) {
		String text = "";
		for (Object obj : objects) {
			text += String.valueOf(obj) + " ";
		}
		
		log(level, text);
	}
	
	/**
	 * Simple static method for providing info through an object.
	 * @param object = object to print
	 */
	public void info(Object object) {
		log(Level.INFO, object);
	}
	
	
	/**
	 * Simple static method for providing info through an object array.
	 * @param objects = object array to print.
	 */
	public void info(Object... objects) {
		log(Level.INFO, objects);
	}
	
	/**
	 * Simple static method for providing a warning through an object.
	 * @param object = object to print
	 */
	public void warn(Object object) {
		log(Level.WARN, object);
	}
	
	/**
	 * Simple static method for providing a warning through an object array.
	 * @param objects = object array to print
	 */
	public void warn(Object... objects) {
		log(Level.WARN, objects);
	}
	
	/**
	 * Simple static method for providing a severe-warning through an object.
	 * @param object = object to print
	 */
	public void severe(Object object) {
		log(Level.ERROR, object);
	}
	
	/**
	 * Simple static method for providing a severe-warning through an object array.
	 * @param objects = object array to print
	 */
	public void severe(Object... objects) {
		log(Level.ERROR, objects);
	}

}
