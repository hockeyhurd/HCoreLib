package com.hockeyhurd.hcorelib.api.handler.client;

import com.hockeyhurd.hcorelib.api.math.Color4f;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author hockeyhurd
 * @version 11/26/17
 */
public interface IWorldDrawable {

    int getRadii();

    @SideOnly(Side.CLIENT)
    Color4f getColor();

}
