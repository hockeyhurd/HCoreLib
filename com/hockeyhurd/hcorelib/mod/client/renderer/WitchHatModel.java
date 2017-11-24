package com.hockeyhurd.hcorelib.mod.client.renderer;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author hockeyhurd
 * @version 9/3/17
 */
@SideOnly(Side.CLIENT)
public class WitchHatModel extends ModelBiped {

    private ModelRenderer witchHat;

    public WitchHatModel() {
        this.bipedHead = new ModelRenderer(this, 0, 0);

        this.witchHat = (new ModelRenderer(this)).setTextureSize(64, 128);
        this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        this.witchHat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);

        ModelRenderer modelRendererLayerBottom = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelRendererLayerBottom.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelRendererLayerBottom.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
        modelRendererLayerBottom.rotateAngleX = -0.05235988F;
        modelRendererLayerBottom.rotateAngleZ = 0.02617994F;

        this.witchHat.addChild(modelRendererLayerBottom);

        ModelRenderer modelRendererLayerMiddle = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelRendererLayerMiddle.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelRendererLayerMiddle.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        modelRendererLayerMiddle.rotateAngleX = -0.10471976F;
        modelRendererLayerMiddle.rotateAngleZ = 0.05235988F;
        modelRendererLayerBottom.addChild(modelRendererLayerMiddle);

        ModelRenderer modelRendererLayerTop = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelRendererLayerTop.setRotationPoint(1.75F, -2.0F, 2.0F);
        modelRendererLayerTop.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
        modelRendererLayerTop.rotateAngleX = -0.20943952F;
        modelRendererLayerTop.rotateAngleZ = 0.10471976F;

        modelRendererLayerMiddle.addChild(modelRendererLayerTop);

        this.bipedHead.addChild(witchHat);

    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, rotationYaw, rotationPitch, scale, entity);

        this.bipedHead.render(scale);

    }

}
