package com.hockeyhurd.hcorelib.api.client.util;

import com.hockeyhurd.hcorelib.api.math.*;
import com.hockeyhurd.hcorelib.api.util.LogicHelper;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Class used as helper for various tessllations with generic methods often used
 * with lwjgl based graphical renderings.
 * <br><bold>NOTE: </bold>There is no need to create own Tessellator instance as I provide this constant.
 * <br>@see {@link com.hockeyhurd.hcorelib.api.client.util.TessellatorHelper#tess}
 *
 * @author hockeyhurd
 * @version Oct 2, 2014
 */
@SideOnly(Side.CLIENT)
public class TessellatorHelper {

	public static final Tessellator tess = Tessellator.instance;
	private boolean testFlagColour = false;

	/**
	 * Default constructor.
	 */
	private TessellatorHelper() {
	}

	/**
	 * Draws icon along z- (can be unspecified icon should coordinates be provided).
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param min minimum point to draw from.
	 * @param max maximum point to draw from.
	 * @param difU difference of du.
	 * @param difV difference of dv.
	 * @param renderInside flag whether inside should also be rendered.
	 *
	 * @deprecated as of 8/7/15, use {@link TessellatorHelper#drawZNeg(com.hockeyhurd.hcorelib.api.math.Vector3, com.hockeyhurd.hcorelib.api.math.Vector3, float, float, float, float) instead.}
	 */
	@Deprecated
	public static void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(0.0f, 0.0f, -1.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, min - difU, min - difV);
		tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, min - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, max - difU, min - difV);

		if (renderInside) {
			tess.setNormal(0.0f, 0.0f, 1.0f);
			tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, max - difU, min - difV);
			tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, max - difU, max - difV);
			tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, min - difU, max - difV);
			tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, min - difU, min - difV);
		}
	}

	/**
	 * Draws textures along z-.
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param minU coordinate.
	 * @param maxU coordinate.
	 * @param minV coordinate.
	 * @param maxV coordinate.
	 */
	public static void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(0.0f, 0.0f, -1.0f);

		addVertUV(minVec.x, maxVec.y, minVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, minVec.z, minU, minV);
		addVertUV(maxVec.x, maxVec.y, minVec.z, minU, maxV);
	}

	/**
	 * Draws icon along z+ (can be unspecified icon should coordinates be provided).
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param min minimum point to draw from.
	 * @param max maximum point to draw from.
	 * @param difU difference of du.
	 * @param difV difference of dv.
	 * @param renderInside flag whether inside should also be rendered.
	 *
	 * @deprecated as of 8/7/15 use {@link TessellatorHelper#drawZPos(com.hockeyhurd.hcorelib.api.math.Vector3, com.hockeyhurd.hcorelib.api.math.Vector3, float, float, float, float)} instead.
	 */
	@Deprecated
	public static void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(0.0f, 0.0f, 1.0f);
		tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, min - difU, min - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, min - difU, max - difV);
		tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, max - difU, max - difV);
		tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, max - difU, min - difV);

		if (renderInside) {
			tess.setNormal(0.0f, 0.0f, -1.0f);
			tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, max - difU, min - difV);
			tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, max - difU, max - difV);
			tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, min - difU, max - difV);
			tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, min - difU, min - difV);
		}
	}

	/**
	 * Draws textures along z+.
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param minU coordinate.
	 * @param maxU coordinate.
	 * @param minV coordinate.
	 * @param maxV coordinate.
	 */
	public static void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(0.0f, 0.0f, 1.0f);

		addVertUV(minVec.x, maxVec.y, maxVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, maxVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws icon along x- (can be unspecified icon should coordinates be provided).
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param min minimum point to draw from.
	 * @param max maximum point to draw from.
	 * @param difU difference of du.
	 * @param difV difference of dv.
	 * @param renderInside flag whether inside should also be rendered.
	 *
	 * @deprecated  as of 8/7/15, use {@link TessellatorHelper#drawXNeg(com.hockeyhurd.hcorelib.api.math.Vector3, com.hockeyhurd.hcorelib.api.math.Vector3, float, float, float, float)} instead.
	 */
	@Deprecated
	public static void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(-1.0f, 0.0f, 0.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, min - difU, min - difV);
		tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, min - difU, max - difV);
		tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, max - difU, min - difV);

		if (renderInside) {
			tess.setNormal(1.0f, 0.0f, 0.0f);
			tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, max - difU, min - difV);
			tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, max - difU, max - difV);
			tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, min - difU, max - difV);
			tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, min - difU, min - difV);
		}
	}

	/**
	 * Draws textures along x-.
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param minU coordinate.
	 * @param maxU coordinate.
	 * @param minV coordinate.
	 * @param maxV coordinate.
	 */
	public static void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(-1.0f, 0.0f, 0.0f);

		addVertUV(minVec.x, maxVec.y, minVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws icon along x+ (can be unspecified icon should coordinates be provided).
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param min minimum point to draw from.
	 * @param max maximum point to draw from.
	 * @param difU difference of du.
	 * @param difV difference of dv.
	 * @param renderInside flag whether inside should also be rendered.
	 *
	 * @deprecated as of 8/7/15, use {@link TessellatorHelper#drawXPos(com.hockeyhurd.hcorelib.api.math.Vector3, com.hockeyhurd.hcorelib.api.math.Vector3, float, float, float, float)} instead.
	 */
	@Deprecated
	public static void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(1.0f, 0.0f, 0.0f);
		tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, max - difU, min - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, min - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, min - difU, min - difV);

		if (renderInside) {
			tess.setNormal(-1.0f, 0.0f, 0.0f);
			tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, min - difU, min - difV);
			tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, min - difU, max - difV);
			tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, max - difU, max - difV);
			tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, max - difU, min - difV);
		}
	}

	/**
	 * Draws textures along x+.
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param minU coordinate.
	 * @param maxU coordinate.
	 * @param minV coordinate.
	 * @param maxV coordinate.
	 */
	public static void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(1.0f, 0.0f, 0.0f);

		addVertUV(maxVec.x, maxVec.y, minVec.z, maxU, maxV);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws icon along y- (can be unspecified icon should coordinates be provided).
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param min minimum point to draw from.
	 * @param max maximum point to draw from.
	 * @param difU difference of du.
	 * @param difV difference of dv.
	 * @param renderInside flag whether inside should also be rendered.
	 *
	 * @deprecated as of 8/7/15, use {@link TessellatorHelper#drawYNeg(com.hockeyhurd.hcorelib.api.math.Vector3, com.hockeyhurd.hcorelib.api.math.Vector3, float, float, float, float)} instead.
	 */
	@Deprecated
	public static void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(0.0f, -1.0f, 0.0f);
		tess.addVertexWithUV(maxVec.x, minVec.y - 0.01d, minVec.z, max - difU, min - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y - 0.01d, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(minVec.x, minVec.y - 0.01d, maxVec.z, min - difU, max - difV);
		tess.addVertexWithUV(minVec.x, minVec.y - 0.01d, minVec.z, min - difU, min - difV);

		if (renderInside) {
			tess.setNormal(0.0f, 1.0f, 0.0f);
			tess.addVertexWithUV(minVec.x, minVec.y - 0.01d, minVec.z, min - difU, min - difV);
			tess.addVertexWithUV(minVec.x, minVec.y - 0.01d, maxVec.z, min - difU, max - difV);
			tess.addVertexWithUV(maxVec.x, minVec.y - 0.01d, maxVec.z, max - difU, max - difV);
			tess.addVertexWithUV(maxVec.x, minVec.y - 0.01d, minVec.z, max - difU, min - difV);
		}
	}

	/**
	 * Draws textures along y-.
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param minU coordinate.
	 * @param maxU coordinate.
	 * @param minV coordinate.
	 * @param maxV coordinate.
	 */
	public static void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(0.0f, -1.0f, 0.0f);

		addVertUV(maxVec.x, minVec.y, maxVec.z, maxU, maxV);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, minVec.y, minVec.z, minU, minV);
		addVertUV(minVec.x, minVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws icon along y+ (can be unspecified icon should coordinates be provided).
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param min minimum point to draw from.
	 * @param max maximum point to draw from.
	 * @param difU difference of du.
	 * @param difV difference of dv.
	 * @param renderInside flag whether inside should also be rendered.
	 *
	 * @deprecated as of 8/7/15, use {@link TessellatorHelper#drawYPos(com.hockeyhurd.hcorelib.api.math.Vector3, com.hockeyhurd.hcorelib.api.math.Vector3, float, float, float, float)} instead.
	 */
	public static void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(0.0f, 1.0f, 0.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y - 0.01d, minVec.z, min - difU, min - difV);
		tess.addVertexWithUV(minVec.x, maxVec.y - 0.01d, maxVec.z, min - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, maxVec.y - 0.01d, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, maxVec.y - 0.01d, minVec.z, max - difU, min - difV);

		if (renderInside) {
			tess.setNormal(0.0f, -1.0f, 0.0f);
			tess.addVertexWithUV(maxVec.x, maxVec.y - 0.01d, minVec.z, max - difU, min - difV);
			tess.addVertexWithUV(maxVec.x, maxVec.y - 0.01d, maxVec.z, max - difU, max - difV);
			tess.addVertexWithUV(minVec.x, maxVec.y - 0.01d, maxVec.z, min - difU, max - difV);
			tess.addVertexWithUV(minVec.x, maxVec.y - 0.01d, minVec.z, min - difU, min - difV);
		}
	}

	/**
	 * Draws textures along y+.
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param minU coordinate.
	 * @param maxU coordinate.
	 * @param minV coordinate.
	 * @param maxV coordinate.
	 */
	public static void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(0.0f, 1.0f, 0.0f);

		addVertUV(maxVec.x, maxVec.y, maxVec.z, maxU, maxV);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, maxVec.y, minVec.z, minU, minV);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws a 2D texture with provided UV coordinates.
	 *
	 * @param x x-position.
	 * @param y y-position.
	 * @param width width of plane.
	 * @param height height of plane
	 * @param minU min U coordinate.
	 * @param maxU max U coordinate.
	 * @param minV min V coordinate.
	 * @param maxV max V coordinate.
	 */
	public static void draw2D(float x, float y, float width, float height, float minU, float maxU, float minV, float maxV) {
		addVertUV(x, y, 0.0f, minU, maxV);
		addVertUV(x, y + height, 0.0f, minU, minV);
		addVertUV(x + width, y + height, 0.0f, maxU, minV);
		addVertUV(x + width, y, 0.0f, maxU, maxV);
	}

	/**
	 * Draws a 2D texture with provided UV coordinates.
	 *
	 * @param vec x, y position.
	 * @param size width, height.
	 * @param minUV min U, V
	 * @param maxUV max Y, V
	 *
	 * @throws IllegalArgumentException on null argument(s).
	 */
	public static void draw2D(Vector2<Float> vec, Vector2<Float> size, Vector2<Float> minUV, Vector2<Float> maxUV) {
		if (vec == null || size == null || minUV == null || maxUV == null)
			throw new IllegalArgumentException("NULL argument!");

		draw2D(vec.x, vec.y, size.x, size.y, minUV.x, maxUV.x, minUV.y, maxUV.y);
	}

	/**
	 * Draws a 2D texture with provided UV coordinates.
	 *
	 * @param vec x, y position.
	 * @param size width, height.
	 * @param minMax minUV, maxUV.
	 */
	public static void draw2D(Vector2<Float> vec, Vector2<Float> size, Vector2<Float> minMax) {
		if (vec == null || size == null || minMax == null)
			throw new IllegalArgumentException("NULL argument!");

		draw2D(vec.x, vec.y, size.x, size.y, minMax.x, minMax.y, minMax.x, minMax.y);
	}

	/**
	 * Draws a 2D texture with provided UV coordinates.
	 *
	 * @param vec x, y position.
	 * @param size width == height size
	 * @param minMax minUV, maxUV.
	 */
	public static void draw2D(Vector2<Float> vec, float size, Vector2<Float> minMax) {
		if (vec == null || minMax == null)
			throw new IllegalArgumentException("NULL argument!");

		draw2D(vec.x, vec.y, size, size, minMax.x, minMax.y, minMax.x, minMax.y);
	}

	/**
	 * Sets normal by specified values.
	 *
	 * @param x x face.
	 * @param y y face.
	 * @param z z face.
	 */
	public static void setNormal(float x, float y, float z) {
		if (LogicHelper.isNumberInRange(x, -1f, 1f) && LogicHelper.isNumberInRange(y, -1f, 1f) &&
				LogicHelper.isNumberInRange(z, -1f, 1f))
			tess.setNormal(x, y, z);
	}

	/**
	 * Sets normal by specified vector values.
	 *
	 * @param vec vector representation of faces.
	 */
	public static void setNormal(Vector3<Float> vec) {
		setNormal(vec.x, vec.y, vec.z);
	}

	/**
	 * Adds vertex with uv.
	 *
	 * @param x x-pos.
	 * @param y y-pos.
	 * @param z z-pos.
	 * @param u u.
	 * @param v v.
	 */
	public static void addVertUV(float x, float y, float z, float u, float v) {
		tess.addVertexWithUV(x, y, z, u, v);
	}

	/**
	 * Adds vertex with uv.
	 *
	 * @param vec vector position.
	 * @param u u.
	 * @param v v.
	 */
	public static void addVertUV(Vector3<Float> vec, float u, float v) {
		tess.addVertexWithUV(vec.x, vec.y, vec.z, u, v);
	}

	/**
	 * Adds vertex with uv.
	 *
	 * @param vec vector position.
	 * @param uv uv vector.
	 */
	public static void addVertUV(Vector3<Float> vec, Vector2<Float> uv) {
		tess.addVertexWithUV(vec.x, vec.y, vec.z, uv.x, uv.y);
	}

	/**
	 * Adds vertex.
	 *
	 * @param x x-pos.
	 * @param y y-pos.
	 * @param z z-pos.
	 */
	public static void addVert(float x, float y, float z) {
		tess.addVertex(x, y, z);
	}

	/**
	 * Adds vertex.
	 *
	 * @param vec vector position.
	 */
	public static void addVert(Vector3<Float> vec) {
		tess.addVertex(vec.x, vec.y, vec.z);
	}

	/**
	 * Sets color RGBA.
	 *
	 * @param r int.
	 * @param g int.
	 * @param b int.
	 * @param a int.
	 */
	public static void setColorRGBA(int r, int g, int b, int a) {
		tess.setColorRGBA(r, g, b, a);
	}

	/**
	 * Sets color RGBA.
	 *
	 * @param color Color4i.
	 */
	public static void setColorRGBA(Color4i color) {
		tess.setColorRGBA(color.getR(), color.getG(), color.getB(), color.getA());
	}

	/**
	 * Sets color RGBA.
	 *
	 * @param hex hex color value.
	 */
	public static void setColorRGBA(int hex) {
		setColorRGBA(new Color4i(hex));
	}

	/**
	 * Sets color RGBA.
	 *
	 * @param r float.
	 * @param g float.
	 * @param b float.
	 * @param a float.
	 */
	public static void setColorRGBA(float r, float g, float b, float a) {
		tess.setColorRGBA_F(r, g, b, a);
	}

	/**
	 * Sets color RGBA.
	 *
	 * @param color Color4f.
	 */
	public static void setColorRGBA(Color4f color) {
		tess.setColorRGBA_F(color.getR(), color.getG(), color.getB(), color.getA());
	}

	/**
	 * Draws face from provided matrix and uv coordinates.
	 *
	 * @param matrix matrix.
	 * @param minU min-u.
	 * @param maxU max-u.
	 * @param minV min-v.
	 * @param maxV max-v.
	 */
	public static void drawFace(Matrix4f matrix, float minU, float maxU, float minV, float maxV) {
		drawFace(matrix, null, minU, maxU, minV, maxV);
	}

	/**
	 * Draws face from provided matrix and uv coordinates with setting of normal vector (if not null).
	 *
	 * @param matrix matrix.
	 * @param normalVec face normals.
	 * @param minU min-u.
	 * @param maxU max-u.
	 * @param minV min-v.
	 * @param maxV max-v.
	 */
	@SuppressWarnings("unchecked")
	public static void drawFace(Matrix4f matrix, Vector3<Float> normalVec, float minU, float maxU, float minV, float maxV) {
		Vector3<?>[] proj = matrix.getProjection();

		if (normalVec != null) setNormal(normalVec);

		addVertUV((Vector3<Float>) proj[0], maxU, maxV);
		addVertUV((Vector3<Float>) proj[1], maxU, minV);
		addVertUV((Vector3<Float>) proj[2], minU, minV);
		addVertUV((Vector3<Float>) proj[3], minU, maxV);
	}

	/**
	 * Tells tessellator to start drawing quads!
	 */
	public static void startDrawingQuads() {
		tess.startDrawingQuads();
	}

	/**
	 * Starts drawing face with 'n' number of vertices where 'n' is a positive integer >= 3.
	 *
	 * @param verts number of vertices to start drawing.
	 */
	public static void startDrawing(int verts) {
		if (verts > 2) tess.startDrawing(verts);
		else HCoreLibMain.logHelper.severe("Error starting to draw with #", verts, "ensure this is (int) 'verts' >= '3'!");
	}

	/**
	 * Teslls tessellator to draw/push drawing calls of established vertices.
	 */
	public static void draw() {
		tess.draw();
	}

}
