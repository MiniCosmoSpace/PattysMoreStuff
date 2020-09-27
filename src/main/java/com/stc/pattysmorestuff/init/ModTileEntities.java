package com.stc.pattysmorestuff.init;

import com.stc.pattysmorestuff.PattysMoreStuff;
import com.stc.pattysmorestuff.blocks.crusher.CrusherTileEntity;
import com.stc.pattysmorestuff.config.ConfigGeneral;
import com.stc.pattysmorestuff.tileentity.*;
import com.stc.pattysmorestuff.tileentity.crates.*;
import com.stc.pattysmorestuff.util.TileEntityNames;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = PattysMoreStuff.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities {

    private static final List<TileEntityType> TILE_ENTITY_TYPES = new ArrayList<>();

    public static final TileEntityType<IronFurnaceTileEntity> IRON_FURNACE_TE = buildType(TileEntityNames.IRON_FURNACE, TileEntityType.Builder.create(IronFurnaceTileEntity::new, ModBlocks.iron_furnace));
    public static final TileEntityType<GoldFurnaceTileEntity> GOLD_FURNACE_TE = buildType(TileEntityNames.GOLD_FURNACE, TileEntityType.Builder.create(GoldFurnaceTileEntity::new, ModBlocks.gold_furnace));
    public static final TileEntityType<DiamondFurnaceTileEntity> DIAMOND_FURNACE_TE = buildType(TileEntityNames.DIAMOND_FURNACE, TileEntityType.Builder.create(DiamondFurnaceTileEntity::new, ModBlocks.diamond_furnace));
    public static final TileEntityType<EmeraldFurnaceTileEntity> EMERALD_FURNACE_TE = buildType(TileEntityNames.EMERALD_FURNACE, TileEntityType.Builder.create(EmeraldFurnaceTileEntity::new, ModBlocks.emerald_furnace));
    public static final TileEntityType<ObsidianFurnaceTileEntity> OBSIDIAN_FURNACE_TE = buildType(TileEntityNames.OBSIDIAN_FURNACE, TileEntityType.Builder.create(ObsidianFurnaceTileEntity::new, ModBlocks.obsidian_furnace));
    public static final TileEntityType<UltimateFurnaceTileEntity> ULTIMATE_FURNACE_TE = buildType(TileEntityNames.ULTIMATE_FURNACE, TileEntityType.Builder.create(UltimateFurnaceTileEntity::new, ModBlocks.ultimate_furnace));

    public static final TileEntityType<OakCrateTileEntity> OAK_STORAGE_CRATE_TE = buildType(TileEntityNames.OAK_CRATE, TileEntityType.Builder.create(OakCrateTileEntity::new, ModBlocks.oak_storage_crate));
    public static final TileEntityType<SpruceCrateTileEntity> SPRUCE_STORAGE_CRATE_TE = buildType(TileEntityNames.SPRUCE_CRATE, TileEntityType.Builder.create(SpruceCrateTileEntity::new, ModBlocks.spruce_storage_crate));
    public static final TileEntityType<BirchCrateTileEntity> BIRCH_STORAGE_CRATE_TE = buildType(TileEntityNames.BIRCH_CRATE, TileEntityType.Builder.create(BirchCrateTileEntity::new, ModBlocks.birch_storage_crate));
    public static final TileEntityType<JungleCrateTileEntity> JUNGLE_STORAGE_CRATE_TE = buildType(TileEntityNames.JUNGLE_CRATE, TileEntityType.Builder.create(JungleCrateTileEntity::new, ModBlocks.jungle_storage_crate));
    public static final TileEntityType<AcaciaCrateTileEntity> ACACIA_STORAGE_CRATE_TE = buildType(TileEntityNames.ACACIA_CRATE, TileEntityType.Builder.create(AcaciaCrateTileEntity::new, ModBlocks.acacia_storage_crate));
    public static final TileEntityType<DarkOakCrateTileEntity> DARK_OAK_STORAGE_CRATE_TE = buildType(TileEntityNames.DARK_OAK_CRATE, TileEntityType.Builder.create(DarkOakCrateTileEntity::new, ModBlocks.big_oak_storage_crate));

    public static final TileEntityType<CrusherTileEntity> CRUSHER = buildType(TileEntityNames.CRUSHER, TileEntityType.Builder.create(CrusherTileEntity::new, ModBlocks.crusher));

    //public static final TileEntityType<TileEntityRedJar> RED_JAR = buildType(TileEntityNames.RED_JAR, TileEntityType.Builder.create(TileEntityRedJar::new, ModBlocks.cookie_jar_red));


    private static <T extends TileEntity> TileEntityType<T> buildType(String id, TileEntityType.Builder<T> builder)
    {
        TileEntityType<T> type = builder.build(null);
        type.setRegistryName(id);
        TILE_ENTITY_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public static void registerTypes(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        TILE_ENTITY_TYPES.forEach(type -> event.getRegistry().register(type));
        TILE_ENTITY_TYPES.clear();
    }
}