package com.stc.pattysmorestuff.items;

import net.minecraft.item.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.registries.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;

@Mod.EventBusSubscriber(modid = "pattysmorestuff", bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder("pattysmorestuff")
public class ItemFlightRing extends Item
{
    public ItemFlightRing(final String name, final Item.Properties properties) {
        super(properties.maxStackSize(1));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableFlightRing.get()) {
            ModItems.ITEMS.add(this);
        }
    }
}
