package com.superdextor.dextersnether.items;

import com.google.common.collect.Multimap;
import com.superdextor.dextersnether.entity.projectile.EntityTeleport;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemScythe extends Item {
   public ItemScythe() {
      this.maxStackSize = 1;
      this.setMaxDamage(765);
   }

   public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
      target.addPotionEffect(new PotionEffect(Potion.confusion.id, 120, 0));
      stack.damageItem(1, attacker);
      return true;
   }

   @SideOnly(Side.CLIENT)
   public boolean isFull3D() {
      return true;
   }

   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.block;
   }

   public int getMaxItemUseDuration(ItemStack stack) {
      return 60;
   }

   public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
      if (!worldIn.isRemote) {
         this.Teleport(stack, worldIn, playerIn, 60 - timeLeft);
      }

   }

   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
      playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
      return itemStackIn;
   }

   public ItemStack onEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      if (!worldIn.isRemote) {
         this.Teleport(stack, worldIn, playerIn, 60);
      }

      return stack;
   }

   public int getItemEnchantability() {
      return 1;
   }

   public Multimap getItemAttributeModifiers() {
      Multimap multimap = super.getItemAttributeModifiers();
      multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 12.0D, 0));
      return multimap;
   }

   protected void Teleport(ItemStack stack, World world, EntityPlayer player, int Distance) {
      if (Distance > 8) {
         stack.damageItem(8, player);
         world.playSoundAtEntity(player, "mob.endermen.portal", 0.9F, 1.0F);
         if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityTeleport(world, player, Distance));
         }
      }

   }
}
