package com.stc.pattysmorestuff.blocks;

import net.minecraft.block.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;

public class BlockKitchenFloor extends Block
{
    public BlockKitchenFloor(final String name, final Block.Properties properties) {
        super(properties.hardnessAndResistance(2.0f, 6.0f));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
}
