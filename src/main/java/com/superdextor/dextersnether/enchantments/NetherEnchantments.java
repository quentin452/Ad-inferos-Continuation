package com.superdextor.dextersnether.enchantments;

import com.superdextor.dextersnether.NetherConfig;
import net.minecraft.enchantment.Enchantment;

public class NetherEnchantments {
   public static final Enchantment berserk;
   public static final Enchantment curseProtection;

   public static void register() {
      Enchantment.addToBookList(berserk);
      Enchantment.addToBookList(curseProtection);
   }

   static {
      berserk = new EnchantmentBerserk(NetherConfig.berserkEnchID, 1);
      curseProtection = new EnchantmentCurse(NetherConfig.curseEnchID, 4);
   }
}
