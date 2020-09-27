package com.stc.pattysmorestuff.items;

import net.minecraft.item.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;

public class ItemKnife extends Item
{
    public ItemKnife(final String name, final Item.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableFood.get()) {
            ModItems.ITEMS.add(this);
        }
    }
}
