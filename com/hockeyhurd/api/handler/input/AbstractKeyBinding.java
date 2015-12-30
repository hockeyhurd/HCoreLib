package com.hockeyhurd.api.handler.input;

import com.hockeyhurd.mod.HCoreLibMain;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

/**
 * Interface for keybindings.
 *
 * @author hockeyhurd
 * @version 12/29/2015.
 */
@SideOnly(Side.CLIENT)
public abstract class AbstractKeyBinding extends KeyBinding {

	/**
	 * @param name Name of keybinding.
	 * @param keyCode Key code for key.
	 * @param category Category to be placed in.
	 */
	public AbstractKeyBinding(String name, int keyCode, String category) {
		super(name, keyCode, category);
	}

	/**
	 * Method directly called from event handler.
	 *
	 * @param event KeyInputEvent referenced.
	 */
	public final void onKeyPressed(KeyInputEvent event) {
		activate(event);

		if (HCoreLibMain.configHandler.isDebugMode()) HCoreLibMain.logHelper.info("Pressed");
	}

	/**
	 * Abstract method called on key pressed event.
	 *
	 * @param event KeyInputEvent referenced.
	 */
	protected abstract void activate(KeyInputEvent event);

}
