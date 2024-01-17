package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherItems;
import java.util.Calendar;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityObsidainSheepmanGolem extends EntityGolem implements NetherMob {
   private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   private static final AttributeModifier babySpeedBoostModifier;

   public EntityObsidainSheepmanGolem(World worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.8F);
      this.isImmuneToFire = true;
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
      this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
      this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataWatcher().addObject(12, (byte)0);
      this.dataWatcher.addObject(14, (byte)0);
      this.dataWatcher.addObject(16, (byte)0);
   }

   public boolean isChild() {
      return this.getDataWatcher().getWatchableObjectByte(12) == 1;
   }

   public void setChild(boolean child) {
      this.getDataWatcher().updateObject(12, (byte)(child ? 1 : 0));
      if (this.worldObj != null && !this.worldObj.isRemote) {
         IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         iattributeinstance.removeModifier(babySpeedBoostModifier);
         if (child) {
            iattributeinstance.applyModifier(babySpeedBoostModifier);
         }
      }

   }

   public boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
         int i = MathHelper.floor_double(this.posX);
         int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
         int k = MathHelper.floor_double(this.posZ);
         Block block = this.worldObj.getBlock(i, j, k);
         if (block.getMaterial() != Material.air) {
            this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(Blocks.obsidian) + "_" + this.worldObj.getBlockMetadata(i, j, k), this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
         }
      }

      if (!this.worldObj.isRemote) {
         this.setAttacking(this.getAttackTarget() != null);
      }

   }

   public boolean canAttackClass(Class target) {
      return this.isPlayerCreated() && EntityPlayer.class.isAssignableFrom(target) ? false : super.canAttackClass(target);
   }

   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("PlayerCreated", this.isPlayerCreated());
      if (this.isChild()) {
         tag.setBoolean("IsBaby", true);
      }

   }

   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setPlayerCreated(tag.getBoolean("PlayerCreated"));
      if (tag.getBoolean("IsBaby")) {
         this.setChild(true);
      }

   }

   public boolean attackEntityAsMob(Entity target) {
      float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
      int i = 0;
      if (target instanceof EntityLivingBase) {
         f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)target);
         i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)target);
      }

      boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), f);
      if (flag) {
         if (i > 0) {
            target.addVelocity((double)(-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * (float)i * 0.5F));
            this.motionX *= 0.6D;
            this.motionZ *= 0.6D;
         }

         int j = EnchantmentHelper.getFireAspectModifier(this);
         if (j > 0) {
            target.setFire(j * 4);
         }

         if (target instanceof EntityLivingBase) {
            EnchantmentHelper.func_151384_a((EntityLivingBase)target, this);
         }

         EnchantmentHelper.func_151385_b(this, target);
      }

      return flag;
   }

   protected String getLivingSound() {
      return "mob.sheep.say";
   }

   protected String getHurtSound() {
      return "mob.sheep.say";
   }

   protected String getDeathSound() {
      return "mob.sheep.say";
   }

   protected void func_145780_a(int x, int y, int z, Block blockOn) {
      this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
   }

   protected Item getDropItem() {
      return NetherItems.obsidian_nugget;
   }

   protected void addRandomArmor() {
      this.setCurrentItemOrArmor(0, new ItemStack(NetherItems.obsidian_sword));
   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData idata) {
      idata = super.onSpawnWithEgg(idata);
      this.addRandomArmor();
      this.enchantEquipment();
      this.setCanPickUpLoot(true);
      if (this.rand.nextFloat() < 0.05F) {
         this.setChild(true);
      }

      if (this.getEquipmentInSlot(4) == null) {
         Calendar calendar = this.worldObj.getCurrentDate();
         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
            this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
            this.equipmentDropChances[4] = 0.0F;
         }
      }

      return idata;
   }

   public boolean isPlayerCreated() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void setPlayerCreated(boolean playerCreated) {
      byte b0 = this.dataWatcher.getWatchableObjectByte(16);
      if (playerCreated) {
         this.dataWatcher.updateObject(16, (byte)(b0 | 1));
      } else {
         this.dataWatcher.updateObject(16, (byte)(b0 & -2));
      }

   }

   public boolean isAttacking() {
      return this.getDataWatcher().getWatchableObjectByte(14) == 1;
   }

   public void setAttacking(boolean attacking) {
      this.getDataWatcher().updateObject(14, (byte)(attacking ? 1 : 0));
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
      return true;
   }

   public boolean SpikeRes() {
      return false;
   }

   static {
      babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5D, 1);
   }
}
