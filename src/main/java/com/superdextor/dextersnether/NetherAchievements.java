package com.superdextor.dextersnether;

import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.thinkbigcore.ModHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class NetherAchievements {
   public static Achievement welcome;
   public static Achievement quartz;
   public static Achievement demonSlayer;
   public static Achievement scythe;
   public static Achievement obsidiancow;
   public static Achievement intoFlames;
   public static Achievement hellProspector;
   public static Achievement wither;
   public static Achievement witherApple;
   public static Achievement netherite;
   public static Achievement wakeAvis;
   public static Achievement hellAltar;
   public static Achievement summor;
   public static Achievement witherStaff;
   public static Achievement flameStaff;
   public static Achievement abyss;
   public static Achievement pocketPortal;

   public static void register() {
      welcome = ModHelper.addAchievement("welcome", 0, 0, new ItemStack(Blocks.netherrack));
      quartz = ModHelper.addAchievement("quartz", 2, 1, new ItemStack(Items.quartz), welcome);
      demonSlayer = ModHelper.addAchievement("demonSlayer", 4, 1, new ItemStack(NetherItems.netherrack_sword), quartz);
      scythe = ModHelper.addAchievement("scythe", 4, -1, new ItemStack(NetherItems.scythe), demonSlayer, true);
      obsidiancow = ModHelper.addAchievement("obsidiancow", 5, 0, new ItemStack(NetherItems.obsidian_nugget), demonSlayer);
      intoFlames = ModHelper.addAchievement("intoFlames", 5, 2, new ItemStack(NetherItems.infernal_rod), demonSlayer);
      hellProspector = ModHelper.addAchievement("hellProspector", 2, 3, new ItemStack(NetherItems.netherrack_pickaxe), quartz);
      wither = ModHelper.addAchievement("wither", 0, 4, new ItemStack(NetherItems.wither_dust), hellProspector);
      witherApple = ModHelper.addAchievement("witherApple", -1, 2, new ItemStack(NetherItems.wither_apple), wither);
      netherite = ModHelper.addAchievement("netherite", 1, 6, new ItemStack(NetherItems.netherite_ingot), wither);
      wakeAvis = ModHelper.addAchievement("wakeAvis", 0, 8, new ItemStack(NetherItems.avis_wing), netherite);
      summor = ModHelper.addAchievement("summor", 3, 7, new ItemStack(NetherBlocks.dark_fire), netherite, true);
      hellAltar = ModHelper.addAchievement("hellAltar", 5, 8, new ItemStack(NetherBlocks.nether_altar), summor);
      witherStaff = ModHelper.addAchievement("witherstaff", 4, 6, new ItemStack(NetherItems.wither_staff), hellAltar, true);
      flameStaff = ModHelper.addAchievement("flamestaff", 6, 6, new ItemStack(NetherItems.flame_staff), hellAltar, true);
      abyss = ModHelper.addAchievement("abyss", 4, 10, new ItemStack(NetherBlocks.dark_bricks), hellAltar, true);
      pocketPortal = ModHelper.addAchievement("pocketPortal", 2, 10, new ItemStack(NetherItems.pocket_worm_hole), abyss, true);
      AchievementPage.registerAchievementPage(new AchievementPage("Ad Inferos", new Achievement[]{welcome, quartz, demonSlayer, obsidiancow, intoFlames, hellProspector, wither, witherApple, witherStaff, scythe, flameStaff, summor, netherite, wakeAvis, hellAltar, abyss, pocketPortal}));
   }
}
