package com.stc.pattysmorestuff.util;

import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraftforge.items.*;
import net.minecraft.inventory.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.*;
import java.util.*;

public class Utilities
{
    public static BlockRayTraceResult getLookingAt(final PlayerEntity player, final double range) {
        return getLookingAt(player, range, RayTraceContext.FluidMode.NONE);
    }
    
    public static BlockRayTraceResult getLookingAt(final PlayerEntity player, final double range, final RayTraceContext.FluidMode fluidMode) {
        final World world = player.world;
        final Vec3d look = player.getLookVec();
        final Vec3d startPos = getVec3d((Entity)player).add(0.0, (double)player.getEyeHeight(), 0.0);
        final Vec3d endPos = startPos.add(look.mul(range, range, range));
        final RayTraceContext context = new RayTraceContext(startPos, endPos, RayTraceContext.BlockMode.OUTLINE, fluidMode, (Entity)player);
        return world.rayTraceBlocks(context);
    }
    
    public static BlockRayTraceResult getLookingAt(final Vec3d position, final Vec3d look, final double range, final Entity entity) {
        final Vec3d endPos = position.add(look.mul(range, range, range));
        final RayTraceContext context = new RayTraceContext(position, endPos, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity);
        return entity.world.rayTraceBlocks(context);
    }
    
    public static Vec3d getVec3d(final Entity entity) {
        return entity.getPositionVec();
    }
    
    public static void dropItems(final World world, final List<ItemStack> items, final BlockPos pos) {
        for (final ItemStack stack : items) {
            if (stack.isEmpty()) {
                continue;
            }
            dropItem(world, stack, pos);
        }
    }
    
    public static void dropItems(final World world, final ItemStackHandler handler, final BlockPos pos) {
        for (int i = 0; i < handler.getSlots(); ++i) {
            final ItemStack stack = handler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                dropItem(world, stack, pos);
            }
        }
    }
    
    public static void dropItem(final World world, final ItemStack stack, final BlockPos pos) {
        InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
    }
    
    public static int spawnExp(final World world, final BlockPos pos, final BlockState state, final int fortune, final int silk) {
        return spawnExp(world, pos, state.getExpDrop((IWorldReader)world, pos, fortune, silk));
    }
    
    public static int spawnExp(final World world, final BlockPos pos, final int exp) {
        return spawnExp(world, pos, exp, true);
    }
    
    public static int spawnExp(final World world, final BlockPos pos, int exp, final boolean split) {
        if (exp > 0) {
            if (split) {
                while (exp > 0) {
                    final int drop = ExperienceOrbEntity.getXPSplit(exp);
                    exp -= drop;
                    world.addEntity((Entity)new ExperienceOrbEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop));
                }
            }
            else {
                world.addEntity((Entity)new ExperienceOrbEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, exp));
            }
        }
        return exp;
    }
    
    public static List<BlockPos> getBlocks(final BlockPos pos, final Direction facing) {
        return getBlocks(pos, facing, 3);
    }
    
    public static List<BlockPos> getBlocks(final BlockPos pos, final Direction facing, final int radius) {
        final List<BlockPos> positions = new ArrayList<BlockPos>();
        if (radius % 2 == 0) {
            return positions;
        }
        if (radius < 3) {
            positions.add(pos);
            return positions;
        }
        final int d = radius / 2;
        final int min = -d;
        final int max = d;
        switch (facing) {
            case DOWN:
            case UP: {
                for (int x = min; x <= max; ++x) {
                    for (int z = min; z <= max; ++z) {
                        positions.add(pos.add(x, 0, z));
                    }
                }
                break;
            }
            case NORTH:
            case SOUTH: {
                for (int x = min; x <= max; ++x) {
                    for (int y = min; y <= max; ++y) {
                        positions.add(pos.add(x, y, 0));
                    }
                }
                break;
            }
            case WEST:
            case EAST: {
                for (int z2 = min; z2 <= max; ++z2) {
                    for (int y = min; y <= max; ++y) {
                        positions.add(pos.add(0, y, z2));
                    }
                }
                break;
            }
        }
        return positions;
    }
}
