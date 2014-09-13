package com.hockeyhurd.util.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IToolToggle extends IKeyBound {
	public void writeToNBT(ItemStack stack, boolean value);
	public boolean readValueFromNBT(ItemStack stack);
	public abstract void doKeyBindingAction(EntityPlayer player, ItemStack stack, int key);
}
