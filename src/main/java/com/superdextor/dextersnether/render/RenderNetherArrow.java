package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.projectile.EntityNetherArrow;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderNetherArrow extends RenderArrow {
   private static final ResourceLocation quartzTextures = new ResourceLocation("dextersnether:textures/entity/arrow_quartz.png");
   private static final ResourceLocation glowstoneTextures = new ResourceLocation("dextersnether:textures/entity/arrow_glowstone.png");
   private static final ResourceLocation obsidianTextures = new ResourceLocation("dextersnether:textures/entity/arrow_obsidian.png");
   private static final ResourceLocation witherTextures = new ResourceLocation("dextersnether:textures/entity/arrow_wither.png");
   private static final ResourceLocation netheriteTextures = new ResourceLocation("dextersnether:textures/entity/arrow_netherite.png");

   protected ResourceLocation getEntityTexture(EntityNetherArrow entity) {
      switch(entity.getArrowType()) {
      case 0:
         return quartzTextures;
      case 1:
         return glowstoneTextures;
      case 2:
         return obsidianTextures;
      case 3:
         return witherTextures;
      case 4:
         return netheriteTextures;
      default:
         return quartzTextures;
      }
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityNetherArrow)entity);
   }
}
