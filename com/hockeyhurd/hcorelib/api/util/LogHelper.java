package com.hockeyhurd.hcorelib.api.util;

import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

/**
 * Class used for logging data.
 *
 * @author hockeyhurd
 * @version Sep 15, 2014
 */
public class LogHelper {

	private String modName;

	public LogHelper(String modName) {
		this.modName = modName;
	}
	
	/**
	 * Static ref method used by all other methods in containing class.
	 * @param logLevel level of logging
	 * @param object object to print
	 */
	public void log(Level logLevel, Object object) {
		FMLLog.log(modName, logLevel, String.valueOf(object));
	}

	/**
	 * Static ref method used by all other methods in containing class in which uses object array.
	 * @param level level of logging
	 * @param objects object array to print
	 */
	public void log(Level level, Object... objects) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] != null) builder.append(String.valueOf(objects[i]));
			else {
				builder.append('[');
				builder.append(i);
				builder.append(']');
				builder.append(" <NULL>");
			}

			builder.append(' ');
		}
		
		log(level, builder.toString());
	}
	
	/**
	 * Simple static method for providing info through an object.
	 * @param object object to print
	 */
	public void info(Object object) {
		log(Level.INFO, object);
	}
	
	
	/**
	 * Simple static method for providing info through an object array.
	 * @param objects object array to print.
	 */
	public void info(Object... objects) {
		log(Level.INFO, objects);
	}
	
	/**
	 * Simple static method for providing a warning through an object.
	 * @param object object to print
	 */
	public void warn(Object object) {
		log(Level.WARN, object);
	}
	
	/**
	 * Simple static method for providing a warning through an object array.
	 * @param objects object array to print
	 */
	public void warn(Object... objects) {
		log(Level.WARN, objects);
	}
	
	/**
	 * Simple static method for providing a severe-warning through an object.
	 * @param object object to print
	 */
	public void severe(Object object) {
		log(Level.ERROR, object);
	}
	
	/**
	 * Simple static method for providing a severe-warning through an object array.
	 * @param objects object array to print
	 */
	public void severe(Object... objects) {
		log(Level.ERROR, objects);
	}

}
