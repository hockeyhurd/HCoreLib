package com.hockeyhurd.hcorelib.api.math;

/**
 * Abstracted class for all other color implementations.
 *
 * @author hockeyhurd
 * @version 12/26/2015.
 */
public abstract class Color {

	/**
	 * Get hexdecimal int for ARGB.
	 *
	 * @return ARGB.
	 */
	public abstract int getARGB();

	/**
	 * Get hexdecimal int for RGBA.
	 *
	 * @return RGBA.
	 */
	public abstract int getRGBA();

}
