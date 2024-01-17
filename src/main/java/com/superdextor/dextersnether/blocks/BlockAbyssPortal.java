package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.world.TeleporterAbyss;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class BlockAbyssPortal extends BlockPortal implements AltarBlock {
   public boolean func_150000_e(World world, int x, int y, int z) {
      BlockAbyssPortal.Size size = new BlockAbyssPortal.Size(world, x, y, z, 1);
      BlockAbyssPortal.Size size1 = new BlockAbyssPortal.Size(world, x, y, z, 2);
      if (size.func_150860_b() && size.field_150864_e == 0) {
         size.func_150859_c();
         return true;
      } else if (size1.func_150860_b() && size1.field_150864_e == 0) {
         size1.func_150859_c();
         return true;
      } else {
         return false;
      }
   }

   public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
      if (!NetherConfig.abyssPortalsAnywhere) {
         int l = func_149999_b(world.getBlockMetadata(x, y, z));
         BlockAbyssPortal.Size size = new BlockAbyssPortal.Size(world, x, y, z, 1);
         BlockAbyssPortal.Size size1 = new BlockAbyssPortal.Size(world, x, y, z, 2);
         if (l != 1 || size.func_150860_b() && size.field_150864_e >= size.field_150868_h * size.field_150862_g) {
            if (l == 2 && (!size1.func_150860_b() || size1.field_150864_e < size1.field_150868_h * size1.field_150862_g)) {
               world.setBlock(x, y, z, Blocks.air);
            } else if (l == 0 && !size.func_150860_b() && !size1.func_150860_b()) {
               world.setBlock(x, y, z, Blocks.air);
            }
         } else {
            world.setBlock(x, y, z, Blocks.air);
         }
      }

   }

   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
      if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityLivingBase && NetherConfig.abyssPortalTravel) {
         if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 130, 0));
         }

         if (entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)entity;
            MinecraftServer server = MinecraftServer.getServer();
            if (entity.timeUntilPortal > 0) {
               entity.timeUntilPortal = 10;
            } else if (entity.dimension != NetherConfig.dimensionID) {
               server.getConfigurationManager().transferPlayerToDimension(player, NetherConfig.dimensionID, new TeleporterAbyss(server.worldServerForDimension(NetherConfig.dimensionID)));
               entity.timeUntilPortal = 10;
            } else {
               server.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterAbyss(server.worldServerForDimension(0)));
               entity.timeUntilPortal = 10;
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
      if (random.nextInt(100) == 0) {
         world.playSound((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "portal.portal", 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
      }

      for(int l = 0; l < 4; ++l) {
         double d0 = (double)((float)x + random.nextFloat());
         double d1 = (double)((float)y + random.nextFloat());
         double d2 = (double)((float)z + random.nextFloat());
         double d3 = 0.0D;
         double d4 = 0.0D;
         double d5 = 0.0D;
         int i1 = random.nextInt(2) * 2 - 1;
         d3 = ((double)random.nextFloat() - 0.5D) * 0.5D;
         d4 = ((double)random.nextFloat() - 0.5D) * 0.5D;
         d5 = ((double)random.nextFloat() - 0.5D) * 0.5D;
         double var10000;
         if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this) {
            d0 = (double)x + 0.5D + 0.25D * (double)i1;
            var10000 = (double)(random.nextFloat() * 2.0F * (float)i1);
         } else {
            d2 = (double)z + 0.5D + 0.25D * (double)i1;
            var10000 = (double)(random.nextFloat() * 2.0F * (float)i1);
         }

         world.spawnParticle("largesmoke", d0, d1, d2, 0.0D + random.nextDouble() - 0.5D, 0.2D, 0.0D + random.nextDouble() - 0.5D);
      }

   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister icon) {
      this.blockIcon = icon.registerIcon("dextersnether:abyss_portal");
   }

   public MapColor getMapColor(int i) {
      return MapColor.blackColor;
   }

   public int AltarPower() {
      return 6;
   }

   public static class Size {
      private final World field_150867_a;
      private final int field_150865_b;
      private final int field_150866_c;
      private final int field_150863_d;
      private int field_150864_e = 0;
      private ChunkCoordinates field_150861_f;
      private int field_150862_g;
      private int field_150868_h;
      private static final String __OBFID = "CL_00000285";

      public Size(World world, int x, int y, int z, int p_i45415_5_) {
         this.field_150867_a = world;
         this.field_150865_b = p_i45415_5_;
         this.field_150863_d = BlockAbyssPortal.field_150001_a[p_i45415_5_][0];
         this.field_150866_c = BlockAbyssPortal.field_150001_a[p_i45415_5_][1];

         int j1;
         for(j1 = y; y > j1 - 21 && y > 0 && this.func_150857_a(world.getBlock(x, y - 1, z)); --y) {
         }

         j1 = this.func_150853_a(x, y, z, this.field_150863_d) - 1;
         if (j1 >= 0) {
            this.field_150861_f = new ChunkCoordinates(x + j1 * Direction.offsetX[this.field_150863_d], y, z + j1 * Direction.offsetZ[this.field_150863_d]);
            this.field_150868_h = this.func_150853_a(this.field_150861_f.posX, this.field_150861_f.posY, this.field_150861_f.posZ, this.field_150866_c);
            if (this.field_150868_h < 2 || this.field_150868_h > 21) {
               this.field_150861_f = null;
               this.field_150868_h = 0;
            }
         }

         if (this.field_150861_f != null) {
            this.field_150862_g = this.func_150858_a();
         }

      }

      protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_) {
         int j1 = Direction.offsetX[p_150853_4_];
         int k1 = Direction.offsetZ[p_150853_4_];

         int i1;
         Block block;
         for(i1 = 0; i1 < 22; ++i1) {
            block = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
            if (!this.func_150857_a(block)) {
               break;
            }

            Block block1 = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);
            if (block1 != NetherBlocks.dark_bricks) {
               break;
            }
         }

         block = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
         return block == NetherBlocks.dark_bricks ? i1 : 0;
      }

      protected int func_150858_a() {
         int i;
         int j;
         int k;
         int l;
         label56:
         for(this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g) {
            i = this.field_150861_f.posY + this.field_150862_g;

            for(j = 0; j < this.field_150868_h; ++j) {
               k = this.field_150861_f.posX + j * Direction.offsetX[BlockAbyssPortal.field_150001_a[this.field_150865_b][1]];
               l = this.field_150861_f.posZ + j * Direction.offsetZ[BlockAbyssPortal.field_150001_a[this.field_150865_b][1]];
               Block block = this.field_150867_a.getBlock(k, i, l);
               if (!this.func_150857_a(block)) {
                  break label56;
               }

               if (block == NetherBlocks.abyss_portal) {
                  ++this.field_150864_e;
               }

               if (j == 0) {
                  block = this.field_150867_a.getBlock(k + Direction.offsetX[BlockAbyssPortal.field_150001_a[this.field_150865_b][0]], i, l + Direction.offsetZ[BlockAbyssPortal.field_150001_a[this.field_150865_b][0]]);
                  if (block != NetherBlocks.dark_bricks) {
                     break label56;
                  }
               } else if (j == this.field_150868_h - 1) {
                  block = this.field_150867_a.getBlock(k + Direction.offsetX[BlockAbyssPortal.field_150001_a[this.field_150865_b][1]], i, l + Direction.offsetZ[BlockAbyssPortal.field_150001_a[this.field_150865_b][1]]);
                  if (block != NetherBlocks.dark_bricks) {
                     break label56;
                  }
               }
            }
         }

         for(i = 0; i < this.field_150868_h; ++i) {
            j = this.field_150861_f.posX + i * Direction.offsetX[BlockAbyssPortal.field_150001_a[this.field_150865_b][1]];
            k = this.field_150861_f.posY + this.field_150862_g;
            l = this.field_150861_f.posZ + i * Direction.offsetZ[BlockAbyssPortal.field_150001_a[this.field_150865_b][1]];
            if (this.field_150867_a.getBlock(j, k, l) != NetherBlocks.dark_bricks) {
               this.field_150862_g = 0;
               break;
            }
         }

         if (this.field_150862_g <= 21 && this.field_150862_g >= 3) {
            return this.field_150862_g;
         } else {
            this.field_150861_f = null;
            this.field_150868_h = 0;
            this.field_150862_g = 0;
            return 0;
         }
      }

      protected boolean func_150857_a(Block p_150857_1_) {
         return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == NetherBlocks.dark_fire || p_150857_1_ == NetherBlocks.abyss_portal;
      }

      public boolean func_150860_b() {
         return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
      }

      public void func_150859_c() {
         for(int i = 0; i < this.field_150868_h; ++i) {
            int j = this.field_150861_f.posX + Direction.offsetX[this.field_150866_c] * i;
            int k = this.field_150861_f.posZ + Direction.offsetZ[this.field_150866_c] * i;

            for(int l = 0; l < this.field_150862_g; ++l) {
               int i1 = this.field_150861_f.posY + l;
               this.field_150867_a.setBlock(j, i1, k, NetherBlocks.abyss_portal, this.field_150865_b, 2);
            }
         }

      }
   }
}
