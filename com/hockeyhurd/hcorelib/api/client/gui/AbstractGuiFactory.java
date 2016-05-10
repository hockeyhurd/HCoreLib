package com.hockeyhurd.hcorelib.api.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

/**
 * Abstract gui factory used by FML to load the in-game gui config.
 *
 * @author hockeyhurd
 * @version 4/23/16
 */
@SideOnly(Side.CLIENT)
public abstract class AbstractGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public abstract Class<? extends ModGuiConfig> mainConfigGuiClass();

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

}
