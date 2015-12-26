package com.hockeyhurd.mod;

import com.hockeyhurd.api.handler.RenderWorldHandler;
import com.hockeyhurd.api.math.Color4i;
import com.hockeyhurd.api.math.Vector3;
import com.hockeyhurd.api.renderer.EnumShape;
import com.hockeyhurd.api.renderer.ShapeRenderer;
import com.hockeyhurd.mod.block.renderer.WhiteBlockRenderer;
import com.hockeyhurd.mod.item.ItemRendererHiddenWhite;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

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

		MinecraftForge.EVENT_BUS.register(RenderWorldHandler.instance());
		RenderWorldHandler.instance().addRenderer(new ShapeRenderer(EnumShape.RECT, null, new Vector3<Integer>(), new Vector3<Integer>(2, 2, 2), new Color4i(0xffff0000)));
	}
	
}
