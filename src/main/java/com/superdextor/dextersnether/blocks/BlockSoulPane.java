package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.init.NetherBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSoulPane extends BlockPane {
   private final String field_150100_a;
   private final boolean field_150099_b;
   private final String field_150101_M;
   @SideOnly(Side.CLIENT)
   private IIcon field_150102_N;
   private final boolean isOn;

   public BlockSoulPane(String p_i45432_1_, String p_i45432_2_, Material p_i45432_3_, boolean p_i45432_4_, boolean isOn) {
      super(p_i45432_2_, p_i45432_2_, p_i45432_3_, p_i45432_4_);
      this.field_150100_a = p_i45432_2_;
      this.field_150099_b = p_i45432_4_;
      this.field_150101_M = p_i45432_1_;
      this.isOn = isOn;
      this.setCreativeTab((CreativeTabs)null);
   }

   public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
      return !this.field_150099_b ? null : super.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
   }

   public int getRenderType() {
      return this.blockMaterial == Material.glass ? 41 : 18;
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
      return p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_) == this ? false : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
   }

   public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
      boolean flag = this.canPaneConnectTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ - 1, ForgeDirection.NORTH);
      boolean flag1 = this.canPaneConnectTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ + 1, ForgeDirection.SOUTH);
      boolean flag2 = this.canPaneConnectTo(p_149743_1_, p_149743_2_ - 1, p_149743_3_, p_149743_4_, ForgeDirection.WEST);
      boolean flag3 = this.canPaneConnectTo(p_149743_1_, p_149743_2_ + 1, p_149743_3_, p_149743_4_, ForgeDirection.EAST);
      if (flag2 && flag3 || !flag2 && !flag3 && !flag && !flag1) {
         this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
         super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
      } else if (flag2 && !flag3) {
         this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
         super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
      } else if (!flag2 && flag3) {
         this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
         super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
      }

      if (flag && flag1 || !flag2 && !flag3 && !flag && !flag1) {
         this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
         super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
      } else if (flag && !flag1) {
         this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
         super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
      } else if (!flag && flag1) {
         this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
         super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
      }

   }

   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
      float f = 0.4375F;
      float f1 = 0.5625F;
      float f2 = 0.4375F;
      float f3 = 0.5625F;
      boolean flag = this.canPaneConnectTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ - 1, ForgeDirection.NORTH);
      boolean flag1 = this.canPaneConnectTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ + 1, ForgeDirection.SOUTH);
      boolean flag2 = this.canPaneConnectTo(p_149719_1_, p_149719_2_ - 1, p_149719_3_, p_149719_4_, ForgeDirection.WEST);
      boolean flag3 = this.canPaneConnectTo(p_149719_1_, p_149719_2_ + 1, p_149719_3_, p_149719_4_, ForgeDirection.EAST);
      if (flag2 && flag3 || !flag2 && !flag3 && !flag && !flag1) {
         f = 0.0F;
         f1 = 1.0F;
      } else if (flag2 && !flag3) {
         f = 0.0F;
      } else if (!flag2 && flag3) {
         f1 = 1.0F;
      }

      if (flag && flag1 || !flag2 && !flag3 && !flag && !flag1) {
         f2 = 0.0F;
         f3 = 1.0F;
      } else if (flag && !flag1) {
         f2 = 0.0F;
      } else if (!flag && flag1) {
         f3 = 1.0F;
      }

      this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
   }

   @SideOnly(Side.CLIENT)
   public IIcon func_150097_e() {
      return this.field_150102_N;
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister p_149651_1_) {
      this.blockIcon = p_149651_1_.registerIcon(this.field_150101_M);
      this.field_150102_N = p_149651_1_.registerIcon(this.field_150100_a);
   }

   public boolean canPaneConnectTo(IBlockAccess world, int x, int y, int z, ForgeDirection dir) {
      return this.canPaneConnectToBlock(world.getBlock(x, y, z)) || world.isSideSolid(x, y, z, dir.getOpposite(), false);
   }

   @SideOnly(Side.CLIENT)
   public int getRenderBlockPass() {
      return 1;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
      return this.isOn ? null : AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
   }

   public void onBlockAdded(World world, int x, int y, int z) {
      if (!world.isRemote) {
         if (this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.scheduleBlockUpdate(x, y, z, this, 4);
         } else if (!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.setBlock(x, y, z, NetherBlocks.soul_glass_ghost_pane, 0, 2);
         }
      }

   }

   public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
      if (!world.isRemote) {
         if (this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.scheduleBlockUpdate(x, y, z, this, 4);
         } else if (!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.setBlock(x, y, z, NetherBlocks.soul_glass_ghost_pane, 0, 2);
         }
      }

   }

   public void updateTick(World world, int x, int y, int z, Random rand) {
      if (!world.isRemote && this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
         world.setBlock(x, y, z, NetherBlocks.soul_glass_pane, 0, 2);
      }

   }

   @SideOnly(Side.CLIENT)
   public Item getItem(World world, int x, int y, int z) {
      return Item.getItemFromBlock(NetherBlocks.soul_glass_pane);
   }

   protected ItemStack createStackedBlock(int var) {
      return new ItemStack(NetherBlocks.soul_glass_pane);
   }
}
