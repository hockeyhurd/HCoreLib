package com.hockeyhurd.api.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class TessellatorHelper {

	private IIcon icon;
	
	public TessellatorHelper(IIcon icon) {
		this.icon = icon;
	}
	
	public void drawCuboid(Block block, int x, int y, int z, double scale) {
		// change the passed integer coordinates into double precision floats, and set the height on the y axis
		double xx = (double) x;
		double yy = (double) y + 0.1d;
		double zz = (double) z;

		// this is the scale of the squares, in blocks
		double scale_copy = scale > 0 && scale < 1 ?  scale : 0.9d;
		
		// Create Tessellator instance.
		Tessellator tess = Tessellator.instance;

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

}
