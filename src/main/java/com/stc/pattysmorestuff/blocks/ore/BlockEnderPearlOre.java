package com.stc.pattysmorestuff.blocks.ore;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockEnderPearlOre extends Block
{
    public BlockEnderPearlOre(final String name, final Block.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
    
    protected int getExperience(final Random p_220281_1_) {
        if (this == ModBlocks.ender_pearl_ore) {
            return MathHelper.nextInt(p_220281_1_, 3, 7);
        }
        if (this == ModBlocks.ender_pearl_ore_nether) {
            return MathHelper.nextInt(p_220281_1_, 3, 7);
        }
        if (this == ModBlocks.ender_pearl_ore_end) {
            return MathHelper.nextInt(p_220281_1_, 3, 7);
        }
        return (this == Blocks.NETHER_QUARTZ_ORE) ? MathHelper.nextInt(p_220281_1_, 2, 5) : 0;
    }
    
    public void spawnAdditionalDrops(final BlockState state, final World worldIn, final BlockPos pos, final ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }
    
    public int getExpDrop(final BlockState state, final IWorldReader reader, final BlockPos pos, final int fortune, final int silktouch) {
        return (silktouch == 0) ? this.getExperience(this.RANDOM) : 0;
    }
    
    public List<ItemStack> getDrops(final BlockState p_220076_1_, final LootContext.Builder p_220076_2_) {
        return Collections.singletonList(new ItemStack((IItemProvider)Items.ENDER_PEARL));
    }
}
