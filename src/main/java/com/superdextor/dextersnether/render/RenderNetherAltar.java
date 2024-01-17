package com.superdextor.dextersnether.render;

import com.superdextor.dextersnether.inventory.TileEntityNetherAltar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderNetherAltar extends TileEntitySpecialRenderer {
   public void renderTileEntityAt(TileEntityNetherAltar tileentity, double x, double y, double z, float facing) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)x + 0.5F, (float)y + 1.0F, (float)z + 0.5F);
      GL11.glEnable(2884);

      float f2;
      for(f2 = tileentity.field_145928_o - tileentity.field_145925_p; f2 >= 3.1415927F; f2 -= 6.2831855F) {
      }

      while(f2 < -3.1415927F) {
         f2 += 6.2831855F;
      }

      float f3 = tileentity.field_145925_p + f2 * facing;
      GL11.glRotatef(-f3 * 180.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
      GL11.glScalef(1.6F, 1.6F, 1.6F);
      ItemStack itemstack = tileentity.getDisplayedItem();
      if (itemstack != null) {
         EntityItem entityitem = new EntityItem(tileentity.getWorldObj(), 0.0D, 0.0D, 0.0D, itemstack);
         ItemStack stack = entityitem.getEntityItem();
         entityitem.getEntityItem().stackSize = 1;
         entityitem.hoverStart = 0.0F;
         float offset = MathHelper.cos((float)tileentity.age * 0.1F) * 0.015F;
         RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, (double)offset, 0.0D, 0.0F, 0.0F);
      }

      GL11.glScalef(0.8F, 0.8F, 0.8F);
      itemstack = tileentity.getStackInSlot(2);
      EntityItem entityitem;
      ItemStack stack;
      float offset;
      double xOffset;
      double zOffset;
      if (itemstack != null) {
         xOffset = 1.2D;
         zOffset = 0.0D;
         entityitem = new EntityItem(tileentity.getWorldObj(), 0.0D, 0.0D, 0.0D, itemstack);
         stack = entityitem.getEntityItem();
         entityitem.getEntityItem().stackSize = 1;
         entityitem.hoverStart = 0.0F;
         offset = MathHelper.cos((float)tileentity.age * 0.1F) * 0.015F;
         RenderManager.instance.renderEntityWithPosYaw(entityitem, xOffset, (double)offset, zOffset, 0.0F, 0.0F);
      }

      itemstack = tileentity.getStackInSlot(5);
      if (itemstack != null) {
         xOffset = -1.2D;
         zOffset = 0.0D;
         entityitem = new EntityItem(tileentity.getWorldObj(), 0.0D, 0.0D, 0.0D, itemstack);
         stack = entityitem.getEntityItem();
         entityitem.getEntityItem().stackSize = 1;
         entityitem.hoverStart = 0.0F;
         offset = MathHelper.cos((float)tileentity.age * 0.1F) * 0.015F;
         RenderManager.instance.renderEntityWithPosYaw(entityitem, xOffset, (double)offset, zOffset, 0.0F, 0.0F);
      }

      itemstack = tileentity.getStackInSlot(1);
      if (itemstack != null) {
         xOffset = 0.8D;
         zOffset = 0.4D;
         entityitem = new EntityItem(tileentity.getWorldObj(), 0.0D, 0.0D, 0.0D, itemstack);
         stack = entityitem.getEntityItem();
         entityitem.getEntityItem().stackSize = 1;
         entityitem.hoverStart = 0.0F;
         offset = MathHelper.cos((float)tileentity.age * 0.1F) * 0.015F;
         RenderManager.instance.renderEntityWithPosYaw(entityitem, xOffset, (double)offset, zOffset, 0.0F, 0.0F);
      }

      itemstack = tileentity.getStackInSlot(4);
      if (itemstack != null) {
         xOffset = -0.8D;
         zOffset = 0.4D;
         entityitem = new EntityItem(tileentity.getWorldObj(), 0.0D, 0.0D, 0.0D, itemstack);
         stack = entityitem.getEntityItem();
         entityitem.getEntityItem().stackSize = 1;
         entityitem.hoverStart = 0.0F;
         offset = MathHelper.cos((float)tileentity.age * 0.1F) * 0.015F;
         RenderManager.instance.renderEntityWithPosYaw(entityitem, xOffset, (double)offset, zOffset, 0.0F, 0.0F);
      }

      itemstack = tileentity.getStackInSlot(3);
      if (itemstack != null) {
         xOffset = 0.8D;
         zOffset = -0.4D;
         entityitem = new EntityItem(tileentity.getWorldObj(), 0.0D, 0.0D, 0.0D, itemstack);
         stack = entityitem.getEntityItem();
         entityitem.getEntityItem().stackSize = 1;
         entityitem.hoverStart = 0.0F;
         offset = MathHelper.cos((float)tileentity.age * 0.1F) * 0.015F;
         RenderManager.instance.renderEntityWithPosYaw(entityitem, xOffset, (double)offset, zOffset, 0.0F, 0.0F);
      }

      itemstack = tileentity.getStackInSlot(6);
      if (itemstack != null) {
         xOffset = -0.8D;
         zOffset = -0.4D;
         entityitem = new EntityItem(tileentity.getWorldObj(), 0.0D, 0.0D, 0.0D, itemstack);
         stack = entityitem.getEntityItem();
         entityitem.getEntityItem().stackSize = 1;
         entityitem.hoverStart = 0.0F;
         offset = MathHelper.cos((float)tileentity.age * 0.1F) * 0.015F;
         RenderManager.instance.renderEntityWithPosYaw(entityitem, xOffset, (double)offset, zOffset, 0.0F, 0.0F);
      }

      GL11.glPopMatrix();
   }

   public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float facing) {
      this.renderTileEntityAt((TileEntityNetherAltar)tileentity, x, y, z, facing);
   }
}
