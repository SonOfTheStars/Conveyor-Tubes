package com.caledios.conveyortubes.block.pipe;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
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
	
	public static final String name = "ConveyorTube";
	private ExtendedBlockState state = new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{OBJModel.OBJProperty.instance});


	
	public BlockCTPipe(){
		super(Material.iron);
		this.setUnlocalizedName(ModConveyorTubes.MODID + ":" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
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
		TileBasicPipe tileentity = (TileBasicPipe) world.getTileEntity(pos);
		OBJModel.OBJState retState = new OBJModel.OBJState(tileentity ==null ? Lists.newArrayList(OBJModel.Group.ALL) : tileentity.visible, true);
		return ((IExtendedBlockState) this.state.getBaseState()).withProperty(OBJModel.OBJProperty.instance, retState);
	}
	
	@Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#onBlockActivated(net.minecraft.world.World, net.minecraft.util.BlockPos, net.minecraft.block.state.IBlockState, net.minecraft.entity.player.EntityPlayer, net.minecraft.util.EnumFacing, float, float, float)
	 */
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
		
		if(worldIn.isRemote)
		{
			OBJBakedModel objBaked = (OBJBakedModel)ClientUtils.mc().getBlockRendererDispatcher().getModelFromBlockState(state, worldIn, pos);
			objBaked.scheduleRebake();
		}
		worldIn.markBlockRangeForRenderUpdate(pos, pos);
		return false;
	}
	
	
}
