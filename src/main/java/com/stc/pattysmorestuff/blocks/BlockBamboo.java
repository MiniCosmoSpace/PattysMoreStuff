package com.stc.pattysmorestuff.blocks;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.block.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockBamboo extends Block
{
    public BlockBamboo(final String name, final Block.Properties properties) {
        super(properties.hardnessAndResistance(1.0f).sound(SoundType.BAMBOO));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack((IItemProvider)this));
    }
}
