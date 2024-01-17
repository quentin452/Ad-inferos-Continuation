package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.entity.NetherMob;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDarkSand extends BlockBreakable {
   public BlockDarkSand() {
      super("dextersnether:dark_sand", Material.sand, true);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
      return null;
   }

   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entityIn) {
      boolean flag = true;
      if (entityIn instanceof NetherMob) {
         NetherMob nethermob = (NetherMob)entityIn;
         if (nethermob.DarkfireRes()) {
            flag = false;
         }
      }

      if (flag) {
         if (entityIn instanceof EntityLivingBase) {
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(Potion.blindness.id, 50, 0));
         }

         if (!(entityIn instanceof EntityItem) && entityIn.ticksExisted % 40 == 0) {
            entityIn.attackEntityFrom(DamageSource.magic, 1.0F);
         }
      }

      entityIn.motionX *= 0.4D;
      entityIn.motionZ *= 0.4D;
      entityIn.motionY = -0.01D;
   }

   public MapColor getMapColor(int color) {
      return MapColor.grayColor;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_) {
      return false;
   }

   public boolean isNormalCube() {
      return false;
   }

   public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
      return false;
   }
}
