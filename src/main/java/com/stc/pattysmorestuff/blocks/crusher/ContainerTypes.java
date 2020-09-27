package com.stc.pattysmorestuff.blocks.crusher;

import net.minecraft.inventory.container.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.event.*;
import com.stc.pattysmorestuff.*;
import net.minecraftforge.registries.*;
import net.minecraftforge.eventbus.api.*;

@ObjectHolder("pattysmorestuff")
public class ContainerTypes
{
    @ObjectHolder("crusher_gui")
    public static final ContainerType<CrusherContainer> CRUSHER;
    
    static {
        CRUSHER = null;
    }
    
    @Mod.EventBusSubscriber(modid = "pattysmorestuff", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration
    {
        @SubscribeEvent
        public static void onContainerTypeRegister(final RegistryEvent.Register<ContainerType<?>> event) {
            final IForgeRegistry registry = event.getRegistry();
            registry.register(new ContainerType(CrusherContainer::new).setRegistryName(PattysMoreStuff.getId("crusher_gui")));
        }
    }
}
