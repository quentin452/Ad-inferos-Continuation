package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.model.ModelObsidianSheepman;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderObsidianSheepmanGolem extends RenderBiped {
   private static final ResourceLocation textures = new ResourceLocation("dextersnether:textures/entity/obsidian_sheepman_golem.png");
   private ModelObsidianSheepman endermanModel;

   public RenderObsidianSheepmanGolem() {
      super(new ModelObsidianSheepman(), 0.5F);
      this.endermanModel = (ModelObsidianSheepman)super.mainModel;
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return textures;
   }
}
