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

	boolean isParenthesisType() {
		return op == EnumOp.PARENTHESIS || op == EnumOp.SQUARE_BRACKET || op == EnumOp.CURLY_BRACKET;
	}

	@Override
	double evaluate() {
		return 0.0;
	}

}
