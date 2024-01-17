package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.monster.EntitySkeletonHorse;
import com.superdextor.dextersnether.model.ModelSkeletonHorse;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSkeletonHorse extends RenderLiving {
   private static final ResourceLocation skeletonHorseEyesTextures = new ResourceLocation("dextersnether:textures/entity/wither_skeleton_horse_eyes.png");
   private static final ResourceLocation skeletonHorseTextures = new ResourceLocation("textures/entity/horse/horse_skeleton.png");
   private static final ResourceLocation witherSkeletonHorseTextures = new ResourceLocation("dextersnether:textures/entity/wither_skeleton_horse.png");

   public RenderSkeletonHorse() {
      super(new ModelSkeletonHorse(), 0.9F);
      this.setRenderPassModel(new ModelSkeletonHorse());
   }

   protected int shouldRenderPass(EntitySkeletonHorse entity, int p_77032_2_, float p_77032_3_) {
      if (p_77032_2_ != 0) {
         return -1;
      } else {
         if (entity.getSkeletonType() == 1) {
            this.bindTexture(skeletonHorseEyesTextures);
            GL11.glEnable(3042);
            GL11.glDisable(3008);
            GL11.glBlendFunc(1, 1);
            if (entity.isInvisible()) {
               GL11.glDepthMask(false);
            } else {
               GL11.glDepthMask(true);
            }

            char c0 = '\uf0f0';
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         return 1;
      }
   }

   protected ResourceLocation getEntityTexture(EntitySkeletonHorse entity) {
      return entity.getSkeletonType() == 1 ? witherSkeletonHorseTextures : skeletonHorseTextures;
   }

   protected int shouldRenderPass(EntityLivingBase entity, int p_77032_2_, float p_77032_3_) {
      return this.shouldRenderPass((EntitySkeletonHorse)entity, p_77032_2_, p_77032_3_);
   }

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return this.getEntityTexture((EntitySkeletonHorse)p_110775_1_);
   }
}
