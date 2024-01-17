package com.superdextor.dextersnether.inventory;

import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class NetherFuels implements IFuelHandler {
   public int getBurnTime(ItemStack stack) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
            Block block = Block.getBlockFromItem(item);
            if (block == NetherBlocks.nether_sapling) {
               return 150;
            }
         }

         if (item == NetherItems.golden_bucket_lava) {
            return 20000;
         } else {
            return item == NetherItems.infernal_rod ? 7600 : 0;
         }
      }
   }
}
