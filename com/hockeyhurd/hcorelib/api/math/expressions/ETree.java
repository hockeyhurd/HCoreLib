package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Expression binary search tree.
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
public final class ETree {

	private ENode root;

	public ETree() {
		this(null);
	}

	public ETree(ENode root) {
		this.root = root;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void setRoot(ENode root) {
		this.root = root;
	}

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

	public boolean add(ENode node) {
		if (node == null) return false;
		else if (root == null) {
			root = node;
			return true;
		}

		return add(root, node);
	}

	public double evaluate() {
		return root != null ? root.evaluate() : Double.NaN;
	}

	public void clear() {
		root = null;
	}

	private String inOrderToString() {
		if (root == null) return "<empty>";

		final StringBuilder builder = new StringBuilder(0x20);
		inOrderToString(root, builder);

		return builder.toString();
	}

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

}
