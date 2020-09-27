package com.stc.pattysmorestuff.items.toolsandarmors;

import java.util.*;
import net.minecraftforge.common.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import com.google.common.collect.*;

public class ItemCustomSpade extends ToolItem
{
    private static final Set<Block> EFFECTIVE_ON;
    protected static final Map<Block, BlockState> SHOVEL_LOOKUP;

    public ItemCustomSpade(final String name, final IItemTier tier, final float attackDamageIn, final float attackSpeedIn, final Item.Properties builder) {
        super(attackDamageIn, attackSpeedIn, tier, (Set)ItemCustomSpade.EFFECTIVE_ON, builder.addToolType(ToolType.SHOVEL, tier.getHarvestLevel()));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableTools.get()) {
            ModItems.ITEMS.add((Item)this);
        }
    }

    public boolean canHarvestBlock(final BlockState blockIn) {
        final Block block = blockIn.getBlock();
        return block == Blocks.SNOW || block == Blocks.SNOW_BLOCK;
    }

    public ActionResultType onItemUse(final ItemUseContext context) {
        final World world = context.getWorld();
        final BlockPos blockpos = context.getPos();
        if (context.getFace() != Direction.DOWN && world.getBlockState(blockpos.up()).isAir((IBlockReader)world, blockpos.up())) {
            final BlockState blockstate = ItemCustomSpade.SHOVEL_LOOKUP.get(world.getBlockState(blockpos).getBlock());
            if (blockstate != null) {
                final PlayerEntity playerentity = context.getPlayer();
                world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if (!world.isRemote) {
                    world.setBlockState(blockpos, blockstate, 11);
                    if (playerentity != null) {
                        context.getItem().damageItem(1, (LivingEntity)playerentity, p_220041_1_ -> p_220041_1_.sendBreakAnimation(context.getHand()));
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    static {
        EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER });
        SHOVEL_LOOKUP = Maps.newHashMap((Map)ImmutableMap.of((Object)Blocks.GRASS_BLOCK, (Object)Blocks.GRASS_PATH.getDefaultState()));
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_shovel) {
            return true;
        }
        return false;
    }
}

