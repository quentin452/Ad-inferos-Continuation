package com.superdextor.dextersnether.event;

import com.superdextor.dextersnether.NetherAchievements;
import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.entity.monster.EntityObsidianSheepman;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.dextersnether.items.ItemWitherArmor;
import com.superdextor.thinkbigcore.items.ItemCustomArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class NetherEvents {
   @SubscribeEvent
   public void onUpdate(LivingUpdateEvent event) {
      if (event.entityLiving instanceof EntityPlayer && event.entityLiving.motionY < 0.0D && !event.entityLiving.isSneaking() && !event.entityLiving.isBurning() && !event.entityLiving.isInWater() && !event.entityLiving.isOnLadder()) {
         EntityPlayer player = (EntityPlayer)event.entityLiving;
         boolean quartzFlag = false;

         for(int k = 1; k < 5; ++k) {
            if (player.getEquipmentInSlot(k) != null && player.getEquipmentInSlot(k).getItem() instanceof ItemCustomArmor && ((ItemCustomArmor)player.getEquipmentInSlot(k).getItem()).getMaterial() == NetherItems.QuartzArmorMaterial) {
               quartzFlag = true;
               break;
            }
         }

         if (NetherConfig.amuletEffects && !quartzFlag && player.inventory.hasItem(NetherItems.amulet)) {
            ItemStack stack = null;

            for(int i = 0; i < player.inventory.mainInventory.length; ++i) {
               if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == NetherItems.amulet && player.inventory.mainInventory[i].getItemDamage() == 2) {
                  quartzFlag = true;
                  break;
               }
            }
         }

         if (quartzFlag) {
            player.motionY *= 0.7D;
            player.fallDistance = 0.0F;
         }
      }

   }

   @SubscribeEvent
   public void onDamage(LivingHurtEvent event) {
      if (event.entityLiving instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)event.entityLiving;
         boolean fireFlag = false;
         boolean witherFlag = false;

         for(int k = 1; k < 5; ++k) {
            if (player.getEquipmentInSlot(k) != null && player.getEquipmentInSlot(k).getItem() instanceof ItemWitherArmor) {
               witherFlag = true;
               break;
            }
         }

         int i;
         Object stack;
         if (NetherConfig.amuletEffects && !witherFlag && player.inventory.hasItem(NetherItems.amulet)) {
            stack = null;

            for(i = 0; i < player.inventory.mainInventory.length; ++i) {
               if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == NetherItems.amulet && player.inventory.mainInventory[i].getItemDamage() == 1) {
                  witherFlag = true;
                  break;
               }
            }
         }

         if (NetherConfig.amuletEffects && player.inventory.hasItem(NetherItems.amulet)) {
            stack = null;

            for(i = 0; i < player.inventory.mainInventory.length; ++i) {
               if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == NetherItems.amulet && player.inventory.mainInventory[i].getItemDamage() == 0) {
                  fireFlag = true;
                  break;
               }
            }
         }

         if (witherFlag && event.isCancelable() && event.source == DamageSource.wither) {
            event.setCanceled(true);
         }

         if (fireFlag && event.isCancelable() && event.source.isFireDamage()) {
            event.setCanceled(true);
         }
      }

   }

   @SubscribeEvent
   public void playerKillEntity(LivingDeathEvent event) {
      if (!event.entityLiving.worldObj.isRemote && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
         EntityLiving entityMob = (EntityLiving)event.entityLiving;
         EntityPlayer player = (EntityPlayer)event.source.getEntity();
         if (entityMob instanceof NetherMob || entityMob instanceof EntityPigZombie || entityMob instanceof EntityBlaze || entityMob instanceof EntityGhast || entityMob instanceof EntityMagmaCube) {
            player.triggerAchievement(NetherAchievements.demonSlayer);
         }

         if (entityMob instanceof EntityObsidianSheepman) {
            player.triggerAchievement(NetherAchievements.obsidiancow);
         }
      }

   }
}
