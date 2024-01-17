package com.superdextor.dextersnether.world;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.entity.monster.EntityCurse;
import com.superdextor.dextersnether.entity.monster.EntityGlowstoneSkeleton;
import com.superdextor.dextersnether.entity.monster.EntityNetherSpider;
import com.superdextor.dextersnether.entity.monster.EntityReaper;
import com.superdextor.dextersnether.worldgen.WorldGenBigNetherMushroom;
import com.superdextor.dextersnether.worldgen.WorldGenNetherTree;
import com.superdextor.dextersnether.worldgen.WorldGenPiggyHouse;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenFire;

public class BiomeGenNether extends BiomeGenBase {
   public BiomeGenNether(int ID) {
      super(ID);
      this.topBlock = Blocks.netherrack;
      this.fillerBlock = Blocks.netherrack;
      this.spawnableMonsterList.clear();
      this.spawnableCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableCaveCreatureList.clear();
      this.theBiomeDecorator.generateLakes = false;
      this.theBiomeDecorator.treesPerChunk = -999;
      this.theBiomeDecorator.flowersPerChunk = -999;
      this.theBiomeDecorator.grassPerChunk = -999;
      this.theBiomeDecorator.sandPerChunk = -999;
      this.theBiomeDecorator.sandPerChunk2 = -999;
      this.theBiomeDecorator.clayPerChunk = -999;
      this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 10, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 50, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 10, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityNetherSpider.class, NetherConfig.netherSpiderSpawns, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityReaper.class, NetherConfig.reaperSpawns, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityGlowstoneSkeleton.class, NetherConfig.glowstoneSkeletonSpawns, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityCurse.class, NetherConfig.curseSpawns, 4, 4));
   }

   public void decorate(World world, Random rand, int x, int z) {
      for(int k = 0; k < 4; ++k) {
         for(int l = 0; l < 4; ++l) {
            int i1;
            int j1;
            int k1;
            if (NetherConfig.genTrees && rand.nextFloat() < 0.002F) {
               WorldGenNetherTree tree = new WorldGenNetherTree(true, false);
               i1 = x + k * 4 + 1 + 8 + rand.nextInt(3);
               j1 = z + l * 4 + 1 + 8 + rand.nextInt(3);
               k1 = world.getHeightValue(i1, j1);
               tree.generate(world, rand, i1, k1, j1);
            }

            if (NetherConfig.genMushrooms && rand.nextFloat() < 0.001F) {
               WorldGenBigNetherMushroom tree = new WorldGenBigNetherMushroom();
               i1 = x + k * 4 + 1 + 8 + rand.nextInt(3);
               j1 = z + l * 4 + 1 + 8 + rand.nextInt(3);
               k1 = world.getHeightValue(i1, j1);
               tree.generate(world, rand, i1, k1, j1);
            }

            if (rand.nextFloat() < 0.02F) {
               WorldGenFire tree = new WorldGenFire();
               i1 = x + k * 4 + 1 + 8 + rand.nextInt(3);
               j1 = z + l * 4 + 1 + 8 + rand.nextInt(3);
               k1 = world.getHeightValue(i1, j1);
               tree.generate(world, rand, i1, k1, j1);
            }

            if (NetherConfig.genPiggyHouse && rand.nextFloat() < 6.0E-4F) {
               WorldGenPiggyHouse tree = new WorldGenPiggyHouse();
               i1 = x + k * 4 + 1 + 8 + rand.nextInt(3);
               j1 = z + l * 4 + 1 + 8 + rand.nextInt(3);
               k1 = world.getHeightValue(i1, j1);
               tree.generate(world, rand, i1, k1, j1);
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public int getBiomeFoliageColor(int R, int B, int G) {
      return 8334095;
   }

   @SideOnly(Side.CLIENT)
   public int getBiomeGrassColor(int R, int B, int G) {
      return 8334095;
   }

   public int getWaterColorMultiplier() {
      return 16711807;
   }

   public int getModdedBiomeGrassColor(int original) {
      return 8334095;
   }

   public int getModdedBiomeFoliageColor(int original) {
      return 8334095;
   }
}
