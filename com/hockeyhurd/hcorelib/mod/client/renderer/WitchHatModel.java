package com.hockeyhurd.hcorelib.mod.client.renderer;

/**
 * @author hockeyhurd
 * @version 9/3/17
 */
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Witch by Unknown
 */
@SideOnly(Side.CLIENT)
public final class WitchHatModel extends ModelBase {

    public ModelRenderer witchHat;
    public ModelRenderer witchHat_0;
    public ModelRenderer witchHat_0_0;
    public ModelRenderer witchHat_0_0_0;

    public WitchHatModel() {
        this.textureWidth = 64;
        this.textureHeight = 128;

        this.witchHat = new ModelRenderer(this, 0, 64);
        this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        this.witchHat.addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
        this.witchHat_0 = new ModelRenderer(this, 0, 76);
        this.witchHat_0.setRotationPoint(1.75F, -4.0F, 2.0F);
        this.witchHat_0.addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
        this.setRotationAngles(this.witchHat_0, -0.05235987755982988F, 0.0F, 0.02617993877991494F);
        this.witchHat.addChild(this.witchHat_0);
        this.witchHat_0_0 = new ModelRenderer(this, 0, 87);
        this.witchHat_0_0.setRotationPoint(1.75F, -4.0F, 2.0F);
        this.witchHat_0_0.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        this.setRotationAngles(this.witchHat_0_0, -0.10471975511965977F, 0.0F, 0.05235987755982988F);
        this.witchHat_0.addChild(this.witchHat_0_0);
        this.witchHat_0_0_0 = new ModelRenderer(this, 0, 95);
        this.witchHat_0_0_0.setRotationPoint(1.75F, -2.0F, 2.0F);
        this.witchHat_0_0_0.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.setRotationAngles(this.witchHat_0_0_0, -0.20943951023931953F, 0.0F, 0.10471975511965977F);
        this.witchHat_0_0.addChild(this.witchHat_0_0_0);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.witchHat.render(scale);
        GlStateManager.disableBlend();
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
