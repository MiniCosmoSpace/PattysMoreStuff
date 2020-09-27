package com.stc.pattysmorestuff.items.toolsandarmors;

import com.stc.pattysmorestuff.PattysMoreStuff;
import com.stc.pattysmorestuff.config.ConfigGeneral;
import com.stc.pattysmorestuff.init.ModItems;

import net.minecraft.item.*;

public class ItemCustomSword extends SwordItem {

    public ItemCustomSword(String name, IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties p_i48460_4_) {
        super(tier, attackDamageIn, attackSpeedIn,p_i48460_4_);
        this.setRegistryName(PattysMoreStuff.MODID, name);
        if(ConfigGeneral.disableTools.get()) {
            ModItems.ITEMS.add(this);
        }
    }


    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return true;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nstar_sword) {
            return true;
        }

        return false;
    }
}
