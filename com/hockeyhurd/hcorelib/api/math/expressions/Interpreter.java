package com.hockeyhurd.hcorelib.api.math.expressions;

import com.hockeyhurd.hcorelib.mod.HCoreLibMain;

import java.util.Scanner;
import java.util.Stack;

/**
 * Class for interpreting mathematical expressions.
 *
 * @author hockeyhurd
 * @version 10/14/2016.
 */
public final class Interpreter {

	private ETree tree;

	/**
	 * Constructs the interpreter and initializes an empty expression tree.
	 */
	public Interpreter() {
		tree = new ETree();
	}

	/**
	 * Helper function used for parsing strings into doubles.
	 *
	 * @param string String to parse.
	 * @return DoubleResult result.
	 */
	private static DoubleResult isValidDouble(String string) {
		if (string == null || string.isEmpty()) return DoubleResult.getFailCondition();
		else if (string.length() == 1 && EnumOp.isOp(string.charAt(0))) return DoubleResult.getFailCondition();

		try {
			double result = Double.parseDouble(string);
			return new DoubleResult(true, result);
		}

		catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return DoubleResult.getFailCondition();
	}

	/**
	 * Attempts to process an expression to a double result.
	 *
	 * @param expression Expression to interpret.
	 * @return double result.
	 */
	public double processExpressionDouble(Expression expression) {
		Stack<ENode> randStack = new Stack<ENode>();
		Stack<ENode.OperatorNode> opStack = new Stack<ENode.OperatorNode>();
		ENode.OperatorNode lastNode = null;
		boolean firstPass = true;
		boolean errored = false;

		String buf = "";

		Expression.ExpressionBuilder expressionBuilder = new Expression.ExpressionBuilder();
		expressionBuilder.addString(expression.toString());
		Expression.ExpressionBuilder.ExpressionResult expressionResult = expressionBuilder.preProcessInput();

		Scanner scanner = new Scanner(expressionResult.string);
		// double valBuf = 0.0;

		while (scanner.hasNext()) {
			buf = scanner.next();

			boolean pushResult = true;

			if (firstPass) {
				if (!tree.isEmpty()) tree.clear();
				firstPass = false;
			}

			DoubleResult doubleResult = isValidDouble(buf);
			if (doubleResult.result) {
				randStack.push(new ENode.ConstantNode(new Constant(doubleResult.value)));
			}

			else if (buf.length() == 1) {
				final char c = buf.charAt(0);
				if (c ==  '(' || c == '[' || c == '{') {
					ENode.OperatorNode operatorNode = new ENode.OperatorNode(new Operator(EnumOp.getOperatorFromString(buf)));
					if (tree.isEmpty()) tree.add(operatorNode);
					lastNode = operatorNode;

					opStack.push(operatorNode);
				}

				else if (c == ')' || c == ']' || c == '}') {
					while (!((Operator) opStack.peek().value).isParenthesisLeft() && pushResult) {
						// lastNode = opStack.pop();
						pushResult = pushOpNode(randStack, opStack.peek());
						opStack.pop();
					}

					opStack.pop();
				}

				else if (EnumOp.isOp(c)) {
					Operator operator = new Operator(EnumOp.getOperatorFromString(buf));

					while (!opStack.isEmpty() && ((Operator) opStack.peek().getValue()).getOperator().getPrecedence() <=
							operator.getOperator().getPrecedence() && pushResult) {
						lastNode = opStack.pop();
						pushResult = pushOpNode(randStack, lastNode);
					}

					ENode.OperatorNode operatorNode = new ENode.OperatorNode(operator);
					if (tree.isEmpty()) tree.add(operatorNode);

					opStack.push(operatorNode);
				}
			}

			// An error has occurred!?!?
			else {
				HCoreLibMain.logHelper.severe(buf);
				errored = true;
				break;
			}
		}

		while (!opStack.isEmpty()) {
			pushOpNode(randStack, opStack.pop());
		}

		if (lastNode != null) {
			if (!tree.isEmpty()) {
				ENode node = randStack.pop();
				tree.setRoot(node);
			}
		}

		if (tree != null) {
			if (!errored) {
				double result = tree.evaluate();

				if (result == Double.NaN) result = 0.0d;
				return result;
			}

			else HCoreLibMain.logHelper.severe("Errored and aborted!");

			// tree.clear();
			errored = false;
		}

		return 0.0;
	}

	/**
	 * Attempts to process an expression to a String result.
	 *
	 * @param expression Expression to interpret.
	 * @return InterpreterResult result.
	 */
	public InterpreterResult processExpressionString(Expression expression) {
		final double result = processExpressionDouble(expression);

		return new InterpreterResult(expression.toString() + " = " + result, result);
	}

	/**
	 * Helper function to push operator nodes onto an operand stack.
	 *
	 * @param randStack Operand stack.
	 * @param n OperatorNode to push.
	 * @return boolean result.
	 */
	private static boolean pushOpNode(Stack<ENode> randStack, ENode.OperatorNode n) {
		if (n == null || randStack == null || randStack.size() < 2) return false;

		n.right = randStack.pop();
		n.left = randStack.pop();

		randStack.push(n);

		return true;
	}

	/**
	 * Helper struct for DoubleResult.
	 *
	 * @author hockeyhurd
	 * @version 10/13/2016
	 */
	private static class DoubleResult {
		boolean result;
		double value;

		DoubleResult(boolean result, double value) {
			this.result = result;
			this.value = value;
		}

		static DoubleResult getFailCondition() {
			return new DoubleResult(false, Double.NaN);
		}
	}

}
