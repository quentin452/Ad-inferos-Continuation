package com.superdextor.dextersnether;

import com.superdextor.dextersnether.init.NetherItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DextersNetherTab extends CreativeTabs {
   public DextersNetherTab(String label) {
      super(label);
      this.setBackgroundImageName("dextersnether.png");
   }

   public Item getTabIconItem() {
      return NetherItems.scythe;
   }
}
