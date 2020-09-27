package com.stc.pattysmorestuff.tileentity;

import com.stc.pattysmorestuff.init.ModTileEntities;
import com.stc.pattysmorestuff.util.FurnaceType;
import com.stc.pattysmorestuff.handlers.RegisteryHandler;
import com.stc.pattysmorestuff.tileentity.abstracts.AbstractDiamondFurnaceTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DiamondFurnaceTileEntity extends AbstractDiamondFurnaceTileEntity {
    public DiamondFurnaceTileEntity() {
        super(ModTileEntities.DIAMOND_FURNACE_TE, IRecipeType.SMELTING, FurnaceType.DIAMOND_FURNACE.cookSpeed, FurnaceType.DIAMOND_FURNACE.output);
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("Diamond Furnace");
    }

    protected Container createMenu(int id, PlayerInventory player) {
        return new FurnaceContainer(id, player, this, this.furnaceData);
    }
}
