package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Expression Token.
 *
 * @author hockeyhurd
 * @version 10/13/2016.
 */
abstract class EToken {

	String prefix;
	String suffix;

	/**
	 * @param prefix Token prefix.
	 * @param suffix Token suffix.
	 */
	EToken(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}

	/**
	 * Gets prefix.
	 *
	 * @return String.
	 */
	String getPrefix() {
		return prefix;
	}

	/**
	 * Gets suffix.
	 *
	 * @return String.
	 */
	String getSuffix() {
		return suffix;
	}

	/**
	 * Evaluates token.
	 *
	 * @return evaluation result.
	 */
	abstract double evaluate();

}
