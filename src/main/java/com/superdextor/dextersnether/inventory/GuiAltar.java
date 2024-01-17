package com.superdextor.dextersnether.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAltar extends GuiContainer {
   private static final ResourceLocation altarGuiTextures = new ResourceLocation("dextersnether:textures/gui/nether_altar.png");
   private TileEntityNetherAltar tileNetherAltar;

   public GuiAltar(InventoryPlayer inventory, TileEntityNetherAltar tileentity) {
      super(new ContainerNetherAltar(inventory, tileentity));
      this.tileNetherAltar = tileentity;
   }

   protected void drawGuiContainerForegroundLayer(int i1, int i2) {
      String s = this.tileNetherAltar.hasCustomInventoryName() ? this.tileNetherAltar.getInventoryName() : I18n.format(this.tileNetherAltar.getInventoryName(), new Object[0]);
      this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
      this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f1, int var, int var2) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(altarGuiTextures);
      int k = (this.width - this.xSize) / 2;
      int l = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
      int i1;
      if (this.tileNetherAltar.isPowred()) {
         i1 = this.tileNetherAltar.getAltarPower() - 1;
         this.drawTexturedModalRect(k + 84, l + 15 + 12 - i1, 176, 7 - i1, 8, i1 + 1);
      }

      i1 = this.tileNetherAltar.getCookProgressScaled(16);
      this.drawTexturedModalRect(k + 71, l + 79 - i1, 176, 23 - i1, 32, i1 + 1);
   }
}
