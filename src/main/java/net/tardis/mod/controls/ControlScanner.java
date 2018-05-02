package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.Tardis;
import net.tardis.mod.entities.EntityCam;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.packets.MessageCam;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlScanner extends EntityControl{

	public ControlScanner(TileEntityTardis tardis) {
		super(tardis);
		this.setRotation(180, 43);
	}
	
	public ControlScanner(World world) {
		super(world);
		this.setSize(0.25F, 0.25F);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(23,16,0);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		
	}

}
