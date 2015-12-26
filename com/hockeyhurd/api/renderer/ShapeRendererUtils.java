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
		Color4i col = new Color4i(color.getARGB());
		// HCoreLibMain.lh.info("Col:", col.toString());
		// HCoreLibMain.lh.info(col.getR(), col.getG(), col.getB(), col.getA());

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);

		GL11.glLineWidth(6);
		GL11.glTranslatef(-worldVec.x, -worldVec.y + 1.62f, -worldVec.z);
		// GL11.glColor3ub((byte) col.getR(), (byte) col.getG(), (byte) col.getB());
		GL11.glColor4ub((byte) col.getR(), (byte) col.getG(), (byte) col.getB(), (byte) col.getA());

		// float mx = 9;
		float mx = worldVec.x;
		// float my = 9;
		float my = worldVec.y;
		// float mz = 9;
		float mz = worldVec.z;

		// GL11.glEnable(GL11.GL_LINE_SMOOTH);
		// GL11.glHint( GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST );
		// GL11.glBegin(GL11.GL_LINE_STRIP);

		// GL11.glVertex3f(mx+0.5f,my,mz+0.5f);
		// GL11.glVertex3f(mx-0.5f,my,mz+0.5f);

		GL11.glBegin(GL11.GL_QUADS);

		GL11.glVertex3f(mx + 0.5f, my, mz - 0.5f);
		GL11.glVertex3f(mx + 0.5f, my, mz + 0.5f);
		GL11.glVertex3f(mx - 0.5f, my, mz + 0.5f);
		GL11.glVertex3f(mx - 0.5f, my, mz - 0.5f);

		GL11.glEnd();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glPopMatrix();

	}

}
