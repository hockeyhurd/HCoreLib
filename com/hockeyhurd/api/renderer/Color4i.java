package com.hockeyhurd.api.renderer;

/**
 * Color class used to store color based pixel data.
 * 
 * @author hockeyhurd
 * @version Oct 18, 2014
 */
public class Color4i {

	private int r, g, b, a;
	
	/**
	 * Generic constructor; assumes alpha value to be '255'.
	 * @param r channel.
	 * @param g channel.
	 * @param b channel.
	 */
	public Color4i(int r, int g, int b) {
		this(r, g, b, 255);
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
		this.r = colorCorrect(r);
		this.g = colorCorrect(g);
		this.b = colorCorrect(b);
		this.a = colorCorrect(a);
	}

	/**
	 * Constructor from hexdecimal.
	 *
	 * @param hex hexdecimal (must be in 'ARGB'!) to use.
	 */
	public Color4i(final int hex) {
		this.a = colorCorrect((hex >> 0x18) & 0xff);
		this.r = colorCorrect((hex >> 0x10) & 0xff);
		this.g = colorCorrect((hex >> 0x8) & 0xff);
		this.b = colorCorrect((hex) & 0xff);
	}
	
	/**
	 * @return the r channel.
	 */
	public int getR() {
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
	public int getG() {
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
	public int getB() {
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
	public int getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(int a) {
		this.a = colorCorrect(a);
	}

	/**
	 * Simple function used to check if the value is in range of 0 <= val <= 255.
	 * @param val = input value.
	 * @return return corrected value if invalid, else return the input value.
	 */
	private int colorCorrect(int val) {
		return val < 0 ? 0 : (val > 255 ? 255 : val);
	}

	/**
	 * @return color int represented in rgba as decimal number.
	 */
	public int getRGBA() {
		return (r << 0x18) + (g << 0x10) + (b << 0x8) + a;
	}

	/**
	 * @return color int represented in argb as decimal number.
	 */
	public int getARGB() {
		return (a << 0x18) + (r << 0x10) + (g << 0x8) + b;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("R: ");
		builder.append(r);
		builder.append(", G: ");
		builder.append(g);
		builder.append(", B: ");
		builder.append(b);
		builder.append(", A: ");
		builder.append(a);

		return builder.toString();
	}

}
