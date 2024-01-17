package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.init.NetherBlocks;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAbyssAcid extends WorldGenerator {
   private boolean flag;

   public WorldGenAbyssAcid(boolean flag) {
      this.flag = flag;
   }

   public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
      if (!p_76484_1_.getBlock(p_76484_3_, p_76484_4_ + 1, p_76484_5_).isOpaqueCube()) {
         return false;
      } else if (p_76484_1_.getBlock(p_76484_3_, p_76484_4_, p_76484_5_).getMaterial() != Material.air && !p_76484_1_.getBlock(p_76484_3_, p_76484_4_, p_76484_5_).isOpaqueCube()) {
         return false;
      } else {
         int l = 0;
         if (p_76484_1_.getBlock(p_76484_3_ - 1, p_76484_4_, p_76484_5_).isOpaqueCube()) {
            ++l;
         }

         if (p_76484_1_.getBlock(p_76484_3_ + 1, p_76484_4_, p_76484_5_).isOpaqueCube()) {
            ++l;
         }

         if (p_76484_1_.getBlock(p_76484_3_, p_76484_4_, p_76484_5_ - 1).isOpaqueCube()) {
            ++l;
         }

         if (p_76484_1_.getBlock(p_76484_3_, p_76484_4_, p_76484_5_ + 1).isOpaqueCube()) {
            ++l;
         }

         if (p_76484_1_.getBlock(p_76484_3_, p_76484_4_ - 1, p_76484_5_).isOpaqueCube()) {
            ++l;
         }

         int i1 = 0;
         if (p_76484_1_.isAirBlock(p_76484_3_ - 1, p_76484_4_, p_76484_5_)) {
            ++i1;
         }

         if (p_76484_1_.isAirBlock(p_76484_3_ + 1, p_76484_4_, p_76484_5_)) {
            ++i1;
         }

         if (p_76484_1_.isAirBlock(p_76484_3_, p_76484_4_, p_76484_5_ - 1)) {
            ++i1;
         }

         if (p_76484_1_.isAirBlock(p_76484_3_, p_76484_4_, p_76484_5_ + 1)) {
            ++i1;
         }

         if (p_76484_1_.isAirBlock(p_76484_3_, p_76484_4_ - 1, p_76484_5_)) {
            ++i1;
         }

         if (!this.flag && l == 4 && i1 == 1 || l == 5) {
            p_76484_1_.setBlock(p_76484_3_, p_76484_4_, p_76484_5_, NetherBlocks.acid, 0, 2);
            p_76484_1_.scheduledUpdatesAreImmediate = true;
            NetherBlocks.acid.updateTick(p_76484_1_, p_76484_3_, p_76484_4_, p_76484_5_, p_76484_2_);
            p_76484_1_.scheduledUpdatesAreImmediate = false;
         }

         return true;
      }
   }
}
