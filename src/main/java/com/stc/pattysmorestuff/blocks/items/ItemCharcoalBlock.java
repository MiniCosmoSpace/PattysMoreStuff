package com.stc.pattysmorestuff.blocks.items;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class ItemCharcoalBlock extends BlockItem
{
    protected final Block block;
    
    public ItemCharcoalBlock(final Block blockIn, final Item.Properties builder) {
        super(blockIn, builder);
        this.block = blockIn;
    }
    
    public int getBurnTime(final ItemStack itemStack) {
        return 16000;
    }
}
