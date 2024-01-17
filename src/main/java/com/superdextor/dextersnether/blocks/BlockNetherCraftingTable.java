package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.DextersNether;
import com.superdextor.dextersnether.init.NetherBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockNetherCraftingTable extends BlockWorkbench {
   @SideOnly(Side.CLIENT)
   private IIcon field_150035_a;
   @SideOnly(Side.CLIENT)
   private IIcon field_150034_b;

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return p_149691_1_ == 1 ? this.field_150035_a : (p_149691_1_ == 0 ? NetherBlocks.nether_planks.getBlockTextureFromSide(p_149691_1_) : (p_149691_1_ != 2 && p_149691_1_ != 4 ? this.blockIcon : this.field_150034_b));
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister p_149651_1_) {
      this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
      this.field_150035_a = p_149651_1_.registerIcon(this.getTextureName() + "_top");
      this.field_150034_b = p_149651_1_.registerIcon(this.getTextureName() + "_front");
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      if (world.isRemote) {
         return true;
      } else {
         player.openGui(DextersNether.modInstance, 1, world, x, y, z);
         return true;
      }
   }
}
