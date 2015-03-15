package com.hockeyhurd.api.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayerMP;
import static net.minecraft.util.EnumChatFormatting.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.hockeyhurd.api.util.AbstractReference;
import com.hockeyhurd.api.util.ChatHelper;
import com.hockeyhurd.mod.HCoreLibMain;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Simple class for notifying player when they join the game about your mod's update!
 * To make use of this class, create a new instance of this class with the parameters
 * of your update handler's instance, a map of retrieved data (call myUpdateHandlerInstance.check(), followed by myUpdateHandlerInstance.getMap()),
 * yourReferenceClass.class provided it extends AabstractReference class and you followed its directions,
 * followed lastly by a generic boolean that you got from calling myUpdateHandlerInstance.getUpToDate().
 * NOTE: All of this instantiating should be handled in your CommonProxy class!
 * 
 * @author hockeyhurd
 * @link <a href = "https://github.com/hockeyhurd/HCoreLib/blob/6fc59183096290a10d060f1f428cbea68ea1185b/com/hockeyhurd/api/handler/NotifyPlayerOnJoinHandler.java">github.com/.../NotifyPlayerOnJoinHandler.java</a>
 */
public class NotifyPlayerOnJoinHandler {

	private UpdateHandler instance;
	private HashMap<Short, String> map;
	private boolean updateFlag;
	private boolean maskUrl;
	private boolean allowUpdateCheck;
	private String name;
	
	/**
	 * Default constructor for instantiating your mod's update notifier.
	 * @param instance = your created instance of updatehandler
	 * @param map = map created by calling myUpdateHandlerInstance.check() followed by myUpdateHandlerInstance.getMap().
	 * @param referenceClass = a reference to your created ReferenceClass.class that extends AbstractReference class and followed its directions.
	 * @param updateFlag = your created stored updateFlag boolean var obtained by calling myUpdateHandlerInstance.getUpToDate().
	 * @param maskUrl = Whether you wish to hide the url in the chat, NOTE: doesn't change what the user sees when prompted to open.
	 */
	public NotifyPlayerOnJoinHandler(UpdateHandler instance, HashMap<Short, String> map, Class<? extends AbstractReference> referenceClass, boolean updateFlag, boolean maskUrl, boolean allowUpdateCheck) {
		this.instance = instance;
		this.map = map;
		this.updateFlag = updateFlag;
		this.maskUrl = maskUrl;
		this.allowUpdateCheck = allowUpdateCheck;
		
		try {
			this.name = referenceClass.getDeclaredField("MOD_NAME").get(referenceClass).toString();
		}
		catch (Exception e) {
			System.err.println("Could not find mod's name! Please fix this mod dev!");
		}
	}

	/**
	 * Method called when player join's the world, whether client or server based.
	 * @param event = event call.
	 */
	@SubscribeEvent
	public void onPlayerJoin(EntityJoinWorldEvent event) {
		if (!(event.entity instanceof EntityPlayerMP) || !allowUpdateCheck) return;
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
				player.addChatComponentMessage(helper.comp(GREEN + "[" + name + "] " + GRAY + "Found an update! Latest build: " + build));
				player.addChatComponentMessage(helper.compURL(GRAY + "You can get this at:" + WHITE, url, this.maskUrl));
			}
		}
	}

}
