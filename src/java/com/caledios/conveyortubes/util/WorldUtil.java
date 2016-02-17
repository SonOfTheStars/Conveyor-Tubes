package com.caledios.conveyortubes.util;

import com.caledios.conveyortubes.block.pipe.BlockBasicPipe;
import com.caledios.conveyortubes.tileentity.TileBasicPipe;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class WorldUtil {
	
	public static boolean isPipe(IBlockAccess world, BlockPos pos){
		return (world.getTileEntity(pos) !=null && world.getTileEntity(pos) instanceof TileBasicPipe);
	}
	public static boolean isInventory(IBlockAccess world, BlockPos pos){
		return (world.getTileEntity(pos) !=null && world.getTileEntity(pos) instanceof IInventory);
	}
}
