package com.stc.pattysmorestuff.items.toolsandarmors;

import java.util.*;
import net.minecraftforge.common.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.state.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import com.google.common.collect.*;

public class ItemCustomAxe extends ToolItem
{
    private static final Set<Block> EFFECTIVE_ON;
    protected static final Map<Block, Block> BLOCK_STRIPPING_MAP;

    public ItemCustomAxe(final String name, final IItemTier tier, final float attackDamageIn, final float attackSpeedIn, final Item.Properties builder) {
        super(attackDamageIn, attackSpeedIn, tier, (Set)ItemCustomAxe.EFFECTIVE_ON, builder.addToolType(ToolType.AXE, tier.getHarvestLevel()));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableTools.get()) {
            ModItems.ITEMS.add((Item)this);
        }
    }

    public float getDestroySpeed(final ItemStack stack, final BlockState state) {
        final Material material = state.getMaterial();
        return (material != Material.WOOD && material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.BAMBOO) ? super.getDestroySpeed(stack, state) : this.efficiency;
    }

    public ActionResultType onItemUse(final ItemUseContext context) {
        final World world = context.getWorld();
        final BlockPos blockpos = context.getPos();
        final BlockState blockstate = world.getBlockState(blockpos);
        final Block block = ItemCustomAxe.BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
        if (block != null) {
            final PlayerEntity playerentity = context.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world.isRemote) {
                world.setBlockState(blockpos, (BlockState)block.getDefaultState().with((IProperty)RotatedPillarBlock.AXIS, blockstate.get((IProperty)RotatedPillarBlock.AXIS)), 11);
                if (playerentity != null) {
                    context.getItem().damageItem(1, (LivingEntity)playerentity, p_220040_1_ -> p_220040_1_.sendBreakAnimation(context.getHand()));
                }
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    static {
        EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE });
        BLOCK_STRIPPING_MAP = (Map)new ImmutableMap.Builder().put((Object)Blocks.OAK_WOOD, (Object)Blocks.STRIPPED_OAK_WOOD).put((Object)Blocks.OAK_LOG, (Object)Blocks.STRIPPED_OAK_LOG).put((Object)Blocks.DARK_OAK_WOOD, (Object)Blocks.STRIPPED_DARK_OAK_WOOD).put((Object)Blocks.DARK_OAK_LOG, (Object)Blocks.STRIPPED_DARK_OAK_LOG).put((Object)Blocks.ACACIA_WOOD, (Object)Blocks.STRIPPED_ACACIA_WOOD).put((Object)Blocks.ACACIA_LOG, (Object)Blocks.STRIPPED_ACACIA_LOG).put((Object)Blocks.BIRCH_WOOD, (Object)Blocks.STRIPPED_BIRCH_WOOD).put((Object)Blocks.BIRCH_LOG, (Object)Blocks.STRIPPED_BIRCH_LOG).put((Object)Blocks.JUNGLE_WOOD, (Object)Blocks.STRIPPED_JUNGLE_WOOD).put((Object)Blocks.JUNGLE_LOG, (Object)Blocks.STRIPPED_JUNGLE_LOG).put((Object)Blocks.SPRUCE_WOOD, (Object)Blocks.STRIPPED_SPRUCE_WOOD).put((Object)Blocks.SPRUCE_LOG, (Object)Blocks.STRIPPED_SPRUCE_LOG).build();
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_axe) {
            return true;
        }
        return false;
    }
}
