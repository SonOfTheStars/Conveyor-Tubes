package com.SOTS.conveyortubes.proxies;

import com.SOTS.conveyortubes.block.BlockRegistry;
import com.SOTS.conveyortubes.items.ItemRegistry;
import com.SOTS.conveyortubes.render.items.ItemRenderRegister;
import com.SOTS.conveyortubes.world.CTWorldGen;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	/**
	 * Pre-Init for both Combined Client and dedicated Server.
	 * Register Items/Blocks here!
	 */
    public void preInit(FMLPreInitializationEvent e) {
    	ItemRegistry.createItems();
    	BlockRegistry.createBlocks();
    }
    
    /**
     * Init for both Combined Client and dedicated Server.
     * Register Recipes here!
     */
    public void init(FMLInitializationEvent e) {
    	GameRegistry.registerWorldGenerator(new CTWorldGen(), 0);
    }
    
    /**
     * Post-Init for both Combined Client and dedicated Server.
     */
    public void postInit(FMLPostInitializationEvent e) {

    }
}
