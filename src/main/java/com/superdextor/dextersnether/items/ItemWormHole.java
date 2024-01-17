package com.superdextor.dextersnether.items;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.world.TeleporterWormHole;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class ItemWormHole extends Item {
   public ItemWormHole() {
      this.setMaxDamage(200);
      this.setNoRepair();
      this.setMaxStackSize(1);
   }

   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
      if (playerIn.isSneaking() && NetherConfig.pocketTeleports) {
         if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
         }

         NBTTagCompound nbt = new NBTTagCompound();
         nbt.setInteger("dim", playerIn.dimension);
         nbt.setInteger("posX", x);
         nbt.setInteger("posY", y + 2);
         nbt.setInteger("posZ", z);
         stack.getTagCompound().setTag("coords", nbt);

         for(int k = 0; k < 15; ++k) {
            playerIn.worldObj.spawnParticle("portal", (double)nbt.getInteger("posX") + 0.5D, (double)nbt.getInteger("posY"), (double)nbt.getInteger("posZ") + 0.5D, ((double)itemRand.nextFloat() - 0.5D) * 0.5D, ((double)itemRand.nextFloat() - 0.5D) * 0.5D, ((double)itemRand.nextFloat() - 0.5D) * 0.5D);
         }

         playerIn.worldObj.playSoundEffect((double)nbt.getInteger("posX"), (double)nbt.getInteger("posY"), (double)nbt.getInteger("posZ"), "mob.endermen.portal", 1.0F, 0.4F);
      }

      return false;
   }

   public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      if (!playerIn.isSneaking() && !playerIn.isPotionActive(Potion.confusion) && NetherConfig.pocketTeleports && stack.getTagCompound() != null) {
         NBTTagCompound nbt = (NBTTagCompound)stack.getTagCompound().getTag("coords");
         int dim = nbt.getInteger("dim");
         int posX = nbt.getInteger("posX");
         int posY = nbt.getInteger("posY");
         int posZ = nbt.getInteger("posZ");
         this.teleportTo(playerIn, stack, (double)posX, (double)posY, (double)posZ, dim);
      }

      return stack;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, EntityPlayer playerIn, List toolTip, boolean advanced) {
      toolTip.add(" ");
      toolTip.add(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "WARNING:");
      toolTip.add(EnumChatFormatting.RED + "Worm Holes are very Unstable!");
      toolTip.add(EnumChatFormatting.RED + "Use With Caution");
      toolTip.add(" ");
      if (stack.getTagCompound() != null) {
         if (stack.getTagCompound().hasKey("coords")) {
            NBTTagCompound nbt = (NBTTagCompound)stack.getTagCompound().getTag("coords");
            int dim = nbt.getInteger("dim");
            double posX = (double)nbt.getInteger("posX");
            double posY = (double)nbt.getInteger("posY");
            double posZ = (double)nbt.getInteger("posZ");
            toolTip.add("Position Set. Dim: " + dim + " X: " + posX + " Y: " + posY + " Z: " + posZ);
         }
      } else {
         toolTip.add("Sneak Click Ground to set Location");
      }

   }

   @SideOnly(Side.CLIENT)
   public boolean hasEffect(ItemStack stack) {
      return true;
   }

   protected boolean teleportTo(EntityPlayer entity, ItemStack stack, double x, double y, double z, int DIM) {
      entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 120, 0));
      entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 30, 0));
      entity.attackEntityFrom(DamageSource.outOfWorld, 5.0F);
      stack.damageItem(4, entity);
      NBTTagCompound nbt = new NBTTagCompound();
      nbt.setInteger("dim", entity.dimension);
      nbt.setInteger("posX", (int)entity.posX - 1);
      nbt.setInteger("posY", (int)entity.posY + 1);
      nbt.setInteger("posZ", (int)entity.posZ - 1);
      stack.getTagCompound().setTag("coords", nbt);
      EnderTeleportEvent event = new EnderTeleportEvent(entity, x + 0.5D, y, z + 0.5D, 0.0F);
      entity.posX = event.targetX;
      entity.posY = event.targetY;
      entity.posZ = event.targetZ;
      if (entity.dimension != DIM && entity instanceof EntityPlayerMP) {
         stack.damageItem(12, entity);
         EntityPlayerMP player = (EntityPlayerMP)entity;
         MinecraftServer server = MinecraftServer.getServer();
         server.getConfigurationManager().transferPlayerToDimension(player, DIM, new TeleporterWormHole(server.worldServerForDimension(DIM)));
         entity.timeUntilPortal = 10;
      }

      boolean flag = false;
      int i = MathHelper.floor_double(entity.posX);
      int j = MathHelper.floor_double(entity.posY);
      int k = MathHelper.floor_double(entity.posZ);
      if (entity.worldObj.blockExists(i, j, k)) {
         boolean flag1 = false;

         while(!flag1 && j > 0) {
            Block block = entity.worldObj.getBlock(i, j - 1, k);
            if (block.getMaterial().blocksMovement()) {
               flag1 = true;
            } else {
               --entity.posY;
               --j;
            }
         }

         if (flag1) {
            entity.setPosition(entity.posX, entity.posY, entity.posZ);
            if (entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() && !entity.worldObj.isAnyLiquid(entity.boundingBox)) {
               flag = true;
            }
         }
      }

      if (!flag) {
         entity.setPosition(event.targetX, event.targetY, event.targetZ);
         return false;
      } else {
         short short1 = 128;

         for(int l = 0; l < short1; ++l) {
            double d6 = (double)l / ((double)short1 - 1.0D);
            float f = (itemRand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (itemRand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (itemRand.nextFloat() - 0.5F) * 0.2F;
            double d7 = event.targetX + (entity.posX - event.targetX) * d6 + (itemRand.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
            double d8 = event.targetY + (entity.posY - event.targetY) * d6 + itemRand.nextDouble() * (double)entity.height;
            double d9 = event.targetZ + (entity.posZ - event.targetZ) * d6 + (itemRand.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
            entity.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
         }

         entity.worldObj.playSoundEffect(event.targetX, event.targetY, event.targetZ, "mob.endermen.portal", 1.0F, 1.0F);
         entity.playSound("mob.endermen.portal", 1.0F, 1.0F);
         return true;
      }
   }
}
