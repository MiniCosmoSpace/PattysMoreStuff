package com.stc.pattysmorestuff.items.infbuckets;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraftforge.event.*;
import net.minecraft.stats.*;
import net.minecraft.tags.*;
import net.minecraft.advancements.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import javax.annotation.*;
import net.minecraft.fluid.*;
import net.minecraft.world.*;
import net.minecraft.particles.*;
import net.minecraft.block.material.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.fluids.capability.wrappers.*;

public class ItemInfWaterBucket extends Item
{
    private final Fluid containedBlock;
    
    public ItemInfWaterBucket(final String name, final Fluid containedFluidIn, final Item.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        this.containedBlock = containedFluidIn;
        if (ConfigGeneral.disableInfiniteWaterBucket.get()) {
            ModItems.ITEMS.add(this);
        }
    }
    
    public boolean hasEffect(final ItemStack stack) {
        return true;
    }
    
    public ActionResult<ItemStack> onItemRightClick(final World worldIn, final PlayerEntity playerIn, final Hand handIn) {
        final ItemStack itemstack = playerIn.getHeldItem(handIn);
        final RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, (this.containedBlock == Fluids.EMPTY) ? RayTraceContext.FluidMode.SOURCE_ONLY : RayTraceContext.FluidMode.NONE);
        final ActionResult<ItemStack> ret = (ActionResult<ItemStack>)ForgeEventFactory.onBucketUse(playerIn, worldIn, itemstack, raytraceresult);
        if (ret != null) {
            return ret;
        }
        if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.PASS, (Object)itemstack);
        }
        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.PASS, (Object)itemstack);
        }
        final BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
        final BlockPos blockpos = blockraytraceresult.getPos();
        if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.FAIL, (Object)itemstack);
        }
        if (this.containedBlock == Fluids.EMPTY) {
            final BlockState blockstate1 = worldIn.getBlockState(blockpos);
            if (blockstate1.getBlock() instanceof IBucketPickupHandler) {
                final Fluid fluid = ((IBucketPickupHandler)blockstate1.getBlock()).pickupFluid((IWorld)worldIn, blockpos, blockstate1);
                if (fluid != Fluids.EMPTY) {
                    playerIn.addStat(Stats.ITEM_USED.get(this));
                    playerIn.playSound(fluid.isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL, 1.0f, 1.0f);
                    final ItemStack itemstack2 = this.fillBucket(itemstack, playerIn, fluid.getFilledBucket());
                    if (!worldIn.isRemote) {
                        CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity)playerIn, new ItemStack((IItemProvider)fluid.getFilledBucket()));
                    }
                    return (ActionResult<ItemStack>)new ActionResult(ActionResultType.SUCCESS, (Object)itemstack2);
                }
            }
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.FAIL, (Object)itemstack);
        }
        final BlockState blockstate2 = worldIn.getBlockState(blockpos);
        final BlockPos blockpos2 = (blockstate2.getBlock() instanceof ILiquidContainer && this.containedBlock == Fluids.WATER) ? blockpos : blockraytraceresult.getPos().offset(blockraytraceresult.getFace());
        if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos2, blockraytraceresult)) {
            this.onLiquidPlaced(worldIn, itemstack, blockpos2);
            if (playerIn instanceof ServerPlayerEntity) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerIn, blockpos2, itemstack);
            }
            playerIn.addStat(Stats.ITEM_USED.get(this));
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.SUCCESS, (Object)this.emptyBucket(itemstack, playerIn));
        }
        return (ActionResult<ItemStack>)new ActionResult(ActionResultType.FAIL, (Object)itemstack);
    }
    
    protected ItemStack emptyBucket(final ItemStack p_203790_1_, final PlayerEntity p_203790_2_) {
        return p_203790_2_.abilities.isCreativeMode ? p_203790_1_ : new ItemStack((IItemProvider)this);
    }
    
    public void onLiquidPlaced(final World worldIn, final ItemStack p_203792_2_, final BlockPos pos) {
    }
    
    private ItemStack fillBucket(final ItemStack emptyBuckets, final PlayerEntity player, final Item fullBucket) {
        if (player.abilities.isCreativeMode) {
            return emptyBuckets;
        }
        emptyBuckets.shrink(1);
        if (emptyBuckets.isEmpty()) {
            return new ItemStack((IItemProvider)fullBucket);
        }
        if (!player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)fullBucket))) {
            player.dropItem(new ItemStack((IItemProvider)fullBucket), false);
        }
        return emptyBuckets;
    }
    
    public boolean tryPlaceContainedLiquid(@Nullable final PlayerEntity player, final World worldIn, final BlockPos posIn, @Nullable final BlockRayTraceResult p_180616_4_) {
        if (!(this.containedBlock instanceof FlowingFluid)) {
            return false;
        }
        final BlockState blockstate = worldIn.getBlockState(posIn);
        final Material material = blockstate.getMaterial();
        final boolean flag = !material.isSolid();
        final boolean flag2 = material.isReplaceable();
        if (worldIn.isAirBlock(posIn) || flag || flag2 || (blockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer)blockstate.getBlock()).canContainFluid((IBlockReader)worldIn, posIn, blockstate, this.containedBlock))) {
            if (worldIn.dimension.doesWaterVaporize() && this.containedBlock.isIn(FluidTags.WATER)) {
                final int i = posIn.getX();
                final int j = posIn.getY();
                final int k = posIn.getZ();
                worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 2.6f + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8f);
                for (int l = 0; l < 8; ++l) {
                    worldIn.addParticle((IParticleData)ParticleTypes.LARGE_SMOKE, i + Math.random(), j + Math.random(), k + Math.random(), 0.0, 0.0, 0.0);
                }
            }
            else if (blockstate.getBlock() instanceof ILiquidContainer && this.containedBlock == Fluids.WATER) {
                if (((ILiquidContainer)blockstate.getBlock()).receiveFluid((IWorld)worldIn, posIn, blockstate, ((FlowingFluid)this.containedBlock).getStillFluidState(false))) {
                    this.playEmptySound(player, (IWorld)worldIn, posIn);
                }
            }
            else {
                if (!worldIn.isRemote && (flag || flag2) && !material.isLiquid()) {
                    worldIn.destroyBlock(posIn, true);
                }
                this.playEmptySound(player, (IWorld)worldIn, posIn);
                worldIn.setBlockState(posIn, this.containedBlock.getDefaultState().getBlockState(), 11);
            }
            return true;
        }
        return p_180616_4_ != null && this.tryPlaceContainedLiquid(player, worldIn, p_180616_4_.getPos().offset(p_180616_4_.getFace()), null);
    }
    
    protected void playEmptySound(@Nullable final PlayerEntity player, final IWorld worldIn, final BlockPos pos) {
        final SoundEvent soundevent = this.containedBlock.isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
        worldIn.playSound(player, pos, soundevent, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
    
    public ICapabilityProvider initCapabilities(final ItemStack stack, @Nullable final CompoundNBT nbt) {
        if (this.getClass() == ItemInfWaterBucket.class) {
            return (ICapabilityProvider)new FluidBucketWrapper(stack);
        }
        return super.initCapabilities(stack, nbt);
    }
}
