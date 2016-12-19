package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Class for storing interpreter results.
 *
 * @author hockeyhurd
 * @version 12/19/2016.
 */
public final class InterpreterResult {

	private String expression;
	private double result;

	public InterpreterResult() {
		this(null, 0.0d);
	}

	/**
	 * Stores a string expression.
	 *
	 * @param expression String expression.
	 */
	public InterpreterResult(String expression) {
		this(expression, 0.0d);
	}

	/**
	 * Stores a string and double result to an expression.
	 *
	 * @param expression String expression.
	 * @param result double expression result.
	 */
	public InterpreterResult(String expression, double result) {
		this.expression = expression;
		this.result = result;
	}

	/**
	 * Checks if expression is valid or not.
	 *
	 * @return boolean result.
	 */
	public boolean isEmpty() {
		return expression == null || expression.isEmpty();
	}

	/**
	 * Gets internal string expression.
	 *
	 * @return String expression.
	 */
	public String getExpressionString() {
		return expression != null && !expression.isEmpty() ? expression : "null";
	}

	/**
	 * Gets internal double result from expression.
	 *
	 * @return double result.
	 */
	public double getResult() {
		return result;
	}

	/**
	 * Updates the internal values.
	 *
	 * @param expression String expression.
	 * @param result double expression result.
	 */
	public void updateResult(String expression, double result) {
		if (expression != null && !expression.isEmpty() && result != Double.NaN) {
			this.expression = expression;
			this.result = result;
		}
	}

}
