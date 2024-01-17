package com.superdextor.dextersnether.items;

import com.superdextor.thinkbigcore.items.ItemCustomSword;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;

public class ItemQuartzSword extends ItemCustomSword {
   public ItemQuartzSword(ToolMaterial material) {
      super(material);
   }

   public boolean hitEntity(ItemStack stack, EntityLivingBase hitEntity, EntityLivingBase attackingEntity) {
      hitEntity.motionY = 0.7D;
      stack.damageItem(1, attackingEntity);
      return true;
   }

   public boolean onMeleeAttack(EntityLiving entity, EntityLivingBase target, ItemStack stack) {
      target.motionY = 0.7D;
      return true;
   }
}
