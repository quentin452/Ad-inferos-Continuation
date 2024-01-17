package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.init.NetherBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSoulGlass extends BlockBreakable {
   private final boolean isOn;

   public BlockSoulGlass(Material material, boolean var, boolean isOn) {
      super("dextersnether:soul_glass", material, var);
      this.isOn = isOn;
      this.setCreativeTab((CreativeTabs)null);
   }

   public int quantityDropped(Random rand) {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   public int getRenderBlockPass() {
      return 1;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
      return this.isOn ? null : AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
   }

   public void onBlockAdded(World world, int x, int y, int z) {
      if (!world.isRemote) {
         if (this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.scheduleBlockUpdate(x, y, z, this, 4);
         } else if (!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.setBlock(x, y, z, NetherBlocks.soul_glass_ghost, 0, 2);
         }
      }

   }

   public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
      if (!world.isRemote) {
         if (this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.scheduleBlockUpdate(x, y, z, this, 4);
         } else if (!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.setBlock(x, y, z, NetherBlocks.soul_glass_ghost, 0, 2);
         }
      }

   }

   public void updateTick(World world, int x, int y, int z, Random rand) {
      if (!world.isRemote && this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
         world.setBlock(x, y, z, NetherBlocks.soul_glass, 0, 2);
      }

   }

   public Item getItemDropped(int var, Random rand, int var2) {
      return null;
   }

   @SideOnly(Side.CLIENT)
   public Item getItem(World world, int x, int y, int z) {
      return Item.getItemFromBlock(NetherBlocks.soul_glass);
   }

   protected ItemStack createStackedBlock(int var) {
      return new ItemStack(NetherBlocks.soul_glass);
   }
}
