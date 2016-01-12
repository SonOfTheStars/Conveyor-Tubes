package com.caledios.conveyortubes.block;

import com.caledios.conveyortubes.ModConveyorTubes;
import com.caledios.conveyortubes.block.pipe.BlockCTPipe;
import com.caledios.conveyortubes.items.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BlockRegistry {
	
	public static Block oreRutile;
	public static Block oreVanadinite;
	public static Block pipeBase;
	
	
	/**
	 * Registers all the Blocks this Mod adds to the Game Registry
	 */
	public static void createBlocks(){
		GameRegistry.registerBlock(oreRutile=new BlockRutileOre("oreRutile", ItemRegistry.itemOreShard), "oreRutile");
		GameRegistry.registerBlock(oreVanadinite=new BlockVanadiniteOre("oreVanadinite", ItemRegistry.itemOreShard), "oreVanadinite");
		GameRegistry.registerBlock(pipeBase=new BlockCTPipe(), "ConveyorTube");
	}

}
