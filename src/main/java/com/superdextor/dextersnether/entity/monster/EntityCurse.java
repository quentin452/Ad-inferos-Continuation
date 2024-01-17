package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCurse extends EntityMob implements NetherMob {
   public EntityCurse(World worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.95F);
      this.isImmuneToFire = true;
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, false));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      this.experienceValue = 8;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.worldObj.isRemote) {
         int i = MathHelper.floor_double(this.posX);
         int j = MathHelper.floor_double(this.posY);
         int k = MathHelper.floor_double(this.posZ);

         for(int l = 0; l < 4; ++l) {
            i = MathHelper.floor_double(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
            j = MathHelper.floor_double(this.posY);
            k = MathHelper.floor_double(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
            if (this.worldObj.getBlock(i, j, k).getMaterial() == Material.air && NetherBlocks.curse_block.canPlaceBlockAt(this.worldObj, i, j - 1, k)) {
               this.worldObj.setBlock(i, j, k, NetherBlocks.curse_block);
            }
         }
      }

   }

   protected String getLivingSound() {
      return "dextersnether:mob.curse.idle";
   }

   protected String getHurtSound() {
      return "dextersnether:mob.curse.damage";
   }

   protected String getDeathSound() {
      return "mob.blaze.death";
   }

   protected float getSoundPitch() {
      return super.getSoundPitch() * 0.7F;
   }

   public boolean getCanSpawnHere() {
      return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
   }

   protected Item getDropItem() {
      return NetherItems.wither_dust;
   }

   public boolean isAIEnabled() {
      return true;
   }

   public boolean attackEntityAsMob(Entity target) {
      if (super.attackEntityAsMob(target)) {
         if (target instanceof EntityLivingBase) {
            byte b0 = 2;
            if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL) {
               b0 = 6;
            } else if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
               b0 = 10;
            }

            if (b0 > 0) {
               ((EntityLivingBase)target).addPotionEffect(new PotionEffect(Potion.confusion.id, b0 * 38, 0));
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public boolean DarkfireRes() {
      return false;
   }

   public boolean CurseRes() {
      return false;
   }

   public boolean WitherRes() {
      return false;
   }

   public boolean AcidRes() {
      return false;
   }

   public boolean SpikeRes() {
      return false;
   }
}
