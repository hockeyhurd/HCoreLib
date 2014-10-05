package com.hockeyhurd.api.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

/**
 * Class used as helper for various tessllations with generic methods often used
 * with lwjgl based graphical renderings. 
 * 
 * @author hockeyhurd
 * @version Oct 2, 2014
 */
public class TessellatorHelper {

	private IIcon icon;
	private final Tessellator tess = Tessellator.instance;
	private boolean testFlagColour = false;
	
	/**
	 * Default constructor used to initialize an icon and new instance of this class.
	 * @param icon = icon to use.
	 */
	public TessellatorHelper(IIcon icon) {
		this.icon = icon;
	}
	
	/**
	 * Method used to draw generic cube/block with another smaller cube/block inside
	 * itself through given parameters.
	 * 
	 * @param block = block to draw (inside). 
	 * @param x = position x.
	 * @param y = position y.
	 * @param z = position z.
	 * @param scale = scale of block inside (defaults to 0.9d if invalid).
	 */
	public void drawCuboid(float x, float y, float z, double scale) {
		// change the passed integer coordinates into double precision floats, and set the height on the y axis
		double xx = (double) x;
		double yy = (double) y + 0.1d;
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
	 * @param type = type of rendering, inventory, entity item, etc.
	 * @param item = copy of itemstack.
	 * @param generalizeNormals = true set all sides as normals, else individual.
	 */
	public void renderItem(ItemRenderType type, ItemStack item, boolean generalizeNormals) {
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
		
		if (mustTranslate) GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

}
