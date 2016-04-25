package com.hockeyhurd.api.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderWorldLastEvent;

/**
 * Interfacing for renderables in the world.
 *
 * @author hockeyhurd
 * @version 12/26/2015.
 */
@SideOnly(Side.CLIENT)
public interface IWorldRenderable {

	/**
	 * Main render call method with attached event.
	 *
	 * @param event Render event.
	 */
	void render(RenderWorldLastEvent event);

}
