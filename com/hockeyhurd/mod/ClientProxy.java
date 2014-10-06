package com.hockeyhurd.mod;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.hockeyhurd.api.renderer.Color4i;
import com.hockeyhurd.mod.block.renderer.WhiteBlockRenderer;
import com.hockeyhurd.mod.item.ItemRendererHiddenWhite;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int hiddenBlockRenderType;

	public ClientProxy() {
	}

	public void registerRenderInformation() {
		registerSpecialRenderers();
	}

	private void registerSpecialRenderers() {
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlowChest.class, new TileEntityGlowChestRenderer());
		// MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(glowChest), new ItemRendererGlowChest());
		
		// HCoreLibMain.white.getBlockTextureFromSide(0)
		// Blocks.diamond_block.getBlockTextureFromSide(0)
		hiddenBlockRenderType = RenderingRegistry.getNextAvailableRenderId(); 
		RenderingRegistry.registerBlockHandler(new WhiteBlockRenderer(hiddenBlockRenderType, Blocks.diamond_block.getBlockTextureFromSide(0), new Color4i(0, 127, 255)));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(HCoreLibMain.white), new ItemRendererHiddenWhite(HCoreLibMain.white.getBlockTextureFromSide(0)));
	}
	
}
