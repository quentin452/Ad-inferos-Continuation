package com.superdextor.dextersnether.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGhost extends BlockBreakable {
   private final boolean IsHollow;

   public BlockGhost(Material materialIn, boolean IsHollow) {
      super("stone", materialIn, false);
      this.setStepSound(Block.soundTypeSand);
      this.setLightOpacity(0);
      this.setBlockTextureName("dextersnether:invisible");
      if (IsHollow) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      }

      this.IsHollow = IsHollow;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   public int getRenderBlockPass() {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
      return null;
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister p_149651_1_) {
      this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
   }

   public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
      return this.IsHollow;
   }
}
