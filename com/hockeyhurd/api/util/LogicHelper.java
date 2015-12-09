package com.hockeyhurd.api.util;

/**
 * Largely static class used in conjunction with common logic uses from
 * null checks to xor boolean statements.
 *
 * @author hockeyhurd
 */
public final class LogicHelper {
	
	private LogicHelper() {
	}
	
	/**
	 * @param text = input
	 * @return true if string isn't (by our standards) null, false if it is.
	 */
	public static boolean nullCheckString(String text) {
		return text != null && text.length() > 0; // && !text.equals("");
	}
	
	/**
	 * Tests set String array and iterates through. If a single element is null, this will return false, else return true.
	 * @param text = inputed text based String array.
	 * @return true if String array is NOT NULL, else returns false.
	 */
	public static boolean nullCheckString(String... text) {
		if (text == null || text.length == 0) return false;

		for (String str : text) {
			if (!nullCheckString(str)) return false;
		}

		return true;
	}
	
	/**
	 * Returns true if the object is NOT null, else false.
	 * @param object = object to test.
	 * @return true if object is NOT NULL, else returns false.
	 */
	public static boolean nullCheck(Object object) {
		return object != null;
	}
	
	/**
	 * Tests set object array and iterates through. If a single element is null, this will return false, else return true.
	 * @param objects = object array.
	 * @return true if object array is NOT NULL, else returns false.
	 */
	public static boolean nullCheck(Object... objects) {
		if (objects == null || objects.length == 0) return false;

		for (Object obj : objects) {
			if (!nullCheck(obj)) return false;
		}

		return true;
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

	/**
	 * Checks if array contains reference object.
	 *
	 * @param array array to reference.
	 * @param ref object to check.
	 * @return true if array contains object, else returns false.
	 */
	public static boolean arrayContains(Object[] array, Object ref) {
		if (ref == null) return false;
		if (array == null || array.length == 0) return false;
		else if (array.length == 1) return array[0].equals(ref);
		else if (array.length == 2) return array[0].equals(ref) || array[1].equals(ref);

		for (Object o : array) {
			if (o.equals(ref)) return true;
		}

		return false;
	}

	/**
	 * Checks if array contains reference byte.
	 *
	 * @param array array to reference.
	 * @param ref byte to check.
	 * @return true if array contains byte, else returns false.
	 */
	public static boolean arrayContains(byte[] array, byte ref) {
		if (array == null || array.length == 0) return false;
		else if (array.length == 1) return array[0] == ref;
		else if (array.length == 2) return array[0] == ref || array[1] == ref;

		for (byte num : array) {
			if (num == ref) return true;
		}

		return false;
	}

	/**
	 * Checks if array contains reference short.
	 *
	 * @param array array to reference.
	 * @param ref short to check.
	 * @return true if array contains short, else returns false.
	 */
	public static boolean arrayContains(short[] array, short ref) {
		if (array == null || array.length == 0) return false;
		else if (array.length == 1) return array[0] == ref;
		else if (array.length == 2) return array[0] == ref || array[1] == ref;

		for (short num : array) {
			if (num == ref) return true;
		}

		return false;
	}

	/**
	 * Checks if array contains reference int.
	 *
	 * @param array array to reference.
	 * @param ref int to check.
	 * @return true if array contains int, else returns false.
	 */
	public static boolean arrayContains(int[] array, int ref) {
		if (array == null || array.length == 0) return false;
		else if (array.length == 1) return array[0] == ref;
		else if (array.length == 2) return array[0] == ref || array[1] == ref;

		for (int num : array) {
			if (num == ref) return true;
		}

		return false;
	}

	/**
	 * Checks if array contains reference long.
	 *
	 * @param array array to reference.
	 * @param ref long to check.
	 * @return true if array contains long, else returns false.
	 */
	public static boolean arrayContains(long[] array, long ref) {
		if (array == null || array.length == 0) return false;
		else if (array.length == 1) return array[0] == ref;
		else if (array.length == 2) return array[0] == ref || array[1] == ref;

		for (long num : array) {
			if (num == ref) return true;
		}

		return false;
	}

	/**
	 * Checks if array contains reference float.
	 *
	 * @param array array to reference.
	 * @param ref float to check.
	 * @return true if array contains float, else returns false.
	 */
	public static boolean arrayContains(float[] array, float ref) {
		if (array == null || array.length == 0) return false;
		else if (array.length == 1) return array[0] == ref;
		else if (array.length == 2) return array[0] == ref || array[1] == ref;

		for (float num : array) {
			if (num == ref) return true;
		}

		return false;
	}

	/**
	 * Checks if array contains reference double.
	 *
	 * @param array array to reference.
	 * @param ref double to check.
	 * @return true if array contains double, else returns false.
	 */
	public static boolean arrayContains(double[] array, double ref) {
		if (array == null || array.length == 0) return false;
		else if (array.length == 1) return array[0] == ref;
		else if (array.length == 2) return array[0] == ref || array[1] == ref;

		for (double num : array) {
			if (num == ref) return true;
		}

		return false;
	}

	/**
	 * Function to check provided statements (array of booleans) for 'AND' logic functionality.
	 *
	 * @param statements statements to check.
	 * @return result of 'AND' logic.
	 */
	public static boolean and(boolean... statements) {
		if (statements == null || statements.length == 0) return false;
		else if (statements.length == 1) return statements[0];
		else if (statements.length == 2) return statements[0] && statements[1];

		for (boolean s : statements) {
			if (!s) return false;
		}

		return true;
	}

	/**
	 * Function to check provided statements (array of booleans) for 'NAND' logic functionality.
	 *
	 * @param statements statements to check.
	 * @return result of 'NAND' logic.
	 */
	public static boolean nand(boolean... statements) {
		return not(and(statements));
	}

	/**
	 * Function to check provided statements (array of booleans) for 'OR' logic functionality.
	 *
	 * @param statements statements to check.
	 * @return result of 'OR' logic.
	 */
	public static boolean or(boolean... statements) {
		if (statements == null || statements.length == 0) return false;
		else if (statements.length == 1) return statements[0];
		else if (statements.length == 2) return statements[0] || statements[1];

		for (boolean s : statements) {
			if (s) return true;
		}

		return false;
	}

	/**
	 * Function to check provided statements (array of booleans) for 'NOR' logic functionality.
	 *
	 * @param statements statements to check.
	 * @return result of 'NOR' logic.
	 */
	public static boolean nor(boolean... statements) {
		return not(or(statements));
	}

	/**
	 * Function to check provided statements (array of booleans) for 'XOR' logic functionality.
	 *
	 * @param statements statements to check.
	 * @return result of 'XOR' logic.
	 */
	public static boolean xor(boolean... statements) {
		if (statements == null || statements.length == 0) return false;
		else if (statements.length == 1) return statements[0];
		else if (statements.length == 2) return statements[0] ^ statements[1];

		int counter = 0;
		for (boolean s : statements) {
			if (s) {
				if (counter > 1) return false;
				else counter++;
			}
		}

		return counter == 1;
	}

	/**
	 * Function to check provided statements (array of booleans) for 'XNOR' logic functionality.
	 *
	 * @param statements statements to check.
	 * @return result of 'XNOR' logic.
	 */
	public static boolean xnor(boolean... statements) {
		return not(xor(statements));
	}

	/**
	 * Function provided to return 'NOT' logic functionality or opposite of provided boolean.
	 *
	 * @param statement statement to check.
	 * @return result of 'NOT' logic or opposite of provided statement.
	 */
	public static boolean not(boolean statement) {
		return !statement;
	}

}
