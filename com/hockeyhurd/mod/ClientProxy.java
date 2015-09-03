package com.hockeyhurd.mod;

import com.hockeyhurd.api.math.Color4i;
import com.hockeyhurd.mod.block.renderer.WhiteBlockRenderer;
import com.hockeyhurd.mod.item.ItemRendererHiddenWhite;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int hiddenBlockRenderType;

	public ClientProxy() {
	}

	public void registerRenderInformation() {
		registerSpecialRenderers();
	}

	private void registerSpecialRenderers() {
		hiddenBlockRenderType = RenderingRegistry.getNextAvailableRenderId(); 
		RenderingRegistry.registerBlockHandler(new WhiteBlockRenderer(hiddenBlockRenderType, Blocks.gold_block.getBlockTextureFromSide(0), new Color4i(0, 127, 255)));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(HCoreLibMain.white), new ItemRendererHiddenWhite(HCoreLibMain.white.getBlockTextureFromSide(0)));
	}
	
}
