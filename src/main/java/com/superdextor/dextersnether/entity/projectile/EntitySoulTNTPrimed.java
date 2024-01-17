package com.superdextor.dextersnether.entity.projectile;

import com.superdextor.dextersnether.NetherConfig;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySoulTNTPrimed extends EntityTNTPrimed {
   public float explosion = 4.0F;
   private EntityLivingBase tntPlacedBy;

   public EntitySoulTNTPrimed(World worldIn) {
      super(worldIn);
      this.setSize(1.0F, 1.0F);
   }

   protected void entityInit() {
      this.dataWatcher.addObject(15, new Integer(6));
   }

   public EntitySoulTNTPrimed(World worldIn, double x, double y, double z, EntityLivingBase placer) {
      super(worldIn, x, y, z, placer);
      this.tntPlacedBy = placer;
      this.setSize(1.0F, 1.0F);
   }

   public void onUpdate() {
      if (!this.worldObj.isRemote) {
         this.dataWatcher.updateObject(15, this.fuse);
      }

      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY -= 0.03999999910593033D;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.9800000190734863D;
      this.motionY *= 0.9800000190734863D;
      this.motionZ *= 0.9800000190734863D;
      if (this.onGround) {
         this.motionX *= 0.699999988079071D;
         this.motionZ *= 0.699999988079071D;
         this.motionY *= -0.5D;
      }

      if (this.getFuse() <= 0) {
         this.setDead();
         if (!this.worldObj.isRemote) {
            this.explode();
         }
      } else {
         this.handleWaterMovement();
         this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
         --this.fuse;
      }

   }

   private void explode() {
      if (NetherConfig.soulTNTGriefing) {
         this.worldObj.createExplosion(this, this.posX, this.posY + (double)(this.height / 2.0F), this.posZ, this.explosion, true);
         float f3 = this.explosion * 2.0F;
         int j = MathHelper.floor_double(this.posX - (double)f3 - 1.0D);
         int k = MathHelper.floor_double(this.posX + (double)f3 + 1.0D);
         int j1 = MathHelper.floor_double(this.posY - (double)f3 - 1.0D);
         int l = MathHelper.floor_double(this.posY + (double)f3 + 1.0D);
         int k1 = MathHelper.floor_double(this.posZ - (double)f3 - 1.0D);
         int i1 = MathHelper.floor_double(this.posZ + (double)f3 + 1.0D);
         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.getTntPlacedBy(), AxisAlignedBB.getBoundingBox((double)j, (double)j1, (double)k1, (double)k, (double)l, (double)i1));
         Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);

         for(int l1 = 0; l1 < list.size(); ++l1) {
            Entity entity = (Entity)list.get(l1);
            double d12 = entity.getDistance(this.posX, this.posY, this.posZ) / (double)f3;
            if (d12 <= 1.0D) {
               double d5 = entity.posX - this.posX;
               double d7 = entity.posY + (double)entity.getEyeHeight() - this.posY;
               double d9 = entity.posZ - this.posZ;
               double d13 = (double)MathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);
               if (d13 != 0.0D && entity instanceof EntityLivingBase) {
                  ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.wither.id, 170, 1));
               }
            }
         }
      }

   }

   public EntityLivingBase getTntPlacedBy() {
      return this.tntPlacedBy;
   }

   public int getFuse() {
      return this.dataWatcher.getWatchableObjectInt(15);
   }
}
