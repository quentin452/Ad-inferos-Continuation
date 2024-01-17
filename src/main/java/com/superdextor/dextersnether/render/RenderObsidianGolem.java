package com.superdextor.dextersnether.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderObsidianGolem extends RenderIronGolem {
   private static final ResourceLocation ironGolemTextures = new ResourceLocation("dextersnether:textures/entity/obsidian_golem.png");

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return ironGolemTextures;
   }
}
