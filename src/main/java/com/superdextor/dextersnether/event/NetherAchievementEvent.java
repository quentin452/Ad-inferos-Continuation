package com.superdextor.dextersnether.event;

import com.superdextor.dextersnether.NetherAchievements;
import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class NetherAchievementEvent {
   @SubscribeEvent
   public void ChangedDimension(PlayerChangedDimensionEvent e) {
      if (e.toDim == -1) {
         e.player.triggerAchievement(NetherAchievements.welcome);
      }

      if (e.toDim == NetherConfig.dimensionID) {
         e.player.triggerAchievement(NetherAchievements.abyss);
      }

   }

   @SubscribeEvent
   public void onItemPickup(ItemPickupEvent e) {
      Item item = e.pickedUp.getEntityItem().getItem();
      if (item.equals(NetherItems.wither_dust)) {
      }

      e.player.triggerAchievement(NetherAchievements.wither);
      if (item.equals(NetherItems.scythe)) {
         e.player.triggerAchievement(NetherAchievements.scythe);
      }

      if (item.equals(Items.quartz)) {
         e.player.triggerAchievement(NetherAchievements.quartz);
      }

      if (item.equals(NetherItems.infernal_rod)) {
         e.player.triggerAchievement(NetherAchievements.intoFlames);
      }

      if (item.equals(NetherItems.netherite_ingot) || item.equals(NetherItems.netherite_nugget)) {
         e.player.triggerAchievement(NetherAchievements.netherite);
      }

   }

   @SubscribeEvent
   public void onCraftItem(ItemCraftedEvent e) {
      Item item = e.crafting.getItem();
      if (item.equals(NetherItems.netherrack_pickaxe) || item.equals(NetherItems.quartz_pickaxe) || item.equals(NetherItems.glowstone_pickaxe) || item.equals(NetherItems.obsidian_pickaxe) || item.equals(NetherItems.wither_pickaxe) || item.equals(NetherItems.netherite_pickaxe)) {
         e.player.triggerAchievement(NetherAchievements.hellProspector);
      }

      if (item == Item.getItemFromBlock(NetherBlocks.nether_altar)) {
         e.player.triggerAchievement(NetherAchievements.hellAltar);
      }

   }
}
