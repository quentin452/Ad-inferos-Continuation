package com.superdextor.dextersnether.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderObsidianSheepman extends RenderZombie {
   private static final ResourceLocation sheepmanTextures = new ResourceLocation("dextersnether:textures/entity/obsidian_sheepman.png");

   protected ResourceLocation getEntityTexture(Entity entity) {
      return sheepmanTextures;
   }
}
