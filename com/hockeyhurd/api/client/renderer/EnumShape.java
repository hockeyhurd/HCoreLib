package com.hockeyhurd.api.client.renderer;

/**
 * Enumeration for shapes.
 *
 * @author hockeyhurd
 * @version 12/26/2015.
 */
public enum EnumShape {

	SQUARE((byte) 4), RECT((byte) 4), CIRCLE((byte) 1), ELLIPSE((byte) 1), TRIANGLE((byte) 3);

	public final byte NUM_SIDES;

	/**
	 * @param sides number of sides making up of the shape.
	 */
	EnumShape(byte sides) {
		this.NUM_SIDES = sides;
	}

	@Override
	public String toString() {
		return name();
	}

}
