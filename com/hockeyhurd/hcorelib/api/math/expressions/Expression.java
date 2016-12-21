package com.hockeyhurd.hcorelib.api.math.expressions;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.hockeyhurd.hcorelib.api.math.expressions.Expression.ExpressionBuilder.ExpressionResult.getFailCondition;

/**
 * Class for containing and processing string expressions.
 *
 * @author hockeyhurd
 * @version 10/14/2016.
 */
public final class Expression {

	private String string;

	public Expression(String string) {
		if (string == null || string.isEmpty()) this.string = "0";
		else this.string = string;
	}

	public Expression(Expression expression) {
		this.string = expression.string;
	}

	@Override
	public String toString() {
		return string;
	}

	static class ExpressionBuilder {
		private LinkedList<Character> list;

		static class ExpressionResult {
			boolean result;
			String string;

			ExpressionResult(boolean result, String string) {
				this.result = result;
				this.string = string;
			}

			static ExpressionResult getFailCondition() {
				return new ExpressionResult(false, "");
			}
		}

		ExpressionBuilder() {
			this.list = new LinkedList<Character>();
		}

		void clear() {
			list.clear();
		}

		void addChar(char c) {
			list.add(c);
		}

		void addString(String string) {
			if (string == null || string.isEmpty()) return;

			for (char c : string.toCharArray())
				list.add(c);
		}

		private static boolean isValidChar(char c) {
			return EnumOp.isOp(c) || c == '.' || c == 'e' || c == 'E'
					|| c == 'p' || c == 'P' || c == 'i' || c == 'I' ||
					c == GlobalConstants.PI_CHAR || (c >= '0' && c <= '9');
		}

		ExpressionResult preProcessInput() {
			if (list.isEmpty()) return getFailCondition();

			ArrayList<Character> arrayList = new ArrayList<Character>(list.size() << 1);
			boolean isNum = false;
			char lastChar = 0;

			for (char c : list) {
				if (c == ' ') continue;
				else if (!isValidChar(c)) return getFailCondition();
				else {
					if (c == '.' || (c >= '0' && c <= '9')) {
						if (arrayList.isEmpty()) {
							isNum = true;
							arrayList.add(c);
							continue;
						}

						if (!isNum) {
							isNum = true;
							arrayList.add(' ');
						}

						arrayList.add(c);
					}

					else if (c == 'e' || c == 'E') {
						if (isNum) return getFailCondition();
						else arrayList.add(' ');

						final String e = Double.toString(GlobalConstants.MATH_E);

						for (char ch : e.toCharArray())
							arrayList.add(ch);

						isNum = false;
						// arrayList.add(' ');
					}

					else if (c == 'p' || c == 'P') lastChar = 'p';
					else if (c == 'i' || c == 'I' || c == GlobalConstants.PI_CHAR) {
						if (lastChar == 'p' || c == GlobalConstants.PI_CHAR) {
							if (isNum) return getFailCondition();
							else arrayList.add(' ');

							final String pi = Double.toString(GlobalConstants.MATH_PI);

							for (char ch : pi.toCharArray())
								arrayList.add(ch);

							lastChar = 0;
							isNum = false;
						}

						else return getFailCondition();
					}

					else {
						if (arrayList.isEmpty()) {
							isNum = false;
							arrayList.add('0');
							arrayList.add(' ');
							arrayList.add(c);
							continue;
						}

						isNum = false;

						arrayList.add(' ');
						arrayList.add(c);
					}
				}
			}

			if (arrayList.isEmpty()) return getFailCondition();

			StringBuilder stringBuilder = new StringBuilder(arrayList.size());

			for (char c : arrayList) {
				stringBuilder.append(c);
			}

			return new ExpressionResult(true, stringBuilder.toString());
		}

		ExpressionResult preProcessExpression(Expression expression) {
			assert(expression != null);
			addString(expression.string);
			return preProcessInput();
		}

		void copyTo(char[] arr) {
			final int copyLen = Math.min(arr.length, list.size());

			int i = 0;
			for (char c : list) {
				arr[i++] = c;
				if (i >= copyLen) break;
			}
		}

	}

}
