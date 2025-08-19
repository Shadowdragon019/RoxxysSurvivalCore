package lol.roxxane.roxxys_survival_core.jei;

import lol.roxxane.roxxys_survival_core.Rsc;
import lol.roxxane.roxxys_survival_core.recipes.RscSmithingRecipe;
import mezz.jei.api.recipe.RecipeType;
import snownee.kiwi.customization.block.family.BlockFamily;

public class RscJeiRecipeTypes {
	public static final RecipeType<BlockFamily> SWITCHING =
		RecipeType.create(Rsc.ID, "switching", BlockFamily.class);
	public static final RecipeType<RscSmithingRecipe> SMITHING =
		RecipeType.create(Rsc.ID, "smithing", RscSmithingRecipe.class);

}
