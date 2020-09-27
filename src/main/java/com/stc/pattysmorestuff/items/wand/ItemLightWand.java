package com.stc.pattysmorestuff.items.wand;

import com.stc.pattysmorestuff.PattysMoreStuff;
import com.stc.pattysmorestuff.blocks.light.BlockIllumination;
import com.stc.pattysmorestuff.config.ConfigGeneral;
import com.stc.pattysmorestuff.init.ModBlocks;
import com.stc.pattysmorestuff.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLightWand extends Item {
    public ItemLightWand(String name, Properties properties) {
        super(properties.maxStackSize(1).maxDamage(0).setNoRepair());
        this.setRegistryName(PattysMoreStuff.MODID, name);

        if(ConfigGeneral.disableIlluminationWand.get()) {
            ModItems.ITEMS.add(this);
        }

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Adds invisible block of light that can be walked through"));

    }

    /*@Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if(!context.getWorld().isRemote) {
            Block block =context.getWorld().getBlockState(context.getPos()).getBlock();
            if(block == ModBlocks.glowair) {
                context.getWorld().isAirBlock(context.getPos());
                return ActionResultType.SUCCESS;
            }

            context.getWorld().setBlockState(context.getPos().offset(Direction.UP), ModBlocks.glowair.getDefaultState(), 3);
            context.getPlayer().getHeldItemMainhand().setDamage(0);
        }
        return ActionResultType.SUCCESS;
    }*/


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);
        RayTraceResult rayTraceResult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
        if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK)
        {
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) rayTraceResult;
            BlockPos pos = blockRayTraceResult.getPos().offset(blockRayTraceResult.getFace());

            if (!playerIn.canPlayerEdit(pos, playerIn.getAdjustedHorizontalFacing(), stack))
            {
                return new ActionResult<ItemStack>(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
            }
            BlockState state = worldIn.getBlockState(pos);
            if (state.getBlock() != ModBlocks.glowair && state.getMaterial().isReplaceable())
            {
                if (!playerIn.isCreative())
                {
                    stack.setDamage(stack.getDamage() + 0);
                }
                {
                    FluidState ifluidstate = (FluidState) worldIn.getFluidState(pos);
                    worldIn.setBlockState(pos, ModBlocks.glowair.getDefaultState().with(BlockIllumination.WATERLOGGED, ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8), 11);
                }
            }
        }

        worldIn.playSound((PlayerEntity) null, playerIn.chunkCoordX, playerIn.chunkCoordY, playerIn.chunkCoordZ, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.NEUTRAL, 0.5F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
        playerIn.swingArm(handIn);
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {

        target.addPotionEffect(new EffectInstance(Effects.GLOWING, 200));
        if (attacker instanceof PlayerEntity && !((PlayerEntity) attacker).isCreative())
        {
            stack.setDamage(stack.getDamage() + 0);
        }
        return true;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return stack.getItem() == Items.GLOWSTONE_DUST;
    }

}
