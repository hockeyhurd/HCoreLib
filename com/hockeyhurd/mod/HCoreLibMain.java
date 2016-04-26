package com.hockeyhurd.mod;

import com.hockeyhurd.api.math.TimeLapse;
import com.hockeyhurd.api.util.LogHelper;
import com.hockeyhurd.api.util.McModInfoDataInjector;
import com.hockeyhurd.api.util.SystemInfo;
import com.hockeyhurd.api.util.interfaces.IForgeMod;
import com.hockeyhurd.mod.block.BlockWhiteHidden;
import com.hockeyhurd.mod.creativetab.ModCreativeTab;
import com.hockeyhurd.mod.handler.CommandHandler;
import com.hockeyhurd.mod.handler.config.ConfigHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = LibReference.MOD_NAME, name = LibReference.MOD_NAME, version = LibReference.VERSION,
		guiFactory = "com.hockeyhurd.mod.client.gui.HGuiFactory")
public final class HCoreLibMain implements IForgeMod {

	@SidedProxy(clientSide = "com.hockeyhurd.mod.ClientProxy", serverSide = "com.hockeyhurd.mod.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(LibReference.MOD_NAME)
	public static HCoreLibMain instance;
	public static LogHelper logHelper;
	public static final String modID = LibReference.MOD_NAME;
	public static final String assetDir = modID.toLowerCase() + ':';
	private TimeLapse tl;
	
	public static ConfigHandler configHandler;
	public static CreativeTabs myCreativeTab = new ModCreativeTab(CreativeTabs.getNextID(), "HCoreLib");
	
	public static Block white;
	
	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		tl = new TimeLapse();
		logHelper = new LogHelper(LibReference.class);

		logHelper.info("Pre-init started, looking for config info!");
		configHandler = new ConfigHandler(event, LibReference.class);
		configHandler.handleConfiguration();
		logHelper.info("Config info handled successfully! Applying changes now!");

		final Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.CLIENT) {
			logHelper.info("Attempting to inject data to mcmod.info file!");

			final McModInfoDataInjector mcModInfoDataInjector = new McModInfoDataInjector(event, logHelper);
			mcModInfoDataInjector.injectData(false, new String[] { "hockeyhurd" }, modID, LibReference.VERSION, LibReference.MOD_URL, null,
					"A simple library for all of hockeyhurd's mods and anyone who wishes to use this as well.");

			if (mcModInfoDataInjector.getResult()) logHelper.info("Injection was successful!");
			else logHelper.warn("Injection was un-successful! mcmod.info is a liar!");
		}

		logHelper.info("Pre-init finished successfully after", tl.getEffectiveTimeSince(), "ms!");
	}
	
	@EventHandler
	@Override
	public void init(FMLInitializationEvent event) {
		tl.resetStartTime();
		logHelper.info("Init started, looking for config info!");

		loadObj();
		proxy.init();
		proxy.registerRenderInformation();
		proxy.registerInputHandlers();

		logHelper.info("Init finished successfully after", tl.getEffectiveTimeSince(), "ms!");
	}
	
	private void loadObj() {
		white = new BlockWhiteHidden(Material.rock, "HiddenWhite");
	}
	
	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		tl.resetStartTime();
		logHelper.info("Post-init started, looking for config info!");
		
		if (configHandler.allowUpdating()) {
			logHelper.info("Checking for updates!");
			proxy.registerUpdateHandler();
			if (!proxy.updateFlag) logHelper.warn("Found an update!");
			else logHelper.info("Everything is UP to date!");
		}
		
		else logHelper.warn("Skipping checking for updates. WARNING: bugs may exist!");
		
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
