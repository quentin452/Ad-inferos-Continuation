package com.superdextor.dextersnether.worldgen;

import com.superdextor.dextersnether.init.NetherBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSpikes extends WorldGenerator {
    public boolean generate(World world, Random random, int x, int y, int z) {
        int spawnAttempts = 144;

        for (int l = 0; l < spawnAttempts; ++l) {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);

            if (world.blockExists(i1, j1, k1) &&
                world.isAirBlock(i1, j1, k1) &&
                world.getBlock(i1, j1 - 1, k1) == Blocks.netherrack) {

                world.setBlock(i1, j1, k1, NetherBlocks.dark_fire, 0, 2);
            }
        }

        return true;
    }
}
