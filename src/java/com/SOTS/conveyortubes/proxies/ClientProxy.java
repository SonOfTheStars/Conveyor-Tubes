package com.SOTS.conveyortubes.proxies;

import com.SOTS.conveyortubes.render.blocks.BlockRenderRegister;
import com.SOTS.conveyortubes.render.items.ItemRenderRegister;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	/**
	 * Pre-Init Event on the Combined Client
	 */
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		
		ItemRenderRegister.preInit();
	}
	
	/**
	 * Init Event for the Combined Client.
	 * Register Renderers and Sound here!
	 */
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		
		ItemRenderRegister.registerItemrender();
		BlockRenderRegister.registerBlockRenderer();
		
	}
	
	/**
	 * Post-Init on the Combined Client
	 */
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
	
}
