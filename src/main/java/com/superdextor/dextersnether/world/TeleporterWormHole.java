package com.superdextor.dextersnether.world;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterWormHole extends Teleporter {
   public TeleporterWormHole(WorldServer worldIn) {
      super(worldIn);
   }

   public void placeInPortal(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_) {
   }

   public boolean placeInExistingPortal(Entity p_77184_1_, double p_77184_2_, double p_77184_4_, double p_77184_6_, float p_77184_8_) {
      return true;
   }

   public boolean makePortal(Entity p_85188_1_) {
      return true;
   }

   public void removeStalePortalLocations(long p_85189_1_) {
   }
}
