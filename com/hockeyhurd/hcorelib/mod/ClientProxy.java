package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.client.util.ModelRegistry;
import com.hockeyhurd.hcorelib.api.handler.config.ConfigChangedEventHandler;
import com.hockeyhurd.hcorelib.mod.client.renderer.TESRTileRenderer;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTESRTest;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int hiddenBlockRenderType;

	public ClientProxy() {
	}

	@Override
	protected void registerBlocks() {
		super.registerBlocks();

		// ModelBakery.registerItemVariants(ItemUtils.getItem(HCoreLibMain.testBlock), HCoreLibMain.testBlock.getResourceLocation());
		// minecraft.getRenderItem().getItemModelMesher().register(ItemUtils.getItem(HCoreLibMain.testBlock), 0,
		//  		new ModelResourceLocation(HCoreLibMain.testBlock.getResourceLocation(), "inventory"));
		ModelRegistry.registerBlock(HCoreLibMain.testBlock);
		ModelRegistry.registerBlock(HCoreLibMain.testTile);
		ModelRegistry.registerBlock(HCoreLibMain.testTESRTile);
	}

	@Override
	protected void registerItems() {
		super.registerItems();

		ModelRegistry.registerItem(HCoreLibMain.testItem);
		ModelRegistry.registerItem(HCoreLibMain.testMetaItem);
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

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTESRTest.class, new TESRTileRenderer());
	}

	@Override
	public boolean isClient() {
		return true;
	}

}
