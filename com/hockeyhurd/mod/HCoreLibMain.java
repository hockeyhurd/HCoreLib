package com.hockeyhurd.mod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import com.hockeyhurd.api.creativetab.ModCreativeTab;
import com.hockeyhurd.api.math.TimeLapse;
import com.hockeyhurd.api.util.LogHelper;
import com.hockeyhurd.mod.block.BlockWhiteHidden;
import com.hockeyhurd.mod.handler.ConfigHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LibReference.MOD_NAME, name = LibReference.MOD_NAME, version = LibReference.VERSION)
public class HCoreLibMain {

	@SidedProxy(clientSide = "com.hockeyhurd.mod.ClientProxy", serverSide = "com.hockeyhurd.mod.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(LibReference.MOD_NAME)
	public static HCoreLibMain instance;
	public static LogHelper lh;
	public static final String modID = LibReference.MOD_NAME;
	public static final String assetDir = "hcorelib:";
	
	public static ConfigHandler configHandler;
	public static CreativeTabs myCreativeTab = new ModCreativeTab(CreativeTabs.getNextID(), "HCoreLib");
	
	public static Block white;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		TimeLapse tl = new TimeLapse();
		lh = new LogHelper(LibReference.class);
		
		lh.info("Pre-init started, looking for config info!");
		configHandler = new ConfigHandler(event, LibReference.class);
		configHandler.handleConfiguration();
		lh.info("Config info handled successfully! Applying changes now!");
		
		lh.info("Pre-init finished succesfully after", tl.getEffectiveTimeSince(), "ms!");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		TimeLapse tl = new TimeLapse();
		lh.info("Init started, looking for config info!");

		loadObj();
		proxy.init();
		
		lh.info("Init finished succesfully after", tl.getEffectiveTimeSince(), "ms!");
	}
	
	private void loadObj() {
		white = new BlockWhiteHidden(Material.rock, "HiddenWhite");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		TimeLapse tl = new TimeLapse();
		lh.info("Post-init started, looking for config info!");
		
		if (configHandler.allowUpdating()) {
			lh.info("Checking for updates!");
			proxy.registerUpdateHandler();
			if (!proxy.updateFlag) lh.warn("Found an update!");
			else lh.info("Everything is up to date!");
		}
		
		else lh.warn("Skipping checking for updates. WARNING: bugs may exist!");
		
		lh.info("Post-init finished succesfully after", tl.getEffectiveTimeSince(), "ms!");
	}
	
	public HCoreLibMain() {
	}

}
