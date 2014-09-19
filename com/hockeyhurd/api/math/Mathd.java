package com.hockeyhurd.api.math;

/**
 * Simple extension of java's excellent math class but adds some missing functions.
 * Most notably csc, sec, cot, and adds a bunch more.
 * 
 * @author hockeyhurd
 * @link <a href = "https://github.com/hockeyhurd/HCoreLib/blob/master/com/hockeyhurd/api/math/Mathd.java">github.com/.../Mathd.java</a>
 */
public class Mathd {

	/**
	 * Converts degrees to radians.
	 * @param angle = angle in degrees.
	 * @return radians value.
	 */
	public static double toRadians(double angle) {
		double radians = angle * Math.PI / 180;
		return radians;
	}
	
	/**
	 * Converts degrees to degrees.
	 * @param radians = angle in radians
	 * @return angle of degree value.
	 */
	public static double toDegrees(double radians) {
		double degrees = radians * 180 / Math.PI;
		return degrees;
	}
	
	/**
	 * Secant value.
	 * @param radians = radians input
	 * @return secant value
	 */
	public static double sec(double radians) {
		return 1.0d / cos(radians);
	}
	
	/**
	 * Absoulute value secant.
	 * @param radians = radians input
	 * @return absoulute value of secant(radians).
	 */
	public static double asec(double radians) {
		return abs(1.0d / cos(radians));
	}
	
	/**
	 * Cosecant value
	 * @param radians = radians input
	 * @return cosecant of value.
	 */
	public static double csc(double radians) {
		return 1.0d / sin(radians);
	}
	
	/**
	 * Absoulute value cosecant.
	 * @param radians = radians input
	 * @return absoulute value of cosecant(radians).
	 */
	public static double acsc(double radians) {
		return abs(1.0d / sin(radians));
	}
	
	/**
	 * CoTangent function
	 * @param radians = radians input
	 * @return cotangent of angle
	 */
	public static double coTan(double radians) {
		return (cos(radians) / sin(radians));
	}
	
	/**
	 * Absoulute value cotangent function
	 * @param radians = radians input
	 * @return absoulute value of contangent(radians)
	 */
	public static double acoTan(double radians) {
		return abs(cos(radians) / sin(radians));
	}
	
	/**
	 * Cosine function
	 * @param radians = radians input
	 * @return cos of angle
	 */
	public static double cos(double radians) {
		return Math.cos(radians);
	}
	
	/**
	 * Absoulute value Cosine function
	 * @param radians = radians input
	 * @return Absolute value cos of angle
	 */
	public static double acos(double radians) {
		return abs(cos(radians));
	}
	
	/**
	 * Cosine inverse function
	 * @param radians = radians input
	 * @return Cosine^-1 of angle
	 */
	public static double cosInv(double radians) {
		return Math.pow(cos(radians), -1);
	}
	
	/**
	 * Absoulute value of cosine inverse function
	 * @param radians = radians input
	 * @return Absolute value of cosine^-1 of angle
	 */
	public static double acosInv(double radians) {
		return Math.pow(acos(radians), -1);
	}
	
	/**
	 * Sine function
	 * @param radians = radians input
	 * @return Sin of angle
	 */
	public static double sin(double radians) {
		return Math.sin(radians);
	}
	
	/**
	 * Absoulute value sine function
	 * @param radians = radians input
	 * @return Absolute value sin of angle
	 */
	public static double asin(double radians) {
		return abs(sin(radians));
	}
	
	/**
	 * Sine inverse function
	 * @param radians = radians input
	 * @return Sine^-1 of angle
	 */
	public static double sinInv(double radians) {
		return Math.pow(sin(radians), -1);
	}
	
	/**
	 * Absoulute value of sine inverse function
	 * @param radians = radians input
	 * @return Absolute value of sine^-1 of angle
	 */
	public static double asinInv(double radians) {
		return Math.pow(asin(radians), -1);
	}
	
	/**
	 * Tangent function
	 * @param radians = radians input
	 * @return Tan of angle
	 */
	public static double tan(double radians) {
		return Math.tan(radians);
	}
	
	/**
	 * Absoulute value tangent function
	 * @param radians = radians input
	 * @return Absolute value tan of angle
	 */
	public static double atan(double radians) {
		return abs(tan(radians));
	}
	
	/**
	 * Absolute value function
	 * @param value = value input
	 * @return Absoulute value of inputted number.
	 */
	public static double abs(double value) {
		return value >= 0.0d ? value : value * -1;
	}
	
}
