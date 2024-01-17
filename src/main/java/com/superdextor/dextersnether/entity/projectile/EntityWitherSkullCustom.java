package com.superdextor.dextersnether.entity.projectile;

import com.superdextor.dextersnether.NetherConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityWitherSkullCustom extends EntityWitherSkull {
   public float damage;
   public int explosionPower;

   public EntityWitherSkullCustom(World worldIn) {
      super(worldIn);
      this.damage = 8.0F;
      this.explosionPower = 1;
   }

   public EntityWitherSkullCustom(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.damage = 8.0F;
      this.explosionPower = 1;
   }

   @SideOnly(Side.CLIENT)
   public EntityWitherSkullCustom(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
      super(worldIn, x, y, z, accelX, accelY, accelZ);
      this.damage = 8.0F;
      this.explosionPower = 1;
   }

   public EntityWitherSkullCustom(World worldIn, EntityLivingBase shooter, double speed) {
      this(worldIn);
      this.shootingEntity = shooter;
      this.setLocationAndAngles(shooter.posX, shooter.posY + 0.8D, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.motionX = this.motionY = this.motionZ = 0.0D;
      Vec3 vec3 = shooter.getLook(1.0F);
      this.accelerationX = vec3.xCoord * speed;
      this.accelerationY = vec3.yCoord * speed;
      this.accelerationZ = vec3.zCoord * speed;
   }

   public EntityWitherSkullCustom(World worldIn, EntityLiving shooter, EntityLivingBase target) {
      this(worldIn);
      Vec3 vec3 = shooter.getLook(1.0F);
      double accelX = target.posX - (shooter.posX + vec3.xCoord * 1.0D);
      double accelY = target.boundingBox.minY + (double)(target.height / 2.0F) - (0.5D + shooter.posY + (double)(shooter.height / 2.0F));
      double accelZ = target.posZ - (shooter.posZ + vec3.zCoord * 1.0D);
      this.shootingEntity = shooter;
      this.setLocationAndAngles(shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.motionX = this.motionY = this.motionZ = 0.0D;
      accelX += this.rand.nextGaussian() * 0.4D;
      accelY += this.rand.nextGaussian() * 0.4D;
      accelZ += this.rand.nextGaussian() * 0.4D;
      double d3 = (double)MathHelper.sqrt_double(accelX * accelX + accelY * accelY + accelZ * accelZ);
      this.accelerationX = accelX / d3 * 0.1D;
      this.accelerationY = accelY / d3 * 0.1D;
      this.accelerationZ = accelZ / d3 * 0.1D;
   }

   protected void onImpact(MovingObjectPosition movingObject) {
      if (!this.worldObj.isRemote) {
         if (movingObject.entityHit != null) {
            if (this.shootingEntity != null) {
               if (movingObject.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F) && !movingObject.entityHit.isEntityAlive()) {
                  this.shootingEntity.heal(1.0F);
               }
            } else {
               movingObject.entityHit.attackEntityFrom(DamageSource.magic, this.damage);
            }
         }

         if (NetherConfig.witherStaffGriefing) {
            this.worldObj.newExplosion(this.shootingEntity, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
         }

         float f3 = (float)this.explosionPower * 2.0F;
         int j = MathHelper.floor_double(this.posX - (double)f3 - 1.0D);
         int k = MathHelper.floor_double(this.posX + (double)f3 + 1.0D);
         int j1 = MathHelper.floor_double(this.posY - (double)f3 - 1.0D);
         int l = MathHelper.floor_double(this.posY + (double)f3 + 1.0D);
         int k1 = MathHelper.floor_double(this.posZ - (double)f3 - 1.0D);
         int i1 = MathHelper.floor_double(this.posZ + (double)f3 + 1.0D);
         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.shootingEntity, AxisAlignedBB.getBoundingBox((double)j, (double)j1, (double)k1, (double)k, (double)l, (double)i1));
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

         this.setDead();
      }

   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("ExplosionPower", this.explosionPower);
      tagCompound.setFloat("Damage", this.damage);
   }

   public void readEntityFromNBT(NBTTagCompound tagCompund) {
      super.readEntityFromNBT(tagCompund);
      if (tagCompund.hasKey("ExplosionPower", 99)) {
         this.explosionPower = tagCompund.getInteger("ExplosionPower");
      }

      if (tagCompund.hasKey("Damage", 99)) {
         this.damage = tagCompund.getFloat("Damage");
      }

   }
}
