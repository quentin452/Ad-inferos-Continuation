package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.DextersNether;
import com.superdextor.dextersnether.inventory.TileEntityNetherAltar;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockNetherAltar extends BlockContainer {
   private final Random field_149933_a = new Random();
   @SideOnly(Side.CLIENT)
   private IIcon field_149950_a;
   @SideOnly(Side.CLIENT)
   private IIcon field_149949_b;

   public BlockNetherAltar() {
      super(Material.rock);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
      this.setLightOpacity(0);
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
      super.randomDisplayTick(world, x, y, z, random);

      int l;
      for(l = x - 2; l <= x + 2; ++l) {
         for(int i1 = z - 2; i1 <= z + 2; ++i1) {
            if (l > x - 2 && l < x + 2 && i1 == z - 1) {
               i1 = z + 2;
            }

            if (random.nextInt(16) == 0) {
               for(int j1 = y; j1 <= y + 1; ++j1) {
                  if (TileEntityNetherAltar.getAltarPower(world, l, j1, i1) > 0) {
                     if (!world.isAirBlock((l - x) / 2 + x, j1, (i1 - z) / 2 + z)) {
                        break;
                     }

                     world.spawnParticle("flame", (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.07D, 0.0D);
                     world.spawnParticle("enchantmenttable", (double)x + 0.5D, (double)y + 2.0D, (double)z + 0.5D, (double)((float)(l - x) + random.nextFloat()) - 0.5D, (double)((float)(j1 - y) - random.nextFloat() - 1.0F), (double)((float)(i1 - z) + random.nextFloat()) - 0.5D);
                  }
               }
            }
         }
      }

      for(l = 0; l < 3; ++l) {
         double var10000 = (double)((float)x + random.nextFloat());
         double d1 = (double)((float)y + random.nextFloat());
         var10000 = (double)((float)z + random.nextFloat());
         double d3 = 0.0D;
         double d4 = 0.0D;
         double d5 = 0.0D;
         int i1 = random.nextInt(2) * 2 - 1;
         int j1 = random.nextInt(2) * 2 - 1;
         d3 = ((double)random.nextFloat() - 0.5D) * 0.125D;
         d4 = ((double)random.nextFloat() - 0.5D) * 0.125D;
         d5 = ((double)random.nextFloat() - 0.5D) * 0.125D;
         double d2 = (double)z + 0.5D + 0.25D * (double)j1;
         d5 = (double)(random.nextFloat() * 1.0F * (float)j1);
         double d0 = (double)x + 0.5D + 0.25D * (double)i1;
         d3 = (double)(random.nextFloat() * 1.0F * (float)i1);
         world.spawnParticle("townaura", d0, d1, d2, d3, d4, d5);
      }

   }

   public boolean isOpaqueCube() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return p_149691_1_ == 0 ? this.field_149949_b : (p_149691_1_ == 1 ? this.field_149950_a : this.blockIcon);
   }

   public TileEntity createNewTileEntity(World world, int var) {
      return new TileEntityNetherAltar();
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i1, float f1, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else {
         TileEntityNetherAltar tileentityenchantmenttable = (TileEntityNetherAltar)world.getTileEntity(x, y, z);
         player.openGui(DextersNether.modInstance, 2, world, x, y, z);
         return true;
      }
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      super.onBlockPlacedBy(world, x, y, z, entity, stack);
      if (stack.hasDisplayName()) {
         ((TileEntityNetherAltar)world.getTileEntity(x, y, z)).SetTilename(stack.getDisplayName());
      }

   }

   public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
      TileEntityNetherAltar TileEntityNetherAltar = (TileEntityNetherAltar)world.getTileEntity(x, y, z);
      if (TileEntityNetherAltar != null) {
         for(int i1 = 0; i1 < TileEntityNetherAltar.getSizeInventory(); ++i1) {
            ItemStack itemstack = TileEntityNetherAltar.getStackInSlot(i1);
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

      super.breakBlock(world, x, y, z, block, p_149749_6_);
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister icon) {
      this.blockIcon = icon.registerIcon(this.getTextureName() + "_" + "side");
      this.field_149950_a = icon.registerIcon(this.getTextureName() + "_" + "top");
      this.field_149949_b = icon.registerIcon(this.getTextureName() + "_" + "bottom");
   }
}
