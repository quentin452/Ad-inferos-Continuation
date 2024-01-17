package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.entity.monster.EntitySummor;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDarkFire extends BlockFire implements AltarBlock {
   public BlockDarkFire() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.01F, 1.0F);
      this.setStepSound(Block.soundTypeSand);
   }

   public static void func_149843_e() {
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.glowstone), 30, 60);
   }

   /** @deprecated */
   @Deprecated
   private void tryCatchFire(World world, int x, int y, int z, int p_149841_5_, Random random, int var) {
      this.tryCatchFire(world, x, y, z, p_149841_5_, random, var, ForgeDirection.UP);
   }

   private void tryCatchFire(World world, int x, int y, int z, int p_149841_5_, Random random, int var, ForgeDirection face) {
      int j1 = world.getBlock(x, y, z).getFlammability(world, x, y, z, face);
      if (random.nextInt(p_149841_5_) < j1) {
         boolean flag = world.getBlock(x, y, z) == Blocks.tnt;
         if (random.nextInt(var + 10) < 5 && !world.canLightningStrikeAt(x, y, z)) {
            int k1 = var + random.nextInt(5) / 4;
            if (k1 > 15) {
               k1 = 15;
            }

            world.setBlock(x, y, z, this, k1, 3);
         } else {
            world.setBlockToAir(x, y, z);
         }

         if (flag) {
            Blocks.tnt.onBlockDestroyedByPlayer(world, x, y, z, 1);
         }
      }

   }

   private boolean canNeighborBurn(World world, int x, int y, int z) {
      return this.canCatchFire(world, x + 1, y, z, ForgeDirection.WEST) || this.canCatchFire(world, x - 1, y, z, ForgeDirection.EAST) || this.canCatchFire(world, x, y - 1, z, ForgeDirection.UP) || this.canCatchFire(world, x, y + 1, z, ForgeDirection.DOWN) || this.canCatchFire(world, x, y, z - 1, ForgeDirection.SOUTH) || this.canCatchFire(world, x, y, z + 1, ForgeDirection.NORTH);
   }

   private int getChanceOfNeighborsEncouragingFire(World world, int x, int y, int z) {
      byte b0 = 0;
      if (!world.isAirBlock(x, y, z)) {
         return 0;
      } else {
         int l = this.getChanceToEncourageFire(world, x + 1, y, z, b0, ForgeDirection.WEST);
         l = this.getChanceToEncourageFire(world, x - 1, y, z, l, ForgeDirection.EAST);
         l = this.getChanceToEncourageFire(world, x, y - 1, z, l, ForgeDirection.UP);
         l = this.getChanceToEncourageFire(world, x, y + 1, z, l, ForgeDirection.DOWN);
         l = this.getChanceToEncourageFire(world, x, y, z - 1, l, ForgeDirection.SOUTH);
         l = this.getChanceToEncourageFire(world, x, y, z + 1, l, ForgeDirection.NORTH);
         return l;
      }
   }

   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
      boolean flag = true;
      if (entity instanceof NetherMob) {
         NetherMob nethermob = (NetherMob)entity;
         if (nethermob.DarkfireRes()) {
            flag = false;
         }
      }

      if (flag) {
         if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.wither.id, 120, 0));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 20, 0));
         }

         entity.attackEntityFrom(DamageSource.wither, 1.0F);
      }

   }

   public boolean isCollidable() {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
      if (random.nextInt(24) == 0) {
         world.playSound((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "ambient.cave.cave", 0.3F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
      }

      int l;
      float f;
      float f1;
      float f2;
      if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !Blocks.fire.canCatchFire(world, x, y - 1, z, ForgeDirection.UP)) {
         if (Blocks.fire.canCatchFire(world, x - 1, y, z, ForgeDirection.EAST)) {
            for(l = 0; l < 2; ++l) {
               f = (float)x + random.nextFloat() * 0.1F;
               f1 = (float)y + random.nextFloat();
               f2 = (float)z + random.nextFloat();
               world.spawnParticle("reddust", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
            }
         }

         if (Blocks.fire.canCatchFire(world, x + 1, y, z, ForgeDirection.WEST)) {
            for(l = 0; l < 2; ++l) {
               f = (float)(x + 1) - random.nextFloat() * 0.1F;
               f1 = (float)y + random.nextFloat();
               f2 = (float)z + random.nextFloat();
               world.spawnParticle("reddust", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
            }
         }

         if (Blocks.fire.canCatchFire(world, x, y, z - 1, ForgeDirection.SOUTH)) {
            for(l = 0; l < 2; ++l) {
               f = (float)x + random.nextFloat();
               f1 = (float)y + random.nextFloat();
               f2 = (float)z + random.nextFloat() * 0.1F;
               world.spawnParticle("reddust", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
            }
         }

         if (Blocks.fire.canCatchFire(world, x, y, z + 1, ForgeDirection.NORTH)) {
            for(l = 0; l < 2; ++l) {
               f = (float)x + random.nextFloat();
               f1 = (float)y + random.nextFloat();
               f2 = (float)(z + 1) - random.nextFloat() * 0.1F;
               world.spawnParticle("reddust", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
            }
         }

         if (Blocks.fire.canCatchFire(world, x, y + 1, z, ForgeDirection.DOWN)) {
            for(l = 0; l < 2; ++l) {
               f = (float)x + random.nextFloat();
               f1 = (float)(y + 1) - random.nextFloat() * 0.1F;
               f2 = (float)z + random.nextFloat();
               world.spawnParticle("reddust", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
            }
         }
      } else {
         for(l = 0; l < 3; ++l) {
            f = (float)x + random.nextFloat();
            f1 = (float)y + random.nextFloat() * 0.5F + 0.5F;
            f2 = (float)z + random.nextFloat();
            world.spawnParticle("reddust", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
         }
      }

   }

   public void onBlockAdded(World world, int x, int y, int z) {
      if (NetherConfig.buildAbyssPortal && (world.provider.dimensionId != 0 && world.provider.dimensionId != NetherConfig.dimensionID || !NetherBlocks.abyss_portal.func_150000_e(world, x, y, z)) && !World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)) {
         world.setBlockToAir(x, y, z);
      }

      if ((NetherConfig.buildSummor && world.provider.dimensionId == -1 || world.provider.dimensionId == NetherConfig.dimensionID) && world.getBlock(x, y - 1, z) == NetherBlocks.wither_block && world.getBlock(x + 1, y - 1, z) == Blocks.gold_block && world.getBlock(x - 1, y - 1, z) == Blocks.gold_block && world.getBlock(x, y - 1, z + 1) == Blocks.gold_block && world.getBlock(x, y - 1, z - 1) == Blocks.gold_block && world.getBlock(x + 1, y - 1, z + 1) == Blocks.obsidian && world.getBlock(x + 1, y - 1, z - 1) == Blocks.obsidian && world.getBlock(x - 1, y - 1, z - 1) == Blocks.obsidian && world.getBlock(x - 1, y - 1, z + 1) == Blocks.obsidian && world.getBlock(x + 2, y, z) == Blocks.fire && world.getBlock(x - 2, y, z) == Blocks.fire && world.getBlock(x, y, z + 2) == Blocks.fire && world.getBlock(x, y, z - 2) == Blocks.fire && world.getBlock(x + 2, y, z + 1) == Blocks.fire && world.getBlock(x + 2, y, z - 1) == Blocks.fire && world.getBlock(x - 2, y, z + 1) == Blocks.fire && world.getBlock(x - 2, y, z - 1) == Blocks.fire && world.getBlock(x + 1, y, z + 2) == Blocks.fire && world.getBlock(x - 1, y, z + 2) == Blocks.fire && world.getBlock(x + 1, y, z - 2) == Blocks.fire && world.getBlock(x - 1, y, z - 2) == Blocks.fire) {
         if (!world.isRemote) {
            EntitySummor entity = new EntitySummor(world);
            entity.setLocationAndAngles((double)x + 0.5D, (double)(y + 3), (double)z + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(entity);
            entity.playSound("mob.wither.spawn", 2.0F, 0.5F);
            entity.playSound("mob.wither.spawn", 2.0F, 0.5F);
         }

         world.setBlockToAir(x, y, z);
         world.setBlock(x, y + 1, z, Blocks.nether_brick);
         world.setBlock(x, y, z, Blocks.nether_brick);
         world.setBlock(x, y - 1, z, NetherBlocks.smooth_netherrack);
         world.setBlock(x + 1, y - 1, z, NetherBlocks.smooth_netherrack);
         world.setBlock(x - 1, y - 1, z, NetherBlocks.smooth_netherrack);
         world.setBlock(x, y - 1, z + 1, NetherBlocks.smooth_netherrack);
         world.setBlock(x, y - 1, z - 1, NetherBlocks.smooth_netherrack);
         world.setBlock(x + 1, y - 1, z + 1, NetherBlocks.smooth_netherrack);
         world.setBlock(x - 1, y - 1, z + 1, NetherBlocks.smooth_netherrack);
         world.setBlock(x - 1, y - 1, z - 1, NetherBlocks.smooth_netherrack);
         world.setBlock(x + 1, y - 1, z - 1, NetherBlocks.smooth_netherrack);
         world.setBlock(x + 2, y, z, NetherBlocks.dark_fire);
         world.setBlock(x - 2, y, z, NetherBlocks.dark_fire);
         world.setBlock(x, y, z + 2, NetherBlocks.dark_fire);
         world.setBlock(x, y, z - 2, NetherBlocks.dark_fire);
         world.setBlock(x + 2, y, z + 1, NetherBlocks.dark_fire);
         world.setBlock(x + 2, y, z - 1, NetherBlocks.dark_fire);
         world.setBlock(x - 2, y, z + 1, NetherBlocks.dark_fire);
         world.setBlock(x - 2, y, z - 1, NetherBlocks.dark_fire);
         world.setBlock(x + 1, y, z + 2, NetherBlocks.dark_fire);
         world.setBlock(x - 1, y, z + 2, NetherBlocks.dark_fire);
         world.setBlock(x + 1, y, z - 2, NetherBlocks.dark_fire);
         world.setBlock(x - 1, y, z - 2, NetherBlocks.dark_fire);
      }

   }

   @SideOnly(Side.CLIENT)
   public Item getItem(World world, int x, int y, int z) {
      return Item.getItemById(Item.getIdFromItem(NetherItems.wither_dust));
   }

   public MapColor getMapColor(int i) {
      return MapColor.blackColor;
   }

   public void updateTick(World world, int x, int y, int z, Random random) {
      if (world.getGameRules().getGameRuleBooleanValue("doFireTick")) {
         boolean flag = world.getBlock(x, y - 1, z).isFireSource(world, x, y - 1, z, ForgeDirection.UP);
         if (world.getBlock(x, y - 1, z) == Blocks.obsidian) {
            flag = true;
         }

         if (world.getBlock(x, y - 1, z) == NetherBlocks.darkstone) {
            flag = true;
         }

         if (!this.canPlaceBlockAt(world, x, y, z)) {
            world.setBlockToAir(x, y, z);
         }

         if (!flag && world.isRaining() && (world.canLightningStrikeAt(x, y, z) || world.canLightningStrikeAt(x - 1, y, z) || world.canLightningStrikeAt(x + 1, y, z) || world.canLightningStrikeAt(x, y, z - 1) || world.canLightningStrikeAt(x, y, z + 1))) {
            world.setBlockToAir(x, y, z);
         } else {
            int l = world.getBlockMetadata(x, y, z);
            if (l < 15) {
               world.setBlockMetadataWithNotify(x, y, z, l + random.nextInt(3) / 2, 4);
            }

            world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + random.nextInt(10));
            if (!flag && !this.canNeighborBurn(world, x, y, z)) {
               if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || l > 3) {
                  world.setBlockToAir(x, y, z);
               }
            } else if (!flag && !this.canCatchFire(world, x, y - 1, z, ForgeDirection.UP) && l == 15 && random.nextInt(4) == 0) {
               world.setBlockToAir(x, y, z);
            } else {
               boolean flag1 = world.isBlockHighHumidity(x, y, z);
               byte b0 = 0;
               if (flag1) {
                  b0 = -50;
               }

               this.tryCatchFire(world, x + 1, y, z, 300 + b0, random, l, ForgeDirection.WEST);
               this.tryCatchFire(world, x - 1, y, z, 300 + b0, random, l, ForgeDirection.EAST);
               this.tryCatchFire(world, x, y - 1, z, 250 + b0, random, l, ForgeDirection.UP);
               this.tryCatchFire(world, x, y + 1, z, 250 + b0, random, l, ForgeDirection.DOWN);
               this.tryCatchFire(world, x, y, z - 1, 300 + b0, random, l, ForgeDirection.SOUTH);
               this.tryCatchFire(world, x, y, z + 1, 300 + b0, random, l, ForgeDirection.NORTH);

               for(int i1 = x - 1; i1 <= x + 1; ++i1) {
                  for(int j1 = z - 1; j1 <= z + 1; ++j1) {
                     for(int k1 = y - 1; k1 <= y + 4; ++k1) {
                        if (i1 != x || k1 != y || j1 != z) {
                           int l1 = 100;
                           if (k1 > y + 1) {
                              l1 += (k1 - (y + 1)) * 100;
                           }

                           int i2 = this.getChanceOfNeighborsEncouragingFire(world, i1, k1, j1);
                           if (i2 > 0) {
                              int j2 = (i2 + 40 + world.difficultySetting.getDifficultyId() * 7) / (l + 30);
                              if (flag1) {
                                 j2 /= 2;
                              }

                              if (j2 > 0 && random.nextInt(l1) <= j2 && (!world.isRaining() || !world.canLightningStrikeAt(i1, k1, j1)) && !world.canLightningStrikeAt(i1 - 1, k1, z) && !world.canLightningStrikeAt(i1 + 1, k1, j1) && !world.canLightningStrikeAt(i1, k1, j1 - 1) && !world.canLightningStrikeAt(i1, k1, j1 + 1)) {
                                 int k2 = l + random.nextInt(5) / 4;
                                 if (k2 > 15) {
                                    k2 = 15;
                                 }

                                 world.setBlock(i1, k1, j1, this, k2, 3);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   public int AltarPower() {
      return 2;
   }
}
