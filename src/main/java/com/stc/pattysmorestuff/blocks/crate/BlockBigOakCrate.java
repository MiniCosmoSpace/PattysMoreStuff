package com.stc.pattysmorestuff.blocks.crate;

import com.stc.pattysmorestuff.config.*;
import net.minecraft.item.*;
import com.stc.pattysmorestuff.init.*;
import com.stc.pattysmorestuff.blocks.items.*;
import net.minecraft.block.*;
import net.minecraft.tileentity.*;
import com.stc.pattysmorestuff.tileentity.crates.*;
import javax.annotation.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.*;
import net.minecraftforge.fml.network.*;
import net.minecraft.inventory.*;

public class BlockBigOakCrate extends ContainerBlock
{
    public BlockBigOakCrate(final String name, final Block.Properties p_i48430_1_) {
        super(p_i48430_1_);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add((Block)this);
            ModItems.ITEMS.add((Item)new ItemCrateBlock((Block)this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
        }
    }
    
    @Nonnull
    public BlockRenderType getRenderType(final BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    @Nullable
    public TileEntity createNewTileEntity(final IBlockReader worldIn) {
        return (TileEntity)new DarkOakCrateTileEntity();
    }
    
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }
    
    public boolean onBlockActivated(final BlockState blockState, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult traceResult) {
        if (!world.isRemote) {
            NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)world.getTileEntity(pos), pos);
        }
        return true;
    }
    
    public void onReplaced(final BlockState state, final World worldIn, final BlockPos pos, final BlockState newState, final boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            final TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof DarkOakCrateTileEntity) {
                ((DarkOakCrateTileEntity)tileentity).removeAdornments();
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
                worldIn.updateComparatorOutputLevel(pos, (Block)this);
            }
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }
}
