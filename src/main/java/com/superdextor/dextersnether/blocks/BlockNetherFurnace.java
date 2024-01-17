package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.DextersNether;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.inventory.TileEntityNetherFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockNetherFurnace extends BlockContainer {
   private final Random field_149933_a = new Random();
   private final boolean field_149932_b;
   private static boolean field_149934_M;
   @SideOnly(Side.CLIENT)
   private IIcon field_149935_N;
   @SideOnly(Side.CLIENT)
   private IIcon field_149936_O;

   public BlockNetherFurnace(boolean p_i45407_1_) {
      super(Material.rock);
      this.field_149932_b = p_i45407_1_;
   }

   public Item getItemDropped(int i, Random rand, int i2) {
      return Item.getItemFromBlock(NetherBlocks.nether_furnace);
   }

   public void onBlockAdded(World world, int x, int y, int z) {
      super.onBlockAdded(world, x, y, z);
      this.func_149930_e(world, x, y, z);
   }

   private void func_149930_e(World world, int x, int y, int z) {
      if (!world.isRemote) {
         Block block = world.getBlock(x, y, z - 1);
         Block block1 = world.getBlock(x, y, z + 1);
         Block block2 = world.getBlock(x - 1, y, z);
         Block block3 = world.getBlock(x + 1, y, z);
         byte b0 = 3;
         if (block.func_149730_j() && !block1.func_149730_j()) {
            b0 = 3;
         }

         if (block1.func_149730_j() && !block.func_149730_j()) {
            b0 = 2;
         }

         if (block2.func_149730_j() && !block3.func_149730_j()) {
            b0 = 5;
         }

         if (block3.func_149730_j() && !block2.func_149730_j()) {
            b0 = 4;
         }

         world.setBlockMetadataWithNotify(x, y, z, b0, 2);
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int i, int i2) {
      return i == 1 ? this.field_149935_N : (i == 0 ? this.field_149935_N : (i != i2 ? this.blockIcon : this.field_149936_O));
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister icon) {
      this.blockIcon = icon.registerIcon("dextersnether:nether_furnace_side");
      this.field_149936_O = icon.registerIcon(this.field_149932_b ? "dextersnether:nether_furnace_on" : "dextersnether:nether_furnace_off");
      this.field_149935_N = icon.registerIcon("dextersnether:nether_furnace_top");
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      if (world.isRemote) {
         return true;
      } else {
         TileEntityNetherFurnace tileentityfurnace = (TileEntityNetherFurnace)world.getTileEntity(x, y, z);
         if (tileentityfurnace != null) {
            player.openGui(DextersNether.modInstance, 3, world, x, y, z);
         }

         return true;
      }
   }

   public static void updateFurnaceBlockState(boolean isBurning, World world, int x, int y, int z) {
      int l = world.getBlockMetadata(x, y, z);
      TileEntity tileentity = world.getTileEntity(x, y, z);
      field_149934_M = true;
      if (isBurning) {
         world.setBlock(x, y, z, NetherBlocks.lit_nether_furnace);
      } else {
         world.setBlock(x, y, z, NetherBlocks.nether_furnace);
      }

      field_149934_M = false;
      world.setBlockMetadataWithNotify(x, y, z, l, 2);
      if (tileentity != null) {
         tileentity.validate();
         world.setTileEntity(x, y, z, tileentity);
      }

   }

   public TileEntity createNewTileEntity(World world, int i) {
      return new TileEntityNetherFurnace();
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
      int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      if (l == 0) {
         world.setBlockMetadataWithNotify(x, y, z, 2, 2);
      }

      if (l == 1) {
         world.setBlockMetadataWithNotify(x, y, z, 5, 2);
      }

      if (l == 2) {
         world.setBlockMetadataWithNotify(x, y, z, 3, 2);
      }

      if (l == 3) {
         world.setBlockMetadataWithNotify(x, y, z, 4, 2);
      }

      if (stack.hasDisplayName()) {
         ((TileEntityNetherFurnace)world.getTileEntity(x, y, z)).func_145951_a(stack.getDisplayName());
      }

   }

   public void breakBlock(World world, int x, int y, int z, Block block, int side) {
      if (!field_149934_M) {
         TileEntityNetherFurnace tileentityfurnace = (TileEntityNetherFurnace)world.getTileEntity(x, y, z);
         if (tileentityfurnace != null) {
            for(int i1 = 0; i1 < tileentityfurnace.getSizeInventory(); ++i1) {
               ItemStack itemstack = tileentityfurnace.getStackInSlot(i1);
               if (itemstack != null) {
                  float f = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
                  float f1 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
                  float f2 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;

                  while(itemstack.stackSize > 0) {
                     int j1 = this.field_149933_a.nextInt(21) + 10;
                     if (j1 > itemstack.stackSize) {
                        j1 = itemstack.stackSize;
                     }

                     itemstack.stackSize -= j1;
                     EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                     if (itemstack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                     }

                     float f3 = 0.05F;
                     entityitem.motionX = (double)((float)this.field_149933_a.nextGaussian() * f3);
                     entityitem.motionY = (double)((float)this.field_149933_a.nextGaussian() * f3 + 0.2F);
                     entityitem.motionZ = (double)((float)this.field_149933_a.nextGaussian() * f3);
                     world.spawnEntityInWorld(entityitem);
                  }
               }
            }

            world.func_147453_f(x, y, z, block);
         }
      }

      super.breakBlock(world, x, y, z, block, side);
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
      if (this.field_149932_b) {
         int l = world.getBlockMetadata(x, y, z);
         float f = (float)x + 0.5F;
         float f1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
         float f2 = (float)z + 0.5F;
         float f3 = 0.52F;
         float f4 = random.nextFloat() * 0.6F - 0.3F;
         if (l == 4) {
            world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
         } else if (l == 5) {
            world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
         } else if (l == 2) {
            world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
         } else if (l == 3) {
            world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
         }
      }

   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World world, int x, int y, int z, int f) {
      return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
   }

   @SideOnly(Side.CLIENT)
   public Item getItem(World world, int x, int y, int z) {
      return Item.getItemFromBlock(NetherBlocks.nether_furnace);
   }

   public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
      return true;
   }
}
