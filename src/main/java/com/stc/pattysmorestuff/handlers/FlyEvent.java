package com.stc.pattysmorestuff.handlers;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.event.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.eventbus.api.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FlyEvent
{
    @SubscribeEvent
    public static void onTickPlayerEvent(final TickEvent.PlayerTickEvent event) {
        final PlayerEntity player = event.player;
        if (event.player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.ring_of_flight))) {
            event.player.abilities.allowFlying = true;
        }
        else if (event.player.abilities.isFlying && !event.player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.ring_of_flight)) && !event.player.isCreative() && !event.player.isSpectator()) {
            event.player.abilities.isFlying = false;
            event.player.abilities.allowFlying = false;
        }
    }
}
