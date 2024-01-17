package com.superdextor.dextersnether.items;

import com.superdextor.dextersnether.entity.projectile.EntityFlameball;
import com.superdextor.dextersnether.entity.projectile.EntityWitherSkullCustom;
import com.superdextor.thinkbigcore.items.IEntityUsable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemNetherStaff extends ItemBow implements IEntityUsable {
   private final int type;

   public ItemNetherStaff(int type) {
      this.maxStackSize = 1;
      this.setMaxDamage(300);
      this.setFull3D();
      this.type = type;
   }

   public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
   }

   public ItemStack onEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      if (stack.getTagCompound() == null) {
         stack.setTagCompound(new NBTTagCompound());
      }

      stack.getTagCompound().setBoolean("charged", true);
      stack.getTagCompound().setInteger("cooldown", 14);
      return stack;
   }

   public int getMaxItemUseDuration(ItemStack stack) {
      return 30;
   }

   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.block;
   }

   public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      boolean flag = true;
      if (stack.getTagCompound() != null && stack.getTagCompound().getInteger("cooldown") > 0) {
         flag = false;
      }

      if (flag) {
         if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("charged")) {
            this.shoot(worldIn, playerIn, stack);
         } else {
            playerIn.setItemInUse(stack, this.getMaxItemUseDuration(stack));
         }
      }

      return stack;
   }

   public int getItemEnchantability() {
      return 1;
   }

   public boolean onRangedAttack(EntityLiving entity, EntityLivingBase target, ItemStack stack, boolean playSFX) {
      World world = entity.worldObj;
      Object fireball;
      if (this.type == 0) {
         fireball = new EntityFlameball(world, entity, target);
      } else {
         fireball = new EntityWitherSkullCustom(world, entity, target);
      }

      if (playSFX) {
         if (this.type == 0) {
            entity.playSound("mob.ghast.fireball", 1.0F, 1.0F / (entity.getRNG().nextFloat() * 0.4F + 0.8F));
         } else {
            entity.playSound("mob.wither.shoot", 1.0F, 1.0F / (entity.getRNG().nextFloat() * 0.4F + 0.8F));
         }
      }

      world.spawnEntityInWorld((Entity)fireball);
      return true;
   }

   public boolean onMeleeAttack(EntityLiving entity, EntityLivingBase target, ItemStack stack) {
      return false;
   }

   protected void shoot(World worldIn, EntityPlayer playerIn, ItemStack stack) {
      if (stack.getTagCompound() == null) {
         stack.setTagCompound(new NBTTagCompound());
      }

      stack.getTagCompound().setBoolean("charged", false);
      Object fireball;
      if (this.type == 0) {
         fireball = new EntityFlameball(worldIn, playerIn, 0.6D);
      } else {
         fireball = new EntityWitherSkullCustom(worldIn, playerIn, 0.6D);
      }

      int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
      if (k > 0) {
         if (fireball instanceof EntityFlameball) {
            ((EntityFlameball)fireball).damage = (float)((int)((double)k * 1.0D + 10.0D));
         } else if (fireball instanceof EntityWitherSkullCustom) {
            ((EntityWitherSkullCustom)fireball).damage = (float)((int)((double)k * 1.0D + 10.0D));
         }
      }

      int d = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
      if (d > 0) {
         if (fireball instanceof EntityFlameball) {
            ((EntityFlameball)fireball).field_92057_e = (int)((double)d * 1.0D + 1.0D);
         } else if (fireball instanceof EntityWitherSkullCustom) {
            ((EntityWitherSkullCustom)fireball).explosionPower = (int)((double)d * 1.0D + 1.0D);
         }
      }

      if (fireball instanceof EntityFlameball) {
         ++((EntityFlameball)fireball).field_92057_e;
      } else if (fireball instanceof EntityWitherSkullCustom) {
         ++((EntityWitherSkullCustom)fireball).explosionPower;
      }

      stack.damageItem(1, playerIn);
      if (this.type == 0) {
         playerIn.playSound("mob.ghast.fireball", 1.0F, 1.0F / (playerIn.getRNG().nextFloat() * 0.4F + 0.8F));
      } else {
         playerIn.playSound("mob.wither.shoot", 1.0F, 1.0F / (playerIn.getRNG().nextFloat() * 0.4F + 0.8F));
      }

      if (!worldIn.isRemote) {
         worldIn.spawnEntityInWorld((Entity)fireball);
      }

   }

   public boolean hasEffect(ItemStack stack) {
      return stack.getTagCompound() != null && stack.getTagCompound().getBoolean("charged") ? true : super.hasEffect(stack);
   }

   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      if (isSelected && stack.getTagCompound() != null && stack.getTagCompound().getInteger("cooldown") > 0) {
         stack.getTagCompound().setInteger("cooldown", stack.getTagCompound().getInteger("cooldown") - 1);
      }

   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister p_94581_1_) {
      this.itemIcon = p_94581_1_.registerIcon(this.getIconString());
   }
}
