package com.stc.pattysmorestuff.blocks;

import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

@FunctionalInterface
public interface CollisionEffect
{
    void apply(final BlockState p0, final World p1, final BlockPos p2, final Entity p3);
}
