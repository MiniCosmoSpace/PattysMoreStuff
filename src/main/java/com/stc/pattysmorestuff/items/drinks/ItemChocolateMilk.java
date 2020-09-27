package com.stc.pattysmorestuff.items.drinks;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.advancements.*;
import net.minecraft.stats.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class ItemChocolateMilk extends Item
{
    public ItemChocolateMilk(final String name, final Item.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableDrinks.get()) {
            ModItems.ITEMS.add(this);
        }
    }
    
    public UseAction getUseAction(final ItemStack stack) {
        return UseAction.DRINK;
    }
    
    public int getUseDuration(final ItemStack stack) {
        return 32;
    }
    
    public ItemStack onItemUseFinish(final ItemStack stack, final World worldIn, final LivingEntity entityLiving) {
        if (!worldIn.isRemote) {
            entityLiving.curePotionEffects(stack);
        }
        if (entityLiving instanceof ServerPlayerEntity) {
            final ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.addStat(Stats.ITEM_USED.get(this));
        }
        if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            stack.shrink(1);
        }
        if (!worldIn.isRemote) {
            entityLiving.clearActivePotions();
        }
        return new ItemStack((IItemProvider)Items.GLASS_BOTTLE);
    }
}
