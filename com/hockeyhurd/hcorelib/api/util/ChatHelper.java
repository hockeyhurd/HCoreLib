package com.hockeyhurd.hcorelib.api.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

/**
 * @deprecated as of 4/27/16.  Code using this class should migrate to ChatUtils.
 *
 * @author hockeyhurd
 */
@Deprecated
public class ChatHelper {

	private EntityPlayer player;
	private TextFormatting color;
	
	// Constructor temporarily set to deprecated until player.sendChatMessage is implemented in this class.
	@Deprecated
	public ChatHelper(EntityPlayer player) {
		this.player = player;
		this.color = TextFormatting.WHITE;
	}
	
	public ChatHelper() {
		this.color = TextFormatting.WHITE;
	}
	
	public void setColor(TextFormatting color) {
		this.color = color;
	}
	
	private TextFormatting getColor() {
		return this.color;
	}
	
	public ITextComponent comp(String message) {
		return comp(message, this.color);
	}
	
	public ITextComponent comp(String message, TextFormatting color) {
		setColor(color);
		TextComponentTranslation comp = new TextComponentTranslation(message, new Object[0]);
		comp.getChatStyle().setColor(color);
		return comp;
	} 
	
	public ITextComponent compURL(String msg, String url, boolean maskUrl) {
		return compURL(msg, url, this.color, maskUrl);
	}
	
	public ITextComponent compURL(String msg, String url, TextFormatting color, boolean maskUrl) {
		setColor(color);
		TextComponentTranslation comp = new TextComponentTranslation(msg + " " + (maskUrl ? "<here>" : url), new Object[0]);
		comp.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
		comp.getChatStyle().setColor(color);
		return comp;
	}
	
}
