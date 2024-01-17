package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityNetherSpider extends EntitySpider implements NetherMob {
   public EntityNetherSpider(World worldIn) {
      super(worldIn);
      this.experienceValue = 7;
      this.isImmuneToFire = true;
   }

   protected float getSoundPitch() {
      return super.getSoundPitch() - (0.5F * (float)this.getSpiderSize() - 0.5F);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(14, (byte)1);
   }

   protected Item getDropItem() {
      return NetherItems.flame_string;
   }

   protected void dropFewItems(boolean playerKill, int looting) {
      int i = this.getSpiderSize();
      if (i < 0) {
         i = 0;
      }

      int j = this.rand.nextInt(2 + i);
      if (looting > 0) {
         j += this.rand.nextInt(looting + 1);
      }

      int k;
      for(k = 0; k < j; ++k) {
         this.dropItem(NetherItems.flame_string, 1);
      }

      for(k = 0; k < i; ++k) {
         if (playerKill && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + looting) > 0)) {
            this.dropItem(Items.spider_eye, 1);
         }
      }

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
      return false;
   }

   public boolean attackEntityAsMob(Entity target) {
      if (super.attackEntityAsMob(target)) {
         if (target instanceof EntityLivingBase) {
            byte b0 = 3;
            if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL) {
               b0 = 6;
            } else if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
               b0 = 12;
            }

            if (b0 > 0) {
               ((EntityLivingBase)target).addPotionEffect(new PotionEffect(Potion.wither.id, b0 * (this.getSpiderSize() + 0) * 15, 0));
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return source == DamageSource.wither ? false : super.attackEntityFrom(source, amount);
   }

   public int getSpiderSize() {
      return this.dataWatcher.getWatchableObjectByte(14);
   }

   public void setSpiderSize(int size, boolean fillHealth) {
      this.dataWatcher.updateObject(14, (byte)size);
      double f = (double)(size * 5 + 15);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(f);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D + (double)(size * 2));
      if (fillHealth) {
         this.setHealth(this.getMaxHealth());
      }

   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("Size", this.getSpiderSize());
   }

   public void readEntityFromNBT(NBTTagCompound tagCompund) {
      super.readEntityFromNBT(tagCompund);
      if (tagCompund.hasKey("Size", 99)) {
         this.setSpiderSize(tagCompund.getInteger("Size"), false);
      }

   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData idata) {
      Object data = super.onSpawnWithEgg(idata);
      this.setSpiderSize(this.rand.nextInt(3), true);
      if (this.getSpiderSize() > 1) {
         int i = this.rand.nextInt(2) + 2;

         for(int x = 0; x < i; ++x) {
            EntityNetherSpider Spider = (EntityNetherSpider)EntityList.createEntityByName("dextersnether_Nether_Spider", this.worldObj);
            Spider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            Spider.setSpiderSize(0, true);
            this.worldObj.spawnEntityInWorld(Spider);
         }
      }

      return (IEntityLivingData)data;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      float f = 0.5F * (float)(this.getSpiderSize() + 1);
      this.setSize(1.4F * f, 0.9F * f);
   }
}
