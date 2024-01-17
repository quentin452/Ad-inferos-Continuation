package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockHellDoor extends BlockDoor {
   public BlockHellDoor(Material materialIn) {
      super(materialIn);
   }

   @SideOnly(Side.CLIENT)
   public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
      return this.getItem();
   }

   public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
      return (p_149650_1_ & 8) != 0 ? null : this.getItem();
   }

   private Item getItem() {
      return NetherItems.nether_door_item;
   }
}
