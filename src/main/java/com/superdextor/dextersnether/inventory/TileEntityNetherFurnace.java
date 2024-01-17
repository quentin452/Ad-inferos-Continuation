package com.superdextor.dextersnether.inventory;

import com.superdextor.dextersnether.blocks.BlockNetherFurnace;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityNetherFurnace extends TileEntity implements ISidedInventory {
   private static final int[] slotsTop = new int[]{0};
   private static final int[] slotsBottom = new int[]{2, 1};
   private static final int[] slotsSides = new int[]{1};
   private ItemStack[] furnaceItemStacks = new ItemStack[3];
   public int furnaceBurnTime;
   public int currentItemBurnTime;
   public float cookTime;
   private String displayName;
   private boolean hasFireSource;

   public int getSizeInventory() {
      return this.furnaceItemStacks.length;
   }

   public ItemStack getStackInSlot(int slot) {
      return this.furnaceItemStacks[slot];
   }

   public ItemStack decrStackSize(int slot, int amount) {
      if (this.furnaceItemStacks[slot] != null) {
         ItemStack itemstack;
         if (this.furnaceItemStacks[slot].stackSize <= amount) {
            itemstack = this.furnaceItemStacks[slot];
            this.furnaceItemStacks[slot] = null;
            return itemstack;
         } else {
            itemstack = this.furnaceItemStacks[slot].splitStack(amount);
            if (this.furnaceItemStacks[slot].stackSize == 0) {
               this.furnaceItemStacks[slot] = null;
            }

            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int slot) {
      if (this.furnaceItemStacks[slot] != null) {
         ItemStack itemstack = this.furnaceItemStacks[slot];
         this.furnaceItemStacks[slot] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int slot, ItemStack stack) {
      this.furnaceItemStacks[slot] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

   }

   public String getInventoryName() {
      return this.hasCustomInventoryName() ? this.displayName : "container.nether_furnace";
   }

   public boolean hasCustomInventoryName() {
      return this.displayName != null && this.displayName.length() > 0;
   }

   public void func_145951_a(String name) {
      this.displayName = name;
   }

   public void readFromNBT(NBTTagCompound tagCompound) {
      super.readFromNBT(tagCompound);
      NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
      this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
         byte b0 = nbttagcompound1.getByte("Slot");
         if (b0 >= 0 && b0 < this.furnaceItemStacks.length) {
            this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
         }
      }

      this.furnaceBurnTime = tagCompound.getShort("BurnTime");
      this.cookTime = (float)tagCompound.getShort("CookTime");
      this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
      if (tagCompound.hasKey("CustomName", 8)) {
         this.displayName = tagCompound.getString("CustomName");
      }

   }

   public void writeToNBT(NBTTagCompound tagCompound) {
      super.writeToNBT(tagCompound);
      tagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
      tagCompound.setShort("CookTime", (short)((int)this.cookTime));
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.furnaceItemStacks.length; ++i) {
         if (this.furnaceItemStacks[i] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
         }
      }

      tagCompound.setTag("Items", nbttaglist);
      if (this.hasCustomInventoryName()) {
         tagCompound.setString("CustomName", this.displayName);
      }

   }

   public int getInventoryStackLimit() {
      return 64;
   }

   @SideOnly(Side.CLIENT)
   public int getCookProgressScaled(int i) {
      return (int)(this.cookTime * (float)i / 200.0F);
   }

   @SideOnly(Side.CLIENT)
   public int getBurnTimeRemainingScaled(int i) {
      if (this.currentItemBurnTime == 0) {
         this.currentItemBurnTime = 200;
      }

      return this.furnaceBurnTime * i / this.currentItemBurnTime;
   }

   public boolean isBurning(boolean fuelOnly) {
      if (fuelOnly) {
         return this.furnaceBurnTime > 0;
      } else {
         return this.furnaceBurnTime > 0 || this.hasFireSource;
      }
   }

   public void updateEntity() {
      boolean flag = this.isBurning(false);
      boolean flag1 = false;
      if (this.isBurning(true)) {
         --this.furnaceBurnTime;
      }

      if (!this.worldObj.isRemote) {
         this.hasFireSource = this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord).getMaterial().equals(Material.fire);
         if (!this.isBurning(false) && (this.furnaceItemStacks[1] == null || this.furnaceItemStacks[0] == null)) {
            if (!this.isBurning(false) && this.cookTime > 0.0F) {
               this.cookTime = -2.0F;
            }
         } else {
            if (!this.isBurning(true) && this.canSmelt()) {
               this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
               if (this.isBurning(true)) {
                  flag1 = true;
                  if (this.furnaceItemStacks[1] != null) {
                     --this.furnaceItemStacks[1].stackSize;
                     if (this.furnaceItemStacks[1].stackSize == 0) {
                        this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItem(this.furnaceItemStacks[1]);
                     }
                  }
               }
            }

            if (this.isBurning(false) && this.canSmelt()) {
               if (this.furnaceBurnTime > 0) {
                  this.cookTime += 0.8F;
               }

               if (this.hasFireSource) {
                  this.cookTime += 0.6F;
               }

               if (this.cookTime >= 200.0F) {
                  this.cookTime = 0.0F;
                  this.smeltItem();
                  flag1 = true;
               }
            } else {
               this.cookTime = 0.0F;
            }
         }

         if (flag != this.isBurning(false)) {
            flag1 = true;
            BlockNetherFurnace.updateFurnaceBlockState(this.isBurning(false), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
         }
      }

      if (flag1) {
         this.markDirty();
      }

   }

   private boolean canSmelt() {
      if (this.furnaceItemStacks[0] == null) {
         return false;
      } else {
         ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
         if (itemstack == null) {
            return false;
         } else if (this.furnaceItemStacks[2] == null) {
            return true;
         } else if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) {
            return false;
         } else {
            int result = this.furnaceItemStacks[2].stackSize + itemstack.stackSize;
            return result <= this.getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize();
         }
      }
   }

   public void smeltItem() {
      if (this.canSmelt()) {
         ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
         if (this.furnaceItemStacks[2] == null) {
            this.furnaceItemStacks[2] = itemstack.copy();
         } else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem()) {
            ItemStack var10000 = this.furnaceItemStacks[2];
            var10000.stackSize += itemstack.stackSize;
         }

         --this.furnaceItemStacks[0].stackSize;
         if (this.furnaceItemStacks[0].stackSize <= 0) {
            this.furnaceItemStacks[0] = null;
         }
      }

   }

   public static int getItemBurnTime(ItemStack stack) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
            Block block = Block.getBlockFromItem(item);
            if (block == Blocks.wooden_slab) {
               return 150;
            }

            if (block.getMaterial() == Material.wood) {
               return 300;
            }

            if (block == Blocks.coal_block) {
               return 16000;
            }
         }

         if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) {
            return 200;
         } else if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) {
            return 200;
         } else if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) {
            return 200;
         } else if (item == Items.stick) {
            return 100;
         } else if (item == Items.coal) {
            return 1600;
         } else if (item == Items.lava_bucket) {
            return 30000;
         } else if (item == Item.getItemFromBlock(Blocks.sapling)) {
            return 100;
         } else if (item == Items.blaze_rod) {
            return 3600;
         } else if (item == NetherItems.golden_bucket_lava) {
            return 30000;
         } else {
            return item == NetherItems.infernal_rod ? 12000 : GameRegistry.getFuelValue(stack);
         }
      }
   }

   public static boolean isItemFuel(ItemStack stack) {
      return getItemBurnTime(stack) > 0;
   }

   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
   }

   public void openInventory() {
   }

   public void closeInventory() {
   }

   public boolean isItemValidForSlot(int slot, ItemStack stack) {
      return slot == 2 ? false : (slot == 1 ? isItemFuel(stack) : true);
   }

   public int[] getAccessibleSlotsFromSide(int i) {
      return i == 0 ? slotsBottom : (i == 1 ? slotsTop : slotsSides);
   }

   public boolean canInsertItem(int slot, ItemStack stack, int side) {
      return this.isItemValidForSlot(slot, stack);
   }

   public boolean canExtractItem(int slot, ItemStack stack, int side) {
      return side != 0 || slot != 1 || stack.getItem() == Items.bucket;
   }
}
