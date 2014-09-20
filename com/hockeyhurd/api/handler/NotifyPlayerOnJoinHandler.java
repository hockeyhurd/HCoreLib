package com.hockeyhurd.api.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.hockeyhurd.api.util.AbstractReference;
import com.hockeyhurd.api.util.ChatHelper;
import com.hockeyhurd.mod.HCoreLibMain;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class NotifyPlayerOnJoinHandler {

	private UpdateHandler instance;
	private HashMap<Short, String> map;
	private boolean updateFlag;
	private String name;
	
	public NotifyPlayerOnJoinHandler(UpdateHandler instance, HashMap<Short, String> map, Class<? extends AbstractReference> referenceClass, boolean updateFlag) {
		this.instance = instance;
		this.map = map;
		this.updateFlag = updateFlag;
		
		try {
			this.name = referenceClass.getDeclaredField("MOD_NAME").get(referenceClass).toString();
		}
		catch (Exception e) {
			HCoreLibMain.lh.severe("Could not find mod's name! Please fix this mod dev!");
		}
	}
	
	@SubscribeEvent
	public void onPlayerJoin(EntityJoinWorldEvent event) {
		if (!(event.entity instanceof EntityPlayerMP)) return;
		else {
			EntityPlayerMP player = (EntityPlayerMP) event.entity;
			if (!updateFlag) {
				short build = -1;
				String url = "";
				
				Iterator iter = map.entrySet().iterator();
				// Grabbing the first index of entry's keys and values and store this data.
				while (iter.hasNext()) {
					Entry<Short, String> current = (Entry<Short, String>) iter.next();
					build = current.getKey();
					url = current.getValue();
					break;
				}
				
				// Output info to joining player.
				ChatHelper helper = new ChatHelper();
				player.addChatComponentMessage(helper.comp("[" + name + "] Found an update! Latest build: " + build));
				player.addChatComponentMessage(helper.compURL("You can get this at:", url));
				// player.addChatComponentMessage(helper.compURL("You can get this at:", "http://goo.gl/nYTUfU"));
			}
		}
	}

}
