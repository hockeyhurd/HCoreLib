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

	/** Store this instance of Minecraft statically. */
	private static final Minecraft minecraft = Minecraft.getMinecraft();

	/** Internal list of KeyBinds */
	private final List<AbstractKeyBinding> keyBindingsList;

	/**
	 * Creates an empty internal list of KeyBinds.
	 */
	public KeyBindingHandler() {
		keyBindingsList = new ArrayList<AbstractKeyBinding>();
	}

	/**
	 * Impractical but somewhat usable constructor with initial capacity of number of KeyBinds.
	 *
	 * @param numKeyBindings Number of initial KeyBinds.
	 */
	public KeyBindingHandler(int numKeyBindings) {
		if (numKeyBindings < 0) numKeyBindings = -numKeyBindings;
		else if (numKeyBindings == 0) numKeyBindings = 10;

		keyBindingsList = new ArrayList<AbstractKeyBinding>(numKeyBindings);
	}

	/**
	 * Constructs internal list with passed array of KeyBinds.
	 *
	 * @param keyBindings KeyBindings to add.
	 */
	public KeyBindingHandler(AbstractKeyBinding... keyBindings) {
		if (keyBindings != null && keyBindings.length > 0) {
			keyBindingsList = new ArrayList<AbstractKeyBinding>(keyBindings.length);

			for (AbstractKeyBinding binding : keyBindings) {
				if (binding != null) {
					keyBindingsList.add(binding);
					ClientRegistry.registerKeyBinding(binding);
					if (HCoreLibMain.configHandler.isDebugMode()) HCoreLibMain.logHelper.info("Key registered!");
				}
			}
		}

		else keyBindingsList = new ArrayList<AbstractKeyBinding>();
	}

	/**
	 * Adds KeyBind to internal KeyBind list.
	 *
	 * @param keyBinding KeyBind to add.
	 */
	public void addKeyBinding(AbstractKeyBinding keyBinding) {
		if (keyBinding != null) {
			keyBindingsList.add(keyBinding);
			if (HCoreLibMain.configHandler.isDebugMode()) HCoreLibMain.logHelper.info("Key registered!");
		}
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (!minecraft.inGameHasFocus || keyBindingsList.isEmpty()) return;

		for (AbstractKeyBinding binding : keyBindingsList) {
			if (binding.getIsKeyPressed()) binding.onKeyPressed(event);
		}
	}

}
