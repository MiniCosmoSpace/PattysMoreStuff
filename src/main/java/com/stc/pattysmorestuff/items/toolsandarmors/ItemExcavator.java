package com.stc.pattysmorestuff.items.toolsandarmors;

import com.stc.pattysmorestuff.items.toolsandarmors.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraftforge.common.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import javax.annotation.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.client.util.*;
import net.minecraft.util.text.*;

public class ItemExcavator extends ShovelItem implements PMTTool {
    public ItemExcavator(final String name, final IItemTier tier, final int attackDamageIn, final float attackSpeedIn, final Item.Properties builder) {
        super (tier, (float) attackDamageIn, attackSpeedIn, builder);
        this.setRegistryName ("pattysmorestuff", name);
        if (ConfigGeneral.disableExcavator.get ()) {
            ModItems.ITEMS.add ((Item) this);
        }
    }

    @Nonnull
    public ToolType getPMTToolClass() {
        return ToolType.SHOVEL;
    }

    @Nullable
    public RayTraceResult rayTraceBlocks(final World world, final PlayerEntity player) {
        return rayTrace (world, player, RayTraceContext.FluidMode.NONE);
    }

    public boolean onBlockStartBreak(final ItemStack itemstack, final BlockPos pos, final PlayerEntity player) {
        return BreakHandler.onBlockStartBreak (itemstack, pos, player);
    }

    public void addInformation(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        tooltip.add ((ITextComponent) new StringTextComponent (TextFormatting.GREEN + "Excavator that mines in a 3x3x1 from the block you mine"));
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_excavator) {
            return true;
        }
        return false;
    }
}

