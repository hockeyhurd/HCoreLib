package com.hockeyhurd.api.util;

import com.hockeyhurd.api.math.*;
import com.hockeyhurd.mod.HCoreLibMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import org.lwjgl.opengl.GL11;

/**
 * Class used as helper for various tessllations with generic methods often used
 * with lwjgl based graphical renderings. 
 * <br><bold>NOTE: </bold>There is no need to create own Tessellator instance as I provide this constant.
 * <br>@see {@link com.hockeyhurd.api.util.TessellatorHelper#tess}
 * 
 * @author hockeyhurd
 * @version Oct 2, 2014
 */
@SideOnly(Side.CLIENT)
public class TessellatorHelper {

	private IIcon icon;
	public final Tessellator tess;
	private boolean testFlagColour = false;

	/**
	 * Default constructor.
	 */
	public TessellatorHelper() {
		this(null);
	}
	
	/**
	 * Constructor used to initialize an icon and new instance of this class.
	 * 
	 * @param icon icon to use.
	 */
	public TessellatorHelper(IIcon icon) {
		this.icon = icon;
		tess = Tessellator.instance;
	}
	
	/**
	 * Method used to draw generic cube/block with another smaller cube/block inside
	 * itself through given parameters.
	 * 
	 * @param x position x.
	 * @param y position y.
	 * @param z position z.
	 * @param scale scale of block inside (defaults to 0.9d if invalid).
	 * @param color 
	 */
	public void drawCuboid(float x, float y, float z, double scale, Color4i color) {
		// change the passed integer coordinates into double precision floats, and set the height on the y axis
		double xx = (double) x;
		double yy = (double) y + 0.075d;
		double zz = (double) z;

		// this is the scale of the squares, in blocks
		double scale_copy = scale > 0 && scale < 1 ?  scale : 0.9d;

		// set the uv coordinates
		double minU = icon.getMinU();
		double maxU = icon.getMaxU(); // var11.getMinV();
		double minV = icon.getMinV(); // var11.getMaxU();
		double maxV = icon.getMaxV();

		// here the scale is changed
		double adjustScale = 0.45D * (double) scale_copy;

		// offset the vertices from the centre of the block
		double xxMin = xx + 0.5D - adjustScale;
		double xxMax = xx + 0.5D + adjustScale;
		double zzMin = zz + 0.5D - adjustScale;
		double zzMax = zz + 0.5D + adjustScale;

		// not create the vertices
		/*
		 * tess.addVertexWithUV(uuMin, yy + (double) scale, vvMin, minU, minV); tess.addVertexWithUV(uuMin, yy + 0.0D, vvMin, minU, maxV); tess.addVertexWithUV(uuMax, yy + 0.0D, vvMax, maxY, maxV); tess.addVertexWithUV(uuMax, yy + (double) scale, vvMax, maxY, minV); tess.addVertexWithUV(uuMax, yy +
		 * (double) scale, vvMax, minU, minV); tess.addVertexWithUV(uuMax, yy + 0.0D, vvMax, minU, maxV); tess.addVertexWithUV(uuMin, yy + 0.0D, vvMin, maxY, maxV); tess.addVertexWithUV(uuMin, yy + (double) scale, vvMin, maxY, minV); tess.addVertexWithUV(uuMin, yy + (double) scale, vvMax, minU,
		 * minV); tess.addVertexWithUV(uuMin, yy + 0.0D, vvMax, minU, maxV); tess.addVertexWithUV(uuMax, yy + 0.0D, vvMin, maxY, maxV); tess.addVertexWithUV(uuMax, yy + (double) scale, vvMin, maxY, minV); tess.addVertexWithUV(uuMax, yy + (double) scale, vvMin, minU, minV);
		 * tess.addVertexWithUV(uuMax, yy + 0.0D, vvMin, minU, maxV); tess.addVertexWithUV(uuMin, yy + 0.0D, vvMax, maxY, maxV); tess.addVertexWithUV(uuMin, yy + (double) scale, vvMax, maxY, minV);
		 */

		if (color != null) tess.setColorOpaque((int) color.getR(), (int)color.getG(), (int)color.getB());
		// tess.setNormal(0.0F, 1.0F, 0.0F);
		// -zz
		tess.addVertexWithUV(xxMin, yy + scale_copy, zzMax, minU, minV);
		tess.addVertexWithUV(xxMin, yy + 0.0D, zzMax, minU, maxV);
		tess.addVertexWithUV(xxMax, yy + 0.0D, zzMax, maxU, maxV);
		tess.addVertexWithUV(xxMax, yy + scale_copy, zzMax, maxU, minV);

		// -zz
		tess.addVertexWithUV(xxMax, yy + scale_copy, zzMin, minU, minV);
		tess.addVertexWithUV(xxMax, yy + 0.0D, zzMin, minU, maxV);
		tess.addVertexWithUV(xxMin, yy + 0.0D, zzMin, maxU, maxV);
		tess.addVertexWithUV(xxMin, yy + scale_copy, zzMin, maxU, minV);

		// -xx
		tess.addVertexWithUV(xxMin, yy + scale_copy, zzMin, minU, minV);
		tess.addVertexWithUV(xxMin, yy + 0.0D, zzMin, minU, maxV);
		tess.addVertexWithUV(xxMin, yy + 0.0D, zzMax, maxU, maxV);
		tess.addVertexWithUV(xxMin, yy + scale_copy, zzMax, maxU, minV);

		// +xx
		tess.addVertexWithUV(xxMax, yy + scale_copy, zzMax, minU, minV);
		tess.addVertexWithUV(xxMax, yy + 0.0D, zzMax, minU, maxV);
		tess.addVertexWithUV(xxMax, yy + 0.0D, zzMin, maxU, maxV);
		tess.addVertexWithUV(xxMax, yy + scale_copy, zzMin, maxU, minV);

		// +yy
		tess.addVertexWithUV(xxMin, yy + scale_copy - 0.01d, zzMin, minU, minV);
		tess.addVertexWithUV(xxMin, yy + scale_copy - 0.01d, zzMax, minU, maxV);
		tess.addVertexWithUV(xxMax, yy + scale_copy - 0.01d, zzMax, maxU, maxV);
		tess.addVertexWithUV(xxMax, yy + scale_copy - 0.01d, zzMin, maxU, minV);

		// -yy
		tess.addVertexWithUV(xxMin, yy + 0.0d, zzMin, minU, minV);
		tess.addVertexWithUV(xxMax, yy + 0.0d, zzMin, minU, maxV);
		tess.addVertexWithUV(xxMax, yy + 0.0d, zzMax, maxU, maxV);
		tess.addVertexWithUV(xxMin, yy + 0.0d, zzMax, maxU, minV);
	}
	
	/**
	 * Method used for rendering block as item.
	 * Tweaked method based from open source work by @author TheGreyGhost which can be found below
	 * @see <a href="https://github.com/TheGreyGhost/ItemRendering/blob/master/src/TestItemRendering/blocks/ItemBlockNumberedFaces1Renderer.java">github.com/../ItemBlockNumberedFaces1Renderer.java</a>.
	 * 
	 * @param type type of rendering, inventory, entity item, etc.
	 * @param item copy of itemstack.
	 * @param generalizeNormals true set all sides as normals, else individual.
	 */
	public void renderItem(ItemRenderType type, ItemStack item, boolean generalizeNormals) {
		
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		
		tess.startDrawingQuads();

		// adjust rendering space to match what caller expects
		boolean mustTranslate = false;
		switch (type) {
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON: {
				break; // caller expects us to render over [0,0,0] to [1,1,1], no translation necessary
			}
			case ENTITY:
			case INVENTORY: {
				// translate our coordinates so that [0,0,0] to [1,1,1] translates to the [-0.5, -0.5, -0.5] to [0.5, 0.5, 0.5] expected by the caller.
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				mustTranslate = true;   // must undo the translation when we're finished rendering
				break;
			}
			default:
				break; // never here
		}

		// xpos face blue
		IIcon icon = item.getItem().getIconFromDamage(5);
		if (generalizeNormals) tess.setNormal(0.0F, 1.0F, 0.0F);
		else if (!generalizeNormals) tess.setNormal(1.0F, 0.0F, 0.0F);
		if (testFlagColour) tess.setColorOpaque(0, 0, 255);
		tess.addVertexWithUV(1.0, 0.0, 0.0, (double) icon.getMaxU(), (double) icon.getMaxV());
		tess.addVertexWithUV(1.0, 1.0, 0.0, (double) icon.getMaxU(), (double) icon.getMinV());
		tess.addVertexWithUV(1.0, 1.0, 1.0, (double) icon.getMinU(), (double) icon.getMinV());
		tess.addVertexWithUV(1.0, 0.0, 1.0, (double) icon.getMinU(), (double) icon.getMaxV());

		// xneg face purple
		icon = item.getItem().getIconFromDamage(4);
		if (!generalizeNormals) tess.setNormal(-1.0F, 0.0F, 0.0F);
		if (testFlagColour) tess.setColorOpaque(255, 0, 255);
		tess.addVertexWithUV(0.0, 0.0, 1.0, (double) icon.getMaxU(), (double) icon.getMaxV());
		tess.addVertexWithUV(0.0, 1.0, 1.0, (double) icon.getMaxU(), (double) icon.getMinV());
		tess.addVertexWithUV(0.0, 1.0, 0.0, (double) icon.getMinU(), (double) icon.getMinV());
		tess.addVertexWithUV(0.0, 0.0, 0.0, (double) icon.getMinU(), (double) icon.getMaxV());

		// zneg face white
		icon = item.getItem().getIconFromDamage(2);
		if (!generalizeNormals) tess.setNormal(0.0F, 0.0F, -1.0F);
		if (testFlagColour) tess.setColorOpaque(255, 255, 255);
		tess.addVertexWithUV(0.0, 0.0, 0.0, (double) icon.getMaxU(), (double) icon.getMaxV());
		tess.addVertexWithUV(0.0, 1.0, 0.0, (double) icon.getMaxU(), (double) icon.getMinV());
		tess.addVertexWithUV(1.0, 1.0, 0.0, (double) icon.getMinU(), (double) icon.getMinV());
		tess.addVertexWithUV(1.0, 0.0, 0.0, (double) icon.getMinU(), (double) icon.getMaxV());

		// zpos face green
		icon = item.getItem().getIconFromDamage(3);
		if (!generalizeNormals) tess.setNormal(0.0F, 0.0F, -1.0F);
		if (testFlagColour) tess.setColorOpaque(0, 255, 0);
		tess.addVertexWithUV(1.0, 0.0, 1.0, (double) icon.getMaxU(), (double) icon.getMaxV());
		tess.addVertexWithUV(1.0, 1.0, 1.0, (double) icon.getMaxU(), (double) icon.getMinV());
		tess.addVertexWithUV(0.0, 1.0, 1.0, (double) icon.getMinU(), (double) icon.getMinV());
		tess.addVertexWithUV(0.0, 0.0, 1.0, (double) icon.getMinU(), (double) icon.getMaxV());

		// ypos face red
		icon = item.getItem().getIconFromDamage(1);
		if (!generalizeNormals) tess.setNormal(0.0F, 1.0F, 0.0F);
		if (testFlagColour) tess.setColorOpaque(255, 0, 0);
		tess.addVertexWithUV(1.0, 1.0, 1.0, (double) icon.getMaxU(), (double) icon.getMaxV());
		tess.addVertexWithUV(1.0, 1.0, 0.0, (double) icon.getMaxU(), (double) icon.getMinV());
		tess.addVertexWithUV(0.0, 1.0, 0.0, (double) icon.getMinU(), (double) icon.getMinV());
		tess.addVertexWithUV(0.0, 1.0, 1.0, (double) icon.getMinU(), (double) icon.getMaxV());

		// yneg face yellow
		icon = item.getItem().getIconFromDamage(0);
		if (!generalizeNormals) tess.setNormal(0.0F, -1.0F, 0.0F);
		if (testFlagColour) tess.setColorOpaque(255, 255, 0);
		tess.addVertexWithUV(0.0, 0.0, 1.0, (double) icon.getMaxU(), (double) icon.getMaxV());
		tess.addVertexWithUV(0.0, 0.0, 0.0, (double) icon.getMaxU(), (double) icon.getMinV());
		tess.addVertexWithUV(1.0, 0.0, 0.0, (double) icon.getMinU(), (double) icon.getMinV());
		tess.addVertexWithUV(1.0, 0.0, 1.0, (double) icon.getMinU(), (double) icon.getMaxV());
		tess.draw();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		if (mustTranslate) GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	/**
	 * Draws icon along z- side of typical block.
	 * 
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		drawZNeg(this.icon, minVec, maxVec, renderInside);
	}
	
	/**
	 * Draws icon along z- side of typical block.
	 * 
	 * @param icon icon to draw.
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawZNeg(IIcon icon, Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		tess.setNormal(0.0f, 0.0f, -1.0f);
		tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
		tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, icon.getMaxU(), icon.getMaxV());
		tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, icon.getMinU(), icon.getMaxV());
		tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, icon.getMinU(), icon.getMinV());
		
		if (renderInside) {
			tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, icon.getMinU(), icon.getMinV());
			tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, icon.getMinU(), icon.getMaxV());
			tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, icon.getMaxU(), icon.getMaxV());
			tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
		}
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
	 * @deprecated as of 8/7/15, use {@link TessellatorHelper#drawZNeg(com.hockeyhurd.api.math.Vector3, com.hockeyhurd.api.math.Vector3, float, float, float, float) instead.}
	 */
	@Deprecated
	public void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(0.0f, 0.0f, -1.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, min - difU, min - difV);
		tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, min - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, max - difU, min - difV);

		if (renderInside) {
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
	public void drawZNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(0.0f, 0.0f, -1.0f);

		addVertUV(minVec.x, maxVec.y, minVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, minVec.z, minU, minV);
		addVertUV(maxVec.x, maxVec.y, minVec.z, minU, maxV);
	}

	/**
	 * Draws icon along z+ side of typical block.
	 * 
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		drawZPos(this.icon, minVec, maxVec, renderInside);
	}
	
	/**
	 * Draws icon along z+ side of typical block.
	 * 
	 * @param icon icon to draw.
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawZPos(IIcon icon, Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		tess.setNormal(0.0f, 0.0f, 1.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, icon.getMinU(), icon.getMinV());
		tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
		tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, icon.getMaxU(), icon.getMaxV());
		tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, icon.getMaxU(), icon.getMinV());
		
		if (renderInside) {
			tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, icon.getMaxU(), icon.getMinV());
			tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, icon.getMaxU(), icon.getMaxV());
			tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
			tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, icon.getMinU(), icon.getMinV());
		}
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
	 * @deprecated as of 8/7/15 use {@link TessellatorHelper#drawZPos(com.hockeyhurd.api.math.Vector3, com.hockeyhurd.api.math.Vector3, float, float, float, float)} instead.
	 */
	@Deprecated
	public void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(0.0f, 0.0f, 1.0f);
		tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, min - difU, min - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, min - difU, max - difV);
		tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, max - difU, max - difV);
		tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, max - difU, min - difV);

		if (renderInside) {
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
	public void drawZPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(0.0f, 0.0f, 1.0f);

		addVertUV(minVec.x, maxVec.y, maxVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, maxVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minU, maxV);
	}
	
	/**
	 * Draws icon along x- side of typical block.
	 * 
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		drawXNeg(this.icon, minVec, maxVec, renderInside);
	}
	
	/**
	 * Draws icon along x- side of typical block.
	 * 
	 * @param icon icon to draw.
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawXNeg(IIcon icon, Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		tess.setNormal(-1.0f, 0.0f, 0.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
		tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, icon.getMaxU(), icon.getMaxV());
		tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
		tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, icon.getMinU(), icon.getMinV());
		
		if (renderInside) {
			tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, icon.getMinU(), icon.getMinV());
			tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
			tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, icon.getMaxU(), icon.getMaxV());
			tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
		}
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
	 * @deprecated  as of 8/7/15, use {@link TessellatorHelper#drawXNeg(com.hockeyhurd.api.math.Vector3, com.hockeyhurd.api.math.Vector3, float, float, float, float)} instead.
	 */
	@Deprecated
	public void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(-1.0f, 0.0f, 0.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, min - difU, min - difV);
		tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, min - difU, max - difV);
		tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, max - difU, min - difV);

		if (renderInside) {
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
	public void drawXNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(-1.0f, 0.0f, 0.0f);

		addVertUV(minVec.x, maxVec.y, minVec.z, maxU, maxV);
		addVertUV(minVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Draws icon along x+ side of typical block.
	 * 
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		drawXPos(this.icon, minVec, maxVec, renderInside);
	}
	
	/**
	 * Draws icon along x+ side of typical block.
	 * 
	 * @param icon icon to draw.
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawXPos(IIcon icon, Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		tess.setNormal(1.0f, 0.0f, 0.0f);
		tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, icon.getMinU(), icon.getMinV());
		tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
		tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, icon.getMaxU(), icon.getMaxV());
		tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
		
		if (renderInside) {
			tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
			tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, icon.getMaxU(), icon.getMaxV());
			tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
			tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, icon.getMinU(), icon.getMinV());
		}
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
	 * @deprecated as of 8/7/15, use {@link TessellatorHelper#drawXPos(com.hockeyhurd.api.math.Vector3, com.hockeyhurd.api.math.Vector3, float, float, float, float)} instead.
	 */
	@Deprecated
	public void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(1.0f, 0.0f, 0.0f);
		tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, max - difU, min - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, min - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, min - difU, min - difV);

		if (renderInside) {
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
	public void drawXPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(1.0f, 0.0f, 0.0f);

		addVertUV(maxVec.x, maxVec.y, minVec.z, maxU, maxV);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(maxVec.x, minVec.y, maxVec.z, minU, minV);
		addVertUV(maxVec.x, maxVec.y, maxVec.z, minU, maxV);
	}
	
	/**
	 * Draws icon along y- side of typical block.
	 * 
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		drawYNeg(this.icon, minVec, maxVec, renderInside);
	}
	
	/**
	 * Draws icon along y- side of typical block.
	 * 
	 * @param icon icon to draw.
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawYNeg(IIcon icon, Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		tess.setNormal(0.0f, -1.0f, 0.0f);
		tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, icon.getMinU(), icon.getMinV());
		tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
		tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, icon.getMaxU(), icon.getMaxV());
		tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
		
		if (renderInside) {
			tess.addVertexWithUV(minVec.x, minVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
			tess.addVertexWithUV(minVec.x, minVec.y, maxVec.z, icon.getMaxU(), icon.getMaxV());
			tess.addVertexWithUV(maxVec.x, minVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
			tess.addVertexWithUV(maxVec.x, minVec.y, minVec.z, icon.getMinU(), icon.getMinV());
		}
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
	 * @deprecated as of 8/7/15, use {@link TessellatorHelper#drawYNeg(com.hockeyhurd.api.math.Vector3, com.hockeyhurd.api.math.Vector3, float, float, float, float)} instead.
	 */
	@Deprecated
	public void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(0.0f, -1.0f, 0.0f);
		tess.addVertexWithUV(maxVec.x, minVec.y - 0.01d, minVec.z, max - difU, min - difV);
		tess.addVertexWithUV(maxVec.x, minVec.y - 0.01d, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(minVec.x, minVec.y - 0.01d, maxVec.z, min - difU, max - difV);
		tess.addVertexWithUV(minVec.x, minVec.y - 0.01d, minVec.z, min - difU, min - difV);

		if (renderInside) {
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
	public void drawYNeg(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(0.0f, -1.0f, 0.0f);

		addVertUV(maxVec.x, minVec.y, maxVec.z, maxU, maxV);
		addVertUV(maxVec.x, minVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, minVec.y, minVec.z, minU, minV);
		addVertUV(minVec.x, minVec.y, maxVec.z, minU, maxV);
	}
	
	/**
	 * Draws icon along y+ side of typical block.
	 *
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		drawYPos(this.icon, minVec, maxVec, renderInside);
	}
	
	/**
	 * Draws icon along y+ side of typical block.
	 * 
	 * @param icon icon to draw.
	 * @param minVec minimum vector coordinate to draw.
	 * @param maxVec maximum vector coordinate to draw.
	 * @param renderInside flag whether to also draw inside.
	 */
	public void drawYPos(IIcon icon, Vector3<Float> minVec, Vector3<Float> maxVec, boolean renderInside) {
		tess.setNormal(0.0f, 1.0f, 0.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
		tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, icon.getMaxU(), icon.getMaxV());
		tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
		tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, icon.getMinU(), icon.getMinV());
		
		if (renderInside) {
			tess.addVertexWithUV(maxVec.x, maxVec.y, minVec.z, icon.getMinU(), icon.getMinV());
			tess.addVertexWithUV(maxVec.x, maxVec.y, maxVec.z, icon.getMinU(), icon.getMaxV());
			tess.addVertexWithUV(minVec.x, maxVec.y, maxVec.z, icon.getMaxU(), icon.getMaxV());
			tess.addVertexWithUV(minVec.x, maxVec.y, minVec.z, icon.getMaxU(), icon.getMinV());
		}
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
	 * @deprecated as of 8/7/15, use {@link TessellatorHelper#drawYPos(com.hockeyhurd.api.math.Vector3, com.hockeyhurd.api.math.Vector3, float, float, float, float)} instead.
	 */
	public void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, float min, float max, float difU, float difV, boolean renderInside) {
		tess.setNormal(0.0f, 1.0f, 0.0f);
		tess.addVertexWithUV(minVec.x, maxVec.y - 0.01d, minVec.z, min - difU, min - difV);
		tess.addVertexWithUV(minVec.x, maxVec.y - 0.01d, maxVec.z, min - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, maxVec.y - 0.01d, maxVec.z, max - difU, max - difV);
		tess.addVertexWithUV(maxVec.x, maxVec.y - 0.01d, minVec.z, max - difU, min - difV);

		if (renderInside) {
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
	public void drawYPos(Vector3<Float> minVec, Vector3<Float> maxVec, float minU, float maxU, float minV, float maxV) {
		setNormal(0.0f, 1.0f, 0.0f);

		addVertUV(maxVec.x, maxVec.y, maxVec.z, maxU, maxV);
		addVertUV(maxVec.x, maxVec.y, minVec.z, maxU, minV);
		addVertUV(minVec.x, maxVec.y, minVec.z, minU, minV);
		addVertUV(minVec.x, maxVec.y, maxVec.z, minU, maxV);
	}

	/**
	 * Sets normal by specified values.
	 *
	 * @param x x face.
	 * @param y y face.
	 * @param z z face.
	 */
	public void setNormal(float x, float y, float z) {
		if (LogicHelper.isNumberInRange(x, -1f, 1f) && LogicHelper.isNumberInRange(y, -1f, 1f) && LogicHelper.isNumberInRange(z, -1f, 1f))
			tess.setNormal(x, y, z);
	}

	/**
	 * Sets normal by specified vector values.
	 *
	 * @param vec vector representation of faces.
	 */
	public void setNormal(Vector3<Float> vec) {
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
	public void addVertUV(float x, float y, float z, float u, float v) {
		tess.addVertexWithUV(x, y, z, u, v);
	}

	/**
	 * Adds vertex with uv.
	 *
	 * @param vec vector position.
	 * @param u u.
	 * @param v v.
	 */
	public void addVertUV(Vector3<Float> vec, float u, float v) {
		tess.addVertexWithUV(vec.x, vec.y, vec.z, u, v);
	}

	/**
	 * Adds vertex with uv.
	 *
	 * @param vec vector position.
	 * @param uv uv vector.
	 */
	public void addVertUV(Vector3<Float> vec, Vector2<Float> uv) {
		tess.addVertexWithUV(vec.x, vec.y, vec.z, uv.x, uv.y);
	}

	/**
	 * Adds vertex.
	 *
	 * @param x x-pos.
	 * @param y y-pos.
	 * @param z z-pos.
	 */
	public void addVert(float x, float y, float z) {
		tess.addVertex(x, y, z);
	}

	/**
	 * Adds vertex.
	 *
	 * @param vec vector position.
	 */
	public void addVert(Vector3<Float> vec) {
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
	public void setColorRGBA(int r, int g, int b, int a) {
		tess.setColorRGBA(r, g, b, a);
	}

	/**
	 * Sets color RGBA.
	 *
	 * @param color Color4i.
	 */
	public void setColorRGBA(Color4i color) {
		tess.setColorRGBA(color.getR(), color.getG(), color.getB(), color.getA());
	}

	/**
	 * Sets color RGBA.
	 *
	 * @param hex hex color value.
	 */
	public void setColorRGBA(int hex) {
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
	public void setColorRGBA(float r, float g, float b, float a) {
		tess.setColorRGBA_F(r, g, b, a);
	}

	/**
	 * Sets color RGBA.
	 *
	 * @param color Color4f.
	 */
	public void setColorRGBA(Color4f color) {
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
	public void drawFace(Matrix4f matrix, float minU, float maxU, float minV, float maxV) {
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
	public void drawFace(Matrix4f matrix, Vector3<Float> normalVec, float minU, float maxU, float minV, float maxV) {
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
	public void startDrawingQuads() {
		tess.startDrawingQuads();
	}

	/**
	 * Starts drawing face with 'n' number of vertices where 'n' is a positive integer >= 3.
	 *
	 * @param verts number of vertices to start drawing.
	 */
	public void startDrawing(int verts) {
		if (verts > 2) tess.startDrawing(verts);
		else HCoreLibMain.lh.severe("Error starting to draw with #", verts, "ensure this is (int) 'verts' >= '3'!");
	}

	/**
	 * Teslls tessellator to draw/push drawing calls of established vertices.
	 */
	public void draw() {
		tess.draw();
	}

}
