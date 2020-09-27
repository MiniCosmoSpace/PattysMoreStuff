package com.stc.pattysmorestuff.config;

import net.minecraftforge.common.*;

public class ConfigGeneral
{
    public static ForgeConfigSpec.BooleanValue disableArmor;
    public static ForgeConfigSpec.BooleanValue disableTools;
    public static ForgeConfigSpec.BooleanValue disableFood;
    public static ForgeConfigSpec.BooleanValue disableDrinks;
    public static ForgeConfigSpec.BooleanValue disableBlocks;
    public static ForgeConfigSpec.BooleanValue disableFurnaces;
    public static ForgeConfigSpec.BooleanValue disableBows;
    public static ForgeConfigSpec.BooleanValue disableHammers;
    public static ForgeConfigSpec.BooleanValue disableScythes;
    public static ForgeConfigSpec.BooleanValue disableExcavator;
    public static ForgeConfigSpec.BooleanValue disablePaxel;
    public static ForgeConfigSpec.BooleanValue disableLumberAxe;
    public static ForgeConfigSpec.BooleanValue disableFlightRing;
    public static ForgeConfigSpec.BooleanValue disableTimeWand;
    public static ForgeConfigSpec.BooleanValue disableWeatherWand;
    public static ForgeConfigSpec.BooleanValue disableMagnet;
    public static ForgeConfigSpec.BooleanValue disableWrench;
    public static ForgeConfigSpec.BooleanValue disableIlluminationWand;
    public static ForgeConfigSpec.BooleanValue disableInfiniteWaterBucket;
    public static ForgeConfigSpec.BooleanValue disableInfiniteLavaBucket;
    
    public static void init(final ForgeConfigSpec.Builder builder) {
        builder.comment("Enable or disable armor sets").push("Armor");
        ConfigGeneral.disableArmor = builder.comment("Setting this to false will disable all the armor [true /false default: true]").define("EnableArmor", true);
        builder.pop();
        builder.comment("Enable or disable tools").push("Tools");
        ConfigGeneral.disableTools = builder.comment("Setting this to false will disable all the tools [true /false default: true]").define("EnableTools", true);
        builder.pop();
        builder.comment("Enable or disable Food").push("Food");
        ConfigGeneral.disableFood = builder.comment("Setting this to false will disable all the Food [true /false default: true]").define("EnableFood", true);
        builder.pop();
        builder.comment("Enable or disable Drinks").push("Drinks");
        ConfigGeneral.disableDrinks = builder.comment("Setting this to false will disable all the drinks [true /false default: true]").define("EnableDrinks", true);
        builder.pop();
        builder.comment("Enable or disable Blocks").push("Blocks");
        ConfigGeneral.disableBlocks = builder.comment("Setting this to false will disable all the blocks [true /false default: true]").define("EnableBlocks", true);
        builder.pop();
        builder.comment("Enable or disable Furnaces").push("Furnaces");
        ConfigGeneral.disableFurnaces = builder.comment("Setting this to false will disable all the furnaces [true /false default: true]").define("EnableFurnaces", true);
        builder.pop();
        builder.comment("Enable or disable Bows").push("Bows");
        ConfigGeneral.disableBows = builder.comment("Setting this to false will disable all the bows [true /false default: true]").define("EnableBows", true);
        builder.pop();
        builder.comment("Enable or disable Hammers").push("Hammers");
        ConfigGeneral.disableHammers = builder.comment("Setting this to false will disable all the hammers [true /false default: true]").define("EnableHammers", true);
        builder.pop();
        builder.comment("Enable or disable Scythes").push("Scythes");
        ConfigGeneral.disableScythes = builder.comment("Setting this to false will disable all the scythes [true /false default: true]").define("EnableScythes", true);
        builder.pop();
        builder.comment("Enable or disable Excavator").push("Excavator");
        ConfigGeneral.disableExcavator = builder.comment("Setting this to false will disable all the excavators [true /false default: true]").define("EnableExcavator", true);
        builder.pop();
        builder.comment("Enable or disable Paxel").push("Paxel");
        ConfigGeneral.disablePaxel = builder.comment("Setting this to false will disable all the paxels [true /false default: true]").define("EnablePaxel", true);
        builder.pop();
        builder.comment("Enable or disable Lumber Axe").push("LumberAxes");
        ConfigGeneral.disableLumberAxe = builder.comment("Setting this to false will disable all the lumber axes [true /false default: true]").define("EnableLumberAxe", true);
        builder.pop();
        builder.comment("Enable or disable Ring of Flight").push("RingOfFlight");
        ConfigGeneral.disableFlightRing = builder.comment("Setting this to false will disable the ring of flight [true /false default: true]").define("EnableRingOfFlight", true);
        builder.pop();
        builder.comment("Enable or disable Infinite Buckets").push("InfiniteBuckets");
        ConfigGeneral.disableInfiniteWaterBucket = builder.comment("Setting this to false will disable the infinite water buckets [true /false default: true]").define("EnableInfiniteWaterBucket", true);
        builder.pop();
        builder.comment("Enable or disable Infinite Buckets").push("InfiniteBuckets");
        ConfigGeneral.disableInfiniteLavaBucket = builder.comment("Setting this to true will enable the infinite lava bucket [true /false default: false]").define("EnableInfiniteLavaBucket", false);
        builder.pop();
        builder.comment("Enable or disable Wands").push("Wands");
        ConfigGeneral.disableTimeWand = builder.comment("Setting this to false will disable the time wand [true /false default: true]").define("EnableTimeWand", true);
        builder.pop();
        builder.comment("Enable or disable Wands").push("Wands");
        ConfigGeneral.disableWeatherWand = builder.comment("Setting this to false will disable the weather wand [true /false default: true]").define("EnableWeatherWand", true);
        builder.pop();
        builder.comment("Enable or disable Wands").push("Wands");
        ConfigGeneral.disableIlluminationWand = builder.comment("Setting this to false will disable the illumination wand [true /false default: true]").define("EnableIlluminationWand", true);
        builder.pop();
        builder.comment("Enable or disable Wrench").push("Wrench");
        ConfigGeneral.disableWrench = builder.comment("Setting this to false will disable the wrench [true /false default: true]").define("EnableWrench", true);
        builder.pop();
        builder.comment("Enable or disable Magnet").push("Magnet");
        ConfigGeneral.disableMagnet = builder.comment("Setting this to false will disable the magnet [true /false default: true]").define("EnableMagnet", true);
    }
}
