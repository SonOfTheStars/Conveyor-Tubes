package com.caledios.conveyortubes.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

import com.caledios.conveyortubes.tileentity.TileBasicPipe;

public class WorldUtil {
	
	public static boolean isPipe(IBlockAccess world, BlockPos pos){
		return (world.getTileEntity(pos) !=null && world.getTileEntity(pos) instanceof TileBasicPipe);
	}
	public static boolean isInventory(IBlockAccess world, BlockPos pos){
		return (world.getTileEntity(pos) !=null && world.getTileEntity(pos) instanceof IInventory);
	}
	
	public static TileBasicPipe getPipe(IBlockAccess world, BlockPos pos){
		if(!isPipe(world, pos)){
			return null;
		}
		else {
			return (TileBasicPipe)world.getTileEntity(pos);
		}
	}
	
	public static TileEntity getAdjacentTile(TileEntity tile, EnumFacing side){
		return side != null ? tile.getWorld().getTileEntity(tile.getPos().offset(side)) : null;
	}
}
