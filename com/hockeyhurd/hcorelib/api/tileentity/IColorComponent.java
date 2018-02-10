package com.hockeyhurd.hcorelib.api.tileentity;

/**
 *
 *
 * @author hockeyhurd
 * @version Feb 5, 2015
 */
public interface IColorComponent {

    /**
     * Function used to get color of component.
     *
     * @return color of the component if has a color, else returns null.
     */
    EnumColor getColor();

    /**
     * Method to set color of given component.
     *
     * @param color color to set.
     */
    void setColor(EnumColor color);

}
