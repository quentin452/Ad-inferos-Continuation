package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.projectile.EntityFlameball;
import com.superdextor.dextersnether.init.NetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityInfernalBlaze extends EntityBlaze {
   private int field_70846_g;

   public EntityInfernalBlaze(World worldIn) {
      super(worldIn);
      this.setSize(0.78F, 2.54F);
      this.experienceValue = 14;
      this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0D);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
   }

   protected float getSoundPitch() {
      return super.getSoundPitch() * 0.7F;
   }

   protected Item getDropItem() {
      return NetherItems.infernal_rod;
   }

   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
      if (p_70628_1_) {
         int j = this.rand.nextInt(3 + p_70628_2_) + 2;

         for(int k = 0; k < j; ++k) {
            this.dropItem(NetherItems.infernal_rod, 1);
         }
      }

   }

   protected void attackEntity(Entity target, float p_70785_2_) {
      if (this.attackTime <= 0 && p_70785_2_ < 2.0F && target.boundingBox.maxY > this.boundingBox.minY && target.boundingBox.minY < this.boundingBox.maxY) {
         this.attackTime = 20;
         this.attackEntityAsMob(target);
      } else if (p_70785_2_ < 30.0F) {
         double d0 = target.posX - this.posX;
         double d1 = target.boundingBox.minY + (double)(target.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
         double d2 = target.posZ - this.posZ;
         if (this.attackTime == 0) {
            ++this.field_70846_g;
            if (this.field_70846_g == 1) {
               this.attackTime = 60;
               this.func_70844_e(true);
            } else if (this.field_70846_g <= 4) {
               this.attackTime = 6;
            } else {
               this.attackTime = 100;
               this.field_70846_g = 0;
               this.func_70844_e(false);
            }

            if (this.field_70846_g > 1) {
               float f1 = MathHelper.sqrt_float(p_70785_2_) * 0.5F;
               this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);

               for(int i = 0; i < 1; ++i) {
                  EntityFlameball entityflameball = new EntityFlameball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
                  entityflameball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
                  this.worldObj.spawnEntityInWorld(entityflameball);
               }
            }
         }

         this.rotationYaw = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
         this.hasAttacked = true;
      }

   }
}
