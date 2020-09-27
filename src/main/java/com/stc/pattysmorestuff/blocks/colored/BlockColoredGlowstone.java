package com.stc.pattysmorestuff.blocks.colored;

import net.minecraft.block.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;

public class BlockColoredGlowstone extends Block
{
    public BlockColoredGlowstone(final String name, final Block.Properties properties) {
        super(properties.hardnessAndResistance(0.3f).lightValue(15).sound(SoundType.GLASS));
        this.setRegistryName("pattysmorestuff", name);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
    }
}
