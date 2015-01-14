package com.hockeyhurd.api.util;

import java.text.DecimalFormat;

/**
 * Class containing code for formatting numbers.
 * 
 * @author hockeyhurd
 * @version Jan 13, 2015
 */
public class NumberFormatter {

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
	 * @param num = number to format
	 * @return formatted number as string.
	 */ 
	public static String format(Number num) {
		return FORMAT.format(String.valueOf(num));
	}
	
}
