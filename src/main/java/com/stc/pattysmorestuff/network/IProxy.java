package com.stc.pattysmorestuff.network;

import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;

public interface IProxy
{
    void setup(final FMLCommonSetupEvent p0);
    
    PlayerEntity getClientPlayer();
    
    World getClientWorld();
}
