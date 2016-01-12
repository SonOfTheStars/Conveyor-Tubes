package com.caledios.conveyortubes.world;



import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSmallMinable extends WorldGenerator{
	private IBlockState block;
	private Predicate<IBlockState> target;
	
	public WorldGenSmallMinable(IBlockState block, Predicate<IBlockState> target){
		this. block = block;
		this. target = target;
	}
	@SuppressWarnings("unchecked")
	public WorldGenSmallMinable(IBlockState block){
		this(block, BlockHelper.forBlock(Blocks.stone));
	}
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.getBlockState(position).getBlock().isReplaceableOreGen(worldIn, position, this.target))
	        worldIn.setBlockState(position, this.block);
	    return true;
	}
}
