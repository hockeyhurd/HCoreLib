package com.hockeyhurd.hcorelib.mod.client;

import com.hockeyhurd.hcorelib.api.client.util.ModelRegistry;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.LibReference;
import com.hockeyhurd.hcorelib.mod.common.ModRegistry;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author hockeyhurd
 * @version 12/23/2017.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = LibReference.MOD_ID)
public final class ModModelRegistry {

    @SubscribeEvent
    public static void registerAllModels(final ModelRegistryEvent event) {
        registerBlocks();
        registerItems();
    }

    private static void registerBlocks() {
        // ModelBakery.registerItemVariants(ItemUtils.getItem(HCoreLibMain.testBlock), HCoreLibMain.testBlock.getResourceLocation());
        // minecraft.getRenderItem().getItemModelMesher().register(ItemUtils.getItem(HCoreLibMain.testBlock), 0,
        //  		new ModelResourceLocation(HCoreLibMain.testBlock.getResourceLocation(), "inventory"));

        if (HCoreLibMain.configHandler.isDebugMode()) {
            ModelRegistry.registerBlock(ModRegistry.ModBlocks.testBlock.getBlock());
            ModelRegistry.registerBlock(ModRegistry.ModBlocks.testTile.getBlock());
            ModelRegistry.registerBlock(ModRegistry.ModBlocks.testTESRTile.getBlock());
            ModelRegistry.registerBlock(ModRegistry.ModBlocks.testFurnace.getBlock());
            ModelRegistry.registerBlock(ModRegistry.ModBlocks.multiblockController.getBlock());
            ModelRegistry.registerBlock(ModRegistry.ModBlocks.multiblockComponent.getBlock());
        }
    }

    private static void registerItems() {
        ModelRegistry.registerItem(ModRegistry.ModItems.itemCalculator.getItem());
        ModelRegistry.registerItem(ModRegistry.ModItems.itemMeasureTape.getItem());
        ModelRegistry.registerItem(ModRegistry.ModItems.buildersWand.getItem());
        ModelRegistry.registerItem(ModRegistry.ModItems.wrench.getItem());
        ModelRegistry.registerItem(ModRegistry.ModItems.witchHat.getItem());

        if (HCoreLibMain.configHandler.isDebugMode()) {
            ModelRegistry.registerItem(ModRegistry.ModItems.testItem.getItem());
            ModelRegistry.registerItem(ModRegistry.ModItems.testMetaItem.getItem());
        }
    }

}
