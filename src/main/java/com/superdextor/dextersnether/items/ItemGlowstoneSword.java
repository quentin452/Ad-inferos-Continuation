package com.superdextor.dextersnether.items;

import com.superdextor.dextersnether.entity.projectile.EntityFlame;
import com.superdextor.thinkbigcore.items.ItemCustomSword;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemGlowstoneSword extends ItemCustomSword {
   public ItemGlowstoneSword(ToolMaterial material) {
      super(material);
   }

   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
      return stack;
   }

   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
      if (count % 4 == 0) {
         World world = player.worldObj;
         if (!world.isRemote) {
            EntityFlame glowstone = new EntityFlame(world, player);
            world.spawnEntityInWorld(glowstone);
         }

         player.playSound("mob.ghast.fireball", 0.6F, 1.0F);
         if (!player.capabilities.isCreativeMode) {
            stack.damageItem(1, player);
         }
      }

   }

   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.bow;
   }

   public boolean onRangedAttack(EntityLiving entity, EntityLivingBase target, ItemStack stack, boolean playSFX) {
      World world = entity.worldObj;
      EntityFlame entityflameball = new EntityFlame(world, entity);
      double d0 = target.posY + (double)target.getEyeHeight() - 2.100000023841858D;
      double d1 = target.posX - entity.posX;
      double d2 = d0 - entityflameball.posY;
      double d3 = target.posZ - entity.posZ;
      entityflameball.damage = 2;
      float f1 = MathHelper.sqrt_double(d1 * d1 + d3 * d3) * 0.2F;
      entityflameball.setThrowableHeading(d1, d2 + (double)f1, d3, 1.6F, 6.0F);
      if (playSFX) {
         entity.playSound("mob.ghast.fireball", 1.0F, 1.0F / (entity.getRNG().nextFloat() * 0.4F + 0.8F));
      }

      world.spawnEntityInWorld(entityflameball);
      return true;
   }
}
