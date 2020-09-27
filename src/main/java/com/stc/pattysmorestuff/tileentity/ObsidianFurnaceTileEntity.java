package com.stc.pattysmorestuff.tileentity;

import com.stc.pattysmorestuff.tileentity.abstracts.*;
import com.stc.pattysmorestuff.init.*;
import com.stc.pattysmorestuff.util.*;
import net.minecraft.tileentity.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.text.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.*;
import net.minecraft.inventory.*;

public class ObsidianFurnaceTileEntity extends AbstractObsidianFurnaceTileEntity
{
    public ObsidianFurnaceTileEntity() {
        super(ModTileEntities.OBSIDIAN_FURNACE_TE, (IRecipeType<? extends AbstractCookingRecipe>)IRecipeType.SMELTING, FurnaceType.OBSIDIAN_FURNACE.cookSpeed, FurnaceType.OBSIDIAN_FURNACE.output);
    }
    
    protected ITextComponent getDefaultName() {
        return (ITextComponent)new TranslationTextComponent("Obsidian Furnace", new Object[0]);
    }
    
    protected Container createMenu(final int id, final PlayerInventory player) {
        return (Container)new FurnaceContainer(id, player, (IInventory)this, this.furnaceData);
    }
}
