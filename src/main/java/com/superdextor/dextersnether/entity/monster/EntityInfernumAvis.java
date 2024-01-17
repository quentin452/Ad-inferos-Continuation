package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.NetherAchievements;
import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.entity.projectile.EntityFlame;
import com.superdextor.dextersnether.entity.projectile.EntityFlameball;
import com.superdextor.dextersnether.entity.projectile.EntityWitherSkullCustom;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.thinkbigcore.entity.ai.EntityAIBreakblocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityInfernumAvis extends EntityMob implements IBossDisplayData, NetherMob, IRangedAttackMob {
   private EntityAIAttackOnCollide aiAttack = new EntityAIAttackOnCollide(this, 1.3D, false);
   private EntityAISwimming AiSwim = new EntityAISwimming(this);
   private EntityAIWander AiWander = new EntityAIWander(this, 1.0D);
   private EntityAILookIdle AiIdle = new EntityAILookIdle(this);
   private int specialtime = 0;
   private int awaketime = 0;

   public EntityInfernumAvis(World worldIn) {
      super(worldIn);
      this.isImmuneToFire = true;
      this.tasks.addTask(0, new EntityAIBreakblocks(this, 60.0F, true, false, false));
      this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      this.experienceValue = 80;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(13, new Byte((byte)1));
      this.dataWatcher.addObject(14, new Byte((byte)0));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0D);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.worldObj.isRemote) {
         if (this.IsSleeping()) {
            if (this.ticksExisted % 40 == 0) {
               this.heal(1.0F);
            }

            this.motionX = 0.0D;
            this.motionY = 0.0D;
            if (this.isWet() || this.isBurning()) {
               this.setSleeping(false);
            }
         } else {
            if (this.getAttackTarget() == null) {
               if (this.awaketime < 1600) {
                  ++this.awaketime;
               }
            } else if (this.awaketime > 1) {
               --this.awaketime;
            }

            if (this.awaketime > 1500) {
               this.setSleeping(true);
            }

            if (this.getAttackTarget() != null) {
               if (this.specialtime <= 0) {
                  if (this.rand.nextBoolean()) {
                     this.Shoot();
                     this.playSound("mob.ghast.fireball", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                  }
               } else {
                  this.SetWignAnimation(1);
                  --this.specialtime;
                  Entity entity = this.getAttackTarget();
                  if (entity != null) {
                     if (this.posY < entity.posY || this.posY < entity.posY + 5.0D) {
                        if (this.motionY < 0.0D) {
                           this.motionY = 0.0D;
                        }

                        this.motionY += (0.5D - this.motionY) * 0.6000000238418579D;
                     }

                     double d0 = entity.posX - this.posX;
                     double d1 = entity.posZ - this.posZ;
                     double d3 = d0 * d0 + d1 * d1;
                     if (d3 > 9.0D) {
                        double d5 = (double)MathHelper.sqrt_double(d3);
                        this.motionX += (d0 / d5 * 0.5D - this.motionX) * 0.6000000238418579D;
                        this.motionZ += (d1 / d5 * 0.5D - this.motionZ) * 0.6000000238418579D;
                     }
                  }

                  if (this.specialtime <= 0) {
                     this.specialtime = -500;
                  }

                  if (this.rand.nextBoolean()) {
                     this.Shoot();
                     this.playSound("mob.wither.shoot", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                  }
               }
            }
         }

         if (!this.onGround && !this.IsSleeping()) {
            if (this.motionY < 0.0D) {
               this.SetWignAnimation(2);
            } else {
               this.SetWignAnimation(1);
            }
         } else {
            this.SetWignAnimation(0);
         }

         if (this.specialtime <= 0 && this.rand.nextFloat() < 0.4F) {
            ++this.specialtime;
            if (this.specialtime > 0) {
               this.specialtime = 100 + this.rand.nextInt(100);
            }
         }
      }

      float width = this.getHealth() / 160.0F + 0.6F;
      float height = this.getHealth() / 100.0F + 2.5F;
      this.setSize(width, height);

      for(int i = 0; i < 5; ++i) {
         this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
      }

   }

   public int getTotalArmorValue() {
      return super.getTotalArmorValue() + 4;
   }

   public float getEyeHeight() {
      return this.getHealth() / 100.0F + 2.1F;
   }

   protected String getLivingSound() {
      return this.IsSleeping() ? "dextersnether:mob.infernumavis.sleep" : "dextersnether:mob.infernumavis.angry";
   }

   protected String getHurtSound() {
      return "dextersnether:mob.infernumavis.damage";
   }

   protected String getDeathSound() {
      return "dextersnether:mob.infernumavis.death";
   }

   protected float getSoundVolume() {
      return 1.4F;
   }

   protected void fall(float p_70069_1_) {
   }

   public boolean attackEntityFrom(DamageSource source, float damage) {
      if (this.IsSleeping()) {
         this.setSleeping(false);
         if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer) {
            ((EntityPlayer)source.getEntity()).triggerAchievement(NetherAchievements.wakeAvis);
         }
      }

      return super.attackEntityFrom(source, damage);
   }

   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
      int j = this.rand.nextInt(7 + p_70628_2_);

      for(int k = 0; k < j; ++k) {
         this.dropItem(NetherItems.wither_dust, 3);
      }

      this.dropItem(NetherItems.avis_wing, 2);
   }

   protected void Shoot() {
      if (!this.isDead) {
         double d0 = this.getDistanceSq(this.getAttackTarget().posX, this.getAttackTarget().boundingBox.minY, this.getAttackTarget().posZ);
         float f = MathHelper.sqrt_double(d0) / 15.0F;
         float f1 = f;
         if (f < 0.1F) {
            f1 = 0.1F;
         }

         if (f1 > 1.0F) {
            f1 = 1.0F;
         }

         this.attackEntityWithRangedAttack(this.getAttackTarget(), f1);
      }

   }

   protected boolean canDespawn() {
      return false;
   }

   public void attackEntityWithRangedAttack(EntityLivingBase target, float var) {
      int i = this.rand.nextInt(2);
      Entity projectile = null;
      if (this.specialtime <= 0) {
         i = 2;
      }

      switch(i) {
      case 0:
         projectile = new EntityFlameball(this.worldObj, this, target);
         ((EntityFlameball)projectile).field_92057_e = 1;
         ((EntityFlameball)projectile).damage = 5.0F;
         break;
      case 1:
         projectile = new EntityWitherSkullCustom(this.worldObj, this, target);
         ((EntityWitherSkullCustom)projectile).explosionPower = 1;
         ((EntityWitherSkullCustom)projectile).damage = 5.0F;
         break;
      case 2:
         projectile = new EntityFlame(this.worldObj, this);
         double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
         double d1 = target.posX - this.posX;
         double d2 = d0 - ((Entity)projectile).posY;
         double d3 = target.posZ - this.posZ;
         float f1 = MathHelper.sqrt_double(d1 * d1 + d3 * d3) * 0.2F;
         ((EntityThrowable)projectile).setThrowableHeading(d1, d2 + (double)f1, d3, 1.6F, 12.0F);
      }

      this.worldObj.spawnEntityInWorld((Entity)projectile);
   }

   public boolean IsSleeping() {
      return this.dataWatcher.getWatchableObjectByte(13) == 1;
   }

   public void setSleeping(boolean sleeping) {
      if (this.IsSleeping() != sleeping) {
         if (sleeping) {
            this.tasks.removeTask(this.aiAttack);
            this.tasks.removeTask(this.AiSwim);
            this.tasks.removeTask(this.AiWander);
            this.tasks.removeTask(this.AiIdle);
            this.awaketime = 0;
         } else {
            this.tasks.addTask(0, this.AiSwim);
            this.tasks.addTask(1, this.aiAttack);
            this.tasks.addTask(2, this.AiWander);
            this.tasks.addTask(3, this.AiIdle);
            this.playSound("dextersnether:mob.infernumavis.awaken", 1.4F, 0.8F);
            this.awaketime = 0;
         }
      }

      this.dataWatcher.updateObject(13, (byte)(sleeping ? 1 : 0));
   }

   public int GetWignAnimation() {
      return this.dataWatcher.getWatchableObjectByte(14);
   }

   public void SetWignAnimation(int animation) {
      if (this.isWet() || this.isBurning()) {
         animation = 0;
      }

      if (!this.worldObj.isRemote && this.GetWignAnimation() != animation) {
         this.dataWatcher.updateObject(14, (byte)animation);
      }

   }

   public boolean isAIEnabled() {
      return true;
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
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
      return false;
   }

   public boolean SpikeRes() {
      return true;
   }
}
