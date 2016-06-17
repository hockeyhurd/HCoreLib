package com.hockeyhurd.hcorelib.api.util;

import com.hockeyhurd.hcorelib.api.command.HCommand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

import java.io.File;

/**
 * Largely static utility class for creating message to be
 * used in the in-game chat system.
 *
 * @author hockeyhurd
 * @version 4/27/16
 */
public final class ChatUtils {

    // private static final EnumChatFormatting DEFAULT_COLOR = EnumChatFormatting.WHITE;
    public static final String DEFAULT_URL_MASK = "<here>";

    private ChatUtils() {
    }

    /**
     * Creates a generic chat component.
     *
     * @param separateMessages boolean flag to separate by commas and space.
     * @param messages Messages.
     * @return IChatComponent.
     */
    public static ITextComponent createComponent(boolean separateMessages, String... messages) {
        if (messages == null || messages.length == 0)
            return createComponent(false, TextFormatting.WHITE + "<empty>");

        StringBuilder builder = new StringBuilder(Math.max(0x20, messages.length << 1));

        for (int i = 0; i < messages.length; i++) {
            builder.append(messages[i]);
            if (i + 1 < messages.length) {
                if (separateMessages) builder.append(", ");
                else builder.append(' ');
            }
        }

        return createTranslation(builder.toString());
    }

    /**
     * Creates a generic chat component.
     *
     * @param messages Messages.
     * @return IChatComponent.
     */
    public static ITextComponent createComponent(String... messages) {
        return createComponent(false, messages);
    }

    /**
     * Creates a url chat component.
     *
     * @param separateMessages boolean flag to separate by commas and space.
     * @param url String url to use.
     * @param maskURL String url mask.
     * @param messages Messages.
     * @return IChatComponent.
     */
    public static ITextComponent createURLComponent(boolean separateMessages, String url, String maskURL, String... messages) {
        if (!StringUtils.nullCheckString(url) || !StringUtils.contains(url, '.'))
            throw new NullPointerException("URL doesn't exist!");

        ITextComponent comp = createComponent(separateMessages, messages);
        comp.appendText(" " + (StringUtils.nullCheckString(maskURL) ? maskURL : url));

        comp.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));

        return comp;
    }

    /**
     * Creates a url chat component.
     *
     * @param url String url to use.
     * @param messages Messages.
     * @return IChatComponent.
     */
    public static ITextComponent createURLComponent(String url, String... messages) {
        return createURLComponent(false, url, null, messages);
    }

    /**
     * Creates a command chat component.
     *
     * @param separateMessages boolean flag to separate by commas and space.
     * @param command HCommand to use.
     * @param messages Messages.
     * @return IChatComponent.
     */
    public static ITextComponent createCmdComponent(boolean separateMessages, HCommand command, String... messages) {
        if (command == null)
            return createComponent(false, "<Invalid command>");

        ITextComponent comp = createComponent(separateMessages, messages);

        comp.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                command.getCommandUsage(null)));

        return comp;
    }

    /**
     * Creates a file chat component.
     *
     * @param separateMessages boolean flag to separate by commas and space.
     * @param file File to open.
     * @param messages Messages.
     * @return IChatComponent.
     */
    public static ITextComponent createFileComponent(boolean separateMessages, File file, String... messages) {
        if (file == null || !file.exists())
            return createComponent(false, "<missing file>");

        ITextComponent comp = createComponent(separateMessages, messages);

        comp.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file.getAbsolutePath()));

        return comp;
    }

    /**
     * Private factory function to create chat translation.
     *
     * @param message Message.
     * @return ChatComponentTranslation.
     */
    private static ITextComponent createTranslation(String message) {
        return new TextComponentTranslation(message);
    }

    /**
     * Private factory function to create chat translation.
     *
     * @param message Message.
     * @param objects Object[].
     * @return ChatComponentTranslation.
     */
    private static ITextComponent createTranslation(String message, Object... objects) {
        return new TextComponentTranslation(message, objects);
    }

}
