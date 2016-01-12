package com.caledios.conveyortubes.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import com.caledios.conveyortubes.ModConveyorTubes;
import com.caledios.conveyortubes.block.BlockRegistry;
import com.caledios.conveyortubes.block.pipe.BlockCTPipe;

public final class BlockRenderRegister {
	
	private static String modid = ModConveyorTubes.MODID;
	
	/**
	 * Open Method to be called in preInit() in the CommonProxy
	 */
	public static void registerBlockRenderer(){
		reg(BlockRegistry.oreRutile);
		reg(BlockRegistry.oreVanadinite);
		regOBJ(BlockRegistry.pipeBase, BlockCTPipe.name);
	}
	
	/**
	 * Method to register a Default Block Render(Metadata 0)
	 * @param toReg Block to be registered
	 */
	private static void reg(Block toReg){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(toReg), 0, new ModelResourceLocation(modid + ":" + toReg.getUnlocalizedName().substring(5), "inventory"));
	}
	
	/**
	 * Method to register a Block Render w/ Metadata
	 * @param toReg Block to be registered
	 * @param meta Integer defining Metadata of @toReg
	 */
	private static void reg(Block toReg, int meta){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(toReg), meta, new ModelResourceLocation(modid + ":" + toReg.getUnlocalizedName().substring(5), "inventory"));
	}
	
	private static void regOBJ(Block toReg, String loc){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(toReg), 0, new ModelResourceLocation(modid.toLowerCase() + ":" + loc, "inventory" ));
	}
}
