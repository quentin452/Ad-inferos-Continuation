package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.entity.NetherMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityObsidianGolem extends EntityIronGolem implements NetherMob {
   public EntityObsidianGolem(World worldIn) {
      super(worldIn);
      this.isImmuneToFire = true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
      this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.5D);
   }

   public boolean attackEntityAsMob(Entity hitEntity) {
      this.worldObj.setEntityState(this, (byte)4);
      boolean flag = hitEntity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(9 + this.rand.nextInt(15)));
      if (flag) {
         hitEntity.motionY += 0.4000000079604645D;
      }

      this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      return flag;
   }

   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
      int j = this.rand.nextInt(3);

      int k;
      for(k = 0; k < j; ++k) {
         this.func_145778_a(Item.getItemFromBlock(Blocks.red_flower), 1, 0.0F);
      }

      k = 3 + this.rand.nextInt(3);

      for(int l = 0; l < k; ++l) {
         this.entityDropItem(new ItemStack(Blocks.obsidian), 1.0F);
      }

   }

   public boolean DarkfireRes() {
      return true;
   }

   public boolean CurseRes() {
      return false;
   }

   public boolean WitherRes() {
      return true;
   }

   public boolean AcidRes() {
      return true;
   }

   public boolean SpikeRes() {
      return false;
   }
}
