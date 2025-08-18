package lol.roxxane.roxxys_survival_core.recipes;

import lol.roxxane.roxxys_survival_core.configs.RscServerConfig;
import lol.roxxane.roxxys_survival_core.utils.ItemManipulation;
import net.minecraft.world.item.FireworkRocketItem.Shape;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lol.roxxane.roxxys_survival_core.mixins.firework_star_recipe_remove_invalid_items.FireworkStarRecipeAccessor.SHAPE_BY_ITEM;
import static lol.roxxane.roxxys_survival_core.mixins.firework_star_recipe_remove_invalid_items.FireworkStarRecipeAccessor.SHAPE_INGREDIENT;

public class FireworkStarRecipeCustomization {
	public static Ingredient shape_ingredient = Ingredient.merge(List.of(SHAPE_INGREDIENT()));
	public static final HashMap<Item, Shape> SHAPE_BY_ITEM = new HashMap<>(SHAPE_BY_ITEM());
	public static void add_item(Item item, Shape shape) {
		SHAPE_BY_ITEM.put(item, shape);
		shape_ingredient = ItemManipulation.ingredient(shape_ingredient, item);
	}
	public static void remove_item(Item item) {
		SHAPE_BY_ITEM.remove(item);
		shape_ingredient = ItemManipulation.ingredient(
			ItemManipulation.minus_items(shape_ingredient, item));
	}
	public static Ingredient get_shape_ingredient() {
		if (RscServerConfig.CUSTOMIZE_FIREWORK_STAR_RECIPE.get())
			return shape_ingredient;
		else return SHAPE_INGREDIENT();
	}
	public static Map<Item, Shape> get_shape_by_item() {
		if (RscServerConfig.CUSTOMIZE_FIREWORK_STAR_RECIPE.get())
			return SHAPE_BY_ITEM;
		else return SHAPE_BY_ITEM();
	}
}
