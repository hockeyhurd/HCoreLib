package com.hockeyhurd.hcorelib.api.util.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound {
	void doKeyBinding(EntityPlayer player, ItemStack stack, int key);
}
