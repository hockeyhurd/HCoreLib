package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Enumeration for operator precedence.
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
enum EnumOp {
	PLUS("+", 3), MINUS("-", 3), MULTIPLY("*", 2), DIVIDE("/", 2), MODULUS("%", 2), EXPONENT("^", 1),
	PARENTHESIS("()", 4), SQUARE_BRACKET("[]", 4), CURLY_BRACKET("{}", 4), NO_OP("null", -1);

	private final String type;
	private final int prec;

	EnumOp(String type, int prec) {
		this.type = type;
		this.prec = prec;
	}

	String getType() {
		return type;
	}

	int getPrecedance() {
		return prec;
	}

	@Override
	public String toString() {
		return type;
	}

	static boolean isOp(String op) {
		if (op == null || op.isEmpty() || op.length() > 2) return false;

		for (EnumOp operator : values())
			if (operator.getType().equals(op)) return true;

		return false;
	}

}
