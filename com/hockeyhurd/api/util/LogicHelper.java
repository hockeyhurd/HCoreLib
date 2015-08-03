package com.hockeyhurd.api.util;

public class LogicHelper {
	
	public LogicHelper() {
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
		if (text == null || text.length == 0) return false;

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

}
