package com.hockeyhurd.mod.client.gui;

import com.hockeyhurd.api.client.gui.AbstractGuiFactory;
import com.hockeyhurd.api.client.gui.ModGuiConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gui factory for HCoreLib.
 *
 * @author hockeyhurd
 * @version 4/23/16
 */
@SideOnly(Side.CLIENT)
public final class HGuiFactory extends AbstractGuiFactory {

    @Override
    public Class<? extends ModGuiConfig> mainConfigGuiClass() {
        return HGuiConfig.class;
    }

}
