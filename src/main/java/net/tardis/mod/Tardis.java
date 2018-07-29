package net.tardis.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import net.tardis.mod.client.creativetabs.TardisTab;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.*;
import net.tardis.mod.common.entities.controls.*;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.protocols.ProtocolCCircuit;
import net.tardis.mod.common.protocols.ProtocolEnabledHADS;
import net.tardis.mod.common.protocols.TardisProtocol;
import net.tardis.mod.common.screwdriver.*;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.*;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;
import net.tardis.mod.common.world.TardisLoadingCallback;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.integrations.Galacticraft;
import net.tardis.mod.integrations.WeepingAngel;
import net.tardis.mod.packets.*;
import net.tardis.mod.proxy.ServerProxy;
import net.tardis.mod.util.helpers.EntityHelper;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tardis.MODID, name = Tardis.NAME, version = Tardis.VERSION, dependencies = Tardis.DEP, updateJSON = Tardis.UPDATE_JSON_URL)
public class Tardis {
	public static final String MODID = "tardis";
	public static final String NAME = "Tardis Mod";
	public static final String DEP = "after:ic2, galacticraftcore, " + TStrings.ModIds.WEEPING_ANGELS + "; required-after:forge@[14.23.2.2638,)";
	public static final String VERSION = "0.0.5A";
	public static final String UPDATE_JSON_URL = "https://raw.githubusercontent.com/Spectre0987/TardisMod/master/update.json";

	
	private static Logger logger;
	
	public static CreativeTabs tab;
	
	public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
	
	public static boolean hasIC2 = false;
	
	public static final int ID_GUI_TEMPORAL_LAB = 0;
	
	@Instance
	public static Tardis instance = new Tardis();
	
	@SidedProxy(clientSide = "net.tardis.mod.proxy.ClientProxy", serverSide = "net.tardis.mod.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		hasIC2 = Loader.isModLoaded(TStrings.ModIds.INDUSTRIAL_CRAFT);
		if (Loader.isModLoaded(TStrings.ModIds.GALACTICRAFT)) Galacticraft.preInit();
		if(Loader.isModLoaded(TStrings.ModIds.WEEPING_ANGELS)) WeepingAngel.preInit();
		logger = event.getModLog();
		tab = new TardisTab();
		TItems.register();
		TBlocks.register();
		TDimensions.register();
		EntityHelper.makeGoodBiomes();
		EntityHelper.registerStatic(ControlLaunch.class, "launch_lever");
		EntityHelper.registerStatic(ControlX.class, "x_valve");
		EntityHelper.registerStatic(ControlY.class, "y_valve");
		EntityHelper.registerStatic(ControlZ.class, "z_valve");
		EntityHelper.registerStatic(ControlDimChange.class, "dim_change");
		EntityHelper.registerStatic(ControlScreen.class, "screen");
		EntityHelper.registerStatic(ControlRandom.class, "rand_control");
		EntityHelper.registerStatic(ControlDoor.class, "tardis_door");
		EntityHelper.registerStatic(ControlSTCLoad.class, "stc_load");
		EntityHelper.registerStatic(ControlSTCButton.class, "stc_button");
		EntityHelper.registerStatic(ControlScanner.class, "scanner");
		EntityHelper.registerStatic(ControlFlight.class, "control_flight");
		EntityHelper.registerStatic(ControlFuel.class, "fuel");
		EntityHelper.registerStatic(EntityForceField.class, "force_field");
		EntityHelper.registerStatic(ControlLandType.class, "land_type");
		EntityHelper.registerStatic(ControlDirection.class, "direction_control");
		EntityHelper.registerStatic(ControlFastReturn.class, "tardis_fast_return");
		EntityHelper.registerStatic(ControlTelepathicCircuts.class, "telepathic_circuts");
		EntityHelper.registerStatic(ControlDoorSwitch.class, "tardis_door_control");
		EntityHelper.registerStatic(ControlPhone.class, "tardis_phone");
		EntityHelper.registerStatic(ControlMag.class, "tardis_magnitude");
		EntityHelper.registerNoSpawn(EntityTardis.class, "tardis");
		EntityHelper.registerProjectiles(EntityDalekRay.class, "ray_dalek");
		EntityHelper.registerProjectiles(EntityRayCyberman.class, "cyber_ray");
		EntityHelper.registerNoSpawn(EntityCorridor.class, "toyota_corridor");
		
		registerTileEntity(TileEntityTardis.class, "TileEntityTardis");
		registerTileEntity(TileEntityDoor.class, "TileEntityDoor");
		registerTileEntity(TileEntityTemporalLab.class, "TileEntityTemporalLab");
		registerTileEntity(TileEntityUmbrellaStand.class, "TileEntityUmbrellaStand");
		registerTileEntity(TileEntityAlembic.class, "TileEntityAlembic");
		registerTileEntity(TileEntityFoodMachine.class, "TileEntityFoodMachine");
		if (hasIC2) registerTileEntity(TileEntityEPanel.class, "TileEntityEPanel");
		registerTileEntity(TileEntityHoloprojector.class, "TileEntityHoloprojector");
		
		registerTileEntity(TileEntityJsonTester.class, "TileEntityJsonTester");
		
		//Exteriors
		registerTileEntity(TileEntityDoor01.class, "TileEntityDoor01");
		registerTileEntity(TileEntityDoor03.class, "TileEntityDoor03");
		
		NETWORK.registerMessage(MessageHandlerProtocol.class, MessageProtocol.class, 1, Side.SERVER);
		NETWORK.registerMessage(MessageHandlerTeleport.class, MessageTeleport.class, 2, Side.SERVER);
		NETWORK.registerMessage(MessageDoorOpen.Handler.class, MessageDoorOpen.class, 3, Side.CLIENT);
		NETWORK.registerMessage(MessageTelepathicCircut.Handler.class, MessageTelepathicCircut.class, 4, Side.SERVER);
		NETWORK.registerMessage(MessageSyncWorldShell.Handler.class, MessageSyncWorldShell.class, 5, Side.CLIENT);
		NETWORK.registerMessage(MessageExteriorChange.Handler.class, MessageExteriorChange.class, 6, Side.SERVER);
		NETWORK.registerMessage(MessageDemat.Handler.class, MessageDemat.class, 7, Side.CLIENT);
		
		ScrewdriverMode.register(new HallwayMode());
		ScrewdriverMode.register(new GRoomMode());
		ScrewdriverMode.register(new ElectricPanelMode());
		ScrewdriverMode.register(new HADSMode());
		
		ForgeChunkManager.setForcedChunkLoadingCallback(instance, new TardisLoadingCallback());
		
		TardisProtocol.register(new ProtocolCCircuit());
		TardisProtocol.register(new ProtocolEnabledHADS());
		
		if (TardisConfig.USE_ENTITIES.entities) {
			// Register All Mobs Here.
			EntityHelper.registerMob(EntityCybermanInvasion.class, "invasion_cyberman", TardisConfig.USE_ENTITIES.cybermanSpawnChance);
			EntityHelper.registerMob(EntityDalek.class, "dalek", TardisConfig.USE_ENTITIES.cybermanSpawnChance);
			EntityHelper.registerNoSpawn(EntityCybermanTomb.class, "cyberman_tomb");
		}
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.renderEntities();
		
		// Ore Dictionary
		OreDictionary.registerOre("oreUranium", TItems.power_cell);
	}
	
	public static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
		GameRegistry.registerTileEntity(clazz, Tardis.MODID + ":" + name);
	}
	
	public static boolean getIsDev() {
		return (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}
}
