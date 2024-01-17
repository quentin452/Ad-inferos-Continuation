package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.entity.monster.EntityQuartzman;
import com.superdextor.dextersnether.init.NetherBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderQuartzman extends RenderLiving {
   private static final ResourceLocation snowManTextures = new ResourceLocation("dextersnether:textures/entity/quartzman.png");
   private ModelSnowMan snowmanModel;

   public RenderQuartzman() {
      super(new ModelSnowMan(), 0.4F);
      this.snowmanModel = (ModelSnowMan)super.mainModel;
      this.setRenderPassModel(this.snowmanModel);
   }

   protected void renderEquippedItems(EntityQuartzman quartzman, float f) {
      super.renderEquippedItems(quartzman, f);
      ItemStack itemstack = new ItemStack(NetherBlocks.wither_block, 1);
      if (itemstack.getItem() instanceof ItemBlock) {
         GL11.glPushMatrix();
         this.snowmanModel.head.postRender(0.0625F);
         IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, ItemRenderType.EQUIPPED);
         boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(ItemRenderType.EQUIPPED, itemstack, ItemRendererHelper.BLOCK_3D);
         if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {
            float f1 = 0.625F;
            GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(f1, -f1, f1);
         }

         this.renderManager.itemRenderer.renderItem(quartzman, itemstack, 0);
         GL11.glPopMatrix();
      }

   }

   protected void renderEquippedItems(EntityLivingBase entity, float f) {
      this.renderEquippedItems((EntityQuartzman)entity, f);
   }

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return snowManTextures;
   }
}
