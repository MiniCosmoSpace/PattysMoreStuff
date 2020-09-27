package com.stc.pattysmorestuff.blocks.crusher;

import net.minecraftforge.common.util.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraftforge.items.wrapper.*;
import net.minecraft.nbt.*;
import net.minecraft.item.crafting.*;
import net.minecraft.inventory.*;
import net.minecraft.util.math.*;
import net.minecraft.state.*;
import javax.annotation.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.event.*;
import net.minecraft.item.*;
import java.util.function.*;
import java.util.*;
import net.minecraft.util.text.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.items.*;

public class CrusherTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity
{
    private static final int[] SLOTS_UP;
    private static final int[] SLOTS_DOWN;
    private static final int[] SLOTS_HORIZONTAL;
    private NonNullList<ItemStack> crusherItemStacks;
    private int burnTime;
    private int crushTime;
    private int crushTimeTotal;
    protected final IIntArray fields;
    LazyOptional<? extends IItemHandler>[] handlers;
    
    public CrusherTileEntity() {
        super((TileEntityType)ModTileEntities.CRUSHER);
        this.crusherItemStacks = (NonNullList<ItemStack>)NonNullList.withSize(3, ItemStack.EMPTY);
        this.fields = (IIntArray)new IIntArray() {
            public int get(final int p_221476_1_) {
                switch (p_221476_1_) {
                    case 0: {
                        return CrusherTileEntity.this.burnTime;
                    }
                    case 1: {
                        return CrusherTileEntity.this.crushTime;
                    }
                    case 2: {
                        return CrusherTileEntity.this.crushTimeTotal;
                    }
                    default: {
                        return 0;
                    }
                }
            }
            
            public void set(final int p_221477_1_, final int p_221477_2_) {
                switch (p_221477_1_) {
                    case 0: {
                        CrusherTileEntity.this.burnTime = p_221477_2_;
                        break;
                    }
                    case 1: {
                        CrusherTileEntity.this.crushTime = p_221477_2_;
                        break;
                    }
                    case 2: {
                        CrusherTileEntity.this.crushTimeTotal = p_221477_2_;
                        break;
                    }
                }
            }
            
            public int size() {
                return 3;
            }
        };
        this.handlers = (LazyOptional<? extends IItemHandler>[])SidedInvWrapper.create((ISidedInventory)this, new Direction[] { Direction.UP, Direction.DOWN, Direction.NORTH });
    }
    
    public void read(final CompoundNBT compound) {
        super.read(compound);
        ItemStackHelper.loadAllItems(compound, (NonNullList)(this.crusherItemStacks = (NonNullList<ItemStack>)NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY)));
        this.burnTime = compound.getInt("BurnTime");
        this.crushTime = compound.getInt("CrushTime");
        this.crushTimeTotal = compound.getInt("CrushTimeTotal");
    }
    
    public CompoundNBT write(final CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CrushTime", this.crushTime);
        compound.putInt("CrushTimeTotal", this.crushTimeTotal);
        ItemStackHelper.saveAllItems(compound, (NonNullList)this.crusherItemStacks);
        return compound;
    }
    
    private boolean isActive() {
        return this.burnTime > 0;
    }
    
    public void tick() {
        final boolean flag = this.isActive();
        boolean flag2 = false;
        if (this.isActive()) {
            --this.burnTime;
        }
        if (!this.world.isRemote) {
            final ItemStack itemstack = (ItemStack)this.crusherItemStacks.get(1);
            if (this.isActive() || (!itemstack.isEmpty() && !((ItemStack)this.crusherItemStacks.get(0)).isEmpty())) {
                final IRecipe<?> irecipe = (IRecipe<?>)this.world.getRecipeManager().getRecipe((IRecipeType)CrushingRecipe.CRUSHING, (IInventory)this, this.world).orElse(null);
                if (!this.isActive() && this.canCrush(irecipe)) {
                    this.burnTime = this.getBurnTime(itemstack);
                    if (this.isActive()) {
                        flag2 = true;
                        if (itemstack.hasContainerItem()) {
                            this.crusherItemStacks.set(1, itemstack.getContainerItem());
                        }
                        else if (!itemstack.isEmpty()) {
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                this.crusherItemStacks.set(1, itemstack.getContainerItem());
                            }
                        }
                    }
                }
                if (this.isActive() && this.canCrush(irecipe)) {
                    ++this.crushTime;
                    if (this.crushTime == this.crushTimeTotal) {
                        this.crushTime = 0;
                        this.crushTimeTotal = this.func_214005_h();
                        this.crushItem(irecipe);
                        flag2 = true;
                    }
                }
                else {
                    this.crushTime = 0;
                }
            }
            else if (!this.isActive() && this.crushTime > 0) {
                this.crushTime = MathHelper.clamp(this.crushTime - 2, 0, this.crushTimeTotal);
            }
            if (flag != this.isActive()) {
                flag2 = true;
                this.world.setBlockState(this.pos, (BlockState)this.world.getBlockState(this.pos).with((IProperty)CustomMachineBlock.ACTIVE, (Comparable)this.isActive()), 3);
            }
        }
        if (flag2) {
            this.markDirty();
        }
    }
    
    protected boolean canCrush(@Nullable final IRecipe<?> p_214008_1_) {
        if (((ItemStack)this.crusherItemStacks.get(0)).isEmpty() || p_214008_1_ == null) {
            return false;
        }
        final ItemStack itemstack = p_214008_1_.getRecipeOutput();
        if (itemstack.isEmpty()) {
            return false;
        }
        final ItemStack itemstack2 = (ItemStack)this.crusherItemStacks.get(2);
        return itemstack2.isEmpty() || (itemstack2.isItemEqual(itemstack) && ((itemstack2.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack2.getCount() + itemstack.getCount() <= itemstack2.getMaxStackSize()) || itemstack2.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize()));
    }
    
    private void crushItem(@Nullable final IRecipe<?> p_214007_1_) {
        if (p_214007_1_ != null && this.canCrush(p_214007_1_)) {
            final ItemStack itemstack = (ItemStack)this.crusherItemStacks.get(0);
            final ItemStack itemstack2 = p_214007_1_.getRecipeOutput();
            final ItemStack itemstack3 = (ItemStack)this.crusherItemStacks.get(2);
            if (itemstack3.isEmpty()) {
                this.crusherItemStacks.set(2, itemstack2.copy());
            }
            else if (itemstack3.getItem() == itemstack2.getItem()) {
                itemstack3.grow(itemstack2.getCount());
            }
            if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !((ItemStack)this.crusherItemStacks.get(1)).isEmpty() && ((ItemStack)this.crusherItemStacks.get(1)).getItem() == Items.BUCKET) {
                this.crusherItemStacks.set(1, new ItemStack((IItemProvider)Items.WATER_BUCKET));
            }
            itemstack.shrink(1);
        }
    }
    
    protected int getBurnTime(final ItemStack p_213997_1_) {
        if (p_213997_1_.isEmpty()) {
            return 0;
        }
        final Item item = p_213997_1_.getItem();
        final int ret = p_213997_1_.getBurnTime();
        return ForgeEventFactory.getItemBurnTime(p_213997_1_, (ret == -1) ? ((int)AbstractFurnaceTileEntity.getBurnTimes().getOrDefault(item, 0)) : ret);
    }
    
    protected int func_214005_h() {
        return this.world.getRecipeManager().getRecipe(CrushingRecipe.CRUSHING, (IInventory)this, this.world).map(CrushingRecipe::getCrushTime).orElse(200);
    }
    
    public int getSizeInventory() {
        return this.crusherItemStacks.size();
    }
    
    public boolean isEmpty() {
        for (final ItemStack itemstack : this.crusherItemStacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public ItemStack getStackInSlot(final int index) {
        return (ItemStack)this.crusherItemStacks.get(index);
    }
    
    public ItemStack decrStackSize(final int index, final int count) {
        return ItemStackHelper.getAndSplit((List)this.crusherItemStacks, index, count);
    }
    
    public ItemStack removeStackFromSlot(final int index) {
        return ItemStackHelper.getAndRemove((List)this.crusherItemStacks, index);
    }
    
    public void setInventorySlotContents(final int index, final ItemStack stack) {
        final ItemStack itemstack = (ItemStack)this.crusherItemStacks.get(index);
        final boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.crusherItemStacks.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        if (index == 0 && !flag) {
            this.crushTimeTotal = this.func_214005_h();
            this.crushTime = 0;
            this.markDirty();
        }
    }
    
    public boolean isUsableByPlayer(final PlayerEntity player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
    }
    
    public void clear() {
        this.crusherItemStacks.clear();
    }
    
    public boolean isItemValidForSlot(final int index, final ItemStack stack) {
        if (index == 2) {
            return false;
        }
        if (index != 1) {
            return true;
        }
        final ItemStack itemstack = (ItemStack)this.crusherItemStacks.get(1);
        return AbstractFurnaceTileEntity.isFuel(stack) || (stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET);
    }
    
    public int[] getSlotsForFace(final Direction side) {
        if (side == Direction.DOWN) {
            return CrusherTileEntity.SLOTS_DOWN;
        }
        return (side == Direction.UP) ? CrusherTileEntity.SLOTS_UP : CrusherTileEntity.SLOTS_HORIZONTAL;
    }
    
    public boolean canInsertItem(final int index, final ItemStack itemStackIn, @Nullable final Direction direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }
    
    public boolean canExtractItem(final int index, final ItemStack stack, final Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            final Item item = stack.getItem();
            if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
                return false;
            }
        }
        return true;
    }
    
    protected ITextComponent getDefaultName() {
        return (ITextComponent)new TranslationTextComponent("container.pattysmorestuff.crusher", new Object[0]);
    }
    
    protected Container createMenu(final int i, final PlayerInventory inventoryPlayer) {
        return new CrusherContainer(i, inventoryPlayer, (IInventory)this, this.fields);
    }
    
    public <T> LazyOptional<T> getCapability(final Capability<T> capability, @Nullable final Direction facing) {
        if (this.removed || facing == null || capability != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (LazyOptional<T>)super.getCapability((Capability)capability, facing);
        }
        if (facing == Direction.UP) {
            return (LazyOptional<T>)this.handlers[0].cast();
        }
        if (facing == Direction.DOWN) {
            return (LazyOptional<T>)this.handlers[1].cast();
        }
        return (LazyOptional<T>)this.handlers[2].cast();
    }
    
    public void remove() {
        super.remove();
        for (int x = 0; x < this.handlers.length; ++x) {
            this.handlers[x].invalidate();
        }
    }
    
    static {
        SLOTS_UP = new int[] { 0 };
        SLOTS_DOWN = new int[] { 2, 1 };
        SLOTS_HORIZONTAL = new int[] { 1 };
    }
}
