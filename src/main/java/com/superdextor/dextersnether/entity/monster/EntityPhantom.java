package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
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
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityPhantom extends EntityMob implements NetherMob {
   public EntityPhantom(World worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.9F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
      this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      this.isImmuneToFire = true;
      this.experienceValue = 12;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
   }

   protected String getLivingSound() {
      return "dextersnether:mob.phantom.idle";
   }

   protected String getHurtSound() {
      return "dextersnether:mob.phantom.hit";
   }

   protected String getDeathSound() {
      return "dextersnether:mob.phantom.death";
   }

   @SideOnly(Side.CLIENT)
   public int getBrightnessForRender(float p_70070_1_) {
      return this.isSneaking() ? 0 : (this.rand.nextFloat() < 0.1F ? 0 : 15728880);
   }

   private boolean shouldAttackPlayer(EntityPlayer p_70821_1_) {
      Vec3 vec3 = p_70821_1_.getLook(1.0F).normalize();
      Vec3 vec31 = Vec3.createVectorHelper(this.posX - p_70821_1_.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (p_70821_1_.posY + (double)p_70821_1_.getEyeHeight()), this.posZ - p_70821_1_.posZ);
      double d0 = vec31.lengthVector();
      vec31 = vec31.normalize();
      double d1 = vec3.dotProduct(vec31);
      return d1 > 1.0D - 0.025D / d0 && p_70821_1_.canEntityBeSeen(this);
   }

   protected void updateAITasks() {
      this.setSneaking(false);
      if (this.getAttackTarget() instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)this.getAttackTarget();
         if (this.shouldAttackPlayer(player)) {
            this.setSneaking(true);
            this.getNavigator().setSpeed(0.0D);
         }
      }

      super.updateAITasks();
   }

   protected Item getDropItem() {
      return NetherItems.ectoplasm;
   }

   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
      int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + p_70628_2_);

      for(int k = 0; k < j; ++k) {
         this.dropItem(NetherItems.ectoplasm, 1);
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
