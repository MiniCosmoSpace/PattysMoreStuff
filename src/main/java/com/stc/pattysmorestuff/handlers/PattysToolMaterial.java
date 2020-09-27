package com.stc.pattysmorestuff.handlers;

import net.minecraft.item.crafting.*;
import java.util.function.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import com.stc.pattysmorestuff.init.*;

    public enum PattysToolMaterial implements IItemTier {

        NSTAR(3, 14049, 24.0F, 18.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.NETHER_STAR});

        }),
        NSTAR_PAXEL(3, 15610, 25.0F, 19.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.NETHER_STAR});

        }),
        OBSIDIAN(3, 872, 6.0F, 3.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{ModItems.obsidian_ingot});

        }),
        OBSIDIAN_PAXEL(3, 2616, 7.0F, 4.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{ModItems.obsidian_ingot});

        }),
        ENDER(3, 936, 7.0F, 4.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.ENDER_PEARL});

        }),
        ENDER_PAXEL(3, 2808, 8.0F, 6.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.ENDER_PEARL});

        }),
        EMERALD(2, 825, 8.0F, 8.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.EMERALD});

        }),
        EMERALD_PAXEL(2, 2475, 9.0F, 9.0F, 20, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.EMERALD});

        }),
        QUARTZ(2, 317, 6.5F, 7.0F, 13, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.QUARTZ});

        }),
        QUARTZ_PAXEL(2, 951, 7.5F, 8.0F, 13, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.QUARTZ});

        }),
        BLAZE(2, 656, 6.0F, 5.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.BLAZE_POWDER});

        }),
        BLAZE_PAXEL(2, 1968, 7.0F, 6.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.BLAZE_POWDER});

        }),
        GLOWSTONE(2, 317, 5.0F, 2.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.GLOWSTONE_DUST});

        }),
        GLOWSTONE_PAXEL(2, 1083, 8.0F, 5.0F, 12, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.GLOWSTONE_DUST});

        }),
        REDSTONE(3, 652, 6.0F, 3.0F, 14, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.REDSTONE});

        }),
        REDSTONE_PAXEL(3, 1416, 7.0F, 6.0F, 16, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.REDSTONE});

        }),
        COAL(1, 192, 4.0F, 1.0F, 6, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.COAL});

        }),
        COAL_PAXEL(1, 576, 5.0F, 4.0F, 7, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.COAL});

        }),
        BRICK(1, 131, 4.0F, 1.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.BRICK});

        }),
        BRICK_PAXEL(1, 393, 4.0F, 5.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.BRICK});

        }),
        NBRICK(1, 131, 4.0F, 1.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.NETHER_BRICK});

        }),
        NBRICK_PAXEL(1, 393, 4.0F, 5.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.NETHER_BRICK});

        }),
        NETHERBRICKRED(1, 131, 4.0F, 1.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.RED_NETHER_BRICKS});

        }),
        NETHERBRICKRED_PAXEL(1, 393, 4.0F, 5.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.RED_NETHER_BRICKS});

        }),
        SANDSTONE(1, 131, 4.0F, 1.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.SANDSTONE});

        }),
        SANDSTONE_PAXEL(1, 393, 4.0F, 5.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.SANDSTONE});

        }),
        SOULSAND(1, 131, 4.0F, 1.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.SOUL_SAND});

        }),
        SOULSAND_PAXEL(1, 393, 4.0F, 5.0F, 5, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.SOUL_SAND});

        }),
        MAGMACREAM(2, 361, 6.0F, 2.0F, 14, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.MAGMA_CREAM});

        }),
        MAGMACREAM_PAXEL(2, 1608, 8.0F, 6.0F, 14, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Items.MAGMA_CREAM});

        }),
        WOOD_PAXEL(0, 177, 2.0F, 4.0F, 14, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.OAK_PLANKS});

        }),
        STONE_PAXEL(1, 393, 2.0F, 4.0F, 14, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.STONE});

        }),
        IRON_PAXEL(2, 750, 6.0F, 5.0F, 14, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.IRON_BLOCK});

        }),
        GOLD_PAXEL(0, 96, 12.0F, 3.0F, 22, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.GOLD_BLOCK});

        }),
        DIAMOND_PAXEL(3, 6768, 8.0F, 6.0F, 10, () -> {
            return Ingredient.fromItems(new IItemProvider[]{Blocks.DIAMOND_BLOCK});

        }),;
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadBase<Ingredient> repairMaterial;
    
    private PattysToolMaterial(final int harvestLevel, final int maxUses, final float efficiency, final float attackDamage, final int enchantability, final Supplier<Ingredient> p_i48458_8_) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = (LazyLoadBase<Ingredient>)new LazyLoadBase((Supplier)p_i48458_8_);
    }
    
    public int getMaxUses() {
        return this.maxUses;
    }
    
    public float getEfficiency() {
        return this.efficiency;
    }
    
    public float getAttackDamage() {
        return this.attackDamage;
    }
    
    public int getHarvestLevel() {
        return this.harvestLevel;
    }
    
    public int getEnchantability() {
        return this.enchantability;
    }
    
    public Ingredient getRepairMaterial() {
        return (Ingredient)this.repairMaterial.getValue();
    }
}
