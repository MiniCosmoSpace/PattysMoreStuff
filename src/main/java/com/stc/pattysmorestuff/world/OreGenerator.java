package com.stc.pattysmorestuff.world;

import net.minecraftforge.registries.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import java.util.*;
import java.util.function.*;
import com.mojang.datafixers.*;

public class OreGenerator
{
    private static final EndOreFeature END_OREGEN;

    public static void setupOreGen() {
        for (final Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.dye_ore.getDefaultState(), 6), Placement.COUNT_RANGE, new CountRangeConfig(8, 5, 0, 16)));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.ender_pearl_ore.getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(3, 5, 0, 16)));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.dye_ore_nether.getDefaultState(), 6), Placement.COUNT_RANGE, new CountRangeConfig(8, 5, 0, 16)));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.ender_pearl_ore_nether.getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(3, 5, 0, 16)));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature((Feature)OreGenerator.END_OREGEN, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.dye_ore_end.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(8, 5, 0, 16)));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature((Feature)OreGenerator.END_OREGEN, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.ender_pearl_ore_end.getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(3, 5, 0, 16)));
        }
    }

    static {
        END_OREGEN = new EndOreFeature(null);
    }
}
