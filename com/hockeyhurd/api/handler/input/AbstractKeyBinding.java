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

	private boolean keyDown = false;

	public AbstractKeyBinding(String name, int keyCode, String category) {
		super(name, keyCode, category);
	}

	public boolean isKeyDown() {
		return keyDown;
	}

	@Override
	public boolean isPressed() {
		return (keyDown = super.isPressed());
	}

	public void onKeyPressed(KeyInputEvent event) {
		if (keyDown) return;

		activate(event);
		keyDown = true;

		HCoreLibMain.lh.info("Pressed");
	}

	protected abstract void activate(KeyInputEvent event);

}
