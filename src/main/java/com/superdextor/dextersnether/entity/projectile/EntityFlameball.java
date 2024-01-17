package com.superdextor.dextersnether.entity.projectile;

import com.superdextor.dextersnether.NetherConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityFlameball extends EntityLargeFireball {
   public float damage;

   public EntityFlameball(World worldIn) {
      super(worldIn);
      this.damage = 8.0F;
   }

   @SideOnly(Side.CLIENT)
   public EntityFlameball(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
      super(worldIn, x, y, z, accelX, accelY, accelZ);
      this.damage = 8.0F;
   }

   public EntityFlameball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
      super(worldIn, shooter, accelX, accelY, accelZ);
      this.damage = 8.0F;
   }

   public EntityFlameball(World worldIn, EntityLivingBase shooter, double speed) {
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

   public EntityFlameball(World worldIn, EntityLiving shooter, EntityLivingBase target) {
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
            movingObject.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), this.damage);
         }

         boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
         if (this.shootingEntity instanceof EntityPlayer) {
            flag = true;
         }

         if (NetherConfig.flameStaffGriefing) {
            this.worldObj.newExplosion(this.shootingEntity, this.posX, this.posY, this.posZ, (float)this.field_92057_e, flag, flag);
         }

         this.setDead();
      }

   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setFloat("Damage", this.damage);
   }

   public void readEntityFromNBT(NBTTagCompound tagCompund) {
      super.readEntityFromNBT(tagCompund);
      if (tagCompund.hasKey("Damage", 99)) {
         this.damage = tagCompund.getFloat("Damage");
      }

   }
}
