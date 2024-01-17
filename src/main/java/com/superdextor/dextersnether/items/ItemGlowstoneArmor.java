package com.superdextor.dextersnether.items;

import com.superdextor.dextersnether.world.WorldProviderAbyss;
import com.superdextor.thinkbigcore.items.ItemCustomArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGlowstoneArmor extends ItemCustomArmor {
   public ItemGlowstoneArmor(ArmorMaterial material, int renderIndex, int armorType) {
      super(material, renderIndex, armorType, "dextersnether:textures/models/armor/glowstone", new PotionEffect(Potion.nightVision.id, 14, 0));
   }

   public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
      if (!world.isRemote && !player.isPotionActive(Potion.blindness) && !(world.provider instanceof WorldProviderAbyss)) {
         super.onArmorTick(world, player, armor);
      }

   }
}
