package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.monster.EntitySummor;
import com.superdextor.dextersnether.model.ModelSummor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSummor extends RenderLiving {
   private static final ResourceLocation defaultTextures = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
   private static final ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   private static final ResourceLocation spiderTextures = new ResourceLocation("dextersnether:textures/entity/nether_spider_head.png");
   private static final ResourceLocation glowstoneSkeletonTextures = new ResourceLocation("dextersnether:textures/entity/glowstone_skeleton.png");
   private static final ResourceLocation phantomTextures = new ResourceLocation("dextersnether:textures/entity/phantom.png");
   private static final ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");
   private static final ResourceLocation reaperTextures = new ResourceLocation("dextersnether:textures/entity/reaper.png");
   private static final ResourceLocation herobrineTextures = new ResourceLocation("dextersnether:textures/entity/herobrine.png");
   private static final ModelSummor skeletonHeadModel = new ModelSummor();
   private static final String __OBFID = "CL_00001035";

   public RenderSummor() {
      super(skeletonHeadModel, 0.2F);
   }

   protected ResourceLocation func_180564_a(EntitySummor entitysummor) {
      switch(entitysummor.getStage()) {
      case 0:
         return defaultTextures;
      case 1:
         return skeletonTextures;
      case 2:
         return spiderTextures;
      case 3:
         return glowstoneSkeletonTextures;
      case 4:
         return phantomTextures;
      case 5:
         return blazeTextures;
      case 6:
         return reaperTextures;
      case 7:
         return herobrineTextures;
      default:
         return defaultTextures;
      }
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return this.func_180564_a((EntitySummor)entity);
   }

   public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
      this.doRender((EntitySummor)entity, x, y, z, p_76986_8_, partialTicks);
   }
}
