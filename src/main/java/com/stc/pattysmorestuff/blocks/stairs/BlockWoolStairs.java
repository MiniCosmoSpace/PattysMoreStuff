package com.stc.pattysmorestuff.blocks.stairs;

import net.minecraft.block.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockWoolStairs extends StairsBlock
{
    public BlockWoolStairs(final String name, final BlockState defaultState, final Block.Properties properties) {
        super(defaultState, properties.hardnessAndResistance(0.8f));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack((IItemProvider)this));
    }
}
