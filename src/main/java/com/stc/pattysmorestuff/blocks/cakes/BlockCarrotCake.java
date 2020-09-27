package com.stc.pattysmorestuff.blocks.cakes;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.util.math.shapes.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.stats.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.state.*;
import net.minecraft.pathfinding.*;
import net.minecraft.state.properties.*;

public class BlockCarrotCake extends Block
{
    public static final IntegerProperty BITES;
    protected static final VoxelShape[] SHAPES;
    
    public BlockCarrotCake(final String name, final Block.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)BlockCarrotCake.BITES, (Comparable)0));
        if (ConfigGeneral.disableFood.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysFood)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        return BlockCarrotCake.SHAPES[(int)state.get((IProperty)BlockCarrotCake.BITES)];
    }
    
    public boolean onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            return this.eatCake((IWorld)worldIn, pos, state, player);
        }
        final ItemStack itemstack = player.getHeldItem(handIn);
        return this.eatCake((IWorld)worldIn, pos, state, player) || itemstack.isEmpty();
    }
    
    private boolean eatCake(final IWorld worldIn, final BlockPos pos, final BlockState state, final PlayerEntity player) {
        if (!player.canEat(false)) {
            return false;
        }
        player.addStat(Stats.EAT_CAKE_SLICE);
        player.getFoodStats().addStats(2, 0.1f);
        final int i = (int)state.get((IProperty)BlockCarrotCake.BITES);
        if (i < 6) {
            worldIn.setBlockState(pos, (BlockState)state.with((IProperty)BlockCarrotCake.BITES, (Comparable)(i + 1)), 3);
        }
        else {
            worldIn.removeBlock(pos, false);
        }
        return true;
    }
    
    public BlockState updatePostPlacement(final BlockState stateIn, final Direction facing, final BlockState facingState, final IWorld worldIn, final BlockPos currentPos, final BlockPos facingPos) {
        return (facing == Direction.DOWN && !stateIn.isValidPosition((IWorldReader)worldIn, currentPos)) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    
    public boolean isValidPosition(final BlockState state, final IWorldReader worldIn, final BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
    }
    
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[] { (IProperty)BlockCarrotCake.BITES });
    }
    
    public int getComparatorInputOverride(final BlockState blockState, final World worldIn, final BlockPos pos) {
        return (7 - (int)blockState.get((IProperty)BlockCarrotCake.BITES)) * 2;
    }
    
    public boolean hasComparatorInputOverride(final BlockState state) {
        return true;
    }
    
    public boolean allowsMovement(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final PathType type) {
        return false;
    }
    
    static {
        BITES = BlockStateProperties.BITES_0_6;
        SHAPES = new VoxelShape[] { Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.makeCuboidShape(3.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.makeCuboidShape(5.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.makeCuboidShape(7.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.makeCuboidShape(9.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.makeCuboidShape(11.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.makeCuboidShape(13.0, 0.0, 1.0, 15.0, 8.0, 15.0) };
    }
}
