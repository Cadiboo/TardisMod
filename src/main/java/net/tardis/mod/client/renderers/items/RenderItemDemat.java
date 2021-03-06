package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.items.ModelDemat;

public class RenderItemDemat extends TileEntityItemStackRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/items/demat.png");
	Minecraft mc;
	ModelDemat model = new ModelDemat();

	public RenderItemDemat() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void renderByItem(ItemStack p_192838_1_, float partialTicks) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(0.5, -2, 0);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
