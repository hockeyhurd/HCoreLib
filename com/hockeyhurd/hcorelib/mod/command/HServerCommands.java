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
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

import javax.annotation.Nullable;
import java.util.*;

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
        commandArgs = new String[] { "tps", "uptime", "hcalc", "mem", "killall" };
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		if (args.length == 0)
			sender.sendMessage(ChatUtils.createComponent(getUsage(sender)));
		else if (args[0].equalsIgnoreCase(commandArgs[0])) {
			if (args.length == 1 || args[1].equals(SERVER_TAG)) {
				if (/*HCoreLibMain.proxy.isClient() &&*/ !(sender instanceof MinecraftServer)) {
					// server.getCommandManager().executeCommand(sender, HCoreLibMain.modID + " " + commandArgs[0]);
					server.getCommandManager().executeCommand(server, MOD_ABREV + ' ' + commandArgs[0] + ' ' + SERVER_TAG + ' ' + sender.getName());
				}

				else {
					HCoreLibMain.logHelper.info(args);
					EntityPlayerMP player = args.length == 3 ? server.getPlayerList().getPlayerByUsername(args[2]) : null;

					List<String> textToSend = SystemInfo.instance().getServerTPSSummary(server);

					if (player != null) {
						for (String str : textToSend) {
							player.sendMessage(ChatUtils.createComponent(str));
						}
					}

					else {
						for (String str : textToSend) {
							server.sendMessage(ChatUtils.createComponent(str));
						}
					}
				}
			}

			else if (args.length == 2) {
				final int dim = NumberParser.parseInt(args[1]);

				String[] textToSend = SystemInfo.instance().getTPSDetails(dim);

				for (String str : textToSend) {
					sender.sendMessage(ChatUtils.createComponent(str));
				}
			}

			else sender.sendMessage(ChatUtils.createComponent(getUsage(sender)));
		}

		else if (args[0].equalsIgnoreCase(commandArgs[1])) {
			if (args.length == 1 || args[1].equals(SERVER_TAG)) {
				if (/*HCoreLibMain.proxy.isClient() &&*/ !(sender instanceof MinecraftServer)) {
					// sender.addChatMessage(ChatUtils.createComponent(SystemInfo.instance().getSystemUpTime()));
					server.getCommandManager().executeCommand(server, MOD_ABREV + ' ' + commandArgs[1] + ' ' + SERVER_TAG + ' ' + sender.getName());
				}

				else {
					EntityPlayerMP player = args.length == 3 ? server.getPlayerList().getPlayerByUsername(args[2]) : null;

					if (player != null) {
						player.sendMessage(ChatUtils.createComponent(SystemInfo.instance().getSystemUpTime()));
					}

					else {
						server.sendMessage(ChatUtils.createComponent(SystemInfo.instance().getSystemUpTime()));
					}
				}
			}

			else sender.sendMessage(ChatUtils.createComponent(getUsage(sender)));
		}

		else if (args[0].equalsIgnoreCase(commandArgs[2])) {
			if (args.length == 1)
				sender.sendMessage(ChatUtils.createComponent(getUsage(sender)));
			else {
				StringBuilder builder = new StringBuilder(0x20);

				for (int i = 1; i < args.length; i++)
					builder.append(args[i]);

				Interpreter interpreter = new Interpreter();
				InterpreterResult result = interpreter.processExpressionString(new Expression(builder.toString()), 0);

				sender.sendMessage(ChatUtils.createComponent("Result:", result.getExpressionString()));
			}
		}

		else if (args[0].equalsIgnoreCase(commandArgs[3])) {
			if (args.length == 1 || args[1].equals(SERVER_TAG)) {
				if (/*HCoreLibMain.proxy.isClient() &&*/ !(sender instanceof MinecraftServer)) {
					server.getCommandManager().executeCommand(server, MOD_ABREV + ' ' + commandArgs[3] + ' ' + SERVER_TAG + ' ' + sender.getName());
				}

				else {
					EntityPlayerMP player = args.length == 3 ? server.getPlayerList().getPlayerByUsername(args[2]) : null;

					String[] output = SystemInfo.instance().getMemoryDetails();

					if (player != null) {
						for (String str : output)
							player.sendMessage(ChatUtils.createComponent(str));
					}

					else {
						for (String str : output)
							server.sendMessage(ChatUtils.createComponent(str));
					}

				}
			}

			else sender.sendMessage(ChatUtils.createComponent(getUsage(sender)));
		}

		else if (args[0].equalsIgnoreCase(commandArgs[4])) {
			if (args.length == 1 || args[1].equals(SERVER_TAG)) {
				if (/*HCoreLibMain.proxy.isClient() &&*/ !(sender instanceof MinecraftServer)) {
					server.getCommandManager().executeCommand(server, MOD_ABREV + ' ' + commandArgs[4] + ' ' + SERVER_TAG + ' ' + sender.getName());
				}

				else {

                    final List<EntityMob> removeList = new ArrayList<EntityMob>(0x40);

                    for (WorldServer worldServer : DimensionManager.getWorlds()) {
                        final List<Entity> loadedEntityList = worldServer.loadedEntityList;

                        for (Entity entity : loadedEntityList) {
                            if (entity instanceof EntityMob)
                                removeList.add((EntityMob) entity);
                        }
                    }

                    if (!removeList.isEmpty()) {
                        final Map<String, Integer> killMap = new TreeMap<String, Integer>();

                        for (EntityMob mob : removeList) {
                            if (killMap.containsKey(mob.getName())) {
                                killMap.put(mob.getName(), killMap.get(mob.getName()) + 1);
                            }

                            else {
                                killMap.put(mob.getName(), 1);
                            }

                            mob.setDead();
                        }

                        final StringBuilder outputString = new StringBuilder();
                        outputString.append("Killed: ");

                        for (Map.Entry<String, Integer> entry : killMap.entrySet()) {
                            outputString.append(entry.getKey());
                            outputString.append('x');
                            outputString.append(entry.getValue());
                            outputString.append(' ');
                        }

                        final EntityPlayerMP player = args.length == 3 ? server.getPlayerList().getPlayerByUsername(args[2]) : null;

                        if (player == null)
                            sender.sendMessage(ChatUtils.createComponent(outputString.toString()));
                        else
                            player.sendMessage(ChatUtils.createComponent(outputString.toString()));
                    }

                    else {
                        final EntityPlayerMP player = args.length == 3 ? server.getPlayerList().getPlayerByUsername(args[2]) : null;

                        if (player == null)
                            sender.sendMessage(ChatUtils.createComponent("No mobs to kill"));
                        else
                            player.sendMessage(ChatUtils.createComponent("No mobs to kill"));
                    }
				}
			}

			else sender.sendMessage(ChatUtils.createComponent(getUsage(sender)));
		}

		else sender.sendMessage(ChatUtils.createComponent(getUsage(sender)));
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] words, @Nullable BlockPos pos) {
		if (words == null || words.length == 0)
			return Collections.<String>emptyList();

		if (words.length == 1)
			return CommandHandler.instance().doesCommandStartWith(words[0]);

		return Collections.<String>emptyList();
	}

}
