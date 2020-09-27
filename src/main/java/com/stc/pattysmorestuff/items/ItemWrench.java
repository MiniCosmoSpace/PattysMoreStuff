package com.stc.pattysmorestuff.items;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import com.stc.pattysmorestuff.util.*;
import net.minecraft.state.properties.*;
import net.minecraft.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import javax.annotation.*;
import net.minecraft.util.*;

public class ItemWrench extends Item
{
    public ItemWrench(final String name, final Item.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableWrench.get()) {
            ModItems.ITEMS.add(this);
        }
    }
    
    public ActionResultType onItemUse(final ItemUseContext context) {
        final PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.PASS;
        }
        final World world = context.getWorld();
        final BlockPos pos = context.getPos();
        final BlockState state = world.getBlockState(pos);
        final Block block = state.getBlock();
        if (state.getBlock() instanceof IWrenchable) {
            final ActionResultType result = ((IWrenchable)state.getBlock()).onWrench(context);
            if (result != ActionResultType.PASS) {
                return result;
            }
        }
        if (player.isSneaking() && state.has((IProperty)BlockStateProperties.AXIS)) {
            final BlockState state2 = cycleProperty(state, (net.minecraft.state.IProperty)BlockStateProperties.AXIS);
            world.setBlockState(pos, state2, 18);
            return ActionResultType.SUCCESS;
        }
        if (player.isSneaking() && state.has((IProperty)BlockStateProperties.HORIZONTAL_FACING)) {
            final BlockState state2 = cycleProperty(state, (net.minecraft.state.IProperty)BlockStateProperties.HORIZONTAL_FACING);
            world.setBlockState(pos, state2, 18);
            return ActionResultType.SUCCESS;
        }
        if (!player.isSneaking() && state.has((IProperty)BlockStateProperties.AXIS)) {
            final BlockState state2 = cycleProperty(state, (net.minecraft.state.IProperty)BlockStateProperties.AXIS);
            world.setBlockState(pos, state2, 18);
            return ActionResultType.SUCCESS;
        }
        if (!player.isSneaking() && state.has((IProperty)BlockStateProperties.HORIZONTAL_FACING)) {
            final BlockState state2 = cycleProperty(state, (net.minecraft.state.IProperty)BlockStateProperties.HORIZONTAL_FACING);
            world.setBlockState(pos, state2, 18);
            return ActionResultType.SUCCESS;
        }
        return super.onItemUse(context);
    }
    
    private static <T extends Comparable<T>> BlockState cycleProperty(final BlockState state, final IProperty<T> propertyIn) {
        return (BlockState)state.with((IProperty)propertyIn, (Comparable)getAdjacentValue(propertyIn.getAllowedValues(), state.get(propertyIn)));
    }
    
    private static <T> T getAdjacentValue(final Iterable<T> p_195959_0_, @Nullable final T p_195959_1_) {
        return (T)Util.getElementAfter((Iterable)p_195959_0_, (Object)p_195959_1_);
    }
}
