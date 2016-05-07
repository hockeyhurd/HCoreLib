package com.hockeyhurd.hcorelib.api.util;

import java.text.DecimalFormat;

/**
 * Class containing code for formatting numbers.
 * 
 * @author hockeyhurd
 * @version Jan 13, 2015
 */
public final class NumberFormatter {

	private static final DecimalFormat FORMAT = new DecimalFormat("###,###.##");
	private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("########0.000");
	private static final int unit = 0x400;
	private static final double mathLogUnit = Math.log(unit);
	
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

	/**
	 * Formats a numerical byte value into a beautiful String representation.
	 *
	 * @param bytes long.
	 * @return Formatted String.
     */
	public static String bytesAsString(long bytes) {
		if (bytes < unit) return bytes + "B";

		final int exponent = (int) (Math.log(bytes) / mathLogUnit);
		final String prefix = "KMGTPE".charAt(exponent - 1) + "";

		return String.format("%.2f, %sB", bytes / Math.pow(unit, exponent), prefix);
	}

	/**
	 * Formats milliseconds long value into String representation.
	 *
	 * @param ms long.
	 * @return Formatted String.
     */
	public static String millisecondsAsString(long ms) {
		final int days = (int) (ms / (1000 * 60 * 60 * 24)) % 7;
		final int hours = (int) (ms / (1000 * 60 * 60)) % 24;
		final int mins = (int) (ms / (1000 * 60)) % 60;
		final int secs = (int) (ms / 1000) % 60;

		if (days > 0) return String.format("%s days, %sh %sm %ss", days, hours, mins, secs);
		else if (hours > 0) return String.format("%sh %sm %ss", hours, mins, secs);

		return String.format("%sm %ss", mins, secs);
	}

	/**
	 * Formats for time.
	 *
	 * @param o number to format.
	 * @return Formatted string.
     */
	public static String formatTime(Object o) {
		return o instanceof Number ? TIME_FORMATTER.format(o) : "<ERROR>";
	}

}
