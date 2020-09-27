package com.stc.pattysmorestuff.blocks.crusher;

import net.minecraft.state.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.block.*;

public abstract class CustomMachineBlock extends ContainerBlock
{
    public static final DirectionProperty HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE;
    
    public CustomMachineBlock(final Block.Properties builder) {
        super(builder);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)CustomMachineBlock.HORIZONTAL_FACING, (Comparable)Direction.NORTH)).with((IProperty)CustomMachineBlock.ACTIVE, (Comparable)false));
    }
    
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[] { (IProperty)CustomMachineBlock.HORIZONTAL_FACING, (IProperty)CustomMachineBlock.ACTIVE });
    }
    
    public abstract void openContainer(final World p0, final BlockPos p1, final PlayerEntity p2);
    
    public boolean onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            this.openContainer(worldIn, pos, player);
        }
        return true;
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return (BlockState)this.getDefaultState().with((IProperty)CustomMachineBlock.HORIZONTAL_FACING, (Comparable)context.getPlacementHorizontalFacing().getOpposite());
    }
    
    public BlockRenderType getRenderType(final BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }
    
    public boolean isSolid(final BlockState state) {
        return true;
    }
    
    public boolean hasTileEntity() {
        return true;
    }
    
    static {
        HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
        ACTIVE = BooleanProperty.create("active");
    }
}
