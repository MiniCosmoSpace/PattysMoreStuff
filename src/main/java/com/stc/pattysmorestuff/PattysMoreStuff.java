package com.stc.pattysmorestuff;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.config.*;
import com.stc.pattysmorestuff.config.*;
import net.minecraftforge.fml.loading.*;
import net.minecraftforge.fml.javafmlmod.*;
import net.minecraftforge.common.*;
import com.stc.pattysmorestuff.init.*;
import com.stc.pattysmorestuff.world.*;
import net.minecraftforge.fml.event.lifecycle.*;
import com.stc.pattysmorestuff.blocks.crusher.*;
import net.minecraft.client.gui.*;
import net.minecraft.inventory.container.*;
import net.minecraft.util.*;
import java.util.function.*;
import com.stc.pattysmorestuff.network.*;
import net.minecraftforge.fml.*;

@Mod("pattysmorestuff")
public class PattysMoreStuff
{
    public static final String MODID = "pattysmorestuff";
    public static PattysMoreStuff instance;
    public static IProxy proxy;
    
    public PattysMoreStuff() {
        PattysMoreStuff.instance = this;
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CoreConfig.SPEC, "PattysMoreStuff-General.toml");
        CoreConfig.loadConfig(CoreConfig.SPEC, FMLPaths.CONFIGDIR.get().resolve("PattysMoreStuff-General.toml".toString()));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        MinecraftForge.EVENT_BUS.register((Object)this);
        ModTabs.pattysTabs();
    }
    
    private void preInit(final FMLCommonSetupEvent event) {
        OreGenerator.setupOreGen();
        PattysMoreStuff.proxy.setup(event);
    }
    
    private void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory((ContainerType)ContainerTypes.CRUSHER, CrusherScreen::new);
    }
    
    public static ResourceLocation getId(final String path) {
        return new ResourceLocation("pattysmorestuff", path);
    }
    
    static {
        PattysMoreStuff.proxy = (IProxy)DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    }
}
