package com.hockeyhurd.api.math;

import com.hockeyhurd.api.util.Color;

/**
 * Color class used to store color based pixel data.
 * 
 * @author hockeyhurd
 * @version Oct 18, 2014
 */
public final class Color4i extends Color {

	// private int r, g, b, a;
	private int rgba;
	public static final int MAX_VALUE = 0xff;
	public static final int MIN_VALUE = 0x0;

	/**
	 * Generic constructor which defaults all channel values to '255'.
	 */
	public Color4i() {
		this.rgba = 0x0;
	}

	/**
	 * Generic constructor; assumes alpha value to be '255'.
	 *
	 * @param r channel.
	 * @param g channel.
	 * @param b channel.
	 */
	public Color4i(int r, int g, int b) {
		this(r, g, b, 0xff);
	}
	
	/**
	 * Main constructor used for initializing and storing color channel data.
	 * 
	 * @param r channel.
	 * @param g channel.
	 * @param b channel.
	 * @param a channel.
	 */
	public Color4i(int r, int g, int b, int a) {
		r = colorCorrect(r);
		g = colorCorrect(g);
		b = colorCorrect(b);
		a = colorCorrect(a);

		rgba = (r << 0x18) + (g << 0x10) + (b << 0x8) + a;
	}

	/**
	 * Constructor from hexdecimal.
	 *
	 * @param hex hexdecimal (must be in 'RGBA'!) to use.
	 */
	public Color4i(final int hex) {
		if (hex >= 0xffffffff) rgba = 0xffffffff;
		else if (hex <= 0) rgba = 0;
		else rgba = hex;
	}
	
	/**
	 * @return the r channel.
	 */
	public int getR() {
		return (rgba >> 0x18) & 0xff;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(int r) {
		r = colorCorrect(r);
		rgba &= r << 0x18;
	}

	/**
	 * @return the g channel.
	 */
	public int getG() {
		return (rgba >> 0x10) & 0xff;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(int g) {
		g = colorCorrect(g);
		rgba &= g << 0x10;
	}

	/**
	 * @return the b channel.
	 */
	public int getB() {
		return (rgba >> 0x8) & 0xff;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(int b) {
		b = colorCorrect(b);
		rgba &= b << 0x8;
	}

	/**
	 * @return the a channel.
	 */
	public int getA() {
		return rgba & 0xff;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(int a) {
		a = colorCorrect(a);
		rgba &= a;
	}

	/**
	 * Simple function used to check if the value is in range of 0 <= val <= 255.
	 *
	 * @param val = input value.
	 * @return return corrected value if invalid, else return the input value.
	 */
	public static int colorCorrect(int val) {
		return val < MIN_VALUE ? MIN_VALUE : (val > MAX_VALUE ? MAX_VALUE : val);
	}

	/**
	 * @return color int represented in rgba as decimal number.
	 */
	public int getRGBA() {
		return rgba;
	}

	/**
	 * @return color int represented in argb as decimal number.
	 */
	public int getARGB() {
		return ((rgba & 0xff) << 0x18) + (((rgba >> 0x8) & 0xff) << 0x10) +
				(((rgba >> 0x10) & 0xff) << 0x8) + ((rgba >> 0x18) & 0xff);
	}

	@Override
	public String toString() {
		return String.format("R: %d, G: %d, B: %d, A: %d", (rgba >> 0x18) & 0xff,
				(rgba >> 0x10) & 0xff, (rgba >> 0x8) & 0xff, rgba & 0xff);
	}

}
