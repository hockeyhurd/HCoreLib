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
        testBlock(new TestBlock(), false),

        testTile(new TestTile(), true),
        testTESRTile(new TestTESRTile(), true),
        testFurnace(new TestFurnace(), true),
        multiblockController(new BlockMultiblockController(Material.ROCK, "multiblockController"), true),
        multiblockComponent(new BlockMultiblockComponent(Material.ROCK, "multiblockComponent"), true);

        private IHBlock block;
        private boolean isTE;

        ModBlocks(IHBlock block, boolean isTE) {
            this.block = block;
            this.isTE = isTE;
        }

        public IHBlock getBlock() {
            return block;
        }

        public boolean isTE() {
            return isTE;
        }

        public static void registerBlocks(final IForgeRegistry<Block> registry) {
            for (ModBlocks modBlock : values()) {
                registry.register(modBlock.getBlock().getBlock());

                /*if (modBlock.isTE()) {
                    GameRegistry.registerTileEntity(((TileEntity) modBlock.getBlock().getTileEntity()).getClass(),
                            modBlock.getBlock().getName());
                }*/
            }
        }

        public static void registerItemBlocks(final IForgeRegistry<Item> registry) {
            for (ModBlocks modBlock : values()) {
                // final Block block = modBlock.getBlock().getBlock();
                final ResourceLocation registryName = modBlock.getBlock().getResourceLocation();

                registry.register(modBlock.getBlock().getItemBlock().setRegistryName(registryName));
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
        forgingHammer(new ItemHammer("forgingHammer")),

        witchHat(new ItemWitchHat(EnumHelper.addArmorMaterial("witchHat", HCoreLibMain.assetDir + ":" + ItemWitchHat.NAME, 100,
                new int[] { 1, 1, 1, 1 }, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f), 0, EnumArmorType.HELMET, HCoreLibMain.assetDir));

        private IHItem item;

        ModItems(IHItem item) {
            this.item = item;
        }

        public IHItem getItem() {
            return item;
        }

        public static void register(final IForgeRegistry<Item> registry) {
            for (ModItems modItems : values()) {
                // final ResourceLocation registryName = modItems.getItem().getResourceLocation(0);

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
