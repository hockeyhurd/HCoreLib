package com.hockeyhurd.hcorelib.mod;

import com.hockeyhurd.hcorelib.api.creativetab.AbstractCreativeTab;
import com.hockeyhurd.hcorelib.api.math.TimeLapse;
import com.hockeyhurd.hcorelib.api.util.LogHelper;
import com.hockeyhurd.hcorelib.api.util.McModInfoDataInjector;
import com.hockeyhurd.hcorelib.api.util.ModsLoadedHelper;
import com.hockeyhurd.hcorelib.api.util.SystemInfo;
import com.hockeyhurd.hcorelib.api.util.exceptions.InCompatibleJavaException.JavaCompatibility;
import com.hockeyhurd.hcorelib.api.util.interfaces.IForgeMod;
import com.hockeyhurd.hcorelib.mod.creativetab.ModCreativeTab;
import com.hockeyhurd.hcorelib.mod.handler.CommandHandler;
import com.hockeyhurd.hcorelib.mod.handler.config.ConfigHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.lang3.JavaVersion;

@Mod(modid = LibReference.MOD_ID, name = LibReference.MOD_NAME, version = LibReference.VERSION,
        acceptedMinecraftVersions = LibReference.MINECRAFT_VERSION,
        guiFactory = "com.hockeyhurd.hcorelib.mod.client.gui.HGuiFactory")
public final class HCoreLibMain implements IForgeMod {

    @SidedProxy(clientSide = "com.hockeyhurd.hcorelib.mod.ClientProxy", serverSide = "com.hockeyhurd.hcorelib.mod.CommonProxy")
    public static CommonProxy proxy;

    @Instance(LibReference.MOD_ID)
    public static HCoreLibMain instance;
    public static LogHelper logHelper;

    // Mod name in lowercase
    public static final String assetDir = LibReference.MOD_ID;
    private static TimeLapse tl;

    public static ConfigHandler configHandler;
    public static final AbstractCreativeTab myCreativeTab = new ModCreativeTab(CreativeTabs.getNextID(), "HCoreLib");

    @EventHandler
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        tl = new TimeLapse();
        logHelper = new LogHelper(LibReference.MOD_ID);

        final boolean result = JavaCompatibility.runCheck(JavaVersion.JAVA_1_8);
        if (result)
            logHelper.info("Java version is compatible!");

        logHelper.info("Pre-init started, looking for config info!");
        configHandler = new ConfigHandler(event, LibReference.MOD_ID);
        configHandler.handleConfiguration();
        logHelper.info("Config info handled successfully! Applying changes now!");

        final Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) {
            logHelper.info("Attempting to inject data to mcmod.info file!");

            final McModInfoDataInjector mcModInfoDataInjector = new McModInfoDataInjector(event, logHelper);
            mcModInfoDataInjector.injectData(false, new String[] { "hockeyhurd" }, LibReference.MOD_ID, LibReference.VERSION, LibReference.MOD_URL, null,
                    "A simple library for all of hockeyhurd's mods and anyone who wishes to use this as well.");

            if (mcModInfoDataInjector.getResult())
                logHelper.info("Injection was successful!");
            else
                logHelper.warn("Injection was un-successful! mcmod.info is a liar!");
        }

        // logHelper.info("Loading mod objects");
        // loadObj();
        // logHelper.info("Done!");

        /*logHelper.info("Initializing proxy");
        proxy.init();
        logHelper.info("Done!");*/

        logHelper.info("Pre-init finished successfully after", tl.getEffectiveTimeSince(), "ms!");
    }

    @EventHandler
    @Override
    public void init(FMLInitializationEvent event) {
        tl.resetStartTime();
        logHelper.info("Init started, looking for config info!");

        logHelper.info("Checking for loaded mods...");
        ModsLoadedHelper.getInstance().init();
        logHelper.info("... Complete! Here are the results", ModsLoadedHelper.getInstance().toString());

        logHelper.info("Initializing proxy");
        proxy.init();
        logHelper.info("Done!");

        // loadObj();
        logHelper.info("Registering render information");
        proxy.registerRenderInformation();
        logHelper.info("Done!");

        logHelper.info("Registering input handlers");
        proxy.registerInputHandlers();
        logHelper.info("Done!");

        logHelper.info("Init finished successfully after", tl.getEffectiveTimeSince(), "ms!");
    }

	/*private static void loadObj() {

		// Blocks:
		// white = new BlockWhiteHidden(Material.ROCK, "HiddenWhite");
		testBlock = new TestBlock();
		testTile = new TestTile();
		testTESRTile = new TestTESRTile();
		testFurnace = new TestFurnace();
		multiblockController = new BlockMultiblockController(Material.ROCK, "multiblockController");
		multiblockComponent = new BlockMultiblockComponent(Material.ROCK, "multiblockComponent");

		// Items:
		testItem = new TestItem();
		testMetaItem = new TestMetaItem("testMetaItem");
		itemCalculator = new ItemCalculator("itemCalculator");
		itemMeasureTape = new ItemMeasureTape("itemMeasureTape");
		buildersWand = new ItemBuildersWand("buildersWand");
		wrench = new ItemWrench("wrench");

		witchHat = new ItemWitchHat(EnumHelper.addArmorMaterial("witchHat", assetDir + ":" + ItemWitchHat.NAME, 100,
				new int[] { 1, 1, 1, 1}, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f), 0, EnumArmorType.HELMET,
				HCoreLibMain.assetDir);
	}*/

    @EventHandler
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        tl.resetStartTime();
        logHelper.info("Post-init started, looking for config info!");

        if (configHandler.allowUpdating()) {
            logHelper.info("Checking for updates!");
            proxy.registerUpdateHandler();
            if (!proxy.updateFlag)
                logHelper.warn("Found an update!");
            else
                logHelper.info("Everything is UP to date!");
        }

        else
            logHelper.warn("Skipping checking for updates. WARNING: bugs may exist!");

        logHelper.info("Post-init finished successfully after", tl.getEffectiveTimeSince(), "ms!");
    }

    @EventHandler
    @Override
    public void serverStartedEvent(FMLServerStartedEvent event) {
        SystemInfo.instance().setStartTime();
    }

    @EventHandler
    @Override
    public void serverStartingEvent(FMLServerStartingEvent event) {
        CommandHandler.instance().registerCommands(event);
    }

    public HCoreLibMain() {
    }

}
