package com.stc.pattysmorestuff.init;

import com.stc.pattysmorestuff.config.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class ModTabs
{
    public static ItemGroup tabPattysBlocks;
    public static ItemGroup tabPattysDecoration;
    public static ItemGroup tabPattysButtons;
    public static ItemGroup tabPattysMisc;
    public static ItemGroup tabPattysFood;
    public static ItemGroup tabPattysTools;
    public static ItemGroup tabPattysCombat;
    
    public static void pattysTabs() {
        if (ConfigGeneral.disableBlocks.get()) {
            ModTabs.tabPattysBlocks = new ItemGroup(ItemGroup.getGroupCountSafe(), "Blocks") {
                public ItemStack createIcon() {
                    return new ItemStack((IItemProvider)ModBlocks.dye_block_black);
                }
                
                public String getTranslationKey() {
                    return "Pattys Blocks";
                }
            };
            ModTabs.tabPattysDecoration = new ItemGroup(ItemGroup.getGroupCountSafe(), "Decoration") {
                public ItemStack createIcon() {
                    return new ItemStack((IItemProvider)ModBlocks.clay_black_fence);
                }
                
                public String getTranslationKey() {
                    return "Pattys Decoration";
                }
            };
            ModTabs.tabPattysButtons = new ItemGroup(ItemGroup.getGroupCountSafe(), "Buttons") {
                public ItemStack createIcon() {
                    return new ItemStack((IItemProvider)ModBlocks.blue_button);
                }
                
                public String getTranslationKey() {
                    return "Pattys Buttons";
                }
            };
        }
        ModTabs.tabPattysMisc = new ItemGroup(ItemGroup.getGroupCountSafe(), "Misc") {
            public ItemStack createIcon() {
                return new ItemStack((IItemProvider)ModItems.obsidian_ingot);
            }
            
            public String getTranslationKey() {
                return "Pattys Misc";
            }
        };
        if (ConfigGeneral.disableFood.get()) {
            ModTabs.tabPattysFood = new ItemGroup(ItemGroup.getGroupCountSafe(), "Food") {
                public ItemStack createIcon() {
                    return new ItemStack((IItemProvider)ModItems.bacon_cooked);
                }
                
                public String getTranslationKey() {
                    return "Pattys Food";
                }
            };
        }
        if (ConfigGeneral.disableTools.get()) {
            ModTabs.tabPattysTools = new ItemGroup(ItemGroup.getGroupCountSafe(), "Tools") {
                public ItemStack createIcon() {
                    return new ItemStack((IItemProvider)ModItems.obsidian_axe);
                }
                
                public String getTranslationKey() {
                    return "Pattys Tools";
                }
            };
        }
        ModTabs.tabPattysCombat = new ItemGroup(ItemGroup.getGroupCountSafe(), "Combat") {
            public ItemStack createIcon() {
                return new ItemStack((IItemProvider)ModItems.ender_sword);
            }
            
            public String getTranslationKey() {
                return "Pattys Combat";
            }
        };
    }
}
