package lol.roxxane.roxxys_survival_core.recipes;

import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;

public class RscRecipeTypes {
	public static final RecipeType<RscSmithingRecipe> SMITHING = register("smithing");
	private static <T extends Recipe<?>> RecipeType<T> register(String path) {
		var type = new RecipeType<T>() {
			public String toString() {
				return path;
			}
		};
		ForgeRegistries.RECIPE_TYPES.register(Id.rsc(path), type);
		return type;
	}
	public static void register(){}
}
