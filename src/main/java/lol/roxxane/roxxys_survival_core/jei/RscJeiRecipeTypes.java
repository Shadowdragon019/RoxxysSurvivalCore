package lol.roxxane.roxxys_survival_core.jei;

import lol.roxxane.roxxys_survival_core.Rsc;
import mezz.jei.api.recipe.RecipeType;
import snownee.kiwi.customization.block.family.BlockFamily;

public class RscJeiRecipeTypes {
	public static final RecipeType<BlockFamily> SWITCHING =
		RecipeType.create(Rsc.ID, "switching", BlockFamily.class);

}
