package com.stc.pattysmorestuff.blocks;

import net.minecraft.block.*;
import net.minecraft.state.properties.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.state.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class BlockConvayor extends BlockConvayorBase
{
    public BlockConvayor(final String name, final CollisionEffect collisionEffect) {
        this(name, BlockConvayor.BLOCK_PROPERTIES, collisionEffect);
    }
    
    public BlockConvayor(final String name, final Block.Properties properties, final CollisionEffect collisionEffect) {
        super(properties, collisionEffect);
        this.setRegistryName("pattysmorestuff", name);
        this.setDefaultState((BlockState)this.getDefaultState().with((IProperty)BlockStateProperties.HORIZONTAL_FACING, (Comparable)Direction.NORTH));
    }
    
    public boolean onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
        if (player.isSneaking()) {
            worldIn.setBlockState(pos, this.rotate(state, Rotation.CLOCKWISE_90));
            return true;
        }
        return false;
    }
    
    @Override
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(new IProperty[] { (IProperty)BlockStateProperties.HORIZONTAL_FACING });
    }
    
    @Override
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        BlockState placedState = super.getStateForPlacement(context);
        for (final Direction facing : context.getNearestLookingDirections()) {
            if (facing.getAxis().isHorizontal()) {
                placedState = (BlockState)placedState.with((IProperty)BlockStateProperties.HORIZONTAL_FACING, (Comparable)facing);
                break;
            }
        }
        return placedState;
    }
    
    public BlockState rotate(final BlockState state, final Rotation rot) {
        return (BlockState)state.with((IProperty)BlockStateProperties.HORIZONTAL_FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)BlockStateProperties.HORIZONTAL_FACING)));
    }
    
    public BlockState mirror(final BlockState state, final Mirror mirror) {
        return (BlockState)state.with((IProperty)BlockStateProperties.HORIZONTAL_FACING, (Comparable)mirror.mirror((Direction)state.get((IProperty)BlockStateProperties.HORIZONTAL_FACING)));
    }
}
