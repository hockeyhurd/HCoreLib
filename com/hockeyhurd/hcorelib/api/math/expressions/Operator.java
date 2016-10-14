package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Operator EToken.
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
class Operator extends EToken {

	private EnumOp op;

	Operator(EnumOp op) {
		super("<", ">");
		this.op = op;
	}

	EnumOp getOperator() {
		return op;
	}

	void setOp(EnumOp op) {
		this.op = op;
	}

	boolean isAdd() {
		return op == EnumOp.PLUS;
	}

	boolean isMinus() {
		return op == EnumOp.MINUS;
	}

	boolean isMultiply() {
		return op == EnumOp.MULTIPLY;
	}

	boolean isDivide() {
		return op == EnumOp.DIVIDE;
	}

	boolean isModulus() {
		return op == EnumOp.MODULUS;
	}

	boolean isExponent() {
		return op == EnumOp.EXPONENT;
	}

	boolean isFactorial() {
		return op == EnumOp.FACTORIAL;
	}

	boolean isParenthesisLeft() {
		return op == EnumOp.PARENTHESIS_L || op == EnumOp.SQUARE_BRACKET_L || op == EnumOp.CURLY_BRACKET_L;
	}

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
