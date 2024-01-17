package com.superdextor.dextersnether.inventory;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.entity.monster.EntityCurse;
import com.superdextor.dextersnether.entity.monster.EntityGlowstoneSkeleton;
import com.superdextor.dextersnether.entity.monster.EntityNetherSpider;
import com.superdextor.dextersnether.entity.monster.EntityObsidianSheepman;
import com.superdextor.dextersnether.entity.monster.EntityPhantom;
import com.superdextor.dextersnether.entity.monster.EntityReaper;
import com.superdextor.dextersnether.entity.monster.EntitySkeletonHorse;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.thinkbigcore.ThinkBigConfig;
import com.superdextor.thinkbigcore.entity.EntityCreator;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AltarRecipes {
   private static final AltarRecipes altarLogic = new AltarRecipes();
   public final ArrayList recipes = new ArrayList();

   public static AltarRecipes logic() {
      return altarLogic;
   }

   private AltarRecipes() {
      this.AddSpawnEggRecipe(50, new ItemStack(Items.gunpowder));
      this.AddSpawnEggRecipe(51, new ItemStack(Items.bone), new ItemStack(Items.arrow), new ItemStack(Items.bone));
      this.AddSpawnEggRecipe(52, new ItemStack(Items.string), new ItemStack(Items.spider_eye), new ItemStack(Items.string));
      this.AddSpawnEggRecipe(54, new ItemStack(Items.rotten_flesh));
      this.AddSpawnEggRecipe(55, new ItemStack(Items.slime_ball));
      this.AddSpawnEggRecipe(56, new ItemStack(Items.gunpowder), new ItemStack(Items.ghast_tear), new ItemStack(Items.gunpowder));
      this.AddSpawnEggRecipe(57, new ItemStack(Items.rotten_flesh), new ItemStack(Items.gold_nugget), new ItemStack(Items.rotten_flesh));
      this.AddSpawnEggRecipe(58, (ItemStack)null, new ItemStack(Items.ender_pearl), (ItemStack)null);
      this.AddSpawnEggRecipe(59, new ItemStack(Items.spider_eye), new ItemStack(Items.string), new ItemStack(Items.spider_eye));
      this.AddSpawnEggRecipe(60, (ItemStack)null, new ItemStack(Blocks.cobblestone), (ItemStack)null);
      this.AddSpawnEggRecipe(61, new ItemStack(Items.blaze_powder), new ItemStack(Items.blaze_rod), new ItemStack(Items.blaze_powder));
      this.AddSpawnEggRecipe(62, (ItemStack)null, new ItemStack(Items.magma_cream), (ItemStack)null);
      this.AddSpawnEggRecipe(65, (ItemStack)null, new ItemStack(Blocks.stone), (ItemStack)null);
      this.AddSpawnEggRecipe(66, new ItemStack(Items.nether_wart), new ItemStack(Items.potionitem), new ItemStack(Items.gunpowder));
      this.AddSpawnEggRecipe(90, new ItemStack(Items.porkchop));
      this.AddSpawnEggRecipe(91, new ItemStack(Blocks.wool));
      this.AddSpawnEggRecipe(92, new ItemStack(Items.leather), new ItemStack(Items.beef), new ItemStack(Items.leather));
      this.AddSpawnEggRecipe(93, new ItemStack(Items.feather), new ItemStack(Items.chicken), new ItemStack(Items.feather));
      this.AddSpawnEggRecipe(94, new ItemStack(Items.dye));
      this.AddSpawnEggRecipe(95, new ItemStack(Items.bone));
      this.AddSpawnEggRecipe(96, new ItemStack(Blocks.red_mushroom), new ItemStack(Items.beef), new ItemStack(Items.leather));
      this.AddSpawnEggRecipe(98, new ItemStack(Items.fish));
      this.AddSpawnEggRecipe(100, new ItemStack(Items.leather));
      this.AddSpawnEggRecipe(120, new ItemStack(Items.emerald));
      if (ThinkBigConfig.extraSpawnEggs) {
         this.AddSpawnEggRecipe(97, new ItemStack(Items.snowball), new ItemStack(Blocks.pumpkin), new ItemStack(Items.snowball));
         this.AddSpawnEggRecipe(99, new ItemStack(Blocks.iron_block), new ItemStack(Blocks.pumpkin), new ItemStack(Blocks.iron_block));
         this.AddRecipe(new ItemStack(Items.spawn_egg, 1, 54), new ItemStack(Items.rotten_flesh), new ItemStack(Items.rotten_flesh), new ItemStack(Items.rotten_flesh), new ItemStack(Items.rotten_flesh), new ItemStack(Items.rotten_flesh), new ItemStack(Items.rotten_flesh), new ItemStack(Items.spawn_egg, 1, 53), 0.3F);
      }

      this.AddSpawnEggRecipe(this.getEntityID(EntityNetherSpider.class), new ItemStack(NetherItems.flame_string), new ItemStack(Items.spider_eye), new ItemStack(NetherItems.flame_string));
      this.AddSpawnEggRecipe(this.getEntityID(EntityReaper.class), new ItemStack(NetherItems.netherite_nugget), new ItemStack(Items.ender_pearl), new ItemStack(NetherItems.netherite_nugget));
      this.AddSpawnEggRecipe(this.getEntityID(EntityObsidianSheepman.class), new ItemStack(Items.rotten_flesh), new ItemStack(NetherItems.obsidian_nugget), new ItemStack(Items.rotten_flesh));
      this.AddSpawnEggRecipe(this.getEntityID(EntityCurse.class), new ItemStack(NetherItems.wither_dust), (ItemStack)null, new ItemStack(NetherItems.wither_dust));
      this.AddSpawnEggRecipe(this.getEntityID(EntityGlowstoneSkeleton.class), new ItemStack(Items.glowstone_dust), new ItemStack(Items.bone), new ItemStack(Items.glowstone_dust));
      this.AddRecipe(new ItemStack(Items.spawn_egg, 1, 100), new ItemStack(Items.bone), new ItemStack(Items.bone), new ItemStack(Items.bone), new ItemStack(Items.bone), new ItemStack(Items.bone), new ItemStack(Items.bone), new ItemStack(Items.spawn_egg, 1, this.getEntityID(EntitySkeletonHorse.class)), 0.3F);
      this.AddSpawnEggRecipe(this.getEntityID(EntityPhantom.class), new ItemStack(NetherItems.ectoplasm), (ItemStack)null, new ItemStack(NetherItems.ectoplasm));
      this.AddRecipe(Items.egg, NetherItems.ectoplasm, NetherItems.netherite_nugget, NetherItems.ectoplasm, NetherItems.ectoplasm, NetherItems.netherite_nugget, NetherItems.ectoplasm, Items.spawn_egg, 0.3F);
      this.AddRecipe(Items.netherbrick, NetherItems.wither_dust, NetherItems.obsidian_chunk, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.obsidian_chunk, NetherItems.wither_dust, NetherItems.dark_brick, 1.0F);
      this.AddRecipe(new ItemStack(Blocks.nether_brick), new ItemStack(NetherBlocks.wither_block), new ItemStack(Blocks.obsidian), new ItemStack(NetherBlocks.wither_block), new ItemStack(NetherBlocks.wither_block), new ItemStack(Blocks.obsidian), new ItemStack(NetherBlocks.wither_block), new ItemStack(NetherBlocks.dark_bricks), 0.4F);
      this.AddRecipe((ItemStack)(new ItemStack(Items.glowstone_dust)), (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.ectoplasm)), (ItemStack)null, (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.ectoplasm)), (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.hidden_light, 4)), 0.9F);
      this.AddRecipe((ItemStack)(new ItemStack(Blocks.netherrack)), (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.ectoplasm)), (ItemStack)null, (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.ectoplasm)), (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.hidden_block, 4)), 0.9F);
      this.AddRecipe((ItemStack)(new ItemStack(Blocks.stone)), (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.ectoplasm)), (ItemStack)null, (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.ectoplasm)), (ItemStack)null, (ItemStack)(new ItemStack(NetherItems.hidden_block, 4)), 0.9F);
      this.AddRecipe(Items.ender_pearl, NetherItems.dimensional_dust, NetherItems.netherite_ingot, NetherItems.dimensional_dust, NetherItems.dimensional_dust, NetherItems.netherite_ingot, NetherItems.dimensional_dust, NetherItems.pocket_worm_hole, 0.2F);
      this.AddRecipe(Items.diamond, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_gem, 0.6F);
      this.AddRecipe(Items.apple, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_dust, NetherItems.wither_apple, 0.5F);
      this.AddRecipe(new ItemStack(NetherItems.golden_bucket_lava), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.amulet, 1, 0), 0.2F);
      this.AddRecipe(new ItemStack(NetherItems.wither_gem), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.amulet, 1, 1), 0.2F);
      this.AddRecipe(new ItemStack(NetherItems.quartz_boots), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.amulet, 1, 2), 0.2F);
      this.AddRecipe(new ItemStack(Items.golden_apple), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.amulet, 1, 3), 0.2F);
      this.AddRecipe(new ItemStack(NetherItems.golden_bucket_acid), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.amulet, 1, 4), 0.2F);
      this.AddRecipe(NetherItems.flameball, NetherItems.obsidian_ingot, NetherItems.infernal_powder, NetherItems.netherite_ingot, NetherItems.obsidian_ingot, NetherItems.infernal_powder, NetherItems.netherite_ingot, NetherItems.flame_staff, 0.2F);
      this.AddRecipe(new ItemStack(Items.skull, 1, 1), new ItemStack(NetherItems.wither_gem), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.wither_gem), new ItemStack(NetherItems.dimensional_dust), new ItemStack(NetherItems.netherite_ingot), new ItemStack(NetherItems.wither_staff), 0.2F);
   }

   public void AddSpawnEggRecipe(int entityID, ItemStack fuel) {
      this.AddSpawnEggRecipe(entityID, fuel, fuel, fuel);
   }

   public void AddSpawnEggRecipe(int entityID, ItemStack fuel, ItemStack fuel2, ItemStack fuel3) {
      this.AddSpawnEggRecipe(new ItemStack(Items.spawn_egg, 1, entityID), fuel, fuel2, fuel3);
   }

   public void AddSpawnEggRecipe(ItemStack entityEgg, ItemStack fuel, ItemStack fuel2, ItemStack fuel3) {
      this.AddRecipe(new ItemStack(Items.spawn_egg, 1, 0), fuel, fuel2, fuel3, fuel, fuel2, fuel3, entityEgg, 0.4F);
   }

   public void AddRecipe(Item input0, Item input1, Item input2, Item input3, Item input4, Item input5, Item input6, Item output, float duration) {
      this.AddRecipe(new ItemStack(input0), new ItemStack(input1), new ItemStack(input2), new ItemStack(input3), new ItemStack(input4), new ItemStack(input5), new ItemStack(input6), new ItemStack(output), duration);
   }

   public void AddRecipe(ItemStack input0, ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5, ItemStack input6, ItemStack output, float duration) {
      if (NetherConfig.altar) {
         AltarRecipes.AltarRecipe recipe = new AltarRecipes.AltarRecipe(input0, input1, input2, input3, input4, input5, input6, output, duration);
         this.recipes.add(recipe);
      }

   }

   public int getEntityID(Class entityClass) {
      return (Integer)EntityCreator.classToIDMapping.get(entityClass);
   }

   public AltarRecipes.AltarRecipe getRecipe(ItemStack[] stacks) {
      Iterator i$ = this.recipes.iterator();

      AltarRecipes.AltarRecipe recipe;
      do {
         if (!i$.hasNext()) {
            return null;
         }

         recipe = (AltarRecipes.AltarRecipe)i$.next();
      } while(!ItemStack.areItemStacksEqual(recipe.input0, stacks[0]) || !ItemStack.areItemStacksEqual(recipe.input1, stacks[1]) || !ItemStack.areItemStacksEqual(recipe.input2, stacks[2]) || !ItemStack.areItemStacksEqual(recipe.input3, stacks[3]) || !ItemStack.areItemStacksEqual(recipe.input4, stacks[4]) || !ItemStack.areItemStacksEqual(recipe.input5, stacks[5]) || !ItemStack.areItemStacksEqual(recipe.input6, stacks[6]));

      return recipe;
   }

   public class AltarRecipe {
      public final ItemStack input0;
      public final ItemStack input1;
      public final ItemStack input2;
      public final ItemStack input3;
      public final ItemStack input4;
      public final ItemStack input5;
      public final ItemStack input6;
      public final ItemStack output;
      public final float duration;

      public AltarRecipe(ItemStack input0, ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5, ItemStack input6, ItemStack output, float duration) {
         this.input0 = input0;
         this.input1 = input1;
         this.input2 = input2;
         this.input3 = input3;
         this.input4 = input4;
         this.input5 = input5;
         this.input6 = input6;
         this.output = output;
         this.duration = duration;
      }

      public String toString() {
         return "[" + this.input0 + "," + this.input1 + "," + this.input2 + "," + this.input3 + "," + this.input4 + "," + this.input5 + "," + this.input6 + "]";
      }
   }
}
