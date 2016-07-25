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
	 * Sets all values to boolean value.
	 *
	 * @param flag boolean value to set.
	 */
	public abstract void set(boolean flag);

	/**
	 * Sets the boolean value at index.
	 *
	 * @param flag boolean value to set.
	 * @param index int index to set at.
	 */
	public abstract void set(boolean flag, int index);

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
	public abstract boolean[] get(int startIndex, int endIndex);

	/**
	 * Gets boolean array representation contained in int.
	 *
	 * @return boolean array.
	 */
	public abstract boolean[] values();

}
