package com.stc.pattysmorestuff.blocks;

import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;

public class BlockNetherStar extends BlockBase
{
    public BlockNetherStar(final String name, final Block.Properties properties) {
        super(name, properties);
    }
    
    public boolean isBeaconBase(final BlockState state, final IWorldReader world, final BlockPos pos, final BlockPos beacon) {
        return true;
    }
}
