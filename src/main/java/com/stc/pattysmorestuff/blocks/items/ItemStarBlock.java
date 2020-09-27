package com.stc.pattysmorestuff.blocks.items;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class ItemStarBlock extends BlockItem
{
    protected final Block block;
    
    public ItemStarBlock(final Block blockIn, final Item.Properties builder) {
        super(blockIn, builder);
        this.block = blockIn;
    }
    
    public boolean hasEffect(final ItemStack stack) {
        return true;
    }
}
