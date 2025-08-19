package lol.roxxane.roxxys_survival_core.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class RscRecipeProvider extends RecipeProvider {
	public RscRecipeProvider(PackOutput output) {
		super(output);
	}
	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> writer) {
		/*
		new RscSmithingRecipe(Id.rsc("smithing/test"),
			WOODEN_AXE, RED_DYE, STONE_AXE).save(writer);
		 */
	}
}
