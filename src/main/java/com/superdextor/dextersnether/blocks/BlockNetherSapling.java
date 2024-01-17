package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.worldgen.WorldGenNetherTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockNetherSapling extends BlockBush implements IGrowable {
   public static final String[] field_149882_a = new String[]{"nether"};
   private static final IIcon[] field_149881_b;

   public BlockNetherSapling() {
      float f = 0.4F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
      this.setStepSound(soundTypeGrass);
   }

   protected boolean canPlaceBlockOn(Block block) {
      return block == Blocks.soul_sand || block == Blocks.netherrack;
   }

   public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
      if (!p_149674_1_.isRemote) {
         super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
         if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9 && p_149674_5_.nextInt(7) == 0) {
            this.func_149879_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      p_149691_2_ &= 7;
      return field_149881_b[MathHelper.clamp_int(p_149691_2_, 0, 5)];
   }

   public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_) {
      int l = p_149879_1_.getBlockMetadata(p_149879_2_, p_149879_3_, p_149879_4_);
      if ((l & 8) == 0) {
         p_149879_1_.setBlockMetadataWithNotify(p_149879_2_, p_149879_3_, p_149879_4_, l | 8, 4);
      } else {
         this.func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
      }

   }

   public void func_149878_d(World world, int var1, int var2, int var3, Random random) {
      if (TerrainGen.saplingGrowTree(world, random, var1, var2, var3)) {
         int l = world.getBlockMetadata(var1, var2, var3) & 7;
         Object object = new WorldGenNetherTree(true, true);
         int i1 = 0;
         int j1 = 0;
         boolean flag = false;
         Block block = Blocks.air;
         if (flag) {
            world.setBlock(var1 + i1, var2, var3 + j1, block, 0, 4);
            world.setBlock(var1 + i1 + 1, var2, var3 + j1, block, 0, 4);
            world.setBlock(var1 + i1, var2, var3 + j1 + 1, block, 0, 4);
            world.setBlock(var1 + i1 + 1, var2, var3 + j1 + 1, block, 0, 4);
         } else {
            world.setBlock(var1, var2, var3, block, 0, 4);
         }

         if (!((WorldGenerator)object).generate(world, random, var1 + i1, var2, var3 + j1)) {
            if (flag) {
               world.setBlock(var1 + i1, var2, var3 + j1, this, l, 4);
               world.setBlock(var1 + i1 + 1, var2, var3 + j1, this, l, 4);
               world.setBlock(var1 + i1, var2, var3 + j1 + 1, this, l, 4);
               world.setBlock(var1 + i1 + 1, var2, var3 + j1 + 1, this, l, 4);
            } else {
               world.setBlock(var1, var2, var3, this, l, 4);
            }
         }

      }
   }

   public boolean func_149880_a(World p_149880_1_, int p_149880_2_, int p_149880_3_, int p_149880_4_, int p_149880_5_) {
      return p_149880_1_.getBlock(p_149880_2_, p_149880_3_, p_149880_4_) == this && (p_149880_1_.getBlockMetadata(p_149880_2_, p_149880_3_, p_149880_4_) & 7) == p_149880_5_;
   }

   public int damageDropped(int p_149692_1_) {
      return MathHelper.clamp_int(p_149692_1_ & 7, 0, 5);
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister p_149651_1_) {
      for(int i = 0; i < field_149881_b.length; ++i) {
         field_149881_b[i] = p_149651_1_.registerIcon(this.getTextureName());
      }

   }

   public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
      return true;
   }

   public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
      return (double)p_149852_1_.rand.nextFloat() < 0.45D;
   }

   public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
      this.func_149879_c(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
   }

   public MapColor getMapColor(int p_149728_1_) {
      return MapColor.grayColor;
   }

   static {
      field_149881_b = new IIcon[field_149882_a.length];
   }
}
