package com.stc.pattysmorestuff.blocks.colored;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.block.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockColoredButton extends StoneButtonBlock
{
    public BlockColoredButton(final String name, final Block.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysButtons)).setRegistryName(this.getRegistryName()));
        }
    }
    
    protected SoundEvent getSoundEvent(final boolean p_196369_1_) {
        return p_196369_1_ ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack((IItemProvider)this));
    }
}
