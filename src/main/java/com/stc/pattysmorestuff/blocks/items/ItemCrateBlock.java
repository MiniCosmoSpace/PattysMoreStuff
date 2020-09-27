package com.stc.pattysmorestuff.blocks.items;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class ItemCrateBlock extends BlockItem
{
    protected final Block block;
    
    public ItemCrateBlock(final Block blockIn, final Item.Properties builder) {
        super(blockIn, builder);
        this.block = blockIn;
    }
    
    public int getBurnTime(final ItemStack itemStack) {
        return 15;
    }
}
