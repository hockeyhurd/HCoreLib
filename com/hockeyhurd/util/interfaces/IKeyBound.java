package com.hockeyhurd.util.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound {
	public abstract void doKeyBinding(EntityPlayer player, ItemStack stack, int key);
}
