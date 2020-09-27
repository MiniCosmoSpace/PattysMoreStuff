package com.stc.pattysmorestuff.blocks.slabs;

import net.minecraft.block.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;

public class BlockColoredSlab extends SlabBlock
{
    public BlockColoredSlab(final String name, final Block.Properties properties) {
        super(properties.hardnessAndResistance(2.0f, 6.0f));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
}
