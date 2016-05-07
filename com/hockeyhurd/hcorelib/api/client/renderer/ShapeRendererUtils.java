package com.hockeyhurd.hcorelib.api.client.renderer;

import com.hockeyhurd.hcorelib.api.math.Color;
import com.hockeyhurd.hcorelib.api.math.Color4i;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.util.TessellatorHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Class for rendering shapes into the world.
 *
 * @author hockeyhurd
 * @version 12/26/2015.
 */
@SideOnly(Side.CLIENT)
public class ShapeRendererUtils {

	private ShapeRendererUtils() {
	}

	/**
	 * Util method for rendering a shape.
	 * <br><bold>NOTE: </bold> Currently only supports quad based shapes, more coming soon.
	 *
	 * @param enumShape shape to render.
	 * @param vectors vectors to render.
	 * @param offset offsetting vec.
	 * @param color color of shape to render.
	 */
	public static void renderShape(EnumShape enumShape, Vector3<Float>[] vectors, Vector3<Float> offset, Color color) {
		final TessellatorHelper tessHelp = new TessellatorHelper();
		final Color4i col = new Color4i(color.getARGB());

		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glTranslatef(-offset.x, -offset.y, -offset.z);
		tessHelp.startDrawingQuads();
		tessHelp.setColorRGBA(col);

		if ((enumShape == EnumShape.RECT || enumShape == EnumShape.SQUARE) && vectors != null && vectors.length == 2) {
			tessHelp.addVert(vectors[1].x, vectors[0].y, vectors[0].z);
			tessHelp.addVert(vectors[1].x, vectors[0].y, vectors[1].z);
			tessHelp.addVert(vectors[0].x, vectors[0].y, vectors[1].z);
			tessHelp.addVert(vectors[0].x, vectors[0].y, vectors[0].z);
		}

		tessHelp.draw();

		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);

	}

}
