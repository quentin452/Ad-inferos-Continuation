package com.superdextor.dextersnether.inventory;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.blocks.AltarBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityNetherAltar extends TileEntity implements ISidedInventory {
   private static final int[] slotsTop = new int[]{0};
   private static final int[] slotsBottom = new int[]{2, 1};
   private static final int[] slotsSides = new int[]{1};
   private ItemStack[] alterItemStacks = new ItemStack[7];
   public int currentItemBurnTime;
   public int furnaceCookTime;
   private String CustomTileName;
   public int AltarPower;
   public int age;
   public int field_145926_a;
   public float field_145933_i;
   public float field_145931_j;
   public float field_145932_k;
   public float field_145929_l;
   public float field_145930_m;
   public float field_145927_n;
   public float field_145928_o;
   public float field_145925_p;
   public float field_145924_q;
   private static Random rand = new Random();
   private float recipeDuration = 0.0F;

   public int getSizeInventory() {
      return this.alterItemStacks.length;
   }

   public ItemStack getStackInSlot(int i1) {
      return this.alterItemStacks[i1];
   }

   public ItemStack decrStackSize(int i1, int i2) {
      if (this.alterItemStacks[i1] != null) {
         ItemStack itemstack;
         if (this.alterItemStacks[i1].stackSize <= i2) {
            itemstack = this.alterItemStacks[i1];
            this.alterItemStacks[i1] = null;
            return itemstack;
         } else {
            itemstack = this.alterItemStacks[i1].splitStack(i2);
            if (this.alterItemStacks[i1].stackSize == 0) {
               this.alterItemStacks[i1] = null;
            }

            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int i1) {
      if (this.alterItemStacks[i1] != null) {
         ItemStack itemstack = this.alterItemStacks[i1];
         this.alterItemStacks[i1] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int i1, ItemStack stack) {
      this.alterItemStacks[i1] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

   }

   public String getInventoryName() {
      return this.hasCustomInventoryName() ? this.CustomTileName : "container.nether_altar";
   }

   public boolean hasCustomInventoryName() {
      return this.CustomTileName != null && this.CustomTileName.length() > 0;
   }

   public void SetTilename(String name) {
      this.CustomTileName = name;
   }

   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      NBTTagList nbttaglist = tag.getTagList("Items", 10);
      this.alterItemStacks = new ItemStack[this.getSizeInventory()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
         byte b0 = nbttagcompound1.getByte("Slot");
         if (b0 >= 0 && b0 < this.alterItemStacks.length) {
            this.alterItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
         }
      }

      this.furnaceCookTime = tag.getShort("CookTime");
      if (tag.hasKey("CustomName", 8)) {
         this.CustomTileName = tag.getString("CustomName");
      }

   }

   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setShort("CookTime", (short)this.furnaceCookTime);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.alterItemStacks.length; ++i) {
         if (this.alterItemStacks[i] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.alterItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
         }
      }

      tag.setTag("Items", nbttaglist);
      if (this.hasCustomInventoryName()) {
         tag.setString("CustomName", this.CustomTileName);
      }

   }

   public int getInventoryStackLimit() {
      return 1;
   }

   @SideOnly(Side.CLIENT)
   public int getCookProgressScaled(int time) {
      return this.furnaceCookTime * time / 200;
   }

   @SideOnly(Side.CLIENT)
   public int getAltarPower() {
      return this.AltarPower / 8;
   }

   public void updateEntity() {
      ++this.age;
      int power = 0;

      for(int j = -1; j <= 1; ++j) {
         for(int k = -1; k <= 1; ++k) {
            if ((j != 0 || k != 0) && this.worldObj.isAirBlock(this.xCoord + k, this.yCoord, this.zCoord + j) && this.worldObj.isAirBlock(this.xCoord + k, this.yCoord + 1, this.zCoord + j)) {
               power += getAltarPower(this.worldObj, this.xCoord + k * 2, this.yCoord, this.zCoord + j * 2);
               if (k != 0 && j != 0) {
                  power += getAltarPower(this.worldObj, this.xCoord + k * 2, this.yCoord, this.zCoord + j);
                  power += getAltarPower(this.worldObj, this.xCoord + k, this.yCoord, this.zCoord + j * 2);
               }
            }
         }
      }

      if (this.worldObj.provider.dimensionId == -1) {
         power += 18 + rand.nextInt(10);
      }

      if (this.worldObj.provider.dimensionId == NetherConfig.dimensionID) {
         power += 28 + rand.nextInt(10);
      }

      this.AltarPower = power;
      if (this.AltarPower > 64) {
         this.AltarPower = 64;
      }

      if (!this.worldObj.isRemote) {
         if (this.hasValidItems() && this.alterItemStacks[0] != null) {
            if (rand.nextFloat() < this.recipeDuration && this.AltarPower > rand.nextInt(63)) {
               if (this.canMorph()) {
                  ++this.furnaceCookTime;
                  if (this.furnaceCookTime == 200) {
                     this.furnaceCookTime = 0;
                     this.MorphItem();
                  }
               } else {
                  this.furnaceCookTime = 0;
               }
            }
         } else if (this.furnaceCookTime > 0) {
            --this.furnaceCookTime;
         }
      }

      if (this.worldObj.isRemote) {
         this.field_145927_n = this.field_145930_m;
         this.field_145925_p = this.field_145928_o;
         this.field_145924_q += 0.02F;

         for(this.field_145930_m -= 0.1F; this.field_145928_o >= 3.1415927F; this.field_145928_o -= 6.2831855F) {
         }

         while(this.field_145928_o < -3.1415927F) {
            this.field_145928_o += 6.2831855F;
         }

         while(this.field_145924_q >= 3.1415927F) {
            this.field_145924_q -= 6.2831855F;
         }

         while(this.field_145924_q < -3.1415927F) {
            this.field_145924_q += 6.2831855F;
         }

         float f2;
         for(f2 = this.field_145924_q - this.field_145928_o; f2 >= 3.1415927F; f2 -= 6.2831855F) {
         }

         while(f2 < -3.1415927F) {
            f2 += 6.2831855F;
         }

         this.field_145928_o += f2 * 0.4F;
         if (this.field_145930_m < 0.0F) {
            this.field_145930_m = 0.0F;
         }

         if (this.field_145930_m > 1.0F) {
            this.field_145930_m = 1.0F;
         }

         ++this.field_145926_a;
         this.field_145931_j = this.field_145933_i;
         float f = (this.field_145932_k - this.field_145933_i) * 0.4F;
         float f3 = 0.2F;
         if (f < -f3) {
            f = -f3;
         }

         if (f > f3) {
            f = f3;
         }

         this.field_145929_l += (f - this.field_145929_l) * 0.9F;
         this.field_145933_i += this.field_145929_l;
      }

   }

   private boolean canMorph() {
      if (this.alterItemStacks[0] == null) {
         return false;
      } else {
         AltarRecipes.AltarRecipe recipe = AltarRecipes.logic().getRecipe(this.alterItemStacks);
         if (recipe == null) {
            return false;
         } else {
            this.recipeDuration = recipe.duration;
            return true;
         }
      }
   }

   private boolean hasValidItems() {
      return this.canMorph();
   }

   public void MorphItem() {
      if (this.canMorph()) {
         AltarRecipes.AltarRecipe recipe = AltarRecipes.logic().getRecipe(this.alterItemStacks);
         if (recipe.input1 != null) {
            this.alterItemStacks[1] = null;
         }

         if (recipe.input2 != null) {
            this.alterItemStacks[2] = null;
         }

         if (recipe.input3 != null) {
            this.alterItemStacks[3] = null;
         }

         if (recipe.input4 != null) {
            this.alterItemStacks[4] = null;
         }

         if (recipe.input5 != null) {
            this.alterItemStacks[5] = null;
         }

         if (recipe.input6 != null) {
            this.alterItemStacks[6] = null;
         }

         ItemStack itemstack = recipe.output;
         if (itemstack != null) {
            this.alterItemStacks[0] = itemstack.copy();
         }
      }

   }

   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
   }

   public void openInventory() {
   }

   public void closeInventory() {
   }

   public boolean isItemValidForSlot(int i1, ItemStack stack) {
      return i1 != 2;
   }

   public int[] getAccessibleSlotsFromSide(int i1) {
      return i1 == 0 ? slotsBottom : (i1 == 1 ? slotsTop : slotsSides);
   }

   public boolean canInsertItem(int i1, ItemStack stack, int i2) {
      return this.isItemValidForSlot(i1, stack);
   }

   public boolean canExtractItem(int i1, ItemStack stack, int i2) {
      return i2 != 0 || i1 != 1 || stack.getItem() == Items.bucket;
   }

   public ItemStack getDisplayedItem() {
      return this.getStackInSlot(0);
   }

   public static int getAltarPower(World world, int x, int y, int z) {
      if (world.getBlock(x, y, z) instanceof AltarBlock) {
         return ((AltarBlock)world.getBlock(x, y, z)).AltarPower();
      } else {
         return world.getBlock(x, y, z) == Blocks.fire ? 1 : 0;
      }
   }

   public boolean isPowred() {
      return this.AltarPower > 0;
   }
}
