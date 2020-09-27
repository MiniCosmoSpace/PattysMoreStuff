package com.stc.pattysmorestuff.blocks;

import net.minecraft.block.material.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import javax.annotation.*;
import java.util.*;
import net.minecraft.state.*;
import net.minecraft.block.*;

public class BlockColoredLamp extends Block
{
    public static Boolean isActive;
    public static final BooleanProperty LIT;
    
    public BlockColoredLamp(final String name, final boolean isActive) {
        super(Block.Properties.create(new Material(MaterialColor.WHITE_TERRACOTTA, false, true, true, true, false, false, false, PushReaction.NORMAL)).hardnessAndResistance(0.3f).sound(SoundType.GLASS));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            if (!isActive) {
                ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
            }
            else {
                ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties()).setRegistryName(this.getRegistryName()));
            }
        }
        BlockColoredLamp.isActive = isActive;
    }
    
    public int getLightValue(final BlockState state) {
        return (boolean) state.get((IProperty)BlockColoredLamp.LIT) ? 15 : 0;
    }
    
    public void onBlockAdded(final BlockState state, final World worldIn, final BlockPos pos, final BlockState oldState, final boolean isMoving) {
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }
    
    @Nullable
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return (BlockState)this.getDefaultState().with((IProperty)BlockColoredLamp.LIT, (Comparable)context.getWorld().isBlockPowered(context.getPos()));
    }
    
    public void neighborChanged(final BlockState state, final World worldIn, final BlockPos pos, final Block blockIn, final BlockPos fromPos, final boolean isMoving) {
        if (!worldIn.isRemote) {
            final boolean flag = (boolean)state.get((IProperty)BlockColoredLamp.LIT);
            if (flag != worldIn.isBlockPowered(pos)) {
                if (flag) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                }
                else {
                    worldIn.setBlockState(pos, (BlockState)state.cycle((IProperty)BlockColoredLamp.LIT), 2);
                }
            }
        }
    }
    
    public void tick(final BlockState state, final World worldIn, final BlockPos pos, final Random random) {
        if (!worldIn.isRemote && (boolean)state.get((IProperty)BlockColoredLamp.LIT) && !worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, (BlockState)state.cycle((IProperty)BlockColoredLamp.LIT), 2);
        }
    }
    
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[] { (IProperty)BlockColoredLamp.LIT });
    }
    
    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}
