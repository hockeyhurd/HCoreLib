package com.hockeyhurd.hcorelib.mod.client.gui;

import com.hockeyhurd.hcorelib.api.client.gui.ModGuiConfig;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
