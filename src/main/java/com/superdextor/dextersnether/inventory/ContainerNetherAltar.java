package com.superdextor.dextersnether.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerNetherAltar extends Container {
   private TileEntityNetherAltar tileNetherAltar;
   private int lastCookTime;
   private int lastBurnTime;
   private int lastItemBurnTime;

   public ContainerNetherAltar(InventoryPlayer inventory, TileEntityNetherAltar tileentity) {
      this.tileNetherAltar = tileentity;
      this.addSlotToContainer(new Slot(tileentity, 0, 80, 38));
      this.addSlotToContainer(new Slot(tileentity, 1, 47, 19));
      this.addSlotToContainer(new Slot(tileentity, 2, 28, 39));
      this.addSlotToContainer(new Slot(tileentity, 3, 47, 59));
      this.addSlotToContainer(new Slot(tileentity, 4, 113, 19));
      this.addSlotToContainer(new Slot(tileentity, 5, 132, 39));
      this.addSlotToContainer(new Slot(tileentity, 6, 113, 59));

      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
      }

   }

   public void addCraftingToCrafters(ICrafting icrafting) {
      super.addCraftingToCrafters(icrafting);
      icrafting.sendProgressBarUpdate(this, 0, this.tileNetherAltar.furnaceCookTime);
      icrafting.sendProgressBarUpdate(this, 1, this.tileNetherAltar.AltarPower);
      icrafting.sendProgressBarUpdate(this, 2, this.tileNetherAltar.currentItemBurnTime);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < this.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)this.crafters.get(i);
         if (this.lastCookTime != this.tileNetherAltar.furnaceCookTime) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileNetherAltar.furnaceCookTime);
         }

         if (this.lastBurnTime != this.tileNetherAltar.AltarPower) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileNetherAltar.AltarPower);
         }

         if (this.lastItemBurnTime != this.tileNetherAltar.currentItemBurnTime) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileNetherAltar.currentItemBurnTime);
         }
      }

      this.lastCookTime = this.tileNetherAltar.furnaceCookTime;
      this.lastBurnTime = this.tileNetherAltar.AltarPower;
      this.lastItemBurnTime = this.tileNetherAltar.currentItemBurnTime;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int i1, int i2) {
      if (i1 == 0) {
         this.tileNetherAltar.furnaceCookTime = i2;
      }

      if (i1 == 1) {
         this.tileNetherAltar.AltarPower = i2;
      }

      if (i1 == 2) {
         this.tileNetherAltar.currentItemBurnTime = i2;
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.tileNetherAltar.isUseableByPlayer(player);
   }

   public ItemStack transferStackInSlot(EntityPlayer player, int p_82846_2_) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (p_82846_2_ == 2) {
            if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
               return null;
            }

            slot.onSlotChange(itemstack1, itemstack);
         }

         if (itemstack1.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.stackSize == itemstack.stackSize) {
            return null;
         }

         slot.onPickupFromSlot(player, itemstack1);
      }

      return itemstack;
   }
}
