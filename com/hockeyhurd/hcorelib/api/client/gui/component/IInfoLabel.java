package com.hockeyhurd.hcorelib.api.client.gui.component;

import com.hockeyhurd.hcorelib.api.math.Vector2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Interfacing for gui labels.
 *
 * @author hockeyhurd
 * @version 5/4/2016.
 */
@SideOnly(Side.CLIENT)
public interface IInfoLabel<N> {

	/**
	 * Gets the label to display to user.
	 *
	 * @return List of strings.
	 */
	List<String> getLabel();

	/**
	 * Function to determine if label can be shown.
	 *
	 * @param ignoreMouse boolean flag whether we ignore the mouse position to
	 *                    effect visibility of this label.
	 * @return boolean result.
	 */
	boolean isVisible(boolean ignoreMouse);

	/**
	 * Handles updating of values as necessary.
	 *
	 * @param mouseVec position of mouse.
	 * @param pos position of this label.
	 * @param minMax min/max boundaries.
	 * @param data array of numeric data that can be passed to label from gui.
	 */
	void update(Vector2<Integer> mouseVec, Vector2<Integer> pos, Vector2<Integer> minMax, N[] data);

}
