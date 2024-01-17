package com.superdextor.dextersnether.world;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.entity.monster.EntityCurse;
import com.superdextor.dextersnether.entity.monster.EntityGlowstoneSkeleton;
import com.superdextor.dextersnether.entity.monster.EntityHerobrine;
import com.superdextor.dextersnether.entity.monster.EntityHerobrineClone;
import com.superdextor.dextersnether.entity.monster.EntityNetherSpider;
import com.superdextor.dextersnether.entity.monster.EntityPhantom;
import com.superdextor.dextersnether.entity.monster.EntityReaper;
import com.superdextor.dextersnether.entity.monster.EntitySkeletonHorse;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenAbyss extends BiomeGenBase {
   public BiomeGenAbyss(int p_i1981_1_) {
      super(p_i1981_1_);
      this.spawnableMonsterList.clear();
      this.spawnableCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableCaveCreatureList.clear();
      this.addSpawn(new SpawnListEntry(EntityReaper.class, NetherConfig.reaperSpawns, 1, 2));
      this.addSpawn(new SpawnListEntry(EntitySkeletonHorse.class, NetherConfig.skeletonHorseSpawns, 4, 7));
      this.addSpawn(new SpawnListEntry(EntityCurse.class, NetherConfig.curseSpawns, 4, 4));
      this.addSpawn(new SpawnListEntry(EntityHerobrine.class, NetherConfig.herobrineSpawns * 2 + 2, 1, 1));
      this.addSpawn(new SpawnListEntry(EntityHerobrineClone.class, NetherConfig.herobrineSpawns * 3 + 3, 1, 1));
      this.addSpawn(new SpawnListEntry(EntityNetherSpider.class, NetherConfig.netherSpiderSpawns, 4, 4));
      this.addSpawn(new SpawnListEntry(EntityPhantom.class, NetherConfig.phantomSpawns, 6, 10));
      this.addSpawn(new SpawnListEntry(EntityGlowstoneSkeleton.class, NetherConfig.glowstoneSkeletonSpawns, 4, 4));
      this.setColor(0);
   }

   private void addSpawn(SpawnListEntry spawn) {
      this.spawnableMonsterList.add(spawn);
      this.spawnableCreatureList.add(spawn);
   }

   public float getSpawningChance() {
      return 1.0F;
   }

   @SideOnly(Side.CLIENT)
   public int getBiomeFoliageColor(int R, int B, int G) {
      return 4210752;
   }

   @SideOnly(Side.CLIENT)
   public int getBiomeGrassColor(int R, int B, int G) {
      return 4210752;
   }

   public int getWaterColorMultiplier() {
      return 8519680;
   }

   public int getModdedBiomeGrassColor(int original) {
      return 4210752;
   }

   public int getModdedBiomeFoliageColor(int original) {
      return 4210752;
   }
}
