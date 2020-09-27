package com.stc.pattysmorestuff.blocks.asphalt;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.block.*;
import net.minecraft.state.*;
import net.minecraft.block.material.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockAsphaltCenter extends HorizontalBlock
{
    public BlockAsphaltCenter(final String name, final Block.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
    
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[] { (IProperty)BlockAsphaltCenter.HORIZONTAL_FACING });
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return (BlockState)this.getDefaultState().with((IProperty)BlockAsphaltCenter.HORIZONTAL_FACING, (Comparable)context.getPlacementHorizontalFacing().getOpposite());
    }
    
    public PushReaction getPushReaction(final BlockState state) {
        return PushReaction.PUSH_ONLY;
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack((IItemProvider)this));
    }
}
