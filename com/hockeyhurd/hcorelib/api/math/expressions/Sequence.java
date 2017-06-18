package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * Class for storing/tracking sequential characters.
 *
 * @author hockeyhurd
 * @version 6/18/2017.
 */
final class Sequence {

	private StringBuilder builder;

	Sequence() {
		this(null);
	}

	Sequence(String seq) {
		if (seq != null && !seq.isEmpty())
			builder = new StringBuilder(seq);
		else
			builder = new StringBuilder();
	}

	void append(char ch) {
		builder.append(ch);
	}

	char getLastChar() {
		return builder.length() > 0 ? builder.charAt(builder.length() - 1) : 0;
	}

	char getCharAt(final int index) {
		return builder.charAt(index);
	}

	String getCurrentSequence() {
		return builder.toString();
	}

	boolean isEqual(Sequence sequence) {
		return sequence != null && builder.equals(sequence.builder);
	}

	boolean isEqual(String sequence) {
		return sequence != null && builder.toString().equals(sequence);
	}

}
