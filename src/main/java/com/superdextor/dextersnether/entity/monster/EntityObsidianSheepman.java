package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherItems;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityObsidianSheepman extends EntityZombie implements NetherMob {
   private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
   private static final AttributeModifier field_110190_br;
   public int angerLevel;
   private int randomSoundDelay;
   private Entity field_110191_bu;

   public EntityObsidianSheepman(World world) {
      super(world);
      this.isImmuneToFire = true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(field_110186_bp).setBaseValue(0.0D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
   }

   protected boolean isAIEnabled() {
      return false;
   }

   public void onUpdate() {
      if (this.field_110191_bu != this.entityToAttack && !this.worldObj.isRemote) {
         IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         iattributeinstance.removeModifier(field_110190_br);
         if (this.entityToAttack != null) {
            iattributeinstance.applyModifier(field_110190_br);
         }
      }

      this.field_110191_bu = this.entityToAttack;
      if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
         this.playSound("mob.sheep.say", this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.4F);
      }

      super.onUpdate();
   }

   public boolean getCanSpawnHere() {
      return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
   }

   public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
      super.writeEntityToNBT(p_70014_1_);
      p_70014_1_.setShort("Anger", (short)this.angerLevel);
   }

   public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
      super.readEntityFromNBT(p_70037_1_);
      this.angerLevel = p_70037_1_.getShort("Anger");
   }

   protected Entity findPlayerToAttack() {
      return this.angerLevel == 0 ? null : super.findPlayerToAttack();
   }

   public boolean attackEntityFrom(DamageSource source, float f) {
      if (this.isEntityInvulnerable()) {
         return false;
      } else {
         Entity entity = source.getEntity();
         if (entity instanceof EntityPlayer) {
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

            for(int i = 0; i < list.size(); ++i) {
               Entity entity1 = (Entity)list.get(i);
               if (entity1 instanceof EntityObsidianSheepman) {
                  EntityObsidianSheepman entitypigzombie = (EntityObsidianSheepman)entity1;
                  entitypigzombie.becomeAngryAt(entity);
               }
            }

            this.becomeAngryAt(entity);
         }

         return super.attackEntityFrom(source, f);
      }
   }

   private void becomeAngryAt(Entity target) {
      this.entityToAttack = target;
      this.angerLevel = 400 + this.rand.nextInt(400);
      this.randomSoundDelay = this.rand.nextInt(40);
   }

   protected float getSoundPitch() {
      return super.getSoundPitch() * 0.7F;
   }

   protected String getLivingSound() {
      return "mob.zombiepig.zpig";
   }

   protected String getHurtSound() {
      this.playSound("dig.stone", this.getSoundVolume() * 1.3F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.0F);
      return "mob.sheep.say";
   }

   protected String getDeathSound() {
      this.playSound("dig.stone", this.getSoundVolume() * 1.3F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.0F);
      return "mob.zombiepig.zpigdeath";
   }

   protected void func_145780_a(int x, int y, int z, Block blockOn) {
      this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
   }

   protected void dropFewItems(boolean playerKill, int looting) {
      int j = this.rand.nextInt(2 + looting);

      int k;
      for(k = 0; k < j; ++k) {
         this.dropItem(Items.rotten_flesh, 1);
      }

      j = this.rand.nextInt(2 + looting);

      for(k = 0; k < j; ++k) {
         this.dropItem(NetherItems.obsidian_nugget, 1);
      }

   }

   public boolean interact(EntityPlayer player) {
      ItemStack itemstack = player.getCurrentEquippedItem();
      if (itemstack != null && itemstack.getItem() == Items.golden_apple && itemstack.getItemDamage() == 0 && this.isPotionActive(Potion.weakness)) {
         if (!player.capabilities.isCreativeMode) {
            --itemstack.stackSize;
         }

         if (itemstack.stackSize <= 0) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
         }

         if (!this.worldObj.isRemote) {
            this.startConversion(this.rand.nextInt(2401) + 3600);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void convertToVillager() {
      EntityObsidainSheepmanGolem entitygolem = new EntityObsidainSheepmanGolem(this.worldObj);
      entitygolem.copyLocationAndAnglesFrom(this);
      entitygolem.onSpawnWithEgg((IEntityLivingData)null);
      entitygolem.setPlayerCreated(true);
      entitygolem.setChild(this.isChild());
      entitygolem.setCurrentItemOrArmor(0, this.getEquipmentInSlot(0));
      entitygolem.setCurrentItemOrArmor(1, this.getEquipmentInSlot(1));
      entitygolem.setCurrentItemOrArmor(2, this.getEquipmentInSlot(2));
      entitygolem.setCurrentItemOrArmor(3, this.getEquipmentInSlot(3));
      entitygolem.setCurrentItemOrArmor(4, this.getEquipmentInSlot(4));
      this.worldObj.removeEntity(this);
      this.worldObj.spawnEntityInWorld(entitygolem);
      entitygolem.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
      this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1017, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
   }

   protected void dropRareDrop(int i) {
      this.dropItem(NetherItems.obsidian_ingot, 1);
   }

   protected void addRandomArmor() {
      this.setCurrentItemOrArmor(0, new ItemStack(NetherItems.obsidian_sword));
      if (this.rand.nextFloat() < (this.worldObj.difficultySetting == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
         int i = this.rand.nextInt(2);
         if (i == 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(NetherItems.obsidian_helmet));
            this.setCurrentItemOrArmor(3, new ItemStack(NetherItems.obsidian_chestplate));
         } else {
            this.setCurrentItemOrArmor(4, new ItemStack(NetherItems.obsidian_helmet));
            this.setCurrentItemOrArmor(3, new ItemStack(NetherItems.obsidian_chestplate));
            this.setCurrentItemOrArmor(2, new ItemStack(NetherItems.obsidian_leggings));
            this.setCurrentItemOrArmor(1, new ItemStack(NetherItems.obsidian_boots));
         }
      }

   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData idata) {
      super.onSpawnWithEgg(idata);
      this.setVillager(false);
      return idata;
   }

   public boolean isAngry() {
      return false;
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
      field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.45D, 0)).setSaved(false);
   }
}
