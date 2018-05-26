package net.tardis.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKey extends ModelBase
{
  //fields
    ModelRenderer KeyBase1;
    ModelRenderer KeyBase2;
    ModelRenderer KeyBase3;
    ModelRenderer KeyBase4;
    ModelRenderer KeyBase5;
    ModelRenderer KeyBase6;
    ModelRenderer KeyShape1;
    ModelRenderer KeyShape2;
    ModelRenderer KeyShape3;
    ModelRenderer KeyShape4;
    ModelRenderer KeyShape5;
    ModelRenderer KeyShape6;
    ModelRenderer KeyShape7;
    ModelRenderer KeyHandle1;
    ModelRenderer KeyHandle2;
    ModelRenderer KeyHole1;
    ModelRenderer KeyHole2;
    ModelRenderer KeyHole3;
    ModelRenderer KeyHole4;
    ModelRenderer KeyHole5;
    ModelRenderer KeyBack1;
    ModelRenderer KeyBack2;
    ModelRenderer KeyBack3;
    ModelRenderer KeyBack4;
    ModelRenderer KeyBack5;
    ModelRenderer KeyBack6;
  
  public ModelKey()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      KeyBase1 = new ModelRenderer(this, 0, 20);
      KeyBase1.addBox(0F, 0F, 0F, 5, 11, 1);
      KeyBase1.setRotationPoint(-3F, 13F, 0F);
      KeyBase1.setTextureSize(64, 64);
      KeyBase1.mirror = true;
      setRotation(KeyBase1, 0F, 0F, 0F);
      KeyBase2 = new ModelRenderer(this, 0, 12);
      KeyBase2.addBox(0F, 0F, 0F, 7, 6, 1);
      KeyBase2.setRotationPoint(-4F, 17F, 0F);
      KeyBase2.setTextureSize(64, 64);
      KeyBase2.mirror = true;
      setRotation(KeyBase2, 0F, 0F, 0F);
      KeyBase3 = new ModelRenderer(this, 13, 22);
      KeyBase3.addBox(0F, 0F, 0F, 2, 9, 1);
      KeyBase3.setRotationPoint(-5F, 13F, 0F);
      KeyBase3.setTextureSize(64, 64);
      KeyBase3.mirror = true;
      setRotation(KeyBase3, 0F, 0F, 0F);
      KeyBase4 = new ModelRenderer(this, 20, 22);
      KeyBase4.addBox(0F, 0F, 0F, 2, 9, 1);
      KeyBase4.setRotationPoint(2F, 13F, 0F);
      KeyBase4.setTextureSize(64, 64);
      KeyBase4.mirror = true;
      setRotation(KeyBase4, 0F, 0F, 0F);
      KeyBase5 = new ModelRenderer(this, 17, 14);
      KeyBase5.addBox(0F, 0F, 0F, 1, 6, 1);
      KeyBase5.setRotationPoint(4F, 14F, 0F);
      KeyBase5.setTextureSize(64, 64);
      KeyBase5.mirror = true;
      setRotation(KeyBase5, 0F, 0F, 0F);
      KeyBase5 = new ModelRenderer(this, 22, 14);
      KeyBase5.addBox(0F, 0F, 0F, 1, 6, 1);
      KeyBase5.setRotationPoint(-6F, 14F, 0F);
      KeyBase5.setTextureSize(64, 64);
      KeyBase5.mirror = true;
      setRotation(KeyBase5, 0F, 0F, 0F);
      KeyShape1 = new ModelRenderer(this, 0, 9);
      KeyShape1.addBox(-3F, 0F, 0F, 3, 1, 1);
      KeyShape1.setRotationPoint(2F, 21F, -0.5F);
      KeyShape1.setTextureSize(64, 64);
      KeyShape1.mirror = true;
      setRotation(KeyShape1, 0F, 0F, -0.5585054F);
      KeyShape2 = new ModelRenderer(this, 9, 8);
      KeyShape2.addBox(-3F, 0F, 0F, 3, 2, 1);
      KeyShape2.setRotationPoint(3F, 18F, -0.5F);
      KeyShape2.setTextureSize(64, 64);
      KeyShape2.mirror = true;
      setRotation(KeyShape2, 0F, 0F, -0.3490659F);
      KeyShape3 = new ModelRenderer(this, 18, 10);
      KeyShape3.addBox(-3F, 0F, 0F, 2, 2, 1);
      KeyShape3.setRotationPoint(0F, 19F, -0.5F);
      KeyShape3.setTextureSize(64, 64);
      KeyShape3.mirror = true;
      setRotation(KeyShape3, 0F, 0F, 2.583087F);
      KeyShape4 = new ModelRenderer(this, 18, 4);
      KeyShape4.addBox(-3F, 0F, 0F, 3, 4, 1);
      KeyShape4.setRotationPoint(-1.5F, 18F, -0.5F);
      KeyShape4.setTextureSize(64, 64);
      KeyShape4.mirror = true;
      setRotation(KeyShape4, 0F, 0F, -0.2443461F);
      KeyShape5 = new ModelRenderer(this, 0, 0);
      KeyShape5.addBox(-1F, 0F, 0F, 1, 4, 1);
      KeyShape5.setRotationPoint(0F, 13.5F, -0.5F);
      KeyShape5.setTextureSize(64, 64);
      KeyShape5.mirror = true;
      setRotation(KeyShape5, 0F, 0F, -1.151917F);
      KeyShape6 = new ModelRenderer(this, 9, 4);
      KeyShape6.addBox(-3F, 0F, 0F, 3, 2, 1);
      KeyShape6.setRotationPoint(-3F, 17.9F, -0.5F);
      KeyShape6.setTextureSize(64, 64);
      KeyShape6.mirror = true;
      setRotation(KeyShape6, 0F, 0F, 2.565634F);
      KeyShape7 = new ModelRenderer(this, 0, 6);
      KeyShape7.addBox(-3F, 0F, 0F, 3, 1, 1);
      KeyShape7.setRotationPoint(-5F, 16.5F, -0.5F);
      KeyShape7.setTextureSize(64, 64);
      KeyShape7.mirror = true;
      setRotation(KeyShape7, 0F, 0F, 2.216568F);
      KeyHandle1 = new ModelRenderer(this, 27, 3);
      KeyHandle1.addBox(0F, 0F, 0F, 3, 5, 1);
      KeyHandle1.setRotationPoint(-2F, 8.6F, 0F);
      KeyHandle1.setTextureSize(64, 64);
      KeyHandle1.mirror = true;
      setRotation(KeyHandle1, 0F, 0F, 0F);
      KeyHandle2 = new ModelRenderer(this, 27, 29);
      KeyHandle2.addBox(0F, 0F, 0F, 10, 2, 1);
      KeyHandle2.setRotationPoint(-5.5F, 9.6F, 0F);
      KeyHandle2.setTextureSize(64, 64);
      KeyHandle2.mirror = true;
      setRotation(KeyHandle2, 0F, 0F, 0F);
      KeyHole1 = new ModelRenderer(this, 27, 26);
      KeyHole1.addBox(0F, 0F, 0F, 4, 1, 1);
      KeyHole1.setRotationPoint(-2.5F, 7.6F, 0F);
      KeyHole1.setTextureSize(64, 64);
      KeyHole1.mirror = true;
      setRotation(KeyHole1, 0F, 0F, 0F);
      KeyHole2 = new ModelRenderer(this, 38, 25);
      KeyHole2.addBox(0F, 0F, 0F, 1, 2, 1);
      KeyHole2.setRotationPoint(0.5F, 5.6F, 0F);
      KeyHole2.setTextureSize(64, 64);
      KeyHole2.mirror = true;
      setRotation(KeyHole2, 0F, 0F, 0F);
      KeyHole3 = new ModelRenderer(this, 38, 21);
      KeyHole3.addBox(0F, 0F, 0F, 1, 2, 1);
      KeyHole3.setRotationPoint(-2.5F, 5.6F, 0F);
      KeyHole3.setTextureSize(64, 64);
      KeyHole3.mirror = true;
      setRotation(KeyHole3, 0F, 0F, 0F);
      KeyHole4 = new ModelRenderer(this, 27, 23);
      KeyHole4.addBox(0F, 0F, 0F, 3, 1, 1);
      KeyHole4.setRotationPoint(-2F, 4.6F, 0F);
      KeyHole4.setTextureSize(64, 64);
      KeyHole4.mirror = true;
      setRotation(KeyHole4, 0F, 0F, 0F);
      KeyHole5 = new ModelRenderer(this, 27, 20);
      KeyHole5.addBox(0F, 0F, 0F, 2, 1, 1);
      KeyHole5.setRotationPoint(-1.5F, 6.6F, 0F);
      KeyHole5.setTextureSize(64, 64);
      KeyHole5.mirror = true;
      setRotation(KeyHole5, 0F, 0F, 0F);
      KeyBack1 = new ModelRenderer(this, 27, 16);
      KeyBack1.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack1.setRotationPoint(0F, 14F, 0.2F);
      KeyBack1.setTextureSize(64, 64);
      KeyBack1.mirror = true;
      setRotation(KeyBack1, 0F, 0F, 0F);
      KeyBack2 = new ModelRenderer(this, 27, 12);
      KeyBack2.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack2.setRotationPoint(-4F, 15F, 0.2F);
      KeyBack2.setTextureSize(64, 64);
      KeyBack2.mirror = true;
      setRotation(KeyBack2, 0F, 0F, 0F);
      KeyBack3 = new ModelRenderer(this, 34, 17);
      KeyBack3.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack3.setRotationPoint(-4F, 19F, 0.2F);
      KeyBack3.setTextureSize(64, 64);
      KeyBack3.mirror = true;
      setRotation(KeyBack3, 0F, 0F, 0F);
      KeyBack4 = new ModelRenderer(this, 34, 13);
      KeyBack4.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack4.setRotationPoint(-1F, 17F, 0.2F);
      KeyBack4.setTextureSize(64, 64);
      KeyBack4.mirror = true;
      setRotation(KeyBack4, 0F, 0F, 0F);
      KeyBack5 = new ModelRenderer(this, 34, 9);
      KeyBack5.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack5.setRotationPoint(-1F, 21F, 0.2F);
      KeyBack5.setTextureSize(64, 64);
      KeyBack5.mirror = true;
      setRotation(KeyBack5, 0F, 0F, 0F);
      KeyBack6 = new ModelRenderer(this, 41, 11);
      KeyBack6.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack6.setRotationPoint(2F, 18F, 0.2F);
      KeyBack6.setTextureSize(64, 64);
      KeyBack6.mirror = true;
      setRotation(KeyBack6, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    KeyBase1.render(f5);
    KeyBase2.render(f5);
    KeyBase3.render(f5);
    KeyBase4.render(f5);
    KeyBase5.render(f5);
    KeyBase5.render(f5);
    KeyShape1.render(f5);
    KeyShape2.render(f5);
    KeyShape3.render(f5);
    KeyShape4.render(f5);
    KeyShape5.render(f5);
    KeyShape6.render(f5);
    KeyShape7.render(f5);
    KeyHandle1.render(f5);
    KeyHandle2.render(f5);
    KeyHole1.render(f5);
    KeyHole2.render(f5);
    KeyHole3.render(f5);
    KeyHole4.render(f5);
    KeyHole5.render(f5);
    KeyBack1.render(f5);
    KeyBack2.render(f5);
    KeyBack3.render(f5);
    KeyBack4.render(f5);
    KeyBack5.render(f5);
    KeyBack6.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
