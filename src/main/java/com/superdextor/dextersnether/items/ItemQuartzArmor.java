package com.superdextor.dextersnether.items;

import com.superdextor.thinkbigcore.items.ItemCustomArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class ItemQuartzArmor extends ItemCustomArmor {
   public ItemQuartzArmor(ArmorMaterial material, int renderIndex, int armorType) {
      super(material, renderIndex, armorType, "dextersnether:textures/models/armor/quartz");
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, EntityPlayer playerIn, List toolTip, boolean advanced) {
      toolTip.add(" ");
      toolTip.add("On Equip:");
      toolTip.add("No Fall Damage");
      super.addInformation(stack, playerIn, toolTip, advanced);
   }
}
