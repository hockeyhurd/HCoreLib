package com.hockeyhurd.hcorelib.api.handler;

import com.hockeyhurd.hcorelib.api.util.AbstractReference;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import static com.hockeyhurd.hcorelib.api.util.ChatUtils.*;
import static net.minecraft.util.text.TextFormatting.*;

/**
 * Simple class for notifying player when they join the game about your mod's update!
 * To make use of this class, create a new instance of this class with the parameters
 * of your update handler's instance, a configCodeMap of retrieved data (call myUpdateHandlerInstance.check(), followed by myUpdateHandlerInstance.getMap()),
 * yourReferenceClass.class provided it extends AabstractReference class and you followed its directions,
 * followed lastly by a generic boolean that you got from calling myUpdateHandlerInstance.getUpToDate().
 * NOTE: All of this instantiating should be handled in your CommonProxy class!
 * 
 * @author hockeyhurd
 * @link <a href = "https://github.com/hockeyhurd/HCoreLib/blob/6fc59183096290a10d060f1f428cbea68ea1185b/com/hockeyhurd/api/handler/NotifyPlayerOnJoinHandler.java">github.com/.../NotifyPlayerOnJoinHandler.java</a>
 */
public class NotifyPlayerOnJoinHandler {

	private UpdateHandler instance;
	private HashMap<String, String> map;
	private boolean updateFlag;
	private boolean maskUrl;
	private boolean allowUpdateCheck;
	private String name;
	
	/**
	 * Default constructor for instantiating your mod's update notifier.
	 *
	 * @param instance your created instance of UpdateHandler.
	 * @param map configCodeMap created by calling myUpdateHandlerInstance.check() followed by myUpdateHandlerInstance.getMap().
	 * @param modName String mod name.
	 * @param updateFlag your created stored updateFlag boolean var obtained by calling myUpdateHandlerInstance.getUpToDate().
	 * @param maskUrl Whether you wish to hide the url in the chat, NOTE: doesn't change what the user sees when prompted to open.
	 */
	public NotifyPlayerOnJoinHandler(UpdateHandler instance, HashMap<String, String> map, String modName, boolean updateFlag, boolean maskUrl, boolean allowUpdateCheck) {
		this.instance = instance;
		this.map = map;
		this.updateFlag = updateFlag;
		this.maskUrl = maskUrl;
		this.allowUpdateCheck = allowUpdateCheck;

		this.name = modName;
	}

	/**
	 * Method called when player join's the world, whether client or server based.
	 *
	 * @param event event call.
	 */
	// @SuppressWarnings("unchecked")
	@SubscribeEvent
	public void onPlayerJoin(EntityJoinWorldEvent event) {
		if (!(event.getEntity() instanceof EntityPlayerMP) || !allowUpdateCheck) return;
		else {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
			if (!updateFlag) {
				String build = "";
				String updateUrl = "";

				String changelogUrl = "";
				
				Iterator<Entry<String, String>> iter = map.entrySet().iterator();
				// Grabbing the first index of entry's keys and values and store this data. 'update/build numer'
				if (iter.hasNext()) {
					Entry<String, String> current = iter.next();
					build = current.getKey();
					updateUrl = current.getValue();
				}

				// Output info to joining player.
				player.addChatComponentMessage(createComponent(GREEN + "[" + name + "] " +
						GRAY + "Found an update! Latest build: " + build));
				player.addChatComponentMessage(createURLComponent(false, updateUrl, DEFAULT_URL_MASK,
						GRAY + "You can get this at:" + WHITE));

				// grab changelog info.
				String[] changelogArray = instance.getChangelogInfo();
				if (changelogArray != null && changelogArray.length > 0) {
					player.addChatComponentMessage(createComponent(GREEN + "Change log:"));

					for (String s : changelogArray) {
						if (s != null) {
							player.addChatComponentMessage(createComponent(WHITE + s));
						}
					}
				}

			}
		}
	}

}
