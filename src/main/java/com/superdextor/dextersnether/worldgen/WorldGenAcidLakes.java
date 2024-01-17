package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.init.NetherBlocks;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAcidLakes extends WorldGenerator {
    public boolean generate(World world, Random random, int x, int y, int z) {
        x -= 8;
        z -= 8;

        if (!world.blockExists(x, y, z)) {
            return false;
        }

        for (; y > 5 && world.blockExists(x, y, z) && world.isAirBlock(x, y, z); --y) {
        }

        if (y <= 4) {
            return false;
        } else {
            y -= 4;
            boolean[] aboolean = new boolean[2048];
            int l = random.nextInt(4) + 4;

            for (int i1 = 0; i1 < l; ++i1) {
                double d0 = random.nextDouble() * 6.0D + 3.0D;
                double d1 = random.nextDouble() * 4.0D + 2.0D;
                double d2 = random.nextDouble() * 6.0D + 3.0D;
                double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int k1 = 1; k1 < 15; ++k1) {
                    for (int l1 = 1; l1 < 15; ++l1) {
                        for (int i2 = 1; i2 < 7; ++i2) {
                            double d6 = (k1 - d3) / (d0 / 2.0D);
                            double d7 = (i2 - d4) / (d1 / 2.0D);
                            double d8 = (l1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D) {
                                aboolean[(k1 * 16 + l1) * 8 + i2] = true;
                            }
                        }
                    }
                }
            }

            for (int i1 = 0; i1 < 16; ++i1) {
                for (int j2 = 0; j2 < 16; ++j2) {
                    for (int j1 = 0; j1 < 8; ++j1) {
                        if (aboolean[(i1 * 16 + j2) * 8 + j1]) {
                            if (j1 >= 4) {
                                world.setBlock(x + i1, y + j1, z + j2, Blocks.air, 0, 2);
                            } else {
                                Material material = world.getBlock(x + i1, y + j1, z + j2).getMaterial();
                                if (material.isSolid() || world.getBlock(x + i1, y + j1, z + j2) == NetherBlocks.acid) {
                                    world.setBlock(x + i1, y + j1, z + j2, NetherBlocks.acid, 0, 2);
                                }
                            }
                        }
                    }
                }
            }

            for (int i1 = 0; i1 < 16; ++i1) {
                for (int j2 = 0; j2 < 16; ++j2) {
                    for (int j1 = 4; j1 < 8; ++j1) {
                        if (aboolean[(i1 * 16 + j2) * 8 + j1] && world.getBlock(x + i1, y + j1 - 1, z + j2) == Blocks.netherrack && world.getSavedLightValue(EnumSkyBlock.Sky, x + i1, y + j1, z + j2) > 0) {
                            BiomeGenBase biomegenbase = world.getBiomeGenForCoords(x + i1, z + j2);
                            if (biomegenbase.topBlock == Blocks.soul_sand) {
                                world.setBlock(x + i1, y + j1 - 1, z + j2, Blocks.soul_sand, 0, 2);
                            } else {
                                world.setBlock(x + i1, y + j1 - 1, z + j2, Blocks.netherrack, 0, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
