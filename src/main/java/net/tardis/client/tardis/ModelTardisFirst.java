package net.tardis.client.tardis;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.Tardis;

public class ModelTardisFirst extends ModelTardisBase {

    //Texture
    private ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/01.png");

    //Left door
    private ModelRenderer Shape1, doorLeft0, doorLeft1, doorLeft2, doorLeft3, doorLeft4, doorLeft5, doorLeft6, doorLeft7, doorLeft8, doorLeft9, doorLeft10, doorLeft11, doorLeft12, doorLeft13, doorLeft14, doorLeft15, doorLeft16;

    public ModelTardisFirst() {
        textureWidth = 256;
        textureHeight = 256;

        Shape1 = new ModelRenderer(this, 18, 72);
        Shape1.addBox(0.5F, -1.0F, -8.5F, 7, 1, 1);
        Shape1.setTextureSize(256, 256);
        Shape1.setRotationPoint(0.0F, 0.0F, -0.5F);
        setRotation(Shape1, 0.0F, 0.0F, 0.0F);
        Shape1.mirror = false;

        doorLeft0 = new ModelRenderer(this, 18, 72);
        doorLeft0.addBox(0.5F, 21.5F, -8.5F, 7, 1, 1);
        doorLeft0.setTextureSize(256, 256);
        doorLeft0.setRotationPoint(0.0F, 0.0F, -0.5F);
        setRotation(doorLeft0, 0.0F, 0.0F, 0.0F);
        doorLeft0.mirror = false;

        doorLeft1 = new ModelRenderer(this, 18, 72);
        doorLeft1.addBox(0.5F, 6.5F, -8.5F, 7, 1, 1);
        doorLeft1.setTextureSize(256, 256);
        doorLeft1.setRotationPoint(0.0F, 0.0F, -0.5F);
        setRotation(doorLeft1, 0.0F, 0.0F, 0.0F);
        doorLeft1.mirror = false;

        doorLeft2 = new ModelRenderer(this, 18, 72);
        doorLeft2.addBox(0.5F, 14.0F, -8.5F, 7, 1, 1);
        doorLeft2.setTextureSize(256, 256);
        doorLeft2.setRotationPoint(0.0F, 0.0F, -0.5F);
        setRotation(doorLeft2, 0.0F, 0.0F, 0.0F);
        doorLeft2.mirror = false;

        doorLeft3 = new ModelRenderer(this, 0, 0);
        doorLeft3.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
        doorLeft3.setTextureSize(256, 256);
        doorLeft3.setRotationPoint(4.0F, -4.5F, -8.2F);
        setRotation(doorLeft3, 0.7853981633974483F, 0.0F, 0.0F);
        doorLeft3.mirror = false;

        doorLeft4 = new ModelRenderer(this, 18, 72);
        doorLeft4.addBox(0.5F, -8.5F, -8.5F, 7, 1, 1);
        doorLeft4.setTextureSize(256, 256);
        doorLeft4.setRotationPoint(0.0F, 0.0F, -0.5F);
        setRotation(doorLeft4, 0.0F, 0.0F, 0.0F);
        doorLeft4.mirror = false;

        doorLeft5 = new ModelRenderer(this, 13, 72);
        doorLeft5.addBox(0.5F, -8.5F, -6.5F, 1, 31, 1);
        doorLeft5.setTextureSize(256, 256);
        doorLeft5.setRotationPoint(0.0F, 0.0F, -2.5F);
        setRotation(doorLeft5, 0.0F, 0.0F, 0.0F);
        doorLeft5.mirror = false;

        doorLeft6 = new ModelRenderer(this, 13, 72);
        doorLeft6.addBox(6.5F, -8.0F, -8.5F, 1, 30, 1);
        doorLeft6.setTextureSize(256, 256);
        doorLeft6.setRotationPoint(0.0F, 0.0F, -0.5F);
        setRotation(doorLeft6, 0.0F, 0.0F, 0.0F);
        doorLeft6.mirror = false;

        doorLeft7 = new ModelRenderer(this, 0, 0);
        doorLeft7.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
        doorLeft7.setTextureSize(256, 256);
        doorLeft7.setRotationPoint(3.1F, -4.45F, -8.2F);
        setRotation(doorLeft7, 0.0F, 0.7853981633974483F, 0.0F);
        doorLeft7.mirror = false;

        doorLeft8 = new ModelRenderer(this, 0, 0);
        doorLeft8.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
        doorLeft8.setTextureSize(256, 256);
        doorLeft8.setRotationPoint(4.9F, -4.45F, -8.2F);
        setRotation(doorLeft8, 0.0F, 0.7853981633974483F, 0.0F);
        doorLeft8.mirror = false;

        doorLeft9 = new ModelRenderer(this, 62, 86);
        doorLeft9.addBox(3.8F, 3.55F, -8.4F, 1, 1, 1);
        doorLeft9.setTextureSize(256, 256);
        doorLeft9.setRotationPoint(0.0F, -0.5F, -0.5F);
        setRotation(doorLeft9, 0.0F, 0.0F, 0.0F);
        doorLeft9.mirror = false;

        doorLeft10 = new ModelRenderer(this, 18, 68);
        doorLeft10.addBox(1.5F, -1.5F, -8.45F, 5, 1, 1);
        doorLeft10.setTextureSize(256, 256);
        doorLeft10.setRotationPoint(0.0F, 0.0F, -0.5F);
        setRotation(doorLeft10, 0.0F, 0.0F, 0.0F);
        doorLeft10.mirror = false;

        doorLeft11 = new ModelRenderer(this, 46, 81);
        doorLeft11.addBox(1.5F, -7.0F, -8.2F, 5, 29, 0);
        doorLeft11.setTextureSize(256, 256);
        doorLeft11.setRotationPoint(0.0F, -0.5F, -0.5F);
        setRotation(doorLeft11, 0.0F, 0.0F, 0.0F);
        doorLeft11.mirror = false;

        doorLeft12 = new ModelRenderer(this, 57, 81);
        doorLeft12.addBox(3.0F, 2.75F, -8.3F, 2, 2, 1);
        doorLeft12.setTextureSize(256, 256);
        doorLeft12.setRotationPoint(0.0F, -0.5F, -0.5F);
        setRotation(doorLeft12, 0.0F, 0.0F, 0.0F);
        doorLeft12.mirror = false;

        doorLeft13 = new ModelRenderer(this, 57, 86);
        doorLeft13.addBox(3.5F, 3.25F, -8.5F, 1, 1, 1);
        doorLeft13.setTextureSize(256, 256);
        doorLeft13.setRotationPoint(0.0F, -0.5F, -0.5F);
        setRotation(doorLeft13, 0.0F, 0.0F, 0.0F);
        doorLeft13.mirror = false;

        doorLeft14 = new ModelRenderer(this, 62, 86);
        doorLeft14.addBox(3.2F, 3.55F, -8.4F, 1, 1, 1);
        doorLeft14.setTextureSize(256, 256);
        doorLeft14.setRotationPoint(0.0F, -0.5F, -0.5F);
        setRotation(doorLeft14, 0.0F, 0.0F, 0.0F);
        doorLeft14.mirror = false;

        doorLeft15 = new ModelRenderer(this, 62, 86);
        doorLeft15.addBox(3.8F, 2.95F, -8.4F, 1, 1, 1);
        doorLeft15.setTextureSize(256, 256);
        doorLeft15.setRotationPoint(0.0F, -0.5F, -0.5F);
        setRotation(doorLeft15, 0.0F, 0.0F, 0.0F);
        doorLeft15.mirror = false;

        doorLeft16 = new ModelRenderer(this, 62, 86);
        doorLeft16.addBox(3.2F, 2.95F, -8.4F, 1, 1, 1);
        doorLeft16.setTextureSize(256, 256);
        doorLeft16.setRotationPoint(0.0F, -0.5F, -0.5F);
        setRotation(doorLeft16, 0.0F, 0.0F, 0.0F);
        doorLeft16.mirror = false;
    }

    @Override
    public void renderDoorLeft(float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.46875, 0, 0.53125F);
        Shape1.render(scale);
        doorLeft0.render(scale);
        doorLeft1.render(scale);
        doorLeft2.render(scale);
        doorLeft3.render(scale);
        doorLeft4.render(scale);
        doorLeft5.render(scale);
        doorLeft6.render(scale);
        doorLeft7.render(scale);
        doorLeft8.render(scale);
        doorLeft9.render(scale);
        doorLeft10.render(scale);
        doorLeft11.render(scale);
        doorLeft12.render(scale);
        doorLeft13.render(scale);
        doorLeft14.render(scale);
        doorLeft15.render(scale);
        doorLeft16.render(scale);
        GlStateManager.popMatrix();
    }

    @Override
    public void renderDoorRight(float scale) {

    }

    @Override
    public void renderShell(float scale) {

    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }


}
