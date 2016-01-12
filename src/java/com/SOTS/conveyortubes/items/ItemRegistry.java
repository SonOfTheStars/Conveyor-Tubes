package com.SOTS.conveyortubes.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {
	
	public static Item itemOreShard;
	
	/**
	 * Registers all the Items of the Mod to the Game Registry
	 */
	public static void createItems(){
		GameRegistry.registerItem(itemOreShard = new ItemShard("oreShard"), "oreShard");
	}
}
