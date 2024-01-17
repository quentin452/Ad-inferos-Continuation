package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.model.ModelPhantom;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPhantom extends RenderLiving {
   private static final ResourceLocation textures = new ResourceLocation("dextersnether:textures/entity/phantom.png");

   public RenderPhantom() {
      super(new ModelPhantom(), 0.6F);
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return textures;
   }
}
