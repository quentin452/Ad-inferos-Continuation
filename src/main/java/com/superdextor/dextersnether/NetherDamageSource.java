package com.superdextor.dextersnether;

import com.superdextor.dextersnether.entity.projectile.EntityNetherArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class NetherDamageSource {
   public static DamageSource acid = (new DamageSource("acid")).setDamageBypassesArmor();
   public static DamageSource curse = (new DamageSource("curse")).setDamageBypassesArmor();

   public static DamageSource causeArrowDamage(EntityNetherArrow attacker, Entity target) {
      return (new EntityDamageSourceIndirect("nether_arrow", attacker, target)).setProjectile();
   }
}
