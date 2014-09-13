package com.hockeyhurd.util;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ItemHelper {

	private World world;
	private EntityPlayer player;

	public ItemHelper(World world, EntityPlayer player) {
		this.world = world;
		this.player = player;
	}

	// Only use this constructor is there is no need with world or player interaction!
	public ItemHelper() {

	}

	public Item getItem(int id) {
		return (Item) Item.itemRegistry.getObjectById(id);
	}

	public String getUnlocalizedName(Item item) {
		return item != null ? item.getUnlocalizedName() : "This is not an item!";
	}

	public boolean itemListContains(int id) {
		Item item_ = getItem(id);
		
		// Checks if the given id is > the last registered item and if so, just return false;
		if (item_ != null) return false;
		Item item = null;

		Iterator iter = Item.itemRegistry.iterator();
		while (iter.hasNext()) {
			if (iter.next() instanceof Item) item = (Item) iter.next();
			if (item == item_) break;
		}

		return item != null ? true : false;
	}

	public boolean isAnItem(Item item) {
		return item != null && Item.itemRegistry.containsKey(item) ? true : false;
	}

}
