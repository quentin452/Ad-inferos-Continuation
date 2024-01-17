package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockNetherLeaves extends BlockLeaves {
   public static final String[] field_150133_O = new String[]{"nether_leaves"};

   protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_, int p_150124_6_) {
      if ((p_150124_5_ & 3) == 0 && p_150124_1_.rand.nextInt(p_150124_6_) == 0) {
         this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(NetherItems.wither_dust, 1, 0));
      }

   }

   public int damageDropped(int p_149692_1_) {
      return super.damageDropped(p_149692_1_) + 4;
   }

   public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_) {
      return p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_) & 3;
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return this.blockIcon;
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister p_149651_1_) {
      this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
   }

   public String[] func_150125_e() {
      return field_150133_O;
   }

   @SideOnly(Side.CLIENT)
   public int getBlockColor() {
      int l = 80000;
      int i1 = 80000;
      int j1 = 80000;
      return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
   }

   @SideOnly(Side.CLIENT)
   public int getRenderColor(int p_149741_1_) {
      int l = 80000;
      int i1 = 80000;
      int j1 = 80000;
      return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
   }

   @SideOnly(Side.CLIENT)
   public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
      int l = 80000;
      int i1 = 80000;
      int j1 = 80000;
      return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
   }

   public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
      return Item.getItemFromBlock(NetherBlocks.nether_sapling);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public MapColor getMapColor(int p_149728_1_) {
      return MapColor.grayColor;
   }
}
