package com.hockeyhurd.hcorelib.api.tileentity;

/**
 * Enumeration for any component that requires a color or two.
 *
 * @author hockeyhurd
 * @version Oct 31, 2014
 */
public enum EnumColor {

    RED("red"), ORANGE("orange"), CLEAR("clear"), GREEN("green"), GREEN_OPAQUE("green_opaque"), BLUE("blue");

    private static int counter = 0;
    private String color;

    /**
     * Constructor for creating new color enumerators.
     * @param colorAsText = color as text, NOTE: Color red = "red"
     * @param id = id to assign to color.
     */
    private EnumColor(String colorAsText) {
        this.color = colorAsText;
    }

    /**
     * Function used to get id for color.
     * @return return color id.
     */
    public int getID() {
        return this.ordinal();
    }

    /**
     * Function used to get the color represented in a string.
     * @return return string value stored for said color.
     */
    public String getColorAsString() {
        return this.color;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.color;
    }

}
