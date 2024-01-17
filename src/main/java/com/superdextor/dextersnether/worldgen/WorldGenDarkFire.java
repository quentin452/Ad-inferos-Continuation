package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.init.NetherBlocks;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDarkFire extends WorldGenerator {
   public boolean generate(World world, Random random, int x, int y, int z) {
      for(int l = 0; l < 64; ++l) {
         int i1 = x + random.nextInt(8) - random.nextInt(8);
         int j1 = y + random.nextInt(4) - random.nextInt(4);
         int k1 = z + random.nextInt(8) - random.nextInt(8);
         if (world.isAirBlock(i1, j1, k1) && world.getBlock(i1, j1 - 1, k1) == Blocks.netherrack) {
            world.setBlock(i1, j1, k1, NetherBlocks.dark_fire, 0, 2);
         }
      }

      return true;
   }
}
