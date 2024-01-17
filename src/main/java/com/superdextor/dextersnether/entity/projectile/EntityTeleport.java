package com.superdextor.dextersnether.entity.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityTeleport extends EntityThrowable {
   public int Distance = 60;

   public EntityTeleport(World worldIn) {
      super(worldIn);
   }

   public EntityTeleport(World worldIn, EntityLivingBase thrower, int Distance) {
      super(worldIn, thrower);
      this.Distance = Distance;
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getVelocity(), 1.0F);
   }

   public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
      double i = (double)this.Distance * 0.009999999776482582D;
      float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
      x /= (double)f2;
      y /= (double)f2;
      z /= (double)f2;
      x += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
      y += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
      z += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
      x *= (double)velocity + i;
      y *= (double)velocity + i;
      z *= (double)velocity + i;
      this.motionX = x;
      this.motionY = y;
      this.motionZ = z;
      float f3 = MathHelper.sqrt_double(x * x + z * z);
      this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / 3.141592653589793D);
      this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, (double)f3) * 180.0D / 3.141592653589793D);
   }

   @SideOnly(Side.CLIENT)
   public EntityTeleport(World worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   protected void onImpact(MovingObjectPosition moving) {
      EntityLivingBase entitylivingbase = this.getThrower();
      if (moving.entityHit != null) {
         moving.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, entitylivingbase), 0.0F);
      }

      if (!this.worldObj.isRemote) {
         this.Teleport();
      }

   }

   public void onUpdate() {
      EntityLivingBase entitylivingbase = this.getThrower();

      for(int i = 0; i < 13; ++i) {
         this.worldObj.spawnParticle("magicCrit", this.posX - 0.5D, this.posY - 0.5D, this.posZ - 0.5D, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
      }

      if (!this.worldObj.isRemote && (double)this.ticksExisted > (double)this.Distance * 1.4D) {
         this.Teleport();
      }

      if (entitylivingbase != null && entitylivingbase instanceof EntityPlayer && !entitylivingbase.isEntityAlive()) {
         this.setDead();
      } else {
         super.onUpdate();
      }

   }

   protected void Teleport() {
      EntityLivingBase entitylivingbase = this.getThrower();
      if (entitylivingbase instanceof EntityPlayerMP) {
         EntityPlayerMP entityplayermp = (EntityPlayerMP)entitylivingbase;
         if (entityplayermp.playerNetServerHandler.func_147362_b().isChannelOpen() && entityplayermp.worldObj == this.worldObj) {
            EnderTeleportEvent event = new EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ, 5.0F);
            if (!MinecraftForge.EVENT_BUS.post(event)) {
               if (entitylivingbase.isRiding()) {
                  entitylivingbase.mountEntity((Entity)null);
               }

               entitylivingbase.setPositionAndUpdate(event.targetX, event.targetY, event.targetZ);
               entitylivingbase.fallDistance = 0.0F;
            }
         }
      }

      this.worldObj.newExplosion(entitylivingbase, this.posX, this.posY, this.posZ, 1.0F, false, true);
      this.setDead();
   }

   protected float getGravityVelocity() {
      return 0.0F;
   }

   protected float getVelocity() {
      return 0.8F;
   }
}
