package com.stc.pattysmorestuff.blocks.crusher;

import net.minecraft.item.crafting.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.event.*;
import com.stc.pattysmorestuff.*;
import net.minecraftforge.registries.*;
import net.minecraftforge.eventbus.api.*;

@ObjectHolder("pattysmorestuff")
public class RecipeSerializers
{
    @ObjectHolder("crushing")
    public static final IRecipeSerializer<CrushingRecipe> CRUSHING;
    
    static {
        CRUSHING = null;
    }
    
    @Mod.EventBusSubscriber(modid = "pattysmorestuff", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration
    {
        @SubscribeEvent
        public static void onRegister(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
            final IForgeRegistry<IRecipeSerializer<?>> registry = (IForgeRegistry<IRecipeSerializer<?>>)event.getRegistry();
            registry.register(new CrushingRecipe.Serializer().setRegistryName(PattysMoreStuff.getId("crushing")));
        }
    }
}
