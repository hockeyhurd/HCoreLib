package com.hockeyhurd.mod.client.gui;

import com.hockeyhurd.api.client.gui.ModGuiConfig;
import com.hockeyhurd.mod.HCoreLibMain;
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

    public HGuiConfig(GuiScreen parent) {
        super(parent, HCoreLibMain.configHandler, HCoreLibMain.modID, HCoreLibMain.modID + " config");
    }

}
