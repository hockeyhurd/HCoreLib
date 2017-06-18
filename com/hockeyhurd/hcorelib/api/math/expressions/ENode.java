package com.hockeyhurd.hcorelib.api.math.expressions;

import com.hockeyhurd.hcorelib.api.math.Mathd;

/**
 * Expression Node
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
abstract class ENode implements Comparable<ENode> {

	/**
	 * Enumeration for representing the different nodes.
	 */
	enum EnumNodeType {
		CONSTANT, VARIABLE, OPERATOR
	}

	ENode left, right;
	EToken value;

	/**
	 * @param value EToken value.
	 */
	ENode(EToken value) {
		this(value, null, null);
	}

	/**
	 *
	 * @param value EToken value.
	 * @param left Left node.
	 * @param right Right node.
	 */
	ENode(EToken value, ENode left, ENode right) {
		assert(value != null);
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/**
	 * Checks if has a left node.
	 *
	 * @return true if has left node, else returns false.
	 */
	boolean hasNodeLeft() {
		return left != null;
	}

	/**
	 * Checks if has a right node.
	 *
	 * @return true if has right node, else returns false.
	 */
	boolean hasNodeRight() {
		return right != null;
	}

	/**
	 * Gets the EToken value.
	 *
	 * @return EToken.
	 */
	EToken getValue() {
		return value;
	}

	/**
	 * Helper function for deleting nodes.
	 *
	 * @param node ENode to delete.
	 */
	static void deleteNode(ENode node) {
		if (node == null) return;

		if (node.hasNodeLeft()) {
			deleteNode(node.left);
			node.left = null;
		}

		if (node.hasNodeRight()) {
			deleteNode(node.right);
			node.right = null;
		}

		node.value = null;
	}

	/**
	 * Abstract function for evaluating entire nodes and its subtree(s).
	 *
	 * @return double result.
	 */
	abstract double evaluate();

	/**
	 * Abstract function to get the node's type.
	 *
	 * @return EnumNodeType.
	 */
	abstract EnumNodeType getNodeType();

	@Override
	public abstract int compareTo(ENode other);

	/**
	 * Variable containing node.
	 *
	 * @author hockeyhurd
	 * @version 04/15/2017
	 */
	static class VariableNode extends ENode {

		/**
		 * Constructs a VariableNode with its containing variable.
		 *
		 * @param variable Variable.
		 */
		VariableNode(Variable variable) {
			super(variable);
		}

		@Override
		double evaluate() {
			return value != null ? value.evaluate() : 0.0d;
		}

		@Override
		EnumNodeType getNodeType() {
			return EnumNodeType.VARIABLE;
		}

		@Override
		public int compareTo(ENode other) {
			if (other == null || other.getNodeType() != EnumNodeType.VARIABLE) return 0;

			return value.toString().compareTo(other.value.toString());
		}

	}

	/**
	 * Constant containing node.
	 *
	 * @author hockeyhurd
	 * @version 10/13/2016
	 */
	static class ConstantNode extends ENode {

		/**
		 * Constructs a ConstantNode with its containing constant value.
		 *
		 * @param constant Constant.
		 */
		ConstantNode(Constant constant) {
			super(constant);
		}

		@Override
		double evaluate() {
			return value != null ? value.evaluate() : 0.0d;
		}

		@Override
		EnumNodeType getNodeType() {
			return EnumNodeType.CONSTANT;
		}

		@Override
		public int compareTo(ENode other) {
			if (other == null || other.getNodeType() != EnumNodeType.CONSTANT) return 0;
			final double thisVal = evaluate();
			final double otherVal = other.evaluate();

			return thisVal > otherVal ? 1 : thisVal < otherVal ? -1 : 0;
		}
	}

	/**
	 * Operator containing node.
	 *
	 * @author hockeyhurd
	 * @version 10/13/2016
	 */
	static class OperatorNode extends ENode {

		/**
		 * Constructs a OperatorNode with its containing operator value.
		 *
		 * @param operator Operator.
		 */
		OperatorNode(Operator operator) {
			super(operator);
		}

		@Override
		double evaluate() {
			if (hasNodeLeft() && !hasNodeRight()) return left.evaluate();
			else if (!hasNodeLeft() && hasNodeRight()) return right.evaluate();
			else if (!hasNodeLeft() && !hasNodeRight()) return Double.NaN;

			final Operator op = (Operator) value;
			if (op.isMultiply()) return left.evaluate() * right.evaluate();
			else if (op.isDivide()) return left.evaluate() / right.evaluate();
			else if (op.isModulus()) return left.evaluate() % right.evaluate();
			else if (op.isAdd()) return left.evaluate() + right.evaluate();
			else if (op.isMinus()) return left.evaluate() - right.evaluate();
			else if (op.isExponent()) return Math.pow(left.evaluate(), right.evaluate());
			else if (op.isFactorial()) return Mathd.factorial(right.evaluate());

			return Double.NaN;
		}

		@Override
		EnumNodeType getNodeType() {
			return EnumNodeType.OPERATOR;
		}

		@Override
		public int compareTo(ENode other) {
			if (other == null || other.getNodeType() != EnumNodeType.OPERATOR)
				return EnumNodeType.OPERATOR.ordinal() - other.getNodeType().ordinal();

			final double thisVal = ((Operator) value).getOperator().getPrecedence();
			final double otherVal = ((Operator) other.value).getOperator().getPrecedence();

			return thisVal > otherVal ? 1 : thisVal < otherVal ? -1 : 0;
		}

	}

}
