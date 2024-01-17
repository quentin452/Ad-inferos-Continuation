package com.superdextor.dextersnether.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemWitherApple extends ItemFood {
   public ItemWitherApple() {
      super(5, 0.4F, false);
      this.setAlwaysEdible();
      this.setMaxStackSize(8);
   }

   public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
      super.onEaten(stack, world, player);
      boolean flag = false;

      for(int k = 0; k < 4; ++k) {
         if (player.getEquipmentInSlot(k + 1) != null && player.getEquipmentInSlot(k + 1).getItem() instanceof ItemWitherArmor) {
            flag = true;
         }
      }

      if (flag) {
         player.heal(5.0F);
      } else {
         player.addPotionEffect(new PotionEffect(Potion.wither.id, 640, 0));
         player.attackEntityFrom(DamageSource.wither, 3.0F);
      }

      return stack;
   }
}
