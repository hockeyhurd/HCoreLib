package com.hockeyhurd.hcorelib.api.util.datatypes;

/**
 * Abstract boolean class for extensible
 * and compact boolean storage array.
 *
 * @author hockeyhurd
 * @version 7/24/2016.
 */
public abstract class Bool {

	public static final int FALSE = 0;
	public static final int TRUE = 1;

	/**
	 * Gets the bit width.
	 *
	 * @return int bit width.
	 */
	protected abstract int getBitWidth();

	/**
	 * Gets the offset value by index.
	 *
	 * @param index int index.
	 * @return offset value based from index.
	 */
	protected abstract Number getOffsetValue(int index);

	/**
	 * Sets all values to boolean value.
	 *
	 * @param flag boolean value to set.
	 */
	public abstract Bool set(boolean flag);

	/**
	 * Sets the boolean value at index.
	 *
	 * @param flag boolean value to set.
	 * @param index int index to set at.
	 */
	public abstract Bool set(boolean flag, int index);

	/**
	 * Gets the boolean value at the index.
	 *
	 * @param index int index to get.
	 * @return boolean value at index.
	 */
	public abstract boolean get(int index);

	/**
	 * Gets the boolean array from the requested indicies.
	 *
	 * @param indicies int indicies to retrieve.
	 * @return boolean array.
	 */
	public abstract boolean[] get(int[] indicies);

	/**
	 * Gets the boolean values in range.
	 *
	 * @param startIndex int starting index.
	 * @param endIndex int end index.
	 * @return boolean array.
	 */
	public boolean[] get(int startIndex, int endIndex) {
		if (startIndex < 0) startIndex = 0;
		if (endIndex > getBitWidth()) endIndex = getBitWidth();
		if (startIndex > endIndex) {
			startIndex ^= endIndex;
			endIndex ^= startIndex;
			startIndex ^= endIndex;
		}

		final boolean[] output = new boolean[endIndex - startIndex + 1];
		int counter = 0;

		for (int i = startIndex; i < endIndex; i++)
			output[counter++] = get(i);

		return output;
	}

	/**
	 * Gets boolean array representation contained in int.
	 *
	 * @return boolean array.
	 */
	public boolean[] values() {
		final boolean[] output = new boolean[getBitWidth()];

		for (int i = 0; i < output.length; i++)
			output[i] = get(i);

		return output;
	}

	/**
	 * Ands two booleans together.
	 *
	 * @param bool Other Boolean.
	 * @return Boolean result.
	 */
	public abstract Bool and(Bool bool);

	/**
	 * Ors two booleans together.
	 *
	 * @param bool Other Boolean.
	 * @return Boolean result.
	 */
	public abstract Bool or(Bool bool);

	/**
	 * Xors two booleans together.
	 *
	 * @param bool Other Boolean.
	 * @return Boolean result.
	 */
	public abstract Bool xor(Bool bool);

	/**
	 * Nots two booleans together.
	 *
	 * @return Boolean result.
	 */
	public abstract Bool not();

}
