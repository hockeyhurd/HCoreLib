package com.hockeyhurd.hcorelib.api.client.renderer;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
