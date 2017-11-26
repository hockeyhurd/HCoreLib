package com.hockeyhurd.hcorelib.api.handler.client;

import com.hockeyhurd.hcorelib.api.math.Color4f;
import com.hockeyhurd.hcorelib.api.util.BlockUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * @author hockeyhurd
 * @version 11/26/17
 */
@SideOnly(Side.CLIENT)
public class DrawBlockSelectionHandler {

    private float pulse = 0.5f;
    private boolean increasing = true;
    private static Color4f selectionBoxColor; //  = ProjectZed.configHandler.getSelectionBoxColor();

    @SubscribeEvent
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
        final ItemStack stack = event.getPlayer().inventory.getCurrentItem();
        if (stack != null && event.getTarget().typeOfHit == RayTraceResult.Type.BLOCK) {

            if (stack.getItem() instanceof IWorldDrawable) {
                drawSelectionBoxQuad((IWorldDrawable) stack.getItem(), event.getContext(), event.getPlayer(),
                        event.getTarget(), 0, event.getPartialTicks());
            }
        }
    }

    /**
     * Draws selection box quad.
     *
     * @param drawable IWorldDrawable.
     * @param context Render context.
     * @param player EntityPlayer.
     * @param rayTrace Ray trace object.
     * @param x int.
     * @param partialTicks float partial ticks.
     */
    private void drawSelectionBoxQuad(IWorldDrawable drawable, RenderGlobal context, EntityPlayer player, RayTraceResult rayTrace, int x, float partialTicks) {
        if (x == 0 && context != null && rayTrace.typeOfHit == RayTraceResult.Type.BLOCK) {
            // selectionBoxColor = ProjectZed.configHandler.getSelectionBoxColor();
            selectionBoxColor = drawable.getColor();

            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);

            GL11.glColor4f(1.0f, 1.0f, 1.0f, getNextPulse());

            GL11.glLineWidth(5.0f);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(false);

            final float offsetY = 0.002f;
            // final int radii = ((IItemAdjustableRadii) player.inventory.getCurrentItem().getItem()).getRadii();
            // final int radii = getRadiiFromPlayer(player);
            final int radii = drawable.getRadii();
            double xp, yp, zp;

            final IBlockState blockLookingAt = BlockUtils.getBlock(player.worldObj, rayTrace.getBlockPos());
            IBlockState currentBlock;
            BlockPos blockPos;

            for (int i = -radii; i <= radii; i++) {
                for (int j = -radii; j <= radii; j++) {

                    if (rayTrace.sideHit == EnumFacing.DOWN || rayTrace.sideHit == EnumFacing.UP) {
                        blockPos = BlockUtils.createBlockPos(rayTrace.getBlockPos().getX() + i,
                                rayTrace.getBlockPos().getY(), rayTrace.getBlockPos().getZ() + j);

                        currentBlock = BlockUtils.getBlock(player.worldObj, blockPos);

                        if (currentBlock.getBlock() == Blocks.AIR || blockLookingAt.getBlock() != currentBlock.getBlock())
                            continue;

                        AxisAlignedBB boundingBox = new AxisAlignedBB(blockPos);

                        xp = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
                        yp = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
                        zp = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

                        RenderGlobal.drawSelectionBoundingBox(boundingBox.expand((double) offsetY, (double) offsetY, (double) offsetY).offset(-xp, -yp, -zp),
                                selectionBoxColor.getR(), selectionBoxColor.getG(), selectionBoxColor.getB(), getNextPulse());
                    }

                    else if (rayTrace.sideHit == EnumFacing.NORTH || rayTrace.sideHit == EnumFacing.SOUTH) {
                        blockPos = BlockUtils
                                .createBlockPos(rayTrace.getBlockPos().getX() + i, rayTrace.getBlockPos().getY() + j, rayTrace.getBlockPos().getZ());
                        currentBlock = BlockUtils.getBlock(player.worldObj, blockPos);

                        if (currentBlock.getBlock() == Blocks.AIR || blockLookingAt.getBlock() != currentBlock.getBlock())
                            continue;

                        AxisAlignedBB boundingBox = new AxisAlignedBB(blockPos);

                        xp = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
                        yp = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
                        zp = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

                        RenderGlobal.drawSelectionBoundingBox(boundingBox.expand((double) offsetY, (double) offsetY, (double) offsetY).offset(-xp, -yp, -zp),
                                selectionBoxColor.getR(), selectionBoxColor.getG(), selectionBoxColor.getB(), getNextPulse());
                    }

                    else {
                        blockPos = BlockUtils
                                .createBlockPos(rayTrace.getBlockPos().getX(), rayTrace.getBlockPos().getY() + j, rayTrace.getBlockPos().getZ() + i);
                        currentBlock = BlockUtils.getBlock(player.worldObj, blockPos);

                        if (currentBlock.getBlock() == Blocks.AIR || blockLookingAt.getBlock() != currentBlock.getBlock())
                            continue;

                        AxisAlignedBB boundingBox = new AxisAlignedBB(blockPos);

                        xp = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
                        yp = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
                        zp = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

                        RenderGlobal.drawSelectionBoundingBox(boundingBox.expand((double) offsetY, (double) offsetY, (double) offsetY).offset(-xp, -yp, -zp),
                                selectionBoxColor.getR(), selectionBoxColor.getG(), selectionBoxColor.getB(), getNextPulse());
                    }
                }
            }

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    /**
     * Adjusts and returns new pulse value.
     *
     * @return float.
     */
    private float getNextPulse() {
        if (pulse >= 0.75f)
            increasing = false;
        else if (pulse <= 0.25f)
            increasing = true;

        pulse += increasing ? (0.0015625f) : (-0.0015625f);

        return pulse;
        // return 1.0f;
    }

}
