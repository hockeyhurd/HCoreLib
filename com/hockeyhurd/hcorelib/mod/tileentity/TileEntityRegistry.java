package com.hockeyhurd.hcorelib.mod.tileentity;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.opencl.CL;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hockeyhurd
 * @version 12/27/2017.
 */
public final class TileEntityRegistry {

    private static final TileEntityRegistry inst = new TileEntityRegistry();

    private final Map<Class<? extends AbstractTile>, Pair> tileEntityMap;
    private int count;

    private TileEntityRegistry() {
        tileEntityMap = new HashMap<>(0x20, 2.0f / 3.0f);
        count = 0;
    }

    public static TileEntityRegistry getInstance() {
        return inst;
    }

    private int getNextAvailableID() {
        return count++;
    }

    public void registerTileEntity(Class<? extends AbstractTile> clazz, String name) {
        tileEntityMap.put(clazz, new Pair(getNextAvailableID(), name));
        GameRegistry.registerTileEntity(clazz, name);
    }

    public int getTileEntityID(Class<? extends AbstractTile> clazz) {
        return tileEntityMap.containsKey(clazz) ? tileEntityMap.get(clazz).id : -1;
    }

    public String getTileEntityName(Class<? extends AbstractTile> clazz) {
        return tileEntityMap.containsKey(clazz) ? tileEntityMap.get(clazz).name : "";
    }

    private static class Pair {
        public final int id;
        public final String name;

        public Pair(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
