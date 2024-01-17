package com.superdextor.dextersnether.render;

import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderInfernalBlaze extends RenderBlaze {
   private static final ResourceLocation textures = new ResourceLocation("dextersnether:textures/entity/infernal_blaze.png");

   protected ResourceLocation getEntityTexture(EntityBlaze entity) {
      return textures;
   }

   protected void preRenderCallback(EntityLivingBase entity, float f) {
      GL11.glScalef(1.3F, 1.3F, 1.3F);
   }
}
