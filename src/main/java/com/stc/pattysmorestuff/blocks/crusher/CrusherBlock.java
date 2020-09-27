package com.stc.pattysmorestuff.blocks.crusher;

import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.*;
import net.minecraft.block.*;
import net.minecraft.state.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.particles.*;
import net.minecraftforge.api.distmarker.*;

public class CrusherBlock extends CustomMachineBlock
{
    public CrusherBlock(final Block.Properties builder) {
        super(builder);
        this.setRegistryName("pattysmorestuff", "crusher");
        ModBlocks.BLOCKS.add((Block)this);
        ModItems.ITEMS.add((Item)new BlockItem((Block)this, new Item.Properties().group(ModTabs.tabPattysDecoration)).setRegistryName(this.getRegistryName()));
    }
    
    public TileEntity createNewTileEntity(final IBlockReader worldIn) {
        return (TileEntity)new CrusherTileEntity();
    }
    
    @Override
    public void openContainer(final World world, final BlockPos pos, final PlayerEntity player) {
        final TileEntity tileentity = world.getTileEntity(pos);
        if (tileentity instanceof CrusherTileEntity && player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity)player).openContainer((INamedContainerProvider)tileentity);
        }
    }
    
    public int getLightValue(final BlockState state) {
        return state.get(CrusherBlock.ACTIVE) ? 13 : 0;
    }
    
    @OnlyIn(Dist.CLIENT)
    public void animateTick(final BlockState stateIn, final World worldIn, final BlockPos pos, final Random rand) {
        if (stateIn.get(CrusherBlock.ACTIVE)) {
            final double d0 = pos.getX() + 0.5;
            final double d2 = pos.getY();
            final double d3 = pos.getZ() + 0.5;
            if (rand.nextDouble() < 0.1) {
                worldIn.playSound(d0, d2, d3, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
            }
            final Direction direction = (Direction)stateIn.get((IProperty)CrusherBlock.HORIZONTAL_FACING);
            final Direction.Axis direction$axis = direction.getAxis();
            final double d4 = 0.52;
            final double d5 = rand.nextDouble() * 0.6 - 0.3;
            final double d6 = (direction$axis == Direction.Axis.X) ? (direction.getXOffset() * d4) : d5;
            final double d7 = rand.nextDouble() * 6.0 / 16.0;
            final double d8 = (direction$axis == Direction.Axis.Z) ? (direction.getZOffset() * d4) : d5;
            worldIn.addParticle((IParticleData)ParticleTypes.SMOKE, d0 + d6, d2 + d7, d3 + d8, 0.0, 0.0, 0.0);
            worldIn.addParticle((IParticleData)ParticleTypes.FLAME, d0 + d6, d2 + d7, d3 + d8, 0.0, 0.0, 0.0);
        }
    }
}
