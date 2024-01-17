package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.thinkbigcore.WorldUtilities;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPiggyHouse extends WorldGenerator {
   public boolean generate(World worldIn, Random random, int x, int y, int z) {
      if (worldIn.isAirBlock(x, y + 1, z) && worldIn.getBlock(x, y - 1, z).isOpaqueCube() && worldIn.isAirBlock(x, y + 6, z) && worldIn.isAirBlock(x - 3, y + 6, z + 4) && worldIn.isAirBlock(x + 3, y, z + 4) && worldIn.isAirBlock(x + 3, y + 6, z - 4) && worldIn.isAirBlock(x - 3, y, z - 4) && worldIn.getBlock(x - 3, y - 1, z + 4).isOpaqueCube() && worldIn.getBlock(x - 3, y - 1, z - 4).isOpaqueCube() && worldIn.getBlock(x + 3, y - 1, z + 4).isOpaqueCube() && worldIn.getBlock(x - 3, y - 1, z - 4).isOpaqueCube()) {
         WorldUtilities.Fill(worldIn, x, y, z, Blocks.nether_brick, 5, 4, 5);
         WorldUtilities.Fill(worldIn, x, y, z, Blocks.air, 4, 3, 4);
         WorldUtilities.Fill(worldIn, x, y, z, NetherBlocks.nether_planks, 1, 3, 4);
         WorldUtilities.Fill(worldIn, x, y + 4, z, NetherBlocks.smooth_netherrack_slab, 8, 1, 3, 4);
         WorldUtilities.Fill(worldIn, x + 3, y, z + 4, NetherBlocks.nether_log, 5, 1, 1);
         WorldUtilities.Fill(worldIn, x - 3, y, z + 4, NetherBlocks.nether_log, 5, 1, 1);
         WorldUtilities.Fill(worldIn, x - 3, y, z - 4, NetherBlocks.nether_log, 5, 1, 1);
         WorldUtilities.Fill(worldIn, x + 3, y, z - 4, NetherBlocks.nether_log, 5, 1, 1);
         WorldUtilities.Fill(worldIn, x, y + 2, z + 4, NetherBlocks.soul_glass_pane, 1, 2, 1);
         WorldUtilities.Fill(worldIn, x, y + 2, z - 4, NetherBlocks.soul_glass_pane, 1, 2, 1);
         WorldUtilities.Fill(worldIn, x - 3, y + 2, z, NetherBlocks.soul_glass_pane, 1, 1, 3);
         WorldUtilities.Fill(worldIn, x + 3, y + 2, z - 1, NetherBlocks.soul_glass_pane, 1, 1, 2);
         worldIn.setBlock(x + 3, y, z + 2, NetherBlocks.nether_planks);
         worldIn.setBlock(x + 3, y + 1, z + 2, Blocks.air);
         worldIn.setBlock(x + 3, y + 2, z + 2, Blocks.air);
         worldIn.setBlock(x + 4, y + 2, z + 3, NetherBlocks.glowstone_torch, 1, 2);
         WorldUtilities.Fill(worldIn, x, y + 5, z, Blocks.nether_brick, 1, 3, 5);
         WorldUtilities.Fill(worldIn, x, y + 6, z, Blocks.nether_brick, 1, 2, 5);
         WorldUtilities.Fill(worldIn, x + 4, y + 4, z, NetherBlocks.nether_stairs, 1, 1, 1, 6);
         WorldUtilities.Fill(worldIn, x - 4, y + 4, z, NetherBlocks.nether_stairs, 1, 1, 6);
         WorldUtilities.Fill(worldIn, x + 3, y + 5, z, NetherBlocks.nether_stairs, 1, 1, 1, 6);
         WorldUtilities.Fill(worldIn, x - 3, y + 5, z, NetherBlocks.nether_stairs, 1, 1, 6);
         WorldUtilities.Fill(worldIn, x + 2, y + 6, z, NetherBlocks.nether_stairs, 1, 1, 1, 6);
         WorldUtilities.Fill(worldIn, x - 2, y + 6, z, NetherBlocks.nether_stairs, 1, 1, 6);
         WorldUtilities.Fill(worldIn, x, y + 7, z, NetherBlocks.nether_slab, 1, 2, 6);
         worldIn.setBlock(x + 3, y + 4, z - 5, NetherBlocks.nether_stairs, 4, 2);
         worldIn.setBlock(x + 3, y + 4, z + 5, NetherBlocks.nether_stairs, 4, 2);
         worldIn.setBlock(x + 2, y + 5, z - 5, NetherBlocks.nether_stairs, 4, 2);
         worldIn.setBlock(x + 2, y + 5, z + 5, NetherBlocks.nether_stairs, 4, 2);
         worldIn.setBlock(x + 1, y + 6, z - 5, NetherBlocks.nether_stairs, 4, 2);
         worldIn.setBlock(x + 1, y + 6, z + 5, NetherBlocks.nether_stairs, 4, 2);
         worldIn.setBlock(x, y + 6, z + 5, NetherBlocks.nether_slab, 8, 2);
         worldIn.setBlock(x, y + 6, z - 5, NetherBlocks.nether_slab, 8, 2);
         worldIn.setBlock(x - 3, y + 4, z - 5, NetherBlocks.nether_stairs, 5, 2);
         worldIn.setBlock(x - 3, y + 4, z + 5, NetherBlocks.nether_stairs, 5, 2);
         worldIn.setBlock(x - 2, y + 5, z - 5, NetherBlocks.nether_stairs, 5, 2);
         worldIn.setBlock(x - 2, y + 5, z + 5, NetherBlocks.nether_stairs, 5, 2);
         worldIn.setBlock(x - 1, y + 6, z - 5, NetherBlocks.nether_stairs, 5, 2);
         worldIn.setBlock(x - 1, y + 6, z + 5, NetherBlocks.nether_stairs, 5, 2);
         if (worldIn.getBlock(x + 4, y, z + 2) == Blocks.air) {
            worldIn.setBlock(x + 4, y, z + 2, NetherBlocks.nether_stairs, 1, 2);
         }

         if (worldIn.getBlock(x + 4, y, z + 1) == Blocks.air) {
            worldIn.setBlock(x + 4, y, z + 1, NetherBlocks.nether_stairs, 2, 2);
         }

         if (worldIn.getBlock(x + 4, y, z + 3) == Blocks.air) {
            worldIn.setBlock(x + 4, y, z + 3, NetherBlocks.nether_stairs, 3, 2);
         }

         WorldUtilities.placeDoor(worldIn, x + 3, y + 1, z + 2, 5, NetherBlocks.nether_door);
         worldIn.setBlock(x - 1, y + 1, z, NetherBlocks.netherrack_chiseled);
         worldIn.setBlock(x - 2, y + 1, z, NetherBlocks.netherrack_chiseled);
         worldIn.setBlock(x - 1, y + 1, z - 1, NetherBlocks.netherrack_chiseled);
         worldIn.setBlock(x - 2, y + 1, z - 1, NetherBlocks.netherrack_chiseled);
         worldIn.setBlock(x - 1, y + 1, z - 2, NetherBlocks.netherrack_chiseled);
         worldIn.setBlock(x - 2, y + 1, z - 2, NetherBlocks.netherrack_chiseled);
         worldIn.setBlock(x - 1, y + 1, z - 3, NetherBlocks.netherrack_chiseled);
         worldIn.setBlock(x - 2, y + 1, z - 3, NetherBlocks.netherrack_chiseled);
         worldIn.setBlock(x - 1, y + 2, z - 3, Blocks.stone_slab, 7, 2);
         worldIn.setBlock(x - 2, y + 2, z - 3, Blocks.stone_slab, 7, 2);
         worldIn.setBlock(x, y + 1, z + 3, NetherBlocks.nether_fence);
         worldIn.setBlock(x, y + 2, z + 3, Blocks.carpet, 7, 2);
         worldIn.setBlock(x + 1, y + 1, z + 3, NetherBlocks.smooth_netherrack_stairs, 0, 2);
         worldIn.setBlock(x - 1, y + 1, z + 3, NetherBlocks.smooth_netherrack_stairs, 1, 2);
         worldIn.setBlock(x + 4, y + 2, z + 1, NetherBlocks.nether_trapdoor_hidden, 7, 2);
         worldIn.setBlock(x + 4, y + 2, z - 3, NetherBlocks.nether_trapdoor_hidden, 7, 2);
         worldIn.setBlock(x + 2, y + 2, z + 5, NetherBlocks.nether_trapdoor_hidden, 5, 2);
         worldIn.setBlock(x - 2, y + 2, z + 5, NetherBlocks.nether_trapdoor_hidden, 5, 2);
         worldIn.setBlock(x + 2, y + 2, z - 5, NetherBlocks.nether_trapdoor_hidden, 4, 2);
         worldIn.setBlock(x - 2, y + 2, z - 5, NetherBlocks.nether_trapdoor_hidden, 4, 2);
         worldIn.setBlock(x + 2, y + 2, z + 5, NetherBlocks.nether_trapdoor_hidden, 5, 2);
         worldIn.setBlock(x - 4, y + 2, z + 3, NetherBlocks.nether_trapdoor_hidden, 6, 2);
         worldIn.setBlock(x - 4, y + 2, z - 3, NetherBlocks.nether_trapdoor_hidden, 6, 2);
         worldIn.setBlock(x + 2, y, z - 3, NetherBlocks.nether_trapdoor_hidden, 8, 2);
         worldIn.setBlock(x + 2, y - 1, z - 3, Blocks.chest, 0, 2);
         TileEntityChest tileentitychest = (TileEntityChest)worldIn.getTileEntity(x + 2, y - 1, z - 3);
         if (tileentitychest instanceof TileEntityChest) {
            WeightedRandomChestContent.generateChestContents(random, GenNetherLoot.getItems("PiggyChest", random), tileentitychest, GenNetherLoot.getCount("PiggyChest", random));
         }

         worldIn.setBlock(x - 1, y, z, Blocks.mob_spawner, 0, 2);
         TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)worldIn.getTileEntity(x - 1, y, z);
         if (tileentitymobspawner instanceof TileEntityMobSpawner) {
            tileentitymobspawner.func_145881_a().setEntityName("PigZombie");
         }

         return true;
      } else {
         return false;
      }
   }
}
