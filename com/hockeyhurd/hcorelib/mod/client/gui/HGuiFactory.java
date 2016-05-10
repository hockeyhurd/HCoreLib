package com.hockeyhurd.hcorelib.mod.client.gui;

import com.hockeyhurd.hcorelib.api.client.gui.AbstractGuiFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Gui factory for HCoreLib.
 *
 * @author hockeyhurd
 * @version 4/23/16
 */
@SideOnly(Side.CLIENT)
public final class HGuiFactory extends AbstractGuiFactory {

    @Override
    public Class<? extends HGuiConfig> mainConfigGuiClass() {
        return HGuiConfig.class;
    }

}
