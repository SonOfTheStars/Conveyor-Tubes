package com.caledios.conveyortubes.block.pipe;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.caledios.conveyortubes.ModConveyorTubes;
import com.caledios.conveyortubes.tileentity.TileBasicPipe;
import com.google.common.collect.Lists;

public class BlockChassisTierI extends Block implements ITileEntityProvider{
	
	public static final String name = "tube_model";
	private ExtendedBlockState state = new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{OBJModel.OBJProperty.instance});


	
	public BlockChassisTierI() {
		super(Material.iron);
		this.setUnlocalizedName(ModConveyorTubes.MODID + ":" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	private List<String> checkConnections(IBlockAccess world, BlockPos pos){
		List<String> connections = Lists.newArrayList("CENTER");
		if (world.getBlockState(pos.north()) ==this.getDefaultState() && !world.isAirBlock(pos.north())) connections.add("UP");
		if (world.getBlockState(pos.south()) ==this.getDefaultState() && !world.isAirBlock(pos.south())) connections.add("DOWN");
		if (world.getBlockState(pos.east()) ==this.getDefaultState() && !world.isAirBlock(pos.east())) connections.add("EAST");
		if (world.getBlockState(pos.west()) ==this.getDefaultState() && !world.isAirBlock(pos.west())) connections.add("WEST");
		if (world.getBlockState(pos.up()) ==this.getDefaultState() && !world.isAirBlock(pos.up())) connections.add("SOUTH");
		if (world.getBlockState(pos.down()) ==this.getDefaultState() && !world.isAirBlock(pos.down())) connections.add("NORTH");
		return connections;
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{ return EnumWorldBlockLayer.CUTOUT; }

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
		OBJModel.OBJState objState = (new OBJModel.OBJState(this.checkConnections(world, pos), true));
		return ((IExtendedBlockState) this.state.getBaseState()).withProperty(OBJModel.OBJProperty.instance, objState);
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
}
