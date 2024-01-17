package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.init.NetherBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHugeNetherMushroom extends Block {
   private static final String[] field_149793_a = new String[]{"outside", "outside"};
   private final int field_149792_b = 0;
   @SideOnly(Side.CLIENT)
   private IIcon[] field_149794_M;
   @SideOnly(Side.CLIENT)
   private IIcon field_149795_N;
   @SideOnly(Side.CLIENT)
   private IIcon field_149796_O;

   public BlockHugeNetherMushroom(Material material) {
      super(material);
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      IIcon var3;
      if (meta == 10 && side > 1) {
         var3 = this.field_149795_N;
      } else {
         IIcon[] var10000;
         if (meta >= 1 && meta <= 9 && side == 1) {
            var10000 = this.field_149794_M;
            this.getClass();
            var3 = var10000[0];
         } else if (meta >= 1 && meta <= 3 && side == 2) {
            var10000 = this.field_149794_M;
            this.getClass();
            var3 = var10000[0];
         } else if (meta >= 7 && meta <= 9 && side == 3) {
            var10000 = this.field_149794_M;
            this.getClass();
            var3 = var10000[0];
         } else if ((meta == 1 || meta == 4 || meta == 7) && side == 4) {
            var10000 = this.field_149794_M;
            this.getClass();
            var3 = var10000[0];
         } else if ((meta == 3 || meta == 6 || meta == 9) && side == 5) {
            var10000 = this.field_149794_M;
            this.getClass();
            var3 = var10000[0];
         } else if (meta == 14) {
            var10000 = this.field_149794_M;
            this.getClass();
            var3 = var10000[0];
         } else {
            var3 = meta == 15 ? this.field_149795_N : this.field_149796_O;
         }
      }

      return var3;
   }

   public int quantityDropped(Random random) {
      int i = random.nextInt(10) - 7;
      if (i < 0) {
         i = 0;
      }

      return i;
   }

   public Item getItemDropped(int size, Random random, int meta) {
      return Item.getItemById(Block.getIdFromBlock(NetherBlocks.nether_mushroom));
   }

   @SideOnly(Side.CLIENT)
   public Item getItem(World world, int x, int y, int z) {
      return Item.getItemById(Block.getIdFromBlock(NetherBlocks.nether_mushroom));
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister icon) {
      this.field_149794_M = new IIcon[field_149793_a.length];

      for(int i = 0; i < this.field_149794_M.length; ++i) {
         this.field_149794_M[i] = icon.registerIcon(this.getTextureName() + "_" + field_149793_a[i]);
      }

      this.field_149796_O = icon.registerIcon(this.getTextureName() + "_" + "inside");
      this.field_149795_N = icon.registerIcon(this.getTextureName() + "_" + "stem");
   }

   public MapColor getMapColor(int i) {
      return MapColor.purpleColor;
   }
}
