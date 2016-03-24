package com.hockeyhurd.mod;

import com.hockeyhurd.api.command.HCommand;
import com.hockeyhurd.api.util.NumberParser;
import com.hockeyhurd.api.util.SystemInfo;
import com.hockeyhurd.mod.handler.CommandHandler;
import net.minecraft.command.ICommandSender;

import java.util.List;

/**
 * Server commands for HCoreLib.
 *
 * @author hockeyhurd
 * @version 3/23/2016.
 */
public final class HServerCommands extends HCommand {

	@Override
	protected void init() {
        commandArgs = new String[] { "tps", "uptime" };
    }

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length == 0) sender.addChatMessage(chatHelper.comp(getCommandUsage(sender)));
		else if (args[0].equalsIgnoreCase(commandArgs[0])) {
			if (args.length == 1) {
				List<String> textToSend = SystemInfo.instance().getServerTPSSummary();

				for (String str : textToSend) {
					sender.addChatMessage(chatHelper.comp(str));
				}
			}

			else if (args.length == 2) {
				final int dim = NumberParser.parseInt(args[1]);

				String[] textToSend = SystemInfo.instance().getTPSDetails(dim);

				for (String str : textToSend) {
					sender.addChatMessage(chatHelper.comp(str));
				}
			}

			else sender.addChatMessage(chatHelper.comp(getCommandUsage(sender)));
		}

		else if (args[0].equalsIgnoreCase(commandArgs[1])) {
			if (args.length == 1) sender.addChatMessage(chatHelper.comp(SystemInfo.instance().getSystemUpTime()));
			else sender.addChatMessage(chatHelper.comp(getCommandUsage(sender)));
		}

		else sender.addChatMessage(chatHelper.comp(getCommandUsage(sender)));
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] words) {
		if (words == null || words.length == 0) return null;

		if (words.length == 1) return CommandHandler.instance().doesCommandStartWith(words[0]);

		return null;
	}

}
