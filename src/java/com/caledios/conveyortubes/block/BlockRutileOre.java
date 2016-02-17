package com.caledios.conveyortubes.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRutileOre extends Block{
	private Item drop;
	private int meta, least, most;
	
	protected BlockRutileOre(String unlocName, Material mat, Item drop, int meta, int least, int most){
		super(mat);
		this.setUnlocalizedName(unlocName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(3.0f);
		this.setResistance(15.0f);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		
		this.drop = drop;
		this.meta = meta;
		this.least = least;
		this.most = most;
	}
	
	protected BlockRutileOre(String unlocName, Item drop){
		this(unlocName, Material.rock, drop, 0, 2, 5);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.drop;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return this.meta;
	}

	

}
