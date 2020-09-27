package com.stc.pattysmorestuff.items.wand;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import javax.annotation.*;
import java.util.*;
import net.minecraft.client.util.*;
import net.minecraft.util.text.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class ItemTimeWand extends Item
{
    public ItemTimeWand(final String name, final Item.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableTimeWand.get()) {
            ModItems.ITEMS.add(this);
        }
    }
    
    public void addInformation(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        tooltip.add((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Switchs from night to day and from day to night!"));
    }
    
    public ActionResult<ItemStack> onItemRightClick(final World worldIn, final PlayerEntity playerIn, final Hand handIn) {
        if (!worldIn.isDaytime()) {
            worldIn.setDayTime(1000L);
        }
        else if (worldIn.isDaytime()) {
            worldIn.setDayTime(15000L);
        }
        return (ActionResult<ItemStack>)super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
