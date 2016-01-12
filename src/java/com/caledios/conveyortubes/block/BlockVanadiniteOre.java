package com.caledios.conveyortubes.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockVanadiniteOre extends Block{
	
	private Item drop;
	private int meta, least, most;
	
	protected BlockVanadiniteOre(String unlocName, Material mat, Item drop, int meta, int least, int most){
		super(mat);
		this.setUnlocalizedName(unlocName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(7.0f);
		this.setResistance(30.0f);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
		
		this.drop = drop;
		this.meta = meta;
		this.least = least;
		this.most = most;
	}
	
	protected BlockVanadiniteOre(String unlocName, Item drop){
		this(unlocName, Material.rock, drop, 1, 1, 3);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.drop;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return this.meta;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		 if (this.least >= this.most)
		        return this.least;
		    return this.least + random.nextInt(this.most - this.least + fortune + 1);
	}
}
