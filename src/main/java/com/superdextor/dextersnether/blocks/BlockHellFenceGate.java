package com.superdextor.dextersnether.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BlockHellFenceGate extends BlockFenceGate {
   private final Block block;
   private final Material material;

   public BlockHellFenceGate(Material material, Block block) {
      this.material = material;
      this.block = block;
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return this.block.getBlockTextureFromSide(p_149691_1_);
   }

   public Material getMaterial() {
      return this.material;
   }
}
