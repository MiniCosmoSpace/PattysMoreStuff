package com.stc.pattysmorestuff.network;

import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;

public class ServerProxy implements IProxy
{
    @Override
    public void setup(final FMLCommonSetupEvent event) {
    }
    
    @Override
    public PlayerEntity getClientPlayer() {
        throw new IllegalStateException("Can't call this server-side!");
    }
    
    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Can't call this server-side!");
    }
}
