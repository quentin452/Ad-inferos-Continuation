package com.superdextor.dextersnether;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class NetherConfig {
   public static boolean amuletEffects;
   public static boolean abyssPortalTravel;
   public static boolean pocketTeleports;
   public static int berserkEnchID;
   public static int curseEnchID;
   public static int dimensionID;
   public static int netherBiomeID;
   public static int abyssBiomeID;
   public static boolean crafting;
   public static boolean netherWoodRecipe;
   public static boolean netherrackRecipe;
   public static boolean quartzRecipe;
   public static boolean glowstoneRecipe;
   public static boolean obsidianRecipe;
   public static boolean witherRecipe;
   public static boolean netheriteRecipe;
   public static boolean miscRecipe;
   public static boolean altar;
   public static boolean smelting;
   public static boolean genOres;
   public static boolean genDungeons;
   public static boolean genTrees;
   public static boolean genAcid;
   public static boolean genMushrooms;
   public static boolean genDarkFire;
   public static boolean genPiggyHouse;
   public static boolean genObsidianPyramide;
   public static boolean genObsidianBeach;
   public static boolean genSpikes;
   public static boolean genDimstone;
   public static boolean genHellBiomes;
   public static boolean abyssPortalsAnywhere;
   public static boolean buildAbyssPortal;
   public static boolean buildObsidianGolem;
   public static boolean buildQuartzGolem;
   public static boolean buildSummor;
   public static int netherSpiderSpawns;
   public static int reaperSpawns;
   public static int obsidianSheepSpawns;
   public static int curseSpawns;
   public static int glowstoneSkeletonSpawns;
   public static int skeletonHorseSpawns;
   public static int phantomSpawns;
   public static int infernalBlazeSpawns;
   public static int herobrineSpawns;
   public static boolean glowstoneSwordFire;
   public static boolean flameStaffGriefing;
   public static boolean witherStaffGriefing;
   public static boolean soulTNTGriefing;
   public static boolean reaperGriefing;

   public static void register(File configFile) {
      Configuration config = new Configuration(configFile);

      try {
         config.load();
         String General = "general" + "." + "general";
         String Recipes = "general" + "." + "recipes";
         String World = "general" + "." + "world";
         String Spawn = "general" + "." + "spawns";
         String IDs = "general" + "." + "ids";
         String Griefing = "general" + "." + "griefing";
         config.addCustomCategoryComment(General, "Ad Inferos 1.7.10 - 1.1 created by superdextor");
         amuletEffects = config.getBoolean("Amulet Effects", General, true, "");
         abyssPortalTravel = config.getBoolean("Abyss Portal Travel", General, true, "");
         pocketTeleports = config.getBoolean("Pocket-Wormhole Travel", General, true, "");
         berserkEnchID = config.getInt("Berserk EnchantmentID", IDs, 65, 63, 255, "");
         curseEnchID = config.getInt("Curse EnchantmentID", IDs, 64, 63, 255, "");
         dimensionID = config.getInt("Dimension ID", IDs, 10, 2, 99, "");
         abyssBiomeID = config.getInt("Abyss Biome ID", IDs, 93, 40, 256, "");
         netherBiomeID = config.getInt("Nether Biome ID", IDs, 94, 40, 256, "");
         crafting = config.getBoolean("Crafting Recipes", Recipes, true, "");
         smelting = config.getBoolean("Smelting Recipes", Recipes, true, "");
         altar = config.getBoolean("Altar Recipes", Recipes, true, "");
         netherWoodRecipe = config.getBoolean("Nether Wood Recipes", Recipes, true, "'Crafting' Must be enabled");
         netherrackRecipe = config.getBoolean("Netherrack Recipes", Recipes, true, "");
         quartzRecipe = config.getBoolean("Quartz Recipes", Recipes, true, "");
         glowstoneRecipe = config.getBoolean("Glowstone Recipes", Recipes, true, "");
         obsidianRecipe = config.getBoolean("Obsidian Recipes", Recipes, true, "");
         netheriteRecipe = config.getBoolean("Netherite Recipes", Recipes, true, "");
         miscRecipe = config.getBoolean("Miscellaneous Recipes", Recipes, true, "");
         genOres = config.getBoolean("Generate Ores", World, true, "");
         genDungeons = config.getBoolean("Generate Nether-Dungeons", World, true, "");
         genTrees = config.getBoolean("Generate Nether-Wood Trees", World, true, "");
         genAcid = config.getBoolean("Generate Acid", World, true, "");
         genMushrooms = config.getBoolean("Generate Nether-Mushrooms", World, true, "");
         genDarkFire = config.getBoolean("Generate Dark-Fire", World, true, "");
         genHellBiomes = config.getBoolean("Overworld Nether-Biome", World, true, "");
         genPiggyHouse = config.getBoolean("Generate Piggy-Houses", World, true, "");
         genObsidianPyramide = config.getBoolean("Generate Obsidian-Pyramids", World, true, "");
         genObsidianBeach = config.getBoolean("Generate Obsidian-Beaches", World, true, "");
         genSpikes = config.getBoolean("Generate Nether-Spikes", World, true, "");
         genDimstone = config.getBoolean("Generate Dimstone", World, true, "");
         abyssPortalsAnywhere = config.getBoolean("Abyss-Portals Anywhere", World, false, "");
         buildAbyssPortal = config.getBoolean("Build Abyss-Portal", World, true, "");
         buildQuartzGolem = config.getBoolean("Build Quartz-Golem", World, true, "");
         buildObsidianGolem = config.getBoolean("Build Obsidian-Golem", World, true, "");
         buildSummor = config.getBoolean("Build Summor", World, true, "");
         netherSpiderSpawns = config.getInt("Nether-Spider", Spawn, 50, 0, 500, "");
         reaperSpawns = config.getInt("Reaper", Spawn, 10, 0, 500, "");
         obsidianSheepSpawns = config.getInt("Obsidian-Sheepman", Spawn, 30, 0, 500, "");
         curseSpawns = config.getInt("Curse", Spawn, 15, 0, 500, "");
         glowstoneSkeletonSpawns = config.getInt("Glowstone-Skeleton", Spawn, 30, 0, 500, "");
         skeletonHorseSpawns = config.getInt("Skeleton-Horse", Spawn, 30, 0, 500, "");
         phantomSpawns = config.getInt("Phantom", Spawn, 50, 0, 500, "");
         infernalBlazeSpawns = config.getInt("Infernal-Blaze", Spawn, 8, 0, 500, "");
         herobrineSpawns = config.getInt("Herobrine", Spawn, 0, 0, 500, "");
         glowstoneSwordFire = config.getBoolean("GlowstoneSword Fire", Griefing, true, "");
         flameStaffGriefing = config.getBoolean("FlameStaff Griefing", Griefing, true, "");
         witherStaffGriefing = config.getBoolean("WitherStaff Griefing", Griefing, true, "");
         soulTNTGriefing = config.getBoolean("SoulTNT Griefing", Griefing, true, "");
         reaperGriefing = config.getBoolean("Reaper Griefing", Griefing, true, "");
      } catch (Exception var11) {
      } finally {
         config.save();
      }

   }
}
