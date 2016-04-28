package com.hockeyhurd.api.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Basis for all HCoreLib commands with potential expandability
 * in other mods through this class.
 *
 * @author hockeyhurd
 * @version 3/19/16
 */
public abstract class HCommand extends CommandBase {

    protected static final String BASE_NAME = "hclib";
    protected final String commandName;
    protected String[] commandArgs;

	/**
     * @param commandName Commands base. <italics>ex. "/<bold>hclib</bold> arg"</italics>.
     */
    public HCommand(String commandName) {
        this.commandName = commandName;
        init();
    }

	/**
     * Creates HCommand with base commandName.
     */
    public HCommand() {
        this.commandName = BASE_NAME;
        init();
    }

	/**
     * Init method for init anything that is necessary.
     */
    protected abstract void init();

	/**
     * Gets a single concatenated string from the args array.
     *
     * @return String.
     */
    protected String getConcatArgs() {
        if (commandArgs == null || commandArgs.length == 0) return "<empty>";
        else if (commandArgs.length == 1) return commandArgs[0];

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < commandArgs.length; i++) {
            builder.append(commandArgs[i]);
            if (i + 1 < commandArgs.length) builder.append(" | ");
        }

        return builder.toString();
    }

	/**
     * Gets a list of args starting with a given String.
     *
     * @param string String to reference.
     * @return List of args.
     */
    public List<String> getCommandArgsStartingWith(String string) {
        if (commandArgs == null || commandArgs.length == 0) return null;

        List<String> list = new ArrayList<String>(commandArgs.length);

        for (String str : commandArgs) {
            if (str.startsWith(string)) list.add(str);
        }

        return list;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return '/' + commandName + ' ' + getConcatArgs();
    }

    @Override
    public abstract void processCommand(ICommandSender sender, String[] args);

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

}
