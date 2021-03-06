package net.tardis.mod.common.entities;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.api.events.tardis.TardisEnterEvent;
import net.tardis.mod.api.events.tardis.TardisExitEvent;
import net.tardis.mod.client.guis.GUICompanion;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.util.UUID;

public class EntityCompanion extends EntityCreature implements IInventory, IEntityOwnable {

	public static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(EntityCompanion.class, DataSerializers.BOOLEAN);
	public static final DataParameter<String> TYPE = EntityDataManager.createKey(EntityCompanion.class, DataSerializers.STRING);
	public static final DataParameter<Float> TARDIS_XP = EntityDataManager.createKey(EntityCompanion.class, DataSerializers.FLOAT);
	public BlockPos tardisPos = BlockPos.ORIGIN;
	public boolean flyTardis = false;
	UUID player;
	private NonNullList<ItemStack> inv = NonNullList.withSize(27, ItemStack.EMPTY);

	public EntityCompanion(World worldIn) {
		super(worldIn);
		this.stepHeight = 1;
	}


	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 1D));
		this.tasks.addTask(3, new EntityAIWander(this, 0.5D));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 30));
		this.tasks.addTask(1, new EntityAIEnterTardis(this, 1.0D));
		this.tasks.addTask(0, new EntityAISwimming(this));
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(SITTING, false);
		this.getDataManager().register(TYPE, EnumCompanionType.values()[rand.nextInt(EnumCompanionType.values().length - 1)].name());
		this.getDataManager().register(TARDIS_XP, 0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote && this.dimension == TDimensions.TARDIS_ID && world.getWorldTime() % 20 == 0) {
			TileEntityTardis tardis = null;
			for (TileEntity te : world.loadedTileEntityList) {
				if (te instanceof TileEntityTardis && te.getPos().distanceSq(this.getPosition()) < Math.pow(16, 2)) {
					tardis = (TileEntityTardis) te;
					break;
				}
			}
			if (tardis != null && tardis.isInFlight()) {
				this.setXP(this.getXP() + 0.001F);
			}
		}
		if (this.isWrongSize()) {
			this.setSize(this.getType().size[0], this.getType().size[1]);
		}
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public EnumCompanionType getType() {
		return Enum.valueOf(EnumCompanionType.class, this.getDataManager().get(TYPE));
	}

	public void setType(EnumCompanionType type) {
		this.getDataManager().set(TYPE, type.name());
	}

	@Override
	public int getSizeInventory() {
		return inv.size();
	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index < inv.size() ? inv.get(index) : ItemStack.EMPTY;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return this.getStackInSlot(index).splitStack(count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack s = this.getStackInSlot(index);
		this.setInventorySlotContents(index, ItemStack.EMPTY);
		return s;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < inv.size()) {
			inv.set(index, stack);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inv.clear();
	}

	public EntityPlayer getOwner() {
		if (this.player != null && world.getPlayerEntityByUUID(player) != null) {
			return world.getPlayerEntityByUUID(player);
		}
		return null;
	}

	public void setOwner(EntityPlayer player) {
		this.player = player.getGameProfile().getId();
	}

	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GUICompanion(this));
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getType().getName());
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for (ItemStack stack : inv) {
			list.appendTag(stack.writeToNBT(new NBTTagCompound()));
		}
		compound.setTag("inv", list);
		compound.setString("owner", player != null ? player.toString() : "");
		compound.setString("type", this.getType().name());
		compound.setFloat("xp", this.getXP());
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		int id = 0;
		NBTTagList list = compound.getTagList("inv", NBT.TAG_COMPOUND);
		for (NBTBase base : list) {
			inv.set(id, new ItemStack((NBTTagCompound) base));
			++id;
		}
		if (!compound.getString("owner").isEmpty()) player = UUID.fromString(compound.getString("owner"));
		if (compound.getString("type") != null && !compound.getString("type").isEmpty())
			this.setType(Enum.valueOf(EnumCompanionType.class, compound.getString("type")));
		this.setXP(compound.getFloat("xp"));
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (world.isRemote && player.getGameProfile().getId().equals(this.getOwnerId())) openGui();
		else if (this.getOwnerId() == null) {
			this.setOwner(player);
			if (!world.isRemote) {
				player.sendStatusMessage(new TextComponentTranslation(TStrings.Companions.SAVED), false);
			}
		}
		return true;
	}

	@Override
	public boolean getAlwaysRenderNameTag() {
		return true;
	}

	public boolean isWrongSize() {
		float[] size = this.getType().size;
		return this.width != size[0] || this.height != size[1];
	}

	@Override
	public UUID getOwnerId() {
		return this.player;
	}

	public boolean getSit() {
		return this.getDataManager().get(SITTING);
	}

	public void setSit(boolean sit) {
		this.getDataManager().set(SITTING, sit);
	}

	public float getXP() {
		return this.getDataManager().get(TARDIS_XP);
	}

	public void setXP(float xp) {
		this.getDataManager().set(TARDIS_XP, xp);
	}

	@Override
	public boolean isChild() {
		return false;
	}

	public enum EnumCompanionType {
		CLAIRE("claire", "Claire"),
		VASSILIS("vassilis", "Vassilis"),
		ALEXA("alexa", "Alexa"),
		PETER("peter", "Peter"),
		VANDHAM("vandham", "Vandham"),
		WOLSEY("black", "Wolsey", new float[]{0.75F, 1F}),
		NONE("", "");

		@SideOnly(Side.CLIENT)
		public ModelBase model;
		ResourceLocation skin;
		String formattedName = "";
		float[] size = {0.95F, 1.75F};

		EnumCompanionType(String name, String formatName) {
			skin = new ResourceLocation(Tardis.MODID, "textures/entity/" + name + ".png");
			formattedName = formatName;
		}

		EnumCompanionType(String name, String formattedName, float[] size) {
			this(name, formattedName);
			this.size = size;
		}

		public ResourceLocation getTexture() {
			return this.skin;
		}

		public String getName() {
			return this.formattedName;
		}

		@SideOnly(Side.CLIENT)
		public ModelBase getModel() {
			model.isChild = false;
			return model;
		}

		@SideOnly(Side.CLIENT)
		public void setModel(ModelBase model) {
			this.model = model;
		}
	}

	public static class EntityAIFollowOwner extends EntityAIBase {

		EntityCompanion entity;
		double speed;

		public EntityAIFollowOwner(EntityCompanion entity, double speed) {
			this.entity = entity;
			this.speed = speed;
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return entity.getOwner() != null && !entity.getDataManager().get(EntityCompanion.SITTING);
		}

		@Override
		public boolean isInterruptible() {
			return true;
		}

		@Override
		public void updateTask() {
			super.updateTask();
			EntityPlayer player = entity.getOwner();
			if (player == null) return;
			if (player.getPositionVector().distanceTo(entity.getPositionVector()) > 5)
				entity.moveHelper.setMoveTo(player.posX, player.posY, player.posZ, speed);
		}

		@Override
		public boolean shouldContinueExecuting() {
			return entity.getOwner() != null && entity.getOwner().getPositionVector().distanceTo(entity.getPositionVector()) > 5 && !entity.getDataManager().get(EntityCompanion.SITTING);
		}

	}

	public static class EntityAIEnterTardis extends EntityAIBase {

		double speed;
		EntityCompanion comp;

		public EntityAIEnterTardis(EntityCompanion com, double s) {
			this.comp = com;
			this.speed = s;
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return !comp.tardisPos.equals(BlockPos.ORIGIN);
		}

		@Override
		public boolean shouldContinueExecuting() {
			return this.shouldExecute();
		}

		@Override
		public void updateTask() {
			if (Helper.blockPosToVec3d(comp.tardisPos).distanceTo(comp.getPositionVector()) > 3) {
				comp.moveHelper.setMoveTo(comp.tardisPos.getX(), comp.tardisPos.getY(), comp.tardisPos.getZ(), speed);
			} else {
				comp.tardisPos = BlockPos.ORIGIN;
				WorldServer world = comp.world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
				if (comp.getOwner() != null && TardisHelper.hasTardis(comp.getOwner().getGameProfile().getId())) {
					TileEntityTardis tardis = Helper.getTardis((world.getTileEntity(TardisHelper.getTardis(comp.getOwner().getGameProfile().getId()))));
					if (tardis != null) {
						tardis.enterTARDIS(comp);
						if (comp.flyTardis) {
							tardis.setDesination(comp.getOwner().getPosition(), comp.getOwner().dimension);
							tardis.startFlight();
							comp.flyTardis = false;
						}
					}
				}
			}
		}
	}

	@EventBusSubscriber
	public static class Events {

		@SubscribeEvent
		public static void enterTardis(TardisEnterEvent event) {
			if (!event.getEntity().world.isRemote && event.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntity();
				for (EntityCompanion e : player.getEntityWorld().getEntitiesWithinAABB(EntityCompanion.class, Block.FULL_BLOCK_AABB.offset(player.getPositionVector()).grow(16))) {
					if (!e.getSit() && e.getOwner() != null && e.getOwner() == player) {
						WorldServer world = player.world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
						TileEntityTardis tardis = Helper.getTardis(world.getTileEntity(event.getInteriorPos()));
						if (tardis != null) {
							tardis.enterTARDIS(e);
						}
					}
				}
			}
		}

		@SubscribeEvent
		public static void exitTardis(TardisExitEvent event) {
			if (!event.getEntity().world.isRemote && event.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntity();
				WorldServer world = player.world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
				for (EntityCompanion c : player.world.getEntitiesWithinAABB(EntityCompanion.class, Block.FULL_BLOCK_AABB.offset(player.getPositionVector()).grow(16))) {
					if (!c.getSit() && c.getOwner() != null && c.getOwner() == player) {
						TileEntityTardis tardis = Helper.getTardis(world.getTileEntity(event.getPos()));
						if (tardis != null) {
							tardis.transferPlayer(c, true);
						}
					}
				}
			}
		}
	}

}
