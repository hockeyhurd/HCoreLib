package com.hockeyhurd.mod.handler;

import com.hockeyhurd.api.command.HCommand;
import com.hockeyhurd.api.math.TimeLapse;
import com.hockeyhurd.mod.HCoreLibMain;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to handle management of all ForgeCommands.
 *
 * @author hockeyhurd
 * @version 3/19/16
 */
public class CommandHandler {

    private static final CommandHandler commandHandler = new CommandHandler();
    private final Map<String, HCommand> commandMap;
    private boolean regComplete;

    private CommandHandler() {
        commandMap = new HashMap<String, HCommand>();
        regComplete = false;
    }

    public static CommandHandler instance() {
        return commandHandler;
    }

    public void addCommand(HCommand command) {
        if (regComplete) {
            HCoreLibMain.logHelper.severe("Error! Registration has already completed... aborting addCommand!");
            return;
        }

        if (command != null && !commandMap.containsKey(command.getCommandName())) {
            commandMap.put(command.getCommandName(), command);

            if (HCoreLibMain.configHandler.isDebugMode())
                HCoreLibMain.logHelper.info("Command:", command.getCommandName(), "was added to internal mapping!");
        }
    }

    public void registerCommands(FMLServerStartingEvent event) {
        TimeLapse timeLapse = new TimeLapse();
        HCoreLibMain.logHelper.info("Registering commands...");

        if (event != null) {

            if (!commandMap.isEmpty()) {
                for (String commandName : commandMap.keySet()) {
                    event.registerServerCommand(commandMap.get(commandName));
                    HCoreLibMain.logHelper.info("Command:", commandName, "was registered!");
                }
            }

            HCoreLibMain.logHelper.info("Finished registering commands successfully! (",
                    timeLapse.getEffectiveTimeSince(), "ms ).");
        }

        else HCoreLibMain.logHelper.severe("Error registering commands... FMLServerStartingEvent is null!");

        regComplete = true;
    }

}
