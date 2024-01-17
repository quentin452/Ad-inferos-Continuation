package com.superdextor.dextersnether.model;

import com.superdextor.dextersnether.entity.monster.EntityGlowstoneSkeleton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelGlowstoneSkeleton extends ModelBiped {
   public ModelGlowstoneSkeleton() {
      this(0.0F, true);
   }

   protected ModelGlowstoneSkeleton(float var, float var2, int width, int height) {
      super(var, var2, width, height);
   }

   public ModelGlowstoneSkeleton(float var, boolean type) {
      super(var, 0.0F, 64, type ? 32 : 64);
   }

   public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
      EntityGlowstoneSkeleton entityglowstoneskeleton = (EntityGlowstoneSkeleton)entity;
      if (entityglowstoneskeleton.IsUsingRangedAttack()) {
         this.aimedBow = true;
      } else {
         this.aimedBow = false;
      }

      if (this.aimedBow) {
         float f7 = 0.0F;
         float f8 = 0.0F;
         this.bipedRightArm.rotateAngleZ = 0.0F;
         this.bipedLeftArm.rotateAngleZ = 0.0F;
         this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
         this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
         this.bipedRightArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
         this.bipedLeftArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
         ModelRenderer var10000 = this.bipedRightArm;
         var10000.rotateAngleX -= f7 * 1.2F - f8 * 0.4F;
         var10000 = this.bipedLeftArm;
         var10000.rotateAngleX -= f7 * 1.2F - f8 * 0.4F;
         var10000 = this.bipedRightArm;
         var10000.rotateAngleZ += MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
         var10000 = this.bipedLeftArm;
         var10000.rotateAngleZ -= MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
         var10000 = this.bipedRightArm;
         var10000.rotateAngleX += MathHelper.sin(f3 * 0.067F) * 0.05F;
         var10000 = this.bipedLeftArm;
         var10000.rotateAngleX -= MathHelper.sin(f3 * 0.067F) * 0.05F;
      }

   }
}
