package com.stc.pattysmorestuff.blocks;

import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.state.properties.*;
import net.minecraft.state.*;

public class CollisionEffectPush implements CollisionEffect
{
    private final double velocity;
    
    public CollisionEffectPush(final double velocity) {
        this.velocity = velocity;
    }
    
    @Override
    public void apply(final BlockState state, final World world, final BlockPos pos, final Entity entity) {
        if (!entity.isSneaking()) {
            final Direction direction = (Direction)state.get((IProperty)BlockStateProperties.HORIZONTAL_FACING);
            entity.setMotion(entity.getMotion().add(this.velocity * direction.getXOffset(), 0.0, this.velocity * direction.getZOffset()));
            this.additionalEffects(state, world, pos, entity);
        }
    }
    
    public void additionalEffects(final BlockState state, final World world, final BlockPos pos, final Entity entity) {
    }
}
