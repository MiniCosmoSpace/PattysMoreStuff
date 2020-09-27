package com.stc.pattysmorestuff.blocks.reinforced;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
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
import net.minecraft.util.*;
import net.minecraft.util.math.shapes.*;

public class ReinforcedGlassBlock extends Block
{
    private final boolean collideOnSneaking;
    
    public ReinforcedGlassBlock(final String name, final Block.Properties properties, final boolean collideOnSneaking) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        this.collideOnSneaking = collideOnSneaking;
        if (ConfigGeneral.disableBlocks.get()) {
            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysBlocks)).setRegistryName(this.getRegistryName()));
        }
    }
    
    public void addInformation(final ItemStack stack, @Nullable final IBlockReader worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        tooltip.add((ITextComponent)new StringTextComponent("Wither proof!"));
        tooltip.add((ITextComponent)new StringTextComponent("Also stops light going through!"));
        tooltip.add((ITextComponent)new StringTextComponent("Can be broken by players!"));
    }
    
    public boolean canEntityDestroy(final BlockState state, final IBlockReader world, final BlockPos pos, final Entity entity) {
        return entity instanceof PlayerEntity;
    }
    
    public void onBlockExploded(final BlockState state, final World world, final BlockPos pos, final Explosion explosion) {
        if (world.getBlockState(pos).getBlock() == ModBlocks.reinforced_glass) {
            explosion.clearAffectedBlockPositions();
        }
    }
    
    public boolean canDropFromExplosion(final BlockState state, final IBlockReader world, final BlockPos pos, final Explosion explosion) {
        return false;
    }
    
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    public boolean doesSideBlockRendering(final BlockState state, final IEnviromentBlockReader world, final BlockPos pos, final Direction face) {
        return false;
    }
    
    public boolean isNormalCube(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
        return false;
    }
    
    public boolean causesSuffocation(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
        return false;
    }
    
    public boolean propagatesSkylightDown(final BlockState state, final IBlockReader reader, final BlockPos pos) {
        return false;
    }
    
    public int getOpacity(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
        return worldIn.getMaxLightLevel();
    }
    
    public VoxelShape getCollisionShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        return (context.isSneaking() == this.collideOnSneaking) ? state.getShape(worldIn, pos) : VoxelShapes.empty();
    }
}
