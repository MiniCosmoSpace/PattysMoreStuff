package com.stc.pattysmorestuff.blocks;

import net.minecraft.block.*;
import net.minecraft.state.properties.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.state.*;
import net.minecraft.util.math.shapes.*;
import net.minecraft.fluid.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.block.material.*;

public class BlockConvayorBase extends Block implements IBucketPickupHandler, ILiquidContainer
{
    public static final Block.Properties BLOCK_PROPERTIES;
    public static final VoxelShape BOUNDS;
    public static final VoxelShape EFFECT_BOUNDS;
    private final CollisionEffect collisionEffect;
    
    public BlockConvayorBase() {
        this(null);
    }
    
    public BlockConvayorBase(final CollisionEffect collisionEffect) {
        this(BlockConvayorBase.BLOCK_PROPERTIES, collisionEffect);
    }
    
    public BlockConvayorBase(final Block.Properties properties, final CollisionEffect collisionEffect) {
        super(properties);
        this.collisionEffect = collisionEffect;
        BlockState defaultState = (BlockState)this.stateContainer.getBaseState();
        defaultState = (BlockState)defaultState.with((IProperty)BlockStateProperties.POWERED, (Comparable)false);
        defaultState = (BlockState)defaultState.with((IProperty)BlockStateProperties.WATERLOGGED, (Comparable)false);
        this.setDefaultState(defaultState);
    }
    
    public void onEntityCollision(final BlockState state, final World world, final BlockPos pos, final Entity entity) {
        if (this.collisionEffect != null && !(boolean)state.get((IProperty)BlockStateProperties.POWERED) && BlockConvayorBase.EFFECT_BOUNDS.getBoundingBox().offset(pos).intersects(entity.getBoundingBox())) {
            this.collisionEffect.apply(state, world, pos, entity);
        }
    }
    
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer((StateContainer.Builder)builder);
        builder.add(new IProperty[] { (IProperty)BlockStateProperties.POWERED, (IProperty)BlockStateProperties.WATERLOGGED });
    }
    
    public VoxelShape getShape(final BlockState state, final IBlockReader world, final BlockPos pos, final ISelectionContext context) {
        return BlockConvayorBase.BOUNDS;
    }
    
    public boolean canSpawnInBlock() {
        return true;
    }
    
    public boolean canContainFluid(final IBlockReader worldIn, final BlockPos pos, final BlockState state, final Fluid fluidIn) {
        return true;
    }
    
    public boolean receiveFluid(final IWorld worldIn, final BlockPos pos, final BlockState state, final IFluidState fluidStateIn) {
        if (!(boolean)state.get((IProperty)BlockStateProperties.WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER && !worldIn.isRemote()) {
            worldIn.setBlockState(pos, (BlockState)state.with((IProperty)BlockStateProperties.WATERLOGGED, (Comparable)true), 3);
            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate((IWorldReader)worldIn));
        }
        return false;
    }
    
    public Fluid pickupFluid(final IWorld worldIn, final BlockPos pos, final BlockState state) {
        if ((boolean) state.get((IProperty)BlockStateProperties.WATERLOGGED)) {
            worldIn.setBlockState(pos, (BlockState)state.with((IProperty)BlockStateProperties.WATERLOGGED, (Comparable)false), 3);
            return (Fluid)Fluids.WATER;
        }
        return Fluids.EMPTY;
    }
    
    public IFluidState getFluidState(final BlockState state) {
        return (boolean) state.get((IProperty)BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
    
    public BlockState updatePostPlacement(final BlockState stateIn, final Direction facing, final BlockState facingState, final IWorld worldIn, final BlockPos currentPos, final BlockPos facingPos) {
        if ((boolean) stateIn.get((IProperty)BlockStateProperties.WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate((IWorldReader)worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        final IFluidState preExistingFluidState = context.getWorld().getFluidState(context.getPos());
        BlockState placedState = super.getStateForPlacement(context);
        placedState = (BlockState)placedState.with((IProperty)BlockStateProperties.WATERLOGGED, (Comparable)(preExistingFluidState.getFluid() == Fluids.WATER));
        placedState = (BlockState)placedState.with((IProperty)BlockStateProperties.POWERED, (Comparable)context.getWorld().isBlockPowered(context.getPos()));
        return placedState;
    }
    
    public void neighborChanged(final BlockState state, final World worldIn, final BlockPos pos, final Block blockIn, final BlockPos fromPos, final boolean isMoving) {
        if (!worldIn.isRemote) {
            worldIn.setBlockState(pos, (BlockState)state.with((IProperty)BlockStateProperties.POWERED, (Comparable)worldIn.isBlockPowered(pos)));
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }
    
    static {
        BLOCK_PROPERTIES = Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(3.0f, 10.0f);
        BOUNDS = Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
        EFFECT_BOUNDS = Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    }
}
