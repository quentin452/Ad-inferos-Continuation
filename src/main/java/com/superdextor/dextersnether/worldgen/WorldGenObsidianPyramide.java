package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.entity.monster.EntityInfernumAvis;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.thinkbigcore.WorldUtilities;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenObsidianPyramide extends WorldGenerator {
   public boolean generate(World worldIn, Random random, int x, int y, int z) {
      if (worldIn.isAirBlock(x, y + 8, z) && worldIn.getBlock(x, y - 5, z).isOpaqueCube() && worldIn.getBlock(x, y + 4, z).isOpaqueCube() && worldIn.getBlock(x - 12, y - 4, z - 12).isOpaqueCube() && worldIn.getBlock(x + 12, y - 4, z - 12).isOpaqueCube() && worldIn.getBlock(x + 12, y - 4, z + 12).isOpaqueCube() && worldIn.getBlock(x - 12, y - 4, z + 12).isOpaqueCube()) {
         WorldUtilities.Fill(worldIn, x, y - 3, z, Blocks.obsidian, 1, 12, 12);
         WorldUtilities.Fill(worldIn, x, y - 2, z, Blocks.obsidian, 1, 12, 12);
         WorldUtilities.Fill(worldIn, x, y - 1, z, Blocks.obsidian, 1, 11, 11);
         WorldUtilities.Fill(worldIn, x, y, z, Blocks.obsidian, 1, 10, 10);
         WorldUtilities.Fill(worldIn, x, y + 1, z, Blocks.obsidian, 1, 9, 9);
         WorldUtilities.Fill(worldIn, x, y + 2, z, Blocks.obsidian, 1, 8, 8);
         WorldUtilities.Fill(worldIn, x, y + 3, z, Blocks.obsidian, 1, 7, 7);
         WorldUtilities.Fill(worldIn, x, y + 4, z, Blocks.obsidian, 1, 6, 6);
         WorldUtilities.Fill(worldIn, x, y + 5, z, Blocks.obsidian, 1, 5, 5);
         WorldUtilities.Fill(worldIn, x, y + 6, z, Blocks.obsidian, 1, 4, 4);
         WorldUtilities.Fill(worldIn, x, y + 7, z, Blocks.obsidian, 1, 3, 3);
         WorldUtilities.Fill(worldIn, x, y + 8, z, Blocks.obsidian, 1, 2, 2);
         WorldUtilities.Fill(worldIn, x, y - 2, z, Blocks.air, 1, 11, 11);
         WorldUtilities.Fill(worldIn, x, y - 1, z, Blocks.air, 1, 10, 10);
         WorldUtilities.Fill(worldIn, x, y, z, Blocks.air, 1, 9, 9);
         WorldUtilities.Fill(worldIn, x, y + 1, z, Blocks.air, 1, 8, 8);
         WorldUtilities.Fill(worldIn, x, y + 2, z, Blocks.air, 1, 2, 2);
         WorldUtilities.Fill(worldIn, x, y + 3, z, Blocks.air, 1, 6, 6);
         WorldUtilities.Fill(worldIn, x, y + 4, z, Blocks.air, 1, 5, 5);
         WorldUtilities.Fill(worldIn, x, y + 5, z, Blocks.air, 1, 4, 4);
         WorldUtilities.Fill(worldIn, x, y + 6, z, Blocks.air, 1, 3, 3);
         WorldUtilities.Fill(worldIn, x, y + 7, z, Blocks.air, 1, 2, 2);
         worldIn.setBlock(x, y + 8, z, Blocks.air);
         WorldUtilities.Fill(worldIn, x + 2, y - 2, z + 2, NetherBlocks.obsidian_smooth, 4, 1, 1);
         WorldUtilities.Fill(worldIn, x + 2, y - 2, z - 2, NetherBlocks.obsidian_smooth, 4, 1, 1);
         WorldUtilities.Fill(worldIn, x - 2, y - 2, y - 2, NetherBlocks.obsidian_smooth, 4, 1, 1);
         WorldUtilities.Fill(worldIn, x - 2, y - 2, z + 2, NetherBlocks.obsidian_smooth, 4, 1, 1);
         worldIn.setBlock(x, y - 3, z, NetherBlocks.obsidian_chiseled);
         worldIn.setBlock(x + 1, y - 3, z + 1, NetherBlocks.obsidian_smooth);
         worldIn.setBlock(x - 1, y - 3, z + 1, NetherBlocks.obsidian_smooth);
         worldIn.setBlock(x + 1, y - 3, z - 1, NetherBlocks.obsidian_smooth);
         worldIn.setBlock(x - 1, y - 3, z - 1, NetherBlocks.obsidian_smooth);
         WorldUtilities.Fill(worldIn, x + 3, y - 3, z, NetherBlocks.obsidian_smooth, 1, 2, 1);
         WorldUtilities.Fill(worldIn, x - 3, y - 3, z, NetherBlocks.obsidian_smooth, 1, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 3, z + 3, NetherBlocks.obsidian_smooth, 1, 1, 2);
         WorldUtilities.Fill(worldIn, x, y - 3, z - 3, NetherBlocks.obsidian_smooth, 1, 1, 2);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z - 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z - 6, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z - 4, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z - 2, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 6, y - 2, z - 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 4, y - 2, z - 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 2, y - 2, z - 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z + 6, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z + 4, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z + 2, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 6, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 4, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 2, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z - 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z - 6, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z - 4, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 8, y - 2, z - 2, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 6, y - 2, z - 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 4, y - 2, z - 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x + 2, y - 2, z - 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z + 6, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z + 4, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 8, y - 2, z + 2, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 6, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 4, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         WorldUtilities.Fill(worldIn, x - 2, y - 2, z + 8, NetherBlocks.obsidian_smooth, 3, 1, 1);
         worldIn.setBlock(x + 2, y - 1, z - 8, NetherBlocks.obsidian_chiseled);
         worldIn.setBlock(x - 2, y - 1, z - 8, NetherBlocks.obsidian_chiseled);
         worldIn.setBlock(x + 5, y + 4, z, Blocks.air);
         worldIn.setBlock(x + 6, y + 3, z, Blocks.air);
         WorldUtilities.Fill(worldIn, x + 5, y + 5, z, Blocks.obsidian, 1, 1, 2);
         WorldUtilities.Fill(worldIn, x + 6, y + 5, z, Blocks.obsidian, 1, 1, 2);
         worldIn.setBlock(x + 6, y + 4, z + 1, Blocks.obsidian);
         worldIn.setBlock(x + 6, y + 4, z - 1, Blocks.obsidian);
         WorldUtilities.Fill(worldIn, x + 7, y + 3, z, Blocks.obsidian, 3, 1, 2);
         worldIn.setBlock(x + 7, y + 4, z, Blocks.air);
         worldIn.setBlock(x + 7, y + 3, z, Blocks.air);
         WorldUtilities.Fill(worldIn, x, y - 3, z - 12, Blocks.obsidian, 5, 3, 4);
         WorldUtilities.Fill(worldIn, x, y - 1, z - 10, NetherBlocks.obsidian_smooth, 1, 3, 2);
         WorldUtilities.Fill(worldIn, x, y - 1, z - 12, NetherBlocks.obsidian_smooth, 1, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 2, z - 13, NetherBlocks.obsidian_smooth, 1, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 2, z - 11, Blocks.air, 3, 2, 3);
         WorldUtilities.Fill(worldIn, x, y - 4, z - 12, Blocks.obsidian, 1, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 3, z - 12, Blocks.air, 1, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 5, z - 13, Blocks.obsidian, 2, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 3, z - 14, NetherBlocks.obsidian_smooth, 1, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 4, z - 13, Blocks.air, 2, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 6, z - 14, Blocks.obsidian, 3, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 7, z - 15, Blocks.obsidian, 4, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 8, z - 16, Blocks.obsidian, 7, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 9, z - 17, Blocks.obsidian, 7, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 4, z - 15, NetherBlocks.obsidian_smooth, 1, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 5, z - 16, NetherBlocks.obsidian_smooth, 1, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 6, z - 17, NetherBlocks.obsidian_smooth, 1, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 5, z - 14, Blocks.air, 5, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 6, z - 15, Blocks.air, 5, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 7, z - 16, Blocks.air, 5, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 8, z - 17, Blocks.air, 5, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 9, z - 18, Blocks.obsidian, 6, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 8, z - 18, Blocks.air, 4, 2, 1);
         WorldUtilities.Fill(worldIn, x, y - 13, z - 30, Blocks.obsidian, 11, 8, 8);
         WorldUtilities.Fill(worldIn, x, y - 7, z - 30, NetherBlocks.obsidian_smooth, 1, 8, 8);
         WorldUtilities.Fill(worldIn, x, y - 12, z - 30, Blocks.air, 9, 7, 7);
         WorldUtilities.Fill(worldIn, x, y - 12, z - 30, NetherBlocks.nether_spikes, 1, 7, 7);
         WorldUtilities.Fill(worldIn, x, y - 12, z - 30, Blocks.obsidian, 4, 5, 5);
         WorldUtilities.Fill(worldIn, x, y - 9, z - 21, Blocks.obsidian, 5, 3, 3);
         WorldUtilities.replaceBlocks(worldIn, x, y - 7, z - 18, NetherBlocks.obsidian_smooth, Blocks.obsidian, 1, 3, 1);
         WorldUtilities.Fill(worldIn, x, y - 7, z - 21, NetherBlocks.obsidian_smooth, 1, 3, 3);
         WorldUtilities.Fill(worldIn, x, y - 8, z - 21, Blocks.air, 3, 2, 3);
         int i1 = -9;
         int i2 = -9;
         if (!random.nextBoolean()) {
            i1 = 9;
         }

         if (!random.nextBoolean()) {
            i2 = 9;
         }

         worldIn.setBlock(x + i1, y - 4, z + i2, Blocks.chest);
         worldIn.setBlock(x + i1, y - 3, z + i2, NetherBlocks.nether_trapdoor_hidden, 8, 2);
         TileEntity tileentitychest = worldIn.getTileEntity(x + i1, y - 4, z + i2);
         if (tileentitychest instanceof TileEntityChest) {
            WeightedRandomChestContent.generateChestContents(random, GenNetherLoot.getItems("NetherChest", random), (TileEntityChest)tileentitychest, GenNetherLoot.getCount("NetherChest", random));
         }

         worldIn.setBlock(x + 2, y - 3, z + 2, Blocks.mob_spawner);
         TileEntity tileentityspawner = worldIn.getTileEntity(x + 2, y - 3, z + 2);
         if (tileentityspawner instanceof TileEntityMobSpawner) {
            ((TileEntityMobSpawner)tileentityspawner).func_145881_a().setEntityName("dextersnether_Nether_Spider");
         }

         if (!worldIn.isRemote) {
            EntityInfernumAvis entityboss = new EntityInfernumAvis(worldIn);
            entityboss.setLocationAndAngles((double)((float)x + 0.5F), (double)(y - 8), (double)((float)z + 0.5F - 30.0F), 0.0F, 0.0F);
            worldIn.spawnEntityInWorld(entityboss);
         }

         return true;
      } else {
         return false;
      }
   }
}
