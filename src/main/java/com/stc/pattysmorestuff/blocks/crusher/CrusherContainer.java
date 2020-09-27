package com.stc.pattysmorestuff.blocks.crusher;

import net.minecraft.world.*;
import net.minecraft.inventory.*;
import net.minecraft.util.*;
import net.minecraft.inventory.container.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.api.distmarker.*;

public class CrusherContainer extends Container
{
    private IInventory inventory;
    private IIntArray fields;
    private World world;
    
    public CrusherContainer(final int id, final PlayerInventory playerInv) {
        this(id, playerInv, (IInventory)new Inventory(3), (IIntArray)new IntArray(3));
    }
    
    public CrusherContainer(final int id, final PlayerInventory playerInventory, final IInventory inventory, final IIntArray fields) {
        super((ContainerType)ContainerTypes.CRUSHER, id);
        this.inventory = inventory;
        this.fields = fields;
        this.world = playerInventory.player.world;
        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new Slot(inventory, 1, 56, 53));
        this.addSlot(new Slot(inventory, 2, 116, 35));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot((IInventory)playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot((IInventory)playerInventory, k, 8 + k * 18, 142));
        }
        this.trackIntArray(fields);
    }
    
    public boolean canInteractWith(final PlayerEntity playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }
    
    public ItemStack transferStackInSlot(final PlayerEntity playerIn, final int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (index == 2) {
                if (!this.mergeItemStack(itemstack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack2, itemstack);
            }
            else if (index != 1 && index != 0) {
                if (this.func_217057_a(itemstack2)) {
                    if (!this.mergeItemStack(itemstack2, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (this.isFuel(itemstack2)) {
                    if (!this.mergeItemStack(itemstack2, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(itemstack2, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack2, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack2, 3, 39, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack2.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }
            if (itemstack2.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack2);
        }
        return itemstack;
    }
    
    protected boolean func_217057_a(final ItemStack p_217057_1_) {
        return this.world.getRecipeManager().getRecipe((IRecipeType)CrushingRecipe.CRUSHING, (IInventory)new Inventory(new ItemStack[] { p_217057_1_ }), this.world).isPresent();
    }
    
    protected boolean isFuel(final ItemStack stack) {
        return AbstractFurnaceTileEntity.isFuel(stack);
    }
    
    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled() {
        final int i = this.fields.get(1);
        final int j = this.fields.get(2);
        return (j != 0 && i != 0) ? (i * 24 / j) : 0;
    }
    
    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {
        final int i = 200;
        return this.fields.get(0) * 13 / i;
    }
    
    @OnlyIn(Dist.CLIENT)
    public boolean func_217061_l() {
        return this.fields.get(0) > 0;
    }
}
