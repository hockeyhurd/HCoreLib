package com.hockeyhurd.api.util;

import com.hockeyhurd.api.math.TimeLapse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
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

    public static SystemInfo instance() {
        return systemInfo;
    }

    public void setStartTime() {
        timeLapse.resetStartTime();
    }

    public String getOSName() {
        return System.getProperty("os.name");
    }

    public String getOSVersion() {
        return System.getProperty("os.version");
    }

    public String getOSArchitecture() {
        return System.getProperty("os.arch");
    }

    public String getTotalMemory() {
        return NumberFormatter.bytesAsString(runtime.totalMemory());
    }

    public String getUsedMemory() {
        return NumberFormatter.bytesAsString(runtime.totalMemory() - runtime.freeMemory());
    }

    public String getAllocatedMemory() {
        return NumberFormatter.bytesAsString(runtime.totalMemory());
    }

    public String getMaxMemory() {
        return NumberFormatter.bytesAsString(runtime.maxMemory());
    }

    public String getPercentageMemoryUsed() {
        return NumberFormatter.format((float) runtime.totalMemory() / (float) runtime.maxMemory() * 100.0f);
    }

    public String getSystemUpTime() {
        return NumberFormatter.millisecondsAsString(Math.round(timeLapse.getEffectiveTimeSince()));
    }

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

    public double getWorldTickTime(WorldServer world) {
        long sum = 0L;
        int counter = 0;

        for (long l : MinecraftServer.getServer().worldTickTimes.get(world.provider.dimensionId)) {
            sum += l;
            counter++;
        }

        return (double) sum / (double) counter * 1e-6;
    }

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

    private static int getAmountOfItemEntities(List<Entity> list) {
        int count = 0;

        for (Entity entity : list) {
            if (entity instanceof EntityItem) count++;
        }

        return count;
    }

    private static int getAmountOfPassiveEntity(List<Entity> list) {
        int count = 0;

        for (Entity entity : list) {
            if (entity instanceof EntityAnimal) count++;
        }

        return count;
    }

    private static int getAmountOfHostileEntity(List<Entity> list) {
        int count = 0;

        for (Entity entity : list) {
            if (entity instanceof EntityMob) count++;
        }

        return count;
    }

    private static int getAmountOfLivingEntity(List<Entity> list) {
        int count = 0;

        for (Entity entity : list) {
            if (entity instanceof EntityLiving) count++;
        }

        return count;
    }

}
