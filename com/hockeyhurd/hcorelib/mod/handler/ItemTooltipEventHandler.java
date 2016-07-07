package com.hockeyhurd.hcorelib.mod.handler;

import com.hockeyhurd.hcorelib.api.block.IBlockTooltip;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * @author hockeyhurd
 * @version 7/7/2016.
 */
@SideOnly(Side.CLIENT)
public final class ItemTooltipEventHandler {

	private static final String shiftInfoTag = TextFormatting.GRAY + "<" + TextFormatting.WHITE + TextFormatting.ITALIC + "Shift" +
			TextFormatting.RESET + TextFormatting.GRAY + ">";

	private static final String controlInfoTag = TextFormatting.GRAY + "<" + TextFormatting.WHITE + TextFormatting.ITALIC + "Ctrl" +
			TextFormatting.RESET + TextFormatting.GRAY + ">";

	private static final Minecraft minecraft = Minecraft.getMinecraft();
	private static final ItemTooltipEventHandler handler = new ItemTooltipEventHandler();

	private ItemTooltipEventHandler() {
	}

	public static ItemTooltipEventHandler getInstance() {
		return handler;
	}

	private static boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
	}

	private static boolean isControlKeyDown() {
		return Minecraft.IS_RUNNING_ON_MAC ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220) : Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
	}

	@SubscribeEvent
	public void onItemHover(ItemTooltipEvent event) {
		final ItemStack itemStack = event.getItemStack();
		if (itemStack == null) return;

		final Block block = BlockUtils.getBlockFromItem(itemStack.getItem());
		if (/*block == null ||*/ !(block instanceof IBlockTooltip)) return;

		final IBlockTooltip blockTooltip = (IBlockTooltip) block;
		final List<String> list = event.getToolTip();
		final boolean hasShiftInfo = blockTooltip.hasShiftInformation();
		final boolean hasControlInfo = blockTooltip.hasControlInformation();

		blockTooltip.addInformation(list, itemStack);

		if (hasShiftInfo) {
			list.add("");

			if (isShiftKeyDown()) blockTooltip.addShiftInformation(list, itemStack);
			else list.add(shiftInfoTag);
		}

		else list.add("");

		if (hasControlInfo) {
			if (isControlKeyDown()) blockTooltip.addControlInformation(list, itemStack);
			else list.add(controlInfoTag);
		}
	}

}
