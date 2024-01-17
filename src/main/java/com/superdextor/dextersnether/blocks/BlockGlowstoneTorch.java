package com.superdextor.dextersnether.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.world.World;

public class BlockGlowstoneTorch extends BlockTorch {
   public BlockGlowstoneTorch() {
      this.setLightLevel(1.0F);
      this.setStepSound(soundTypeStone);
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
   }
}
