package com.hockeyhurd.api.client.gui.component;

import com.hockeyhurd.api.math.Vector2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

/**
 * Interfacing for gui buttons.
 *
 * @author hockeyhurd
 * @version 5/4/16
 */
@SideOnly(Side.CLIENT)
public interface IGuiButton {

    /**
     * @param minecraft minecraft client instance.
     * @param x x-pos.
     * @param y y-pos.
     */
    void drawButton(Minecraft minecraft, int x, int y);

    /**
     * @param minecraft minecraft client instance.
     * @param x x-pos.
     * @param y y-pos.
     * @return true if mouse was pressed, else returns false.
     */
    boolean mousePressed(Minecraft minecraft, int x, int y);

    /**
     * @return true if button is active, else returns false.
     */
    boolean isActive();

    /**
     * Sets whether button is active or not.
     *
     * @param active value to set.
     */
    void setActive(boolean active);

    /**
     * Gets the string text.
     *
     * @return String.
     */
    String getText();

    /**
     * Sets the buttons string text.
     *
     * @param text String text to set.
     */
    void setText(String text);

    /**
     * @return vector position of button.
     */
    Vector2<Integer> getPos();

}
