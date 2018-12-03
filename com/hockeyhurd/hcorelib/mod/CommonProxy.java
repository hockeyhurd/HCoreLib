package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.handler.NotifyPlayerOnJoinHandler;
import com.hockeyhurd.hcorelib.api.handler.RecipeGen;
import com.hockeyhurd.hcorelib.api.handler.RecipePattern;
import com.hockeyhurd.hcorelib.api.handler.UpdateHandler;
import com.hockeyhurd.hcorelib.api.util.interfaces.ICraftableRecipe;
import com.hockeyhurd.hcorelib.api.util.interfaces.IProxy;
import com.hockeyhurd.hcorelib.mod.common.ModRegistry;
import com.hockeyhurd.hcorelib.mod.handler.GuiHandler;
import com.hockeyhurd.hcorelib.mod.handler.packet.PacketHandler;
import com.hockeyhurd.hcorelib.mod.tileentity.TileAndGate;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityRegistry;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTESRTest;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTest;
import com.hockeyhurd.hcorelib.mod.tileentity.TileFurnace;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockComponent;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.HashMap;

public class CommonProxy implements IProxy {

    protected UpdateHandler updateHandler;
    protected HashMap<String, String> updateMap;
    protected static GuiHandler guiHandler;
    protected RecipeGen recipeGen;
    public boolean updateFlag;

    public CommonProxy() {
        recipeGen = new RecipeGen(LibReference.MOD_ID);
    }

    @Override
    public void registerRenderInformation() {
    }

    @Override
    public void registerInputHandlers() {
    }

    @Override
    public void preInit() {
        // registerBlocks(); // un-comment to regen crafting recipes to recipe output folder.
        // registerItems();  // un-comment to regen crafting recipes to recipe output folder.
        registerTileEntities();
    }

    @Override
    public void init() {
        registerGuiHandler();
        registerEventHandlers();
        registerInputHandlers();

        HCoreLibMain.logHelper.info("Registering render information");
        registerRenderInformation();
        HCoreLibMain.logHelper.info("Done!");

        HCoreLibMain.logHelper.info("Registering input handlers");
        registerInputHandlers();
        HCoreLibMain.logHelper.info("Done!");
    }

    @Override
    public void postInit() {
        registerUpdateHandler();

        /*final VersionGen versionGen = new VersionGen(LibReference.MOD_ID);
        final VersionGen.VersionData latestVersion = new VersionGen.VersionData(LibReference.MAJOR_VERSION, LibReference.SUB_VERSION, LibReference.BUILD,
                "https://github.com/hockeyhurd/HCoreLib/releases/1.3.2", "First build for 1.12.2!");

        final VersionGen.VersionData recommendedVersion = new VersionGen.VersionData(LibReference.MAJOR_VERSION, LibReference.SUB_VERSION, LibReference.BUILD + 1,
                "https://github.com/hockeyhurd/HCoreLib/releases/1.3.1", "Fake build");

        versionGen.generateVersion(latestVersion, recommendedVersion, LibReference.HOMEPAGE_URL);*/
    }

    protected void registerBlocks() {
        if (!HCoreLibMain.configHandler.isDebugMode())
            return;

        for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModBlocks.testBlock.getBlock()).getRecipePatterns()) {
            if (pattern != null)
                pattern.registerRecipe(recipeGen);
        }
    }

    protected void registerTileEntities() {
        if (!HCoreLibMain.configHandler.isDebugMode())
            return;

        final TileEntityRegistry inst = TileEntityRegistry.getInstance();

        inst.registerTileEntity(TileEntityTest.class, "testTile");
        inst.registerTileEntity(TileEntityTESRTest.class, "testTESRTile");
        inst.registerTileEntity(TileFurnace.class, "testFurnaceTile");
        inst.registerTileEntity(MultiblockController.class, "multiblockController");
        inst.registerTileEntity(MultiblockComponent.class, "multiblockComponent");
        inst.registerTileEntity(TileAndGate.class, "gateAnd");

        // GameRegistry.registerTileEntity(TileEntityTest.class, "testTile");
        // GameRegistry.registerTileEntity(TileEntityTESRTest.class, "testTESRTile");
        // GameRegistry.registerTileEntity(TileFurnace.class, "testFurnaceTile");
        // GameRegistry.registerTileEntity(MultiblockController.class, "multiblockController");
        // GameRegistry.registerTileEntity(MultiblockComponent.class, "multiblockComponent");
    }

    protected void registerItems() {
        for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.itemCalculator.getItem()).getRecipePatterns()) {
            if (pattern != null)
                pattern.registerRecipe(recipeGen);
        }

        for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.itemMeasureTape.getItem()).getRecipePatterns()) {
            if (pattern != null)
                pattern.registerRecipe(recipeGen);
        }

        for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.wrench.getItem()).getRecipePatterns()) {
            if (pattern != null)
                pattern.registerRecipe(recipeGen);
        }

        for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.hammer.getItem()).getRecipePatterns()) {
            if (pattern != null)
                pattern.registerRecipe(recipeGen);
        }

        for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.witchHat.getItem()).getRecipePatterns()) {
            if (pattern != null)
                pattern.registerRecipe(recipeGen);
        }

        if (HCoreLibMain.configHandler.isDebugMode()) {
            for (RecipePattern pattern : ((ICraftableRecipe) ModRegistry.ModItems.buildersWand.getItem()).getRecipePatterns()) {
                if (pattern != null)
                    pattern.registerRecipe(recipeGen);
            }
        }
    }

    protected void registerGuiHandler() {
        if (guiHandler != null)
            NetworkRegistry.INSTANCE.registerGuiHandler(HCoreLibMain.instance, guiHandler);
        else {
            guiHandler = new GuiHandler();
            NetworkRegistry.INSTANCE.registerGuiHandler(HCoreLibMain.instance, guiHandler);
        }
    }

    @Override
    public void registerUpdateHandler() {
        updateHandler = new UpdateHandler(LibReference.MOD_NAME, LibReference.VERSION, LibReference.MOD_URL, LibReference.CHANGELOG_URL);

        if (HCoreLibMain.configHandler.allowUpdating()) {
            HCoreLibMain.logHelper.info("Checking for updates!");

            updateHandler.check();
            updateMap = updateHandler.getMap();
            updateFlag = updateHandler.getUpToDate();

            if (!HCoreLibMain.proxy.updateFlag)
                HCoreLibMain.logHelper.warn("Found an update!");
            else
                HCoreLibMain.logHelper.info("Everything is UP to date!");
        }

        else
            HCoreLibMain.logHelper.warn("Skipping checking for updates. WARNING: bugs may exist!");

        MinecraftForge.EVENT_BUS.register(new NotifyPlayerOnJoinHandler(updateHandler, updateMap, LibReference.MOD_NAME, updateFlag, true,
                HCoreLibMain.configHandler.allowUpdating()));
    }

    @Override
    public void registerEventHandlers() {
        PacketHandler.getInstance().init();
        MinecraftForge.EVENT_BUS.register(PacketHandler.getInstance());
    }

    @Override
    public boolean isClient() {
        return false;
    }

}
