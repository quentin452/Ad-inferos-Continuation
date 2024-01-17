package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.monster.EntityInfernumAvis;
import com.superdextor.dextersnether.model.ModelInfernumAvis;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderInfernumAvis extends RenderLiving {
   private static final ResourceLocation textures = new ResourceLocation("dextersnether:textures/entity/infernum_avis.png");

   public RenderInfernumAvis() {
      super(new ModelInfernumAvis(), 0.0F);
      this.shadowSize = 2.0F;
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return textures;
   }

   public void doRender(EntityInfernumAvis entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
      this.shadowSize = entity.getHealth() / 160.0F + 1.0F;
      if (entity.IsSleeping()) {
         y += -0.800000011920929D;
      }

      super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
   }

   public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
      this.doRender((EntityInfernumAvis)entity, x, y, z, p_76986_8_, partialTicks);
   }

   public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
      this.doRender((EntityInfernumAvis)entity, x, y, z, p_76986_8_, partialTicks);
   }

   protected void preRenderCallback(EntityLivingBase entity, float p_77041_2_) {
      float size = entity.getHealth() / 160.0F + 1.0F;
      if (entity.isChild()) {
         size = (float)((double)size * 0.5D);
      }

      GL11.glScalef(size, size, size);
   }
}
