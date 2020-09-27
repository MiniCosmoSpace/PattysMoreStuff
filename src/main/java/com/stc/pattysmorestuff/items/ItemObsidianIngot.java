package com.stc.pattysmorestuff.items;

import net.minecraft.item.*;
import com.stc.pattysmorestuff.init.*;

public class ItemObsidianIngot extends Item
{
    public ItemObsidianIngot(final String name, final Item.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        ModItems.ITEMS.add(this);
    }
}
