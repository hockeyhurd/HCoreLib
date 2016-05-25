package com.hockeyhurd.hcorelib.api.client.gui;

import com.hockeyhurd.hcorelib.api.math.Color4f;
import com.hockeyhurd.hcorelib.api.math.Rect;
import com.hockeyhurd.hcorelib.api.math.Vector2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Gui helper class.
 *
 * @author hockeyhurd
 * @version 5/25/2016.
 */
@SideOnly(Side.CLIENT)
public final class GuiHelper {

	private static final Minecraft minecraft = Minecraft.getMinecraft();
	private static final TextureManager textureManager = minecraft.getTextureManager();

	private GuiHelper() {
	}

	/**
	 * Binds resourece to texture manager.
	 *
	 * @param resourceLocation ResourceLocation.
	 * @return boolean result.
	 */
	public static boolean bindTexture(ResourceLocation resourceLocation) {
		if (resourceLocation == null) return false;

		textureManager.bindTexture(resourceLocation);
		return true;
	}

	/**
	 * Handles commong initialization of GlStateManager.
	 *
	 * @see net.minecraft.client.renderer.GlStateManager
	 *
	 * @param resourceLocation ResourceLocation.
	 * @param r red.
	 * @param g green.
	 * @param b blue.
	 * @param a alpha.
	 */
	public static void preRenderInit(ResourceLocation resourceLocation, float r, float g, float b, float a) {
		if (!bindTexture(resourceLocation)) return;

		GlStateManager.color(r, g, b, a);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
				GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	}

	/**
	 * Handles commong initialization of GlStateManager.
	 *
	 * @see net.minecraft.client.renderer.GlStateManager
	 *
	 * @param resourceLocation ResourceLocation.
	 * @param color4f Color4f color.
	 */
	public static void preRenderInit(ResourceLocation resourceLocation, Color4f color4f) {
		if (!bindTexture(resourceLocation) || color4f == null) return;

		GlStateManager.color(color4f.getR(), color4f.getG(), color4f.getB(), color4f.getA());
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
				GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	}

	/**
	 * Attempts to draw model rectangle.
	 *
	 * @param gui Gui reference.
	 * @param xp x-position.
	 * @param yp y-position.
	 * @param minU int.
	 * @param minV int.
	 * @param maxU int.
	 * @param maxV int.
	 */
	public static void drawModelRect(Gui gui, int xp, int yp, int minU, int minV, int maxU, int maxV) {
		gui.drawTexturedModalRect(xp, yp, minU, minV, maxU, maxV);
	}

	/**
	 * Attempts to draw model rectangle.
	 *
	 * @param gui Gui reference.
	 * @param vec Vector2i position.
	 * @param rect Rectangle to render.
	 */
	public static void drawModelRect(Gui gui, Vector2<Integer> vec, Rect<Integer> rect) {
		gui.drawTexturedModalRect(vec.x, vec.y, rect.min.x, rect.min.y, rect.max.x, rect.max.y);
	}

	/**
	 * Handles generic set-up of GlStateManager and renders model rectangle.
	 *
	 * @see net.minecraft.client.renderer.GlStateManager
	 *
	 * @param gui Gui reference.
	 * @param resourceLocation ResourceLocation.
	 * @param color4f Color4f color.
	 * @param xp x-position.
	 * @param yp y-position.
	 * @param minU int.
	 * @param minV int.
	 * @param maxU int.
	 * @param maxV int.
	 */
	public static void simpleRenderGui(Gui gui, ResourceLocation resourceLocation, Color4f color4f, int xp, int yp,
			int minU, int minV, int maxU, int maxV) {

		preRenderInit(resourceLocation, color4f);
		drawModelRect(gui, xp, yp, minU, minV, maxU, maxV);
	}

	/**
	 * Handles generic set-up of GlStateManager and renders model rectangle.
	 *
	 * @see net.minecraft.client.renderer.GlStateManager
	 *
	 * @param gui Gui reference.
	 * @param resourceLocation ResourceLocation.
	 * @param color4f Color4f color.
	 * @param vec Vector2i positon.
	 * @param rect Rectangle to render.
	 */
	public static void simpleRenderGui(Gui gui, ResourceLocation resourceLocation, Color4f color4f, Vector2<Integer> vec, Rect<Integer> rect) {
		preRenderInit(resourceLocation, color4f);
		drawModelRect(gui, vec, rect);
	}

}
