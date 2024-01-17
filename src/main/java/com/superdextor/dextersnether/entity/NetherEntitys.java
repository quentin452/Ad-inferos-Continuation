package com.superdextor.dextersnether.entity;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.entity.monster.EntityCurse;
import com.superdextor.dextersnether.entity.monster.EntityGlowstoneSkeleton;
import com.superdextor.dextersnether.entity.monster.EntityHerobrine;
import com.superdextor.dextersnether.entity.monster.EntityHerobrineClone;
import com.superdextor.dextersnether.entity.monster.EntityInfernalBlaze;
import com.superdextor.dextersnether.entity.monster.EntityInfernumAvis;
import com.superdextor.dextersnether.entity.monster.EntityNetherSpider;
import com.superdextor.dextersnether.entity.monster.EntityObsidainSheepmanGolem;
import com.superdextor.dextersnether.entity.monster.EntityObsidianGolem;
import com.superdextor.dextersnether.entity.monster.EntityObsidianSheepman;
import com.superdextor.dextersnether.entity.monster.EntityPhantom;
import com.superdextor.dextersnether.entity.monster.EntityQuartzman;
import com.superdextor.dextersnether.entity.monster.EntityReaper;
import com.superdextor.dextersnether.entity.monster.EntitySkeletonHorse;
import com.superdextor.dextersnether.entity.monster.EntitySummor;
import com.superdextor.dextersnether.entity.projectile.EntityFlame;
import com.superdextor.dextersnether.entity.projectile.EntityFlameball;
import com.superdextor.dextersnether.entity.projectile.EntityNetherArrow;
import com.superdextor.dextersnether.entity.projectile.EntitySoulTNTPrimed;
import com.superdextor.dextersnether.entity.projectile.EntityTeleport;
import com.superdextor.dextersnether.entity.projectile.EntityWitherSkullCustom;
import com.superdextor.thinkbigcore.entity.EntityCreator;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class NetherEntitys {
   public static void entitys() {
      EntityCreator.createEntity(EntityNetherSpider.class, "dextersnether_Nether_Spider", EnumCreatureType.monster, NetherConfig.netherSpiderSpawns, 1, 3, new BiomeGenBase[]{BiomeGenBase.hell}, 4857874, 2435118, true);
      EntityCreator.createEntity(EntityFlameball.class, "dextersnether_Flameball", EnumCreatureType.ambient);
      EntityCreator.createEntity(EntityReaper.class, "dextersnether_Reaper", EnumCreatureType.monster, NetherConfig.reaperSpawns, 0, 1, new BiomeGenBase[]{BiomeGenBase.hell}, 0, 7171413, true);
      EntityCreator.createEntity(EntityObsidianSheepman.class, "dextersnether_Obsidian_Sheepman", EnumCreatureType.monster, NetherConfig.obsidianSheepSpawns, 10, 20, new BiomeGenBase[]{BiomeGenBase.hell}, 1903672, 16548375, true);
      EntityCreator.createEntity(EntityObsidainSheepmanGolem.class, "dextersnether_Obsidian_Sheepman_Golem", EnumCreatureType.creature, 0, 0, 0, new BiomeGenBase[0], 1903672, 6893035, true);
      EntityCreator.createEntity(EntityCurse.class, "dextersnether_Curse", EnumCreatureType.monster, NetherConfig.curseSpawns, 1, 3, new BiomeGenBase[]{BiomeGenBase.hell}, 12926955, 7541043, true);
      EntityCreator.createEntity(EntityWitherSkullCustom.class, "dextersnether_Wither_Skull", EnumCreatureType.ambient);
      EntityCreator.createEntity(EntityFlame.class, "dextersnether_Flame", EnumCreatureType.ambient);
      EntityCreator.createEntity(EntityGlowstoneSkeleton.class, "dextersnether_Glowstone_Skeleton", EnumCreatureType.monster, NetherConfig.glowstoneSkeletonSpawns, 0, 5, new BiomeGenBase[]{BiomeGenBase.hell}, 16774236, 16107313, true);
      EntityCreator.createEntity(EntitySkeletonHorse.class, "dextersnether_Skeleton_Horse", EnumCreatureType.monster, NetherConfig.skeletonHorseSpawns, 0, 5, new BiomeGenBase[]{BiomeGenBase.hell}, 14606046, 9868950, true);
      EntityCreator.createEntity(EntityNetherArrow.class, "dextersnether_NetherArrow", EnumCreatureType.ambient);
      EntityCreator.createEntity(EntitySoulTNTPrimed.class, "dextersnether_Soul_TNT", EnumCreatureType.ambient);
      EntityCreator.createEntity(EntityHerobrine.class, "dextersnether_", EnumCreatureType.monster, NetherConfig.herobrineSpawns, 1, 1, new BiomeGenBase[]{BiomeGenBase.hell}, 0, 0, false);
      EntityCreator.createEntity(EntitySummor.class, "dextersnether_Summor", EnumCreatureType.creature);
      EntityCreator.createEntity(EntityObsidianGolem.class, "dextersnether_ObsidianGolem", EnumCreatureType.creature);
      EntityCreator.createEntity(EntityQuartzman.class, "dextersnether_Quartzman", EnumCreatureType.creature);
      EntityCreator.createEntity(EntityPhantom.class, "dextersnether_Phantom", EnumCreatureType.monster, 0, 0, 0, new BiomeGenBase[0], 1184274, 12850710, true);
      EntityCreator.createEntity(EntityHerobrineClone.class, "dextersnether__", EnumCreatureType.monster, NetherConfig.herobrineSpawns + 1, 1, 1, new BiomeGenBase[]{BiomeGenBase.hell}, 0, 0, false);
      EntityCreator.createEntity(EntityInfernumAvis.class, "dextersnether_InfernumAvis", EnumCreatureType.monster, 0, 0, 0, new BiomeGenBase[0], 9190537, 12753493, true);
      EntityCreator.createEntity(EntityTeleport.class, "dextersnether_Teleport", EnumCreatureType.ambient);
      EntityCreator.createEntity(EntityInfernalBlaze.class, "dextersnether_InfernalBlaze", EnumCreatureType.monster, NetherConfig.infernalBlazeSpawns, 1, 3, new BiomeGenBase[]{BiomeGenBase.hell}, 14098721, 16736095, true);
   }
}
