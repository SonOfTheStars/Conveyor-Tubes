package com.caledios.conveyortubes.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.obj.OBJModel;

public class TileBasicPipe extends TileEntity{
	
	public List<String> visible = new ArrayList<String>();
	
	public TileBasicPipe(){
		this.visible.add(OBJModel.Group.ALL);
	}
}
