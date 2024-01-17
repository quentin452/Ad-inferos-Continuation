package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.init.NetherItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockDarkstone extends Block {
   public BlockDarkstone() {
      super(Material.rock);
      this.setHardness(3.0F);
      this.setResistance(40.0F);
      this.setStepSound(soundTypeMetal);
      this.setHarvestLevel("pickaxe", 2);
   }

   public Item getItemDropped(int par1, Random rand, int par2) {
      return NetherItems.wither_dust;
   }

   public int quantityDropped(Random rand) {
      return rand.nextFloat() < 0.1F ? 1 : 0;
   }

   public MapColor getMapColor(int i) {
      return MapColor.blackColor;
   }
}
