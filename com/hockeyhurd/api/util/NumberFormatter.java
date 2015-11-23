package com.hockeyhurd.api.util;

import java.text.DecimalFormat;

/**
 * Class containing code for formatting numbers.
 * 
 * @author hockeyhurd
 * @version Jan 13, 2015
 */
public final class NumberFormatter {

	private static final DecimalFormat FORMAT = new DecimalFormat("###,###.##");
	
	private NumberFormatter() {
	}

	/**
	 * @return decimal formatter object used in this class.
	 */
	public static DecimalFormat getFormatter() {
		return FORMAT;
	}
	
	/**
	 * Formats a number object.
	 * 
	 * @param o number to format
	 * @return formatted number as string.
	 */ 
	public static String format(Object o) {
		return o instanceof Number ? FORMAT.format(o) : "<ERROR>";
	}

	/**
	 * Formats object with provided decimal format object.
	 *
	 * @param format format to use.
	 * @param o object to format.
	 * @return formatted string.
	 */
	public static String format(DecimalFormat format, Object o) {
		return o instanceof Number && format != null ? format.format(o) : "<ERROR>";
	}

	/**
	 * Shortened method for getting formatted text via string format and object.
	 *
	 * @param format format to pass to decimal formatter object.
	 * @param o object to format.
	 * @return formatted string.
	 */
	public static String format(String format, Object o) {
		return o instanceof Number ? format(new DecimalFormat(format), o) : "<ERROR>";
	}

}
