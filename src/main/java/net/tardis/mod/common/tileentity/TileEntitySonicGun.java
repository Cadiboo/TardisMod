package net.tardis.mod.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntitySonicGun extends TileEntity implements ITickable {

	private IBlockState state = Blocks.AIR.getDefaultState();
	private int ticks = 90;

	public TileEntitySonicGun() {

	}

	@Override
	public void update() {
		--ticks;
		if (ticks <= 0) {
			if (state == null) state = Blocks.AIR.getDefaultState();
			world.setBlockState(this.getPos(), state);
		}
	}

	public void setBlockState(IBlockState state) {
		this.state = state;
		markDirty();
	}

	public IBlockState getState() {
		return state;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		Block block = Block.getBlockFromName(tag.getString("block_name"));
		if (block == null) {
			state = Blocks.AIR.getDefaultState();
			return;
		}
		if (tag.hasKey("meta"))
			state = block.getStateFromMeta(tag.getInteger("meta"));
		else
			state = block.getDefaultState();
		super.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setString("block_name", state.getBlock().getRegistryName().toString());
		if (state.getBlock().getMetaFromState(state) != 0)
			tag.setInteger("meta", state.getBlock().getMetaFromState(state));
		return super.writeToNBT(tag);
	}

}
