package com.stc.pattysmorestuff.util;

import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;

public class MagnetRange
{
    public static int getCurrentRange(final ItemStack stack) {
        return stack.getTag().getInt("currentRange");
    }
    
    public static boolean getCurrentlySet(final ItemStack stack) {
        return stack.getTag().getBoolean("currentlySet");
    }
    
    public static void setCurrentRange(final PlayerEntity player, final Hand hand, final int range) {
        setCurrentRange(player.getHeldItem(hand), range);
    }
    
    public static void setCurrentRange(final ItemStack stack, final int newRange) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        stack.getTag().putInt("currentRange", newRange);
        stack.getTag().putBoolean("currentlySet", true);
    }
}
