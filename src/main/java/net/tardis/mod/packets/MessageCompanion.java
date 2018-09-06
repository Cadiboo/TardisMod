package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.client.guis.GUICompanion.EnumAction;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.EntityCompanion;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.TardisHelper;

public class MessageCompanion implements IMessage {

	public int id;
	public EnumAction action;
	
	public MessageCompanion() {}
	
	public MessageCompanion(int id, EnumAction act) {
		this.id = id;
		this.action = act;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.action = Enum.valueOf(EnumAction.class, ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		ByteBufUtils.writeUTF8String(buf, action.name());
	}

	public static class Handler implements IMessageHandler<MessageCompanion, IMessage>{
		@Override
		public IMessage onMessage(MessageCompanion message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					EntityCompanion comp = (EntityCompanion)ctx.getServerHandler().player.getServerWorld().getEntityByID(message.id);
					if(comp != null) {
						if(message.action == EnumAction.GO_TO_TARDIS) {
							WorldServer world = ctx.getServerHandler().player.getServer().getWorld(TDimensions.TARDIS_ID);
							BlockPos pos = TardisHelper.hasTardis(ctx.getServerHandler().player.getGameProfile().getId()) ? TardisHelper.getTardis(ctx.getServerHandler().player.getGameProfile().getId()) : BlockPos.ORIGIN;
							if(pos.equals(BlockPos.ORIGIN)) return;
							TileEntityTardis tardis = Helper.getTardis(world.getTileEntity(pos));
							if(tardis != null) {
								comp.setSit(false);
								comp.tardisPos = tardis.getLocation().toImmutable();
							}
						}
						else if(message.action == EnumAction.FOLLOW){
							comp.setSit(!comp.getSit());
						}
					}
				}});
			return null;
		}
		
	}
}
