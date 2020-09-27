package com.stc.pattysmorestuff.handlers;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.event.*;
import net.minecraft.block.*;
import net.minecraftforge.registries.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraft.item.*;
import com.stc.pattysmorestuff.init.*;

@Mod.EventBusSubscriber(modid = "pattysmorestuff", bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisteryHandler
{
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        blockRegistryEvent.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }
    
    @SubscribeEvent
    public static void onItemRegister(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }
}
