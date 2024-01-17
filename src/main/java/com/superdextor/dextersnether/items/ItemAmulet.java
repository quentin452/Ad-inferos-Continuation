package com.superdextor.dextersnether.items;

import com.google.common.collect.Maps;
import com.superdextor.dextersnether.NetherConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemAmulet extends Item {
   public ItemAmulet() {
      this.setMaxStackSize(1);
      this.setHasSubtypes(true);
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      ItemAmulet.Type[] afishtype = ItemAmulet.Type.values();
      int i = afishtype.length;

      for(int j = 0; j < i; ++j) {
         ItemAmulet.Type fishtype = afishtype[j];
         fishtype.getIcon(icon);
      }

   }

   public String getUnlocalizedName(ItemStack stack) {
      ItemAmulet.Type type = ItemAmulet.Type.byItemStack(stack);
      return this.getUnlocalizedName() + "." + type.getUnlocalizedName();
   }

   @SideOnly(Side.CLIENT)
   public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
      ItemAmulet.Type[] aType = ItemAmulet.Type.values();
      int i = aType.length;

      for(int j = 0; j < i; ++j) {
         ItemAmulet.Type type = aType[j];
         subItems.add(new ItemStack(this, 1, type.getMetadata()));
      }

   }

   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      if (NetherConfig.amuletEffects) {
         switch(stack.getItemDamage()) {
         case 0:
            if (entityIn instanceof EntityLivingBase && entityIn.isBurning()) {
               ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10, 0));
               entityIn.extinguish();
            }
            break;
         case 1:
            if (entityIn instanceof EntityLivingBase) {
               ((EntityLivingBase)entityIn).removePotionEffect(Potion.wither.id);
            }
         case 2:
         default:
            break;
         case 3:
            if (entityIn instanceof EntityLivingBase) {
               EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
               if (entitylivingbase.getHealth() <= 4.0F && !entitylivingbase.isPotionActive(Potion.regeneration.id)) {
                  entitylivingbase.addPotionEffect(new PotionEffect(Potion.regeneration.id, 149, 0));
               }
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIconFromDamage(int meta) {
      ItemAmulet.Type fishtype = ItemAmulet.Type.byMetadata(meta);
      return fishtype.getIcon();
   }

   public static enum Type {
      FIRE(0, "fire"),
      WITHER(1, "wither"),
      FALL(2, "fall"),
      HEAL(3, "heal"),
      ACID(4, "acid");

      private static final Map META_LOOKUP = Maps.newHashMap();
      private final int meta;
      private final String name;
      @SideOnly(Side.CLIENT)
      private IIcon ItemIcon;

      private Type(int meta, String name) {
         this.meta = meta;
         this.name = name;
      }

      public int getMetadata() {
         return this.meta;
      }

      public String getUnlocalizedName() {
         return this.name;
      }

      @SideOnly(Side.CLIENT)
      public void getIcon(IIconRegister icon) {
         this.ItemIcon = icon.registerIcon("dextersnether:amulet_" + this.name);
      }

      @SideOnly(Side.CLIENT)
      public IIcon getIcon() {
         return this.ItemIcon;
      }

      public static ItemAmulet.Type byMetadata(int p_150974_0_) {
         ItemAmulet.Type Type = (ItemAmulet.Type)META_LOOKUP.get(p_150974_0_);
         return Type == null ? FIRE : Type;
      }

      public static ItemAmulet.Type byItemStack(ItemStack stack) {
         return stack.getItem() instanceof ItemAmulet ? byMetadata(stack.getItemDamage()) : FIRE;
      }

      static {
         ItemAmulet.Type[] var0 = values();
         int var1 = var0.length;

         for(int var2 = 0; var2 < var1; ++var2) {
            ItemAmulet.Type var3 = var0[var2];
            META_LOOKUP.put(var3.getMetadata(), var3);
         }

      }
   }
}
