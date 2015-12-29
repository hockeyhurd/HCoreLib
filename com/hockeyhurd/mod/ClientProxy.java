package com.hockeyhurd.mod;

import com.hockeyhurd.api.handler.input.KeyBindingHandler;
import com.hockeyhurd.api.handler.input.TestKeyBinding;
import com.hockeyhurd.api.math.Color4i;
import com.hockeyhurd.mod.block.renderer.WhiteBlockRenderer;
import com.hockeyhurd.mod.item.ItemRendererHiddenWhite;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int hiddenBlockRenderType;

	public ClientProxy() {
	}

	@Override
	public void registerRenderInformation() {
		registerSpecialRenderers();
	}

	@Override
	public void registerInputHandlers() {
		KeyBindingHandler handler = new KeyBindingHandler(new TestKeyBinding("Test", Keyboard.KEY_F4, LibReference.MOD_NAME));

		FMLCommonHandler.instance().bus().register(handler);
	}

	private void registerSpecialRenderers() {
		hiddenBlockRenderType = RenderingRegistry.getNextAvailableRenderId(); 
		RenderingRegistry.registerBlockHandler(new WhiteBlockRenderer(hiddenBlockRenderType, Blocks.gold_block.getBlockTextureFromSide(0), new Color4i(0, 127, 255)));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(HCoreLibMain.white), new ItemRendererHiddenWhite(HCoreLibMain.white.getBlockTextureFromSide(0)));
	}

}
