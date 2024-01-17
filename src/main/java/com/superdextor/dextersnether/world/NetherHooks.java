package com.superdextor.dextersnether.world;

import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.dextersnether.inventory.TileEntityNetherAltar;
import com.superdextor.dextersnether.inventory.TileEntityNetherFurnace;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedRandomChestContent;

public class NetherHooks {
   public static final String PIGGYCHEST = "PiggyChest";
   public static final String NETHERCHEST = "NetherChest";
   public static final WeightedRandomChestContent[] NETHERCHESTItems;
   public static final WeightedRandomChestContent[] PIGGYCHESTItems;

   public static void Init() {
      TileEntity.addMapping(TileEntityNetherAltar.class, "NetherAltar");
      TileEntity.addMapping(TileEntityNetherFurnace.class, "DextersNether_NetherFurnace");
   }

   static {
      NETHERCHESTItems = new WeightedRandomChestContent[]{new WeightedRandomChestContent(NetherItems.netherite_ingot, 0, 1, 1, 10), new WeightedRandomChestContent(NetherItems.quartz_ingot, 0, 1, 4, 10), new WeightedRandomChestContent(Items.nether_wart, 0, 1, 1, 10), new WeightedRandomChestContent(Items.rotten_flesh, 0, 1, 4, 10), new WeightedRandomChestContent(NetherItems.wither_dust, 0, 1, 4, 10), new WeightedRandomChestContent(NetherItems.obsidian_chunk, 0, 1, 4, 10), new WeightedRandomChestContent(NetherItems.golden_bucket_empty, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(NetherItems.flameball, 0, 1, 4, 10), new WeightedRandomChestContent(Items.record_11, 0, 1, 1, 10), new WeightedRandomChestContent(NetherItems.nether_disc, 0, 1, 1, 10), new WeightedRandomChestContent(Items.name_tag, 0, 1, 1, 10), new WeightedRandomChestContent(NetherItems.wither_gem, 0, 1, 1, 5), new WeightedRandomChestContent(Items.nether_star, 0, 1, 1, 1)};
      PIGGYCHESTItems = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 6), new WeightedRandomChestContent(Items.gold_nugget, 0, 5, 23, 8), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.carrot, 0, 5, 7, 10), new WeightedRandomChestContent(new ItemStack(NetherBlocks.gold_ore_nether), 3, 7, 6), new WeightedRandomChestContent(Items.flint_and_steel, 0, 1, 1, 4), new WeightedRandomChestContent(new ItemStack(NetherBlocks.nether_log), 4, 13, 9), new WeightedRandomChestContent(new ItemStack(NetherBlocks.nether_planks), 7, 34, 8), new WeightedRandomChestContent(Items.glowstone_dust, 0, 3, 7, 4), new WeightedRandomChestContent(Items.quartz, 0, 3, 7, 4), new WeightedRandomChestContent(Items.golden_pickaxe, 0, 1, 1, 6)};
   }
}
