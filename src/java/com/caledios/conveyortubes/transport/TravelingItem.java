package com.caledios.conveyortubes.transport;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import com.caledios.conveyortubes.tileentity.TileBasicPipe;
import com.caledios.conveyortubes.util.WorldUtil;

public class TravelingItem {
	
	public static final int MAX_PROGRESS = 128;
	public static final int CENTER_PROGRESS = MAX_PROGRESS / 2;
	public static final int SPEED = MAX_PROGRESS / 16;
	private static short nextID;
	
	public final short id;
	private TileBasicPipe owner;
	private boolean stuck;
	
	protected EnumFacing input, output;
	protected boolean reachedCenter;
	protected ItemStack stack;
	protected int prog;
	protected int blocksSinceSync;
	
	public TravelingItem(TileBasicPipe owner, ItemStack stack, EnumFacing side){
		this.id = nextID++;
		this.owner = owner;
		this.stack = stack;
		
	}

	public TravelingItem(TileBasicPipe owner, NBTTagCompound nbt){
		this.id = nextID++;
		this.owner = owner;
		
	}
	
	public TravelingItem(TileBasicPipe owner, short id){
		this.id = id;
		this.owner = owner;
	}
	
	public boolean isStuck(){return stuck;}
	
	public boolean isValid(){return (stack != null && stack.getItem() != null && input != null);}
	
	private float getTranslatedCoords(int offset){
		if (prog >= CENTER_PROGRESS){
			return 0.5F + (float)offset * (prog-CENTER_PROGRESS) / MAX_PROGRESS;
		}else{
			switch(offset){
				case -1:
					 return 1.0F + (float) offset * prog / MAX_PROGRESS;
				case 0:
				default:
					return 0.5F;
				case 1:
					return (float) offset * prog / MAX_PROGRESS;
			}
		}
	}
	
	public float getX(){return getDirection() != null ? getTranslatedCoords(getDirection().getFrontOffsetX()) : 0.5F;}
	
	public float getY(){return getDirection() != null ? getTranslatedCoords(getDirection().getFrontOffsetY()) : 0.5F;}
	
	public float getZ(){return getDirection() != null ? getTranslatedCoords(getDirection().getFrontOffsetZ()) : 0.5F;}
	
	public ItemStack getStack(){return stack;}
	
	public EnumFacing getDirection(){return reachedCenter ? output : (input != null ? input.getOpposite() : null);}
	
	public boolean isCentered(){return prog == CENTER_PROGRESS;}
	
	//TODO: ItemUpdate Packets for client/server sync
	
	public boolean move(){
		if(!reachedCenter){
			boolean atCenter = (prog + SPEED) >= CENTER_PROGRESS;
			
			if (atCenter){
				//Todo: onReachedCenter();
			} 
			else if(!stuck){prog+=SPEED;}
		}
		else {
			if (owner.getWorld().isRemote){
				if(!stuck){prog += SPEED;}
				
				if (prog >=MAX_PROGRESS){
					//Todo: onEndReached();
					return false;
				}
			}
			else {
				EnumFacing output_ = output;
				boolean stuck_ = stuck;
				
				//TODO: Update Stuck Flag
				
				if (!stuck){prog+=SPEED;}
				
				if (prog >= MAX_PROGRESS){
					//Todo: onEndReached();
					return false;
				}
				
				if (stuck_ != stuck || output_ != output){
					//Todo: sendPacket(false)
					}
			}
		}
		return true;
	}
	
	public void onEndReached(){
		TileBasicPipe pipe = output != null ? WorldUtil.getPipe(owner.getWorld(), owner.getPos().offset(output)) : null;
		
		if (owner.getWorld().isRemote) {
			blocksSinceSync++;
			if (blocksSinceSync < 2){
				passToPipe(pipe, output, false);
			}
			return;
		}
		
		if (output != null){
			if(passToPipe(pipe, output, false)){return;}
			else{
				TileEntity tile = owner.getNeighbourTile(output);
				
				if(!passToInjectable(tile, output, false)){
					//Todo: addToItemHandler(tile, output, false);
				}
			}
		}
		
		if (stack != null && stack.stackSize > 0) {
			//Todo: dropItem(true);
		}
		
	}
	
	protected void reset(TileBasicPipe owner, EnumFacing input) {
		this.owner = owner;
		initializeFromEntrySide(input);

		// Do an early calculation to aid the server side.
		// Won't always be right, might be sometimes right.
		//Todo: calculateOutputDirection();
	}
	
	private boolean passToPipe(TileBasicPipe pipe, EnumFacing dir, boolean simulate) {
		if (pipe != null) {
			if (pipe.injectItemInternal(this, dir.getOpposite(), simulate)) {
				return true;
			}
		}

		return false;
	}
	
	private boolean passToInjectable(TileEntity tile, EnumFacing dir, boolean simulate){
		if(WorldUtil.isPipe(tile.getWorld(), tile.getPos())){return false;}
		
		if(tile instanceof IItemInjectable){
			int added = ((IItemInjectable) tile).injectItem(stack, dir.getOpposite(), simulate);
			if(added > 0){
				if(!simulate) {
					stack.stackSize -= added;
				}
				return true;
			}
		}
		return true;
	}
	
	protected void dropItem(boolean useOutputDirection) {
		EnumFacing dir = null;

		if (useOutputDirection) {
			// Decide output direction
			int directions = 0;
			for (EnumFacing d : EnumFacing.VALUES) {
				if (owner.connects(d)) {
					directions++;
					dir = d.getOpposite();
					if (directions >= 2) {
						break;
					}
				}
			}

			if (directions >= 2) {
				dir = null;
			}
		}

		ItemUtils.spawnItemEntity(owner.getWorld(),
				(double) owner.getPos().getX() + 0.5 + (dir != null ? dir.getFrontOffsetX() : 0) * 0.75,
				(double) owner.getPos().getY() + 0.5 + (dir != null ? dir.getFrontOffsetY() : 0) * 0.75,
				(double) owner.getPos().getZ() + 0.5 + (dir != null ? dir.getFrontOffsetZ() : 0) * 0.75,
				stack, 0, 0, 0);

		stack = null;
	}
	
	private void initializeFromEntrySide(EnumFacing side) {
		this.input = side;
		this.output = null;
		this.reachedCenter = false;
		this.stuck = false;
		this.prog = 0;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		stack = ItemStack.loadItemStackFromNBT(nbt);
		prog = nbt.getShort("p");
		input = DirectionUtils.get(nbt.getByte("in"));
		output = DirectionUtils.get(nbt.getByte("out"));
		reachedCenter = nbt.getBoolean("reachedCenter");
		if (nbt.hasKey("stuck")) {
			stuck = nbt.getBoolean("stuck");
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		stack.writeToNBT(nbt);
		nbt.setShort("p", (short) prog);
		nbt.setByte("in", (byte) DirectionUtils.ordinal(input));
		nbt.setByte("out", (byte) DirectionUtils.ordinal(output));
		nbt.setBoolean("reachedCenter", reachedCenter);
		if (stuck) {
			nbt.setBoolean("stuck", stuck);
		}
	}
	
	public boolean hasReachedCenter() {
		return reachedCenter;
	}

	public void setStuckFlagClient(boolean stuck) {
		if (owner.getWorld().isRemote) {
			this.stuck = stuck;
		}
	}

	public TileBasicPipe getOwner() {
		return owner;
	}

	public float getProgress() {
		return (float) prog / MAX_PROGRESS;
	}
}
