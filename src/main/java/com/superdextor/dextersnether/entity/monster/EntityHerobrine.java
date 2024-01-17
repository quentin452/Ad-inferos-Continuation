package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.thinkbigcore.entity.ai.EntityAIBreakblocks;
import com.superdextor.thinkbigcore.entity.ai.EntityAITeleport;
import com.superdextor.thinkbigcore.items.IEntityUsable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHerobrine extends EntityMob implements IRangedAttackMob, NetherMob {
   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.25D, 7, 10.0F);
   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, 1.2D, false);
   private int cooldown = 0;
   private int attackUpdateTimer = 0;
   private int SpecialCooldown = 0;

   public EntityHerobrine(World world) {
      super(world);
      this.experienceValue = 100;
      this.isImmuneToFire = true;
      this.tasks.addTask(0, new EntityAITeleport(this, 2.0F));
      this.tasks.addTask(0, new EntityAIBreakblocks(this, 49.0F, true, false, true));
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
      this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
   }

   protected void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(13, new Byte((byte)0));
   }

   protected String getLivingSound() {
      return null;
   }

   protected String getHurtSound() {
      return "mob.enderdragon.hit";
   }

   protected String getDeathSound() {
      return "mob.horse.skeleton.death";
   }

   protected float getSoundPitch() {
      return super.getSoundPitch() * 0.7F;
   }

   protected Item getDropItem() {
      return NetherItems.netherite_ingot;
   }

   protected void dropFewItems(boolean playerKill, int looting) {
      this.dropItem(NetherItems.darkcore, 1);
      this.entityDropItem(new ItemStack(Items.skull, 1, 1), 0.0F);
      int j = this.rand.nextInt(3 + looting);

      int k;
      for(k = 0; k < j; ++k) {
         this.dropItem(NetherItems.dimensional_dust, 1);
      }

      j = this.rand.nextInt(13 + looting);

      for(k = 0; k < j; ++k) {
         this.dropItem(NetherItems.wither_dust, 1);
      }

      j = this.rand.nextInt(7 + looting);

      for(k = 0; k < j; ++k) {
         this.dropItem(NetherItems.netherite_nugget, 1);
      }

      j = this.rand.nextInt(5 + looting);

      for(k = 0; k < j; ++k) {
         this.dropItem(Items.diamond, 1);
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.46D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(80.0D);
   }

   public int getTotalArmorValue() {
      return super.getTotalArmorValue() + 6;
   }

   public void setCombatTask() {
      this.tasks.removeTask(this.aiAttackOnCollide);
      this.tasks.removeTask(this.aiArrowAttack);
      this.cooldown = 20;
      ItemStack newStack = new ItemStack(Items.stick);
      switch(this.rand.nextInt(5)) {
      case 0:
         newStack = new ItemStack(NetherItems.scythe, 1, 765);
         this.tasks.addTask(4, this.aiAttackOnCollide);
         break;
      case 1:
         newStack = new ItemStack(NetherItems.flame_staff, 1, 300);
         this.tasks.addTask(4, this.aiArrowAttack);
         break;
      case 2:
         newStack = new ItemStack(NetherItems.glowstone_sword, 1, 48);
         this.tasks.addTask(4, this.aiArrowAttack);
         break;
      case 3:
         newStack = new ItemStack(NetherItems.wither_staff, 1, 300);
         this.tasks.addTask(4, this.aiArrowAttack);
         break;
      case 4:
         newStack = new ItemStack(NetherItems.wither_bow, 1, 3000);
         this.tasks.addTask(4, this.aiArrowAttack);
      }

      this.setCurrentItemOrArmor(0, newStack);
      this.playSound("mob.horse.armor", 1.3F, 0.8F);
   }

   public void onLivingUpdate() {
      if (!this.worldObj.isRemote) {
         if (this.getAttackTarget() != null) {
            if (this.attackUpdateTimer <= 0) {
               this.setCombatTask();
               this.attackUpdateTimer = 100 + this.rand.nextInt(70);
            } else {
               --this.attackUpdateTimer;
            }

            if (this.cooldown > 0) {
               --this.cooldown;
            }

            if (this.cooldown == 14 && this.getHeldItem() != null && this.getHeldItem().getItem() == NetherItems.wither_staff) {
               this.playSound("mob.wither.idle", 1.3F, 0.9F);
            }

            if (this.cooldown == 12 && this.getHeldItem() != null && this.getHeldItem().getItem() == NetherItems.flame_staff) {
               this.playSound("mob.ghast.scream", 1.3F, 0.9F);
            }

            if (this.SpecialCooldown < 1) {
               this.SpecialAttack(this.rand.nextInt(3));
            } else {
               --this.SpecialCooldown;
            }
         } else if (this.getHeldItem() != null) {
            this.setCurrentItemOrArmor(0, (ItemStack)null);
            this.SetCloak(false);
            this.attackUpdateTimer = 0;
            this.SpecialCooldown = 400 + this.rand.nextInt(200);
         }
      }

      super.onLivingUpdate();
   }

   protected void SpecialAttack(int attackID) {
      switch(attackID) {
      case 0:
         int k = this.rand.nextInt(5) + 7;

         for(int x = 0; x < k; ++x) {
            EntityHerobrineClone entityclone = (EntityHerobrineClone)this.createEntity(EntityHerobrineClone.class);
            entityclone.SetOwner(this, this.getAttackTarget());
            entityclone.setCurrentItemOrArmor(0, this.getHeldItem());
            this.spawnMob(entityclone, 5);
         }

         this.playSound("random.fizz", 1.6F, 0.8F);
         this.playSound("mob.endermen.portal", 1.6F, 1.3F);
         this.SetCloak(true);
         break;
      case 1:
         EntityNetherSpider netherSpider = (EntityNetherSpider)this.createEntity(EntityNetherSpider.class);
         netherSpider.setAttackTarget(this.getAttackTarget());
         netherSpider.setSpiderSize(5, true);
         this.spawnMob(netherSpider, 0);
         this.mountEntity(netherSpider);
         break;
      case 2:
         EntityGlowstoneSkeleton glowstoneSkeleton = (EntityGlowstoneSkeleton)this.createEntity(EntityGlowstoneSkeleton.class);
         glowstoneSkeleton.setAttackTarget(this.getAttackTarget());
         glowstoneSkeleton.setSkeletonType(1);
         glowstoneSkeleton.setCurrentItemOrArmor(0, new ItemStack(NetherItems.wither_staff));
         glowstoneSkeleton.setCurrentItemOrArmor(4, new ItemStack(NetherItems.wither_helmet));
         glowstoneSkeleton.setCurrentItemOrArmor(3, new ItemStack(NetherItems.wither_chestplate));
         glowstoneSkeleton.setCurrentItemOrArmor(2, new ItemStack(NetherItems.wither_leggings));
         glowstoneSkeleton.setCurrentItemOrArmor(1, new ItemStack(NetherItems.wither_boots));
         this.spawnMob(glowstoneSkeleton, 2);
      }

      this.SpecialCooldown = 800 + this.rand.nextInt(200);
   }

   public void updateRidden() {
      super.updateRidden();
      if (this.ridingEntity instanceof EntityCreature) {
         EntityCreature entitycreature = (EntityCreature)this.ridingEntity;
         entitycreature.setAttackTarget(this.getAttackTarget());
         this.renderYawOffset = entitycreature.renderYawOffset;
      }

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

   public boolean attackEntityFrom(DamageSource source, float damage) {
      if (source == DamageSource.wither) {
         return false;
      } else {
         this.SetCloak(false);
         return super.attackEntityFrom(source, damage);
      }
   }

   public boolean attackEntityAsMob(Entity entity) {
      if (super.attackEntityAsMob(entity)) {
         if (this.getHeldItem() != null && this.getHeldItem().getItem() instanceof IEntityUsable && entity instanceof EntityLivingBase) {
            ((IEntityUsable)this.getHeldItem().getItem()).onMeleeAttack(this, (EntityLivingBase)entity, this.getHeldItem());
         }

         return true;
      } else {
         return false;
      }
   }

   public void attackEntityWithRangedAttack(EntityLivingBase entity, float var) {
      if (this.cooldown < 1 && this.getHeldItem() != null && this.getHeldItem().getItem() instanceof IEntityUsable) {
         Item item = this.getHeldItem().getItem();
         ((IEntityUsable)item).onRangedAttack(this, entity, this.getHeldItem(), true);
         if (item == NetherItems.flame_staff) {
            this.cooldown = 60;
         } else if (item == NetherItems.wither_staff) {
            this.cooldown = 70;
         } else if (item == NetherItems.quartz_bow) {
            this.cooldown = 8;
         }
      }

   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setBoolean("InCloak", this.InCloak());
      tagCompound.setInteger("SpecialCooldown", this.SpecialCooldown);
      tagCompound.setInteger("Cooldown", this.cooldown);
      tagCompound.setInteger("attackUpdateTimer", this.attackUpdateTimer);
   }

   public void readEntityFromNBT(NBTTagCompound tagCompund) {
      super.readEntityFromNBT(tagCompund);
      if (tagCompund.hasKey("InCloak", 1)) {
         this.SetCloak(tagCompund.getBoolean("IsInCloak"));
      }

      int b0;
      if (tagCompund.hasKey("SpecialCooldown", 99)) {
         b0 = tagCompund.getInteger("SpecialCooldown");
         this.SpecialCooldown = b0;
      }

      if (tagCompund.hasKey("Cooldown", 99)) {
         b0 = tagCompund.getInteger("Cooldown");
         this.cooldown = b0;
      }

      if (tagCompund.hasKey("attackUpdateTimer", 99)) {
         b0 = tagCompund.getInteger("attackUpdateTimer");
         this.attackUpdateTimer = b0;
      }

   }

   public boolean isAIEnabled() {
      return true;
   }

   public boolean InCloak() {
      return this.dataWatcher.getWatchableObjectByte(13) == 1;
   }

   protected void SetCloak(boolean cloak) {
      this.dataWatcher.updateObject(13, (byte)(cloak ? 1 : 0));
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
