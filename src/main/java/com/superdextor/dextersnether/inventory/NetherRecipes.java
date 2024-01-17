package com.superdextor.dextersnether.inventory;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.thinkbigcore.RecipeHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class NetherRecipes {
   public static void register() {
      if (NetherConfig.crafting) {
         if (NetherConfig.netherWoodRecipe) {
            NetherWoodRecipes();
         }

         if (NetherConfig.netherrackRecipe) {
            NetherrackRecipes();
         }

         if (NetherConfig.quartzRecipe) {
            QuartzRecipes();
         }

         if (NetherConfig.glowstoneRecipe) {
            GlowstoneRecipes();
         }

         if (NetherConfig.obsidianRecipe) {
            ObsidianRecipes();
         }

         if (NetherConfig.witherRecipe) {
            WitherRecipes();
         }

         if (NetherConfig.netheriteRecipe) {
            NetheriteRecipes();
         }

         if (NetherConfig.miscRecipe) {
            MiscRecipes();
         }
      }

   }

   private static void NetherWoodRecipes() {
      RecipeHelper.addToolRecipe(new ItemStack(NetherBlocks.nether_planks), Items.stick, Items.wooden_sword, Items.wooden_axe, Items.wooden_pickaxe, Items.wooden_shovel, Items.wooden_hoe);
      GameRegistry.addShapelessRecipe(new ItemStack(NetherBlocks.nether_planks, 4), new Object[]{NetherBlocks.nether_log});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_slab, 6), new Object[]{"WWW", 'W', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_stairs, 4), new Object[]{"W  ", "WW ", "WWW", 'W', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_stairs, 4), new Object[]{"  W", " WW", "WWW", 'W', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Items.stick, 4), new Object[]{"I", "I", 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_crafting_table), new Object[]{"II", "II", 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Blocks.chest), new Object[]{"III", "I I", "III", 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_pressure_plate), new Object[]{"II", 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Blocks.wooden_button), new Object[]{"I", 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Items.bowl, 4), new Object[]{"I I", " I ", 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Blocks.jukebox), new Object[]{"III", "IZI", "III", 'Z', Items.diamond, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Blocks.noteblock), new Object[]{"III", "IZI", "III", 'Z', Items.redstone, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Items.sign, 3), new Object[]{"III", "III", " S ", 'S', Items.stick, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Blocks.bookshelf), new Object[]{"III", "ZZZ", "III", 'Z', Items.book, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Blocks.jukebox), new Object[]{"III", "IZI", "III", 'Z', Items.diamond, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_trapdoor, 2), new Object[]{"III", "III", 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_fence, 3), new Object[]{"IZI", "IZI", 'Z', Items.stick, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_fence_gate), new Object[]{"ZIZ", "ZIZ", 'Z', Items.stick, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Items.bed), new Object[]{"ZZZ", "III", 'Z', Blocks.wool, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Blocks.tripwire_hook, 2), new Object[]{"X", "Z", "I", 'X', Items.iron_ingot, 'Z', Items.stick, 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(NetherItems.nether_door_item, 3), new Object[]{"II", "II", "II", 'I', NetherBlocks.nether_planks});
      GameRegistry.addRecipe(new ItemStack(Items.boat), new Object[]{"I I", "III", 'I', NetherBlocks.nether_planks});
      GameRegistry.addSmelting(NetherBlocks.nether_trapdoor, new ItemStack(NetherBlocks.nether_trapdoor_hidden, 1), 0.1F);
   }

   private static void NetherrackRecipes() {
      RecipeHelper.addMaterialSetRecipe(new ItemStack(Blocks.netherrack), NetherItems.quartz_stick, NetherItems.netherrack_sword, NetherItems.netherrack_axe, NetherItems.netherrack_pickaxe, NetherItems.netherrack_shovel, NetherItems.netherrack_hoe, NetherItems.netherrack_helmet, NetherItems.netherrack_chestplate, NetherItems.netherrack_leggings, NetherItems.netherrack_boots);
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.netherrack_chiseled), new Object[]{"I", "I", 'I', NetherBlocks.smooth_netherrack_slab});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.smooth_netherrack, 4), new Object[]{"NN", "NN", 'N', Blocks.netherrack});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.smooth_netherrack_slab, 6), new Object[]{"WWW", 'W', NetherBlocks.smooth_netherrack});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.smooth_netherrack_stairs, 4), new Object[]{"W  ", "WW ", "WWW", 'W', NetherBlocks.smooth_netherrack});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.smooth_netherrack_stairs, 4), new Object[]{"W  ", "WW ", "WWW", 'W', NetherBlocks.smooth_netherrack});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_furnace), new Object[]{"XXX", "X X", "XXX", 'X', Blocks.netherrack});
   }

   private static void QuartzRecipes() {
      RecipeHelper.addMaterialSetRecipe(new ItemStack(NetherItems.quartz_ingot), NetherItems.quartz_stick, NetherItems.quartz_sword, NetherItems.quartz_axe, NetherItems.quartz_pickaxe, NetherItems.quartz_shovel, NetherItems.quartz_hoe, NetherItems.quartz_helmet, NetherItems.quartz_chestplate, NetherItems.quartz_leggings, NetherItems.quartz_boots);
      GameRegistry.addRecipe(new ItemStack(NetherItems.quartz_stick, 4), new Object[]{"X", "X", 'X', Items.quartz});
      GameRegistry.addRecipe(new ItemStack(NetherItems.quartz_ingot, 1), new Object[]{"XX", 'X', Items.quartz});
      GameRegistry.addRecipe(new ItemStack(NetherItems.quartz_bow), new Object[]{"FQ ", "F Q", "FQ ", 'F', NetherItems.flame_string, 'Q', NetherItems.quartz_ingot});
      GameRegistry.addRecipe(new ItemStack(NetherItems.quartz_arrow, 12), new Object[]{"B", "Q", "F", 'B', NetherItems.quartz_ingot, 'Q', Items.blaze_rod, 'F', Items.quartz});
   }

   private static void GlowstoneRecipes() {
      RecipeHelper.addMaterialSetRecipe(new ItemStack(Blocks.glowstone), Items.blaze_rod, NetherItems.glowstone_sword, NetherItems.glowstone_axe, NetherItems.glowstone_pickaxe, NetherItems.glowstone_shovel, NetherItems.glowstone_hoe, NetherItems.glowstone_helmet, NetherItems.glowstone_chestplate, NetherItems.glowstone_leggings, NetherItems.glowstone_boots);
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.glowstone_torch, 4), new Object[]{"G", "Q", 'G', Items.glowstone_dust, 'Q', NetherItems.quartz_stick});
      GameRegistry.addRecipe(new ItemStack(NetherItems.glowstone_bow), new Object[]{"FQ ", "F Q", "FQ ", 'F', NetherItems.flame_string, 'Q', Blocks.glowstone});
      GameRegistry.addRecipe(new ItemStack(NetherItems.glowstone_arrow, 12), new Object[]{"B", "Q", "F", 'B', Blocks.glowstone, 'Q', Items.blaze_rod, 'F', Items.quartz});
   }

   private static void ObsidianRecipes() {
      RecipeHelper.addMaterialSetRecipe(new ItemStack(NetherItems.obsidian_ingot), NetherItems.quartz_stick, NetherItems.obsidian_sword, NetherItems.obsidian_axe, NetherItems.obsidian_pickaxe, NetherItems.obsidian_shovel, NetherItems.obsidian_hoe, NetherItems.obsidian_helmet, NetherItems.obsidian_chestplate, NetherItems.obsidian_leggings, NetherItems.obsidian_boots);
      GameRegistry.addRecipe(new ItemStack(NetherItems.obsidian_ingot), new Object[]{"XXX", "XXX", "XXX", 'X', NetherItems.obsidian_nugget});
      GameRegistry.addShapelessRecipe(new ItemStack(NetherItems.obsidian_nugget, 9), new Object[]{NetherItems.obsidian_ingot});
      GameRegistry.addRecipe(new ItemStack(NetherItems.obsidian_ingot, 1), new Object[]{"XX", 'X', NetherItems.obsidian_chunk});
      GameRegistry.addRecipe(new ItemStack(NetherItems.obsidian_bow), new Object[]{"FQ ", "F Q", "FQ ", 'F', NetherItems.flame_string, 'Q', NetherItems.obsidian_ingot});
      GameRegistry.addRecipe(new ItemStack(NetherItems.obsidian_arrow, 12), new Object[]{"B", "Q", "F", 'B', NetherItems.obsidian_ingot, 'Q', Items.blaze_rod, 'F', Items.quartz});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.obsidian_slab, 6), new Object[]{"WWW", 'W', Blocks.obsidian});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.obsidian_stairs, 4), new Object[]{"W  ", "WW ", "WWW", 'W', Blocks.obsidian});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.obsidian_stairs, 4), new Object[]{"W  ", "WW ", "WWW", 'W', Blocks.obsidian});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.obsidian_fence_gate), new Object[]{"ZIZ", "ZIZ", 'Z', NetherItems.quartz_stick, 'I', Blocks.obsidian});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.obsidian_fence, 3), new Object[]{"IZI", "IZI", 'Z', NetherItems.quartz_stick, 'I', Blocks.obsidian});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.obsidian_chiseled), new Object[]{"I", "I", 'I', NetherBlocks.obsidian_slab});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.obsidian_smooth, 4), new Object[]{"II", "II", 'I', Blocks.obsidian});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.obsidian_bars, 16), new Object[]{"II", "II", 'I', NetherItems.obsidian_ingot});
      GameRegistry.addSmelting(Blocks.obsidian, new ItemStack(NetherItems.obsidian_chunk, 1), 0.9F);
   }

   private static void WitherRecipes() {
      RecipeHelper.addMaterialSetRecipe(new ItemStack(NetherItems.wither_gem), NetherBlocks.wither_gem_block, NetherItems.quartz_stick, NetherItems.wither_sword, NetherItems.wither_axe, NetherItems.wither_pickaxe, NetherItems.wither_shovel, NetherItems.wither_hoe, NetherItems.wither_helmet, NetherItems.wither_chestplate, NetherItems.wither_leggings, NetherItems.wither_boots);
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.wither_block), new Object[]{"III", "III", "III", 'I', NetherItems.wither_dust});
      GameRegistry.addShapelessRecipe(new ItemStack(NetherItems.wither_dust, 9), new Object[]{NetherBlocks.wither_block});
      GameRegistry.addRecipe(new ItemStack(NetherItems.wither_bow), new Object[]{"FQ ", "F Q", "FQ ", 'F', NetherItems.flame_string, 'Q', NetherItems.wither_gem});
      GameRegistry.addRecipe(new ItemStack(NetherItems.wither_arrow, 12), new Object[]{"B", "Q", "F", 'B', NetherItems.wither_gem, 'Q', NetherItems.netherite_ingot, 'F', Items.quartz});
      GameRegistry.addSmelting(NetherBlocks.wither_ore, new ItemStack(NetherItems.wither_dust, 4), 1.0F);
      GameRegistry.addSmelting(NetherBlocks.wither_ore_darkstone, new ItemStack(NetherItems.wither_dust, 4), 1.0F);
   }

   private static void NetheriteRecipes() {
      RecipeHelper.addMaterialSetRecipe(new ItemStack(NetherItems.netherite_ingot), NetherBlocks.netherite_block, Items.blaze_rod, NetherItems.netherite_sword, NetherItems.netherite_axe, NetherItems.netherite_pickaxe, NetherItems.netherite_shovel, NetherItems.netherite_hoe, NetherItems.netherite_helmet, NetherItems.netherite_chestplate, NetherItems.netherite_leggings, NetherItems.netherite_boots);
      GameRegistry.addRecipe(new ItemStack(NetherItems.netherite_ingot), new Object[]{"XXX", "XXX", "XXX", 'X', NetherItems.netherite_nugget});
      GameRegistry.addShapelessRecipe(new ItemStack(NetherItems.netherite_nugget, 9), new Object[]{NetherItems.netherite_ingot});
      GameRegistry.addRecipe(new ItemStack(NetherItems.netherite_bow), new Object[]{"FQ ", "F Q", "FQ ", 'F', NetherItems.flame_string, 'Q', NetherItems.netherite_ingot});
      GameRegistry.addRecipe(new ItemStack(NetherItems.netherite_arrow, 12), new Object[]{"B", "Q", "F", 'B', NetherItems.netherite_ingot, 'Q', NetherItems.infernal_rod, 'F', Items.quartz});
      GameRegistry.addSmelting(NetherBlocks.netherite_ore, new ItemStack(NetherItems.netherite_nugget, 4), 1.0F);
      GameRegistry.addSmelting(NetherBlocks.netherite_ore_darkstone, new ItemStack(NetherItems.netherite_nugget, 4), 1.0F);
   }

   private static void MiscRecipes() {
      GameRegistry.addShapelessRecipe(new ItemStack(NetherItems.flameball, 3), new Object[]{Items.blaze_powder, Items.quartz, Items.gunpowder});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.soul_tnt), new Object[]{"GSG", "SGS", "GSG", 'S', Blocks.soul_sand, 'G', Items.gunpowder});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.soul_glass_pane, 16), new Object[]{"WWW", "WWW", 'W', NetherBlocks.soul_glass});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_brick_fence_gate), new Object[]{"ZIZ", "ZIZ", 'Z', NetherItems.quartz_stick, 'I', Blocks.nether_brick});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.dark_bricks), new Object[]{"II", "II", 'I', NetherItems.dark_brick});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.nether_altar), new Object[]{" G ", "WZW", "III", 'Z', NetherItems.darkcore, 'W', NetherItems.avis_wing, 'G', NetherItems.wither_dust, 'I', NetherItems.netherite_ingot});
      GameRegistry.addRecipe(new ItemStack(NetherBlocks.dimstone), new Object[]{"II", "II", 'I', NetherItems.dimstone_dust});
      GameRegistry.addShapelessRecipe(new ItemStack(NetherItems.infernal_powder, 2), new Object[]{NetherItems.infernal_rod});
      GameRegistry.addSmelting(NetherBlocks.nether_log, new ItemStack(Items.coal, 1), 0.4F);
      GameRegistry.addSmelting(NetherBlocks.nether_mushroom, new ItemStack(NetherItems.fired_nether_mushroom, 1), 0.4F);
      GameRegistry.addSmelting(Blocks.soul_sand, new ItemStack(NetherBlocks.soul_glass, 1), 0.1F);
      GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(NetherItems.cooked_flesh, 1), 0.2F);
      GameRegistry.addRecipe(new ItemStack(NetherItems.golden_bucket_empty), new Object[]{"G G", " G ", 'G', Items.gold_ingot});
      GameRegistry.addRecipe(new ItemStack(NetherItems.golden_shears), new Object[]{" I", "I ", 'I', Items.gold_ingot});
      GameRegistry.addSmelting(NetherBlocks.gold_ore_nether, new ItemStack(Items.gold_ingot, 1), 0.7F);
      GameRegistry.addSmelting(NetherBlocks.dimensional_ore, new ItemStack(NetherItems.dimensional_dust, 1), 1.0F);
   }
}
