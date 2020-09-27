package com.stc.pattysmorestuff.util;

import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;

public class EnableUtil
{
    public static boolean isEnabled(final ItemStack stack) {
        return stack.hasTag() && stack.getTag().getBoolean("IsEnabled");
    }
    
    public static void changeEnabled(final PlayerEntity player, final Hand hand) {
        changeEnabled(player.getHeldItem(hand));
    }
    
    public static void changeEnabled(final ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        final boolean isEnabled = isEnabled(stack);
        stack.getTag().putBoolean("IsEnabled", !isEnabled);
    }
}
