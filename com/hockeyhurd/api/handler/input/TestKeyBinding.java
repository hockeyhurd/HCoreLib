package com.hockeyhurd.api.handler.input;

import com.hockeyhurd.mod.HCoreLibMain;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author hockeyhurd
 * @version 12/29/2015.
 */
@SideOnly(Side.CLIENT)
public class TestKeyBinding extends AbstractKeyBinding {

	public TestKeyBinding(String name, int keyCode, String category) {
		super(name, keyCode, category);
	}

	@Override
	protected void activate(InputEvent.KeyInputEvent event) {
		HCoreLibMain.lh.info("onKeyPressed called!");
	}

}
