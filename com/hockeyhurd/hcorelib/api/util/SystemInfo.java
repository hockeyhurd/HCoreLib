package com.hockeyhurd.hcorelib.api.util;

import com.hockeyhurd.hcorelib.api.math.TimeLapse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for getting relevant system data.
 *
 * @author hockeyhurd
 * @version 3/21/16
 */
public final class SystemInfo {

    private static final SystemInfo systemInfo = new SystemInfo();
    private static final Runtime runtime = Runtime.getRuntime();

    private final TimeLapse timeLapse;

    private SystemInfo() {
        timeLapse = new TimeLapse();
    }

	/**
     * Gets the static SystemInfo instance.
     *
     * @return SystemInfo static instance.
     */
    public static SystemInfo instance() {
        return systemInfo;
    }

	/**
     * Sets the new starting time.
     */
    public void setStartTime() {
        timeLapse.resetStartTime();
    }

	/**
     * Gets the server OS Name.
     *
     * @return String.
     */
    public String getOSName() {
        return System.getProperty("os.name");
    }

    /**
     * Gets the server OS Version.
     *
     * @return String.
     */
    public String getOSVersion() {
        return System.getProperty("os.version");
    }

    /**
     * Gets the server OS Architecture.
     *
     * @return String.
     */
    public String getOSArchitecture() {
        return System.getProperty("os.arch");
    }

    /**
     * Gets the server OS Total Memory.
     *
     * @return String.
     */
    public String getTotalMemory() {
        return NumberFormatter.bytesAsString(runtime.totalMemory());
    }

    /**
     * Gets the server Used Memory.
     *
     * @return String.
     */
    public String getUsedMemory() {
        return NumberFormatter.bytesAsString(runtime.totalMemory() - runtime.freeMemory());
    }

    /**
     * Gets the server Allocated Memory.
     *
     * @return String.
     */
    public String getAllocatedMemory() {
        return NumberFormatter.bytesAsString(runtime.totalMemory());
    }

    /**
     * Gets the server Max Memory.
     *
     * @return String.
     */
    public String getMaxMemory() {
        return NumberFormatter.bytesAsString(runtime.maxMemory());
    }

    /**
     * Gets the server Percentage of Memory Used.
     *
     * @return String.
     */
    public String getPercentageMemoryUsed() {
        return NumberFormatter.format((float) runtime.totalMemory() / (float) runtime.maxMemory() * 100.0f);
    }

    /**
     * Gets the server OS Name.
     *
     * @return String.
     */
    public String getSystemUpTime() {
        return NumberFormatter.millisecondsAsString(Math.round(timeLapse.getEffectiveTimeSince()));
    }

	/**
     * Gets the world tps for given world.
     *
     * @param world World to reference.
     * @return double.
     */
    public double getWorldTPS(WorldServer world) {
        long sum = 0;
        long counter = 0;

        for (long l : MinecraftServer.getServer().worldTickTimes.get(world.provider.dimensionId)) {
            sum += l;
            counter++;
        }

        final double avgTickLen = ((double) sum / (double) counter * 1e-6);
        return Math.min(1000.0d / avgTickLen, 20.0d);
    }

	/**
     * Gets the world tick time for given world.
     *
     * @param world World to reference.
     * @return double.
     */
    public double getWorldTickTime(WorldServer world) {
        long sum = 0L;
        int counter = 0;

        for (long l : MinecraftServer.getServer().worldTickTimes.get(world.provider.dimensionId)) {
            sum += l;
            counter++;
        }

        return (double) sum / (double) counter * 1e-6;
    }

	/**
     * Gets a list of data for the running server.
     *
     * @return List< String />.
     */
    public List<String> getServerTPSSummary() {
        final WorldServer[] worldServers = MinecraftServer.getServer().worldServers;
        final List<String> list = new ArrayList<String>(3 + worldServers.length);
        int chunksLoaded = 0;

        int dimID;
        String dimName;

        for (WorldServer world : worldServers) {
            chunksLoaded += world.getChunkProvider().getLoadedChunkCount();

            dimID = world.provider.dimensionId;
            dimName = world.provider.getDimensionName();

            list.add("[" + dimID + "]" + dimName + ": " + NumberFormatter.formatTime(getWorldTickTime(world)) +
                    "ms [" + NumberFormatter.formatTime(getWorldTPS(world)) + "]");
        }

        list.add("# of Chunks Currently Loaded: " + chunksLoaded);

        long sum = 0L;
        int counter = 0;

        for (long l : MinecraftServer.getServer().tickTimeArray) {
            sum += l;
            counter++;
        }

        final double avgTickLen = (double) sum / (double) counter * 1e-6;

        list.add("Overall: " + NumberFormatter.formatTime(avgTickLen) + "ms [" +
                Math.min(1000.0d / avgTickLen, 20) + ']');

        return list;
    }

	/**
     * Gets the world tps details from a provided dimensional ID.
     *
     * @param dimension Dimensional ID.
     * @return String[] containing relevant data.
     */
    @SuppressWarnings("unchecked")
    public String[] getTPSDetails(final int dimension) {
        List<String> list = new ArrayList<String>(11);

        WorldServer world = MinecraftServer.getServer().worldServerForDimension(dimension);
        List<Entity> entities = world.getLoadedEntityList();

        list.add("Uptime: " + NumberFormatter.millisecondsAsString((long) timeLapse.getEffectiveTimeSince()));
        list.add("Dimension [" + dimension + ']' + world.provider.getDimensionName());
        list.add("Players online: (" + world.playerEntities.size() + "): " + getPlayersForDimension(dimension));
        list.add("Item entity count: " + getAmountOfItemEntities(entities));
        list.add("Hostile entities: " + getAmountOfHostileEntity(entities));
        list.add("Friendly entities: " + getAmountOfPassiveEntity(entities));
        list.add("Living entities: " + getAmountOfLivingEntity(entities));
        list.add("Total entities: " + entities.size());
        list.add("Chunks loaded: " + world.getChunkProvider().getLoadedChunkCount());
        list.add("TPS: " + NumberFormatter.millisecondsAsString((long) getWorldTickTime(world)) + "ms[" + getWorldTPS(world) + ']');

        return list.toArray(new String[list.size()]);
    }

	/**
     * Counts the number of Item Entities in the current world.
     *
     * @param list List of entities.
     * @return int number of Item Entities in the current world.
     */
    private static int getAmountOfItemEntities(List<Entity> list) {
        int count = 0;

        for (Entity entity : list) {
            if (entity instanceof EntityItem) count++;
        }

        return count;
    }

    /**
     * Counts the number of Passive Entities in the current world.
     *
     * @param list List of entities.
     * @return int number of Passive Entities in the current world.
     */
    private static int getAmountOfPassiveEntity(List<Entity> list) {
        int count = 0;

        for (Entity entity : list) {
            if (entity instanceof EntityAnimal) count++;
        }

        return count;
    }

    /**
     * Counts the number of Hostile Entities in the current world.
     *
     * @param list List of entities.
     * @return int number of Hostile Entities in the current world.
     */
    private static int getAmountOfHostileEntity(List<Entity> list) {
        int count = 0;

        for (Entity entity : list) {
            if (entity instanceof EntityMob) count++;
        }

        return count;
    }

    /**
     * Counts the number of Living Entities in the current world.
     *
     * @param list List of entities.
     * @return int number of Living Entities in the current world.
     */
    private static int getAmountOfLivingEntity(List<Entity> list) {
        int count = 0;

        for (Entity entity : list) {
            if (entity instanceof EntityLiving) count++;
        }

        return count;
    }

	/**
     * Gets all players in a dimension and combines into a single String
     * representation.
     *
     * @param dimension int dimensional ID to check.
     * @return String of player names in world if applicable else returns "Empty".
     */
    @SuppressWarnings("unchecked")
    private static String getPlayersForDimension(final int dimension) {
        List<EntityPlayer> players = (ArrayList<EntityPlayer>) MinecraftServer.getServer().worldServerForDimension(dimension).playerEntities;

        if (players.isEmpty()) return "<Empty>";

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < players.size(); i++) {
            builder.append(players.get(i).getCommandSenderName());

            if (i + 1 < players.size()) builder.append(", ");
            else builder.append('.');
        }

        return builder.toString();
    }

}
