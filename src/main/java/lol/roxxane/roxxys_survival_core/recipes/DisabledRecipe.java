package lol.roxxane.roxxys_survival_core.recipes;

import com.google.gson.JsonObject;
import lol.roxxane.roxxys_survival_core.RscRecipeSerializers;
import lol.roxxane.roxxys_survival_core.RscRecipeTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public record DisabledRecipe(ResourceLocation id) implements Recipe<Container> {
	public DisabledRecipe(ResourceLocation id, Consumer<FinishedRecipe> writer) {
		this(id);
		writer.accept(new Finished(id));
	}
	@Override
	public boolean matches(@NotNull Container $, @NotNull Level $1) {
		return false;
	}
	@Override
	public @NotNull ItemStack assemble(@NotNull Container $, @NotNull RegistryAccess $1) {
		return ItemStack.EMPTY;
	}
	@Override
	public boolean canCraftInDimensions(int $, int $1) {
		return false;
	}
	@Override
	public @NotNull ItemStack getResultItem(@NotNull RegistryAccess $) {
		return ItemStack.EMPTY;
	}
	@Override
	public @NotNull ResourceLocation getId() {
		return id;
	}
	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return RscRecipeSerializers.DISABLED;
	}
	@Override
	public @NotNull RecipeType<?> getType() {
		return RscRecipeTypes.DISABLED;
	}
	public record Finished(ResourceLocation id) implements FinishedRecipe {
		@Override
		public void serializeRecipeData(@NotNull JsonObject $) {}
		@Override
		public @NotNull ResourceLocation getId() {
			return id;
		}
		@Override
		public @NotNull RecipeSerializer<?> getType() {
			return RscRecipeSerializers.DISABLED;
		}
		@Override
		public @Nullable JsonObject serializeAdvancement() {
			return null;
		}
		@Override
		public @Nullable ResourceLocation getAdvancementId() {
			return null;
		}
	}
}

/*
import lol.roxxane.roxxys_survival_core.RscRecipeSerializers
import lol.roxxane.roxxys_survival_core.RscRecipeTypes
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.Container
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType

import java.util.function.Consumer

#Types can be inferred by the compiler (your IDE would show them, this is a minimal typing thing!)
type DisabledRecipe Recipe<Container>
	this(ResourceLocation id temp () -> FinishedRecipe writer)
		writer(Finished(id))
	matches()
		return false
	assemble()
		return ItemStack.EMPTY
	canCraftInDimensions()
		return false
	getResultItem()
		return ItemStack.EMPTY
	getId()
		return id
	getSerializer()
		return RscRecipeSerializers.DISABLED
    getType()
		return RscRecipeTypes.DISABLED
	type Finished FinishedRecipe
		this(ResourceLocation id)
		serializeRecipeData()
		getId()
			return id
		getType()
			return RscRecipeSerializers.DISABLED
		#Optional<?> is my workaround to no nulls. Once more, types are inferred by the compiler & your IDE would show these
	    serializeAdvancement()
			return Optional()
		getAdvancementId()
			return Optional()
 */