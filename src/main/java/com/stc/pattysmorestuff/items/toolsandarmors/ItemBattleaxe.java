package com.stc.pattysmorestuff.items.toolsandarmors;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.util.*;
import net.minecraft.state.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.inventory.*;
import com.google.common.collect.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.*;

public class ItemBattleaxe extends AxeItem
{
    public ItemBattleaxe(final String name, final IItemTier material, final Item.Properties builder) {
        super(material, 5.0f, -3.0f, builder);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableTools.get()) {
            ModItems.ITEMS.add((Item)this);
        }
    }

    public ActionResultType onItemUse(final ItemUseContext p_195939_1_) {
        final World world = p_195939_1_.getWorld();
        final BlockPos blockpos = p_195939_1_.getPos();
        final BlockState iblockstate = world.getBlockState(blockpos);
        final Block block = ItemBattleaxe.BLOCK_STRIPPING_MAP.get(iblockstate.getBlock());
        if (block != null) {
            final PlayerEntity entityplayer = p_195939_1_.getPlayer();
            world.playSound(entityplayer, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world.isRemote) {
                world.setBlockState(blockpos, (BlockState)block.getDefaultState().with((IProperty)RotatedPillarBlock.AXIS, iblockstate.get((IProperty)RotatedPillarBlock.AXIS)), 11);
                if (entityplayer != null) {
                    p_195939_1_.getItem().setDamage(1);
                }
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public boolean canPlayerBreakBlockWhileHolding(final BlockState p_195938_1_, final World p_195938_2_, final BlockPos p_195938_3_, final PlayerEntity p_195938_4_) {
        return !p_195938_4_.isCreative();
    }

    public boolean hitEntity(final ItemStack p_77644_1_, final LivingEntity p_77644_2_, final LivingEntity p_77644_3_) {
        p_77644_1_.setDamage(1);
        return true;
    }

    public boolean onBlockDestroyed(final ItemStack p_179218_1_, final World p_179218_2_, final BlockState p_179218_3_, final BlockPos p_179218_4_, final LivingEntity p_179218_5_) {
        if (p_179218_3_.getBlockHardness((IBlockReader)p_179218_2_, p_179218_4_) != 0.0f) {
            p_179218_1_.setDamage(1);
        }
        return true;
    }

    public boolean canHarvestBlock(final BlockState p_150897_1_) {
        return p_150897_1_.getBlock() == Blocks.COBWEB;
    }

    public Multimap<String, AttributeModifier> getAttributeModifiers(final EquipmentSlotType p_111205_1_) {
        final Multimap<String, AttributeModifier> lvt_2_1_ = (Multimap<String, AttributeModifier>)super.getAttributeModifiers(p_111205_1_);
        if (p_111205_1_ == EquipmentSlotType.MAINHAND) {
            lvt_2_1_.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ItemBattleaxe.ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
            lvt_2_1_.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ItemBattleaxe.ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
        }
        return lvt_2_1_;
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.netherstar_battle_axe) {
            return true;
        }
        return false;
    }
}
