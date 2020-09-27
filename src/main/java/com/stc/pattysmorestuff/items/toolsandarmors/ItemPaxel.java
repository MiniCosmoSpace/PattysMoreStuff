package com.stc.pattysmorestuff.items.toolsandarmors;

import net.minecraftforge.common.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.util.*;
import net.minecraftforge.event.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import javax.annotation.*;
import java.util.*;
import net.minecraft.client.util.*;
import net.minecraft.util.text.*;
import net.minecraft.block.*;
import com.google.common.collect.*;

public class ItemPaxel extends ToolItem
{
    public ItemTier material;
    private static final Set<Block> EFFECTIVE_ON;
    protected static final Map<Block, Block> BLOCK_STRIPPING_MAP;
    protected static final Map<Block, BlockState> SHOVEL_LOOKUP;
    protected static final Map<Block, BlockState> HOE_LOOKUP;

    public ItemPaxel(final String name, final IItemTier tier, final int attackDamageIn, final float attackSpeedIn, final Item.Properties builder) {
        super((float)attackDamageIn, attackSpeedIn, tier, (Set)ItemPaxel.EFFECTIVE_ON, builder.addToolType(ToolType.PICKAXE, tier.getHarvestLevel()));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disablePaxel.get()) {
            ModItems.ITEMS.add((Item)this);
        }
    }

    public boolean canHarvestBlock(final BlockState state) {
        final Block block = state.getBlock();
        final int i = this.getTier().getHarvestLevel();
        if (state.getHarvestTool() == ToolType.PICKAXE) {
            return i >= state.getHarvestLevel();
        }
        if (state.getHarvestTool() == ToolType.AXE) {
            return i >= state.getHarvestLevel();
        }
        if (state.getHarvestTool() == ToolType.SHOVEL) {
            return i >= state.getHarvestLevel();
        }
        final Material material = state.getMaterial();
        return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
    }

    public boolean hitEntity(final ItemStack p_77644_1_, final LivingEntity p_77644_2_, final LivingEntity p_77644_3_) {
        p_77644_1_.damageItem(1, p_77644_3_, p_220045_0_ -> p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        return true;
    }

    public ActionResultType onItemUse(final ItemUseContext useContext) {
        final World world = useContext.getWorld();
        final BlockPos blockpos = useContext.getPos();
        final BlockState iblockstate = world.getBlockState(blockpos);
        final Block block = ItemPaxel.BLOCK_STRIPPING_MAP.get(iblockstate.getBlock());
        if (block != null) {
            final PlayerEntity entityplayer = useContext.getPlayer();
            world.playSound(entityplayer, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world.isRemote) {
                world.setBlockState(blockpos, (BlockState)block.getDefaultState().with((IProperty)RotatedPillarBlock.AXIS, iblockstate.get((IProperty)RotatedPillarBlock.AXIS)), 11);
                if (entityplayer != null) {
                    useContext.getItem().damageItem(1, (LivingEntity)entityplayer, p_220040_1_ -> p_220040_1_.sendBreakAnimation(useContext.getHand()));
                }
            }
            return ActionResultType.SUCCESS;
        }
        final World world2 = useContext.getWorld();
        final BlockPos blockpos2 = useContext.getPos();
        if (useContext.getFace() != Direction.DOWN && world.getBlockState(blockpos.up()).isAir((IBlockReader)world2, blockpos2)) {
            final BlockState iblockstate2 = ItemPaxel.SHOVEL_LOOKUP.get(world.getBlockState(blockpos).getBlock());
            if (iblockstate2 != null) {
                final PlayerEntity entityplayer2 = useContext.getPlayer();
                world2.playSound(entityplayer2, blockpos2, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if (!world.isRemote) {
                    world2.setBlockState(blockpos2, iblockstate2, 11);
                    if (entityplayer2 != null) {
                        useContext.getItem().damageItem(1, (LivingEntity)entityplayer2, p_220040_1_ -> p_220040_1_.sendBreakAnimation(useContext.getHand()));
                    }
                }
            }
        }
        if (useContext.getPlayer().isSneaking()) {
            final World world3 = useContext.getWorld();
            final BlockPos blockpos3 = useContext.getPos();
            final int hook = ForgeEventFactory.onHoeUse(useContext);
            if (hook != 0) {
                return (hook > 0) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
            }
            if (useContext.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
                final BlockState iblockstate3 = ItemPaxel.HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
                if (iblockstate3 != null) {
                    final PlayerEntity entityplayer3 = useContext.getPlayer();
                    world3.playSound(entityplayer3, blockpos3, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    if (!world.isRemote) {
                        world3.setBlockState(blockpos3, iblockstate3, 11);
                        if (entityplayer3 != null) {
                            useContext.getItem().damageItem(1, (LivingEntity)entityplayer3, p_220040_1_ -> p_220040_1_.sendBreakAnimation(useContext.getHand()));
                        }
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    public void addInformation(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        tooltip.add((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Right click Grass to make a path, Shift right click Grass to till it, Right click Logs to strip them"));
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_paxel) {
            return true;
        }
        return false;
    }

    static {
        EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.CRAFTING_TABLE, Blocks.ACACIA_FENCE, Blocks.BIRCH_FENCE, Blocks.DARK_OAK_FENCE, Blocks.JUNGLE_FENCE, Blocks.NETHER_BRICK_FENCE, Blocks.OAK_FENCE, Blocks.SPRUCE_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.END_GATEWAY, Blocks.COBWEB, Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL, Blocks.IRON_BARS, Blocks.IRON_DOOR, Blocks.ACACIA_DOOR, Blocks.BIRCH_DOOR, Blocks.DARK_OAK_DOOR, Blocks.JUNGLE_DOOR, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.ACACIA_TRAPDOOR, Blocks.TRAPPED_CHEST, Blocks.ACACIA_TRAPDOOR, Blocks.BIRCH_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR, Blocks.JUNGLE_TRAPDOOR, Blocks.OAK_TRAPDOOR, Blocks.SPRUCE_TRAPDOOR, Blocks.PURPUR_SLAB, Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR, Blocks.PURPUR_STAIRS, Blocks.PRISMARINE, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE_STAIRS, Blocks.DARK_PRISMARINE, Blocks.DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE_STAIRS, Blocks.ANVIL, Blocks.CHIPPED_ANVIL, Blocks.DAMAGED_ANVIL, Blocks.END_STONE, Blocks.END_STONE_BRICKS, Blocks.ENDER_CHEST, Blocks.FURNACE, Blocks.DISPENSER, Blocks.DROPPER, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_PILLAR, Blocks.QUARTZ_STAIRS, Blocks.CHISELED_QUARTZ_BLOCK, Blocks.NETHER_QUARTZ_ORE, Blocks.TERRACOTTA, Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.LIME_GLAZED_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.BLACK_CONCRETE, Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.CRAFTING_TABLE, Blocks.ACACIA_FENCE, Blocks.BIRCH_FENCE, Blocks.DARK_OAK_FENCE, Blocks.JUNGLE_FENCE, Blocks.NETHER_BRICK_FENCE, Blocks.OAK_FENCE, Blocks.SPRUCE_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.END_GATEWAY, Blocks.COBWEB, Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL, Blocks.IRON_BARS, Blocks.IRON_DOOR, Blocks.ACACIA_DOOR, Blocks.BIRCH_DOOR, Blocks.DARK_OAK_DOOR, Blocks.JUNGLE_DOOR, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.ACACIA_TRAPDOOR, Blocks.TRAPPED_CHEST, Blocks.ACACIA_TRAPDOOR, Blocks.BIRCH_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR, Blocks.JUNGLE_TRAPDOOR, Blocks.OAK_TRAPDOOR, Blocks.SPRUCE_TRAPDOOR, Blocks.PURPUR_SLAB, Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR, Blocks.PURPUR_STAIRS, Blocks.PRISMARINE, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE_STAIRS, Blocks.DARK_PRISMARINE, Blocks.DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE_STAIRS, Blocks.ANVIL, Blocks.CHIPPED_ANVIL, Blocks.DAMAGED_ANVIL, Blocks.END_STONE, Blocks.END_STONE_BRICKS, Blocks.ENDER_CHEST, Blocks.FURNACE, Blocks.DISPENSER, Blocks.DROPPER, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_PILLAR, Blocks.QUARTZ_STAIRS, Blocks.CHISELED_QUARTZ_BLOCK, Blocks.NETHER_QUARTZ_ORE, Blocks.TERRACOTTA, Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.LIME_GLAZED_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.BLUE_CONCRETE, Blocks.BROWN_CONCRETE, Blocks.CYAN_CONCRETE, Blocks.GRAY_CONCRETE, Blocks.GREEN_CONCRETE, Blocks.LIGHT_BLUE_CONCRETE, Blocks.LIGHT_GRAY_CONCRETE, Blocks.LIME_CONCRETE, Blocks.MAGENTA_CONCRETE, Blocks.ORANGE_CONCRETE, Blocks.PINK_CONCRETE, Blocks.PURPLE_CONCRETE, Blocks.RED_CONCRETE, Blocks.WHITE_CONCRETE, Blocks.YELLOW_CONCRETE, Blocks.ENCHANTING_TABLE, Blocks.JUKEBOX, Blocks.OBSERVER, Blocks.CAULDRON, Blocks.BRICKS, Blocks.ACACIA_STAIRS, Blocks.BIRCH_STAIRS, Blocks.BRICK_STAIRS, Blocks.SANDSTONE_STAIRS, Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE_STAIRS, Blocks.DARK_OAK_STAIRS, Blocks.JUNGLE_STAIRS, Blocks.NETHER_BRICK_STAIRS, Blocks.OAK_STAIRS, Blocks.RED_SANDSTONE_STAIRS, Blocks.SPRUCE_STAIRS, Blocks.STONE_BRICK_STAIRS, Blocks.HOPPER, Blocks.BEACON });
        BLOCK_STRIPPING_MAP = (Map)new ImmutableMap.Builder().put((Object)Blocks.OAK_WOOD, (Object)Blocks.STRIPPED_OAK_WOOD).put((Object)Blocks.OAK_LOG, (Object)Blocks.STRIPPED_OAK_LOG).put((Object)Blocks.DARK_OAK_WOOD, (Object)Blocks.STRIPPED_DARK_OAK_WOOD).put((Object)Blocks.DARK_OAK_LOG, (Object)Blocks.STRIPPED_DARK_OAK_LOG).put((Object)Blocks.ACACIA_WOOD, (Object)Blocks.STRIPPED_ACACIA_WOOD).put((Object)Blocks.ACACIA_LOG, (Object)Blocks.STRIPPED_ACACIA_LOG).put((Object)Blocks.BIRCH_WOOD, (Object)Blocks.STRIPPED_BIRCH_WOOD).put((Object)Blocks.BIRCH_LOG, (Object)Blocks.STRIPPED_BIRCH_LOG).put((Object)Blocks.JUNGLE_WOOD, (Object)Blocks.STRIPPED_JUNGLE_WOOD).put((Object)Blocks.JUNGLE_LOG, (Object)Blocks.STRIPPED_JUNGLE_LOG).put((Object)Blocks.SPRUCE_WOOD, (Object)Blocks.STRIPPED_SPRUCE_WOOD).put((Object)Blocks.SPRUCE_LOG, (Object)Blocks.STRIPPED_SPRUCE_LOG).build();
        SHOVEL_LOOKUP = Maps.newHashMap((Map)ImmutableMap.of((Object)Blocks.GRASS_BLOCK, (Object)Blocks.GRASS_PATH.getDefaultState()));
        HOE_LOOKUP = Maps.newHashMap((Map)ImmutableMap.of((Object)Blocks.GRASS_BLOCK, (Object)Blocks.FARMLAND.getDefaultState(), (Object)Blocks.GRASS_PATH, (Object)Blocks.FARMLAND.getDefaultState(), (Object)Blocks.DIRT, (Object)Blocks.FARMLAND.getDefaultState(), (Object)Blocks.COARSE_DIRT, (Object)Blocks.DIRT.getDefaultState()));
    }
}