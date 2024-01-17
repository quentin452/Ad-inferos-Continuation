package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.init.NetherBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBigNetherMushroom extends WorldGenerator {
   private int mushroomType = -1;

   public WorldGenBigNetherMushroom(int type) {
      super(true);
      this.mushroomType = type;
   }

   public WorldGenBigNetherMushroom() {
      super(false);
   }

    public boolean generate(World world, Random random, int x, int y, int z) {
        int l = random.nextInt(2);
        if (this.mushroomType >= 0) {
            l = this.mushroomType;
        }

        int i1 = random.nextInt(3) + 4;
        boolean flag = true;
        if (y >= 1 && y + i1 + 1 < 256) {
            for (int posY = y; posY <= y + 1 + i1; ++posY) {
                byte b0 = 3;
                if (posY <= y + 3) {
                    b0 = 0;
                }

                for (int posX = x - b0; posX <= x + b0 && flag; ++posX) {
                    for (int posZ = z - b0; posZ <= z + b0 && flag; ++posZ) {
                        if (posY >= 0 && posY < 256) {
                            if (world.blockExists(posX, posY, posZ) && !world.isAirBlock(posX, posY, posZ) &&
                                !world.getBlock(posX, posY, posZ).isLeaves(world, posX, posY, posZ) &&
                                !world.getBlock(posX, posY, posZ).isLeaves(world, posX, posY, posZ)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                Block blocposX = world.getBlock(x, y - 1, z);
                if (blocposX != Blocks.soul_sand && blocposX != Blocks.netherrack && blocposX != Blocks.mycelium) {
                    return false;
                } else {
                    int k2 = y + i1;
                    if (l == 1) {
                        k2 = y + i1 - 3;
                    }

                    for (int posX = k2; posX <= y + i1; ++posX) {
                        int posZ = 1;
                        if (posX < y + i1) {
                            ++posZ;
                        }

                        if (l == 0) {
                            posZ = 3;
                        }

                        for (int l2 = x - posZ; l2 <= x + posZ; ++l2) {
                            for (int i2 = z - posZ; i2 <= z + posZ; ++i2) {
                                int j2 = 5;
                                if (l2 == x - posZ) {
                                    --j2;
                                }

                                if (l2 == x + posZ) {
                                    ++j2;
                                }

                                if (i2 == z - posZ) {
                                    j2 -= 3;
                                }

                                if (i2 == z + posZ) {
                                    j2 += 3;
                                }

                                if (l2 == x - (posZ - 1) && i2 == z - posZ) {
                                    j2 = 1;
                                }

                                if (l2 == x - posZ && i2 == z - (posZ - 1)) {
                                    j2 = 1;
                                }

                                if (l2 == x + (posZ - 1) && i2 == z - posZ) {
                                    j2 = 3;
                                }

                                if (l2 == x + posZ && i2 == z - (posZ - 1)) {
                                    j2 = 3;
                                }

                                if (l2 == x - (posZ - 1) && i2 == z + posZ) {
                                    j2 = 7;
                                }

                                if (l2 == x - posZ && i2 == z + (posZ - 1)) {
                                    j2 = 7;
                                }

                                if (l2 == x + (posZ - 1) && i2 == z + posZ) {
                                    j2 = 9;
                                }

                                if (l2 == x + posZ && i2 == z + (posZ - 1)) {
                                    j2 = 9;
                                }

                                if (j2 == 5 && posX < y + i1) {
                                    j2 = 0;
                                }

                                if ((j2 != 0 || y >= y + i1 - 1) && world.getBlock(l2, posX, i2).canBeReplacedByLeaves(world, l2, posX, i2)) {
                                    this.setBlockAndNotifyAdequately(world, l2, posX, i2, Block.getBlockById(Block.getIdFromBlock(NetherBlocks.nether_mushroom_block)), j2);
                                }
                            }
                        }
                    }

                    for (int posX = 0; posX < i1; ++posX) {
                        if (world.getBlock(x, y + posX, z).canBeReplacedByLeaves(world, x, y + posX, z)) {
                            this.setBlockAndNotifyAdequately(world, x, y + posX, z, Block.getBlockById(Block.getIdFromBlock(NetherBlocks.nether_mushroom_block)), 10);
                        }
                    }

                    return true;
                }
            }
        } else {
            return false;
        }
    }
}
