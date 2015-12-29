package com.hockeyhurd.api.renderer;

import com.hockeyhurd.api.math.Vector3;
import com.hockeyhurd.api.util.Color;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderWorldLastEvent;

/**
 * Shape renderer class.
 *
 * @author hockeyhurd
 * @version 12/26/2015.
 */
@SideOnly(Side.CLIENT)
public class ShapeRenderer implements IWorldRenderable {

	private final EnumShape enumShape;
	private final Vector3<Float>[] vectors;
	private final Vector3<Float> offset;
	private final Color color;

	/**
	 * Creates ShapeRenderer.
	 *
	 * @param enumShape shape to render.
	 * @param vectors vectors to render at.
	 * @param offset offsetting vec.
	 * @param color color of shape to render.
	 */
	public ShapeRenderer(EnumShape enumShape, Vector3<Float>[] vectors, Vector3<Float> offset, Color color) {
		this.enumShape = enumShape;
		this.vectors = vectors;
		this.offset = offset;
		this.color = color;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void render(RenderWorldLastEvent event) {
		ShapeRendererUtils.renderShape(enumShape,vectors, offset, color);
	}

}
