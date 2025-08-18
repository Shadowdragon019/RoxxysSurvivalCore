package lol.roxxane.roxxys_survival_core.mixins.firework_star_recipe_remove_invalid_items;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import lol.roxxane.roxxys_survival_core.configs.RscServerConfig;
import lol.roxxane.roxxys_survival_core.recipes.FireworkStarRecipeCustomization;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.FireworkStarRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(FireworkStarRecipe.class)
abstract class FireworkStarRecipeMixin {
	@ModifyExpressionValue(method = {
		"matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z",
		"assemble(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;"
	},
		at = @At(value = "FIELD",
			opcode = Opcodes.H_GETSTATIC,
			target = "Lnet/minecraft/world/item/crafting/FireworkStarRecipe;SHAPE_INGREDIENT:Lnet/minecraft/world/item/crafting/Ingredient;"))
	private Ingredient shape_ingredient(Ingredient original) {
		if (RscServerConfig.CUSTOMIZE_FIREWORK_STAR_RECIPE.get())
			return FireworkStarRecipeCustomization.shape_ingredient;
		else return original;
	}
	@ModifyExpressionValue(method = {
		"matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z",
		"assemble(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;"
	},
		at = @At(value = "FIELD",
			opcode = Opcodes.H_GETSTATIC,
			target = "Lnet/minecraft/world/item/crafting/FireworkStarRecipe;SHAPE_BY_ITEM:Ljava/util/Map;"))
	private Map<Item, FireworkRocketItem.Shape> shape_by_item(Map<Item, FireworkRocketItem.Shape> original) {
		if (RscServerConfig.CUSTOMIZE_FIREWORK_STAR_RECIPE.get())
			return FireworkStarRecipeCustomization.SHAPE_BY_ITEM;
		else return original;
	}
}
