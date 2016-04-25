package com.hockeyhurd.api.handler;

import com.hockeyhurd.api.client.gui.ModGuiConfig;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;

import java.lang.reflect.InvocationTargetException;

/**
 * In game gui event handler.
 *
 * @author hockeyhurd
 * @version 4/23/16
 */
@SideOnly(Side.CLIENT)
public class InGameGuiHandler {

    protected final Class<? extends ModGuiConfig> modGuiConfigClass;
    protected final AbstractConfigHandler configHandler;

    public InGameGuiHandler(Class<? extends ModGuiConfig> modGuiConfigClass, AbstractConfigHandler configHandler) {
        this.modGuiConfigClass = modGuiConfigClass;
        this.configHandler = configHandler;
    }

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {
        if (modGuiConfigClass.isInstance(event.gui)) {
            Class[] classes = new Class[2];
            classes[0] = modGuiConfigClass;
            classes[1] = configHandler.getClass();

            try {
                event.gui = (GuiScreen) modGuiConfigClass.getDeclaredConstructor(classes).newInstance(null,
                        configHandler);
            }

            catch (InstantiationException e) {
                e.printStackTrace();
            }

            catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

}
