package com.stc.pattysmorestuff.blocks.fences;

import net.minecraft.util.math.shapes.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.pathfinding.*;
import net.minecraft.tags.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.fluid.*;
import net.minecraft.world.*;
import net.minecraft.state.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockDyeFence extends FourWayBlock
{
    private final VoxelShape[] renderShapes;
    
    public BlockDyeFence(final String name, final Block.Properties properties) {
        super(2.0f, 2.0f, 16.0f, 16.0f, 24.0f, properties);
        this.setRegistryName("pattysmorestuff", name);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)BlockDyeFence.NORTH, (Comparable)false)).with((IProperty)BlockDyeFence.EAST, (Comparable)false)).with((IProperty)BlockDyeFence.SOUTH, (Comparable)false)).with((IProperty)BlockDyeFence.WEST, (Comparable)false)).with((IProperty)BlockDyeFence.WATERLOGGED, (Comparable)false));
        this.renderShapes = this.makeShapes(2.0f, 1.0f, 16.0f, 6.0f, 15.0f);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public VoxelShape getRenderShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
        return this.renderShapes[this.getIndex(state)];
    }
    
    public boolean allowsMovement(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final PathType type) {
        return false;
    }
    
    public boolean func_220111_a(final BlockState p_220111_1_, final boolean p_220111_2_, final Direction p_220111_3_) {
        final Block block = p_220111_1_.getBlock();
        final boolean flag = block.isIn(BlockTags.FENCES) && p_220111_1_.getMaterial() == this.material;
        final boolean flag2 = block instanceof FenceGateBlock && FenceGateBlock.isParallel(p_220111_1_, p_220111_3_);
        return (!cannotAttach(block) && p_220111_2_) || flag || flag2;
    }
    
    public boolean onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            return LeadItem.attachToFence(player, worldIn, pos);
        }
        final ItemStack itemstack = player.getHeldItem(handIn);
        return itemstack.getItem() == Items.LEAD || itemstack.isEmpty();
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        final IBlockReader iblockreader = (IBlockReader)context.getWorld();
        final BlockPos blockpos = context.getPos();
        final IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        final BlockPos blockpos2 = blockpos.north();
        final BlockPos blockpos3 = blockpos.east();
        final BlockPos blockpos4 = blockpos.south();
        final BlockPos blockpos5 = blockpos.west();
        final BlockState blockstate = iblockreader.getBlockState(blockpos2);
        final BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
        final BlockState blockstate3 = iblockreader.getBlockState(blockpos4);
        final BlockState blockstate4 = iblockreader.getBlockState(blockpos5);
        return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)super.getStateForPlacement(context).with((IProperty)BlockDyeFence.NORTH, (Comparable)this.func_220111_a(blockstate, blockstate.func_224755_d(iblockreader, blockpos2, Direction.SOUTH), Direction.SOUTH))).with((IProperty)BlockDyeFence.EAST, (Comparable)this.func_220111_a(blockstate2, blockstate2.func_224755_d(iblockreader, blockpos3, Direction.WEST), Direction.WEST))).with((IProperty)BlockDyeFence.SOUTH, (Comparable)this.func_220111_a(blockstate3, blockstate3.func_224755_d(iblockreader, blockpos4, Direction.NORTH), Direction.NORTH))).with((IProperty)BlockDyeFence.WEST, (Comparable)this.func_220111_a(blockstate4, blockstate4.func_224755_d(iblockreader, blockpos5, Direction.EAST), Direction.EAST))).with((IProperty)BlockDyeFence.WATERLOGGED, (Comparable)(ifluidstate.getFluid() == Fluids.WATER));
    }
    
    public BlockState updatePostPlacement(final BlockState stateIn, final Direction facing, final BlockState facingState, final IWorld worldIn, final BlockPos currentPos, final BlockPos facingPos) {
        if ((boolean) stateIn.get((IProperty)BlockDyeFence.WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate((IWorldReader)worldIn));
        }
        return (BlockState)((facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL) ? stateIn.with((IProperty)BlockDyeFence.FACING_TO_PROPERTY_MAP.get(facing), (Comparable)this.func_220111_a(facingState, facingState.func_224755_d((IBlockReader)worldIn, facingPos, facing.getOpposite()), facing.getOpposite())) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos));
    }
    
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[] { (IProperty)BlockDyeFence.NORTH, (IProperty)BlockDyeFence.EAST, (IProperty)BlockDyeFence.WEST, (IProperty)BlockDyeFence.SOUTH, (IProperty)BlockDyeFence.WATERLOGGED });
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack((IItemProvider)this));
    }
}
