package com.superdextor.dextersnether.proxy;

import com.superdextor.dextersnether.entity.monster.EntityCurse;
import com.superdextor.dextersnether.entity.monster.EntityGlowstoneSkeleton;
import com.superdextor.dextersnether.entity.monster.EntityHerobrine;
import com.superdextor.dextersnether.entity.monster.EntityHerobrineClone;
import com.superdextor.dextersnether.entity.monster.EntityInfernalBlaze;
import com.superdextor.dextersnether.entity.monster.EntityInfernumAvis;
import com.superdextor.dextersnether.entity.monster.EntityNetherSpider;
import com.superdextor.dextersnether.entity.monster.EntityObsidainSheepmanGolem;
import com.superdextor.dextersnether.entity.monster.EntityObsidianGolem;
import com.superdextor.dextersnether.entity.monster.EntityObsidianSheepman;
import com.superdextor.dextersnether.entity.monster.EntityPhantom;
import com.superdextor.dextersnether.entity.monster.EntityQuartzman;
import com.superdextor.dextersnether.entity.monster.EntityReaper;
import com.superdextor.dextersnether.entity.monster.EntitySkeletonHorse;
import com.superdextor.dextersnether.entity.monster.EntitySummor;
import com.superdextor.dextersnether.entity.projectile.EntityFlame;
import com.superdextor.dextersnether.entity.projectile.EntityFlameball;
import com.superdextor.dextersnether.entity.projectile.EntityNetherArrow;
import com.superdextor.dextersnether.entity.projectile.EntitySoulTNTPrimed;
import com.superdextor.dextersnether.entity.projectile.EntityTeleport;
import com.superdextor.dextersnether.init.NetherItems;
import com.superdextor.dextersnether.inventory.TileEntityNetherAltar;
import com.superdextor.dextersnether.render.RenderCurse;
import com.superdextor.dextersnether.render.RenderFlameball;
import com.superdextor.dextersnether.render.RenderGlowstoneSkeleton;
import com.superdextor.dextersnether.render.RenderHerobrine;
import com.superdextor.dextersnether.render.RenderHerobrineClone;
import com.superdextor.dextersnether.render.RenderInfernalBlaze;
import com.superdextor.dextersnether.render.RenderInfernumAvis;
import com.superdextor.dextersnether.render.RenderNetherAltar;
import com.superdextor.dextersnether.render.RenderNetherArrow;
import com.superdextor.dextersnether.render.RenderNetherSpider;
import com.superdextor.dextersnether.render.RenderObsidianGolem;
import com.superdextor.dextersnether.render.RenderObsidianSheepman;
import com.superdextor.dextersnether.render.RenderObsidianSheepmanGolem;
import com.superdextor.dextersnether.render.RenderPhantom;
import com.superdextor.dextersnether.render.RenderQuartzman;
import com.superdextor.dextersnether.render.RenderReaper;
import com.superdextor.dextersnether.render.RenderSkeletonHorse;
import com.superdextor.dextersnether.render.RenderSoulTNTPrimed;
import com.superdextor.dextersnether.render.RenderSummor;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class ClientProxy extends CommonProxy {
   public void preInit() {
   }

   public void init() {
      RenderingRegistry.registerEntityRenderingHandler(EntityNetherSpider.class, new RenderNetherSpider());
      RenderingRegistry.registerEntityRenderingHandler(EntityFlameball.class, new RenderFlameball(3.0F));
      RenderingRegistry.registerEntityRenderingHandler(EntityReaper.class, new RenderReaper());
      RenderingRegistry.registerEntityRenderingHandler(EntityObsidianSheepman.class, new RenderObsidianSheepman());
      RenderingRegistry.registerEntityRenderingHandler(EntityObsidainSheepmanGolem.class, new RenderObsidianSheepmanGolem());
      RenderingRegistry.registerEntityRenderingHandler(EntityCurse.class, new RenderCurse());
      RenderingRegistry.registerEntityRenderingHandler(EntityFlame.class, new RenderSnowball(NetherItems.flame, 0));
      RenderingRegistry.registerEntityRenderingHandler(EntityGlowstoneSkeleton.class, new RenderGlowstoneSkeleton());
      RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonHorse.class, new RenderSkeletonHorse());
      RenderingRegistry.registerEntityRenderingHandler(EntityNetherArrow.class, new RenderNetherArrow());
      RenderingRegistry.registerEntityRenderingHandler(EntitySoulTNTPrimed.class, new RenderSoulTNTPrimed());
      RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class, new RenderHerobrine());
      RenderingRegistry.registerEntityRenderingHandler(EntitySummor.class, new RenderSummor());
      RenderingRegistry.registerEntityRenderingHandler(EntityObsidianGolem.class, new RenderObsidianGolem());
      RenderingRegistry.registerEntityRenderingHandler(EntityQuartzman.class, new RenderQuartzman());
      RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, new RenderPhantom());
      RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineClone.class, new RenderHerobrineClone());
      RenderingRegistry.registerEntityRenderingHandler(EntityInfernumAvis.class, new RenderInfernumAvis());
      RenderingRegistry.registerEntityRenderingHandler(EntityTeleport.class, new RenderSnowball(NetherItems.pocket_worm_hole, 0));
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNetherAltar.class, new RenderNetherAltar());
      RenderingRegistry.registerEntityRenderingHandler(EntityInfernalBlaze.class, new RenderInfernalBlaze());
   }

   public void postInit() {
   }
}
