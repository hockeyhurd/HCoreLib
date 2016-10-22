package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Constant EToken.
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
class Constant extends EToken {

	public static double MATH_PI = Math.PI;
	public static double MATH_E = Math.E;

	private double value;

	/**
	 * Initializes constant to '0.0'.
	 */
	Constant() {
		this(0.0);
	}

	/**
	 * @param value value to initialize constant.
	 */
	Constant(double value) {
		super("@", " ");
		this.value = value;
	}

	@Override
	double evaluate() {
		return value;
	}

	@Override
	public String toString() {
		return Double.toString(value);
	}

}
