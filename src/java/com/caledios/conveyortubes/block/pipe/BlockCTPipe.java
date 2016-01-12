package com.caledios.conveyortubes.block.pipe;

import java.io.IOException;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.obj.OBJModel.OBJBakedModel;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

import com.caledios.conveyortubes.ModConveyorTubes;
import com.caledios.conveyortubes.tileentity.TileBasicPipe;
import com.caledios.conveyortubes.util.ClientUtils;
import com.google.common.collect.Lists;

public class BlockCTPipe extends Block implements ITileEntityProvider{
	
	public static final String name = "tube_model";
	private ExtendedBlockState state = new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{OBJModel.OBJProperty.instance});


	
	public BlockCTPipe(){
		super(Material.iron);
		this.setUnlocalizedName(ModConveyorTubes.MODID + ":" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	private List<String> checkConnections(IBlockAccess world, BlockPos pos){
		List<String> connections = Lists.newArrayList("center");
		if (world.getBlockState(pos.north()) != null && !world.isAirBlock(pos.north())) connections.add("north");
		if (world.getBlockState(pos.south()) != null && !world.isAirBlock(pos.south())) connections.add("south");
		if (world.getBlockState(pos.east()) != null && !world.isAirBlock(pos.east())) connections.add("east");
		if (world.getBlockState(pos.west()) != null && !world.isAirBlock(pos.west())) connections.add("west");
		if (world.getBlockState(pos.up()) != null && !world.isAirBlock(pos.up())) connections.add("up");
		if (world.getBlockState(pos.down()) != null && !world.isAirBlock(pos.down())) connections.add("down");
		return connections;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBasicPipe();
	}

	@Override
	public boolean isVisuallyOpaque() {return false;}

	@Override
	public boolean isFullCube() {return false;}
	
	@Override
	public boolean isOpaqueCube()  {return false;}
	
	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos){
		OBJModel.OBJState objState = new OBJModel.OBJState(this.checkConnections(world, pos), true).setIgnoreHidden(true);
		
	}

	@Override
	protected BlockState createBlockState() {
		return new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{OBJModel.OBJProperty.instance});
	}

	@Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

	/**
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.getTileEntity(pos) == null) worldIn.setTileEntity(pos, new TileBasicPipe());
		
		TileBasicPipe te = (TileBasicPipe) worldIn.getTileEntity(pos);
		
		IModel model = ModelLoaderRegistry.getMissingModel();
		try
		{
			model = ModelLoaderRegistry.getModel(new ResourceLocation(ModConveyorTubes.MODID.toLowerCase() + ":" + "block/ConveyorTube.obj"));
		}
		catch(IOException e)
		{
			model = ModelLoaderRegistry.getMissingModel();
		}
		worldIn.markBlockRangeForRenderUpdate(pos, pos);
		return false;
	}
	**/
	
}
