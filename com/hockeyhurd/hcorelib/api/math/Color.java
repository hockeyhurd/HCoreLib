package com.hockeyhurd.hcorelib.api.math;

/**
 * Abstracted class for all other color implementations.
 *
 * @author hockeyhurd
 * @version 12/26/2015.
 */
public abstract class Color {

	/**
	 * Get hexadecimal int for ARGB.
	 *
	 * @return ARGB.
	 */
	public abstract int getARGB();

	/**
	 * Get hexadecimal int for RGBA.
	 *
	 * @return RGBA.
	 */
	public abstract int getRGBA();

	/**
	 * Gets a copy of the object.
	 *
	 * @return Color copy.
	 */
	public abstract Color copy();

}
