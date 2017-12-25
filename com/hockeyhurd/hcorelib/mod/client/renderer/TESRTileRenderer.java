package com.hockeyhurd.hcorelib.mod.client.renderer;

import com.hockeyhurd.hcorelib.api.client.util.RenderHelper;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import com.hockeyhurd.hcorelib.mod.tileentity.TileEntityTESRTest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author hockeyhurd
 * @version 6/11/2016.
 */
@SideOnly(Side.CLIENT)
public final class TESRTileRenderer extends TileEntitySpecialRenderer<TileEntityTESRTest> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(HCoreLibMain.assetDir, "textures/blocks/tiletesrtest.png");

	@Override
	public void render(TileEntityTESRTest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		RenderHelper.setupPreRender(TEXTURE, x, y, z);

		drawCuboid(te, x, y, z);

		RenderHelper.finishPostRender(x, y, z);
	}

	private static void drawCuboid(TileEntityTESRTest te, double x, double y, double z) {
		RenderHelper.startDrawingQuads();

		final Vector3<Float> minVec = new Vector3<Float>(RenderHelper.DRAW_OFFSET, RenderHelper.DRAW_OFFSET, RenderHelper.DRAW_OFFSET);
		final Vector3<Float> maxVec = new Vector3<Float>(1.0f - RenderHelper.DRAW_OFFSET, 1.0f - RenderHelper.DRAW_OFFSET, 1.0f - RenderHelper.DRAW_OFFSET);

		RenderHelper.drawCuboid(minVec, maxVec, 0.0f, 0.0f, 1.0f, 1.0f);

		RenderHelper.draw();
	}

}
