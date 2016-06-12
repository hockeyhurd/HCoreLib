package com.hockeyhurd.hcorelib.api.client.util;

import com.hockeyhurd.hcorelib.api.math.*;
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

	private RenderHelper() {
	}

	public static void bindTexture(ResourceLocation resourceLocation) {
		if (resourceLocation != null) textureManager.bindTexture(resourceLocation);
	}

	public static void bindTexture(String assetDir, String path) {
		textureManager.bindTexture(new ResourceLocation(assetDir, path));
	}

	public static void startDrawing(int glMode) {
		vertexBuf.begin(glMode, DefaultVertexFormats.POSITION_TEX);
	}

	public static void startDrawingQuads() {
		vertexBuf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
	}

	public static void draw() {
		tessellator.draw();
	}

	public static void addVert(float x, float y, float z) {
		vertexBuf.pos(x, y, z).endVertex();
	}

	public static void addVert(Vector3<Float> vec) {
		vertexBuf.pos(vec.x, vec.y, vec.z);
	}

	public static void addVertUV(float x, float y, float z, float u, float v) {
		vertexBuf.pos(x, y, z).tex(u, v).endVertex();
	}

	public static void addVertUV(Vector3<Float> vec, float u, float v) {
		vertexBuf.pos(vec.x, vec.y, vec.z).tex(u, v).endVertex();
	}

	public static void addVertUV(Vector3<Float> vec, Vector2<Float> texVec) {
		vertexBuf.pos(vec.x, vec.y, vec.z).tex(texVec.x, texVec.y).endVertex();
	}

	public static void setNormal(float x, float y, float z) {
		vertexBuf.normal(x, y, z);
	}

	public static void setNormal(Vector3<Float> vec) {
		vertexBuf.normal(vec.x, vec.y, vec.z);
	}

	public static void setColor(byte r, byte g, byte b, byte a) {
		GL11.glColor4b(r, g, b, a);
	}

	public static void setColor(byte r, byte g, byte b) {
		GL11.glColor4b(r, g, b, Byte.MAX_VALUE);
	}

	public static void setColor(int argb) {
		setColor(new Color4i(argb));
	}

	public static void setColor(Color4i color) {
		GL11.glColor4b((byte) color.getR(), (byte) color.getG(), (byte) color.getB(), (byte) color.getA());
	}

	public static void setColor(float r, float g, float b, float a) {
		GL11.glColor4f(r, g, b, a);
	}

	public static void setColor(float r, float g, float b) {
		GL11.glColor4f(r, g, b, 1.0f);
	}

	public static void setColor(Color4f color) {
		GL11.glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
	}

	public static void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(0.0f, 0.0f, -1.0f);
		addVertUV(maxVec.x, maxVec.y, minVec.z, minU, maxV);
		addVertUV(maxVec.x, minVec.y, minVec.z, minU, minV);
		addVertUV(minVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, maxVec.y, minVec.z, maxU, maxV);
	}

	public static void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(0.0f, 0.0f, -1.0f);
		addVertUV(maxVec.x, maxVec.y, minVec.z, minUV.x, maxUV.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, minUV.x, minUV.y);
		addVertUV(minVec.x, minVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(minVec.x, maxVec.y, minVec.z, maxUV.x, maxUV.y);
	}

	public static void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(0.0f, 0.0f, -1.0f);
		addVertUV(maxVec.x, maxVec.y, minVec.z, rect.min.x, rect.max.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, rect.min.x, rect.min.y);
		addVertUV(minVec.x, minVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(minVec.x, maxVec.y, minVec.z, rect.max.x, rect.max.y);
	}

	public static void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(0.0f, 0.0f, 1.0f);
		addVertUV(minVec.x, maxVec.y, maxVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, maxVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	public static void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(0.0f, 0.0f, 1.0f);
		addVertUV(minVec.x, maxVec.y, maxVec.z, maxUV.x, maxUV.y);
		addVertUV(minVec.x, minVec.y, maxVec.z, maxUV.x, minUV.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minUV.x, minUV.y);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minUV.x, maxUV.y);
	}

	public static void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(0.0f, 0.0f, 1.0f);
		addVertUV(minVec.x, maxVec.y, maxVec.z, rect.max.x, rect.max.y);
		addVertUV(minVec.x, minVec.y, maxVec.z, rect.max.x, rect.min.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, rect.min.x, rect.min.y);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, rect.min.x, rect.max.y);
	}

	public static void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(-1.0f, 0.0f, 0.0f);
		addVertUV(minVec.x, maxVec.y, minVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	public static void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(-1.0f, 0.0f, 0.0f);
		addVertUV(minVec.x, maxVec.y, minVec.z, maxUV.x, maxUV.y);
		addVertUV(minVec.x, minVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(minVec.x, minVec.y, maxVec.z, minUV.x, minUV.y);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minUV.x, maxUV.y);
	}

	public static void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(-1.0f, 0.0f, 0.0f);
		addVertUV(minVec.x, maxVec.y, minVec.z, rect.max.x, rect.max.y);
		addVertUV(minVec.x, minVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(minVec.x, minVec.y, maxVec.z, rect.min.x, rect.min.y);
		addVertUV(minVec.x, maxVec.y, maxVec.z, rect.min.x, rect.max.y);
	}

	public static void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(1.0f, 0.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minU, maxV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxU, maxV);
	}

	public static void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(1.0f, 0.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minUV.x, maxUV.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minUV.x, minUV.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxUV.x, maxUV.y);
	}

	public static void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(1.0f, 0.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, rect.min.x, rect.max.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, rect.min.x, rect.min.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(maxVec.x, maxVec.y, minVec.z, rect.max.x, rect.max.y);
	}

	public static void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// setNormal(0.0f, -1.0f, 0.0f);
		addVertUV(minVec.x, minVec.y, maxVec.z, minU, maxV);
		addVertUV(minVec.x, minVec.y, minVec.z, minU, minV);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, maxU, maxV);
	}

	public static void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(0.0f, -1.0f, 0.0f);
		addVertUV(minVec.x, minVec.y, maxVec.z, minUV.x, maxUV.y);
		addVertUV(minVec.x, minVec.y, minVec.z, minUV.x, minUV.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, maxUV.x, maxUV.y);
	}

	public static void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(0.0f, -1.0f, 0.0f);
		addVertUV(minVec.x, minVec.y, maxVec.z, rect.min.x, rect.max.y);
		addVertUV(minVec.x, minVec.y, minVec.z, rect.min.x, rect.min.y);
		addVertUV(maxVec.x, minVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(maxVec.x, minVec.y, maxVec.z, rect.max.x, rect.max.y);
	}

	public static void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		// // setNormal(0.0f, 1.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, maxU, maxV);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, maxVec.y, minVec.z, minU, minV);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	public static void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, Vector2<Float> minUV, Vector2<Float> maxUV) {
		// setNormal(0.0f, 1.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, maxUV.x, maxUV.y);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxUV.x, minUV.y);
		addVertUV(minVec.x, maxVec.y, minVec.z, minUV.x, minUV.y);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minUV.x, maxUV.y);
	}

	public static void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, Rect<Float> rect) {
		// setNormal(0.0f, 1.0f, 0.0f);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, rect.max.x, rect.max.y);
		addVertUV(maxVec.x, maxVec.y, minVec.z, rect.max.x, rect.min.y);
		addVertUV(minVec.x, maxVec.y, minVec.z, rect.min.x, rect.min.y);
		addVertUV(minVec.x, maxVec.y, maxVec.z, rect.min.x, rect.max.y);
	}

	public static void drawCuboid(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float minV, float maxU, float maxV) {
		drawXPos(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawXNeg(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawYPos(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawYNeg(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawZPos(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
		drawZNeg(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);
	}

	public static void setupPreRenderf(ResourceLocation resource, float x, float y, float z) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xf0 % 0x10000, 0xf0 / 0x10000);
		bindTexture(resource);
	}

	public static void setupPreRenderf(ResourceLocation resource, Vector3<Float> vec) {
		GL11.glPushMatrix();
		GL11.glTranslatef(vec.x, vec.y, vec.z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xf0 % 0x10000, 0xf0 / 0x10000);
		bindTexture(resource);
	}

	public static void setupPreRender(ResourceLocation resource, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xf0 % 0x10000, 0xf0 / 0x10000);
		bindTexture(resource);
	}

	public static void setupPreRender(ResourceLocation resource, Vector3<Double> vec) {
		GL11.glPushMatrix();
		GL11.glTranslated(vec.x, vec.y, vec.z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xf0 % 0x10000, 0xf0 / 0x10000);
		bindTexture(resource);
	}

	public static void finishPostRenderf(float x, float y, float z) {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslatef(-x, -y, -z);
		GL11.glPopMatrix();
	}

	public static void finishPostRenderf(Vector3<Float> vec) {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslatef(-vec.x, -vec.y, -vec.z);
		GL11.glPopMatrix();
	}

	public static void finishPostRender(double x, double y, double z) {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-x, -y, -z);
		GL11.glPopMatrix();
	}

	public static void finishPostRender(Vector3<Double> vec) {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-vec.x, -vec.y, -vec.z);
		GL11.glPopMatrix();
	}

}
