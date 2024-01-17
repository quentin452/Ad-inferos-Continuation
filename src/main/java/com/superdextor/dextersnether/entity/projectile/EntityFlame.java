package com.superdextor.dextersnether.entity.projectile;

import com.superdextor.dextersnether.NetherConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFlame extends EntityThrowable {
   public int damage = 3;

   public EntityFlame(World worldIn) {
      super(worldIn);
   }

   public EntityFlame(World worldIn, EntityLivingBase entity) {
      super(worldIn, entity);
   }

   public EntityFlame(World worldIn, double var, double var2, double var3) {
      super(worldIn, var, var2, var3);
   }

   public void onUpdate() {
      super.onUpdate();
      this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, 0.0D, 0.1D, 0.0D);
   }

   protected void onImpact(MovingObjectPosition var) {
      if (var.entityHit != null) {
         var.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)this.damage);
         var.entityHit.setFire(5);
         Entity var10000 = var.entityHit;
         var10000.motionX *= -0.23D;
         var10000 = var.entityHit;
         var10000.motionY *= -0.23D;
         var10000 = var.entityHit;
         var10000.motionZ *= -0.23D;
      } else {
         int i = var.blockX;
         int j = var.blockY;
         int k = var.blockZ;
         switch(var.sideHit) {
         case 0:
            --j;
            break;
         case 1:
            ++j;
            break;
         case 2:
            --k;
            break;
         case 3:
            ++k;
            break;
         case 4:
            --i;
            break;
         case 5:
            ++i;
         }

         if (!this.worldObj.isRemote && this.worldObj.isAirBlock(i, j, k) && NetherConfig.glowstoneSwordFire) {
            this.worldObj.setBlock(i, j, k, Blocks.fire);
         }
      }

   }
}
