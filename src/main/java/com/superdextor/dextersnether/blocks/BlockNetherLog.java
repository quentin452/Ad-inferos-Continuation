package com.superdextor.dextersnether.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockNetherLog extends BlockLog {
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister icon) {
      this.field_150167_a = new IIcon[1];
      this.field_150166_b = new IIcon[1];

      for(int i = 0; i < this.field_150167_a.length; ++i) {
         this.field_150167_a[i] = icon.registerIcon(this.getTextureName());
         this.field_150166_b[i] = icon.registerIcon(this.getTextureName() + "_top");
      }

   }

   public MapColor getMapColor(int i) {
      return MapColor.redColor;
   }
}
