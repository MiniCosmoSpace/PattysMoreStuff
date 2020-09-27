package com.stc.pattysmorestuff.items.drinks;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.world.*;
import javax.annotation.*;
import java.util.*;
import net.minecraft.client.util.*;
import net.minecraft.util.text.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class ItemDrinkable extends Item
{
    public ItemDrinkable(final String name, final Item.Properties properties) {
        super(properties);
        this.setRegistryName(name);
        if (ConfigGeneral.disableDrinks.get()) {
            ModItems.ITEMS.add(this);
        }
    }
    
    public void addInformation(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        if (stack.getItem() == ModItems.carrot_juice) {
            tooltip.add((ITextComponent)new StringTextComponent("§cRight click a carrot on the blender to obtain this juice"));
        }
        if (stack.getItem() == ModItems.melon_juice) {
            tooltip.add((ITextComponent)new StringTextComponent("§cRight click a melon on the blender to obtain this juice"));
        }
        if (stack.getItem() == ModItems.beetroot_juice) {
            tooltip.add((ITextComponent)new StringTextComponent("§cRight click a beetroot on the blender to obtain this juice"));
        }
        if (stack.getItem() == ModItems.pumpkin_juice) {
            tooltip.add((ITextComponent)new StringTextComponent("§cRight click a pumpkin on the blender to obtain this juice"));
        }
        if (stack.getItem() == ModItems.apple_juice) {
            tooltip.add((ITextComponent)new StringTextComponent("§cRight click a apple on the blender to obtain this juice"));
        }
    }
    
    public UseAction getUseAction(final ItemStack stack) {
        return UseAction.DRINK;
    }
    
    public int getUseDuration(final ItemStack stack) {
        return 32;
    }
    
    public ItemStack onItemUseFinish(final ItemStack p_77654_1_, final World p_77654_2_, final LivingEntity p_77654_3_) {
        return new ItemStack((IItemProvider)Items.GLASS_BOTTLE);
    }
}
