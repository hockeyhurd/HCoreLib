package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.handler.client.DrawBlockSelectionHandler;
import com.hockeyhurd.hcorelib.api.handler.config.ConfigChangedEventHandler;
import com.hockeyhurd.hcorelib.api.handler.tooltip.ItemTooltipEventHandler;
import com.hockeyhurd.hcorelib.mod.client.renderer.TESRTileRenderer;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTESRTest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int hiddenBlockRenderType;

	public ClientProxy() {
	}

	@Override
	protected void registerMCForgeEventHandlers() {
		super.registerMCForgeEventHandlers();
	}

	@Override
	public void registerRenderInformation() {
		registerSpecialRenderers();
	}

	@Override
	public void registerInputHandlers() {
		// Below is an example of how to create, initialize, and register a KeyInputHandler from this API.
		// KeyBindingHandler handler = new KeyBindingHandler(new TestKeyBinding("Test", Keyboard.KEY_F4, LibReference.MOD_NAME));
		// FMLCommonHandler.instance().bus().register(handler);
		FMLCommonHandler.instance().bus().register(new ConfigChangedEventHandler(LibReference.MOD_ID, HCoreLibMain.configHandler));
	}

	private void registerSpecialRenderers() {
		MinecraftForge.EVENT_BUS.register(new DrawBlockSelectionHandler());

		if (HCoreLibMain.configHandler.isDebugMode()) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTESRTest.class, new TESRTileRenderer());
		}
	}

	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();

		MinecraftForge.EVENT_BUS.register(ItemTooltipEventHandler.getInstance());
	}

	@Override
	public boolean isClient() {
		return true;
	}

	public static EntityPlayer getPlayer() {
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

}
