package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Expression binary search tree.
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
public final class ETree {

	private ENode root;
	private double cachedResult;

	/**
	 * Creates an empty expression binary tree.
	 */
	public ETree() {
		this(null);
	}

	/**
	 * Creates an expression binary tree with its root node.
	 *
	 * @param root ENode root.
	 */
	public ETree(ENode root) {
		this.root = root;
	}

	public boolean isAssignment() {
		return root.toString().equals(EnumOp.EQUALS.toString());
		// return root != null && root.hasNodeLeft() && root.left.toString().equals(En)
	}

	/**
	 * Function checks if the expression tree is empty.
	 *
	 * @return boolean result.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Sets the root node of the expression tree.
	 *
	 * @param root ENode root.
	 */
	public void setRoot(ENode root) {
		this.root = root;
	}

	/**
	 * Adds a node to a stem/subtree root.
	 *
	 * @param stem ENode.
	 * @param node ENode.
	 * @return boolean result.
	 */
	private boolean add(ENode stem, ENode node) {
		assert(node != null);
		if (stem == null) {
			stem = node;
			return true;
		}

		final int result = stem.compareTo(node);
		if (result > 0) return add(stem.left, node);
		else if (result <= 0) return add(stem.right, node);

		return false;
	}

	/**
	 * Adds a node to expression tree.
	 *
	 * @param node ENode.
	 * @return boolean result.
	 */
	public boolean add(ENode node) {
		if (node == null) return false;
		else if (root == null) {
			root = node;
			return true;
		}

		return add(root, node);
	}

	/**
	 * Attempts to evaluate an expression tree.
	 *
	 * @return double result.
	 */
	public double evaluate() {
		// return root != null ? root.evaluate() : Double.NaN;
		if (root == null)
			return Double.NaN;

		cachedResult = root.evaluate();

		return cachedResult;
	}

	/**
	 * Gets the root node.
	 *
	 * @return ENode.
	 */
	ENode getRootNode() {
		return root;
	}

	boolean isRootAVariable() {
		return root != null && root instanceof ENode.VariableNode;
	}

	/**
	 * Clears/nulls out expression tree.
	 */
	public void clear() {
		root = null;
		cachedResult = 0.0d;
	}

	/**
	 * Helper function to get the tree in the form of an in-order string.
	 *
	 * @return String expression.
	 */
	private String inOrderToString() {
		if (root == null) return "<empty>";

		final StringBuilder builder = new StringBuilder(0x20);
		inOrderToString(root, builder);

		return builder.toString();
	}

	/**
	 * Helper function to get the tree in the form of an in-order string.
	 *
	 * @param stem ENode subtree.
	 * @param builder StringBuilder to append.
	 */
	private void inOrderToString(ENode stem, StringBuilder builder) {
		if (stem == null) return;
		else if (stem.hasNodeLeft()) inOrderToString(stem.left, builder);

		builder.append(stem.value);

		if (stem.hasNodeRight()) inOrderToString(stem.right, builder);
	}

	@Override
	public String toString() {
		return inOrderToString();
	}

	static class AssignmentResult {

		Variable var;
		double result;

		AssignmentResult(Variable var, double result) {
			this.var = var;
			this.result = result;
		}

		boolean isAssignment() {
			return var != null;
		}

	}

}
