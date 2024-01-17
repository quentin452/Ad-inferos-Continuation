package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.init.NetherBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDimstone1 extends WorldGenerator {
   public boolean generate(World world, Random random, int x, int y, int z) {
      if (!world.isAirBlock(x, y, z)) {
         return false;
      } else if (world.getBlock(x, y + 1, z) != NetherBlocks.darkstone) {
         return false;
      } else {
         world.setBlock(x, y, z, NetherBlocks.dimstone, 0, 2);

         for(int l = 0; l < 1500; ++l) {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y - random.nextInt(12);
            int k1 = z + random.nextInt(8) - random.nextInt(8);
            if (world.getBlock(i1, j1, k1).getMaterial() == Material.air) {
               int l1 = 0;

               for(int i2 = 0; i2 < 6; ++i2) {
                  Block block = null;
                  if (i2 == 0) {
                     block = world.getBlock(i1 - 1, j1, k1);
                  }

                  if (i2 == 1) {
                     block = world.getBlock(i1 + 1, j1, k1);
                  }

                  if (i2 == 2) {
                     block = world.getBlock(i1, j1 - 1, k1);
                  }

                  if (i2 == 3) {
                     block = world.getBlock(i1, j1 + 1, k1);
                  }

                  if (i2 == 4) {
                     block = world.getBlock(i1, j1, k1 - 1);
                  }

                  if (i2 == 5) {
                     block = world.getBlock(i1, j1, k1 + 1);
                  }

                  if (block == NetherBlocks.dimstone) {
                     ++l1;
                  }
               }

               if (l1 == 1) {
                  world.setBlock(i1, j1, k1, NetherBlocks.dimstone, 0, 2);
               }
            }
         }

         return true;
      }
   }
}
