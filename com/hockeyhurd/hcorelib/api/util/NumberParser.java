package com.hockeyhurd.hcorelib.api.util;

/**
 * Small, largely static class for parsing data.
 *
 * @author hockeyhurd
 * @version 11/12/2015.
 */
public final class NumberParser {

	private NumberParser() {
	}

	/**
	 * Parses a given String into a byte.
	 *
	 * @param value String representation.
	 * @return byte.
	 */
	public static byte parseByte(String value) {
		if (!LogicHelper.nullCheckString(value)) return 0;

		try {
			return Byte.parseByte(value);
		}

		catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Parses a given String into a short.
	 *
	 * @param value String representation.
	 * @return short.
	 */
	public static short parseShort(String value) {
		if (!LogicHelper.nullCheckString(value)) return 0;

		try {
			return Short.parseShort(value);
		}

		catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Parses a given String into a int.
	 *
	 * @param value String representation.
	 * @return int.
	 */
	public static int parseInt(String value) {
		if (!LogicHelper.nullCheckString(value)) return 0;

		try {
			return Integer.parseInt(value);
		}

		catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Parses a given String into a long.
	 *
	 * @param value String representation.
	 * @return long.
	 */
	public static long parseLong(String value) {
		if (!LogicHelper.nullCheckString(value)) return 0L;

		try {
			return Long.parseLong(value);
		}

		catch (NumberFormatException e) {
			return 0L;
		}
	}

	/**
	 * Parses a given String into a float.
	 *
	 * @param value String representation.
	 * @return float.
	 */
	public static float parseFloat(String value) {
		if (!LogicHelper.nullCheckString(value)) return 0.0f;

		try {
			return Float.parseFloat(value);
		}

		catch (NumberFormatException e) {
			return 0.0f;
		}
	}

	/**
	 * Parses a given String into a double.
	 *
	 * @param value String representation.
	 * @return double.
	 */
	public static double parseDouble(String value) {
		if (!LogicHelper.nullCheckString(value)) return 0.0d;

		try {
			return Double.parseDouble(value);
		}

		catch (NumberFormatException e) {
			return 0.0d;
		}
	}

}
