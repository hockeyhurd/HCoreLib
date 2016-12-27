package com.hockeyhurd.hcorelib.api.handler.tooltip;

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
 * Item tooltip event handler.
 *
 * @author hockeyhurd
 * @version 7/7/2016.
 */
@SideOnly(Side.CLIENT)
public final class ItemTooltipEventHandler {

	private static final String shiftInfoTag = TextFormatting.WHITE + "Hold " + TextFormatting.GRAY + "<" + TextFormatting.WHITE +
			TextFormatting.ITALIC + "Shift" + TextFormatting.RESET + TextFormatting.GRAY + "> " + TextFormatting.WHITE + "for details";

	private static final String controlInfoTag = TextFormatting.WHITE + "Hold " + TextFormatting.GRAY + "<" + TextFormatting.WHITE +
			TextFormatting.ITALIC + "Ctrl" + TextFormatting.RESET + TextFormatting.GRAY + "> " + TextFormatting.WHITE + "for details";

	private static final ItemTooltipEventHandler handler = new ItemTooltipEventHandler();

	private ItemTooltipEventHandler() {
	}

	/**
	 * Gets the static instance.
	 * @return Class static instance.
	 */
	public static ItemTooltipEventHandler getInstance() {
		return handler;
	}

	/**
	 * Inserts an empty line into tooltip list.
	 *
	 * @param list String list of tooltip info.
	 */
	public static void insertEmptyLine(List<String> list) {
		if (list != null) list.add("");
	}

	/**
	 * Gets if the shift key on client side is held.
	 *
	 * @return boolean result.
	 */
	private static boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
	}

	/**
	 * Gets if the ctrl key on client side is held.
	 *
	 * @return boolean result.
	 */
	private static boolean isControlKeyDown() {
		return !Minecraft.IS_RUNNING_ON_MAC ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220) : Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
	}

	@SubscribeEvent
	public void onItemHover(ItemTooltipEvent event) {
		final ItemStack itemStack = event.getItemStack();
		if (itemStack == null) return;

		ITooltip<?> tooltip;

		final Block block = BlockUtils.getBlockFromItem(itemStack.getItem());
		if (block instanceof IBlockTooltip) tooltip = (IBlockTooltip) block;
		else if (itemStack.getItem() instanceof ITooltip<?>) tooltip = (ITooltip<?>) itemStack.getItem();
		else return;

		// ITooltip<?> tooltip = (IBlockTooltip) block;
		// final ITooltip<?> tooltip = (IBlockTooltip) itemStack.getItem();
		final List<String> list = event.getToolTip();
		final boolean hasShiftInfo = tooltip.hasShiftInformation();
		final boolean hasControlInfo = tooltip.hasControlInformation();

		tooltip.addInformation(list, itemStack);

		if (hasShiftInfo) {
			list.add("");

			if (isShiftKeyDown()) tooltip.addShiftInformation(list, itemStack);
			else list.add(shiftInfoTag);
		}

		if (hasControlInfo) {
			if (!hasShiftInfo) list.add("");
			if (isControlKeyDown()) tooltip.addControlInformation(list, itemStack);
			else list.add(controlInfoTag);
		}
	}

}
