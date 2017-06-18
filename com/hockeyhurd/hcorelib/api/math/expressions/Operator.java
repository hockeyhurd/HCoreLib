package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Operator EToken.
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
class Operator extends EToken {

	private EnumOp op;

	/**
	 * Constructs an 'operator' with its containing operator type.
	 *
	 * @param op EnumOp.
	 */
	Operator(EnumOp op) {
		super("<", ">");
		this.op = op;
	}

	/**
	 * Gets the actual operator contained.
	 *
	 * @return EnumOp.
	 */
	EnumOp getOperator() {
		return op;
	}

	/**
	 * Sets the EnumOp.
	 *
	 * @param op EnumOp.
	 */
	void setOp(EnumOp op) {
		this.op = op;
	}

	/**
	 * Function to see if this is an add function.
	 *
	 * @return boolean result.
	 */
	boolean isAdd() {
		return op == EnumOp.PLUS;
	}

	/**
	 * Function to see if this is an subtract function.
	 *
	 * @return boolean result.
	 */
	boolean isMinus() {
		return op == EnumOp.MINUS;
	}

	/**
	 * Function to see if this is an multiply function.
	 *
	 * @return boolean result.
	 */
	boolean isMultiply() {
		return op == EnumOp.MULTIPLY;
	}

	/**
	 * Function to see if this is an divide function.
	 *
	 * @return boolean result.
	 */
	boolean isDivide() {
		return op == EnumOp.DIVIDE;
	}

	/**
	 * Function to see if this is an modulus function.
	 *
	 * @return boolean result.
	 */
	boolean isModulus() {
		return op == EnumOp.MODULUS;
	}

	/**
	 * Function to see if this is an exponent function.
	 *
	 * @return boolean result.
	 */
	boolean isExponent() {
		return op == EnumOp.EXPONENT;
	}

	/**
	 * Function to see if this is an factorial function.
	 *
	 * @deprecated Deprecated (10/15/2016) until further implemented.
	 *
	 * @return boolean result.
	 */
	@Deprecated
	boolean isFactorial() {
		return op == EnumOp.FACTORIAL;
	}

	/**
	 * Function to see if this is an equals function.
	 *
	 * @return boolean result.
	 */
	boolean isEquals() {
		return op == EnumOp.EQUALS;
	}

	/**
	 * Function to see if this is an parenthesis left function.
	 *
	 * @return boolean result.
	 */
	boolean isParenthesisLeft() {
		return op == EnumOp.PARENTHESIS_L || op == EnumOp.SQUARE_BRACKET_L || op == EnumOp.CURLY_BRACKET_L;
	}

	/**
	 * Function to see if this is an parenthesis right function.
	 *
	 * @return boolean result.
	 */
	boolean isParenthesisRight() {
		return op == EnumOp.PARENTHESIS_R || op == EnumOp.SQUARE_BRACKET_R || op == EnumOp.CURLY_BRACKET_R;
	}

	@Override
	double evaluate() {
		return 0.0;
	}

	@Override
	public String toString() {
		return op.toString();
	}

}
