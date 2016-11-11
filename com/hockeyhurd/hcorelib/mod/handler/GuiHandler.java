package com.hockeyhurd.hcorelib.mod.handler;

import com.hockeyhurd.hcorelib.mod.client.gui.GuiCalculator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * @author hockeyhurd
 * @version 11/10/16
 */
public class GuiHandler implements IGuiHandler {

    public enum EnumGuiType {
        TILE_ENTITY, ITEM;

        public static EnumGuiType getTypeByID(int id) {
            return id < 0 ? ITEM : TILE_ENTITY;
        }
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (EnumGuiType.getTypeByID(ID) == EnumGuiType.ITEM) {
            return new GuiCalculator();
        }

        return null;
    }

}
