package com.superdextor.dextersnether.model;

import com.superdextor.dextersnether.entity.monster.EntityObsidainSheepmanGolem;
import com.superdextor.dextersnether.entity.monster.EntityObsidianSheepman;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelObsidianSheepman extends ModelBiped {
   private static final String __OBFID = "CL_00000869";

   public ModelObsidianSheepman() {
      this(0.0F, false);
   }

   protected ModelObsidianSheepman(float p_i1167_1_, float p_i1167_2_, int p_i1167_3_, int p_i1167_4_) {
      super(p_i1167_1_, p_i1167_2_, p_i1167_3_, p_i1167_4_);
   }

   public ModelObsidianSheepman(float p_i1168_1_, boolean p_i1168_2_) {
      super(p_i1168_1_, 0.0F, 64, p_i1168_2_ ? 32 : 64);
   }

   public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
      float f7 = MathHelper.sin(this.onGround * 3.1415927F);
      float f8 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * 3.1415927F);
      boolean flag = false;
      if (entity instanceof EntityObsidianSheepman) {
         EntityObsidianSheepman entitysheepman = (EntityObsidianSheepman)entity;
         if (entitysheepman.isAngry()) {
            flag = true;
         }
      }

      if (entity instanceof EntityObsidainSheepmanGolem) {
         EntityObsidainSheepmanGolem entitysheepman = (EntityObsidainSheepmanGolem)entity;
         if (entitysheepman.isAttacking()) {
            flag = true;
         }
      }

      if (flag) {
         this.bipedRightArm.rotateAngleZ = 0.0F;
         this.bipedLeftArm.rotateAngleZ = 0.0F;
         this.bipedRightArm.rotateAngleY = -(0.1F - f7 * 0.6F);
         this.bipedLeftArm.rotateAngleY = 0.1F - f7 * 0.6F;
         this.bipedRightArm.rotateAngleX = -1.5707964F;
         this.bipedLeftArm.rotateAngleX = -1.5707964F;
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
