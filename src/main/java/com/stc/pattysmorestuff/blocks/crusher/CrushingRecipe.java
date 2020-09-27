package com.stc.pattysmorestuff.blocks.crusher;

import net.minecraft.inventory.*;
import net.minecraft.world.*;
import net.minecraft.item.crafting.*;
import com.stc.pattysmorestuff.util.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraftforge.registries.*;
import com.google.gson.*;
import net.minecraft.network.*;

public class CrushingRecipe implements IRecipe<IInventory>
{
    private final ResourceLocation id;
    private final String group;
    private final Ingredient ingredient;
    private final ItemStack result;
    private final ItemStack secondResult;
    private final float experience;
    private final int crushTime;
    public static final IRecipeType<CrushingRecipe> CRUSHING;
    
    public CrushingRecipe(final ResourceLocation id, final String group, final Ingredient ingredient, final ItemStack result, final ItemStack secondResult, final float experience, final int crushTime) {
        this.id = id;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.secondResult = secondResult;
        this.experience = experience;
        this.crushTime = crushTime;
    }
    
    public String getGroup() {
        return this.group;
    }
    
    public NonNullList<Ingredient> getIngredients() {
        final NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }
    
    public boolean isDynamic() {
        return false;
    }
    
    public float getExperience() {
        return this.experience;
    }
    
    public int getCrushTime() {
        return this.crushTime;
    }
    
    public ItemStack getSecondRecipeOutput() {
        return this.secondResult;
    }
    
    public boolean matches(final IInventory inv, final World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }
    
    public ItemStack getCraftingResult(final IInventory inv) {
        return this.result.copy();
    }
    
    public boolean canFit(final int width, final int height) {
        return false;
    }
    
    public ItemStack getRecipeOutput() {
        return this.result;
    }
    
    public ResourceLocation getId() {
        return this.id;
    }
    
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializers.CRUSHING;
    }
    
    public ItemStack getIcon() {
        return new ItemStack((IItemProvider)BlockNames.CRUSHER);
    }
    
    public IRecipeType<?> getType() {
        return CrushingRecipe.CRUSHING;
    }
    
    static {
        CRUSHING = IRecipeType.register("pattysmorestuff:crushing");
    }
    
    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CrushingRecipe>
    {
        public static ItemStack deserializeItem(final JsonObject p_199798_0_) {
            final String s = JSONUtils.getString(p_199798_0_, "item");
            final Item item = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(s));
            if (item == null) {
                throw new JsonSyntaxException("Unknown item '" + s + "'");
            }
            if (p_199798_0_.has("data")) {
                throw new JsonParseException("Disallowed data tag found");
            }
            final int i = JSONUtils.getInt(p_199798_0_, "count", 1);
            return new ItemStack((IItemProvider)item, i);
        }
        
        public CrushingRecipe read(final ResourceLocation recipeId, final JsonObject json) {
            final String s = JSONUtils.getString(json, "group", "");
            final JsonElement jsonelement = (JsonElement)(JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
            final Ingredient ingredient = Ingredient.deserialize(jsonelement);
            final ItemStack result = deserializeItem(JSONUtils.getJsonObject(json, "result"));
            ItemStack secondResult = ItemStack.EMPTY;
            if (JSONUtils.hasField(json, "secondresult")) {
                secondResult = deserializeItem(JSONUtils.getJsonObject(json, "secondresult"));
            }
            final float f = JSONUtils.getFloat(json, "experience", 0.0f);
            final int i = JSONUtils.getInt(json, "crushtime", 200);
            return new CrushingRecipe(recipeId, s, ingredient, result, secondResult, f, i);
        }
        
        public CrushingRecipe read(final ResourceLocation recipeId, final PacketBuffer buffer) {
            final String s = buffer.readString(32767);
            final Ingredient ingredient = Ingredient.read(buffer);
            final ItemStack result = buffer.readItemStack();
            final ItemStack secondResult = buffer.readItemStack();
            final float f = buffer.readFloat();
            final int i = buffer.readVarInt();
            return new CrushingRecipe(recipeId, s, ingredient, result, secondResult, f, i);
        }
        
        public void write(final PacketBuffer buffer, final CrushingRecipe recipe) {
            buffer.writeString(recipe.group);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
            buffer.writeItemStack(recipe.secondResult);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.crushTime);
        }
    }
}
