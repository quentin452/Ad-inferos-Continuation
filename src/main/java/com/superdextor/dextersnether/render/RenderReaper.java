package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.monster.EntityReaper;
import com.superdextor.dextersnether.model.ModelReaper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderReaper extends RenderBiped {
   private static final ResourceLocation textures = new ResourceLocation("dextersnether:textures/entity/reaper.png");
   private ModelReaper endermanModel;
   private Random rnd = new Random();

   public RenderReaper() {
      super(new ModelReaper(), 0.5F);
      this.endermanModel = (ModelReaper)super.mainModel;
   }

   protected void renderModel(EntityLivingBase entity, float f1, float f2, float f3, float f4, float f5, float f6) {
      this.bindEntityTexture(entity);
      if (entity instanceof EntityReaper) {
         EntityReaper entityreaper = (EntityReaper)entity;
         if (entityreaper.getAlpha() < 1.0F) {
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, entityreaper.getAlpha());
            GL11.glDepthMask(false);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glAlphaFunc(516, 0.003921569F);
            this.mainModel.render(entity, f1, f2, f3, f4, f5, f6);
            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
         } else {
            super.renderModel(entity, f1, f2, f3, f4, f5, f6);
         }
      } else {
         super.renderModel(entity, f1, f2, f3, f4, f5, f6);
      }

   }

   public void doRender(EntityReaper p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      if (p_76986_1_.isScreaming()) {
         double d3 = 0.02D;
         p_76986_2_ += this.rnd.nextGaussian() * d3;
         p_76986_6_ += this.rnd.nextGaussian() * d3;
      }

      super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityReaper)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityReaper)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return textures;
   }

   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityReaper)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }
}
