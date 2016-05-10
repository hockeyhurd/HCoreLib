package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.handler.config.ConfigChangedEventHandler;
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
		FMLCommonHandler.instance().bus().register(new ConfigChangedEventHandler(HCoreLibMain.modID, HCoreLibMain.configHandler));
	}

	private void registerSpecialRenderers() {
		// hiddenBlockRenderType = RenderingRegistry.getNextAvailableRenderId();
		// RenderingRegistry.registerBlockHandler(new WhiteBlockRenderer(hiddenBlockRenderType, Blocks.gold_block.getBlockTextureFromSide(0), new Color4i(0, 127, 255)));
		// MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(HCoreLibMain.white), new ItemRendererHiddenWhite(HCoreLibMain.white.getBlockTextureFromSide(0)));
	}

	@Override
	public boolean isClient() {
		return true;
	}

}