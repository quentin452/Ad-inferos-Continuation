package com.superdextor.dextersnether.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentCurse extends Enchantment {
   public EnchantmentCurse(int id, int rar) {
      super(id, rar, EnumEnchantmentType.armor);
      this.setName("curse_protection");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return enchantmentLevel * 10;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 15;
   }

   public int getMaxLevel() {
      return 4;
   }

   public boolean canApplyTogether(Enchantment ench) {
      return ench instanceof EnchantmentProtection ? false : super.canApplyTogether(ench);
   }
}
