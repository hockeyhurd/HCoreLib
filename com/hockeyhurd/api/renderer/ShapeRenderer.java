package com.hockeyhurd.api.renderer;

import com.hockeyhurd.api.math.Vector3;
import com.hockeyhurd.api.util.Color;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;

/**
 * Shape renderer class.
 *
 * @author hockeyhurd
 * @version 12/26/2015.
 */
@SideOnly(Side.CLIENT)
public class ShapeRenderer implements IWorldRenderable {

	private final EnumShape enumShape;
	private final World world;
	private final Vector3<Integer> worldVec;
	private final Vector3<Integer> offset;
	private final Color color;

	/**
	 * Creates ShapeRenderer.
	 *
	 * @param enumShape shape to render.
	 * @param world world to render in.
	 * @param worldVec world vec to render at.
	 * @param offset offsetting vec.
	 * @param color color of shape to render.
	 */
	public ShapeRenderer(EnumShape enumShape, World world, Vector3<Integer> worldVec, Vector3<Integer> offset, Color color) {
		this.enumShape = enumShape;
		this.world = world;
		this.worldVec = worldVec;
		this.offset = offset;
		this.color = color;
	}

	@Override
	public void render(RenderWorldLastEvent event) {
		// ShapeRendererUtils.renderShape(enumShape, world, worldVec, offset, color);
		final EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		final Vector3<Integer> playerVec = new Vector3<Double>(player.posX + 3.0d, player.posY, player.posZ + 3.0d).getVector3i();
		ShapeRendererUtils.renderShape(enumShape, world, playerVec, offset, color);
	}

}
