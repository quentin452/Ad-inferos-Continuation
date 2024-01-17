package com.superdextor.dextersnether.items;

import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ItemGoldenBucket extends Item {
   private int isFull;

   public ItemGoldenBucket(int block) {
      this.maxStackSize = 1;
      this.isFull = block;
   }

   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      boolean flag = this.isFull == 0;
      MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);
      if (movingobjectposition == null) {
         return stack;
      } else {
         FillBucketEvent event = new FillBucketEvent(player, stack, world, movingobjectposition);
         if (MinecraftForge.EVENT_BUS.post(event)) {
            return stack;
         } else if (event.getResult() == Result.ALLOW) {
            if (player.capabilities.isCreativeMode) {
               return stack;
            } else if (--stack.stackSize <= 0) {
               return event.result;
            } else {
               if (!player.inventory.addItemStackToInventory(event.result)) {
                  player.dropPlayerItemWithRandomChoice(event.result, false);
               }

               return stack;
            }
         } else {
            if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
               int i = movingobjectposition.blockX;
               int j = movingobjectposition.blockY;
               int k = movingobjectposition.blockZ;
               if (!world.canMineBlock(player, i, j, k)) {
                  return stack;
               }

               if (flag) {
                  if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack)) {
                     return stack;
                  }

                  Material material = world.getBlock(i, j, k).getMaterial();
                  Block block = world.getBlock(i, j, k);
                  int l = world.getBlockMetadata(i, j, k);
                  if (material == Material.water && block == Blocks.water && l == 0) {
                     world.setBlockToAir(i, j, k);
                     return this.func_150910_a(stack, player, NetherItems.golden_bucket_water);
                  }

                  if (material == Material.lava && l == 0) {
                     world.setBlockToAir(i, j, k);
                     return this.func_150910_a(stack, player, NetherItems.golden_bucket_lava);
                  }

                  if (material == Material.water && block == NetherBlocks.acid && l == 0) {
                     world.setBlockToAir(i, j, k);
                     return this.func_150910_a(stack, player, NetherItems.golden_bucket_acid);
                  }
               } else {
                  if (this.isFull == 0) {
                     return new ItemStack(NetherItems.golden_bucket_empty);
                  }

                  if (movingobjectposition.sideHit == 0) {
                     --j;
                  }

                  if (movingobjectposition.sideHit == 1) {
                     ++j;
                  }

                  if (movingobjectposition.sideHit == 2) {
                     --k;
                  }

                  if (movingobjectposition.sideHit == 3) {
                     ++k;
                  }

                  if (movingobjectposition.sideHit == 4) {
                     --i;
                  }

                  if (movingobjectposition.sideHit == 5) {
                     ++i;
                  }

                  if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack)) {
                     return stack;
                  }

                  if (this.tryPlaceContainedLiquid(world, i, j, k) && !player.capabilities.isCreativeMode) {
                     return new ItemStack(NetherItems.golden_bucket_empty);
                  }
               }
            }

            return stack;
         }
      }
   }

   private ItemStack func_150910_a(ItemStack stack, EntityPlayer player, Item item) {
      if (player.capabilities.isCreativeMode) {
         return stack;
      } else if (--stack.stackSize <= 0) {
         return new ItemStack(item);
      } else {
         if (!player.inventory.addItemStackToInventory(new ItemStack(item))) {
            player.dropPlayerItemWithRandomChoice(new ItemStack(item, 1, 0), false);
         }

         return stack;
      }
   }

   public boolean tryPlaceContainedLiquid(World world, int x, int y, int z) {
      if (this.isFull == 0) {
         return false;
      } else {
         Material material = world.getBlock(x, y, z).getMaterial();
         boolean flag = !material.isSolid();
         if (!world.isAirBlock(x, y, z) && !flag) {
            return false;
         } else {
            if (world.provider.isHellWorld && this.isFull == 2) {
               world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

               for(int l = 0; l < 8; ++l) {
                  world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
               }
            } else {
               if (!world.isRemote && flag && !material.isLiquid()) {
                  world.func_147480_a(x, y, z, true);
               }

               if (this.isFull == 1) {
                  world.setBlock(x, y, z, Blocks.flowing_lava, 0, 3);
               } else if (this.isFull == 2) {
                  world.setBlock(x, y, z, Blocks.flowing_water, 0, 3);
               } else if (this.isFull == 3) {
                  world.setBlock(x, y, z, NetherBlocks.acid, 0, 3);
               }
            }

            return true;
         }
      }
   }
}
