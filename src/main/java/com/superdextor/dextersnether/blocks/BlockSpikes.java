package com.superdextor.dextersnether.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockSpikes extends Block {
   public BlockSpikes(Material material) {
      super(material);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
      this.setHardness(0.2F);
      this.setBlockTextureName("dextersnether:nether_spikes");
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
      return null;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 1;
   }

   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
      entity.attackEntityFrom(DamageSource.cactus, 3.0F);
   }

   public boolean canPlaceBlockAt(World world, int x, int y, int z) {
      return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
   }

   public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
      return rand.nextBoolean() ? Item.getItemFromBlock(Blocks.netherrack) : null;
   }

   protected boolean canSilkHarvest() {
      return true;
   }
}
