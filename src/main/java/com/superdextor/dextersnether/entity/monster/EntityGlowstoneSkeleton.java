package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.dextersnether.world.WorldProviderAbyss;
import com.superdextor.thinkbigcore.items.IEntityUsable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Calendar;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGlowstoneSkeleton extends EntityMob implements IRangedAttackMob, NetherMob {
   private EntityAIArrowAttack aiShootFlame = new EntityAIArrowAttack(this, 1.25D, 7, 10.0F);
   private EntityAIArrowAttack aiShootSkull = new EntityAIArrowAttack(this, 1.25D, 50, 10.0F);
   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, 1.2D, false);
   private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   private static final AttributeModifier babySpeedBoostModifier;

   public EntityGlowstoneSkeleton(World world) {
      super(world);
      this.isImmuneToFire = true;
      this.setSize(0.4F, 1.8F);
      this.experienceValue = 10;
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIRestrictSun(this));
      this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
      this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      if (world != null && !world.isRemote) {
         this.setCombatTask();
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataWatcher().addObject(12, (byte)0);
      this.getDataWatcher().addObject(13, (byte)0);
      this.getDataWatcher().addObject(14, (byte)0);
   }

   public boolean isChild() {
      return this.getDataWatcher().getWatchableObjectByte(12) == 1;
   }

   public void setChild(boolean p_82227_1_) {
      this.getDataWatcher().updateObject(12, (byte)(p_82227_1_ ? 1 : 0));
      if (this.worldObj != null && !this.worldObj.isRemote) {
         IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         iattributeinstance.removeModifier(babySpeedBoostModifier);
         if (p_82227_1_) {
            iattributeinstance.applyModifier(babySpeedBoostModifier);
         }
      }

      this.setSize(0.3F, 0.7F);
   }

   public int getSkeletonType() {
      return this.getDataWatcher().getWatchableObjectByte(14);
   }

   public void setSkeletonType(int type) {
      this.getDataWatcher().updateObject(14, (byte)type);
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected String getLivingSound() {
      return "mob.skeleton.say";
   }

   protected String getHurtSound() {
      return "mob.skeleton.hurt";
   }

   protected String getDeathSound() {
      return "mob.skeleton.death";
   }

   protected void func_145780_a(int x, int y, int z, Block block) {
      this.playSound("mob.skeleton.step", 0.15F, 1.0F);
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.isChild()) {
         this.setSize(0.3F, 0.7F);
      }

      if (!this.worldObj.isRemote && this.getSkeletonType() == 0 && this.ticksExisted % 300 == 0) {
         this.setCombatTask();
      }

   }

   protected Item getDropItem() {
      return this.getSkeletonType() > 0 ? NetherItems.dimstone_dust : Items.glowstone_dust;
   }

   protected void dropFewItems(boolean playerKill, int looting) {
      int j = this.rand.nextInt(3 + looting);

      int k;
      for(k = 0; k < j; ++k) {
         this.dropItem(Items.bone, 1);
      }

      j = this.rand.nextInt(3 + looting);

      for(k = 0; k < j; ++k) {
         this.dropItem(this.getDropItem(), 1);
      }

   }

   protected void addRandomArmor() {
      if (this.getSkeletonType() == 0) {
         this.setCurrentItemOrArmor(0, new ItemStack(NetherItems.glowstone_sword));
      } else if (this.rand.nextBoolean()) {
         this.setCurrentItemOrArmor(0, new ItemStack(NetherItems.wither_sword));
      } else {
         this.setCurrentItemOrArmor(0, new ItemStack(NetherItems.wither_staff));
      }

   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData idata) {
      idata = super.onSpawnWithEgg(idata);
      int baby = this.rand.nextInt(20);
      if (this.worldObj.provider instanceof WorldProviderAbyss) {
         this.setSkeletonType(1);
      }

      this.addRandomArmor();
      this.enchantEquipment();
      if (baby > 18) {
         this.setChild(true);
      }

      this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));
      if (this.getEquipmentInSlot(4) == null) {
         Calendar calendar = this.worldObj.getCurrentDate();
         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
            this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
            this.equipmentDropChances[4] = 0.0F;
         }
      }

      return idata;
   }

   public void setCombatTask() {
      this.tasks.removeTask(this.aiAttackOnCollide);
      this.tasks.removeTask(this.aiShootFlame);
      this.tasks.removeTask(this.aiShootSkull);
      boolean ranged = this.rand.nextBoolean();
      if (this.getHeldItem() == null) {
         ranged = false;
      } else if (this.getSkeletonType() > 0) {
         if (this.getHeldItem().getItem() == NetherItems.wither_staff) {
            ranged = true;
         } else {
            ranged = false;
         }
      }

      if (ranged) {
         if (this.getSkeletonType() > 0) {
            this.tasks.addTask(4, this.aiShootSkull);
         } else {
            this.tasks.addTask(4, this.aiShootFlame);
         }

         this.getDataWatcher().updateObject(13, (byte)1);
      } else {
         this.tasks.addTask(4, this.aiAttackOnCollide);
         this.getDataWatcher().updateObject(13, (byte)0);
      }

   }

   public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
      if (this.getHeldItem() != null && this.getHeldItem().getItem() instanceof IEntityUsable) {
         ((IEntityUsable)this.getHeldItem().getItem()).onRangedAttack(this, target, this.getHeldItem(), true);
      }

   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      if (tagCompound.getBoolean("IsBaby")) {
         this.setChild(true);
      }

      if (tagCompound.hasKey("SkeletonType", 99)) {
         byte b0 = tagCompound.getByte("SkeletonType");
         this.setSkeletonType(b0);
      }

      this.setCombatTask();
   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      if (this.isChild()) {
         tagCompound.setBoolean("IsBaby", true);
      }

      tagCompound.setByte("SkeletonType", (byte)this.getSkeletonType());
   }

   public void setCurrentItemOrArmor(int slot, ItemStack stack) {
      super.setCurrentItemOrArmor(slot, stack);
      if (!this.worldObj.isRemote && slot == 0) {
         this.setCombatTask();
      }

   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return this.getSkeletonType() > 0 && source == DamageSource.wither ? false : super.attackEntityFrom(source, amount);
   }

   @SideOnly(Side.CLIENT)
   public int getBrightnessForRender(float f) {
      return this.getSkeletonType() > 0 ? super.getBrightnessForRender(f) : 15728880;
   }

   public float getBrightness(float f) {
      return this.getSkeletonType() > 0 ? super.getBrightness(f) : 1.0F;
   }

   public boolean IsUsingRangedAttack() {
      return this.getDataWatcher().getWatchableObjectByte(13) == 1;
   }

   public boolean DarkfireRes() {
      return true;
   }

   public boolean CurseRes() {
      return false;
   }

   public boolean WitherRes() {
      return this.getSkeletonType() > 0;
   }

   public boolean AcidRes() {
      return false;
   }

   public boolean SpikeRes() {
      return false;
   }

   static {
      babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5D, 1);
   }
}
