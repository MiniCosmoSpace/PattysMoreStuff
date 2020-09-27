package com.stc.pattysmorestuff.blocks.blender;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.world.*;

public class BlockBlender extends Block
{
    public BlockBlender(final String name, final Block.Properties properties) {
        super(properties.hardnessAndResistance(3.0f, 3.0f));
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public BlockRenderType getRenderType(final BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    public boolean onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            if (player.getHeldItem(hand).getItem() == Items.CARROT) {
                final ItemStack CARROT_JUICE = new ItemStack((IItemProvider)ModItems.carrot_juice);
                player.getHeldItem(hand).shrink(1);
                player.addItemStackToInventory(CARROT_JUICE);
                return true;
            }
            if (player.getHeldItem(hand).getItem() == Items.MELON) {
                final ItemStack MELON_JUICE = new ItemStack((IItemProvider)ModItems.melon_juice);
                player.getHeldItem(hand).shrink(1);
                player.addItemStackToInventory(MELON_JUICE);
                return true;
            }
            if (player.getHeldItem(hand).getItem() == Items.BEETROOT) {
                final ItemStack BEETROOT_JUICE = new ItemStack((IItemProvider)ModItems.beetroot_juice);
                player.getHeldItem(hand).shrink(1);
                player.addItemStackToInventory(BEETROOT_JUICE);
                return true;
            }
            if (player.getHeldItem(hand).getItem() == Items.APPLE) {
                final ItemStack APPLE_JUICE = new ItemStack((IItemProvider)ModItems.apple_juice);
                player.getHeldItem(hand).shrink(1);
                player.addItemStackToInventory(APPLE_JUICE);
                return true;
            }
            if (player.getHeldItem(hand).getItem() == Item.getItemFromBlock(Blocks.PUMPKIN)) {
                final ItemStack PUMPKIN_JUICE = new ItemStack((IItemProvider)ModItems.pumpkin_juice);
                player.getHeldItem(hand).shrink(1);
                player.addItemStackToInventory(PUMPKIN_JUICE);
                return true;
            }
        }
        return false;
    }
    
    public boolean isNormalCube(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
        return false;
    }
}
