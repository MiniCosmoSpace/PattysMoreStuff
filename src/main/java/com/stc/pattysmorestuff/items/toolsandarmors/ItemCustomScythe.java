package com.stc.pattysmorestuff.items.toolsandarmors;

import java.util.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

import javax.annotation.Nullable;

public class ItemCustomScythe extends TieredItem
{
    private final float speed;
    protected static final Map<Block, BlockState> HOE_LOOKUP = null;

    public ItemCustomScythe(final String name, final IItemTier tier, final float attackSpeedIn, final Item.Properties builder) {
        super(tier, builder);
        this.speed = attackSpeedIn;
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableTools.get()) {
            ModItems.ITEMS.add((Item)this);
        }
    }
    @Override
    public ActionResultType onItemUse(ItemUseContext useContext) {

        World world = useContext.getWorld();
        BlockPos pos = useContext.getPos();

        for(int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                BlockPos bPos = new BlockPos(pos.add(i, 0, j));
                if (world.getBlockState(bPos).getBlock() instanceof IGrowable) {
                    IGrowable iG = (IGrowable) world.getBlockState(bPos).getBlock();
                    BlockState bState = world.getBlockState(bPos);
                    if (!iG.canGrow(world, bPos, bState, false)) {
                        world.destroyBlock(bPos, true);
                        world.setBlockState(bPos, bState.getBlock().getDefaultState());
                        useContext.getItem().setDamage(9);

                    }

                }
            }
        }
        return super.onItemUse(useContext);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.GREEN  + "Harvests (and replants) a 3x3 of grown crops & flowers"));
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_scythe) {
            return true;
        }
        return false;
    }
}