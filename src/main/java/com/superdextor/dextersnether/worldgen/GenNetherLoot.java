package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.world.NetherHooks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class GenNetherLoot extends ChestGenHooks {
   private static final HashMap chestInfo = new HashMap();
   private int countMin;
   private int countMax;
   private ArrayList contents;

   private static void init() {
      addInfo("NetherChest", NetherHooks.NETHERCHESTItems, 8, 8);
      addInfo("PiggyChest", NetherHooks.PIGGYCHESTItems, 2, 5);
   }

   private static void addInfo(String category, WeightedRandomChestContent[] items, int min, int max) {
      chestInfo.put(category, new GenNetherLoot(category, items, min, max));
   }

   public static GenNetherLoot getInfo(String category) {
      if (!chestInfo.containsKey(category)) {
         chestInfo.put(category, new GenNetherLoot(category));
      }

      return (GenNetherLoot)chestInfo.get(category);
   }

   public WeightedRandomChestContent[] getItems(Random rnd) {
      ArrayList ret = new ArrayList();
      Iterator i$ = this.contents.iterator();

      while(i$.hasNext()) {
         WeightedRandomChestContent orig = (WeightedRandomChestContent)i$.next();
         Item item = orig.theItemId.getItem();
         if (item != null) {
            WeightedRandomChestContent n = item.getChestGenBase(this, rnd, orig);
            if (n != null) {
               ret.add(n);
            }
         }
      }

      return (WeightedRandomChestContent[])ret.toArray(new WeightedRandomChestContent[ret.size()]);
   }

   public int getCount(Random rand) {
      return this.countMin < this.countMax ? this.countMin + rand.nextInt(this.countMax - this.countMin) : this.countMin;
   }

   public void removeItem(ItemStack item) {
      Iterator itr = this.contents.iterator();

      while(true) {
         WeightedRandomChestContent cont;
         do {
            if (!itr.hasNext()) {
               return;
            }

            cont = (WeightedRandomChestContent)itr.next();
         } while(!item.isItemEqual(cont.theItemId) && (item.getItemDamage() != 32767 || item.getItem() != cont.theItemId.getItem()));

         itr.remove();
      }
   }

   public ItemStack getOneItem(Random rand) {
      WeightedRandomChestContent[] items = this.getItems(rand);
      WeightedRandomChestContent item = (WeightedRandomChestContent)WeightedRandom.getRandomItem(rand, items);
      ItemStack[] stacks = ChestGenHooks.generateStacks(rand, item.theItemId, item.theMinimumChanceToGenerateItem, item.theMaximumChanceToGenerateItem);
      return stacks.length > 0 ? stacks[0] : null;
   }

   public static WeightedRandomChestContent[] getItems(String category, Random rnd) {
      return getInfo(category).getItems(rnd);
   }

   public static int getCount(String category, Random rand) {
      return getInfo(category).getCount(rand);
   }

   public static void addItem(String category, WeightedRandomChestContent item) {
      getInfo(category).addItem(item);
   }

   public static void removeItem(String category, ItemStack item) {
      getInfo(category).removeItem(item);
   }

   public static ItemStack getOneItem(String category, Random rand) {
      return getInfo(category).getOneItem(rand);
   }

   public GenNetherLoot(String category) {
      super(category);
      this.countMin = 0;
      this.countMax = 0;
      this.contents = new ArrayList();
   }

   public GenNetherLoot(String category, WeightedRandomChestContent[] items, int min, int max) {
      this(category);
      WeightedRandomChestContent[] arr$ = items;
      int len$ = items.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         WeightedRandomChestContent item = arr$[i$];
         this.contents.add(item);
      }

      this.countMin = min;
      this.countMax = max;
   }

   static {
      init();
   }
}
