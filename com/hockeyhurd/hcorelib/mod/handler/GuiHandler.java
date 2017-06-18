package com.hockeyhurd.hcorelib.mod.handler;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import com.hockeyhurd.hcorelib.mod.client.gui.GuiCalculator;
import com.hockeyhurd.hcorelib.mod.client.gui.GuiFurnace;
import com.hockeyhurd.hcorelib.mod.common.ContainerFurnace;
import com.hockeyhurd.hcorelib.mod.tileentity.TileFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
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
        final AbstractTile tile = (AbstractTile) world.getTileEntity(new BlockPos(x, y, z));

        if (tile instanceof TileFurnace) return new ContainerFurnace(player.inventory, ((TileFurnace) tile));

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        final AbstractTile tile = (AbstractTile) world.getTileEntity(new BlockPos(x, y, z));

        if (EnumGuiType.getTypeByID(ID) == EnumGuiType.ITEM) {
            return new GuiCalculator();
        }

        else if (tile instanceof TileFurnace) return new GuiFurnace(player.inventory, ((TileFurnace) tile));

        return null;
    }

}
