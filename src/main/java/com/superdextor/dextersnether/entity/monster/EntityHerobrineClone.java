package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.thinkbigcore.entity.ai.EntityAITeleport;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHerobrineClone extends EntityMob implements NetherMob {
   private EntityHerobrine master;

   public EntityHerobrineClone(World worldIn) {
      super(worldIn);
      this.experienceValue = 0;
      this.isImmuneToFire = true;
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
      this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(5, new EntityAILookIdle(this));
      this.tasks.addTask(6, new EntityAITeleport(this, 1.0F));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
   }

   public void SetOwner(EntityHerobrine herobrine, EntityLivingBase target) {
      this.setAttackTarget(target);
      this.master = herobrine;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.43D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
   }

   public void onLivingUpdate() {
      if (this.master != null && !this.master.InCloak()) {
         this.attackEntityFrom(DamageSource.outOfWorld, 1.0F);
      }

      super.onLivingUpdate();
   }

   public boolean attackEntityFrom(DamageSource source, float damage) {
      if (this.worldObj.isRemote) {
         for(int i = 0; i < 20; ++i) {
            this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 1.0D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
         }
      }

      this.playSound("mob.endermen.portal", 1.0F, 1.0F);
      this.setDead();
      return false;
   }

   public boolean isAIEnabled() {
      return true;
   }

   public boolean DarkfireRes() {
      return true;
   }

   public boolean CurseRes() {
      return true;
   }

   public boolean WitherRes() {
      return true;
   }

   public boolean AcidRes() {
      return true;
   }

   public boolean SpikeRes() {
      return true;
   }
}
