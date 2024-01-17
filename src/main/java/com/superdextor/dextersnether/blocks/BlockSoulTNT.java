package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.entity.projectile.EntitySoulTNTPrimed;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockSoulTNT extends BlockTNT implements AltarBlock {
   public BlockSoulTNT() {
      this.setHardness(0.0F);
      this.setResistance(0.0F);
      this.setLightLevel(0.2F);
      this.setStepSound(soundTypeGrass);
   }

   public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
      if (p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_)) {
         this.onBlockDestroyedByPlayer(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, 1);
         p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
      }

   }

   public void onBlockDestroyedByExplosion(World world, int var, int var2, int var3, Explosion explosion) {
      if (!world.isRemote) {
         EntitySoulTNTPrimed entitytntprimed = new EntitySoulTNTPrimed(world, (double)((float)var + 0.5F), (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), explosion.getExplosivePlacedBy());
         entitytntprimed.fuse = world.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
         world.spawnEntityInWorld(entitytntprimed);
      }

   }

   public void func_150114_a(World p_150114_1_, int p_150114_2_, int p_150114_3_, int p_150114_4_, int p_150114_5_, EntityLivingBase p_150114_6_) {
      if (!p_150114_1_.isRemote && (p_150114_5_ & 1) == 1) {
         EntitySoulTNTPrimed entitytntprimed = new EntitySoulTNTPrimed(p_150114_1_, (double)((float)p_150114_2_ + 0.5F), (double)((float)p_150114_3_ + 0.5F), (double)((float)p_150114_4_ + 0.5F), p_150114_6_);
         entitytntprimed.fuse = p_150114_1_.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
         p_150114_1_.spawnEntityInWorld(entitytntprimed);
         p_150114_1_.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
      }

   }

   public void onEntityCollidedWithBlock(World world, int var, int var2, int var3, Entity entity) {
      if (entity instanceof Entity && !world.isRemote) {
         this.func_150114_a(world, var, var2, var3, 1, (EntityLivingBase)null);
         world.setBlockToAir(var, var2, var3);
      }

   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
      float f = 0.0625F;
      return AxisAlignedBB.getBoundingBox((double)((float)p_149668_2_ + f), (double)p_149668_3_, (double)((float)p_149668_4_ + f), (double)((float)(p_149668_2_ + 1) - f), (double)((float)(p_149668_3_ + 1) - f), (double)((float)(p_149668_4_ + 1) - f));
   }

   public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      if (p_149727_5_.getCurrentEquippedItem() != null && p_149727_5_.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
         this.func_150114_a(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, 1, p_149727_5_);
         p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);
         p_149727_5_.getCurrentEquippedItem().damageItem(1, p_149727_5_);
         return true;
      } else {
         return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
      }
   }

   public MapColor getMapColor(int p_149728_1_) {
      return MapColor.brownColor;
   }

   public int AltarPower() {
      return 4;
   }
}
