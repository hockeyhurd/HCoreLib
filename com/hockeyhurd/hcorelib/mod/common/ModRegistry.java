package com.hockeyhurd.hcorelib.mod.common;

import com.hockeyhurd.hcorelib.api.block.IHBlock;
import com.hockeyhurd.hcorelib.api.item.IHItem;
import com.hockeyhurd.hcorelib.api.util.enums.EnumArmorType;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.LibReference;
import com.hockeyhurd.hcorelib.mod.block.TestBlock;
import com.hockeyhurd.hcorelib.mod.block.TestFurnace;
import com.hockeyhurd.hcorelib.mod.block.TestTESRTile;
import com.hockeyhurd.hcorelib.mod.block.TestTile;
import com.hockeyhurd.hcorelib.mod.block.multiblock.BlockMultiblockComponent;
import com.hockeyhurd.hcorelib.mod.block.multiblock.BlockMultiblockController;
import com.hockeyhurd.hcorelib.mod.item.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @author hockeyhurd
 * @version 12/19/2017.
 */
public final class ModRegistry {

    private ModRegistry() {
    }

    // Blocks:

    // @GameRegistry.ObjectHolder(HCoreLibMain.modID)
    public enum ModBlocks {
        // public static Block white;
        testBlock(new TestBlock(), false, true),

        testTile(new TestTile(), true, true),
        testTESRTile(new TestTESRTile(), true, true),
        testFurnace(new TestFurnace(), true, true),
        multiblockController(new BlockMultiblockController(Material.ROCK, "multiblockController"), true),
        multiblockComponent(new BlockMultiblockComponent(Material.ROCK, "multiblockComponent"), true);

        private IHBlock block;
        private boolean isTE;
        private boolean debugOnly;

        ModBlocks(IHBlock block, boolean isTE) {
            this(block, isTE, false);
        }

        ModBlocks(IHBlock block, boolean isTE, boolean debugOnly) {
            this.block = block;
            this.isTE = isTE;
            this.debugOnly = debugOnly;
        }

        public IHBlock getBlock() {
            return block;
        }

        public boolean isTE() {
            return isTE;
        }

        public boolean isDebugOnly() {
            return debugOnly;
        }

        public static void registerBlocks(final IForgeRegistry<Block> registry) {
            for (ModBlocks modBlock : values()) {

                if (!modBlock.isDebugOnly() || (modBlock.isDebugOnly() && HCoreLibMain.configHandler.isDebugMode()))
                    registry.register(modBlock.getBlock().getBlock());
            }
        }

        public static void registerItemBlocks(final IForgeRegistry<Item> registry) {
            for (ModBlocks modBlock : values()) {
                // final Block block = modBlock.getBlock().getBlock();
                if (!modBlock.isDebugOnly() || (modBlock.isDebugOnly() && HCoreLibMain.configHandler.isDebugMode())) {
                    final ResourceLocation registryName = modBlock.getBlock().getResourceLocation();

                    registry.register(modBlock.getBlock().getItemBlock().setRegistryName(registryName));
                }
            }
        }
    }

    // Items:

    // @GameRegistry.ObjectHolder(HCoreLibMain.modID)
    public enum ModItems {
        testItem(new TestItem()),
        testMetaItem(new TestMetaItem("testMetaItem")),
        itemCalculator(new ItemCalculator("itemCalculator")),
        itemMeasureTape(new ItemMeasureTape("itemMeasureTape")),
        buildersWand(new ItemBuildersWand("buildersWand")),
        wrench(new ItemWrench("wrench")),
        hammer(new ItemHammer("hammer")),

        witchHat(new ItemWitchHat(EnumHelper.addArmorMaterial("witchHat", HCoreLibMain.assetDir + ":" + ItemWitchHat.NAME, 100,
                new int[] { 1, 1, 1, 1 }, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f), 0, EnumArmorType.HELMET, HCoreLibMain.assetDir));

        private IHItem item;
        private boolean debugOnly;

        ModItems(IHItem item) {
            this(item, false);
        }

        ModItems(IHItem item, boolean debugOnly) {
            this.item = item;
            this.debugOnly = debugOnly;
        }

        public IHItem getItem() {
            return item;
        }

        public boolean isDebugOnly() {
            return debugOnly;
        }

        public static void register(final IForgeRegistry<Item> registry) {
            for (ModItems modItems : values()) {
                if (!modItems.isDebugOnly() || (modItems.isDebugOnly() && HCoreLibMain.configHandler.isDebugMode()))
                    registry.register(modItems.getItem().getItem());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = LibReference.MOD_ID)
    public static class RegistrationHandler {

        private RegistrationHandler() {
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            ModBlocks.registerBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            ModBlocks.registerItemBlocks(event.getRegistry());
            ModItems.register(event.getRegistry());
        }
    }

}
