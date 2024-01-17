package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.dextersnether.items.ItemNetherStaff;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityQuartzman extends EntityGolem implements IRangedAttackMob {

   public EntityQuartzman(World worldIn) {
      super(worldIn);
      this.isImmuneToFire = true;
      this.setSize(0.4F, 1.8F);
      this.getNavigator().setAvoidsWater(true);
      this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
      this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(4, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3000000029802322D);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
   }

   protected Item getDropItem() {
      return Items.quartz;
   }

   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
      int j = this.rand.nextInt(16);

      for(int k = 0; k < j; ++k) {
         this.dropItem(Items.quartz, 1);
      }

   }

   public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
      ((ItemNetherStaff)NetherItems.flame_staff).onRangedAttack(this, target, new ItemStack(NetherItems.flame_staff), true);
   }

   public boolean isAIEnabled() {
      return true;
   }
}
