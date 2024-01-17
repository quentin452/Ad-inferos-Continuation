package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.entity.monster.EntityObsidianGolem;
import com.superdextor.dextersnether.entity.monster.EntityQuartzman;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockWitherBlock extends Block implements AltarBlock {
   private final int type;

   public BlockWitherBlock(Material materialIn, int type) {
      super(materialIn);
      this.setHardness(4.0F);
      this.setResistance(30.0F);
      this.setLightLevel(0.3F);
      this.setStepSound(soundTypeMetal);
      this.type = type;
   }

   public Item getItemDropped(int par1, Random rand, int par2) {
      return this.type == 1 ? NetherItems.wither_gem : NetherItems.wither_dust;
   }

   public int quantityDropped(Random rand) {
      return 9;
   }

   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
      boolean flag = true;
      if (entity instanceof NetherMob) {
         NetherMob nethermob = (NetherMob)entity;
         if (nethermob.WitherRes()) {
            flag = false;
         }
      }

      if (flag) {
         if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.wither.id, 120, this.type));
         }

         if (!(entity instanceof EntityItem)) {
            entity.attackEntityFrom(DamageSource.wither, (float)(1 + this.type));
         }
      }

   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
      float f = 0.065F;
      return AxisAlignedBB.getBoundingBox((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)((float)(p_149668_3_ + 1) - f), (double)(p_149668_4_ + 1));
   }

   public MapColor getMapColor(int p_149728_1_) {
      return MapColor.grayColor;
   }

   public int AltarPower() {
      return 4 + this.type;
   }

   public void onBlockAdded(World world, int x, int y, int z) {
      if (NetherConfig.buildObsidianGolem && world.getBlock(x, y, z) == NetherBlocks.wither_block && world.getBlock(x, y - 1, z) == Blocks.obsidian && world.getBlock(x, y - 2, z) == Blocks.obsidian) {
         EntityObsidianGolem entity;
         if (world.getBlock(x + 1, y - 1, z) == Blocks.obsidian && world.getBlock(x - 1, y - 1, z) == Blocks.obsidian) {
            if (!world.isRemote) {
               entity = new EntityObsidianGolem(world);
               entity.setPlayerCreated(true);
               entity.setLocationAndAngles((double)x + 0.5D, (double)y - 2.0D, (double)z + 0.5D, 0.0F, 0.0F);
               world.spawnEntityInWorld(entity);
            }

            world.setBlockToAir(x, y, z);
            world.setBlockToAir(x, y - 1, z);
            world.setBlockToAir(x, y - 2, z);
            world.setBlockToAir(x + 1, y - 1, z);
            world.setBlockToAir(x - 1, y - 1, z);
         } else if (world.getBlock(x, y - 1, z + 1) == Blocks.obsidian && world.getBlock(x, y - 1, z - 1) == Blocks.obsidian) {
            if (!world.isRemote) {
               entity = new EntityObsidianGolem(world);
               entity.setPlayerCreated(true);
               entity.setLocationAndAngles((double)x + 0.5D, (double)y - 2.0D, (double)z + 0.5D, 0.0F, 0.0F);
               world.spawnEntityInWorld(entity);
            }

            world.setBlockToAir(x, y, z);
            world.setBlockToAir(x, y - 1, z);
            world.setBlockToAir(x, y - 2, z);
            world.setBlockToAir(x, y - 1, z + 1);
            world.setBlockToAir(x, y - 1, z - 1);
         }
      } else if (NetherConfig.buildQuartzGolem && world.getBlock(x, y, z) == NetherBlocks.wither_block && world.getBlock(x, y - 1, z) == Blocks.quartz_block && world.getBlock(x, y - 2, z) == Blocks.quartz_block) {
         if (!world.isRemote) {
            EntityQuartzman entity = new EntityQuartzman(world);
            entity.setLocationAndAngles((double)x + 0.5D, (double)y - 2.0D, (double)z + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(entity);
         }

         world.setBlockToAir(x, y, z);
         world.setBlockToAir(x, y - 1, z);
         world.setBlockToAir(x, y - 2, z);
      }

   }
}
