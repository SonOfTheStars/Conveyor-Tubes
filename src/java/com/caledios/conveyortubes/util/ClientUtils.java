package com.caledios.conveyortubes.util;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClientUtils {
	static HashMap<String, ResourceLocation> resourceMap = new HashMap<String, ResourceLocation>(); 
	
	public static Tessellator tesl(){
		return Tessellator.getInstance();
	}
	public static Minecraft mc(){
		return Minecraft.getMinecraft();
	}
	
	public static void bindTexture(String path){
		mc().getTextureManager().bindTexture(getResource(path));
	}
	
	public static void bindAtlas18(){
		mc().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
	}
	
	public static ResourceLocation getResource(String path){
		ResourceLocation rl = resourceMap.containsKey(path) ? resourceMap.get(path) : new ResourceLocation(path);
		if(!resourceMap.containsKey(path))
			resourceMap.put(path, rl);
		return rl;
	}
}
