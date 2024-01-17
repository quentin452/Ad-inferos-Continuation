package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.init.NetherBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBigNetherMushroom extends WorldGenerator {
   private int mushroomType = -1;
   private static final String __OBFID = "CL_00000415";

   public WorldGenBigNetherMushroom(int type) {
      super(true);
      this.mushroomType = type;
   }

   public WorldGenBigNetherMushroom() {
      super(false);
   }

   public boolean generate(World world, Random random, int x, int y, int z) {
      int l = random.nextInt(2);
      if (this.mushroomType >= 0) {
         l = this.mushroomType;
      }

      int i1 = random.nextInt(3) + 4;
      boolean flag = true;
      if (y >= 1 && y + i1 + 1 < 256) {
         int posX;
         int PosZ;
         Block block2;
         for(int PosY = y; PosY <= y + 1 + i1; ++PosY) {
            byte b0 = 3;
            if (PosY <= y + 3) {
               b0 = 0;
            }

            for(posX = x - b0; posX <= x + b0 && flag; ++posX) {
               for(PosZ = z - b0; PosZ <= z + b0 && flag; ++PosZ) {
                  if (PosY >= 0 && PosY < 256) {
                     block2 = world.getBlock(posX, PosY, PosZ);
                     if (!block2.isAir(world, posX, PosY, PosZ) && !block2.isLeaves(world, posX, PosY, PosZ) && !block2.isLeaves(world, posX, PosY, PosZ)) {
                        flag = false;
                     }
                  } else {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else {
            Block blocposX = world.getBlock(x, y - 1, z);
            if (blocposX != Blocks.soul_sand && blocposX != Blocks.netherrack && blocposX != Blocks.mycelium) {
               return false;
            } else {
               int k2 = y + i1;
               if (l == 1) {
                  k2 = y + i1 - 3;
               }

               for(posX = k2; posX <= y + i1; ++posX) {
                  PosZ = 1;
                  if (posX < y + i1) {
                     ++PosZ;
                  }

                  if (l == 0) {
                     PosZ = 3;
                  }

                  for(int l2 = x - PosZ; l2 <= x + PosZ; ++l2) {
                     for(int i2 = z - PosZ; i2 <= z + PosZ; ++i2) {
                        int j2 = 5;
                        if (l2 == x - PosZ) {
                           --j2;
                        }

                        if (l2 == x + PosZ) {
                           ++j2;
                        }

                        if (i2 == z - PosZ) {
                           j2 -= 3;
                        }

                        if (i2 == z + PosZ) {
                           j2 += 3;
                        }

                        if (l == 0 || posX < y + i1) {
                           if ((l2 == x - PosZ || l2 == x + PosZ) && (i2 == z - PosZ || i2 == z + PosZ)) {
                              continue;
                           }

                           if (l2 == x - (PosZ - 1) && i2 == z - PosZ) {
                              j2 = 1;
                           }

                           if (l2 == x - PosZ && i2 == z - (PosZ - 1)) {
                              j2 = 1;
                           }

                           if (l2 == x + (PosZ - 1) && i2 == z - PosZ) {
                              j2 = 3;
                           }

                           if (l2 == x + PosZ && i2 == z - (PosZ - 1)) {
                              j2 = 3;
                           }

                           if (l2 == x - (PosZ - 1) && i2 == z + PosZ) {
                              j2 = 7;
                           }

                           if (l2 == x - PosZ && i2 == z + (PosZ - 1)) {
                              j2 = 7;
                           }

                           if (l2 == x + (PosZ - 1) && i2 == z + PosZ) {
                              j2 = 9;
                           }

                           if (l2 == x + PosZ && i2 == z + (PosZ - 1)) {
                              j2 = 9;
                           }
                        }

                        if (j2 == 5 && posX < y + i1) {
                           j2 = 0;
                        }

                        if ((j2 != 0 || y >= y + i1 - 1) && world.getBlock(l2, posX, i2).canBeReplacedByLeaves(world, l2, posX, i2)) {
                           this.setBlockAndNotifyAdequately(world, l2, posX, i2, Block.getBlockById(Block.getIdFromBlock(NetherBlocks.nether_mushroom_block)), j2);
                        }
                     }
                  }
               }

               for(posX = 0; posX < i1; ++posX) {
                  block2 = world.getBlock(x, y + posX, z);
                  if (block2.canBeReplacedByLeaves(world, x, y + posX, z)) {
                     this.setBlockAndNotifyAdequately(world, x, y + posX, z, Block.getBlockById(Block.getIdFromBlock(NetherBlocks.nether_mushroom_block)), 10);
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
