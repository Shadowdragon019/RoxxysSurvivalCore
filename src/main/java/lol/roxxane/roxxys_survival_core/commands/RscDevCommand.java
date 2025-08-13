package lol.roxxane.roxxys_survival_core.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lol.roxxane.roxxys_survival_core.Rsc;
import lol.roxxane.roxxys_survival_core.recipes.DisabledRecipe;
import lol.roxxane.roxxys_survival_core.utils.DamageTypeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;

public class RscDevCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher,
		CommandBuildContext build_context
	) {
		var builder = Commands.literal("rsc_dev")
			.then(Commands.literal("dump_recipes")
				.then(Commands.argument("item", ItemArgument.item(build_context))
					.executes(context -> {
						var level = Minecraft.getInstance().level;
						if (level == null) return 0;
						var recipes = level.getRecipeManager().getRecipes();
						var stack = ItemArgument.getItem(context, "item")
							.createItemStack(1, true);
						var item = stack.getItem();
						var register_access = level.registryAccess();
						var stringed_recipes = new StringBuilder();
						for (var recipe : recipes) {
							var print_recipe = false;
							if (recipe instanceof DisabledRecipe)
								continue;
							else if (recipe instanceof SmithingTransformRecipe smithing_recipe)
								print_recipe = smithing_recipe.isTemplateIngredient(stack) ||
									smithing_recipe.isBaseIngredient(stack) ||
									smithing_recipe.isAdditionIngredient(stack);
							else {
								var ingredients = recipe.getIngredients();
								print_recipe = recipe.getIngredients().stream().anyMatch(ingredient ->
									ingredient.test(stack)) && !ingredients.isEmpty();
							}
							if (print_recipe || recipe.getResultItem(register_access).is(item))
								stringed_recipes.append('"')
									.append(recipe.getId().getPath())
									.append("\", ");
						}
						if (stringed_recipes.isEmpty())
							Rsc.log("Found no recipes using " + item);
						else {
							stringed_recipes.delete(stringed_recipes.length() - 2, stringed_recipes.length() - 1);
							Rsc.log("Recipes using " + item + ":", stringed_recipes);
						}
						return 0;
					})));
		damages_types_that_bypass_cooldown(builder);
		dispatcher.register(builder);
	}

	@SuppressWarnings("DataFlowIssue")
	private static void damages_types_that_bypass_cooldown(
		LiteralArgumentBuilder<CommandSourceStack> builder
	) {
		builder.then(Commands.literal("print_damages_types_that_bypass_iframes")
			.executes(context -> {
				final var registry = DamageTypeUtil.registry(context.getSource().getLevel());
				final var string_builder = new StringBuilder();
				registry.getTag(DamageTypeTags.BYPASSES_COOLDOWN).ifPresent(damage_types_holder -> {
					for (var damage_type_holder : damage_types_holder)
						string_builder
							.append('"')
							.append(registry.getKey(damage_type_holder.get()).toString())
							.append("\", ");
				});
				if (string_builder.isEmpty())
					Rsc.log("No damage types bypass iframes");
				else {
					string_builder.delete(string_builder.length() - 2, string_builder.length() - 1);
					Rsc.log("Damage types that bypass iframes are:", string_builder);
				}
				return 0;
			}));
	}
}