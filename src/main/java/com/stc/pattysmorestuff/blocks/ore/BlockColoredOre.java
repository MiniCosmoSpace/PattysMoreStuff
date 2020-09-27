package com.stc.pattysmorestuff.blocks.ore;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import javax.annotation.*;
import net.minecraft.client.util.*;
import net.minecraft.util.text.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;

public class BlockColoredOre extends Block
{
    public BlockColoredOre(final String name, final Block.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public void addInformation(final ItemStack stack, @Nullable final IBlockReader world, final List<ITextComponent> tooltip, final ITooltipFlag flag) {
        tooltip.add((ITextComponent)new StringTextComponent("When mined the block will drop random amounts of dye between [1 - 5] of a single dye type!"));
    }
    
    protected int getExperience(final Random p_220281_1_) {
        if (this == ModBlocks.dye_ore) {
            return MathHelper.nextInt(p_220281_1_, 2, 5);
        }
        if (this == ModBlocks.dye_ore_nether) {
            return MathHelper.nextInt(p_220281_1_, 2, 5);
        }
        if (this == ModBlocks.dye_ore_end) {
            return MathHelper.nextInt(p_220281_1_, 2, 5);
        }
        return (this == Blocks.NETHER_QUARTZ_ORE) ? MathHelper.nextInt(p_220281_1_, 2, 5) : 0;
    }
    
    public void spawnAdditionalDrops(final BlockState state, final World worldIn, final BlockPos pos, final ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }
    
    public int getExpDrop(final BlockState state, final IWorldReader reader, final BlockPos pos, final int fortune, final int silktouch) {
        return (silktouch == 0) ? this.getExperience(this.RANDOM) : 0;
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        final Random random = new Random();
        final int drop = random.nextInt(20);
        if (drop == 0) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.BLACK_DYE, random.nextInt(6)));
        }
        if (drop == 1) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.RED_DYE, random.nextInt(6)));
        }
        if (drop == 2) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.GREEN_DYE, random.nextInt(6)));
        }
        if (drop == 3) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.BROWN_DYE, random.nextInt(6)));
        }
        if (drop == 4) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.BLUE_DYE, random.nextInt(6)));
        }
        if (drop == 5) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.PURPLE_DYE, random.nextInt(6)));
        }
        if (drop == 6) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.CYAN_DYE, random.nextInt(6)));
        }
        if (drop == 7) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.LIGHT_GRAY_DYE, random.nextInt(6)));
        }
        if (drop == 8) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.GRAY_DYE, random.nextInt(6)));
        }
        if (drop == 9) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.PINK_DYE, random.nextInt(6)));
        }
        if (drop == 10) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.LIME_DYE, random.nextInt(6)));
        }
        if (drop == 11) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.YELLOW_DYE, random.nextInt(6)));
        }
        if (drop == 12) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.LIGHT_BLUE_DYE, random.nextInt(6)));
        }
        if (drop == 13) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.MAGENTA_DYE, random.nextInt(6)));
        }
        if (drop == 14) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.ORANGE_DYE, random.nextInt(6)));
        }
        if (drop == 15) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.WHITE_DYE, random.nextInt(6)));
        }
        if (drop == 16) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.INK_SAC, random.nextInt(6)));
        }
        if (drop == 17) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.COCOA_BEANS, random.nextInt(6)));
        }
        if (drop == 18) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.LAPIS_LAZULI, random.nextInt(6)));
        }
        if (drop == 19) {
            return Collections.singletonList(new ItemStack((IItemProvider)Items.BONE_MEAL, random.nextInt(6)));
        }
        return null;
    }
}
