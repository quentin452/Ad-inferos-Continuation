package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.init.NetherItems;
import java.util.Random;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockDimstone extends BlockGlowstone implements AltarBlock {
   public BlockDimstone(Material material) {
      super(material);
      this.setLightLevel(0.0F);
   }

   public Item getItemDropped(int i, Random rand, int i2) {
      return NetherItems.dimstone_dust;
   }

   public MapColor getMapColor(int i) {
      return MapColor.blackColor;
   }

   public int AltarPower() {
      return 1;
   }
}
