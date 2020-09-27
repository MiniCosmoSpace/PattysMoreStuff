package com.stc.pattysmorestuff.items.toolsandarmors;

import javax.annotation.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import net.minecraftforge.registries.*;
import net.minecraft.block.*;
import net.minecraftforge.common.*;
import java.util.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraftforge.eventbus.api.*;

public interface PMTTool
{
    @Nonnull
    ToolType getPMTToolClass();
    
    @Nullable
    RayTraceResult rayTraceBlocks(final World p0, final PlayerEntity p1);
    
    default List<BlockPos> getExtraBlocks(final World world, @Nullable final BlockRayTraceResult rt, final PlayerEntity player, final ItemStack stack) {
        final List<BlockPos> positions = new ArrayList<BlockPos>();
        if (player.isSneaking() || rt == null || rt.getPos() == null || rt.getFace() == null) {
            return positions;
        }
        final BlockPos pos = rt.getPos();
        final BlockState state = world.getBlockState(pos);
        if (this.isEffectiveOnBlock(stack, world, pos, state)) {
            switch (rt.getFace().getAxis()) {
                case Y: {
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.EAST), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.WEST), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH).offset(Direction.EAST), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.EAST).offset(Direction.SOUTH), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH).offset(Direction.WEST), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.WEST).offset(Direction.NORTH), stack, positions);
                    break;
                }
                case X: {
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.UP), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH).offset(Direction.UP), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.UP).offset(Direction.SOUTH), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH).offset(Direction.DOWN), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN).offset(Direction.NORTH), stack, positions);
                    break;
                }
                case Z: {
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.EAST), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.UP), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.WEST), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN).offset(Direction.EAST), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.EAST).offset(Direction.UP), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.UP).offset(Direction.WEST), stack, positions);
                    this.attemptAddExtraBlock(world, state, pos.offset(Direction.WEST).offset(Direction.DOWN), stack, positions);
                    break;
                }
            }
        }
        return positions;
    }
    
    default boolean isEffectiveOnBlock(final ItemStack stack, final World world, final BlockPos pos, final BlockState state) {
        return stack.getItem().canHarvestBlock(stack, state) || ForgeHooks.canToolHarvestBlock((IWorldReader)world, pos, stack);
    }
    
    default void attemptAddExtraBlock(final World world, final BlockState state1, final BlockPos pos2, final ItemStack stack, final List<BlockPos> list) {
        final BlockState state2 = world.getBlockState(pos2);
        if (state2.getBlockHardness((IBlockReader)world, pos2) < 0.0f) {
            return;
        }
        if (!world.isAirBlock(pos2) && BreakHandler.areBlocksSimilar(state1, state2) && (state2.getBlock().isToolEffective(state2, this.getPMTToolClass()) || stack.getItem().canHarvestBlock(stack, state2))) {
            list.add(pos2);
        }
    }
    
    public enum MatchMode
    {
        LOOSE, 
        MODERATE, 
        STRICT;
    }
    
    public static final class BreakHandler
    {
        private static final Set<Block> ORE_BLOCKS;
        
        private BreakHandler() {
        }
        
        public static boolean onBlockStartBreak(final ItemStack tool, final BlockPos pos, final PlayerEntity player) {
            final World world = player.getEntityWorld();
            if (world.isRemote || !(player instanceof ServerPlayerEntity) || !(tool.getItem() instanceof PMTTool)) {
                return false;
            }
            final PMTTool item = (PMTTool)tool.getItem();
            final RayTraceResult rt = item.rayTraceBlocks(world, player);
            final BlockState stateOriginal = world.getBlockState(pos);
            if (rt != null && rt.getType() == RayTraceResult.Type.BLOCK && item.isEffectiveOnBlock(tool, world, pos, stateOriginal)) {
                final BlockRayTraceResult brt = (BlockRayTraceResult)rt;
                final Direction side = brt.getFace();
                final List<BlockPos> extraBlocks = item.getExtraBlocks(world, brt, player, tool);
                for (final BlockPos pos2 : extraBlocks) {
                    final BlockState state = world.getBlockState(pos2);
                    if (world.isBlockPresent(pos2) && player.canPlayerEdit(pos2, side, tool)) {
                        if (!state.canHarvestBlock((IBlockReader)world, pos2, player)) {
                            continue;
                        }
                        if (player.abilities.isCreativeMode) {
                            state.getBlock().onBlockHarvested(world, pos2, state, player);
                            if (state.getBlock().removedByPlayer(state, world, pos2, player, false, state.getFluidState())) {
                                state.getBlock().onPlayerDestroy((IWorld)world, pos2, state);
                            }
                        }
                        else {
                            final int xp = ForgeHooks.onBlockBreakEvent(world, ((ServerPlayerEntity)player).interactionManager.getGameType(), (ServerPlayerEntity)player, pos2);
                            tool.getItem().onBlockDestroyed(tool, world, state, pos2, (LivingEntity)player);
                            if (state.getBlock().removedByPlayer(state, world, pos2, player, true, state.getFluidState())) {
                                state.getBlock().onPlayerDestroy((IWorld)world, pos2, state);
                                state.getBlock().harvestBlock(world, player, pos2, state, world.getTileEntity(pos2), tool);
                                state.getBlock().dropXpOnBlockBreak(world, pos2, xp);
                            }
                        }
                        world.playEvent(2001, pos, Block.getStateId(state));
                        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SChangeBlockPacket((IBlockReader)world, pos));
                    }
                }
            }
            return false;
        }
        
        public static void buildOreBlocksSet() {
            BreakHandler.ORE_BLOCKS.clear();
            for (final Block block : ForgeRegistries.BLOCKS) {
                if (block instanceof OreBlock || Tags.Blocks.ORES.contains(block)) {
                    BreakHandler.ORE_BLOCKS.add(block);
                }
            }
        }
        
        static boolean areBlocksSimilar(final BlockState state1, final BlockState state2) {
            final Block block1 = state1.getBlock();
            final Block block2 = state2.getBlock();
            final boolean isOre1 = BreakHandler.ORE_BLOCKS.contains(block1);
            final boolean isOre2 = BreakHandler.ORE_BLOCKS.contains(block2);
            final int level1 = block1.getHarvestLevel(state1);
            final int level2 = block2.getHarvestLevel(state2);
            return level1 >= level2 || level2 == 0;
        }
        
        static {
            ORE_BLOCKS = new HashSet<Block>();
        }
    }
    
    @Mod.EventBusSubscriber(modid = "pattysmorestuff", value = { Dist.CLIENT })
    public static final class HighlightHandler
    {
        private HighlightHandler() {
        }
        
        @SubscribeEvent
        public static void onDrawBlockHighlight(final DrawBlockHighlightEvent event) {
            final ActiveRenderInfo info = event.getInfo();
            final Entity entity = info.getRenderViewEntity();
            if (!(entity instanceof PlayerEntity)) {
                return;
            }
            final PlayerEntity player = (PlayerEntity)entity;
            final RayTraceResult rt = event.getTarget();
            if (event.getSubID() == 0 && rt.getType() == RayTraceResult.Type.BLOCK) {
                final ItemStack stack = player.getHeldItemMainhand();
                if (stack.getItem() instanceof PMTTool) {
                    final World world = player.getEntityWorld();
                    final PMTTool item = (PMTTool)stack.getItem();
                    for (final BlockPos pos : item.getExtraBlocks(world, (BlockRayTraceResult)rt, player, stack)) {
                        event.getContext().drawSelectionBox(info, (RayTraceResult)new BlockRayTraceResult(Vec3d.ZERO, Direction.UP, pos, false), 0);
                    }
                }
            }
        }
    }
}
