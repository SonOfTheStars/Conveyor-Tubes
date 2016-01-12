package com.SOTS.conveyortubes.block;

import com.SOTS.conveyortubes.items.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BlockRegistry {
	
	public static Block oreRutile;
	public static Block oreVanadinite;
	
	
	/**
	 * Registers all the Blocks this Mod adds to the Game Registry
	 */
	public static void createBlocks(){
		GameRegistry.registerBlock(oreRutile=new BlockRutileOre("oreRutile", ItemRegistry.itemOreShard), "oreRutile");
		GameRegistry.registerBlock(oreVanadinite=new BlockVanadiniteOre("oreVanadinite", ItemRegistry.itemOreShard), "oreVanadinite");
	}

}
