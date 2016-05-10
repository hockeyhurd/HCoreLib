package com.hockeyhurd.hcorelib.api.handler.input;

import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Class example of implmenting a KeyBind from this API.
 * <br><bold>NOTE: </bold>This class does nothing but print "onKeyPressed called!" when activated.
 *
 * @author hockeyhurd
 * @version 12/29/2015.
 */
@SideOnly(Side.CLIENT)
public class TestKeyBinding extends AbstractKeyBinding {

	/**
	 * @param name     Name of keybinding.
	 * @param keyCode  Key code for key.
	 * @param category Category to be placed in.
	 */
	public TestKeyBinding(String name, int keyCode, String category) {
		super(name, keyCode, category);
	}

	@Override
	protected void activate(InputEvent.KeyInputEvent event) {
		HCoreLibMain.logHelper.info("onKeyPressed called!");
	}

}
