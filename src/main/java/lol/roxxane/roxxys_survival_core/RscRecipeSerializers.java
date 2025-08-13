package lol.roxxane.roxxys_survival_core;

import lol.roxxane.roxxys_survival_core.recipes.DisabledRecipe;
import lol.roxxane.roxxys_survival_core.recipes.SimpleRecipeSerializer;
import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class RscRecipeSerializers {
	public static SimpleRecipeSerializer<DisabledRecipe> DISABLED =
		register_simple("disabled", DisabledRecipe::new);
	@SuppressWarnings("unchecked")
	private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String key, S serializer) {
		ForgeRegistries.RECIPE_SERIALIZERS.register(Id.rsc(key), serializer);
		return (S) ForgeRegistries.RECIPE_SERIALIZERS.getValue(Id.rsc(key));
	}
	private static <T extends Recipe<?>> SimpleRecipeSerializer<T> register_simple(String key,
		Function<ResourceLocation, T> constructor
	) {
		return register(key, new SimpleRecipeSerializer<>(constructor));
	}
	public static void register() {}
}
