package lol.roxxane.roxxys_survival_core.jei.categories;

import lol.roxxane.roxxys_survival_core.blocks.RscBlocks;
import lol.roxxane.roxxys_survival_core.jei.RscJeiRecipeTypes;
import lol.roxxane.roxxys_survival_core.recipes.RscSmithingRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;

public class RscSmithingCategory extends AbstractRecipeCategory<RscSmithingRecipe> {
	public RscSmithingCategory(IGuiHelper guiHelper) {
		super(
			RscJeiRecipeTypes.SMITHING,
			Component.translatable("jei.category.roxxys_survival_core.smithing"),
			guiHelper.createDrawableItemLike(RscBlocks.SMITHING_TABLE.get()),
			108 - 18,
			28
		);
	}
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, RscSmithingRecipe recipe, IFocusGroup focuses) {
		builder.addInputSlot(1, 6).addItemStack(recipe.base.getDefaultInstance()).setStandardSlotBackground();
		builder.addInputSlot(19, 6).addItemStack(recipe.material.getDefaultInstance()).setStandardSlotBackground();
		builder.addOutputSlot(91 - 18, 6).addItemStack(recipe.result.getDefaultInstance()).setStandardSlotBackground();
	}
	@Override
	public void createRecipeExtras(IRecipeExtrasBuilder builder, RscSmithingRecipe recipe, IFocusGroup focuses) {
		builder.addRecipeArrow().setPosition(61 - 18, 6);
	}
}
