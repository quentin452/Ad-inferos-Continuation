package com.superdextor.dextersnether.render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCurse extends RenderLiving {
   private static final ResourceLocation textures = new ResourceLocation("dextersnether:textures/entity/curse.png");

   public RenderCurse() {
      super(new ModelZombie(), 0.0F);
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return textures;
   }
}
