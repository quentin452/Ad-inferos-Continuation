package com.superdextor.dextersnether.worldgen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom.Item;

public class NetherDungeonHooks {
   private static ArrayList dungeonMobs = new ArrayList();

   public static float addDungeonMob(String name, int rarity) {
      if (rarity <= 0) {
         throw new IllegalArgumentException("Rarity must be greater then zero");
      } else {
         Iterator i$ = dungeonMobs.iterator();

         NetherDungeonHooks.DungeonMob mob;
         do {
            if (!i$.hasNext()) {
               dungeonMobs.add(new NetherDungeonHooks.DungeonMob(rarity, name));
               return (float)rarity;
            }

            mob = (NetherDungeonHooks.DungeonMob)i$.next();
         } while(!name.equals(mob.type));

         return (float)(mob.itemWeight += rarity);
      }
   }

   public static int removeDungeonMob(String name) {
      Iterator i$ = dungeonMobs.iterator();

      NetherDungeonHooks.DungeonMob mob;
      do {
         if (!i$.hasNext()) {
            return 0;
         }

         mob = (NetherDungeonHooks.DungeonMob)i$.next();
      } while(!name.equals(mob.type));

      dungeonMobs.remove(mob);
      return mob.itemWeight;
   }

   public static String getRandomDungeonMob(Random rand) {
      NetherDungeonHooks.DungeonMob mob = (NetherDungeonHooks.DungeonMob)WeightedRandom.getRandomItem(rand, dungeonMobs);
      return mob == null ? "" : mob.type;
   }

   static {
      addDungeonMob("dextersnether_Nether_Spider", 100);
      addDungeonMob("dextersnether_Obsidian_Sheepman", 100);
      addDungeonMob("dextersnether_Glowstone_Skeleton", 100);
      addDungeonMob("Blaze", 100);
      addDungeonMob("PigZombie", 100);
      addDungeonMob("Skeleton", 100);
   }

   public static class DungeonMob extends Item {
      public String type;

      public DungeonMob(int weight, String type) {
         super(weight);
         this.type = type;
      }

      public boolean equals(Object target) {
         return target instanceof NetherDungeonHooks.DungeonMob && this.type.equals(((NetherDungeonHooks.DungeonMob)target).type);
      }
   }
}
