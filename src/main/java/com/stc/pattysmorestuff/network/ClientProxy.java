package com.stc.pattysmorestuff.network;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.client.model.obj.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import net.minecraft.world.*;

@Mod.EventBusSubscriber({ Dist.CLIENT })
public class ClientProxy implements IProxy
{
    @Override
    public void setup(final FMLCommonSetupEvent event) {
        OBJLoader.INSTANCE.addDomain("pattysmorestuff");
    }
    
    @Override
    public PlayerEntity getClientPlayer() {
        return (PlayerEntity)Minecraft.getInstance().player;
    }
    
    @Override
    public World getClientWorld() {
        return (World)Minecraft.getInstance().world;
    }
}
