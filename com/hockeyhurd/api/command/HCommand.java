package com.hockeyhurd.api.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

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

    public HCommand(String commandName) {
        this.commandName = commandName;
        init();
    }

    public HCommand() {
        this.commandName = BASE_NAME;
        init();
    }

    protected void init() {
        commandArgs = new String[] { "tps", "up-time" };
    }

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

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return '/' + commandName + getConcatArgs();
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
