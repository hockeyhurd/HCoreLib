package com.hockeyhurd.api.math;

/**
 * @author hockeyhurd
 * @version 9/3/15
 */
public final class Color4f {

	private float r, g, b, a;
	public static final float MAX_VALUE = 1.0f;
	public static final float MIN_VALUE = 0.0f;

	/**
	 * Generic constructor which defaults all channel values to '0.0f'.
	 */
	public Color4f() {
	}

	/**
	 * Constructor from hexdecimal.
	 *
	 * @param hex hexdecimal (must be in 'ARGB'!) to use.
	 */
	public Color4f(final int hex) {
		a = (hex >> 0x18) & 0xff;
		r = (hex >> 0x10) & 0xff;
		g = (hex >> 0x8) & 0xff;
		b = (hex) & 0xff;

		r /= (float) Color4i.MAX_VALUE;
		g /= (float) Color4i.MAX_VALUE;
		b /= (float) Color4i.MAX_VALUE;
		a /= (float) Color4i.MAX_VALUE;

		r = colorCorrect(r);
		g = colorCorrect(g);
		b = colorCorrect(b);
		a = colorCorrect(a);
	}

	/**
	 * Generic constructor; assumes alpha value to be '1.0f'.
	 *
	 * @param r channel.
	 * @param g channel.
	 * @param b channel.
	 */
	public Color4f(float r, float g, float b) {
		this(r, g, b, 1.0f);
	}

	/**
	 * Main constructor used for initializing and storing color channel data.
	 *
	 * @param r channel.
	 * @param g channel.
	 * @param b channel.
	 * @param a channel.
	 */
	public Color4f(float r, float g, float b, float a) {
		this.r = colorCorrect(r);
		this.g = colorCorrect(g);
		this.b = colorCorrect(b);
		this.a = colorCorrect(a);
	}

	/**
	 * Function used to clamp value to interval [0.0f, 1.0f] (inclusively).
	 *
	 * @param value value to evaluate.
	 * @return color corrected value.
	 */
	public static float colorCorrect(float value) {
		return value < MIN_VALUE ? MIN_VALUE : value > MAX_VALUE ? MAX_VALUE : value;
	}

	/**
	 * @return the r channel.
	 */
	public float getR() {
		return r;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(int r) {
		this.r = colorCorrect(r);
	}

	/**
	 * @return the g channel.
	 */
	public float getG() {
		return g;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(int g) {
		this.g = colorCorrect(g);
	}

	/**
	 * @return the b channel.
	 */
	public float getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(int b) {
		this.b = colorCorrect(b);
	}

	/**
	 * @return the a channel.
	 */
	public float getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(int a) {
		this.a = colorCorrect(a);
	}

	/**
	 * @return color int represented in rgba as decimal number.
	 */
	public int getRGBA() {
		int r = (int) Math.floor(this.r * Color4i.MAX_VALUE);
		int g = (int) Math.floor(this.g * Color4i.MAX_VALUE);
		int b = (int) Math.floor(this.b * Color4i.MAX_VALUE);
		int a = (int) Math.floor(this.a * Color4i.MAX_VALUE);

		return (r << 0x18) + (g << 0x10) + (b << 0x8) + a;
	}

	/**
	 * @return color int represented in argb as decimal number.
	 */
	public int getARGB() {
		int r = (int) Math.floor(this.r * Color4i.MAX_VALUE);
		int g = (int) Math.floor(this.g * Color4i.MAX_VALUE);
		int b = (int) Math.floor(this.b * Color4i.MAX_VALUE);
		int a = (int) Math.floor(this.a * Color4i.MAX_VALUE);

		return (a << 0x18) + (r << 0x10) + (g << 0x8) + b;
	}

	@Override
	public String toString() {
		return String.format("R: %f, G: %f, B: %f, A: %f\n", r, g, b, a);
	}

}
