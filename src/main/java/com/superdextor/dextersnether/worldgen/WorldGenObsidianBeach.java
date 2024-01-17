package com.superdextor.dextersnether.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenObsidianBeach extends WorldGenerator {
   public boolean generate(World world, Random random, int x, int y, int z) {
      if (world.getBlock(x, y, z).getMaterial() != Material.lava) {
         return false;
      } else {
         int l = random.nextInt(28) + 2;
         byte b0 = 2;

         for(int i1 = x - l; i1 <= x + l; ++i1) {
            for(int j1 = z - l; j1 <= z + l; ++j1) {
               int k1 = i1 - x;
               int l1 = j1 - z;
               if (k1 * k1 + l1 * l1 <= l * l) {
                  for(int i2 = y - b0; i2 <= y + b0; ++i2) {
                     Block block = world.getBlock(i1, i2, j1);
                     if (block == Blocks.netherrack || block == Blocks.soul_sand || block == Blocks.quartz_ore) {
                        world.setBlock(i1, i2, j1, Blocks.obsidian, 0, 2);
                     }
                  }
               }
            }
         }

         return true;
      }
   }
}
