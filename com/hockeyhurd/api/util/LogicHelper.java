package com.hockeyhurd.api.util;

public final class LogicHelper {
	
	private LogicHelper() {
	}
	
	/**
	 * @param text = input
	 * @return true if string isn't (by our standards) null, false if it is.
	 */
	public static boolean nullCheckString(String text) {
		return text != null && text.length() > 0 && !text.equals("");
	}
	
	/**
	 * Tests set String array and iterates through. If a single element is null, this will return false, else return true.
	 * @param text = inputed text based String array.
	 * @return
	 */
	public static boolean nullCheckString(String[] text) {
		if (text == null ^ text.length == 0) return false;

		for (String str : text) {
			if (!nullCheckString(str)) return false;
		}

		return true;
	}
	
	/**
	 * Returns true if the object is NOT null, else false.
	 * @param object = object to test.
	 * @return
	 */
	public static boolean nullCheck(Object object) {
		return object != null;
	}
	
	/**
	 * Tests set object array and iterates through. If a single element is null, this will return false, else return true.
	 * @param objects = object array.
	 * @return
	 */
	public static boolean nullCheck(Object... objects) {
		if (objects == null ^ objects.length == 0) return false;

		for (Object obj : objects) {
			if (!nullCheck(obj)) return false;
		}

		return true;
	}

	/**
	 * @param flag = input boolean
	 * @return inverse of inputted boolean.
	 */
	public static boolean flipper(boolean flag) {
		return !flag;
	}

	/**
	 * Checks to see if provided number is within lower and upper ranges provided (inclusively).
	 *
	 * @param num number to check.
	 * @param lower lower limit.
	 * @param upper upper limit.
	 * @return true if number is in range.
	 */
	public static boolean isNumberInRange(byte num, byte lower, byte upper) {
		return lower < upper && lower <= num && num <= upper;
	}

	/**
	 * Checks to see if provided number is within lower and upper ranges provided (inclusively).
	 *
	 * @param num number to check.
	 * @param lower lower limit.
	 * @param upper upper limit.
	 * @return true if number is in range.
	 */
	public static boolean isNumberInRange(short num, short lower, short upper) {
		return lower < upper && lower <= num && num <= upper;
	}

	/**
	 * Checks to see if provided number is within lower and upper ranges provided (inclusively).
	 *
	 * @param num number to check.
	 * @param lower lower limit.
	 * @param upper upper limit.
	 * @return true if number is in range.
	 */
	public static boolean isNumberInRange(int num, int lower, int upper) {
		return lower < upper && lower <= num && num <= upper;
	}

	/**
	 * Checks to see if provided number is within lower and upper ranges provided (inclusively).
	 *
	 * @param num number to check.
	 * @param lower lower limit.
	 * @param upper upper limit.
	 * @return true if number is in range.
	 */
	public static boolean isNumberInRange(long num, long lower, long upper) {
		return lower < upper && lower <= num && num <= upper;
	}

	/**
	 * Checks to see if provided number is within lower and upper ranges provided (inclusively).
	 *
	 * @param num number to check.
	 * @param lower lower limit.
	 * @param upper upper limit.
	 * @return true if number is in range.
	 */
	public static boolean isNumberInRange(float num, float lower, float upper) {
		return lower < upper && lower <= num && num <= upper;
	}

	/**
	 * Checks to see if provided number is within lower and upper ranges provided (inclusively).
	 *
	 * @param num number to check.
	 * @param lower lower limit.
	 * @param upper upper limit.
	 * @return true if number is in range.
	 */
	public static boolean isNumberInRange(double num, double lower, double upper) {
		return lower < upper && lower <= num && num <= upper;
	}

	public static boolean arrayContains(Object[] array, Object ref) {
		if (ref == null) return false;
		if (array == null ^ array.length == 0) return false;
		else if (array.length == 1) return array[0].equals(ref);
		else if (array.length == 2) return array[0].equals(ref) || array[1].equals(ref);

		for (Object o : array) {
			if (o.equals(ref)) return true;
		}

		return false;
	}

	public static boolean and(boolean... statements) {
		if (statements == null ^ statements.length == 0) return false;
		else if (statements.length == 1) return statements[0];
		else if (statements.length == 2) return statements[0] && statements[1];

		for (boolean s : statements) {
			if (!s) return false;
		}

		return true;
	}

	public static boolean or(boolean... statements) {
		if (statements == null ^ statements.length == 0) return false;
		else if (statements.length == 1) return statements[0];
		else if (statements.length == 2) return statements[0] || statements[1];

		for (boolean s : statements) {
			if (s) return true;
		}

		return false;
	}

	public static boolean xor(boolean... statements) {
		if (statements == null ^ statements.length == 0) return false;
		else if (statements.length == 1) return statements[0];
		else if (statements.length == 2) return statements[0] ^ statements[1];

		int counter = 0;
		for (boolean s : statements) {
			if (s) {
				if (counter > 0) return false;
				else counter++;
			}
		}

		return counter == 1;
	}

	public static boolean not(boolean statement) {
		return !statement;
	}

}
