package com.stc.pattysmorestuff.items.toolsandarmors;

import java.util.*;
import net.minecraftforge.common.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.block.material.*;
import net.minecraft.block.*;
import com.google.common.collect.*;

public class ItemCustomPickaxe extends ToolItem
{
    private static final Set<Block> EFFECTIVE_ON;
    
    public ItemCustomPickaxe(final String name, final IItemTier tier, final int attackDamageIn, final float attackSpeedIn, final Item.Properties builder) {
        super((float)attackDamageIn, attackSpeedIn, tier, (Set)ItemCustomPickaxe.EFFECTIVE_ON, builder.addToolType(ToolType.PICKAXE, tier.getHarvestLevel()));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableTools.get()) {
            ModItems.ITEMS.add((Item)this);
        }
    }

    public boolean canHarvestBlock(final BlockState blockIn) {
        final Block block = blockIn.getBlock();
        final int i = this.getTier().getHarvestLevel();
        if (blockIn.getHarvestTool() == ToolType.PICKAXE) {
            return i >= blockIn.getHarvestLevel();
        }
        final Material material = blockIn.getMaterial();
        return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
    }
    
    public float getDestroySpeed(final ItemStack stack, final BlockState state) {
        final Material material = state.getMaterial();
        return (material != Material.IRON && material != Material.ANVIL && material != Material.ROCK) ? super.getDestroySpeed(stack, state) : this.efficiency;
    }
    
    static {
        EFFECTIVE_ON = (Set)ImmutableSet.of((Object)Blocks.ACTIVATOR_RAIL, (Object)Blocks.COAL_ORE, (Object)Blocks.COBBLESTONE, (Object)Blocks.DETECTOR_RAIL, (Object)Blocks.DIAMOND_BLOCK, (Object)Blocks.DIAMOND_ORE, (Object[])new Block[] { Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX });
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_pickaxe) {
            return true;
        }
        return false;
    }
}
