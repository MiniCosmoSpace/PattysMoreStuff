package com.stc.pattysmorestuff.blocks.light;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

public class BlockCeilingLight extends Block
{
    protected static final VoxelShape SHAPE;
    
    public BlockCeilingLight(final String name, final Block.Properties properties) {
        super(properties.lightValue(14).hardnessAndResistance(3.0f, 3.0f));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public VoxelShape getShape(final BlockState p_220053_1_, final IBlockReader p_220053_2_, final BlockPos p_220053_3_, final ISelectionContext p_220053_4_) {
        return BlockCeilingLight.SHAPE;
    }
    
    public BlockRenderType getRenderType(final BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
    
    public boolean isNormalCube(final BlockState p_220081_1_, final IBlockReader p_220081_2_, final BlockPos p_220081_3_) {
        return false;
    }
    
    static {
        SHAPE = Block.makeCuboidShape(12.0, 8.5, 12.0, 4.0, 16.0, 4.0);
    }
}
