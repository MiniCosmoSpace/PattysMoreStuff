package com.stc.pattysmorestuff.items;

import com.stc.pattysmorestuff.config.*;
import com.stc.pattysmorestuff.init.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import com.stc.pattysmorestuff.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.client.util.*;
import net.minecraft.util.text.*;

public class ItemMagnet extends Item
{
    int range;
    
    public ItemMagnet(final String name, final Item.Properties properties) {
        super(properties);
        this.setRegistryName("pattysmorestuff", name);
        if (ConfigGeneral.disableMagnet.get()) {
            ModItems.ITEMS.add(this);
        }
    }
    
    public boolean hasEffect(final ItemStack p_77636_1_) {
        return EnableUtil.isEnabled(p_77636_1_);
    }
    
    public void inventoryTick(final ItemStack stack, final World world, final Entity entity, final int itemSlot, final boolean isSelected) {
        if (entity instanceof PlayerEntity && !world.isRemote && EnableUtil.isEnabled(stack)) {
            final PlayerEntity player = (PlayerEntity)entity;
            final boolean init = MagnetRange.getCurrentlySet(stack);
            if (!init) {
                this.range = 0;
            }
            else {
                this.range = MagnetRange.getCurrentRange(stack);
            }
            final double x = player.posX;
            final double y = player.posY + 1.5;
            final double z = player.posZ;
            final List<ItemEntity> items = (List<ItemEntity>)entity.world.getEntitiesWithinAABB((Class)ItemEntity.class, new AxisAlignedBB(x - this.range, y - this.range, z - this.range, x + this.range, y + this.range, z + this.range));
            for (final ItemEntity e : items) {
                if (!player.isSneaking() && !e.getPersistentData().getBoolean("PreventRemoteMovement")) {
                    final boolean isPulling = true;
                    final double factor = 0.02;
                    e.addVelocity((x - e.posX) * factor, (y - e.posY) * factor, (z - e.posZ) * factor);
                }
            }
            if (items.isEmpty()) {}
            final List<ExperienceOrbEntity> xp = (List<ExperienceOrbEntity>)entity.world.getEntitiesWithinAABB((Class)ExperienceOrbEntity.class, new AxisAlignedBB(x - this.range, y - this.range, z - this.range, x + this.range, y + this.range, z + this.range));
            for (final ExperienceOrbEntity orb : xp) {
                if (!player.isSneaking()) {
                    final boolean isPulling = true;
                    final double factor2 = 0.02;
                    orb.addVelocity((x - orb.posX) * factor2, (y - orb.posY) * factor2, (z - orb.posZ) * factor2);
                    player.onItemPickup((Entity)orb, 1);
                    player.giveExperiencePoints(orb.xpValue);
                    orb.remove();
                }
            }
            if (items.isEmpty()) {}
        }
    }
    
    public ActionResult<ItemStack> onItemRightClick(final World world, final PlayerEntity player, final Hand hand) {
        final ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote && !player.isSneaking()) {
            EnableUtil.changeEnabled(player, hand);
            player.sendMessage((ITextComponent)new StringTextComponent("Attraction ability active: " + EnableUtil.isEnabled(stack)));
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.SUCCESS, (Object)player.getHeldItem(hand));
        }
        if (!world.isRemote && player.isSneaking()) {
            MagnetRange.setCurrentRange(stack, 8);
        }
        return (ActionResult<ItemStack>)super.onItemRightClick(world, player, hand);
    }
    
    public void addInformation(final ItemStack stack, final World world, final List<ITextComponent> list, final ITooltipFlag flag) {
        super.addInformation(stack, world, (List)list, flag);
        list.add((ITextComponent)new StringTextComponent(TextFormatting.BLUE + "Draws dropped items from 8 blocks away toward the player"));
        list.add((ITextComponent)new StringTextComponent(TextFormatting.RED + "Attraction ability active: " + EnableUtil.isEnabled(stack)));
        list.add((ITextComponent)new StringTextComponent(TextFormatting.GOLD + "Works while in player inventory"));
        list.add((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Right-click to toggle on/off"));
    }
}
