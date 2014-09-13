package com.hockeyhurd.util;

public class LogicHelper {
	
	public LogicHelper() {
	}
	
	/**
	 * @param text = input
	 * @return true if string isn't null, false if it is.
	 */
	public static boolean nullCheckString(String text) {
		return text != null && !text.equals("");
	}
	
	/**
	 * Tests set String array and iterates through. If a single element is null, this will return false, else return true.
	 * @param text = inputed text based String array.
	 * @return
	 */
	public static boolean nullCheckString(String[] text) {
		boolean flag = false;
		for (String str : text) {
			if (!nullCheckString(str)) {
				flag = true;
				break;
			}
		}
		
		return flag;
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
		boolean flag = false;
		for (Object obj : objects) {
			if (!nullCheck(obj)) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}

	/**
	 * @param flag = input boolean
	 * @return inverse of inputted boolean.
	 */
	public static boolean flipper(boolean flag) {
		return flag ? false : true;
	}

}
