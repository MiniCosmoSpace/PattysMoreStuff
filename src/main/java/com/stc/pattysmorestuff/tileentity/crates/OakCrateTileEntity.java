package com.stc.pattysmorestuff.tileentity.crates;

import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraftforge.common.util.*;
import net.minecraftforge.items.*;
import com.stc.pattysmorestuff.init.*;
import java.util.*;
import net.minecraft.util.text.*;
import net.minecraft.nbt.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.tileentity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.*;
import net.minecraft.inventory.*;

public class OakCrateTileEntity extends LockableLootTileEntity implements INamedContainerProvider
{
    private NonNullList<ItemStack> crateContents;
    public int numPlayersUsing;
    private int ticksSinceSync;
    private LazyOptional<IItemHandlerModifiable> crateHandler;
    
    public OakCrateTileEntity() {
        super((TileEntityType)ModTileEntities.OAK_STORAGE_CRATE_TE);
        this.crateContents = (NonNullList<ItemStack>)NonNullList.withSize(54, ItemStack.EMPTY);
    }
    
    public int getSizeInventory() {
        return 54;
    }
    
    public boolean isEmpty() {
        for (final ItemStack itemstack : this.crateContents) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    protected ITextComponent getDefaultName() {
        return (ITextComponent)new TranslationTextComponent("Oak Crate", new Object[0]);
    }
    
    public void read(final CompoundNBT compound) {
        super.read(compound);
        this.crateContents = (NonNullList<ItemStack>)NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, (NonNullList)this.crateContents);
        }
    }
    
    public CompoundNBT write(final CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, (NonNullList)this.crateContents);
        }
        return compound;
    }
    
    public boolean isUsableByPlayer(final PlayerEntity player) {
        return this.world.getTileEntity(this.pos) == this && this.world.getBlockState(this.pos).getBlock() != Blocks.AIR && player.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
    }
    
    public void tick() {
        final int i = this.pos.getX();
        final int j = this.pos.getY();
        final int k = this.pos.getZ();
        ++this.ticksSinceSync;
        this.numPlayersUsing = calculatePlayersUsingSync(this.world, (LockableTileEntity)this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
    }
    
    public static int calculatePlayersUsingSync(final World world, final LockableTileEntity lockableTileEntity, final int p_213977_2_, final int p_213977_3_, final int p_213977_4_, final int p_213977_5_, final int p_213977_6_) {
        return p_213977_6_;
    }
    
    public boolean receiveClientEvent(final int id, final int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        }
        return super.receiveClientEvent(id, type);
    }
    
    public void openInventory(final PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }
            ++this.numPlayersUsing;
        }
    }
    
    protected NonNullList<ItemStack> getItems() {
        return this.crateContents;
    }
    
    protected void setItems(final NonNullList<ItemStack> itemsIn) {
        this.crateContents = itemsIn;
    }
    
    public static int getPlayersUsing(final IBlockReader reader, final BlockPos posIn) {
        final BlockState blockstate = reader.getBlockState(posIn);
        if (blockstate.hasTileEntity()) {
            final TileEntity tileentity = reader.getTileEntity(posIn);
            if (tileentity instanceof OakCrateTileEntity) {
                return ((OakCrateTileEntity)tileentity).numPlayersUsing;
            }
        }
        return 0;
    }
    
    protected Container createMenu(final int id, final PlayerInventory player) {
        return (Container)ChestContainer.createGeneric9X6(id, player, (IInventory)this);
    }
    
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if (this.crateHandler != null) {
            this.crateHandler.invalidate();
            this.crateHandler = null;
        }
    }
    
    public void remove() {
        super.remove();
        if (this.crateHandler != null) {
            this.crateHandler.invalidate();
        }
    }
    
    public void removeAdornments() {
    }
}
