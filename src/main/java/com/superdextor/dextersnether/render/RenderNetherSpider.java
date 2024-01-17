package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.monster.EntityNetherSpider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderNetherSpider extends RenderSpider {
   private static final ResourceLocation textures = new ResourceLocation("dextersnether:textures/entity/nether_spider.png");
   private static final ResourceLocation eyesTextures = new ResourceLocation("dextersnether:textures/entity/nether_spider_eyes.png");

   protected int shouldRenderPass(EntitySpider p_77032_1_, int p_77032_2_, float p_77032_3_) {
      if (p_77032_2_ != 0) {
         return -1;
      } else {
         this.bindTexture(eyesTextures);
         GL11.glEnable(3042);
         GL11.glDisable(3008);
         GL11.glBlendFunc(1, 1);
         if (p_77032_1_.isInvisible()) {
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

   protected ResourceLocation getEntityTexture(Entity entity) {
      return textures;
   }

   public void doRender(EntityNetherSpider entityNetherSpider, double x, double y, double z, float f, float partialTicks) {
      this.shadowSize = 0.5F * (float)(entityNetherSpider.getSpiderSize() + 1);
      super.doRender(entityNetherSpider, x, y, z, f, partialTicks);
   }

   protected void preRenderCallback(EntityNetherSpider entityNetherSpider, float f) {
      float f1 = 0.5F * (float)(entityNetherSpider.getSpiderSize() + 1);
      GL11.glScalef(f1, f1, f1);
   }

   public void doRender(EntityLiving entity, double x, double y, double z, float f, float partialTicks) {
      this.doRender((EntityNetherSpider)entity, x, y, z, f, partialTicks);
   }

   protected void preRenderCallback(EntityLivingBase entityLivingBase, float f) {
      this.preRenderCallback((EntityNetherSpider)entityLivingBase, f);
   }
}
