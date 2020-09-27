package com.stc.pattysmorestuff.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import com.stc.pattysmorestuff.config.*;
import net.minecraft.item.*;
import com.stc.pattysmorestuff.init.*;
import com.stc.pattysmorestuff.blocks.items.*;

public class BlockCharcoal extends Block
{
    public PlayerEntity player;
    
    public BlockCharcoal(final String name, final Block.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new ItemCharcoalBlock(this, new Item.Properties().group(ModTabs.tabPattysMisc)).setRegistryName(this.getRegistryName()));
        }
    }
}
