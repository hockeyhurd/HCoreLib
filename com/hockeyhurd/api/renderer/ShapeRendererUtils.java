package com.hockeyhurd.api.renderer;

import com.hockeyhurd.api.math.Color4i;
import com.hockeyhurd.api.math.Vector3;
import com.hockeyhurd.api.util.Color;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
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
	 *
	 * @param enumShape shape to render.
	 * @param world world to render in.
	 * @param worldVec world vec to render at.
	 * @param offset offsetting vec.
	 * @param color color of shape to render.
	 */
	public static void renderShape(EnumShape enumShape, World world, Vector3<Integer> worldVec, Vector3<Integer> offset, Color color) {
		final Vector3<Float> vec = new Vector3<Float>((float) worldVec.x - offset.x, (float) worldVec.y - offset.y, (float) worldVec.z - offset.z);
		final Vector3<Float> sizeVec = new Vector3<Float>(9.0f, 9.0f, 9.0f);
		Color4i col = new Color4i(color.getRGBA());

		GL11.glPushMatrix();

		GL11.glTranslatef(-vec.x, -vec.y, -vec.z);
		GL11.glColor4b((byte) col.getR(), (byte) col.getG(), (byte) col.getB(), (byte) col.getA());

		if (enumShape == EnumShape.RECT || enumShape == EnumShape.SQUARE) {
			GL11.glBegin(GL11.GL_LINES);

			/*GL11.glVertex3f(sizeVec.x + 0.4f, sizeVec.y, sizeVec.z + 0.4f);
			GL11.glVertex3f(sizeVec.x - 0.4f, sizeVec.y, sizeVec.z - 0.4f);
			GL11.glVertex3f(sizeVec.x + 0.4f, sizeVec.y, sizeVec.z - 0.4f);
			GL11.glVertex3f(sizeVec.x - 0.4f, sizeVec.y, sizeVec.z + 0.4f);*/

			GL11.glVertex3f(vec.x - 0.4f, vec.y, vec.z + 0.4f);
			GL11.glVertex3f(vec.x + 0.4f, vec.y, vec.z - 0.4f);
			GL11.glVertex3f(vec.x - 0.4f, vec.y, vec.z - 0.4f);
			GL11.glVertex3f(vec.x + 0.4f, vec.y, vec.z + 0.4f);

			GL11.glEnd();
		}

		GL11.glTranslatef(vec.x, vec.y, vec.z);
		GL11.glPopMatrix();
	}

}
