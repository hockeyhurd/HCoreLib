package com.hockeyhurd.hcorelib.mod.command;

import com.hockeyhurd.hcorelib.api.command.HCommand;
import com.hockeyhurd.hcorelib.api.math.expressions.Expression;
import com.hockeyhurd.hcorelib.api.math.expressions.Interpreter;
import com.hockeyhurd.hcorelib.api.math.expressions.InterpreterResult;
import com.hockeyhurd.hcorelib.api.util.ChatUtils;
import com.hockeyhurd.hcorelib.api.util.NumberParser;
import com.hockeyhurd.hcorelib.api.util.SystemInfo;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.handler.CommandHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.util.List;

/**
 * Server commands for HCoreLib.
 *
 * @author hockeyhurd
 * @version 3/23/2016.
 */
public final class HServerCommands extends HCommand {

	public static final String MOD_ABREV = "hclib";
	private static final String SERVER_TAG = "SERVER";

	@Override
	protected void init() {
        commandArgs = new String[] { "tps", "uptime", "hcalc", "mem" };
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		if (args.length == 0)
			sender.addChatMessage(ChatUtils.createComponent(getCommandUsage(sender)));
		else if (args[0].equalsIgnoreCase(commandArgs[0])) {
			if (args.length == 1 || args[1].equals(SERVER_TAG)) {
				if (/*HCoreLibMain.proxy.isClient() &&*/ !(sender instanceof MinecraftServer)) {
					// server.getCommandManager().executeCommand(sender, HCoreLibMain.modID + " " + commandArgs[0]);
					server.getCommandManager().executeCommand(server, MOD_ABREV + ' ' + commandArgs[0] + ' ' + SERVER_TAG + ' ' + sender.getName());
				}

				else {
					HCoreLibMain.logHelper.info(args);
					EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(args[2]);

					if (player == null) {
						HCoreLibMain.logHelper.severe("Error player not found!");
						return;
					}

					List<String> textToSend = SystemInfo.instance().getServerTPSSummary(server);

					for (String str : textToSend) {
						// sender.addChatMessage(ChatUtils.createComponent(str));
						player.addChatComponentMessage(ChatUtils.createComponent(str));
					}
				}
			}

			else if (args.length == 2) {
				final int dim = NumberParser.parseInt(args[1]);

				String[] textToSend = SystemInfo.instance().getTPSDetails(dim);

				for (String str : textToSend) {
					sender.addChatMessage(ChatUtils.createComponent(str));
				}
			}

			else sender.addChatMessage(ChatUtils.createComponent(getCommandUsage(sender)));
		}

		else if (args[0].equalsIgnoreCase(commandArgs[1])) {
			if (args.length == 1 || args[1].equals(SERVER_TAG)) {
				if (/*HCoreLibMain.proxy.isClient() &&*/ !(sender instanceof MinecraftServer)) {
					// sender.addChatMessage(ChatUtils.createComponent(SystemInfo.instance().getSystemUpTime()));
					server.getCommandManager().executeCommand(server, MOD_ABREV + ' ' + commandArgs[1] + ' ' + SERVER_TAG + ' ' + sender.getName());
				}

				else {
					EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(args[2]);

					if (player == null) {
						HCoreLibMain.logHelper.severe("Error player not found!");
						return;
					}

					player.addChatComponentMessage(ChatUtils.createComponent(SystemInfo.instance().getSystemUpTime()));
				}
			}

			else sender.addChatMessage(ChatUtils.createComponent(getCommandUsage(sender)));
		}

		else if (args[0].equalsIgnoreCase(commandArgs[2])) {
			if (args.length == 1)
				sender.addChatMessage(ChatUtils.createComponent(getCommandUsage(sender)));
			else {
				StringBuilder builder = new StringBuilder(0x20);

				for (int i = 1; i < args.length; i++)
					builder.append(args[i]);

				Interpreter interpreter = new Interpreter();
				InterpreterResult result = interpreter.processExpressionString(new Expression(builder.toString()));

				sender.addChatMessage(ChatUtils.createComponent("Result:", result.getExpressionString()));
			}
		}

		else if (args[0].equalsIgnoreCase(commandArgs[3])) {
			if (args.length == 1 || args[1].equals(SERVER_TAG)) {
				if (/*HCoreLibMain.proxy.isClient() &&*/ !(sender instanceof MinecraftServer)) {
					server.getCommandManager().executeCommand(server, MOD_ABREV + ' ' + commandArgs[3] + ' ' + SERVER_TAG + ' ' + sender.getName());
				}

				else {
					EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(args[2]);

					if (player == null) {
						HCoreLibMain.logHelper.severe("Error player not found!");
						return;
					}

					String[] output = SystemInfo.instance().getMemoryDetails();

					for (String str : output)
						player.addChatComponentMessage(ChatUtils.createComponent(str));
				}
			}

			else sender.addChatMessage(ChatUtils.createComponent(getCommandUsage(sender)));
		}

		else sender.addChatMessage(ChatUtils.createComponent(getCommandUsage(sender)));
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] words, BlockPos pos) {
		if (words == null || words.length == 0) return null;

		if (words.length == 1) return CommandHandler.instance().doesCommandStartWith(words[0]);

		return null;
	}

}
