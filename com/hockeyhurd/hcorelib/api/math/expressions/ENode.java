package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Expression Node
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
abstract class ENode implements Comparable<ENode> {

	enum EnumNodeType {
		CONSTANT, OPERATOR
	}

	ENode left, right;
	EToken value;

	ENode(EToken value) {
		this(value, null, null);
	}

	ENode(EToken value, ENode left, ENode right) {
		assert(value != null);
		this.value = value;
		this.left = left;
		this.right = right;
	}

	boolean hasNodeLeft() {
		return left != null;
	}

	boolean hasNodeRight() {
		return right != null;
	}

	EToken getValue() {
		return value;
	}

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

	abstract double evaluate();

	abstract EnumNodeType getNodeType();

	@Override
	public abstract int compareTo(ENode other);

	static class ConstantNode extends ENode {

		ConstantNode(Constant constant) {
			super(constant);
		}

		@Override
		double evaluate() {
			return value != null ? value.evaluate() : 0.0;
		}

		@Override
		EnumNodeType getNodeType() {
			return EnumNodeType.CONSTANT;
		}

		@Override
		public int compareTo(ENode other) {
			if (other == null || other.getNodeType() != EnumNodeType.CONSTANT) return 0;
			final double thisVal = value.evaluate();
			final double otherVal = other.value.evaluate();

			return thisVal > otherVal ? 1 : thisVal < otherVal ? -1 : 0;
		}
	}

}
