package com.caledios.conveyortubes.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

import com.caledios.conveyortubes.transport.TravelingItem;
import com.caledios.conveyortubes.util.WorldUtil;
import com.google.common.collect.Lists;

public class TileBasicPipe extends TileEntity{
	
	public List<String> visible = new ArrayList<String>();
	private boolean[] conMatrix = new boolean[6];
	
	private final Set<TravelingItem> itemSet = new HashSet<TravelingItem>();
	
	public TileBasicPipe(){
		Arrays.fill(conMatrix, false);
	}
	
	private void modifyConMatrix(EnumFacing index, boolean value){
		conMatrix[index.getIndex()] = value;
	}
	
	public List<String> checkConnections(IBlockAccess world, BlockPos pos){
		//Allways Render the Center
		List<String> connections = Lists.newArrayList("CENTER"); //("CENTER", "GCENTER"); G-Pieces disabled for now. Translucent Render is no Option ATM
		
		//Check for Connections on the North Side
		if (!world.isAirBlock(pos.north())){
			if(WorldUtil.isPipe(world, pos.north())) {
				connections.add("UP");
				modifyConMatrix(EnumFacing.NORTH, true);
			}
			if(WorldUtil.isInventory(world, pos.north())) {
				connections.add("UP");
				connections.add("CUP");
				modifyConMatrix(EnumFacing.NORTH, true);
			}
			//connections.add("GUP");
		}
		else {
			modifyConMatrix(EnumFacing.NORTH, false);
		}
		//Check for Connections on the South Side
		if (!world.isAirBlock(pos.south())) {
			if(WorldUtil.isPipe(world, pos.south())) {
				connections.add("DOWN");
				modifyConMatrix(EnumFacing.SOUTH, true);
			}
			if(WorldUtil.isInventory(world, pos.south())) {
				connections.add("DOWN");
				connections.add("CDOWN");
				modifyConMatrix(EnumFacing.SOUTH, true);
			}
			//connections.add("GDOWN");
		}
		else {
			modifyConMatrix(EnumFacing.SOUTH, false);
		}
		//Check for Connections on the East Side
		if (!world.isAirBlock(pos.east())) {
			if(WorldUtil.isPipe(world, pos.east())){
				connections.add("EAST");
				modifyConMatrix(EnumFacing.EAST, true);
			}
			if(WorldUtil.isInventory(world, pos.east()))
			{
				connections.add("EAST");
				connections.add("CEAST");
				modifyConMatrix(EnumFacing.EAST, true);
			}
			//connections.add("GEAST");
		}
		else {
			modifyConMatrix(EnumFacing.EAST, false);
		}
		//Check for Connections on the (West Side Mofo!)
		if (!world.isAirBlock(pos.west())) {
			if(WorldUtil.isPipe(world, pos.west())) {
				connections.add("WEST");
				modifyConMatrix(EnumFacing.WEST, true);
			}
			if(WorldUtil.isInventory(world, pos.west())){
				connections.add("WEST");
				connections.add("CWEST");
				modifyConMatrix(EnumFacing.WEST, true);
			}
			//connections.add("GWEST");
		}
		else {
			modifyConMatrix(EnumFacing.WEST, false);
		}
		//Check for Connections on the Up Side
		if (!world.isAirBlock(pos.up())) {
			if(WorldUtil.isPipe(world, pos.up())){
				connections.add("SOUTH");
				modifyConMatrix(EnumFacing.UP, true);
			}
			if(WorldUtil.isInventory(world, pos.up())){
				connections.add("SOUTH");
				connections.add("CSOUTH");
				modifyConMatrix(EnumFacing.UP, true);
			}
		}
		else {
			modifyConMatrix(EnumFacing.UP, false);
		}
		//Check for Connections on the Down Side
		if (!world.isAirBlock(pos.down())) {
			if(WorldUtil.isPipe(world, pos.down())){
				connections.add("NORTH");
				modifyConMatrix(EnumFacing.DOWN, true);
			}
			if(WorldUtil.isInventory(world, pos.down())){
				connections.add("NORTH");
				connections.add("CNORTH");
				modifyConMatrix(EnumFacing.DOWN, true);
			}
		}
		else {
			modifyConMatrix(EnumFacing.DOWN, false);
		}
		return connections;
	}
	
	public TileEntity getNeighbourTile(EnumFacing side){
		return WorldUtil.getAdjacentTile(this, side);
	}
	
	protected boolean injectItemInternal(TravelingItem item, EnumFacing dir, boolean simulate) {
		if (item.isValid()) {
			int stuckItems = 0;

			synchronized (itemSet) {
				for (TravelingItem p : itemSet) {
					if (p.isStuck()) {
						stuckItems++;

						if (stuckItems >= 1) {
							return false;
						}
					}
				}

				if (!simulate) {
					itemSet.add(item);
				}
			}

			if (!simulate) {
				item.reset(this, dir);
				item.sendPacket(true);
			}
			return true;
		} else {
			return false;
		}
	}
	
	protected void addItemClientSide(TravelingItem item) {
		if (!getWorld().isRemote) {
			return;
		}

		synchronized (itemSet) {
			Iterator<TravelingItem> itemIterator = itemSet.iterator();
			while (itemIterator.hasNext()) {
				TravelingItem p = itemIterator.next();
				if (p.id == item.id) {
					itemIterator.remove();
					break;
				}
			}

			itemSet.add(item);
		}
	}

	protected void removeItemClientSide(TravelingItem item) {
		if (getWorld().isRemote) {
			synchronized (itemSet) {
				itemSet.remove(item);
			}
		}
	}
}
