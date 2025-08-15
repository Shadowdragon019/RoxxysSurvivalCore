package lol.roxxane.roxxys_survival_core.jei;

import lol.roxxane.roxxys_survival_core.Rsc;
import lol.roxxane.roxxys_survival_core.recipes.JeiOutputOverride;
import lol.roxxane.roxxys_survival_core.recipes.SimpleJeiRecipe;
import lol.roxxane.roxxys_survival_core.utils.Id;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static net.minecraft.tags.ItemTags.*;
import static net.minecraft.tags.ItemTags.TERRACOTTA;
import static net.minecraft.world.item.ItemStack.EMPTY;
import static net.minecraft.world.item.Items.*;

@JeiPlugin
public class RscJeiPlugin implements IModPlugin {
	public static final ResourceLocation ID = Id.rsc(Rsc.ID);
	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return ID;
	}
	@Override
	public void registerVanillaCategoryExtensions(@NotNull IVanillaCategoryExtensionRegistration registration) {
		registration.getCraftingCategory().addCategoryExtension(CustomRecipe.class,
			recipe -> recipe instanceof JeiOutputOverride,
			recipe -> (layout_builder, grid_helper, focus_group) ->
			{
				if (((JeiOutputOverride)recipe).shapeless())
					layout_builder.setShapeless();
				grid_helper.createAndSetInputs(layout_builder,
					recipe.getIngredients().stream().map(ingredient ->
						Arrays.stream(ingredient.getItems()).toList()).toList(), 3, 3);
				grid_helper.createAndSetOutputs(layout_builder, ((JeiOutputOverride)recipe).jei_output());
			});
	}
	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registration) {
		registration.addRecipes(RecipeTypes.CRAFTING, List.of(
			new SimpleJeiRecipe(Id.rsc("carpets"), false)
				.ingredients(WOOL, WOOL)
				.output(3, WOOL_CARPETS),
			new SimpleJeiRecipe(Id.rsc("beds"), false)
				.ingredients(EMPTY, EMPTY, EMPTY)
				.ingredients(WOOL, WOOL, WOOL)
				.ingredients(PLANKS, PLANKS, PLANKS)
				.output(1, BEDS),
			new SimpleJeiRecipe(Id.rsc("banners"), false)
				.ingredients(WOOL, WOOL, WOOL, WOOL, WOOL, WOOL)
				.ingredients(EMPTY)
				.ingredients(STICK)
				.output(1, BANNERS),
			new SimpleJeiRecipe(Id.rsc("glazed_terracotta"), false)
				.ingredients(EMPTY, EMPTY, EMPTY, EMPTY)
				.ingredients(TERRACOTTA)
				.output(1, BROWN_GLAZED_TERRACOTTA, WHITE_GLAZED_TERRACOTTA, ORANGE_GLAZED_TERRACOTTA,
					MAGENTA_GLAZED_TERRACOTTA, LIGHT_BLUE_GLAZED_TERRACOTTA, YELLOW_GLAZED_TERRACOTTA,
					LIME_GLAZED_TERRACOTTA, PINK_GLAZED_TERRACOTTA, GRAY_GLAZED_TERRACOTTA,
					LIGHT_GRAY_GLAZED_TERRACOTTA, CYAN_GLAZED_TERRACOTTA, PURPLE_GLAZED_TERRACOTTA,
					BLUE_GLAZED_TERRACOTTA, GREEN_GLAZED_TERRACOTTA, RED_GLAZED_TERRACOTTA, BLACK_GLAZED_TERRACOTTA),
			new SimpleJeiRecipe(Id.rsc("terracotta"), false)
				.ingredients(EMPTY, EMPTY, EMPTY, EMPTY)
				.ingredients(Ingredient.of(BROWN_GLAZED_TERRACOTTA, WHITE_GLAZED_TERRACOTTA,
					ORANGE_GLAZED_TERRACOTTA, MAGENTA_GLAZED_TERRACOTTA, LIGHT_BLUE_GLAZED_TERRACOTTA,
					YELLOW_GLAZED_TERRACOTTA, LIME_GLAZED_TERRACOTTA, PINK_GLAZED_TERRACOTTA, GRAY_GLAZED_TERRACOTTA,
					LIGHT_GRAY_GLAZED_TERRACOTTA, CYAN_GLAZED_TERRACOTTA, PURPLE_GLAZED_TERRACOTTA,
					BLUE_GLAZED_TERRACOTTA, GREEN_GLAZED_TERRACOTTA, RED_GLAZED_TERRACOTTA, BLACK_GLAZED_TERRACOTTA))
				.output(1, TERRACOTTA)
		));
	}
}
