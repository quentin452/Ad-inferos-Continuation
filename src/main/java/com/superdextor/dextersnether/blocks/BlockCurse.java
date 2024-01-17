package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.NetherDamageSource;
import com.superdextor.dextersnether.enchantments.NetherEnchantments;
import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.entity.monster.EntityCurse;
import com.superdextor.dextersnether.entity.monster.EntitySummor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCurse extends BlockBreakable implements AltarBlock {
   public BlockCurse(Material material) {
      super("dextersnether:curse_block", material, false);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
      this.setTickRandomly(true);
      this.slipperiness = 0.98F;
      this.setTickRandomly(true);
      this.setStepSound(soundTypeSand);
   }

   @SideOnly(Side.CLIENT)
   public int getRenderBlockPass() {
      return 1;
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockAccess access, int x, int y, int z, int facing) {
      return super.shouldSideBeRendered(access, x, y, z, 1 - facing);
   }

   public int getMobilityFlag() {
      return 0;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   protected int func_149804_e(World world, int x, int y, int z) {
      return world.getBlock(x, y, z).getMaterial() == this.blockMaterial ? world.getBlockMetadata(x, y, z) : -1;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean canPlaceBlockAt(World world, int x, int y, int z) {
      return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
   }

   public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
      if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)) {
         world.setBlockToAir(x, y, z);
      }

   }

   private boolean func_150090_e(World p_150090_1_, int p_150090_2_, int p_150090_3_, int p_150090_4_) {
      if (!this.canBlockStay(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_)) {
         this.dropBlockAsItem(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_, p_150090_1_.getBlockMetadata(p_150090_2_, p_150090_3_, p_150090_4_), 0);
         p_150090_1_.setBlockToAir(p_150090_2_, p_150090_3_, p_150090_4_);
         return false;
      } else {
         return true;
      }
   }

   public boolean canBlockStay(World world, int var, int var1, int var2) {
      return !world.isAirBlock(var, var1 - 1, var2);
   }

   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
      boolean flag = true;
      if (entity instanceof NetherMob) {
         NetherMob nethermob = (NetherMob)entity;
         if (nethermob.CurseRes()) {
            flag = false;
         }
      }

      if (entity instanceof EntityLivingBase) {
         ItemStack stack = EnchantmentHelper.func_92099_a(NetherEnchantments.curseProtection, (EntityLivingBase)entity);
         if (stack != null && world.rand.nextInt(1 + EnchantmentHelper.getEnchantmentLevel(NetherEnchantments.curseProtection.effectId, stack)) > 1) {
            flag = false;
         }
      }

      if (flag) {
         if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 102, 0));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.hunger.id, 102, 0));
         }

         if (world.rand.nextInt(7) == 0 && !(entity instanceof EntityItem) && !(entity instanceof EntityCurse) && !(entity instanceof EntitySummor)) {
            entity.attackEntityFrom(NetherDamageSource.curse, 1.0F);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
      double d0 = (double)((float)x + random.nextFloat());
      double d1 = (double)((float)y + 0.1F);
      double d2 = (double)((float)z + random.nextFloat());
      double d3 = 0.5D;
      double d4 = 0.0D;
      double d5 = 1.0D;
      world.spawnParticle("reddust", d0, d1, d2, d3, d4, d5);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
      return null;
   }

   public void updateTick(World world, int x, int y, int z, Random random) {
      if (world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) > 0 - this.getLightOpacity()) {
         world.setBlockToAir(x, y, z);
      }
   }

   public MapColor getMapColor(int i) {
      return MapColor.purpleColor;
   }

   public int AltarPower() {
      return 1;
   }
}
