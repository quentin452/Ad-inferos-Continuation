package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.world.WorldProviderAbyss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySkeletonHorse extends EntityMob implements NetherMob {
   public float mad = 0.0F;
   int cooldown = 75;

   public EntitySkeletonHorse(World worldIn) {
      super(worldIn);
      this.setSize(1.4F, 1.6F);
      this.isImmuneToFire = true;
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, false));
      this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      this.experienceValue = 7;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(13, new Byte((byte)0));
   }

   public float getEyeHeight() {
      return 1.9F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.34D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
   }

   protected String getLivingSound() {
      return "mob.horse.skeleton.idle";
   }

   protected String getHurtSound() {
      return "mob.horse.skeleton.hit";
   }

   protected String getDeathSound() {
      return "mob.horse.skeleton.death";
   }

   public boolean attackEntityAsMob(Entity p_70652_1_) {
      if (super.attackEntityAsMob(p_70652_1_)) {
         if (this.getSkeletonType() == 1 && p_70652_1_ instanceof EntityLivingBase) {
            ((EntityLivingBase)p_70652_1_).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
         }

         return true;
      } else {
         return false;
      }
   }

   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
      int j = this.rand.nextInt(2 + p_70628_2_);

      int k;
      for(k = 0; k < j; ++k) {
         this.dropItem(Items.bone, 1);
      }

      j = this.rand.nextInt(2 + p_70628_2_);

      for(k = 0; k < j; ++k) {
         this.dropItem(Items.bone, 2);
      }

   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_) {
      Object p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);
      if (this.worldObj.provider instanceof WorldProviderAbyss && this.getRNG().nextInt(5) > 0) {
         this.setSkeletonType(1);
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
         this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
      }

      if (this.worldObj.rand.nextInt(7) == 0) {
         EntitySkeleton rider = new EntitySkeleton(this.worldObj);
         rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         rider.onSpawnWithEgg((IEntityLivingData)null);
         this.worldObj.spawnEntityInWorld(rider);
         rider.mountEntity(this);
      } else if (this.worldObj.rand.nextInt(10) == 0) {
         EntityGlowstoneSkeleton rider = new EntityGlowstoneSkeleton(this.worldObj);
         rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         rider.onSpawnWithEgg((IEntityLivingData)null);
         this.worldObj.spawnEntityInWorld(rider);
         rider.mountEntity(this);
      } else if (this.worldObj.rand.nextInt(30) == 0) {
         EntityReaper rider = new EntityReaper(this.worldObj);
         rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         rider.onSpawnWithEgg((IEntityLivingData)null);
         this.worldObj.spawnEntityInWorld(rider);
         rider.mountEntity(this);
      } else if (this.worldObj.rand.nextInt(10) == 0) {
         EntityObsidianSheepman rider = new EntityObsidianSheepman(this.worldObj);
         rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         rider.onSpawnWithEgg((IEntityLivingData)null);
         rider.angerLevel = 400;
         this.worldObj.spawnEntityInWorld(rider);
         rider.mountEntity(this);
      }

      return (IEntityLivingData)p_110161_1_1;
   }

   public void onLivingUpdate() {
      if (this.mad >= 0.0F) {
         if (this.cooldown <= 0) {
            this.mad = 0.0F;
            this.cooldown = 22 + this.rand.nextInt(14);
         }

         --this.cooldown;
      }

      super.onLivingUpdate();
   }

   public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
      this.mad = 1.0F;
      if (this.isEntityInvulnerable()) {
         return false;
      } else if (super.attackEntityFrom(p_70097_1_, p_70097_2_)) {
         Entity entity = p_70097_1_.getEntity();
         if (this.riddenByEntity != entity && this.ridingEntity != entity) {
            if (entity != this) {
               this.entityToAttack = entity;
            }

            return true;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   public int getSkeletonType() {
      return this.dataWatcher.getWatchableObjectByte(13);
   }

   public void setSkeletonType(int p_82201_1_) {
      this.dataWatcher.updateObject(13, (byte)p_82201_1_);
   }

   public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
      super.readEntityFromNBT(p_70037_1_);
      if (p_70037_1_.hasKey("SkeletonType", 99)) {
         byte b0 = p_70037_1_.getByte("SkeletonType");
         this.setSkeletonType(b0);
      }

   }

   public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
      super.writeEntityToNBT(p_70014_1_);
      p_70014_1_.setByte("SkeletonType", (byte)this.getSkeletonType());
   }

   public boolean isAIEnabled() {
      return true;
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public boolean DarkfireRes() {
      return this.getSkeletonType() == 1;
   }

   public boolean CurseRes() {
      return this.getSkeletonType() == 1;
   }

   public boolean WitherRes() {
      return this.getSkeletonType() == 1;
   }

   public boolean AcidRes() {
      return false;
   }

   public boolean SpikeRes() {
      return false;
   }
}
