package com.hockeyhurd.api.handler.input;

import com.hockeyhurd.mod.HCoreLibMain;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling keybinding data.
 *
 * @author hockeyhurd
 * @version 12/29/2015.
 */
@SideOnly(Side.CLIENT)
public class KeyBindingHandler {

	private static final Minecraft minecraft = Minecraft.getMinecraft();

	private List<AbstractKeyBinding> keyBindingsList;

	public KeyBindingHandler() {
		keyBindingsList = new ArrayList<AbstractKeyBinding>();
	}

	public KeyBindingHandler(int numKeyBindings) {
		if (numKeyBindings < 0) numKeyBindings = -numKeyBindings;
		else if (numKeyBindings == 0) numKeyBindings = 10;

		keyBindingsList = new ArrayList<AbstractKeyBinding>(numKeyBindings);
	}

	public KeyBindingHandler(AbstractKeyBinding... keyBindings) {
		if (keyBindings != null && keyBindings.length > 0) {
			keyBindingsList = new ArrayList<AbstractKeyBinding>(keyBindings.length);

			for (AbstractKeyBinding binding : keyBindings) {
				if (binding != null) {
					keyBindingsList.add(binding);
					ClientRegistry.registerKeyBinding(binding);
					HCoreLibMain.lh.info("Key registered!");
				}
			}
		}

		else keyBindingsList = new ArrayList<AbstractKeyBinding>();
	}

	public void addKeyBinding(AbstractKeyBinding keyBinding) {
		if (keyBinding != null) keyBindingsList.add(keyBinding);
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (!minecraft.inGameHasFocus) return;

		HCoreLibMain.lh.info("KeyInputEvent called!");
		for (AbstractKeyBinding binding : keyBindingsList) {
			if (binding.isPressed()) binding.onKeyPressed(event);
		}
	}

}
