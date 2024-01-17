package com.superdextor.dextersnether.items;

import com.superdextor.dextersnether.NetherAchievements;
import com.superdextor.dextersnether.init.NetherBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWitherDust extends Item {
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int var, float varx, float vary, float varz) {
      if (var == 0) {
         --y;
      }

      if (var == 1) {
         ++y;
      }

      if (var == 2) {
         --z;
      }

      if (var == 3) {
         ++z;
      }

      if (var == 4) {
         --x;
      }

      if (var == 5) {
         ++x;
      }

      if (!player.canPlayerEdit(x, y, z, var, stack)) {
         return false;
      } else {
         if (world.isAirBlock(x, y, z)) {
            if (world.provider.dimensionId == -1 && world.getBlock(x, y - 1, z) == NetherBlocks.wither_block && world.getBlock(x + 1, y - 1, z) == Blocks.gold_block && world.getBlock(x - 1, y - 1, z) == Blocks.gold_block && world.getBlock(x, y - 1, z + 1) == Blocks.gold_block && world.getBlock(x, y - 1, z - 1) == Blocks.gold_block && world.getBlock(x + 1, y - 1, z + 1) == Blocks.obsidian && world.getBlock(x + 1, y - 1, z - 1) == Blocks.obsidian && world.getBlock(x - 1, y - 1, z - 1) == Blocks.obsidian && world.getBlock(x - 1, y - 1, z + 1) == Blocks.obsidian && world.getBlock(x + 2, y, z) == Blocks.fire && world.getBlock(x - 2, y, z) == Blocks.fire && world.getBlock(x, y, z + 2) == Blocks.fire && world.getBlock(x, y, z - 2) == Blocks.fire && world.getBlock(x + 2, y, z + 1) == Blocks.fire && world.getBlock(x + 2, y, z - 1) == Blocks.fire && world.getBlock(x - 2, y, z + 1) == Blocks.fire && world.getBlock(x - 2, y, z - 1) == Blocks.fire && world.getBlock(x + 1, y, z + 2) == Blocks.fire && world.getBlock(x - 1, y, z + 2) == Blocks.fire && world.getBlock(x + 1, y, z - 2) == Blocks.fire && world.getBlock(x - 1, y, z - 2) == Blocks.fire) {
               player.triggerAchievement(NetherAchievements.summor);
            }

            if (player.canPlayerEdit(x, y, z, var, stack) && world.isAirBlock(x, y, z) && NetherBlocks.dark_fire.canPlaceBlockAt(world, x, y, z)) {
               world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "mob.wither.shoot", 1.4F, itemRand.nextFloat() * 0.4F + 0.8F);
               world.setBlock(x, y, z, NetherBlocks.dark_fire);
            }
         }

         if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
         }

         return true;
      }
   }
}
