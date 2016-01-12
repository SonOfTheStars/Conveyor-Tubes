package com.SOTS.conveyortubes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.SOTS.conveyortubes.proxies.CommonProxy;


@Mod(modid = ModConveyorTubes.MODID, name = ModConveyorTubes.MODNAME, version = ModConveyorTubes.VERSION)
public class ModConveyorTubes {

    public static final String MODID = "conveyortubes";
    public static final String MODNAME = "Conveyor Tubes";
    public static final String VERSION = "0.0.1";
    
    //Singleton-Instance of our Mod for further reference
    @Instance
    public static ModConveyorTubes instance = new ModConveyorTubes();
    
    //Proxy Storage Object. Value Determined by @SidedProxy
    @SidedProxy(clientSide="com.SOTS.conveyortubes.proxies.ClientProxy", serverSide="com.SOTS.conveyortubes.proxies.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
    	this.proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	this.proxy.postInit(e);
    }
}
