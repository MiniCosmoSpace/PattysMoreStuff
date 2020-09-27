package com.stc.pattysmorestuff.blocks.reinforced;

import com.stc.pattysmorestuff.blocks.*;
import net.minecraft.item.*;
import javax.annotation.*;
import java.util.*;
import net.minecraft.client.util.*;
import net.minecraft.util.text.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import com.stc.pattysmorestuff.init.*;

public class ReinforcedObsidianBlock extends BlockBase
{
    public ReinforcedObsidianBlock(final String name, final Block.Properties properties) {
        super(name, properties);
    }
    
    public void addInformation(final ItemStack stack, @Nullable final IBlockReader worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        tooltip.add((ITextComponent)new StringTextComponent("Wither proof!"));
        tooltip.add((ITextComponent)new StringTextComponent("Can be broken by players!"));
    }
    
    public boolean canEntityDestroy(final BlockState state, final IBlockReader world, final BlockPos pos, final Entity entity) {
        return entity instanceof PlayerEntity;
    }
    
    public void onBlockExploded(final BlockState state, final World world, final BlockPos pos, final Explosion explosion) {
        if (world.getBlockState(pos).getBlock() == ModBlocks.reinforced_obsidian) {
            explosion.clearAffectedBlockPositions();
        }
    }
    
    public boolean canDropFromExplosion(final BlockState state, final IBlockReader world, final BlockPos pos, final Explosion explosion) {
        return false;
    }
}
