package com.stc.pattysmorestuff.blocks.stairs;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockCustomStairs extends StairsBlock
{
    public BlockCustomStairs(final String name, final BlockState defaultState, final Block.Properties properties) {
        super(defaultState, properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    public BlockRenderType getRenderType(final BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    public void onFallenUpon(final World worldIn, final BlockPos pos, final Entity entityIn, final float fallDistance) {
        if (entityIn.isSneaking()) {
            super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
        }
        else {
            entityIn.fall(fallDistance, 0.0f);
        }
    }
    
    public void onLanded(final IBlockReader worldIn, final Entity entityIn) {
        if (entityIn.isSneaking()) {
            super.onLanded(worldIn, entityIn);
        }
        else {
            final Vec3d vec3d = entityIn.getMotion();
            if (vec3d.y < 0.0) {
                final double d0 = (entityIn instanceof LivingEntity) ? 1.0 : 0.8;
                entityIn.setMotion(vec3d.x, -vec3d.y * d0, vec3d.z);
            }
        }
    }
    
    public void onEntityWalk(final World worldIn, final BlockPos pos, final Entity entityIn) {
        final double d0 = Math.abs(entityIn.getMotion().y);
        if (d0 < 0.1 && !entityIn.isSneaking()) {
            final double d2 = 0.4 + d0 * 0.2;
            entityIn.setMotion(entityIn.getMotion().mul(d2, 1.0, d2));
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack((IItemProvider)this));
    }
}
