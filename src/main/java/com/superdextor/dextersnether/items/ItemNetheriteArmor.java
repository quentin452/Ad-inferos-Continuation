package com.superdextor.dextersnether.items;

import com.superdextor.thinkbigcore.items.ItemCustomArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemNetheriteArmor extends ItemCustomArmor {
   public ItemNetheriteArmor(ArmorMaterial material, int renderIndex, int armorType) {
      super(material, renderIndex, armorType, "dextersnether:textures/models/armor/netherite");
   }

   public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
      player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 10, 1));
      player.removePotionEffect(Potion.blindness.id);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, EntityPlayer playerIn, List toolTip, boolean advanced) {
      toolTip.add(" ");
      toolTip.add("On Equip:");
      toolTip.add("Speed II");
      toolTip.add("Blindness Immunity");
      toolTip.add(" ");
      super.addInformation(stack, playerIn, toolTip, advanced);
   }
}
