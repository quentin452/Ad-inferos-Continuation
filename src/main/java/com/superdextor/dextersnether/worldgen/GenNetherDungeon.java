package com.superdextor.dextersnether.worldgen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GenNetherDungeon extends WorldGenerator {
   public boolean generate(World world, Random random, int var, int var2, int var3) {
      byte y_up = 4;
      int x = random.nextInt(2) + 2;
      int z = random.nextInt(2) + 2;
      int spawn = 0;

      int var4;
      int var5;
      int var6;
       for (var4 = var - x - 1; var4 <= var + x + 1; ++var4) {
           for (var5 = var2 - 1; var5 <= var2 + y_up + 1; ++var5) {
               for (var6 = var3 - z - 1; var6 <= var3 + z + 1; ++var6) {
                   if ((var4 == var - x - 1 || var4 == var + x + 1 || var6 == var3 - z - 1 || var6 == var3 + z + 1) && var5 == var2) {
                       if (world.blockExists(var4, var5, var6) && world.isAirBlock(var4, var5, var6)) {
                           ++spawn;
                       }
                   }
               }
           }
       }

      if (spawn >= 1 && spawn <= 5) {
         for(var4 = var - x - 1; var4 <= var + x + 1; ++var4) {
            for(var5 = var2 + y_up; var5 >= var2 - 1; --var5) {
               for(var6 = var3 - z - 1; var6 <= var3 + z + 1; ++var6) {
                  if (var4 != var - x - 1 && var5 != var2 - 1 && var6 != var3 - z - 1 && var4 != var + x + 1 && var5 != var2 + y_up + 1 && var6 != var3 + z + 1) {
                     world.setBlockToAir(var4, var5, var6);
                  } else if (var5 >= 0 && !world.getBlock(var4, var5 - 1, var6).getMaterial().isSolid()) {
                     world.setBlockToAir(var4, var5, var6);
                  } else if (world.getBlock(var4, var5, var6).getMaterial().isSolid()) {
                     if (var5 == var2 - 1 && random.nextInt(4) != 0) {
                        world.setBlock(var4, var5, var6, Blocks.nether_brick, 0, 2);
                     } else {
                        world.setBlock(var4, var5, var6, Blocks.nether_brick, 0, 2);
                     }
                  }
               }
            }
         }

         for(var4 = 0; var4 < 2; ++var4) {
            for(var5 = 0; var5 < 3; ++var5) {
               var6 = var + random.nextInt(x * 2 + 1) - x;
               int j2 = var3 + random.nextInt(z * 2 + 1) - z;
               if (world.isAirBlock(var6, var2, j2)) {
                  int k2 = 0;
                  if (world.getBlock(var6 - 1, var2, j2).getMaterial().isSolid()) {
                     ++k2;
                  }

                  if (world.getBlock(var6 + 1, var2, j2).getMaterial().isSolid()) {
                     ++k2;
                  }

                  if (world.getBlock(var6, var2, j2 - 1).getMaterial().isSolid()) {
                     ++k2;
                  }

                  if (world.getBlock(var6, var2, j2 + 1).getMaterial().isSolid()) {
                     ++k2;
                  }

                  if (k2 == 1) {
                     world.setBlock(var6, var2, j2, Blocks.chest, 0, 2);
                     TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(var6, var2, j2);
                     if (tileentitychest != null) {
                        WeightedRandomChestContent.generateChestContents(random, GenNetherLoot.getItems("NetherChest", random), tileentitychest, GenNetherLoot.getCount("NetherChest", random));
                     }
                     break;
                  }
               }
            }
         }

         world.setBlock(var, var2, var3, Blocks.mob_spawner, 0, 2);
         TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(var, var2, var3);
         if (tileentitymobspawner != null) {
            tileentitymobspawner.func_145881_a().setEntityName(this.pickMobSpawner(random));
         } else {
            System.err.println("Failed to fetch mob spawner entity at (" + var + ", " + var2 + ", " + var3 + ")");
         }

         return true;
      } else {
         return false;
      }
   }

   private String pickMobSpawner(Random random) {
      return NetherDungeonHooks.getRandomDungeonMob(random);
   }
}
