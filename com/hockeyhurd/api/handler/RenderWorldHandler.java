package com.hockeyhurd.api.handler;

import com.hockeyhurd.api.client.renderer.IWorldRenderable;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for rendering world shapes.
 *
 * @author hockeyhurd
 * @version 12/26/2015.
 */
@SideOnly(Side.CLIENT)
public final class RenderWorldHandler {

	/** List of renderables. */
	private List<IWorldRenderable> renderableList;

	/** Main instance of class. */
	private static RenderWorldHandler instance = new RenderWorldHandler();

	private RenderWorldHandler() {
		renderableList = new ArrayList<IWorldRenderable>();
	}

	/** Main instance of class.
	 *
	 */
	public static RenderWorldHandler instance() {
		return instance;
	}

	/**
	 * Adds a renderable to internal rendering list.
	 *
	 * @param renderable renderable to render.
	 */
	public void addRenderer(IWorldRenderable renderable) {
		if (renderable != null) renderableList.add(renderable);
	}

	@SubscribeEvent
	public void renderWorldLastEvent(RenderWorldLastEvent event) {
		if (!instance.renderableList.isEmpty()) {
			for (IWorldRenderable renderable : instance.renderableList) {
				renderable.render(event);
			}
		}
	}

}
