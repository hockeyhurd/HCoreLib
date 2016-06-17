package com.hockeyhurd.hcorelib.api.client.util;

import com.hockeyhurd.hcorelib.api.math.*;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Render helper class to replace TessellatorHelper
 * for 1.9.x Minecraft.
 *
 * @author hockeyhurd
 * @version 6/11/2016.
 */
@SideOnly(Side.CLIENT)
public final class RenderHelper {

	public static final Tessellator tessellator = Tessellator.getInstance();
	private static final VertexBuffer vertexBuf = tessellator.getBuffer();
	private static final TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
	public static final float DRAW_OFFSET = 0.0001f;
	private static boolean isDrawing = false;

	private RenderHelper() {
	}

	/**
	 * Gets the current drawing state.
	 *
	 * @return boolean result.
	 */
	public static boolean getDrawingState() {
		return isDrawing;
	}

	/**
	 * Binds resource location texture.
	 *
	 * @param resourceLocation ResourceLocation.
	 */
	public static void bindTexture(ResourceLocation resourceLocation) {
		if (resourceLocation != null) textureManager.bindTexture(resourceLocation);
	}

	/**
	 * Binds resource location texture.
	 *
	 * @param assetDir String asset dir i.e. Mod name.
	 * @param path String file path.
	 */
	public static void bindTexture(String assetDir, String path) {
		textureManager.bindTexture(new ResourceLocation(assetDir, path));
	}

	/**
	 * Pushes gl matrix.
	 */
	public static void pushMatrix() {
		if (!isDrawing) {
			GL11.glPushMatrix();
			isDrawing = true;
		}

		else HCoreLibMain.logHelper.warn("Already drawing!");
	}

	/**
	 * Pops gl matrix.
	 */
	public static void popMatrix() {
		if (isDrawing) {
			GL11.glPopMatrix();
			isDrawing = false;
		}

		else HCoreLibMain.logHelper.warn("Not drawing currently!");
	}

	/**
	 * Starts tesellating with appropriate gl mode.
	 *
	 * @param glMode OpenGL mode.
	 */
	public static void startDrawing(int glMode) {
		if (isDrawing)
			vertexBuf.begin(glMode, DefaultVertexFormats.POSITION_TEX);
		else HCoreLibMain.logHelper.warn("Matrix not pushed!");
	}

	/**
	 * Starts tessllating quads with OpenGL.
	 */
	public static void startDrawingQuads() {
		if (isDrawing)
			vertexBuf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		else HCoreLibMain.logHelper.warn("Matrix not pushed!");
	}

	/**
	 * Draws tessellated vertexes.
	 */
	public static void draw() {
		if (isDrawing)
			tessellator.draw();
		else HCoreLibMain.logHelper.warn("Not drawing currently!");
	}

	/**
	 * Adds vertex.
	 *
	 * @param x float.
	 * @param y float.
	 * @param z float.
	 */
	public static void addVert(float x, float y, float z) {
		vertexBuf.pos(x, y, z).endVertex();
	}

	/**
	 * Adds vertex.
	 *
	 * @param vec Vector3f.
	 */
	public static void addVert(Vector3<Float> vec) {
		vertexBuf.pos(vec.x, vec.y, vec.z);
	}

	/**
	 * Adds vertex with uv coordinates.
	 *
	 * @param x float.
	 * @param y float.
	 * @param z float.
	 * @param u float.
	 * @param v float.
	 */
	public static void addVertUV(float x, float y, float z, float u, float v) {
		vertexBuf.pos(x, y, z).tex(u, v).endVertex();
	}

	/**
	 * Adds vertex with uv coordinates.
	 *
	 * @param vec Vector3f.
	 * @param u float.
	 * @param v float.
	 */
	public static void addVertUV(Vector3<Float> vec, float u, float v) {
		vertexBuf.pos(vec.x, vec.y, vec.z).tex(u, v).endVertex();
	}

	/**
	 * Adds vertex with uv coordinates.
	 *
	 * @param vec Vector3f.
	 * @param texVec Vector2f.
	 */
	public static void addVertUV(Vector3<Float> vec, Vector2<Float> texVec) {
		vertexBuf.pos(vec.x, vec.y, vec.z).tex(texVec.x, texVec.y).endVertex();
	}

	/*public static void setNormal(float x, float y, float z) {
		vertexBuf.normal(x, y, z);
	}

	public static void setNormal(Vector3<Float> vec) {
		vertexBuf.normal(vec.x, vec.y, vec.z);
	}*/

	/**
	 * Sets OpenGL color.
	 *
	 * @param r byte.
	 * @param g byte.
	 * @param b byte.
	 * @param a byte.
	 */
	public static void setColor(byte r, byte g, byte b, byte a) {
		GL11.glColor4b(r, g, b, a);
	}

	/**
	 * Sets OpenGL color.
	 *
	 * @param r byte.
	 * @param g byte.
	 * @param b byte.
	 */
	public static void setColor(byte r, byte g, byte b) {
		GL11.glColor4b(r, g, b, Byte.MAX_VALUE);
	}

	/**
	 * Sets OpenGL color.
	 *
	 * @param argb int argb.
	 */
	public static void setColor(int argb) {
		setColor(new Color4i(argb));
	}

	/**
	 * Sets OpenGL color.
	 *
	 * @param color Color4i color.
	 */
	public static void setColor(Color4i color) {
		GL11.glColor4b((byte) color.getR(), (byte) color.getG(), (byte) color.getB(), (byte) color.getA());
	}

	/**
	 * Sets OpenGL color.
	 *
	 * @param r float.
	 * @param g float.
	 * @param b float.
	 * @param a float.
	 */
	public static void setColor(float r, float g, float b, float a) {
		GL11.glColor4f(r, g, b, a);
	}

	/**
	 * Sets OpenGL color.
	 *
	 * @param r float.
	 * @param g float.
	 * @param b float.
	 */
	public static void setColor(float r, float g, float b) {
		GL11.glColor4f(r, g, b, 1.0f);
	}

	/**
	 * Sets OpenGL color.
	 *
	 * @param color Color4f color.
	 */
	public static void setColor(Color4f color) {
		GL11.glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minU float.
	 * @param minV float.
	 * @param maxU float.
	 * @param maxV float.
	 */
	public static void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(0.0f, 0.0f, -1.0f);
		addVertUV(maxVec.x, maxVec.y, minVec.z, minU, maxV);
		addVertUV(maxVec.x, minVec.y, minVec.z, minU, minV);
		addVertUV(minVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, maxVec.y, minVec.z, maxU, maxV);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minUV Vector2f.
	 * @param maxUV Vector2f.
	 */
	public static void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(0.0f, 0.0f, -1.0f);
		addVertUV(maxVec.x, maxVec.y, minVec.z, minUV.x, maxUV.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, minUV.x, minUV.y);
		addVertUV(minVec.x, minVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(minVec.x, maxVec.y, minVec.z, maxUV.x, maxUV.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param rect Rectangle.
	 */
	public static void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(0.0f, 0.0f, -1.0f);
		addVertUV(maxVec.x, maxVec.y, minVec.z, rect.min.x, rect.max.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, rect.min.x, rect.min.y);
		addVertUV(minVec.x, minVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(minVec.x, maxVec.y, minVec.z, rect.max.x, rect.max.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minU float.
	 * @param minV float.
	 * @param maxU float.
	 * @param maxV float.
	 */
	public static void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(0.0f, 0.0f, 1.0f);
		addVertUV(minVec.x, maxVec.y, maxVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, maxVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minUV Vector2f.
	 * @param maxUV Vector2f.
	 */
	public static void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(0.0f, 0.0f, 1.0f);
		addVertUV(minVec.x, maxVec.y, maxVec.z, maxUV.x, maxUV.y);
		addVertUV(minVec.x, minVec.y, maxVec.z, maxUV.x, minUV.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minUV.x, minUV.y);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minUV.x, maxUV.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param rect Rectangle.
	 */
	public static void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(0.0f, 0.0f, 1.0f);
		addVertUV(minVec.x, maxVec.y, maxVec.z, rect.max.x, rect.max.y);
		addVertUV(minVec.x, minVec.y, maxVec.z, rect.max.x, rect.min.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, rect.min.x, rect.min.y);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, rect.min.x, rect.max.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minU float.
	 * @param minV float.
	 * @param maxU float.
	 * @param maxV float.
	 */
	public static void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(-1.0f, 0.0f, 0.0f);
		addVertUV(minVec.x, maxVec.y, minVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minUV Vector2f.
	 * @param maxUV Vector2f.
	 */
	public static void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(-1.0f, 0.0f, 0.0f);
		addVertUV(minVec.x, maxVec.y, minVec.z, maxUV.x, maxUV.y);
		addVertUV(minVec.x, minVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(minVec.x, minVec.y, maxVec.z, minUV.x, minUV.y);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minUV.x, maxUV.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param rect Rectangle.
	 */
	public static void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(-1.0f, 0.0f, 0.0f);
		addVertUV(minVec.x, maxVec.y, minVec.z, rect.max.x, rect.max.y);
		addVertUV(minVec.x, minVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(minVec.x, minVec.y, maxVec.z, rect.min.x, rect.min.y);
		addVertUV(minVec.x, maxVec.y, maxVec.z, rect.min.x, rect.max.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minU float.
	 * @param minV float.
	 * @param maxU float.
	 * @param maxV float.
	 */
	public static void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(1.0f, 0.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minU, maxV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxU, maxV);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minUV Vector2f.
	 * @param maxUV Vector2f.
	 */
	public static void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(1.0f, 0.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minUV.x, maxUV.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minUV.x, minUV.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxUV.x, maxUV.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param rect Rectangle.
	 */
	public static void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(1.0f, 0.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, rect.min.x, rect.max.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, rect.min.x, rect.min.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(maxVec.x, maxVec.y, minVec.z, rect.max.x, rect.max.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minU float.
	 * @param minV float.
	 * @param maxU float.
	 * @param maxV float.
	 */
	public static void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(0.0f, -1.0f, 0.0f);
		addVertUV(minVec.x, minVec.y, maxVec.z, minU, maxV);
		addVertUV(minVec.x, minVec.y, minVec.z, minU, minV);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, maxU, maxV);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minUV Vector2f.
	 * @param maxUV Vector2f.
	 */
	public static void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(0.0f, -1.0f, 0.0f);
		addVertUV(minVec.x, minVec.y, maxVec.z, minUV.x, maxUV.y);
		addVertUV(minVec.x, minVec.y, minVec.z, minUV.x, minUV.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, maxUV.x, maxUV.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param rect Rectangle.
	 */
	public static void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(0.0f, -1.0f, 0.0f);
		addVertUV(minVec.x, minVec.y, maxVec.z, rect.min.x, rect.max.y);
		addVertUV(minVec.x, minVec.y, minVec.z, rect.min.x, rect.min.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, rect.max.x, rect.max.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minU float.
	 * @param minV float.
	 * @param maxU float.
	 * @param maxV float.
	 */
	public static void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// // setNormal(0.0f, 1.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, maxU, maxV);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, maxVec.y, minVec.z, minU, minV);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minUV Vector2f.
	 * @param maxUV Vector2f.
	 */
	public static void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(0.0f, 1.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, maxUV.x, maxUV.y);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(minVec.x, maxVec.y, minVec.z, minUV.x, minUV.y);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minUV.x, maxUV.y);
	}

	/**
	 * Draws quad at min/max vectors.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param rect Rectangle.
	 */
	public static void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(0.0f, 1.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, rect.max.x, rect.max.y);
		addVertUV(maxVec.x, maxVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(minVec.x, maxVec.y, minVec.z, rect.min.x, rect.min.y);
		addVertUV(minVec.x, maxVec.y, maxVec.z, rect.min.x, rect.max.y);
	}

	/**
	 * Draws a generic cube at min/max vectors with uv coordinates.
	 *
	 * @param minVec Vector3f.
	 * @param maxVec Vector3f.
	 * @param minU float.
	 * @param minV float.
	 * @param maxU float.
	 * @param maxV float.
	 */
	public static void drawCuboid(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		drawXPos(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawXNeg(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawYPos(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawYNeg(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawZPos(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawZNeg(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
	}

	/**
	 * Handles setting-up standard OpenGL code.
	 *
	 * @param resource ResourceLocation texture.
	 * @param x float.
	 * @param y float.
	 * @param z float.
	 */
	public static void setupPreRenderf(ResourceLocation resource, float x, float y, float z) {
		// GL11.glPushMatrix();
		pushMatrix();

		GL11.glTranslatef(x, y, z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xf0 % 0x10000, 0xf0 / 0x10000);
		bindTexture(resource);
	}

	/**
	 * Handles setting-up standard OpenGL code.
	 *
	 * @param resource ResourceLocation texture.
	 * @param vec Vector3f.
	 */
	public static void setupPreRenderf(ResourceLocation resource, Vector3<Float> vec) {
		// GL11.glPushMatrix();
		pushMatrix();

		GL11.glTranslatef(vec.x, vec.y, vec.z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xf0 % 0x10000, 0xf0 / 0x10000);
		bindTexture(resource);
	}

	/**
	 * Handles setting-up standard OpenGL code.
	 *
	 * @param resource ResourceLocation texture.
	 * @param x double.
	 * @param y double.
	 * @param z double.
	 */
	public static void setupPreRender(ResourceLocation resource, double x, double y, double z) {
		// GL11.glPushMatrix();
		pushMatrix();

		GL11.glTranslated(x, y, z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xf0 % 0x10000, 0xf0 / 0x10000);
		bindTexture(resource);
	}

	/**
	 * Handles setting-up standard OpenGL code.
	 *
	 * @param resource ResourceLocation texture.
	 * @param vec Vector3d.
	 */
	public static void setupPreRender(ResourceLocation resource, Vector3<Double> vec) {
		// GL11.glPushMatrix();
		pushMatrix();

		GL11.glTranslated(vec.x, vec.y, vec.z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xf0 % 0x10000, 0xf0 / 0x10000);
		bindTexture(resource);
	}

	/**
	 * Handles finishing up of standard OpenGL code.
	 *
	 * @param x float.
	 * @param y float.
	 * @param z float.
	 */
	public static void finishPostRenderf(float x, float y, float z) {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslatef(-x, -y, -z);

		// GL11.glPopMatrix();
		popMatrix();
	}

	/**
	 * Handles finishing up of standard OpenGL code.
	 *
	 * @param vec Vector3f.
	 */
	public static void finishPostRenderf(Vector3<Float> vec) {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslatef(-vec.x, -vec.y, -vec.z);

		// GL11.glPopMatrix();
		popMatrix();
	}

	/**
	 * Handles finishing up of standard OpenGL code.
	 *
	 * @param x double.
	 * @param y double.
	 * @param z double.
	 */
	public static void finishPostRender(double x, double y, double z) {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-x, -y, -z);

		// GL11.glPopMatrix();
		popMatrix();
	}

	/**
	 * Handles finishing up of standard OpenGL code.
	 *
	 * @param vec Vector3d.
	 */
	public static void finishPostRender(Vector3<Double> vec) {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-vec.x, -vec.y, -vec.z);

		// GL11.glPopMatrix();
		popMatrix();
	}

}
