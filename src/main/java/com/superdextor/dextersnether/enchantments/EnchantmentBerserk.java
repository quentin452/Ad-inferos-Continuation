package com.superdextor.dextersnether.enchantments;

import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnchantmentBerserk extends Enchantment {
   public EnchantmentBerserk(int id, int rarity) {
      super(id, rarity, EnumEnchantmentType.armor);
      this.setName("berserk");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 10 + 20 * (enchantmentLevel - 1);
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return super.getMinEnchantability(enchantmentLevel) + 50;
   }

   public int getMaxLevel() {
      return 3;
   }

   public void func_151367_b(EntityLivingBase entityliving, Entity entity, int lvl) {
      Random random = entityliving.getRNG();
      if (this.func_92094_a(lvl, random)) {
         if (lvl > 1) {
            entityliving.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 120 + lvl * 30, 0));
         }

         if (lvl < 2) {
            entityliving.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 120 + lvl * 30, 0));
         } else {
            entityliving.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 120 + lvl * 30, 1));
         }

         entity.playSound("mob.zombiepig.zpigangry", 2.0F, 0.7F);
      }

   }

   public boolean func_92094_a(int lvl, Random random) {
      return lvl <= 0 ? false : random.nextFloat() < 0.12F * (float)lvl;
   }

   public boolean canApplyTogether(Enchantment ench) {
      return ench instanceof EnchantmentThorns ? false : super.canApplyTogether(ench);
   }
}
