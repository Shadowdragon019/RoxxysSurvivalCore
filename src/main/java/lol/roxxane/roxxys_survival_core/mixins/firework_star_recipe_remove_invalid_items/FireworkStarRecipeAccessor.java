package lol.roxxane.roxxys_survival_core.mixins.firework_star_recipe_remove_invalid_items;

import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.FireworkStarRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(FireworkStarRecipe.class)
public interface FireworkStarRecipeAccessor {
	@Accessor("SHAPE_INGREDIENT")
	static Ingredient SHAPE_INGREDIENT() {
		throw new AssertionError();
	}
	@Accessor("SHAPE_INGREDIENT") @Mutable
	static void SHAPE_INGREDIENT(Ingredient ingredient) {}
	@Accessor("SHAPE_BY_ITEM")
	static Map<Item, FireworkRocketItem.Shape> SHAPE_BY_ITEM() {
		throw new AssertionError();
	}
}
