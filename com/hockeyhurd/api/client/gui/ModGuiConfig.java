package com.hockeyhurd.api.client.gui;

import com.hockeyhurd.api.handler.AbstractConfigHandler;
import com.hockeyhurd.mod.HCoreLibMain;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class for creating in-game configs.
 *
 * @author hockeyhurd
 * @version 4/23/16
 */
@SideOnly(Side.CLIENT)
public abstract class ModGuiConfig extends GuiConfig {

    @SuppressWarnings("unchecked")
    public ModGuiConfig(GuiScreen parent, AbstractConfigHandler config, String modID, String title) {
        super(parent, getConfigElements(config), modID, false, false, title);

        titleLine2 = getConfigHandler().PATH;
    }

    protected static List getConfigElements(AbstractConfigHandler config) {
        List<ConfigElement> list = new LinkedList<ConfigElement>();

        if (config != null && config.getCategories() != null && config.getCategories().length > 0) {
            for (String string : config.getCategories()) {
                HCoreLibMain.logHelper.info("Added:", string);
                list.add(new ConfigElement(new ConfigCategory(string)));
            }
        }

        return list;
    }

    /**
     * Gets the mod's config handler.
     *
     * @return AbstractConfigHandler.
     */
    protected abstract AbstractConfigHandler getConfigHandler();

}
