package com.hockeyhurd.api.client.gui;

import com.hockeyhurd.api.handler.config.AbstractConfigHandler;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
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

    protected final AbstractConfigHandler configHandler;

    @SuppressWarnings("unchecked")
    public ModGuiConfig(GuiScreen parent, AbstractConfigHandler config, String modID, String title) {
        super(parent, getConfigElements(config), modID, false, false, title);

        this.configHandler = config;
        titleLine2 = getConfigHandler().PATH;
    }

    @SuppressWarnings("unchecked")
    protected static List getConfigElements(AbstractConfigHandler config) {
        final List<ConfigElement> list = new LinkedList<ConfigElement>();

        if (config != null) {
            final ConfigCategory[] categories = config.getCategories();

            for (ConfigCategory configCategory : categories) {
                // list.addAll(new ConfigElement(configCategory).getChildElements());
                list.add(new ConfigElement(configCategory));
            }
        }

        return list;
    }

    /**
     * Gets the mod's config handler.
     *
     * @return AbstractConfigHandler.
     */
    protected AbstractConfigHandler getConfigHandler() {
        return configHandler;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
    }

}
