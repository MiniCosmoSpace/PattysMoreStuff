package com.stc.pattysmorestuff.tileentity.abstracts;

import net.minecraftforge.common.util.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.items.wrapper.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.tags.*;
import net.minecraft.nbt.*;
import net.minecraft.inventory.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import com.stc.pattysmorestuff.blocks.furnaces.abstracts.*;
import net.minecraft.state.*;
import javax.annotation.*;
import net.minecraftforge.event.*;
import java.util.function.*;
import net.minecraft.entity.player.*;
import com.google.common.collect.*;
import java.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraft.item.crafting.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.items.*;

public abstract class AbstractGoldFurnaceTileEntity extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity
{
    private static final int[] SLOTS_UP;
    private static final int[] SLOTS_DOWN;
    private static final int[] SLOTS_HORIZONTAL;
    private NonNullList<ItemStack> items;
    private int burnTime;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal;
    private int cookSpeed;
    private int extraOutput;
    protected final IIntArray furnaceData;
    private final Map<ResourceLocation, Integer> field_214022_n;
    protected final IRecipeType<? extends AbstractCookingRecipe> recipeType;
    LazyOptional<? extends IItemHandler>[] handlers;
    
    public AbstractGoldFurnaceTileEntity(final TileEntityType<?> tileTypeIn, final IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn, final int cookSpeed, final int extraOutput) {
        super((TileEntityType)tileTypeIn);
        this.items = (NonNullList<ItemStack>)NonNullList.withSize(3, ItemStack.EMPTY);
        this.furnaceData = (IIntArray)new IIntArray() {
            public int get(final int index) {
                switch (index) {
                    case 0: {
                        return AbstractGoldFurnaceTileEntity.this.burnTime;
                    }
                    case 1: {
                        return AbstractGoldFurnaceTileEntity.this.recipesUsed;
                    }
                    case 2: {
                        return AbstractGoldFurnaceTileEntity.this.cookTime;
                    }
                    case 3: {
                        return AbstractGoldFurnaceTileEntity.this.cookTimeTotal;
                    }
                    default: {
                        return 0;
                    }
                }
            }
            
            public void set(final int index, final int value) {
                switch (index) {
                    case 0: {
                        AbstractGoldFurnaceTileEntity.this.burnTime = value;
                        break;
                    }
                    case 1: {
                        AbstractGoldFurnaceTileEntity.this.recipesUsed = value;
                        break;
                    }
                    case 2: {
                        AbstractGoldFurnaceTileEntity.this.cookTime = value;
                        break;
                    }
                    case 3: {
                        AbstractGoldFurnaceTileEntity.this.cookTimeTotal = value;
                        break;
                    }
                }
            }
            
            public int size() {
                return 4;
            }
        };
        this.field_214022_n = Maps.newHashMap();
        this.handlers = (LazyOptional<? extends IItemHandler>[])SidedInvWrapper.create((ISidedInventory)this, new Direction[] { Direction.UP, Direction.DOWN, Direction.NORTH });
        this.recipeType = recipeTypeIn;
        this.cookSpeed = cookSpeed;
        this.extraOutput = extraOutput;
    }
    
    public static Map<Item, Integer> getBurnTimes() {
        final Map<Item, Integer> map = Maps.newLinkedHashMap();
        addItemBurnTime(map, (IItemProvider)Items.LAVA_BUCKET, 20000);
        addItemBurnTime(map, (IItemProvider)Blocks.COAL_BLOCK, 16000);
        addItemBurnTime(map, (IItemProvider)Items.BLAZE_ROD, 2400);
        addItemBurnTime(map, (IItemProvider)Items.COAL, 1600);
        addItemBurnTime(map, (IItemProvider)Items.CHARCOAL, 1600);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.LOGS, 300);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.PLANKS, 300);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.WOODEN_STAIRS, 300);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.WOODEN_SLABS, 150);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.WOODEN_TRAPDOORS, 300);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.WOODEN_PRESSURE_PLATES, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.OAK_FENCE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.BIRCH_FENCE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.SPRUCE_FENCE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.JUNGLE_FENCE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.DARK_OAK_FENCE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.ACACIA_FENCE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.OAK_FENCE_GATE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.BIRCH_FENCE_GATE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.SPRUCE_FENCE_GATE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.JUNGLE_FENCE_GATE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.DARK_OAK_FENCE_GATE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.ACACIA_FENCE_GATE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.NOTE_BLOCK, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.BOOKSHELF, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.LECTERN, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.JUKEBOX, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.CHEST, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.TRAPPED_CHEST, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.CRAFTING_TABLE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.DAYLIGHT_DETECTOR, 300);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.BANNERS, 300);
        addItemBurnTime(map, (IItemProvider)Items.BOW, 300);
        addItemBurnTime(map, (IItemProvider)Items.FISHING_ROD, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.LADDER, 300);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.SIGNS, 200);
        addItemBurnTime(map, (IItemProvider)Items.WOODEN_SHOVEL, 200);
        addItemBurnTime(map, (IItemProvider)Items.WOODEN_SWORD, 200);
        addItemBurnTime(map, (IItemProvider)Items.WOODEN_HOE, 200);
        addItemBurnTime(map, (IItemProvider)Items.WOODEN_AXE, 200);
        addItemBurnTime(map, (IItemProvider)Items.WOODEN_PICKAXE, 200);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.WOODEN_DOORS, 200);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.BOATS, 200);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.WOOL, 100);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.WOODEN_BUTTONS, 100);
        addItemBurnTime(map, (IItemProvider)Items.STICK, 100);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.SAPLINGS, 100);
        addItemBurnTime(map, (IItemProvider)Items.BOWL, 100);
        addItemTagBurnTime(map, (Tag<Item>)ItemTags.CARPETS, 67);
        addItemBurnTime(map, (IItemProvider)Blocks.DRIED_KELP_BLOCK, 4001);
        addItemBurnTime(map, (IItemProvider)Items.CROSSBOW, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.BAMBOO, 50);
        addItemBurnTime(map, (IItemProvider)Blocks.DEAD_BUSH, 100);
        addItemBurnTime(map, (IItemProvider)Blocks.SCAFFOLDING, 50);
        addItemBurnTime(map, (IItemProvider)Blocks.LOOM, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.BARREL, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.CARTOGRAPHY_TABLE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.FLETCHING_TABLE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.SMITHING_TABLE, 300);
        addItemBurnTime(map, (IItemProvider)Blocks.COMPOSTER, 300);
        return map;
    }
    
    private static void addItemTagBurnTime(final Map<Item, Integer> map, final Tag<Item> itemTag, final int p_213992_2_) {
        for (final Item item : itemTag.getAllElements()) {
            map.put(item, p_213992_2_);
        }
    }
    
    private static void addItemBurnTime(final Map<Item, Integer> map, final IItemProvider itemProvider, final int burnTimeIn) {
        map.put(itemProvider.asItem(), burnTimeIn);
    }
    
    private boolean isBurning() {
        return this.burnTime > 0;
    }
    
    public void read(final CompoundNBT compound) {
        super.read(compound);
        ItemStackHelper.loadAllItems(compound, (NonNullList)(this.items = (NonNullList<ItemStack>)NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY)));
        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.recipesUsed = this.getBurnTime((ItemStack)this.items.get(1));
        for (int i = compound.getShort("RecipesUsedSize"), j = 0; j < i; ++j) {
            final ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
            final int k = compound.getInt("RecipeAmount" + j);
            this.field_214022_n.put(resourcelocation, k);
        }
    }
    
    public CompoundNBT write(final CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        ItemStackHelper.saveAllItems(compound, (NonNullList)this.items);
        compound.putShort("RecipesUsedSize", (short)this.field_214022_n.size());
        int i = 0;
        for (final Map.Entry<ResourceLocation, Integer> entry : this.field_214022_n.entrySet()) {
            compound.putString("RecipeLocation" + i, entry.getKey().toString());
            compound.putInt("RecipeAmount" + i, (int)entry.getValue());
            ++i;
        }
        return compound;
    }
    
    public void tick() {
        final boolean flag = this.isBurning();
        boolean flag2 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }
        if (!this.world.isRemote) {
            final ItemStack itemstack = (ItemStack)this.items.get(1);
            if (this.isBurning() || (!itemstack.isEmpty() && !((ItemStack)this.items.get(0)).isEmpty())) {
                final IRecipe<?> irecipe = (IRecipe<?>)this.world.getRecipeManager().getRecipe((IRecipeType)this.recipeType, (IInventory)this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(irecipe)) {
                    this.burnTime = this.getBurnTime(itemstack);
                    this.recipesUsed = this.burnTime;
                    if (this.isBurning()) {
                        flag2 = true;
                        if (itemstack.hasContainerItem()) {
                            this.items.set(1, itemstack.getContainerItem());
                        }
                        else if (!itemstack.isEmpty()) {
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                this.items.set(1, itemstack.getContainerItem());
                            }
                        }
                    }
                }
                if (this.isBurning() && this.canSmelt(irecipe)) {
                    this.cookTime += this.cookSpeed;
                    if (this.cookTime >= this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.func_214005_h();
                        this.func_214007_c(irecipe);
                        flag2 = true;
                    }
                }
                else {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
            }
            if (flag != this.isBurning()) {
                flag2 = true;
                this.world.setBlockState(this.pos, (BlockState)this.world.getBlockState(this.pos).with((IProperty)AbstractGoldFurnaceBlock.LIT, (Comparable)this.isBurning()), 3);
            }
        }
        if (flag2) {
            this.markDirty();
        }
    }
    
    protected boolean canSmelt(@Nullable final IRecipe<?> recipeIn) {
        if (((ItemStack)this.items.get(0)).isEmpty() || recipeIn == null) {
            return false;
        }
        final ItemStack itemstack = recipeIn.getRecipeOutput();
        if (itemstack.isEmpty()) {
            return false;
        }
        final ItemStack itemstack2 = (ItemStack)this.items.get(2);
        return itemstack2.isEmpty() || (itemstack2.isItemEqual(itemstack) && itemstack2.getCount() + itemstack.getCount() <= 64);
    }
    
    private void func_214007_c(@Nullable final IRecipe<?> p_214007_1_) {
        if (p_214007_1_ != null && this.canSmelt(p_214007_1_)) {
            final ItemStack itemstack = (ItemStack)this.items.get(0);
            final ItemStack itemstack2 = p_214007_1_.getRecipeOutput();
            final ItemStack itemstack3 = (ItemStack)this.items.get(2);
            if (itemstack3.isEmpty()) {
                itemstack2.setCount(this.extraOutput);
                this.items.set(2, itemstack2.copy());
            }
            else if (itemstack3.getItem() == itemstack2.getItem()) {
                itemstack3.grow(this.extraOutput);
            }
            if (!this.world.isRemote) {
                this.setRecipeUsed(p_214007_1_);
            }
            if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !((ItemStack)this.items.get(1)).isEmpty() && ((ItemStack)this.items.get(1)).getItem() == Items.BUCKET) {
                this.items.set(1, new ItemStack((IItemProvider)Items.WATER_BUCKET));
            }
            itemstack.shrink(1);
        }
    }
    
    protected int getBurnTime(final ItemStack p_213997_1_) {
        if (p_213997_1_.isEmpty()) {
            return 0;
        }
        final Item item = p_213997_1_.getItem();
        final int ret = p_213997_1_.getBurnTime();
        return ForgeEventFactory.getItemBurnTime(p_213997_1_, (ret == -1) ? ((int)getBurnTimes().getOrDefault(item, 0)) : ret);
    }
    
    protected int func_214005_h() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, (IInventory)this, this.world).map(AbstractCookingRecipe::getCookTime).orElse(200);
    }
    
    public static boolean isFuel(final ItemStack p_213991_0_) {
        final int ret = p_213991_0_.getBurnTime();
        return ForgeEventFactory.getItemBurnTime(p_213991_0_, (ret == -1) ? ((int)getBurnTimes().getOrDefault(p_213991_0_.getItem(), 0)) : ret) > 0;
    }
    
    public int[] getSlotsForFace(final Direction side) {
        if (side == Direction.DOWN) {
            return AbstractGoldFurnaceTileEntity.SLOTS_DOWN;
        }
        return (side == Direction.UP) ? AbstractGoldFurnaceTileEntity.SLOTS_UP : AbstractGoldFurnaceTileEntity.SLOTS_HORIZONTAL;
    }
    
    public boolean canInsertItem(final int index, final ItemStack itemStackIn, @Nullable final Direction direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }
    
    public boolean canExtractItem(final int index, final ItemStack stack, final Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            final Item item = stack.getItem();
            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }
        return true;
    }
    
    public int getSizeInventory() {
        return this.items.size();
    }
    
    public boolean isEmpty() {
        for (final ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public ItemStack getStackInSlot(final int index) {
        return (ItemStack)this.items.get(index);
    }
    
    public ItemStack decrStackSize(final int index, final int count) {
        return ItemStackHelper.getAndSplit((List)this.items, index, count);
    }
    
    public ItemStack removeStackFromSlot(final int index) {
        return ItemStackHelper.getAndRemove((List)this.items, index);
    }
    
    public void setInventorySlotContents(final int index, final ItemStack stack) {
        final ItemStack itemstack = (ItemStack)this.items.get(index);
        final boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        if (index == 0 && !flag) {
            this.cookTimeTotal = this.func_214005_h();
            this.cookTime = 0;
            this.markDirty();
        }
    }
    
    public boolean isUsableByPlayer(final PlayerEntity player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
    }
    
    public boolean isItemValidForSlot(final int index, final ItemStack stack) {
        if (index == 2) {
            return false;
        }
        if (index != 1) {
            return true;
        }
        final ItemStack itemstack = (ItemStack)this.items.get(1);
        return isFuel(stack) || (stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET);
    }
    
    public void clear() {
        this.items.clear();
    }
    
    public void setRecipeUsed(@Nullable final IRecipe<?> recipe) {
        if (recipe != null) {
            this.field_214022_n.compute(recipe.getId(), (p_214004_0_, p_214004_1_) -> 1 + ((p_214004_1_ == null) ? 0 : p_214004_1_));
        }
    }
    
    @Nullable
    public IRecipe<?> getRecipeUsed() {
        return null;
    }
    
    public void onCrafting(final PlayerEntity player) {
    }
    
    public void func_213995_d(final PlayerEntity p_213995_1_) {
        final List<IRecipe<?>> list = Lists.newArrayList();
        for (final Map.Entry<ResourceLocation, Integer> entry : this.field_214022_n.entrySet()) {
            final List<IRecipe> list2 = null;
            final Map.Entry<ResourceLocation, Integer> entry2 = null;
            p_213995_1_.world.getRecipeManager().getRecipe((ResourceLocation)entry.getKey()).ifPresent(p_213993_3_ -> {
                list2.add(p_213993_3_);
                func_214003_a(p_213995_1_, entry2.getValue(), ((AbstractCookingRecipe)p_213993_3_).getExperience());
                return;
            });
        }
        p_213995_1_.unlockRecipes((Collection)list);
        this.field_214022_n.clear();
    }
    
    private static void func_214003_a(final PlayerEntity p_214003_0_, int p_214003_1_, final float p_214003_2_) {
        if (p_214003_2_ == 0.0f) {
            p_214003_1_ = 0;
        }
        else if (p_214003_2_ < 1.0f) {
            int i = MathHelper.floor(p_214003_1_ * p_214003_2_);
            if (i < MathHelper.ceil(p_214003_1_ * p_214003_2_) && Math.random() < p_214003_1_ * p_214003_2_ - i) {
                ++i;
            }
            p_214003_1_ = i;
        }
        while (p_214003_1_ > 0) {
            final int j = ExperienceOrbEntity.getXPSplit(p_214003_1_);
            p_214003_1_ -= j;
            p_214003_0_.world.addEntity((Entity)new ExperienceOrbEntity(p_214003_0_.world, p_214003_0_.posX, p_214003_0_.posY + 0.5, p_214003_0_.posZ + 0.5, j));
        }
    }
    
    public void fillStackedContents(final RecipeItemHelper helper) {
        for (final ItemStack itemstack : this.items) {
            helper.accountStack(itemstack);
        }
    }
    
    public <T> LazyOptional<T> getCapability(final Capability<T> capability, @Nullable final Direction facing) {
        if (this.removed || facing == null || capability != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (LazyOptional<T>)super.getCapability((Capability)capability, facing);
        }
        if (facing == Direction.UP) {
            return (LazyOptional<T>)this.handlers[0].cast();
        }
        if (facing == Direction.DOWN) {
            return (LazyOptional<T>)this.handlers[1].cast();
        }
        return (LazyOptional<T>)this.handlers[2].cast();
    }
    
    public void remove() {
        super.remove();
        for (int x = 0; x < this.handlers.length; ++x) {
            this.handlers[x].invalidate();
        }
    }
    
    static {
        SLOTS_UP = new int[] { 0 };
        SLOTS_DOWN = new int[] { 2, 1 };
        SLOTS_HORIZONTAL = new int[] { 1 };
    }
}
