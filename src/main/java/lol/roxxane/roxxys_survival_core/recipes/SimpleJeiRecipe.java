package lol.roxxane.roxxys_survival_core.recipes;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleJeiRecipe extends CustomRecipe implements JeiOutputOverride {
	public final boolean shapeless;
	public final NonNullList<Ingredient> ingredients = NonNullList.create();
	public final ArrayList<ItemStack> output = new ArrayList<>();
	public SimpleJeiRecipe(ResourceLocation id, boolean shapeless) {
		super(id, CraftingBookCategory.MISC);
		this.shapeless = shapeless;
	}
	public SimpleJeiRecipe ingredients(Ingredient... ingredients) {
		this.ingredients.addAll(Arrays.stream(ingredients).toList());
		return this;
	}
	public SimpleJeiRecipe ingredients(ItemStack... stacks) {
		ingredients.addAll(Arrays.stream(stacks).map(Ingredient::of).toList());
		return this;
	}
	public SimpleJeiRecipe ingredients(ItemLike... items) {
		ingredients.addAll(Arrays.stream(items).map(Ingredient::of).toList());
		return this;
	}
	@SafeVarargs
	public final SimpleJeiRecipe ingredients(TagKey<Item>... tags) {
		ingredients.addAll(Arrays.stream(tags).map(Ingredient::of).toList());
		return this;
	}
	public SimpleJeiRecipe output(ItemStack... stacks) {
		output.addAll(Arrays.stream(stacks).toList());
		return this;
	}
	public SimpleJeiRecipe output(int count, ItemLike... items) {
		output.addAll(Arrays.stream(items)
			.map(item -> new ItemStack(item, count)).toList());
		return this;
	}
	@SuppressWarnings("DataFlowIssue")
	@SafeVarargs
	public final SimpleJeiRecipe output(int count, TagKey<Item>... tags) {
		// This could be made so more readable but I like it hehehe
		output.addAll(Arrays.stream(tags)
			.map(tag -> ForgeRegistries.ITEMS.tags().getTag(tag).stream().toList())
			.collect(ArrayList::new, (result_items, tag_items) ->
				result_items.addAll(tag_items.stream().map(item -> new ItemStack(item, count)).toList()),
				ArrayList::addAll));
		return this;
	}
	@Override
	public List<ItemStack> jei_output() {
		return output;
	}
	@Override
	public boolean shapeless() {
		return shapeless;
	}
	@Override
	public @NotNull NonNullList<Ingredient> getIngredients() {
		return ingredients;
	}
	@Override
	public boolean matches(@NotNull CraftingContainer $, @NotNull Level $1) {
		throw new NotImplementedException();
	}
	@Override
	public @NotNull ItemStack assemble(@NotNull CraftingContainer $, @NotNull RegistryAccess $1) {
		throw new NotImplementedException();
	}
	@Override
	public boolean canCraftInDimensions(int $, int $1) {
		throw new NotImplementedException();
	}
	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		throw new NotImplementedException();
	}
}
