package com.stc.pattysmorestuff.blocks.furnaces;

import com.stc.pattysmorestuff.blocks.furnaces.abstracts.*;
import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.*;
import com.stc.pattysmorestuff.tileentity.*;
import javax.annotation.*;
import net.minecraft.block.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockEmeraldFurnace extends AbstractEmeraldFurnaceBlock
{
    public BlockEmeraldFurnace(final String name, final Block.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableFurnaces.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
        }
    }
    
    @Nullable
    public TileEntity createNewTileEntity(final IBlockReader worldIn) {
        return (TileEntity)new EmeraldFurnaceTileEntity();
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack((IItemProvider)this));
    }
}
