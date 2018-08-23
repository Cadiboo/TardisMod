package net.tardis.mod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.client.EnumClothes;
import net.tardis.mod.client.models.ModelConsole;
import net.tardis.mod.client.models.ModelFirstCane;
import net.tardis.mod.client.models.ModelKey01;
import net.tardis.mod.client.models.clothing.ModelVortexM;
import net.tardis.mod.client.models.sonics.ModelSonic13;
import net.tardis.mod.client.overlays.OverlayHandler;
import net.tardis.mod.client.renderers.RenderCorridor;
import net.tardis.mod.client.renderers.RenderInvis;
import net.tardis.mod.client.renderers.RenderItemSonicPen;
import net.tardis.mod.client.renderers.RenderTardis;
import net.tardis.mod.client.renderers.consoles.RenderConsole01;
import net.tardis.mod.client.renderers.consoles.RenderConsole02;
import net.tardis.mod.client.renderers.controls.RenderConsole;
import net.tardis.mod.client.renderers.controls.RenderDoor;
import net.tardis.mod.client.renderers.controls.RenderInteriorDoor;
import net.tardis.mod.client.renderers.decorations.hellbent.*;
import net.tardis.mod.client.renderers.entities.*;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor03;
import net.tardis.mod.client.renderers.exteriors.RendererTileDoor01;
import net.tardis.mod.client.renderers.items.*;
import net.tardis.mod.client.renderers.layers.RenderLayerVortexM;
import net.tardis.mod.client.renderers.tiles.*;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.*;
import net.tardis.mod.common.entities.controls.*;
import net.tardis.mod.common.entities.hellbent.EntityHellbentCorridor;
import net.tardis.mod.common.entities.hellbent.EntityHellbentDoor;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.*;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.common.tileentity.decoration.TileEntityHelbentRoof;
import net.tardis.mod.common.tileentity.decoration.TileEntityHellbentMonitor;
import net.tardis.mod.common.tileentity.decoration.TileEntityHellbentPole;
import net.tardis.mod.common.tileentity.decoration.TileEntityRoundelChest;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.util.LimbManipulationUtil;
import net.tardis.mod.util.LimbManipulationUtil.Limb;

import java.util.ArrayList;
import java.util.Random;

@EventBusSubscriber
public class ClientProxy extends ServerProxy {

	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis.class, new RenderConsole());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmbrellaStand.class, new RenderUmbrellaStand());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor.class, new RenderTileDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFoodMachine.class, new RenderFoodMachine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTemporalLab.class, new RenderTemporalLab());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloprojector.class, new RenderTileHolo());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlembic.class, new RenderAlembic());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHellbentLight.class, new RenderHellbentLight());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHellbentMonitor.class, new RenderHellbentMonitor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHellbentPole.class, new RenderHellbentPole());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHelbentRoof.class, new RenderHellbentRoof());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoundelChest.class, new RenderRoundelChest());
		//Consoles
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis01.class, new RenderConsole01());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis02.class, new RenderConsole02());
		
		//Exteriors
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor01.class, new RendererTileDoor01());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor03.class, new RenderTileDoor03());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJsonTester.class, new RenderJsonHelper());
		
		//Interior Door
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInteriorDoor.class, new RenderInteriorDoor());
		
		// Controls
		RenderingRegistry.registerEntityRenderingHandler(ControlDoor.class, RenderDoor::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlX.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlY.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlZ.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlLaunch.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlDimChange.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlFlight.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlRandom.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlSTCLoad.class, RenderInvis::new);
		//RenderingRegistry.registerEntityRenderingHandler(ControlSTCButton.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlFuel.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlLandType.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlDirection.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlFastReturn.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlTelepathicCircuts.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlDoorSwitch.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlMag.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(ControlPhone.class, RenderInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCorridor.class, RenderCorridor::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHellbentCorridor.class, RenderHellbentCorridor::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHellbentDoor.class, RenderHellbentDoor::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekCasing.class, RenderDalekCaseing::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTardis.class, RenderTardis::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekRay.class, RenderRay::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanInvasion.class, RenderCybermanInvasion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanTomb.class, RenderCybermanTomb::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRayCyberman.class, RenderCyberRay::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityDalek.class, RenderDalek::new);
		
	}
	
	@Override
	public void preInit() {
		registerRenders();
		EnumClothes.ClothingHandler.init();
		OverlayHandler.init();
		if(!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
			Minecraft.getMinecraft().getFramebuffer().enableStencil();
		}
	}
	
	public static boolean getRenderBOTI() {
		return Minecraft.getMinecraft().getFramebuffer().isStencilEnabled() && TardisConfig.BOTI.enable;
	}

	@Override
	public void init() {
		super.init();
		TItems.sonic_pen.setTileEntityItemStackRenderer(new RenderItemSonicPen());
		TItems.space_helm.setTileEntityItemStackRenderer(new RenderItemSpaceHelm());
		TItems.vortex_manip.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelVortexM(), ModelVortexM.TEXTURE));
		TItems.key_01.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelKey01(), ModelKey01.TEXTURE));
		TItems.first_cane.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelFirstCane(), ModelFirstCane.TEXTURE));
		TItems.demat_circut.setTileEntityItemStackRenderer(new RendererItemDemat());
		TItems.key.setTileEntityItemStackRenderer(new RendererKey());
		TItems.sonic13th.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelSonic13()));

		Item.getItemFromBlock(TBlocks.tardis_top).setTileEntityItemStackRenderer(new RendererItemTardis());
		Item.getItemFromBlock(TBlocks.food_machine).setTileEntityItemStackRenderer(new RenderItemFoodMachine());
		Item.getItemFromBlock(TBlocks.tardis_top_01).setTileEntityItemStackRenderer(new RenderItemTardis02());
		Item.getItemFromBlock(TBlocks.tardis_top_02).setTileEntityItemStackRenderer(new RenderItemTardis03());
		Item.getItemFromBlock(TBlocks.alembic).setTileEntityItemStackRenderer(new RenderItemAlembic());
		
		Item.getItemFromBlock(TBlocks.console).setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelConsole(), RenderConsole.CONSOLE_TEXTURE));
	}
	
	
    public static ArrayList<EntityPlayer> layerPlayers = new ArrayList<>();

    
    @SubscribeEvent
    public static void addLayers(RenderPlayerEvent.Pre e) {
    	
    	EntityPlayer player = e.getEntityPlayer();
    	
        if (!layerPlayers.contains(player)) {
            RenderPlayer render = e.getRenderer();
            addRenderLayer(new RenderLayerVortexM(render));
            layerPlayers.add(player);
        }
    
        Random rand = e.getEntityPlayer().world.rand;
       
        //pls write something that says "THIS PLAYERS BEING SUCKED INTO THE VOID" k thx
        if(false) {
        LimbManipulationUtil.getLimbManipulator(e.getRenderer(), Limb.RIGHT_ARM).setAngles(-85, 8, 10);
        LimbManipulationUtil.getLimbManipulator(e.getRenderer(), Limb.LEFT_ARM).setAngles(-85, -8, -10);
     
        LimbManipulationUtil.getLimbManipulator(e.getRenderer(), Limb.LEFT_LEG).setAngles(-90, -11, 0);
        LimbManipulationUtil.getLimbManipulator(e.getRenderer(), Limb.RIGHT_LEG).setAngles(-90, 11, 0);
        }
    }
   
  

    public static void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch, Entity entity, ModelBiped model){

        model.bipedHead.rotateAngleZ = -netHeadYaw / (180F / (float)Math.PI);
        model.bipedHead.rotateAngleY = 0;
        model.bipedHead.rotateAngleX = -55 / (180F / (float)Math.PI);

        model.bipedHeadwear.rotateAngleX = model.bipedHead.rotateAngleX;
        model.bipedHeadwear.rotateAngleY = model.bipedHead.rotateAngleY;
        model.bipedHeadwear.rotateAngleZ = model.bipedHead.rotateAngleZ;
        
        if(limbSwingAmount > 0.25)
        	limbSwingAmount = 0.25f;
        float movement = MathHelper.cos(limbSwing * 0.8f + (float)Math.PI) * limbSwingAmount;

        model.bipedLeftArm.rotateAngleX = 180 / (180F / (float)Math.PI) - movement * 0.25f;
        model.bipedLeftArm.rotateAngleY = movement * -0.46f;
        model.bipedLeftArm.rotateAngleZ = movement * -0.2f;
        model.bipedLeftArm.rotationPointY = 2 - movement * 9.0F;
        
        model.bipedRightArm.rotateAngleX = 180 / (180F / (float)Math.PI) + movement * 0.25f;
        model.bipedRightArm.rotateAngleY = movement * -0.4f;
        model.bipedRightArm.rotateAngleZ = movement * -0.2f;
        model.bipedRightArm.rotationPointY = 2 + movement * 9.0F;

        model.bipedBody.rotateAngleY = movement * 0.1f;
        model.bipedBody.rotateAngleX = 0;
        model.bipedBody.rotateAngleZ = movement * 0.1f;
        
        model.bipedLeftLeg.rotateAngleX = movement * 0.1f;
        model.bipedLeftLeg.rotateAngleY = movement * 0.1f;
        model.bipedLeftLeg.rotateAngleZ = -7 / (180F / (float)Math.PI) - movement * 0.25f;
        model.bipedLeftLeg.rotationPointY = 10.4f + movement * 9.0F;
        model.bipedLeftLeg.rotationPointZ = movement * 0.6f;

        model.bipedRightLeg.rotateAngleX = movement * -0.1f;
        model.bipedRightLeg.rotateAngleY = movement * 0.1f;
        model.bipedRightLeg.rotateAngleZ = 7 / (180F / (float)Math.PI) - movement * 0.25f;
        model.bipedRightLeg.rotationPointY = 10.4f - movement * 9.0F;
        model.bipedRightLeg.rotationPointZ = movement * -0.6f;
	}

    
    private static void addRenderLayer(LayerRenderer layer) {
        for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
            playerRender.addLayer(layer);
        }
    }
}
