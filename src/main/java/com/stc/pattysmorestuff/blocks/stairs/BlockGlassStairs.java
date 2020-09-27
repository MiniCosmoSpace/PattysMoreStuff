package com.stc.pattysmorestuff.blocks.stairs;

import net.minecraft.block.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class BlockGlassStairs extends StairsBlock
{
    public BlockGlassStairs(final String name, final BlockState defaultState, final Block.Properties properties) {
        super(defaultState, properties.hardnessAndResistance(0.3f).sound(SoundType.GLASS));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
