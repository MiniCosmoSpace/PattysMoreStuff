package com.stc.pattysmorestuff.blocks.furnaces;

import com.stc.pattysmorestuff.PattysMoreStuff;
import com.stc.pattysmorestuff.blocks.furnaces.abstracts.AbstractDiamondFurnaceBlock;
import com.stc.pattysmorestuff.config.ConfigGeneral;
import com.stc.pattysmorestuff.init.ModBlocks;
import com.stc.pattysmorestuff.init.ModItems;
import com.stc.pattysmorestuff.init.ModTabs;
import com.stc.pattysmorestuff.tileentity.DiamondFurnaceTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.*;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class BlockDiamondFurnace extends AbstractDiamondFurnaceBlock {

    public BlockDiamondFurnace(String name, Properties properties) {
        super(properties);
        this.setRegistryName(PattysMoreStuff.MODID, name);

        if(ConfigGeneral.disableFurnaces.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new DiamondFurnaceTileEntity();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return (Collections.singletonList(new ItemStack(this)));
    }
}