package com.hockeyhurd.hcorelib.mod.client;

import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.client.renderer.WitchHatModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author hockeyhurd
 * @version 9/3/17
 */
public final class PlayerRenderer {

    private static WitchHatModel witchHatModel = new WitchHatModel();

    private static PlayerRenderer inst = new PlayerRenderer();

    private PlayerRenderer() {

    }

    public static PlayerRenderer getInstance() {
        return inst;
    }

    @SubscribeEvent
    public void renderPlayerModel(RenderPlayerEvent.Post event) {
        HCoreLibMain.logHelper.info("Rendering!");
        event.getRenderer().bindTexture(new ResourceLocation(HCoreLibMain.assetDir, ""));
        witchHatModel.render(event.getEntityPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f);
    }

}
