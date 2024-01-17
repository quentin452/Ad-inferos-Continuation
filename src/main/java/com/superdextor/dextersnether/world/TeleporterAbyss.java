package com.superdextor.dextersnether.world;

import com.superdextor.dextersnether.init.NetherBlocks;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterAbyss extends Teleporter {
   private final WorldServer worldServerInstance;
   private final Random random;
   private final LongHashMap destinationCoordinateCache = new LongHashMap();
   private final List destinationCoordinateKeys = new ArrayList();
   private static final String __OBFID = "CL_00000153";

   public TeleporterAbyss(WorldServer worldIn) {
      super(worldIn);
      this.worldServerInstance = worldIn;
      this.random = new Random(worldIn.getSeed());
   }

   public void placeInPortal(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_) {
      if (this.worldServerInstance.provider.dimensionId != 1) {
         if (!this.placeInExistingPortal(p_77185_1_, p_77185_2_, p_77185_4_, p_77185_6_, p_77185_8_)) {
            this.makePortal(p_77185_1_);
            this.placeInExistingPortal(p_77185_1_, p_77185_2_, p_77185_4_, p_77185_6_, p_77185_8_);
         }
      } else {
         int i = MathHelper.floor_double(p_77185_1_.posX);
         int j = MathHelper.floor_double(p_77185_1_.posY) - 1;
         int k = MathHelper.floor_double(p_77185_1_.posZ);
         byte b0 = 1;
         byte b1 = 0;

         for(int l = -2; l <= 2; ++l) {
            for(int i1 = -2; i1 <= 2; ++i1) {
               for(int j1 = -1; j1 < 3; ++j1) {
                  int k1 = i + i1 * b0 + l * b1;
                  int l1 = j + j1;
                  int i2 = k + i1 * b1 - l * b0;
                  boolean flag = j1 < 0;
                  this.worldServerInstance.setBlock(k1, l1, i2, flag ? NetherBlocks.dark_bricks : Blocks.air);
               }
            }
         }

         p_77185_1_.setLocationAndAngles((double)i, (double)j, (double)k, p_77185_1_.rotationYaw, 0.0F);
         p_77185_1_.motionX = p_77185_1_.motionY = p_77185_1_.motionZ = 0.0D;
      }

   }

   public boolean placeInExistingPortal(Entity p_77184_1_, double p_77184_2_, double p_77184_4_, double p_77184_6_, float p_77184_8_) {
      short short1 = 128;
      double d3 = -1.0D;
      int i = 0;
      int j = 0;
      int k = 0;
      int l = MathHelper.floor_double(p_77184_1_.posX);
      int i1 = MathHelper.floor_double(p_77184_1_.posZ);
      long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
      boolean flag = true;
      double d7;
      int l3;
      int j2;
      double d11;
      if (this.destinationCoordinateCache.containsItem(j1)) {
         TeleporterAbyss.PortalPosition portalposition = (TeleporterAbyss.PortalPosition)this.destinationCoordinateCache.getValueByKey(j1);
         d3 = 0.0D;
         i = portalposition.posX;
         j = portalposition.posY;
         k = portalposition.posZ;
         portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
         flag = false;
      } else {
         for(l3 = l - short1; l3 <= l + short1; ++l3) {
            d11 = (double)l3 + 0.5D - p_77184_1_.posX;

            for(int l1 = i1 - short1; l1 <= i1 + short1; ++l1) {
               double d5 = (double)l1 + 0.5D - p_77184_1_.posZ;

               for(j2 = this.worldServerInstance.getActualHeight() - 1; j2 >= 0; --j2) {
                  if (this.worldServerInstance.getBlock(l3, j2, l1) == NetherBlocks.abyss_portal) {
                     while(this.worldServerInstance.getBlock(l3, j2 - 1, l1) == NetherBlocks.abyss_portal) {
                        --j2;
                     }

                     d7 = (double)j2 + 0.5D - p_77184_1_.posY;
                     double d8 = d11 * d11 + d7 * d7 + d5 * d5;
                     if (d3 < 0.0D || d8 < d3) {
                        d3 = d8;
                        i = l3;
                        j = j2;
                        k = l1;
                     }
                  }
               }
            }
         }
      }

      if (d3 >= 0.0D) {
         if (flag) {
            this.destinationCoordinateCache.add(j1, new TeleporterAbyss.PortalPosition(i, j, k, this.worldServerInstance.getTotalWorldTime()));
            this.destinationCoordinateKeys.add(j1);
         }

         d11 = (double)i + 0.5D;
         double d6 = (double)j + 0.5D;
         d7 = (double)k + 0.5D;
         int i4 = -1;
         if (this.worldServerInstance.getBlock(i - 1, j, k) == NetherBlocks.abyss_portal) {
            i4 = 2;
         }

         if (this.worldServerInstance.getBlock(i + 1, j, k) == NetherBlocks.abyss_portal) {
            i4 = 0;
         }

         if (this.worldServerInstance.getBlock(i, j, k - 1) == NetherBlocks.abyss_portal) {
            i4 = 3;
         }

         if (this.worldServerInstance.getBlock(i, j, k + 1) == NetherBlocks.abyss_portal) {
            i4 = 1;
         }

         j2 = p_77184_1_.getTeleportDirection();
         if (i4 > -1) {
            int k2 = Direction.rotateLeft[i4];
            int l2 = Direction.offsetX[i4];
            int i3 = Direction.offsetZ[i4];
            int j3 = Direction.offsetX[k2];
            int k3 = Direction.offsetZ[k2];
            boolean flag1 = !this.worldServerInstance.isAirBlock(i + l2 + j3, j, k + i3 + k3) || !this.worldServerInstance.isAirBlock(i + l2 + j3, j + 1, k + i3 + k3);
            boolean flag2 = !this.worldServerInstance.isAirBlock(i + l2, j, k + i3) || !this.worldServerInstance.isAirBlock(i + l2, j + 1, k + i3);
            if (flag1 && flag2) {
               i4 = Direction.rotateOpposite[i4];
               k2 = Direction.rotateOpposite[k2];
               l2 = Direction.offsetX[i4];
               i3 = Direction.offsetZ[i4];
               j3 = Direction.offsetX[k2];
               k3 = Direction.offsetZ[k2];
               l3 = i - j3;
               d11 -= (double)j3;
               int k1 = k - k3;
               d7 -= (double)k3;
               flag1 = !this.worldServerInstance.isAirBlock(l3 + l2 + j3, j, k1 + i3 + k3) || !this.worldServerInstance.isAirBlock(l3 + l2 + j3, j + 1, k1 + i3 + k3);
               flag2 = !this.worldServerInstance.isAirBlock(l3 + l2, j, k1 + i3) || !this.worldServerInstance.isAirBlock(l3 + l2, j + 1, k1 + i3);
            }

            float f1 = 0.5F;
            float f2 = 0.5F;
            if (!flag1 && flag2) {
               f1 = 1.0F;
            } else if (flag1 && !flag2) {
               f1 = 0.0F;
            } else if (flag1 && flag2) {
               f2 = 0.0F;
            }

            d11 += (double)((float)j3 * f1 + f2 * (float)l2);
            d7 += (double)((float)k3 * f1 + f2 * (float)i3);
            float f3 = 0.0F;
            float f4 = 0.0F;
            float f5 = 0.0F;
            float f6 = 0.0F;
            if (i4 == j2) {
               f3 = 1.0F;
               f4 = 1.0F;
            } else if (i4 == Direction.rotateOpposite[j2]) {
               f3 = -1.0F;
               f4 = -1.0F;
            } else if (i4 == Direction.rotateRight[j2]) {
               f5 = 1.0F;
               f6 = -1.0F;
            } else {
               f5 = -1.0F;
               f6 = 1.0F;
            }

            double d9 = p_77184_1_.motionX;
            double d10 = p_77184_1_.motionZ;
            p_77184_1_.motionX = d9 * (double)f3 + d10 * (double)f6;
            p_77184_1_.motionZ = d9 * (double)f5 + d10 * (double)f4;
            p_77184_1_.rotationYaw = p_77184_8_ - (float)(j2 * 90) + (float)(i4 * 90);
         } else {
            p_77184_1_.motionX = p_77184_1_.motionY = p_77184_1_.motionZ = 0.0D;
         }

         p_77184_1_.setLocationAndAngles(d11, d6, d7, p_77184_1_.rotationYaw, p_77184_1_.rotationPitch);
         return true;
      } else {
         return false;
      }
   }

   public boolean makePortal(Entity p_85188_1_) {
      byte b0 = 16;
      double d0 = -1.0D;
      int i = MathHelper.floor_double(p_85188_1_.posX);
      int j = MathHelper.floor_double(p_85188_1_.posY);
      int k = MathHelper.floor_double(p_85188_1_.posZ);
      int l = i;
      int i1 = j;
      int j1 = k;
      int k1 = 0;
      int l1 = this.random.nextInt(4);

      int i2;
      double d1;
      int k2;
      double d2;
      int i3;
      int j3;
      int k3;
      int l3;
      int i4;
      int j4;
      int k4;
      int l4;
      int i5;
      double d3;
      double d4;
      int j5;
      for(i2 = i - b0; i2 <= i + b0; ++i2) {
         d1 = (double)i2 + 0.5D - p_85188_1_.posX;

         for(k2 = k - b0; k2 <= k + b0; ++k2) {
            d2 = (double)k2 + 0.5D - p_85188_1_.posZ;

            label294:
            for(i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3) {
               if (this.worldServerInstance.isAirBlock(i2, i3, k2)) {
                  while(i3 > 0 && this.worldServerInstance.isAirBlock(i2, i3 - 1, k2)) {
                     --i3;
                  }

                  for(j3 = l1; j3 < l1 + 4; ++j3) {
                     k3 = j3 % 2;
                     l3 = 1 - k3;
                     if (j3 % 4 >= 2) {
                        k3 = -k3;
                        l3 = -l3;
                     }

                     for(i4 = 0; i4 < 3; ++i4) {
                        for(j4 = 0; j4 < 4; ++j4) {
                           for(k4 = -1; k4 < 4; ++k4) {
                              l4 = i2 + (j4 - 1) * k3 + i4 * l3;
                              i5 = i3 + k4;
                              j5 = k2 + (j4 - 1) * l3 - i4 * k3;
                              if (k4 < 0 && !this.worldServerInstance.getBlock(l4, i5, j5).getMaterial().isSolid() || k4 >= 0 && !this.worldServerInstance.isAirBlock(l4, i5, j5)) {
                                 continue label294;
                              }
                           }
                        }
                     }

                     d3 = (double)i3 + 0.5D - p_85188_1_.posY;
                     d4 = d1 * d1 + d3 * d3 + d2 * d2;
                     if (d0 < 0.0D || d4 < d0) {
                        d0 = d4;
                        l = i2;
                        i1 = i3;
                        j1 = k2;
                        k1 = j3 % 4;
                     }
                  }
               }
            }
         }
      }

      if (d0 < 0.0D) {
         for(i2 = i - b0; i2 <= i + b0; ++i2) {
            d1 = (double)i2 + 0.5D - p_85188_1_.posX;

            for(k2 = k - b0; k2 <= k + b0; ++k2) {
               d2 = (double)k2 + 0.5D - p_85188_1_.posZ;

               label232:
               for(i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3) {
                  if (this.worldServerInstance.isAirBlock(i2, i3, k2)) {
                     while(i3 > 0 && this.worldServerInstance.isAirBlock(i2, i3 - 1, k2)) {
                        --i3;
                     }

                     for(j3 = l1; j3 < l1 + 2; ++j3) {
                        k3 = j3 % 2;
                        l3 = 1 - k3;

                        for(i4 = 0; i4 < 4; ++i4) {
                           for(j4 = -1; j4 < 4; ++j4) {
                              k4 = i2 + (i4 - 1) * k3;
                              l4 = i3 + j4;
                              i5 = k2 + (i4 - 1) * l3;
                              if (j4 < 0 && !this.worldServerInstance.getBlock(k4, l4, i5).getMaterial().isSolid() || j4 >= 0 && !this.worldServerInstance.isAirBlock(k4, l4, i5)) {
                                 continue label232;
                              }
                           }
                        }

                        d3 = (double)i3 + 0.5D - p_85188_1_.posY;
                        d4 = d1 * d1 + d3 * d3 + d2 * d2;
                        if (d0 < 0.0D || d4 < d0) {
                           d0 = d4;
                           l = i2;
                           i1 = i3;
                           j1 = k2;
                           k1 = j3 % 2;
                        }
                     }
                  }
               }
            }
         }
      }

      j5 = l;
      int j2 = i1;
      k2 = j1;
      int l5 = k1 % 2;
      int l2 = 1 - l5;
      if (k1 % 4 >= 2) {
         l5 = -l5;
         l2 = -l2;
      }

      boolean flag;
      if (d0 < 0.0D) {
         if (i1 < 70) {
            i1 = 70;
         }

         if (i1 > this.worldServerInstance.getActualHeight() - 10) {
            i1 = this.worldServerInstance.getActualHeight() - 10;
         }

         j2 = i1;

         for(i3 = -1; i3 <= 1; ++i3) {
            for(j3 = 1; j3 < 3; ++j3) {
               for(k3 = -1; k3 < 3; ++k3) {
                  l3 = j5 + (j3 - 1) * l5 + i3 * l2;
                  i4 = j2 + k3;
                  j4 = k2 + (j3 - 1) * l2 - i3 * l5;
                  flag = k3 < 0;
                  this.worldServerInstance.setBlock(l3, i4, j4, flag ? NetherBlocks.dark_bricks : Blocks.air);
               }
            }
         }
      }

      for(i3 = 0; i3 < 4; ++i3) {
         for(j3 = 0; j3 < 4; ++j3) {
            for(k3 = -1; k3 < 4; ++k3) {
               l3 = j5 + (j3 - 1) * l5;
               i4 = j2 + k3;
               j4 = k2 + (j3 - 1) * l2;
               flag = j3 == 0 || j3 == 3 || k3 == -1 || k3 == 3;
               this.worldServerInstance.setBlock(l3, i4, j4, (Block)(flag ? NetherBlocks.dark_bricks : NetherBlocks.abyss_portal), 0, 2);
            }
         }

         for(j3 = 0; j3 < 4; ++j3) {
            for(k3 = -1; k3 < 4; ++k3) {
               l3 = j5 + (j3 - 1) * l5;
               i4 = j2 + k3;
               j4 = k2 + (j3 - 1) * l2;
               this.worldServerInstance.notifyBlocksOfNeighborChange(l3, i4, j4, this.worldServerInstance.getBlock(l3, i4, j4));
            }
         }
      }

      return true;
   }

   public void removeStalePortalLocations(long p_85189_1_) {
      if (p_85189_1_ % 100L == 0L) {
         Iterator iterator = this.destinationCoordinateKeys.iterator();
         long j = p_85189_1_ - 600L;

         while(true) {
            Long olong;
            TeleporterAbyss.PortalPosition portalposition;
            do {
               if (!iterator.hasNext()) {
                  return;
               }

               olong = (Long)iterator.next();
               portalposition = (TeleporterAbyss.PortalPosition)this.destinationCoordinateCache.getValueByKey(olong);
            } while(portalposition != null && portalposition.lastUpdateTime >= j);

            iterator.remove();
            this.destinationCoordinateCache.remove(olong);
         }
      }
   }

   public class PortalPosition extends ChunkCoordinates {
      public long lastUpdateTime;
      private static final String __OBFID = "CL_00000154";

      public PortalPosition(int p_i1962_2_, int p_i1962_3_, int p_i1962_4_, long p_i1962_5_) {
         super(p_i1962_2_, p_i1962_3_, p_i1962_4_);
         this.lastUpdateTime = p_i1962_5_;
      }
   }
}
