package com.superdextor.dextersnether;

import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.world.BiomeGenAbyss;
import com.superdextor.dextersnether.world.BiomeGenNether;
import com.superdextor.dextersnether.worldgen.GenNetherDungeon;
import com.superdextor.dextersnether.worldgen.WorldGenAcidLakes;
import com.superdextor.dextersnether.worldgen.WorldGenBigNetherMushroom;
import com.superdextor.dextersnether.worldgen.WorldGenNetherTree;
import com.superdextor.dextersnether.worldgen.WorldGenObsidianBeach;
import com.superdextor.dextersnether.worldgen.WorldGenObsidianPyramide;
import com.superdextor.dextersnether.worldgen.WorldGenPiggyHouse;
import com.superdextor.dextersnether.worldgen.WorldGenSpikes;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class NetherGeneration implements IWorldGenerator {
   public static final BiomeGenBase abyss;
   public static final BiomeGenBase nether;

   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      switch(world.provider.dimensionId) {
      case -1:
         this.generateNether(world, random, chunkX * 16, chunkZ * 16);
         break;
      case 0:
         this.generateOverworld(world, random, chunkX * 16, chunkZ * 16);
         break;
      case 1:
         this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
      }

   }

   public void generateNether(World world, Random rand, int x, int z) {
      if (NetherConfig.genOres) {
         this.generateOre(NetherBlocks.netherite_ore, world, rand, x, z, 4, 8, 1, 0, 120, Blocks.netherrack);
         this.generateOre(NetherBlocks.wither_ore, world, rand, x, z, 4, 8, 3, 0, 120, Blocks.netherrack);
         this.generateOre(NetherBlocks.gold_ore_nether, world, rand, x, z, 5, 10, 5, 0, 120, Blocks.netherrack);
      }

      if (NetherConfig.genTrees) {
         (new WorldGenNetherTree(false, true)).generate(world, rand, x + rand.nextInt(16), 8 + rand.nextInt(72), z + rand.nextInt(16));
      }

      if (NetherConfig.genMushrooms) {
         (new WorldGenBigNetherMushroom()).generate(world, rand, x + rand.nextInt(16), 8 + rand.nextInt(72), z + rand.nextInt(16));
      }

      if (NetherConfig.genDungeons) {
         (new GenNetherDungeon()).generate(world, rand, x + rand.nextInt(16), 8 + rand.nextInt(72), z + rand.nextInt(16));
      }

      if (NetherConfig.genAcid && rand.nextFloat() < 0.6F) {
         (new WorldGenAcidLakes()).generate(world, rand, x + rand.nextInt(16), 8 + rand.nextInt(72), z + rand.nextInt(16));
      }

      if (NetherConfig.genPiggyHouse) {
         (new WorldGenPiggyHouse()).generate(world, rand, x + rand.nextInt(16), 8 + rand.nextInt(72), z + rand.nextInt(16));
      }

      if (NetherConfig.genObsidianPyramide) {
         (new WorldGenObsidianPyramide()).generate(world, rand, x + rand.nextInt(16), 8 + rand.nextInt(72), z + rand.nextInt(16));
      }

      if (NetherConfig.genObsidianBeach) {
         (new WorldGenObsidianBeach()).generate(world, rand, x + rand.nextInt(16), 8 + rand.nextInt(72), z + rand.nextInt(16));
      }

      if (NetherConfig.genSpikes) {
         (new WorldGenSpikes()).generate(world, rand, x + rand.nextInt(16), 8 + rand.nextInt(72), z + rand.nextInt(16));
      }

   }

   public void generateOverworld(World world, Random rand, int x, int z) {
   }

   public void generateEnd(World world, Random rand, int x, int z) {
   }

   public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, Block generateIn) {
      int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
      int hightRange = maxY - minY;

      for(int i = 0; i < chance; ++i) {
         int xRand = chunkX + random.nextInt(16);
         int yRand = random.nextInt(hightRange) + minY;
         int zRand = chunkZ + random.nextInt(16);
         (new WorldGenMinable(block, vienSize, generateIn)).generate(world, random, xRand, yRand, zRand);
      }

   }

   static {
      abyss = (new BiomeGenAbyss(NetherConfig.abyssBiomeID)).setBiomeName("Abyss");
      nether = (new BiomeGenNether(NetherConfig.netherBiomeID)).setColor(16711680).setBiomeName("Nether").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
   }
}
