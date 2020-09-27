package com.stc.pattysmorestuff.blocks.light;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.particles.*;

public class BlockLantern extends Block
{
    protected static final VoxelShape SHAPE;
    
    public BlockLantern(final String name, final Block.Properties properties) {
        super(properties.lightValue(10).hardnessAndResistance(3.0f, 3.0f));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public VoxelShape getShape(final BlockState state, final IBlockReader iBlockReader, final BlockPos pos, final ISelectionContext context) {
        return BlockLantern.SHAPE;
    }
    
    public BlockRenderType getRenderType(final BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    public boolean ticksRandomly(final BlockState state) {
        return super.ticksRandomly(state);
    }
    
    public void animateTick(final BlockState state, final World world, final BlockPos pos, final Random random) {
        final double d0 = pos.getX() + 0.5;
        final double d2 = pos.getY() + 0.3;
        final double d3 = pos.getZ() + 0.5;
        world.addParticle((IParticleData)ParticleTypes.SMOKE, d0, d2, d3, 0.0, 0.0, 0.0);
        world.addParticle((IParticleData)ParticleTypes.FLAME, d0, d2, d3, 0.0, 0.0, 0.0);
    }
    
    static {
        SHAPE = Block.makeCuboidShape(4.0, 0.0, 4.0, 12.0, 11.0, 12.0);
    }
}
