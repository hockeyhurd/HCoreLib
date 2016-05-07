package com.hockeyhurd.hcorelib.api.util.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IToolToggle extends com.hockeyhurd.hcorelib.api.util.interfaces.IKeyBound {
	void writeToNBT(ItemStack stack, boolean value);
	boolean readValueFromNBT(ItemStack stack);
	void doKeyBindingAction(EntityPlayer player, ItemStack stack, int key);
}
