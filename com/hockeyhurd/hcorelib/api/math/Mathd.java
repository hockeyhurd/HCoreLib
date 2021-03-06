package com.hockeyhurd.hcorelib.api.math;

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
		return angle * Math.PI / 180.0d;
	}
	
	/**
	 * Converts degrees to degrees.
	 * @param radians = angle in radians
	 * @return angle of degree value.
	 */
	public static double toDegrees(double radians) {
		return radians * 180 / Math.PI;
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
	public static double absSec(double radians) {
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
	public static double absCsc(double radians) {
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
	public static double absCoTan(double radians) {
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
	public static double absCos(double radians) {
		return abs(cos(radians));
	}
	
	/**
	 * Cosine inverse function
	 * @param radians = radians input
	 * @return Cosine^-1 of angle
	 */
	public static double cosInv(double radians) {
		return Math.acos(radians);
	}
	
	/**
	 * Absoulute value of cosine inverse function
	 * @param radians = radians input
	 * @return Absolute value of cosine^-1 of angle
	 */
	public static double absCosInv(double radians) {
		return abs(cosInv(radians));
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
	public static double absSin(double radians) {
		return abs(sin(radians));
	}
	
	/**
	 * Sine inverse function
	 * @param radians = radians input
	 * @return Sine^-1 of angle
	 */
	public static double sinInv(double radians) {
		return Math.asin(radians);
	}
	
	/**
	 * Absoulute value of sine inverse function
	 * @param radians = radians input
	 * @return Absolute value of sine^-1 of angle
	 */
	public static double absSinInv(double radians) {
		return abs(sinInv(radians));
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
	public static double absTan(double radians) {
		return abs(tan(radians));
	}
	
	/**
	 * Absolute value function
	 * @param value = value input
	 * @return Absolute value of inputted number.
	 */
	public static double abs(double value) {
		return value >= 0.0d ? value : value * -1;
	}
	
	/**
	 * Simple method for rounding quickly to nearest hundredths place.
	 * @param value = input raw value
	 * @return new value to nearest hundredth.
	 */
	public static double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}
	
	/**
	 * Simple method for rounding to nearest specified decimal place. NOTE: must be >= 1!
	 * @param value = value to round
	 * @param decPlaces = number of decimal places to round to (default: 1).
	 * @return rounded number to defined decimal place.
	 */
	public static double round(double value, int decPlaces) {
		if (decPlaces < 1) decPlaces = 1;

		double power = 10.0;
		while (--decPlaces > 0)
			power *= 10.0;

		return Math.round(value * power) / power;
	}
	
	/**
	 * Function used to exponentiate any value to any exponent.
	 *
	 * @param value value to raise to power.
	 * @param exp exponent value or power to raise to.
	 * @return value raised to exponent.
	 */
	public static double pow(double value, int exp) {
		return exp == 0 ? 1 : exp > 0 ? powRecursive(value, exp) : 1.0d / powRecursive(value, -exp);
	}

	private static double powRecursive(double value, int exp) {
		if (exp == 0) return 1.0d;
		else if (exp == 1) return value;

		final double x = powRecursive(value, exp >> 1);

		if (exp % 2 == 0) return x * x;
		return x * x * value;
	}

	private static double[] p = {0.99999999999980993, 676.5203681218851, -1259.1392167224028,
			771.32342877765313, -176.61502916214059, 12.507343278686905,
			-0.13857109526572012, 9.9843695780195716e-6, 1.5056327351493116e-7};

	public static double factorial(double value) {
		if (value < 0.5d) return Math.PI / (sin(Math.PI * value) * factorial(1 - value));

		value -= 1.0d;

		double a = p[0];
		double t = value + 7 + 0.5d;
		for (int i = 1; i < p.length; i++)
			a += p[1] / (value + 1.0d);

		return Math.sqrt(2.0d * Math.PI) * Math.pow(t, value + 0.5d) * Math.exp(-t) * a;
	}

	/**
	 * Calculates linear interpolation betwen two numbers.
	 * <br>By using this specific lerp, we set the weight to 0.5 resulting in midpoint.
	 *
	 * @param x0 smaller number.
	 * @param x1 bigger number.
	 * @return linear interpolation with weight being un-effected.
	 */
	public static double lerp(double x0, double x1) {
		return lerp(x0, x1, 0.5d);
	}

	/**
	 * Calculates linear interpolation betwen two numbers.
	 *
	 * @param p0 smaller number.
	 * @param p1 bigger number.
	 * @param weight weighting. (setting < 0.5 will weigh more towards smaller number; setting > 0.5 will weigh more towards larger number.
	 *               setting to 0.5 will be towards the midpoint. <bold>NOTE: </bold>Weight is clamped to 0 <= weight <= 1.
	 * @return linear interpolation with weight being effected.
	 */
	public static double lerp(double p0, double p1, double weight) {
		if (weight < 0.0d) weight = 0.0d;
		else if (weight > 1.0d) weight = 1.0d;

		return p0 + (p1 - p0) * weight;
	}
	
}

