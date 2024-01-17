package com.superdextor.dextersnether.items;

import com.superdextor.dextersnether.entity.projectile.EntityNetherArrow;
import com.superdextor.thinkbigcore.items.ItemCustomBow;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class ItemNetherBow extends ItemCustomBow {
   private final int type;

   public ItemNetherBow(String modelName, int durability, Item ammo, int drawbackSpeed, double damage, float speed, int zoomDelay, float maxZoom, int type) {
      super(modelName, durability, ammo, drawbackSpeed, damage, speed, zoomDelay, maxZoom);
      this.type = type;
   }

   public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
      int j = this.getMaxItemUseDuration(stack) - timeLeft;
      ArrowLooseEvent event = new ArrowLooseEvent(playerIn, stack, j);
      if (!MinecraftForge.EVENT_BUS.post(event)) {
         j = event.charge;
         boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;
         if (flag || playerIn.inventory.hasItem(this.ammo)) {
            float f = (float)j / (20.0F - (float)this.drawbackSpeed);
            f = (f * f + f * 2.0F) / 3.0F;
            if ((double)f < 0.1D) {
               return;
            }

            if (f > 1.0F) {
               f = 1.0F;
            }

            EntityNetherArrow entityarrow = new EntityNetherArrow(worldIn, playerIn, f * this.speed * 2.0F);
            entityarrow.setArrowType(this.type);
            if (f == 1.0F) {
               entityarrow.setIsCritical(true);
            }

            stack.damageItem(1, playerIn);
            worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if (flag) {
               entityarrow.canBePickedUp = 2;
            } else {
               playerIn.inventory.consumeInventoryItem(this.ammo);
            }

            this.finalizeArrow(entityarrow, stack, worldIn);
         }

      }
   }

   public boolean onRangedAttack(EntityLiving entity, EntityLivingBase target, ItemStack stack, boolean playSFX) {
      World world = entity.worldObj;
      EntityNetherArrow entityarrow = new EntityNetherArrow(world, entity, target, 1.6F * this.speed, (float)(14 - world.difficultySetting.getDifficultyId() * 4));
      entityarrow.setArrowType(this.type);
      if (playSFX) {
         entity.playSound("random.bow", 1.0F, 1.0F / (entity.getRNG().nextFloat() * 0.4F + 0.8F));
      }

      this.finalizeArrow(entityarrow, stack, world);
      return true;
   }

   public boolean onMeleeAttack(EntityLiving entity, EntityLivingBase target, ItemStack stack) {
      return false;
   }
}
