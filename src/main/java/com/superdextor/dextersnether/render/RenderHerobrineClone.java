package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.monster.EntityHerobrineClone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHerobrineClone extends RenderBiped {
   private static final ResourceLocation herobrineEyesTextures = new ResourceLocation("dextersnether:textures/entity/herobrine_eyes.png");
   private static final ResourceLocation herobrineTextures = new ResourceLocation("dextersnether:textures/entity/herobrine.png");

   public RenderHerobrineClone() {
      super(new ModelBiped(), 0.5F);
      this.setRenderPassModel(new ModelBiped());
   }

   protected int shouldRenderPass(EntityHerobrineClone entity, int p_77032_2_, float p_77032_3_) {
      if (p_77032_2_ != 0) {
         return -1;
      } else {
         this.bindTexture(herobrineEyesTextures);
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
         return 1;
      }
   }

   protected void renderModel(EntityLivingBase entity, float f1, float f2, float f3, float f4, float f5, float f6) {
      this.bindEntityTexture(entity);
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.0F + entity.getRNG().nextFloat() / 2.0F);
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glAlphaFunc(516, 0.003921569F);
      this.mainModel.render(entity, f1, f2, f3, f4, f5, f6);
      GL11.glDisable(3042);
      GL11.glAlphaFunc(516, 0.1F);
      GL11.glPopMatrix();
      GL11.glDepthMask(true);
   }

   protected ResourceLocation getEntityTexture(EntityHerobrineClone entity) {
      return herobrineTextures;
   }

   protected int shouldRenderPass(EntityLivingBase entity, int p_77032_2_, float p_77032_3_) {
      return this.shouldRenderPass((EntityHerobrineClone)entity, p_77032_2_, p_77032_3_);
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityHerobrineClone)entity);
   }
}
