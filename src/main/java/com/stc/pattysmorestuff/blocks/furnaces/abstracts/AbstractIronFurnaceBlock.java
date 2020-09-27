package com.stc.pattysmorestuff.blocks.furnaces.abstracts;

import net.minecraft.state.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import com.stc.pattysmorestuff.tileentity.abstracts.*;
import net.minecraft.inventory.container.*;
import net.minecraft.stats.*;
import net.minecraft.tileentity.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.particles.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.block.*;

public abstract class AbstractIronFurnaceBlock extends AbstractFurnaceBlock
{
    public static final DirectionProperty FACING;
    public static final BooleanProperty LIT;
    
    protected AbstractIronFurnaceBlock(final Block.Properties p_i50000_1_) {
        super(p_i50000_1_);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)AbstractIronFurnaceBlock.FACING, (Comparable)Direction.NORTH)).with((IProperty)AbstractIronFurnaceBlock.LIT, (Comparable)false));
    }
    
    protected void interactWith(final World worldIn, final BlockPos pos, final PlayerEntity player) {
        final TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof AbstractIronFurnaceTileEntity) {
            player.openContainer((INamedContainerProvider)tileentity);
            player.addStat(Stats.INTERACT_WITH_FURNACE);
        }
    }
    
    @OnlyIn(Dist.CLIENT)
    public void animateTick(final BlockState stateIn, final World worldIn, final BlockPos pos, final Random rand) {
        if (stateIn.get(AbstractIronFurnaceBlock.LIT)) {
            final double d0 = pos.getX() + 0.5;
            final double d2 = pos.getY();
            final double d3 = pos.getZ() + 0.5;
            if (rand.nextDouble() < 0.1) {
                worldIn.playSound(d0, d2, d3, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
            }
            final Direction direction = (Direction)stateIn.get((IProperty)AbstractIronFurnaceBlock.FACING);
            final Direction.Axis direction$axis = direction.getAxis();
            final double d4 = 0.52;
            final double d5 = rand.nextDouble() * 0.6 - 0.3;
            final double d6 = (direction$axis == Direction.Axis.X) ? (direction.getXOffset() * 0.52) : d5;
            final double d7 = rand.nextDouble() * 6.0 / 16.0;
            final double d8 = (direction$axis == Direction.Axis.Z) ? (direction.getZOffset() * 0.52) : d5;
            worldIn.addParticle((IParticleData)ParticleTypes.SMOKE, d0 + d6, d2 + d7, d3 + d8, 0.0, 0.0, 0.0);
            worldIn.addParticle((IParticleData)ParticleTypes.FLAME, d0 + d6, d2 + d7, d3 + d8, 0.0, 0.0, 0.0);
        }
    }
    
    public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final BlockState state, final LivingEntity placer, final ItemStack stack) {
        if (stack.hasDisplayName()) {
            final TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof AbstractIronFurnaceTileEntity) {
                ((AbstractIronFurnaceTileEntity)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }
    
    public void onReplaced(final BlockState state, final World worldIn, final BlockPos pos, final BlockState newState, final boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            final TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof AbstractIronFurnaceTileEntity) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
                worldIn.updateComparatorOutputLevel(pos, (Block)this);
            }
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }
    
    static {
        FACING = HorizontalBlock.HORIZONTAL_FACING;
        LIT = RedstoneTorchBlock.LIT;
    }
}
