package com.stc.pattysmorestuff.blocks.light;

import com.stc.pattysmorestuff.PattysMoreStuff;
import com.stc.pattysmorestuff.config.ConfigGeneral;
import com.stc.pattysmorestuff.init.ModBlocks;
import com.stc.pattysmorestuff.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockIllumination extends Block implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BlockIllumination(String name, Properties properties) {
        super(properties.lightValue(15));

        this.setRegistryName(PattysMoreStuff.MODID, name);
        if(ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add(new BlockItem(this, new Item.Properties()).setRegistryName(this.getRegistryName()));
        }

        setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public boolean isAir(BlockState state, IBlockReader world, BlockPos pos)
    {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_)
    {
        return (EffectiveSide.get() == LogicalSide.CLIENT && hasItem()) ? VoxelShapes.fullCube() : VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_)
    {
        return VoxelShapes.empty();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (hasItem())
        {
            float x = pos.getX() + 0.3F + rand.nextFloat() * 0.4F;
            float y = pos.getY() + 0.5F;
            float z = pos.getZ() + 0.3F + rand.nextFloat() * 0.4F;
        }
        super.animateTick(stateIn, worldIn, pos, rand);
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean hasItem()
    {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null)
        {
            return false;
        }
        Item main = player.getHeldItemMainhand().getItem();
        Item off = player.getHeldItemOffhand().getItem();
        return main == ModItems.light_wand || off == ModItems.light_wand;
    }



    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return (FluidState) (state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        IBlockReader iBlockReader = context.getWorld();
        BlockPos pos = context.getPos();
        FluidState iFluidState = (FluidState) context.getWorld().getFluidState(pos);
        return super.getStateForPlacement(context).with(WATERLOGGED, iFluidState.getFluid() == Fluids.WATER);
    }
}
