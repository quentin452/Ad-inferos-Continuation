package com.superdextor.dextersnether.model;

import com.superdextor.dextersnether.entity.monster.EntityInfernumAvis;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelInfernumAvis extends ModelBase {
   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer rightarm;
   ModelRenderer leftarm;
   ModelRenderer rightleg;
   ModelRenderer leftleg;
   ModelRenderer leftHorn;
   ModelRenderer rightHorn;
   ModelRenderer leftWign;
   ModelRenderer rightWign;

   public ModelInfernumAvis() {
      this.textureWidth = 68;
      this.textureHeight = 34;
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
      this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.head.setTextureSize(64, 32);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.body = new ModelRenderer(this, 16, 16);
      this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
      this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body.setTextureSize(64, 32);
      this.body.mirror = true;
      this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
      this.rightarm = new ModelRenderer(this, 40, 16);
      this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 3, 14, 4);
      this.rightarm.setRotationPoint(-4.0F, 2.0F, 0.0F);
      this.rightarm.setTextureSize(64, 32);
      this.rightarm.mirror = true;
      this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
      this.leftarm = new ModelRenderer(this, 40, 16);
      this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 3, 14, 4);
      this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
      this.leftarm.setTextureSize(64, 32);
      this.leftarm.mirror = true;
      this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
      this.rightleg = new ModelRenderer(this, 0, 16);
      this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
      this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
      this.rightleg.setTextureSize(64, 32);
      this.rightleg.mirror = true;
      this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
      this.leftleg = new ModelRenderer(this, 0, 16);
      this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
      this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
      this.leftleg.setTextureSize(64, 32);
      this.leftleg.mirror = true;
      this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
      this.leftHorn = new ModelRenderer(this, 32, 0);
      this.leftHorn.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.leftHorn.setRotationPoint(4.0F, -11.0F, -2.0F);
      this.leftHorn.setTextureSize(64, 32);
      this.leftHorn.mirror = true;
      this.head.addChild(this.leftHorn);
      this.setRotation(this.leftHorn, 0.0F, 0.0F, 0.0F);
      this.rightHorn = new ModelRenderer(this, 32, 0);
      this.rightHorn.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
      this.rightHorn.setRotationPoint(-5.0F, -11.0F, -2.0F);
      this.rightHorn.setTextureSize(64, 32);
      this.rightHorn.mirror = true;
      this.head.addChild(this.rightHorn);
      this.setRotation(this.rightHorn, 0.0F, 0.0F, 0.0F);
      this.leftWign = new ModelRenderer(this, 36, 0);
      this.leftWign.addBox(0.0F, 0.0F, 0.0F, 7, 12, 1);
      this.leftWign.setRotationPoint(-0.5F, 0.0F, 1.0F);
      this.leftWign.setTextureSize(64, 32);
      this.setRotation(this.leftWign, 0.0F, -0.1396263F, -0.3316126F);
      this.rightWign = new ModelRenderer(this, 52, 0);
      this.rightWign.addBox(0.0F, 0.0F, 0.0F, 7, 12, 1);
      this.rightWign.setRotationPoint(0.5F, 0.0F, 2.0F);
      this.rightWign.setTextureSize(64, 32);
      this.rightWign.mirror = true;
      this.setRotation(this.rightWign, 0.0F, 3.281219F, 0.3141593F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head.render(f5);
      this.body.render(f5);
      this.rightarm.render(f5);
      this.leftarm.render(f5);
      this.rightleg.render(f5);
      this.leftleg.render(f5);
      this.leftWign.render(f5);
      this.rightWign.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      EntityInfernumAvis entityavis = (EntityInfernumAvis)entity;
      this.head.rotateAngleY = f3 / 57.295776F;
      this.head.rotateAngleX = f4 / 57.295776F;
      this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 2.0F * f1 * 0.5F;
      this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.rightarm.rotateAngleZ = 0.0F;
      this.leftarm.rotateAngleZ = 0.0F;
      this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * f1;
      this.rightleg.rotateAngleY = 0.0F;
      this.leftleg.rotateAngleY = 0.0F;
      int wign = entityavis.GetWignAnimation();
      this.rightWign.rotateAngleZ = 0.0141593F;
      this.rightWign.rotateAngleX = 0.0F;
      this.leftWign.rotateAngleZ = -0.0141593F;
      this.leftWign.rotateAngleX = 0.0F;
      ModelRenderer var10000;
      if (wign == 0) {
         var10000 = this.rightWign;
         var10000.rotateAngleZ -= MathHelper.cos(f2 * 0.15F) * 0.05F + 0.05F - 0.1F;
         var10000 = this.rightWign;
         var10000.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F + 0.2F;
         var10000 = this.leftWign;
         var10000.rotateAngleZ -= MathHelper.cos(f2 * 0.15F) * 0.05F + 0.05F + 0.1F;
         var10000 = this.leftWign;
         var10000.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F - 0.2F;
      } else if (wign == 1) {
         var10000 = this.rightWign;
         var10000.rotateAngleZ -= MathHelper.cos(f2 * 0.8F) * 0.4F + -0.3F;
         var10000 = this.rightWign;
         var10000.rotateAngleX -= MathHelper.sin(f2 * 0.8F) * 0.8F + 0.8F;
         var10000 = this.leftWign;
         var10000.rotateAngleZ -= -MathHelper.cos(f2 * 0.8F) * 0.4F + 0.3F;
         var10000 = this.leftWign;
         var10000.rotateAngleX -= -MathHelper.sin(f2 * 0.8F) * 0.8F + -0.8F;
      } else {
         float falltimer = (float)entity.motionY * 1.6F;
         var10000 = this.rightWign;
         var10000.rotateAngleX -= MathHelper.sin(f2 * 0.1F) * 0.05F - falltimer;
         var10000 = this.leftWign;
         var10000.rotateAngleX -= -MathHelper.sin(f2 * 0.1F) * 0.05F + falltimer;
         var10000 = this.rightWign;
         var10000.rotateAngleZ = (float)((double)var10000.rotateAngleZ - (double)(-falltimer) * 0.3D);
         var10000 = this.leftWign;
         var10000.rotateAngleZ = (float)((double)var10000.rotateAngleZ - (double)falltimer * 0.3D);
      }

      var10000 = this.rightarm;
      var10000.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
      var10000 = this.leftarm;
      var10000.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
      var10000 = this.rightarm;
      var10000.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
      var10000 = this.leftarm;
      var10000.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
      if (entityavis.IsSleeping()) {
         var10000 = this.rightarm;
         var10000.rotateAngleX += -0.62831855F;
         var10000 = this.leftarm;
         var10000.rotateAngleX += -0.62831855F;
         this.rightleg.rotateAngleX = -1.2566371F;
         this.leftleg.rotateAngleX = -1.2566371F;
         this.rightleg.rotateAngleY = 0.31415927F;
         this.leftleg.rotateAngleY = -0.31415927F;
         this.head.rotateAngleX = 20.0F;
         this.head.rotateAngleY = 0.0F;
      }

   }

   public void postRenderArm(float pos) {
      this.rightarm.postRender(pos);
   }

   public void setInvisible(boolean invisible) {
      this.head.showModel = invisible;
      this.body.showModel = invisible;
      this.rightarm.showModel = invisible;
      this.leftarm.showModel = invisible;
      this.rightleg.showModel = invisible;
      this.leftleg.showModel = invisible;
   }
}
