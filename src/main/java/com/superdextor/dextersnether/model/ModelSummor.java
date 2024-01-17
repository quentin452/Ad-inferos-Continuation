package com.superdextor.dextersnether.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelSummor extends ModelBase {
   public ModelRenderer skeletonHead;
   public ModelSummor() {
      this(0, 0, 64, 32);
   }

   public ModelSummor(int p_i1155_1_, int p_i1155_2_, int p_i1155_3_, int p_i1155_4_) {
      this.textureWidth = p_i1155_3_;
      this.textureHeight = p_i1155_4_;
      this.skeletonHead = new ModelRenderer(this, p_i1155_1_, p_i1155_2_);
      this.skeletonHead.addBox(-4.0F, 10.0F, -4.0F, 8, 8, 8, 0.0F);
      this.skeletonHead.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
      this.skeletonHead.render(p_78088_7_);
   }

   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
      this.skeletonHead.rotateAngleY = p_78087_4_ / 57.295776F;
      this.skeletonHead.rotateAngleX = p_78087_5_ / 57.295776F;
   }
}
