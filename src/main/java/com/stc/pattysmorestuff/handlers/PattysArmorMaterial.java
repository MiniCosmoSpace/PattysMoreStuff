package com.stc.pattysmorestuff.handlers;

import net.minecraft.item.crafting.*;
import java.util.function.*;
import net.minecraft.inventory.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public enum PattysArmorMaterial implements IArmorMaterial
{
    NSTAR("pattysmorestuff:nether_star", 66, new int[] { 6, 9, 11, 7 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 6.5f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.NETHER_STAR })), 
    EMERALD("pattysmorestuff:emerald", 36, new int[] { 4, 7, 9, 4 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    OBSIDIAN("pattysmorestuff:obsidian", 40, new int[] { 2, 3, 2, 2 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    ENDER("pattysmorestuff:ender", 12, new int[] { 1, 5, 6, 2 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    MAGMA_CREAM("pattysmorestuff:megma_cream", 12, new int[] { 1, 5, 6, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    BLAZE("pattysmorestuff:blaze", 11, new int[] { 1, 5, 6, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    QUARTZ("pattysmorestuff:quartz", 11, new int[] { 1, 4, 5, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    GLOWSTONE("pattysmorestuff:glowstone", 11, new int[] { 1, 4, 5, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    REDSTONE("pattysmorestuff:redstone", 11, new int[] { 1, 4, 5, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    COAL("pattysmorestuff:coal", 10, new int[] { 1, 3, 4, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    BRICK("pattysmorestuff:brick", 8, new int[] { 1, 3, 3, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    NETHER_BRICK("pattysmorestuff:nether_brick", 8, new int[] { 1, 3, 3, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    RED_NETHER_BRICK("pattysmorestuff:red_nether_brick", 8, new int[] { 1, 3, 3, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    SANDSTONE("pattysmorestuff:sandstone", 8, new int[] { 1, 3, 3, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    SOUL_SAND("pattysmorestuff:soul_sand", 8, new int[] { 1, 3, 3, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD })), 
    MINER("pattysmorestuff:miner", 15, new int[] { 1, 4, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.EMERALD }));
    
    private static final int[] MAX_DAMAGE_ARRAY;
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyLoadBase<Ingredient> repairMaterial;
    
    private PattysArmorMaterial(final String nameIn, final int p_i48533_4_, final int[] p_i48533_5_, final int p_i48533_6_, final SoundEvent p_i48533_7_, final float p_i48533_8_, final Supplier<Ingredient> p_i48533_9_) {
        this.name = nameIn;
        this.maxDamageFactor = p_i48533_4_;
        this.damageReductionAmountArray = p_i48533_5_;
        this.enchantability = p_i48533_6_;
        this.soundEvent = p_i48533_7_;
        this.toughness = p_i48533_8_;
        this.repairMaterial = (LazyLoadBase<Ingredient>)new LazyLoadBase((Supplier)p_i48533_9_);
    }
    
    public int getDurability(final EquipmentSlotType slotIn) {
        return PattysArmorMaterial.MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }
    
    public int getDamageReductionAmount(final EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }
    
    public int getEnchantability() {
        return this.enchantability;
    }
    
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }
    
    public Ingredient getRepairMaterial() {
        return (Ingredient)this.repairMaterial.getValue();
    }
    
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }
    
    public float getToughness() {
        return this.toughness;
    }
    
    static {
        MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
    }
}
