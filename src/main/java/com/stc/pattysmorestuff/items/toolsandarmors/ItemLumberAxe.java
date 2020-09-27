package com.stc.pattysmorestuff.items.toolsandarmors;

import net.minecraft.block.material.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.tags.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraft.util.*;
import net.minecraft.tileentity.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import com.google.common.collect.*;

public class ItemLumberAxe extends AxeItem
{
    public static final Random random;
    private static final Set<Block> EFFECTIVE_ON;
    public static final Set<Material> EFFECTIVE_MATERIALS;
    public static final int LOG_BREAK_DELAY = 1;
    public static int mineRadius;
    public static int mineDepth;
    
    public ItemLumberAxe(final String name, final IItemTier tier, final float attackDamageIn, final float attackSpeedIn, final Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableLumberAxe.get()) {
            ModItems.ITEMS.add((Item)this);
        }
    }
    
    public boolean onBlockDestroyed(final ItemStack stack, final World world, final BlockState state, final BlockPos pos, final LivingEntity entityLiving) {
        stack.attemptDamageItem(2, ItemLumberAxe.random, (ServerPlayerEntity)null);
        if (entityLiving instanceof PlayerEntity) {
            final PlayerEntity player = (PlayerEntity)entityLiving;
            if (!this.attemptFellTree(world, pos, player)) {
                attemptBreakNeighbors(world, pos, player, ItemLumberAxe.EFFECTIVE_ON, ItemLumberAxe.EFFECTIVE_MATERIALS);
            }
        }
        return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
    }
    
    private boolean attemptFellTree(final World world, final BlockPos pos, final PlayerEntity player) {
        final ArrayList<BlockPos> logs = new ArrayList<BlockPos>();
        final ArrayList<BlockPos> candidates = new ArrayList<BlockPos>();
        candidates.add(pos);
        int leaves = 0;
        for (int i = 0; i < candidates.size(); ++i) {
            if (logs.size() > 200) {
                return false;
            }
            final BlockPos candidate = candidates.get(i);
            final Block block = world.getBlockState(candidate).getBlock();
            if (BlockTags.LEAVES.contains(block)) {
                ++leaves;
            }
            else if (BlockTags.LOGS.contains(block)) {
                logs.add(candidate);
                for (int x = -1; x <= 1; ++x) {
                    for (int y = 0; y <= 1; ++y) {
                        for (int z = -1; z <= 1; ++z) {
                            final BlockPos neighbor = candidate.add(x, y, z);
                            if (!candidates.contains(neighbor)) {
                                candidates.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
        if (logs.size() == 0) {
            return false;
        }
        if (leaves >= logs.size() / 6.0) {
            MinecraftForge.EVENT_BUS.register((Object)new Object() {
                int delay = 1;
                int i = 0;
                
                @SubscribeEvent
                public void onTick(final TickEvent.WorldTickEvent event) {
                    if (this.delay-- > 0) {
                        return;
                    }
                    this.delay = 1;
                    if (this.i < logs.size()) {
                        final BlockPos log = logs.get(this.i);
                        ItemLumberAxe.attemptBreak(world, log, player, ItemLumberAxe.EFFECTIVE_ON, ItemLumberAxe.EFFECTIVE_MATERIALS);
                        ++this.i;
                    }
                    else {
                        MinecraftForge.EVENT_BUS.unregister((Object)this);
                    }
                }
            });
            return true;
        }
        return false;
    }
    
    public static void attemptBreakNeighbors(final World world, final BlockPos pos, final PlayerEntity player, final Set<Block> effectiveOn, final Set<Material> effectiveMaterials) {
        final RayTraceResult trace = calcRayTrace(world, player, RayTraceContext.FluidMode.NONE);
        final BlockRayTraceResult blockTrace = (BlockRayTraceResult)trace;
        final Direction face = blockTrace.getFace();
        int zDist;
        int yDist;
        int xDist = yDist = (zDist = ItemLumberAxe.mineRadius);
        switch (face) {
            case UP:
            case DOWN: {
                yDist = ItemLumberAxe.mineDepth;
                break;
            }
            case NORTH:
            case SOUTH: {
                zDist = ItemLumberAxe.mineDepth;
                break;
            }
            case EAST:
            case WEST: {
                xDist = ItemLumberAxe.mineDepth;
                break;
            }
        }
    }
    
    public static void attemptBreak(final World world, final BlockPos pos, final PlayerEntity player, final Set<Block> effectiveOn, final Set<Material> effectiveMaterials) {
        final BlockState state = world.getBlockState(pos);
        final boolean isEffective = effectiveOn.contains(state.getBlock()) || effectiveMaterials.contains(state.getMaterial());
        final boolean witherImmune = BlockTags.WITHER_IMMUNE.contains(state.getBlock());
        if (isEffective && !witherImmune) {
            world.destroyBlock(pos, false);
            Block.spawnDrops(state, world, pos, (TileEntity)null, (Entity)player, player.getHeldItemMainhand());
        }
    }
    
    public static RayTraceResult calcRayTrace(final World world, final PlayerEntity player, final RayTraceContext.FluidMode fluidMode) {
        final float f = player.rotationPitch;
        final float f2 = player.rotationYaw;
        final Vec3d vec3d = player.getEyePosition(1.0f);
        final float f3 = MathHelper.cos(-f2 * 0.017453292f - 3.1415927f);
        final float f4 = MathHelper.sin(-f2 * 0.017453292f - 3.1415927f);
        final float f5 = -MathHelper.cos(-f * 0.017453292f);
        final float f6 = MathHelper.sin(-f * 0.017453292f);
        final float f7 = f4 * f5;
        final float f8 = f3 * f5;
        final double d0 = player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();
        final Vec3d vec3d2 = vec3d.add(f7 * d0, f6 * d0, f8 * d0);
        return (RayTraceResult)world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE, fluidMode, (Entity)player));
    }
    
    static {
        random = new Random();
        EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE });
        EFFECTIVE_MATERIALS = (Set)ImmutableSet.of((Object)Material.WOOD, (Object)Material.GOURD, (Object)Material.CACTUS);
        ItemLumberAxe.mineRadius = 1;
        ItemLumberAxe.mineDepth = 0;
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_lumber_axe) {
            return true;
        }
        return false;
    }
}

