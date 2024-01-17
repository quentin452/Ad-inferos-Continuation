package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.worldgen.WorldGenBigNetherMushroom;
import java.util.Random;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MapColor;
import net.minecraft.world.World;

public class BlockNetherMushroom extends BlockMushroom implements IGrowable {
   public boolean func_149884_c(World world, int x, int y, int z, Random random) {
      int l = world.getBlockMetadata(x, y, z);
      world.setBlockToAir(x, y, z);
      WorldGenBigNetherMushroom worldgenbigmushroom = new WorldGenBigNetherMushroom();
      if (worldgenbigmushroom != null && worldgenbigmushroom.generate(world, random, x, y, z)) {
         return true;
      } else {
         world.setBlock(x, y, z, this, l, 3);
         return false;
      }
   }

   public MapColor getMapColor(int i) {
      return MapColor.purpleColor;
   }
}
