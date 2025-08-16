package lol.roxxane.roxxys_survival_core.recipes;

import lol.roxxane.roxxys_survival_core.utils.Merge;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SimpleJeiRecipe extends CustomRecipe implements JeiOutputOverride {
	public final boolean shapeless;
	public final NonNullList<Ingredient> ingredients = NonNullList.create();
	public final ArrayList<ItemStack> output = new ArrayList<>();
	public SimpleJeiRecipe(ResourceLocation id, boolean shapeless) {
		super(id, CraftingBookCategory.MISC);
		this.shapeless = shapeless;
	}
	public SimpleJeiRecipe ingredients_count(int count, Object... objects) {
		ingredients.addAll(Merge.ingredients_count(count, objects));
		return this;
	}
	public SimpleJeiRecipe ingredients(Object... objects) {
		ingredients.addAll(Merge.ingredients(objects));
		return this;
	}
	public SimpleJeiRecipe output_count(int count, Object... objects) {
		output.addAll(Merge.stacks_count(count, objects));
		return this;
	}
	public SimpleJeiRecipe output(Object... objects) {
		output.addAll(Merge.stacks(objects));
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
