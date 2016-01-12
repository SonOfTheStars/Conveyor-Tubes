package com.caledios.conveyortubes.render.items;

import com.caledios.conveyortubes.ModConveyorTubes;
import com.caledios.conveyortubes.block.BlockRegistry;
import com.caledios.conveyortubes.block.pipe.BlockCTPipe;
import com.caledios.conveyortubes.items.ItemRegistry;
import com.caledios.conveyortubes.util.ClientUtils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;

public final class ItemRenderRegister {
	
	private static String modid = ModConveyorTubes.MODID;
	
	/**
	 * Registers all the Mods Item Renders, called in Init in the Client Proxy
	 */
	public static void registerItemrender(){
		reg(ItemRegistry.itemOreShard, 0, "shardRutile");
		reg(ItemRegistry.itemOreShard, 1, "shardVanadinite");
	}
	
	/**
	 * Registers a Default Item Render(Metadata 0) 
	 * @param toReg The Item to register
	 */
	private static void reg(Item toReg){
		ClientUtils.mc().getRenderItem().getItemModelMesher()
		.register(toReg, 0, new ModelResourceLocation(modid + ":" + toReg.getUnlocalizedName().substring(5), "inventory"));
	}
	
	/**
	 * Registers an Item Render w/ Metadata 
	 * @param toReg The Item to register
	 * @param meta The Metadata of @toReg
	 */
	private static void reg(Item toReg, int meta, String file){
		ClientUtils.mc().getRenderItem().getItemModelMesher()
		.register(toReg, meta, new ModelResourceLocation(modid + ":" + file, "inventory"));
	}
	
	public static void preInit(){
		ModelBakery.addVariantName(ItemRegistry.itemOreShard, "conveyortubes:shardRutile", "conveyortubes:shardVanadinite");
		//ModelBakery.registerItemVariants(ItemRegistry.itemOreShard, ClientUtils.getResource(modid + ":oreShard.shardRutile"), ClientUtils.getResource(modid + ":oreShard.shardVanadinite"));
	}
	private static void regOBJ(Block toReg, String loc){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(toReg), 0, new ModelResourceLocation(modid.toLowerCase() + ":" + loc, "inventory" ));
	}
}
