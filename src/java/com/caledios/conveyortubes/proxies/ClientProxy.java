package com.caledios.conveyortubes.proxies;

import com.caledios.conveyortubes.ModConveyorTubes;
import com.caledios.conveyortubes.render.blocks.BlockRenderRegister;
import com.caledios.conveyortubes.render.items.ItemRenderRegister;

import net.minecraftforge.client.model.obj.OBJLoader;
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
		OBJLoader.instance.addDomain(ModConveyorTubes.MODID.toLowerCase());
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
