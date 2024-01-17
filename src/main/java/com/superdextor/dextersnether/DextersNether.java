package com.superdextor.dextersnether;

import com.superdextor.dextersnether.enchantments.NetherEnchantments;
import com.superdextor.dextersnether.entity.NetherEntitys;
import com.superdextor.dextersnether.event.NetherAchievementEvent;
import com.superdextor.dextersnether.event.NetherEvents;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.dextersnether.inventory.NetherFuels;
import com.superdextor.dextersnether.inventory.NetherGuiHandler;
import com.superdextor.dextersnether.inventory.NetherRecipes;
import com.superdextor.dextersnether.proxy.CommonProxy;
import com.superdextor.dextersnether.world.NetherHooks;
import com.superdextor.dextersnether.world.WorldProviderAbyss;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

@Mod(
   modid = "dextersnether",
   name = "Ad Inferos",
   version = "1.1",
   acceptedMinecraftVersions = "[1.7.10]",
   dependencies = "required-after:ThinkBigCore@[1.4,]"
)
public class DextersNether {
   @SidedProxy(
      clientSide = "com.superdextor.dextersnether.proxy.ClientProxy",
      serverSide = "com.superdextor.dextersnether.proxy.CommonProxy"
   )
   public static CommonProxy proxy;
   public static final DextersNetherTab tabDextersNether = new DextersNetherTab("tabdextersnether");
   @Instance("dextersnether")
   public static DextersNether modInstance;

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      NetherConfig.register(new File(event.getModConfigurationDirectory(), "Ad Inferos.cfg"));
      NetherBlocks.init();
      NetherBlocks.register();
      NetherItems.register();
      NetherRecipes.register();
      NetherEntitys.entitys();
      NetherHooks.Init();
      GameRegistry.registerWorldGenerator(new NetherGeneration(), 2);
      DimensionManager.registerProviderType(NetherConfig.dimensionID, WorldProviderAbyss.class, false);
      DimensionManager.registerDimension(NetherConfig.dimensionID, NetherConfig.dimensionID);
      NetherAchievements.register();
      NetherEnchantments.register();
      proxy.preInit();
   }

   @EventHandler
   public void Init(FMLInitializationEvent event) {
      FMLCommonHandler.instance().bus().register(new NetherAchievementEvent());
      NetworkRegistry.INSTANCE.registerGuiHandler(modInstance, new NetherGuiHandler());
      MinecraftForge.EVENT_BUS.register(new NetherEvents());
      if (NetherConfig.genHellBiomes) {
         BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(NetherGeneration.nether, 20));
      }

      if (NetherConfig.smelting) {
         GameRegistry.registerFuelHandler(new NetherFuels());
      }

      proxy.init();
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      proxy.postInit();
   }
}
