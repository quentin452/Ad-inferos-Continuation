package com.superdextor.dextersnether.inventory;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class NetherGuiHandler implements IGuiHandler {
   public static final int craftingGui = 1;
   public static final int altarGui = 2;
   public static final int furnaceGui = 3;

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      if (ID == 1) {
         return new GuiCrafting(player.inventory, world, x, y, z);
      } else if (ID == 2) {
         return new GuiAltar(player.inventory, (TileEntityNetherAltar)world.getTileEntity(x, y, z));
      } else {
         return ID == 3 ? new GuiNetherFurnace(player.inventory, (TileEntityNetherFurnace)world.getTileEntity(x, y, z)) : null;
      }
   }

   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      if (ID == 1) {
         return new WorkbenchContainer(player.inventory, world);
      } else if (ID == 2) {
         return new ContainerNetherAltar(player.inventory, (TileEntityNetherAltar)world.getTileEntity(x, y, z));
      } else {
         return ID == 3 ? new ContainerNetherFurnace(player.inventory, (TileEntityNetherFurnace)world.getTileEntity(x, y, z)) : null;
      }
   }
}
