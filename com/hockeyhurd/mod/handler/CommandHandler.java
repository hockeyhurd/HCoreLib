package com.hockeyhurd.mod.handler;

import com.hockeyhurd.api.command.HCommand;
import com.hockeyhurd.api.math.TimeLapse;
import com.hockeyhurd.api.util.LogicHelper;
import com.hockeyhurd.mod.HCoreLibMain;
import com.hockeyhurd.mod.HServerCommands;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

import java.util.*;
import java.util.Map.Entry;

/**
 * Class to handle management of all ForgeCommands.
 *
 * @author hockeyhurd
 * @version 3/19/16
 */
public final class CommandHandler {

    private static final CommandHandler commandHandler = new CommandHandler();
    private final Map<String, HCommand> commandMap;
    private final TimeLapse timeLapse;
    private boolean regComplete;

    public final HServerCommands hServerCommands;

    private CommandHandler() {
        commandMap = new HashMap<String, HCommand>();
        regComplete = false;
        timeLapse = new TimeLapse();
        hServerCommands = new HServerCommands();
        addLocalCommands();
    }

	/**
	 * Gets the CommandHandler's static instance.
     *
     * @return CommandHandler.
     */
    public static CommandHandler instance() {
        return commandHandler;
    }

	/**
     * Adds localized commands to handler.
     */
    private void addLocalCommands() {
        addCommand(hServerCommands);
    }

	/**
     * Attempts to add a HCommand to the handler's internal mapping for registering.
     *
     * @param command HCommand to add.
     */
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

	/**
     * Method for handling main registering call from FMLServerStartingEvent.
     *
     * @see FMLServerStartingEvent
     *
     * @param event FMLServerStartingEvent.
     */
    public void registerCommands(FMLServerStartingEvent event) {
        timeLapse.resetStartTime();
        HCoreLibMain.logHelper.info("Registering commands...");

        if (event != null) {

            for (String commandName : commandMap.keySet()) {
                event.registerServerCommand(commandMap.get(commandName));
                HCoreLibMain.logHelper.info("Command:", commandName, "was registered!");
            }

            HCoreLibMain.logHelper.info("Finished registering commands successfully! (",
                    timeLapse.getEffectiveTimeSince(), "ms ).");
        }

        else HCoreLibMain.logHelper.severe("Error registering commands... FMLServerStartingEvent is null!");

        regComplete = true;
    }

	/**
     * Gets a list of args that start with a given String.
     *
     * @param string String to check.
     * @return Output list.
     */
    @SuppressWarnings("unchecked")
    public List<String> doesCommandStartWith(String string) {
        if (!LogicHelper.nullCheckString(string)) return null;

        final int size = Math.max(commandMap.size(), 0x10);

        List<String> list = new ArrayList<String>(size);

        Iterator iter = commandMap.entrySet().iterator();

        while (iter.hasNext()) {
            Entry<String, HCommand> entry = (Entry<String, HCommand>) iter.next();

            List<String> argsList = entry.getValue().getCommandArgsStartingWith(string);

            if (argsList != null && !argsList.isEmpty()) {
                for (String str : argsList) {
                    list.add(str);
                }
            }
        }

        return list;
    }

}
