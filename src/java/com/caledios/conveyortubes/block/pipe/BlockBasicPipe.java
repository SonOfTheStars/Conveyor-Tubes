package com.caledios.conveyortubes.block.pipe;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
import com.caledios.conveyortubes.util.References;
import com.google.common.collect.Lists;

public class BlockBasicPipe extends Block{
	
	public static final String name = "tube_model";
	private ExtendedBlockState state = new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{OBJModel.OBJProperty.instance});
	


	
	public BlockBasicPipe(){
		super(Material.iron);
		this.setUnlocalizedName(ModConveyorTubes.MODID + ":" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{ return EnumWorldBlockLayer.SOLID; }
	
	@Override
	public boolean isVisuallyOpaque() {return false;}

	@Override
	public boolean isFullCube() {return false;}
	
	@Override
	public boolean isOpaqueCube()  {return false;}
	
	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos){
		List<String> connections = Lists.newArrayList("CENTER");
		TileBasicPipe te = (TileBasicPipe) world.getTileEntity(pos);
		OBJModel.OBJState objState = null;
		if(te != null){
			objState = (new OBJModel.OBJState(te.checkConnections(world, pos), true));
		}
		else{
			objState = (new OBJModel.OBJState(connections, true));
		}
		return ((IExtendedBlockState) this.state.getBaseState()).withProperty(OBJModel.OBJProperty.instance, objState);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileBasicPipe();
	}

	@Override
	protected BlockState createBlockState() {
		return new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{OBJModel.OBJProperty.instance});
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
		setBlockBounds(References.PIPE_MIN_POS, References.PIPE_MIN_POS, References.PIPE_MIN_POS, References.PIPE_MAX_POS, References.PIPE_MAX_POS, References.PIPE_MAX_POS);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}

	@Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
}
