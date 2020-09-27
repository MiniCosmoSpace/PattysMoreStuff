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

public class ItemWeatherWand extends Item
{
    public ItemWeatherWand(final String name, final Item.Properties properties) {
        super(properties.maxStackSize(1).maxDamage(0).setNoRepair());
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableWeatherWand.get()) {
            ModItems.ITEMS.add(this);
        }
    }

    public void addInformation(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Currently only clears rain/thunder storms and makes it sunny!"));
    }

    public ActionResult<ItemStack> onItemRightClick(final World worldIn, final PlayerEntity playerIn, final Hand handIn) {
        if (worldIn.isThundering() || worldIn.isRaining()) {
            worldIn.setRainStrength(0.0f);
            worldIn.setThunderStrength(0.0f);
            worldIn.getWorldInfo().setRaining(false);
            worldIn.getWorldInfo().setThundering(false);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
