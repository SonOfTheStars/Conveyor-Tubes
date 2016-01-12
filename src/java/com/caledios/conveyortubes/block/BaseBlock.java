package com.caledios.conveyortubes.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BaseBlock extends Block{
	
	/**
	 * Main Constructor
	 * Creates an Instance of Block
	 * @param unlocName The unlocalized Name the Block shall have
	 * @param mat The Material Type the Block shall have
	 * @param hardness The time it takes to mine this Block
	 * @param resistance The Blocks resistance to explosions
	 */
	public BaseBlock(String unlocName, Material mat,SoundType stype, float hardness, float resistance, String harvlvl, int lvl){
		super(mat);
		this.setUnlocalizedName(unlocName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setStepSound(stype);
		this.setHarvestLevel(harvlvl, lvl);
	}
	
	/**
	 * Constructor with pre-defined Material(Material.rock). Calls the Main Constructor!
	 * @param unlocName The unlocalized Name the Block shall have
	 * @param hardness The time it takes to mine this Block
	 * @param resistance The Blocks resistance to explosions
	 */
	//public BaseBlock(String unlocName, float hardness, float resistance){
	//	this(unlocName, Material.rock, hardness, resistance);
	//}
	
	/**
	 * Constructor with pre-defined Material(Material.rock), Hardness(2.0f) and Resistance to explosions(10.0f)
	 * @param unlocName The unlocalized Name the Block shall have
	 */
	//public BaseBlock(String unlocName){
	//	this(unlocName, 2.0f, 10.0f);
	//}
	
}
