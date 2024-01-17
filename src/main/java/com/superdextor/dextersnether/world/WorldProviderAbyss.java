package com.superdextor.dextersnether.world;

import com.superdextor.dextersnether.NetherConfig;
import com.superdextor.dextersnether.NetherGeneration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderAbyss extends WorldProvider {
   private static final String __OBFID = "CL_00000387";

   public void registerWorldChunkManager() {
      this.worldChunkMgr = new WorldChunkManagerHell(NetherGeneration.abyss, 0.0F);
      this.hasNoSky = true;
      this.dimensionId = NetherConfig.dimensionID;
      this.isHellWorld = true;
   }

   @SideOnly(Side.CLIENT)
   public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
      return Vec3.createVectorHelper(0.006D, 0.0D, 0.01D);
   }

   public IChunkProvider createChunkGenerator() {
      return new ChunkProviderAbyss(this.worldObj, this.worldObj.getSeed());
   }

   public boolean isSurfaceWorld() {
      return false;
   }

   public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_) {
      return false;
   }

   public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
      return 0.5F;
   }

   public boolean canRespawnHere() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
      return true;
   }

   public String getDimensionName() {
      return "Abyss";
   }
}
