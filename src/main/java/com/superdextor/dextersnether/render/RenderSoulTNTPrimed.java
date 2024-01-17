package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.projectile.EntitySoulTNTPrimed;
import com.superdextor.dextersnether.init.NetherBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSoulTNTPrimed extends Render {
   private RenderBlocks blockRenderer = new RenderBlocks();

   public RenderSoulTNTPrimed() {
      this.shadowSize = 0.4F;
   }

   public void doRender(EntitySoulTNTPrimed p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
      float f2;
      if ((float)p_76986_1_.fuse - p_76986_9_ + 1.0F < 10.0F) {
         f2 = 1.0F - ((float)p_76986_1_.fuse - p_76986_9_ + 1.0F) / 10.0F;
         if (f2 < 0.0F) {
            f2 = 0.0F;
         }

         if (f2 > 1.0F) {
            f2 = 1.0F;
         }

         f2 *= f2;
         f2 *= f2;
         float f3 = 1.0F + f2 * 0.3F;
         GL11.glScalef(f3, f3, f3);
      }

      f2 = (1.0F - ((float)p_76986_1_.fuse - p_76986_9_ + 1.0F) / 100.0F) * 0.8F;
      this.bindEntityTexture(p_76986_1_);
      this.blockRenderer.renderBlockAsItem(NetherBlocks.soul_tnt, 0, p_76986_1_.getBrightness(p_76986_9_));
      if (p_76986_1_.fuse / 5 % 2 == 0) {
         GL11.glDisable(3553);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 772);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
         this.blockRenderer.renderBlockAsItem(NetherBlocks.soul_tnt, 0, 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(3042);
         GL11.glEnable(2896);
         GL11.glEnable(3553);
      }

      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntitySoulTNTPrimed p_110775_1_) {
      return TextureMap.locationBlocksTexture;
   }

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return this.getEntityTexture((EntitySoulTNTPrimed)p_110775_1_);
   }

   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntitySoulTNTPrimed)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }
}
