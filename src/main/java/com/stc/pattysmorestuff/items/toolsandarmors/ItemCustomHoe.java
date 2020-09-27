package com.stc.pattysmorestuff.items.toolsandarmors;

import java.util.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraftforge.event.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import com.google.common.collect.*;

public class ItemCustomHoe extends TieredItem
{
    private final float speed;
    protected static final Map<Block, BlockState> HOE_LOOKUP;

    public ItemCustomHoe(final String name, final IItemTier tier, final float attackSpeedIn, final Item.Properties builder) {
        super (tier, builder);
        this.speed = attackSpeedIn;
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableTools.get()) {
            ModItems.ITEMS.add((Item)this);
        }
    }

    public ActionResultType onItemUse(final ItemUseContext context) {
        final World world = context.getWorld();
        final BlockPos blockpos = context.getPos();
        final int hook = ForgeEventFactory.onHoeUse(context);
        if (hook != 0) {
            return (hook > 0) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
        }
        if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
            final BlockState blockstate = com.stc.pattysmorestuff.items.toolsandarmors.ItemCustomHoe.HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
            if (blockstate != null) {
                final PlayerEntity playerentity = context.getPlayer();
                world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if (!world.isRemote) {
                    world.setBlockState(blockpos, blockstate, 11);
                    if (playerentity != null) {
                        context.getItem().damageItem(1, (LivingEntity)playerentity, p_220043_1_ -> p_220043_1_.sendBreakAnimation(context.getHand()));
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    public boolean hitEntity(final ItemStack stack, final LivingEntity target, final LivingEntity attacker) {
        stack.damageItem(1, attacker, p_220042_0_ -> p_220042_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        return true;
    }

    public Multimap<String, AttributeModifier> getAttributeModifiers(final EquipmentSlotType equipmentSlot) {
        final Multimap<String, AttributeModifier> multimap = (Multimap<String, AttributeModifier>)super.getAttributeModifiers(equipmentSlot);
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(com.stc.pattysmorestuff.items.toolsandarmors.ItemCustomHoe.ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0, AttributeModifier.Operation.ADDITION));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(com.stc.pattysmorestuff.items.toolsandarmors.ItemCustomHoe.ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.speed, AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

    static {
        HOE_LOOKUP = Maps.newHashMap((Map)ImmutableMap.of((Object)Blocks.GRASS_BLOCK, (Object)Blocks.FARMLAND.getDefaultState(), (Object)Blocks.GRASS_PATH, (Object)Blocks.FARMLAND.getDefaultState(), (Object)Blocks.DIRT, (Object)Blocks.FARMLAND.getDefaultState(), (Object)Blocks.COARSE_DIRT, (Object)Blocks.DIRT.getDefaultState()));
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_hoe) {
            return true;
        }
        return false;
    }
}
