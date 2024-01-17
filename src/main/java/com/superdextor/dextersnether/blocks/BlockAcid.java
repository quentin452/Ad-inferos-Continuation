package com.superdextor.dextersnether.blocks;

import com.superdextor.dextersnether.NetherDamageSource;
import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockAcid extends BlockFluidClassic {
   @SideOnly(Side.CLIENT)
   protected IIcon stillIcon;
   @SideOnly(Side.CLIENT)
   protected IIcon flowingIcon;

   public BlockAcid(Fluid fluid, Material material) {
      super(fluid, material);
   }

   public IIcon getIcon(int side, int meta) {
      return side != 0 && side != 1 ? this.flowingIcon : this.stillIcon;
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister register) {
      this.stillIcon = register.registerIcon(this.getTextureName() + "_still");
      this.flowingIcon = register.registerIcon(this.getTextureName() + "_flow");
   }

   public void onEntityCollidedWithBlock(World world, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity) {
      boolean flag = true;
      if (entity instanceof NetherMob) {
         NetherMob nethermob = (NetherMob)entity;
         if (nethermob.AcidRes()) {
            flag = false;
         }
      }

      if (entity instanceof EntityPlayer && ((EntityPlayer)entity).inventory.hasItem(NetherItems.amulet)) {
         EntityPlayer player = (EntityPlayer)entity;
         ItemStack stack = null;

         for(int i = 0; i < player.inventory.mainInventory.length; ++i) {
            if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == NetherItems.amulet && player.inventory.mainInventory[i].getItemDamage() == 4) {
               flag = false;
               break;
            }
         }
      }

      if (flag && entity instanceof EntityLivingBase) {
         EntityLivingBase entitylivingbase = (EntityLivingBase)entity;
         entitylivingbase.playSound("random.fizz", 1.0F, 0.8F + world.rand.nextFloat());
         entity.attackEntityFrom(NetherDamageSource.acid, 3.0F);
      }

   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
      if (random.nextBoolean() && world.isAirBlock(x, y + 1, z)) {
         double d0 = (double)((float)x + random.nextFloat());
         double d1 = (double)((float)y + 0.6F + random.nextFloat());
         double d2 = (double)((float)z + random.nextFloat());
         world.spawnParticle("reddust", d0, d1, d2, 0.5D, 0.0D, 1.0D);
      }

   }

   public MapColor getMapColor(int i) {
      return MapColor.purpleColor;
   }
}
