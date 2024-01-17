package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.monster.EntityGlowstoneSkeleton;
import com.superdextor.dextersnether.model.ModelGlowstoneSkeleton;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGlowstoneSkeleton extends RenderBiped {
   private static final ResourceLocation glowstoneTextures = new ResourceLocation("dextersnether:textures/entity/glowstone_skeleton.png");
   private static final ResourceLocation darkstoneTextures = new ResourceLocation("dextersnether:textures/entity/darkstone_skeleton.png");

   public RenderGlowstoneSkeleton() {
      super(new ModelGlowstoneSkeleton(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityGlowstoneSkeleton entity) {
      return entity.getSkeletonType() == 0 ? glowstoneTextures : darkstoneTextures;
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityGlowstoneSkeleton)entity);
   }
}
