package com.hockeyhurd.mod.client.gui;

import com.hockeyhurd.api.client.gui.ModGuiConfig;
import com.hockeyhurd.api.handler.AbstractConfigHandler;
import com.hockeyhurd.mod.HCoreLibMain;
import com.hockeyhurd.mod.handler.ConfigHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;

/**
 * Gui class for HCoreLib gui config.
 *
 * @author hockeyhurd
 * @version 4/23/16
 */
@SideOnly(Side.CLIENT)
public final class HGuiConfig extends ModGuiConfig {

    public HGuiConfig(GuiScreen parent, ConfigHandler config) {
        super(parent, config, HCoreLibMain.modID, HCoreLibMain.modID + " config");
    }

    @Override
    protected AbstractConfigHandler getConfigHandler() {
        return HCoreLibMain.configHandler;
    }

}
