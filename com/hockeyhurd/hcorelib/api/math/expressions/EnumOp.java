package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Enumeration for operator precedence.
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
enum EnumOp {
	EQUALS('=', 1), PLUS('+', 5), MINUS('-', 5), MULTIPLY('*', 4), DIVIDE('/', 4), MODULUS('%', 4), EXPONENT('^', 3),
	PARENTHESIS_L('(', 6), PARENTHESIS_R(')', 6), SQUARE_BRACKET_L('[', 6), SQUARE_BRACKET_R(']', 6),
	CURLY_BRACKET_L('{', 6), CURLY_BRACKET_R('}', 6), FACTORIAL('!', 2), NO_OP('\0', -1);

	private final char type;
	private final int prec;

	EnumOp(char type, int prec) {
		this.type = type;
		this.prec = prec;
	}

	/**
	 * Gets the op type.
	 *
	 * @return char.
	 */
	char getType() {
		return type;
	}

	/**
	 * Gets the precedence of the operator.
	 *
	 * @return int.
	 */
	int getPrecedence() {
		return prec;
	}

	@Override
	public String toString() {
		return Character.toString(type);
	}

	static boolean isOp(char op) {
		for (EnumOp operator : values())
			if (operator.getType() == op) return true;

		return false;
	}

	/**
	 * Attempts to parse a string into its appropriate EnumOp value.
	 *
	 * @param string String to parse.
	 * @return EnumOp result.
	 */
	static EnumOp getOperatorFromString(String string) {
		if (string == null || string.length() != 1) return NO_OP;

		final char ch = string.charAt(0);
		for (EnumOp op : values())
			if (op.getType() == ch) return op;

		return NO_OP;
	}

}
