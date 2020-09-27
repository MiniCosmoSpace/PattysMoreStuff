package com.stc.pattysmorestuff.items.foods;

import net.minecraft.item.*;

public class PattysFoods
{
    public static final Food FRIED_EGG;
    public static final Food BACON_RAW;
    public static final Food BACON_COOKED;
    public static final Food SAUSAGE_RAW;
    public static final Food SAUSAGE_COOKED;
    public static final Food BAGUETTE_ROLL;
    public static final Food BAGUETTE_BACON;
    public static final Food BAGUETTE_EGG;
    public static final Food BAGUETTE_EGGBACON;
    public static final Food BAGUETTE_SAUSAGE;
    public static final Food BAGUETTE_BACONSAUSAGE;
    public static final Food BAGUETTE_EGGSAUSAGE;
    public static final Food BAGUETTE_EGGBACONSAUSAGE;
    public static final Food APPLE_PIE;
    public static final Food SWEET_BERRIE_PIE;
    public static final Food TOAST;
    public static final Food FRUIT_SALAD;
    public static final Food DOUBLE_CHOC_COOKIE;
    public static final Food APPLE_JUICE;
    public static final Food MELON_JUICE;
    public static final Food CARROT_JUICE;
    public static final Food BEETROOT_JUICE;
    public static final Food PUMPKIN_JUICE;
    public static final Food MILK_BOTTLE;
    public static final Food CHOCOLATE_MILK_BOTTLE;
    public static final Food APPLE_SLUSHIE;
    public static final Food MELON_SLUSHIE;
    public static final Food CARROT_SLUSHIE;
    public static final Food BEETROOT_SLUSHIE;
    public static final Food PUMPKIN_SLUSHIE;
    public static final Food APPLE_SMOOTHIE;
    public static final Food MELON_SMOOTHIE;
    public static final Food CARROT_SMOOTHIE;
    public static final Food BEETROOT_SMOOTHIE;
    public static final Food PUMPKIN_SMOOTHIE;
    
    static {
        FRIED_EGG = new Food.Builder().hunger(4).saturation(0.4f).build();
        BACON_RAW = new Food.Builder().hunger(3).saturation(0.3f).build();
        BACON_COOKED = new Food.Builder().hunger(7).saturation(0.7f).build();
        SAUSAGE_RAW = new Food.Builder().hunger(3).saturation(0.3f).build();
        SAUSAGE_COOKED = new Food.Builder().hunger(7).saturation(0.7f).build();
        BAGUETTE_ROLL = new Food.Builder().hunger(5).saturation(0.7f).build();
        BAGUETTE_BACON = new Food.Builder().hunger(8).saturation(0.8f).build();
        BAGUETTE_EGG = new Food.Builder().hunger(6).saturation(0.7f).build();
        BAGUETTE_EGGBACON = new Food.Builder().hunger(9).saturation(1.0f).build();
        BAGUETTE_SAUSAGE = new Food.Builder().hunger(8).saturation(0.8f).build();
        BAGUETTE_BACONSAUSAGE = new Food.Builder().hunger(8).saturation(0.8f).build();
        BAGUETTE_EGGSAUSAGE = new Food.Builder().hunger(6).saturation(0.7f).build();
        BAGUETTE_EGGBACONSAUSAGE = new Food.Builder().hunger(9).saturation(1.0f).build();
        APPLE_PIE = new Food.Builder().hunger(8).saturation(0.3f).build();
        SWEET_BERRIE_PIE = new Food.Builder().hunger(8).saturation(0.3f).build();
        TOAST = new Food.Builder().hunger(7).saturation(1.2f).build();
        FRUIT_SALAD = new Food.Builder().hunger(6).saturation(1.6f).build();
        DOUBLE_CHOC_COOKIE = new Food.Builder().hunger(2).saturation(0.1f).build();
        APPLE_JUICE = new Food.Builder().hunger(4).saturation(0.3f).build();
        MELON_JUICE = new Food.Builder().hunger(2).saturation(0.3f).build();
        CARROT_JUICE = new Food.Builder().hunger(3).saturation(0.6f).build();
        BEETROOT_JUICE = new Food.Builder().hunger(1).saturation(0.6f).build();
        PUMPKIN_JUICE = new Food.Builder().hunger(5).saturation(0.3f).build();
        MILK_BOTTLE = new Food.Builder().hunger(1).saturation(0.6f).build();
        CHOCOLATE_MILK_BOTTLE = new Food.Builder().hunger(1).saturation(0.6f).build();
        APPLE_SLUSHIE = new Food.Builder().hunger(4).saturation(0.3f).build();
        MELON_SLUSHIE = new Food.Builder().hunger(2).saturation(0.3f).build();
        CARROT_SLUSHIE = new Food.Builder().hunger(3).saturation(0.6f).build();
        BEETROOT_SLUSHIE = new Food.Builder().hunger(1).saturation(0.6f).build();
        PUMPKIN_SLUSHIE = new Food.Builder().hunger(5).saturation(0.3f).build();
        APPLE_SMOOTHIE = new Food.Builder().hunger(4).saturation(0.3f).build();
        MELON_SMOOTHIE = new Food.Builder().hunger(2).saturation(0.3f).build();
        CARROT_SMOOTHIE = new Food.Builder().hunger(3).saturation(0.6f).build();
        BEETROOT_SMOOTHIE = new Food.Builder().hunger(1).saturation(0.6f).build();
        PUMPKIN_SMOOTHIE = new Food.Builder().hunger(5).saturation(0.3f).build();
    }
}
