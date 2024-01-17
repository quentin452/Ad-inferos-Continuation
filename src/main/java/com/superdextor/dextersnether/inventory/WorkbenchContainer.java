package com.superdextor.dextersnether.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class WorkbenchContainer extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
   public IInventory craftResult = new InventoryCraftResult();
   private World worldObj;

   public WorkbenchContainer(InventoryPlayer inventoryplayer, World world) {
      this.worldObj = world;
      this.addSlotToContainer(new SlotCrafting(inventoryplayer.player, this.craftMatrix, this.craftResult, 0, 124, 35));

      int column;
      int row;
      for(column = 0; column < 3; ++column) {
         for(row = 0; row < 3; ++row) {
            this.addSlotToContainer(new Slot(this.craftMatrix, row + column * 3, 30 + row * 18, 17 + column * 18));
         }
      }

      for(column = 0; column < 3; ++column) {
         for(row = 0; row < 9; ++row) {
            this.addSlotToContainer(new Slot(inventoryplayer, row + column * 9 + 9, 8 + row * 18, 84 + column * 18));
         }
      }

      for(column = 0; column < 9; ++column) {
         this.addSlotToContainer(new Slot(inventoryplayer, column, 8 + column * 18, 142));
      }

      this.onCraftMatrixChanged(this.craftMatrix);
   }

   public void onCraftMatrixChanged(IInventory iinventory) {
      this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
   }

   public void onContainerClosed(EntityPlayer entityplayer) {
      super.onContainerClosed(entityplayer);
      if (!this.worldObj.isRemote) {
         for(int i = 0; i < 9; ++i) {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            if (itemstack != null) {
               entityplayer.entityDropItem(itemstack, 0.0F);
            }
         }

      }
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return true;
   }

   public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.inventorySlots.get(i);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (i == 0) {
            if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
               return null;
            }
         } else if (i >= 10 && i < 37) {
            if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
               return null;
            }
         } else if (i >= 37 && i < 46) {
            if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
            return null;
         }

         if (itemstack1.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.stackSize == itemstack.stackSize) {
            return null;
         }

         slot.onPickupFromSlot(entityplayer, itemstack1);
      }

      return itemstack;
   }
}
