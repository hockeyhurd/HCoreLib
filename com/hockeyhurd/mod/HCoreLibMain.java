package com.hockeyhurd.mod;

import com.hockeyhurd.math.TimeLapse;
import com.hockeyhurd.util.LibReference;
import com.hockeyhurd.util.LogHelper;

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
	public static final String modID = LibReference.MOD_NAME;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("Pre-init started, looking for config info!");
		TimeLapse tl = new TimeLapse();
		
		LogHelper.info("Pre-init finished succesfully after", tl.getEffectiveTimeSince(), "ms!");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		LogHelper.info("Init started, looking for config info!");
		TimeLapse tl = new TimeLapse();
		
		LogHelper.info("Init finished succesfully after", tl.getEffectiveTimeSince(), "ms!");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Post-init started, looking for config info!");
		TimeLapse tl = new TimeLapse();
		
		LogHelper.info("Post-init finished succesfully after", tl.getEffectiveTimeSince(), "ms!");
	}
	
	public HCoreLibMain() {
	}

}
