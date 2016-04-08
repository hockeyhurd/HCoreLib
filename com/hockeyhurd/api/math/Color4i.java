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
		int r = colorCorrect((hex >> 0x18) & 0xff);
		int g = colorCorrect((hex >> 0x10) & 0xff);
		int b = colorCorrect((hex >> 0x8) & 0xff);
		int a = colorCorrect((hex) & 0xff);

		rgba = (r << 0x18) + (g << 0x10) + (b << 0x8) + a;
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
		int g = (rgba >> 0x10) & 0xff;
		int b = (rgba >> 0x8) & 0xff;
		int a = rgba & 0xff;

		rgba = (r << 0x18) + (g << 0x10) + (b << 0x8) + a;
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
		int r = (rgba >> 0x18) & 0xff;
		g = colorCorrect(g);
		int b = (rgba >> 0x8) & 0xff;
		int a = rgba & 0xff;

		rgba = (r << 0x18) + (g << 0x10) + (b << 0x8) + a;
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
		int r = (rgba >> 0x18) & 0xff;
		int g = (rgba >> 0x10) & 0xff;
		b = colorCorrect(b);
		int a = rgba & 0xff;

		rgba = (r << 0x18) + (g << 0x10) + (b << 0x8) + a;
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
		int r = (rgba >> 0x18) & 0xff;
		int g = (rgba >> 0x10) & 0xff;
		int b = (rgba >> 0x8) & 0xff;
		a = colorCorrect(a);

		rgba = (r << 0x18) + (g << 0x10) + (b << 0x8) + a;
	}

	/**
	 * Simple function used to check if the value is in range of 0 <= val <= 255.
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
		int r = (rgba >> 0x18) & 0xff;
		int g = (rgba >> 0x10) & 0xff;
		int b = (rgba >> 0x8) & 0xff;
		int a = rgba & 0xff;

		return (a << 0x18) + (r << 0x10) + (g << 0x8) + b;
	}

	@Override
	public String toString() {
		return String.format("R: %d, G: %d, B: %d, A: %d\n", (rgba >> 0x18) & 0xff,
				(rgba >> 0x10) & 0xff, (rgba >> 0x8) & 0xff, rgba & 0xff);
	}

}
