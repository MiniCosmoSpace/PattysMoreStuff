package com.stc.pattysmorestuff.items.toolsandarmors;

import com.stc.pattysmorestuff.PattysMoreStuff;
import com.stc.pattysmorestuff.config.ConfigGeneral;
import com.stc.pattysmorestuff.init.ModItems;
import com.stc.pattysmorestuff.init.ModTabs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ItemMoreArmor extends ArmorItem {


    public ItemMoreArmor(String name, IArmorMaterial materialIn, String armorType, EquipmentSlotType slots, Properties builder) {
        super(materialIn, slots, builder.group(ModTabs.tabPattysCombat));
        this.setRegistryName(PattysMoreStuff.MODID, name);

        if(ConfigGeneral.disableArmor.get()) {
            ModItems.ITEMS.add(this);
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.miners_helmet){
            player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 600 ,0, false, false));
        }

        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.glowstone_helmet
                && player.getItemStackFromSlot(EquipmentSlotType.CHEST) != null && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.glowstone_chestplate
                && player.getItemStackFromSlot(EquipmentSlotType.LEGS) != null && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.glowstone_leggings
                && player.getItemStackFromSlot(EquipmentSlotType.FEET) != null && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.glowstone_boots) {
            player.addPotionEffect(new EffectInstance(Effects.GLOWING, 600,0, false,false));
        }

        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.blaze_helmet
                && player.getItemStackFromSlot(EquipmentSlotType.CHEST) != null && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.blaze_chestplate
                && player.getItemStackFromSlot(EquipmentSlotType.LEGS) != null && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.blaze_leggings
                && player.getItemStackFromSlot(EquipmentSlotType.FEET) != null && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.blaze_boots) {
            player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600,0, false,false));
        }

        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.magma_cream_helmet
                && player.getItemStackFromSlot(EquipmentSlotType.CHEST) != null && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.magma_cream_chestplate
                && player.getItemStackFromSlot(EquipmentSlotType.LEGS) != null && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.magma_cream_leggings
                && player.getItemStackFromSlot(EquipmentSlotType.FEET) != null && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.magma_cream_boots) {
            player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600, 0, false, false));
        }

        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.obsidian_helmet
                && player.getItemStackFromSlot(EquipmentSlotType.CHEST) != null && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.obsidian_chestplate
                && player.getItemStackFromSlot(EquipmentSlotType.LEGS) != null && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.obsidian_leggings
                && player.getItemStackFromSlot(EquipmentSlotType.FEET) != null && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.obsidian_boots) {
            player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 600, 3, false, false));
        }

        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.quartz_helmet
                && player.getItemStackFromSlot(EquipmentSlotType.CHEST) != null && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.quartz_chestplate
                && player.getItemStackFromSlot(EquipmentSlotType.LEGS) != null && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.quartz_leggings
                && player.getItemStackFromSlot(EquipmentSlotType.FEET) != null && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.quartz_boots) {
            player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 600, 3, false, false));
        }

        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.ender_helmet
                && player.getItemStackFromSlot(EquipmentSlotType.CHEST) != null && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.ender_chestplate
                && player.getItemStackFromSlot(EquipmentSlotType.LEGS) != null && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.ender_leggings
                && player.getItemStackFromSlot(EquipmentSlotType.FEET) != null && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.ender_boots) {
            player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 3, false, false));
        }

        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.soul_sand_helmet
                && player.getItemStackFromSlot(EquipmentSlotType.CHEST) != null && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.soul_sand_chestplate
                && player.getItemStackFromSlot(EquipmentSlotType.LEGS) != null && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.soul_sand_leggings
                && player.getItemStackFromSlot(EquipmentSlotType.FEET) != null && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.soul_sand_boots) {
            player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 3, false, false));
        }
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.getItem() == ModItems.nether_star_helmet) {
            return true;
        }
        if(stack.getItem() == ModItems.nether_star_chestplate) {
            return true;
        }
        if(stack.getItem() == ModItems.nether_star_leggings) {
            return true;
        }
        if(stack.getItem() == ModItems.nether_star_boots) {
            return true;
        }
        return false;
}
}
