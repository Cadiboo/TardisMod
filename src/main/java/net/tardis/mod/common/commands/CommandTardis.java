package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityTardisCoral;
import net.tardis.mod.util.common.helpers.FileHelper;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.TardisHelper;

import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.*;


public class CommandTardis extends CommandBase {

    public static final List<String> subcommands = Arrays.asList("grow", "transfer", "interior", "summon", "remove", "restoresys");
    /**
     * Gets the name of the command
     */
    @Override
    public String getName() {
        return "tardis";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender
     */
    @Override
    public String getUsage(ICommandSender sender) {
        StringBuilder usageString = new StringBuilder();
        usageString.append("/tardis ");
        for (String subcommand : subcommands ) {
            usageString.append(MessageFormat.format("{0} | ", subcommand));
        }
        return usageString.toString();
    }

    /**
     * Callback for when the command is executed
     *
     * @param server
     * @param sender
     * @param args
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (args.length == 0) {
            throw new CommandException("You need to include a argument of the following: " + this.getUsage(sender));
        }

        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
            
            String alias = args[0];

            if(args.length > 1 && args[0].equals("restoresys")) {
                if (PermissionAPI.hasPermission(player, TStrings.Permissions.REMOVE_TARDIS)) {
                    if(args.length == 3)
                        player = player.world.getMinecraftServer().getPlayerList().getPlayerByUsername(args[2]);
                    if(player != null) {
                        this.restoreSystem(args[1], player, player.getUniqueID());
                    }
                } else {
                    throw new CommandException("You do not have permission to run this command.");
                }
            } else if (args.length == 1){
                if (alias.equals("grow")) {
                    handleGrow(player);
                }

                if (alias.equals("interior")) {
                    if (PermissionAPI.hasPermission(player, TStrings.Permissions.TP_IN_TARDIS)) {
                        handleTeleport(player);
                    } else {
                        throw new CommandException("You do not have permission to run this command.");
                    }
                }
            } else if (args.length == 2){
                if (alias.equals("transfer")) {
                    handlerOwner(player, args[1]);
                }

                if (alias.equals("summon")) {
                    if (PermissionAPI.hasPermission(player, TStrings.Permissions.SUMMON_TARDIS)) {
                        handleSummon(player, args[1]);
                    } else {
                        throw new CommandException("You do not have permission to run this command.");
                    }
                }

                if (alias.equals("remove")) {
                    if (PermissionAPI.hasPermission(player, TStrings.Permissions.REMOVE_TARDIS)) {
                        handleRemove(player, args[1]);
                    } else {
                        throw new CommandException("You do not have permission to run this command.");
                    }
                }
            }
            else
                throw new CommandException("/tardis [summon | remove | transfer] <username>");
        }
        else
            throw new CommandException("You are not a player. You must run these commands in game.");
    }
    
    private void restoreSystem(String name, EntityPlayerMP player, @Nullable UUID id) throws CommandException {
    	BaseSystem systemBase = TardisSystems.createFromName(name);
    	if(systemBase != null) {
    		UUID owner = (id == null ? player.getUniqueID() : id);
    		if(TardisHelper.hasTardis(owner)) {
    			TileEntityTardis tardis = (TileEntityTardis)player.getServerWorld().getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(TardisHelper.getTardis(owner));
    			if(tardis != null) {
    				tardis.getSystem(systemBase.getClass()).setHealth(1F);
    				player.sendStatusMessage(new TextComponentTranslation(TStrings.Commands.SYSTEM_RESTORED), false);
    			}
    			else throw new CommandException(TStrings.Commands.NO_TARIDS_IN_WORLD);
    		}
    		else throw new CommandException(TStrings.Commands.NO_TARDIS_OWNED);
    	}
    	else throw new CommandException(TStrings.Commands.NO_SYSTEM);
    }

    //Handle removing the Tardis
    private void handleRemove(EntityPlayerMP senderPlayer, String toBeRemoved) {
        MinecraftServer server = senderPlayer.getServer();
        Map<UUID,String> playersMap = FileHelper.getPlayersFromServerFile();

        if (playersMap.containsValue(toBeRemoved)) {
            UUID toBeRemovedID = Helper.getKeyByValue(playersMap,toBeRemoved);
            if (TardisHelper.hasTardis(toBeRemovedID)) {
                TardisHelper.tardisOwners.remove(toBeRemovedID.toString());
                senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.TARDIS_DELETED));
            } else {
                senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
            }
        } else {
            senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_PLAYER_FOUND));
        }
    }

    //Handle summoning the Tardis
    private void handleSummon(EntityPlayerMP senderPlayer, String owner) {
        MinecraftServer server = senderPlayer.getServer();
        Map<UUID, String> playersMap = FileHelper.getPlayersFromServerFile();

        if (playersMap.containsValue(owner)) {
            UUID ownerUUID = Helper.getKeyByValue(playersMap,owner);
            if (TardisHelper.hasTardis(ownerUUID)) {
                BlockPos tardisbp = TardisHelper.getTardis(ownerUUID);
                TileEntity te = server.getWorld(TDimensions.TARDIS_ID).getTileEntity(tardisbp);
                if (te instanceof TileEntityTardis) {
                    ((TileEntityTardis) te).setDesination(senderPlayer.getPosition().add(1, 0, 1), senderPlayer.dimension);
                    ((TileEntityTardis) te).startFlight();
                    senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.TARDIS_TRAVEL));
                }
            } else {
                senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
            }
        } else {
            senderPlayer.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_PLAYER_FOUND));
        }
    }


    //Handles transfering Tardis ownership
    private void handlerOwner(EntityPlayerMP player, String newOwnerName) {
        MinecraftServer server = player.getServer();
        EntityPlayer newOwner = server.getPlayerList().getPlayerByUsername(newOwnerName);
        if (newOwner != null && TardisHelper.hasTardis(player.getGameProfile().getId()) && !TardisHelper.hasTardis(newOwner.getGameProfile().getId())) {
            BlockPos pos = TardisHelper.getTardis(player.getGameProfile().getId());
            if (pos != null) {
                TardisHelper.tardisOwners.remove(player.getGameProfile().getId().toString());
                TardisHelper.tardisOwners.put(newOwner.getGameProfile().getId().toString(), pos.toImmutable());
            }
        }
    }

    //Grow the tardis
    private void handleGrow(EntityPlayerMP player) {
        World world = player.getEntityWorld();
        RayTraceResult ray = world.rayTraceBlocks(player.getPositionVector().add(0, player.getEyeHeight(), 0), player.getPositionVector().add(player.getLookVec().scale(16D)));
        if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
            TileEntity te = world.getTileEntity(ray.getBlockPos());
            if (te != null && te instanceof TileEntityTardisCoral) {
                ((TileEntityTardisCoral) te).grow();
            }
        }
    }

    //Handles teleporting the user to their interior
    private void handleTeleport(EntityPlayerMP player) {
        MinecraftServer server = player.getServer();
        if (TardisHelper.hasTardis(player.getUniqueID())) {
            BlockPos pos = TardisHelper.getTardis(player.getUniqueID());
            player.dismountRidingEntity();
            ((TileEntityTardis) server.getWorld(TDimensions.TARDIS_ID).getTileEntity(pos)).enterTARDIS(player);
        } else {
            player.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
        }
    }

    /**
     * Get a list of options for when the user presses the TAB key
     */
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if(args.length < 2 ) {
            return getListOfStringsMatchingLastWord(args, subcommands);
        }
        else if (args[0].equals("summon") || args[0].equals("remove")){
            return getListOfStringsMatchingLastWord(args, FileHelper.getPlayersFromServerFile().values());
        }
        else if(args[0].equals("transfer")){
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }
        else if (args[0].equals("restoresys")){
            List<String> systemNames = new ArrayList<String>();

            for (Map.Entry<String,Class<? extends BaseSystem>> entry : TardisSystems.SYSTEMS.entrySet()) {
                systemNames.add(entry.getKey());
            }

            if (args.length == 2){
                systemNames.addAll(Arrays.asList(server.getOnlinePlayerNames()));
            }
            return getListOfStringsMatchingLastWord(args, systemNames);
        }
        return Collections.emptyList();
    }
}
