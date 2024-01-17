package com.superdextor.dextersnether.entity.monster;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherItems;
import java.util.Calendar;
import java.util.IdentityHashMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityReaper extends EntityMob implements NetherMob {
   private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
   private static final AttributeModifier attackingSpeedBoostModifier;
   private static final UUID babySpeedBoostUUID;
   private static final AttributeModifier babySpeedBoostModifier;
   private static boolean[] carriableBlocks;
   private int teleportDelay;
   private boolean isAggressive;
   private static IdentityHashMap carriable;

   public EntityReaper(World worldIn) {
      super(worldIn);
      this.isImmuneToFire = true;
      this.experienceValue = 20;
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataWatcher().addObject(12, (byte)0);
      this.dataWatcher.addObject(16, new Byte((byte)0));
      this.dataWatcher.addObject(17, new Byte((byte)0));
      this.dataWatcher.addObject(18, new Byte((byte)0));
      this.dataWatcher.addObject(19, new Float(0.0F));
   }

   public boolean isChild() {
      return this.getDataWatcher().getWatchableObjectByte(12) == 1;
   }

   public void setChild(boolean p_82227_1_) {
      this.getDataWatcher().updateObject(12, (byte)(p_82227_1_ ? 1 : 0));
      this.setSize(0.3F, 0.7F);
      if (this.worldObj != null && !this.worldObj.isRemote) {
         IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         iattributeinstance.removeModifier(babySpeedBoostModifier);
         if (p_82227_1_) {
            iattributeinstance.applyModifier(babySpeedBoostModifier);
         }
      }

   }

   public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
      super.writeEntityToNBT(p_70014_1_);
      p_70014_1_.setShort("carried", (short)Block.getIdFromBlock(this.getCurrentBlock()));
      p_70014_1_.setShort("carriedData", (short)this.getCarryingData());
      if (this.isChild()) {
         p_70014_1_.setBoolean("IsBaby", true);
      }

   }

   public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
      super.readEntityFromNBT(p_70037_1_);
      this.setCurrentBlock(Block.getBlockById(p_70037_1_.getShort("carried")));
      this.setCarryingData(p_70037_1_.getShort("carriedData"));
      if (p_70037_1_.getBoolean("IsBaby")) {
         this.setChild(true);
      }

   }

   protected Entity findPlayerToAttack() {
      EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
      if (entityplayer != null) {
         this.isAggressive = true;
      }

      return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
   }

   private boolean shouldAttackPlayer(EntityPlayer p_70821_1_) {
      Vec3 vec3 = p_70821_1_.getLook(1.0F).normalize();
      Vec3 vec31 = Vec3.createVectorHelper(this.posX - p_70821_1_.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (p_70821_1_.posY + (double)p_70821_1_.getEyeHeight()), this.posZ - p_70821_1_.posZ);
      double d0 = vec31.lengthVector();
      vec31 = vec31.normalize();
      double d1 = vec3.dotProduct(vec31);
      return d1 > 1.0D - 0.025D / d0 && p_70821_1_.canEntityBeSeen(this);
   }

   public void onLivingUpdate() {
      if (this.isSneaking()) {
         this.setAlpha(0.0F);
         this.setSneaking(false);
      }

      if (this.getAlpha() < 1.0F) {
         this.setAlpha(this.getAlpha() + 0.05F);
      }

      if (this.isAggressive) {
         IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         iattributeinstance.removeModifier(attackingSpeedBoostModifier);
         if (this.entityToAttack != null) {
            iattributeinstance.applyModifier(attackingSpeedBoostModifier);
         }
      }

      int k;
      if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") && NetherConfig.reaperGriefing) {
         int i;
         int j;
         Block block;
         if (this.getCurrentBlock().getMaterial() == Material.air) {
            if (this.rand.nextInt(20) == 0) {
               k = MathHelper.floor_double(this.posX - 2.0D + this.rand.nextDouble() * 4.0D);
               i = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 3.0D);
               j = MathHelper.floor_double(this.posZ - 2.0D + this.rand.nextDouble() * 4.0D);
               block = this.worldObj.getBlock(k, i, j);
               if (getCarriable(block)) {
                  this.setCurrentBlock(block);
                  this.setCarryingData(this.worldObj.getBlockMetadata(k, i, j));
                  this.worldObj.setBlock(k, i, j, Blocks.air);
               }
            }
         } else if (this.rand.nextInt(2000) == 0) {
            k = MathHelper.floor_double(this.posX - 1.0D + this.rand.nextDouble() * 2.0D);
            i = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 2.0D);
            j = MathHelper.floor_double(this.posZ - 1.0D + this.rand.nextDouble() * 2.0D);
            block = this.worldObj.getBlock(k, i, j);
            Block block1 = this.worldObj.getBlock(k, i - 1, j);
            if (block.getMaterial() == Material.air && block1.getMaterial() != Material.air && block1.renderAsNormalBlock()) {
               this.worldObj.setBlock(k, i, j, this.getCurrentBlock(), this.getCarryingData(), 3);
               this.setCurrentBlock(Blocks.air);
            }
         }
      }

      for(k = 0; k < 2; ++k) {
         this.worldObj.spawnParticle("reddust", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 1.0D) * 2.0D);
      }

      if (this.isScreaming() && !this.isAggressive && this.rand.nextInt(100) == 0) {
         this.setScreaming(false);
      }

      this.isJumping = false;
      if (this.entityToAttack != null) {
         this.faceEntity(this.entityToAttack, 100.0F, 100.0F);
      }

      if (!this.worldObj.isRemote && this.isEntityAlive()) {
         if (this.entityToAttack != null) {
            if (this.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)this.entityToAttack)) {
               if (this.entityToAttack.getDistanceSqToEntity(this) < 16.0D) {
                  this.teleportRandomly();
               }

               this.teleportDelay = 0;
            } else if (this.entityToAttack.getDistanceSqToEntity(this) > 256.0D && this.teleportDelay++ >= 10 && this.teleportToEntity(this.entityToAttack)) {
               this.teleportDelay = 0;
            }
         } else {
            this.setScreaming(false);
            this.teleportDelay = 0;
         }
      }

      super.onLivingUpdate();
   }

   protected boolean teleportRandomly() {
      double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
      double d1 = this.posY + (double)(this.rand.nextInt(64) - 32);
      double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
      return this.teleportTo(d0, d1, d2);
   }

   protected boolean teleportToEntity(Entity p_70816_1_) {
      Vec3 vec3 = Vec3.createVectorHelper(this.posX - p_70816_1_.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - p_70816_1_.posY + (double)p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
      vec3 = vec3.normalize();
      double d0 = 16.0D;
      double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
      double d2 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
      double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
      return this.teleportTo(d1, d2, d3);
   }

   protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
      this.setSneaking(true);
      EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0.0F);
      if (MinecraftForge.EVENT_BUS.post(event)) {
         return false;
      } else {
         double d3 = this.posX;
         double d4 = this.posY;
         double d5 = this.posZ;
         this.posX = event.targetX;
         this.posY = event.targetY;
         this.posZ = event.targetZ;
         boolean flag = false;
         int i = MathHelper.floor_double(this.posX);
         int j = MathHelper.floor_double(this.posY);
         int k = MathHelper.floor_double(this.posZ);
         if (this.worldObj.blockExists(i, j, k)) {
            boolean flag1 = false;

            while(!flag1 && j > 0) {
               Block block = this.worldObj.getBlock(i, j - 1, k);
               if (block.getMaterial().blocksMovement()) {
                  flag1 = true;
               } else {
                  --this.posY;
                  --j;
               }
            }

            if (flag1) {
               this.setPosition(this.posX, this.posY, this.posZ);
               if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
                  flag = true;
               }
            }
         }

         if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
         } else {
            short short1 = 128;

            for(int l = 0; l < short1; ++l) {
               double d6 = (double)l / ((double)short1 - 1.0D);
               float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
               double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
               double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
               this.worldObj.spawnParticle("flame", d7, d8, d9, (double)f, (double)f1, (double)f2);
            }

            this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound("mob.endermen.portal", 1.0F, 1.0F);
            return true;
         }
      }
   }

   protected String getLivingSound() {
      return this.isScreaming() ? "mob.endermen.stare" : "mob.endermen.stare";
   }

   protected String getHurtSound() {
      return "mob.wither.hurt";
   }

   protected String getDeathSound() {
      return "mob.wither.death";
   }

   protected void dropFewItems(boolean playerKill, int looting) {
      int j = this.rand.nextInt(2 + looting);
      int k;
      if (playerKill) {
         for(k = 0; k < j; ++k) {
            this.dropItem(NetherItems.netherite_nugget, 3);
         }
      }

      j = this.rand.nextInt(2 + looting);

      for(k = 0; k < j; ++k) {
         this.dropItem(Items.ender_pearl, 3);
      }

   }

   public boolean interact(EntityPlayer player) {
      return false;
   }

   protected void dropRareDrop(int i) {
      this.dropItem(NetherItems.netherite_nugget, 1);
   }

   protected void addRandomArmor() {
      this.setCurrentItemOrArmor(0, new ItemStack(NetherItems.scythe));
   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData idata) {
      idata = super.onSpawnWithEgg(idata);
      int baby = this.rand.nextInt(20);
      this.addRandomArmor();
      this.enchantEquipment();
      if (baby >= 18) {
         this.setChild(true);
      }

      this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));
      if (this.getEquipmentInSlot(4) == null) {
         Calendar calendar = this.worldObj.getCurrentDate();
         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
            this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
            this.equipmentDropChances[4] = 0.0F;
         }
      }

      return idata;
   }

   public void setCurrentBlock(Block setBlock) {
      this.dataWatcher.updateObject(16, (byte)(Block.getIdFromBlock(setBlock) & 255));
   }

   public Block getCurrentBlock() {
      return Block.getBlockById(this.dataWatcher.getWatchableObjectByte(16));
   }

   public void setCarryingData(int p_70817_1_) {
      this.dataWatcher.updateObject(17, (byte)(p_70817_1_ & 255));
   }

   public int getCarryingData() {
      return this.dataWatcher.getWatchableObjectByte(17);
   }

   public boolean attackEntityFrom(DamageSource source, float dmg) {
      if (this.isEntityInvulnerable()) {
         return false;
      } else {
         this.setScreaming(true);
         this.setAlpha(0.4F);
         if (source instanceof EntityDamageSource && source.getEntity() instanceof EntityPlayer) {
            this.isAggressive = true;
         }

         if (source instanceof EntityDamageSourceIndirect) {
            this.isAggressive = false;

            for(int i = 0; i < 64; ++i) {
               if (this.teleportRandomly()) {
                  return true;
               }
            }

            return super.attackEntityFrom(source, dmg);
         } else {
            return super.attackEntityFrom(source, dmg);
         }
      }
   }

   public boolean isScreaming() {
      return this.dataWatcher.getWatchableObjectByte(18) > 0;
   }

   public void setScreaming(boolean screaming) {
      this.dataWatcher.updateObject(18, (byte)(screaming ? 1 : 0));
   }

   public float getAlpha() {
      return this.dataWatcher.getWatchableObjectFloat(19);
   }

   public void setAlpha(float f) {
      this.dataWatcher.updateObject(19, f);
   }

   public static void setCarriable(Block block, boolean canCarry) {
      if (carriable == null) {
         carriable = new IdentityHashMap(4096);
      }

      carriable.put(block, canCarry);
   }

   public static boolean getCarriable(Block block) {
      Boolean ret = (Boolean)carriable.get(block);
      return ret != null ? ret : false;
   }

   public boolean attackEntityAsMob(Entity target) {
      if (super.attackEntityAsMob(target)) {
         if (target instanceof EntityLivingBase) {
            byte b0 = 0;
            if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL) {
               b0 = 7;
            } else if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
               b0 = 15;
            }

            if (b0 > 0) {
               ((EntityLivingBase)target).addPotionEffect(new PotionEffect(Potion.confusion.id, b0 * 38, 0));
            }
         }

         return true;
      } else {
         return false;
      }
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
      return false;
   }

   public boolean AcidRes() {
      return false;
   }

   public boolean SpikeRes() {
      return true;
   }

   static {
      attackingSpeedBoostModifier = (new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 50.0D, 0)).setSaved(false);
      babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
      babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5D, 1);
      carriableBlocks = new boolean[256];
      carriableBlocks[Block.getIdFromBlock(Blocks.netherrack)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.soul_sand)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.nether_brick)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.nether_brick_fence)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.nether_brick_stairs)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.obsidian)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.brown_mushroom)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.red_mushroom)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.tnt)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.glowstone)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.quartz_ore)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.quartz_block)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.quartz_stairs)] = true;

      for(int x = 0; x < carriableBlocks.length; ++x) {
         if (carriableBlocks[x]) {
            setCarriable(Block.getBlockById(x), true);
         }
      }

   }
}
