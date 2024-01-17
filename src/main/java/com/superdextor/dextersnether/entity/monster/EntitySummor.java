package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.thinkbigcore.entity.ai.EntityAIStair;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntitySummor extends EntityLiving implements NetherMob {
   private int cooldown = 500;
   private int summorMethod = 0;

   public EntitySummor(World worldIn) {
      super(worldIn);
      this.tasks.addTask(1, new EntityAIStair(this, EntityPlayer.class, 8.0F));
      this.isImmuneToFire = true;
      this.setSize(0.6F, 1.0F);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(14, (byte)0);
   }

   protected String getHurtSound() {
      return "dig.stone";
   }

   protected String getDeathSound() {
      return "dig.stone";
   }

   protected String getLivingSound() {
      return "ambient.weather.thunder";
   }

   public void readEntityFromNBT(NBTTagCompound tagCompund) {
      super.readEntityFromNBT(tagCompund);
      byte b0;
      if (tagCompund.hasKey("Cooldown", 99)) {
         b0 = tagCompund.getByte("Cooldown");
         this.cooldown = b0;
      }

      if (tagCompund.hasKey("SummorMethod", 99)) {
         b0 = tagCompund.getByte("SummorMethod");
         this.summorMethod = b0;
      }

   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setByte("Cooldown", (byte)this.getCooldown());
      tagCompound.setByte("SummorMethod", (byte)this.getSummorMethod());
   }

   public int getCooldown() {
      return this.cooldown;
   }

   public int getSummorMethod() {
      return this.summorMethod;
   }

   public int getStage() {
      return this.dataWatcher.getWatchableObjectByte(14);
   }

   protected void setStage(int stage) {
      this.dataWatcher.updateObject(14, (byte)stage);
   }

   public void ResetCooldown() {
      this.cooldown = 450;
   }

   public void onLivingUpdate() {
      if (!this.worldObj.isRemote) {
         int k;
         if (this.cooldown > 0) {
            if (this.cooldown == 70) {
               this.addPotionEffect(new PotionEffect(Potion.resistance.id, 80, 1));
               k = this.rand.nextInt(3);
               if (k == 0) {
                  this.playSound("mob.ghast.scream", 2.0F, 0.5F);
               } else if (k == 1) {
                  this.playSound("mob.endermen.scream", 2.0F, 0.5F);
               } else {
                  this.playSound("mob.wither.idle", 2.0F, 0.5F);
               }

               this.setStage(this.summorMethod + 1);
            }
         } else {
            int k = false;
            int i = false;
            this.playSound("dextersnether:summon", 2.0F, 1.3F);
            this.worldObj.newExplosion((Entity)null, this.posX, this.posY + 1.0D, this.posZ, 0.5F, false, false);
            this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 80, 3));
            int i;
            if (this.summorMethod == 0) {
               i = this.rand.nextInt(3) + 3;

               for(k = 0; k < i; ++k) {
                  this.spawnMob(this.createEntity(EntitySkeleton.class), 3);
               }
            } else if (this.summorMethod == 1) {
               i = this.rand.nextInt(3) + 3;

               for(k = 0; k < i; ++k) {
                  this.spawnMob(this.createEntity(EntityNetherSpider.class), 4);
               }
            } else if (this.summorMethod == 2) {
               i = this.rand.nextInt(3) + 2;

               for(k = 0; k < i; ++k) {
                  EntityGlowstoneSkeleton entitySkeleton = (EntityGlowstoneSkeleton)this.createEntity(EntityGlowstoneSkeleton.class);
                  entitySkeleton.setSkeletonType(0);
                  this.spawnMob(entitySkeleton, 4);
               }

               i = this.rand.nextInt(2) + 2;

               for(k = 0; k < i; ++k) {
                  this.spawnMob(this.createEntity(EntityMagmaCube.class), 4);
               }
            } else if (this.summorMethod == 3) {
               i = this.rand.nextInt(4) + 3;

               for(k = 0; k < i; ++k) {
                  this.spawnMob(this.createEntity(EntityPhantom.class), 5);
               }
            } else if (this.summorMethod == 4) {
               i = this.rand.nextInt(6) + 4;

               for(k = 0; k < i; ++k) {
                  this.spawnMob(this.createEntity(EntityBlaze.class), 5);
               }

               if (this.rand.nextBoolean()) {
                  this.spawnMob(this.createEntity(EntitySkeletonHorse.class), 5);
               }
            } else if (this.summorMethod == 5) {
               i = this.rand.nextInt(2) + 2;

               for(k = 0; k < i; ++k) {
                  this.spawnMob(this.createEntity(EntityReaper.class), 3);
               }

               i = this.rand.nextInt(2) + 2;

               for(k = 0; k < i; ++k) {
                  this.spawnMob(this.createEntity(EntitySkeletonHorse.class), 5);
               }

               i = this.rand.nextInt(2) + 2;

               for(k = 0; k < i; ++k) {
                  this.spawnMob(this.createEntity(EntityPhantom.class), 5);
               }

               if (this.rand.nextBoolean()) {
                  this.spawnMob(this.createEntity(EntityCurse.class), 7);
               }
            } else {
               this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, 6.0F, false, true);
               EntityHerobrine herobrine = (EntityHerobrine)this.createEntity(EntityHerobrine.class);
               EntityReaper reaper = (EntityReaper)this.createEntity(EntityReaper.class);
               herobrine.addPotionEffect(new PotionEffect(Potion.regeneration.id, 40, 3));
               herobrine.addPotionEffect(new PotionEffect(Potion.resistance.id, 40, 3));
               reaper.addPotionEffect(new PotionEffect(Potion.regeneration.id, 40, 3));
               reaper.addPotionEffect(new PotionEffect(Potion.resistance.id, 40, 3));
               this.spawnMob(herobrine, 0);
               this.spawnMob(reaper, 2);
               this.setDead();
            }

            ++this.summorMethod;
            this.ResetCooldown();
            this.setStage(0);
         }

         if (this.cooldown > 0) {
            --this.cooldown;
         }
      }

      super.onLivingUpdate();
   }

   private EntityLiving createEntity(Class c) {
      EntityLiving entity = (EntityLiving)EntityList.createEntityByName((String)EntityList.classToStringMapping.get(c), this.worldObj);
      entity.onSpawnWithEgg((IEntityLivingData)null);
      return entity;
   }

   private void spawnMob(EntityLiving entity, int radius) {
      entity.setLocationAndAngles(this.posX - (double)(2 + radius) + (double)this.rand.nextInt(5 + radius), this.posY, this.posZ - (double)(2 + radius) + (double)this.rand.nextInt(5 + radius), 0.0F, 0.0F);

      for(int x = 0; x < 10 && entity.isEntityInsideOpaqueBlock(); ++x) {
         ++entity.posY;
      }

      this.worldObj.spawnEntityInWorld(entity);
   }

   public boolean canBePushed() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public int getBrightnessForRender(float p_70070_1_) {
      return this.rand.nextFloat() < 0.1F ? 0 : 15728880;
   }

   protected boolean canDespawn() {
      return false;
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
