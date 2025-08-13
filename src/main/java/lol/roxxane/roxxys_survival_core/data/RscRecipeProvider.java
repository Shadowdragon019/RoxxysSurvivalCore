package lol.roxxane.roxxys_survival_core.data;

import lol.roxxane.roxxys_survival_core.recipes.DisabledRecipe;
import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class RscRecipeProvider extends RecipeProvider {
	public RscRecipeProvider(PackOutput output) {
		super(output);
	}
	Consumer<FinishedRecipe> writer;
	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
		this.writer = writer;
		// Path post
		for (var entry : Map.of(
			"golden_", List.of("shovel", "pickaxe", "axe", "hoe", "sword",
				"helmet", "chestplate", "leggings", "boots"),
			"netherite_", List.of("ingot", "block", "scrap")
		).entrySet())
			for (var path : entry.getValue())
				new DisabledRecipe(Id.mc(entry.getKey() + path), writer);
		// Path pre
		for (var entry : Map.of(
			"_from_smelting", List.of("gold_nugget"),
			"_from_blasting", List.of("gold_nugget")
		).entrySet())
			for (var path : entry.getValue())
				new DisabledRecipe(Id.mc(path + entry.getKey()), writer);
		for (var path : List.of(
			"netherite_scrap_from_blasting", "netherite_ingot_from_netherite_block",
			"netherite_upgrade_smithing_template", "netherite_sword_smithing", "netherite_hoe_smithing",
			"netherite_axe_smithing", "netherite_pickaxe_smithing", "netherite_helmet_smithing",
			"netherite_chestplate_smithing", "netherite_leggings_smithing", "netherite_shovel_smithing",
			"netherite_boots_smithing"
		))
			new DisabledRecipe(Id.mc(path), writer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.LODESTONE)
			.define('S', Items.CHISELED_STONE_BRICKS)
			.define('#', Items.IRON_INGOT)
			.pattern("SSS")
			.pattern("S#S")
			.pattern("SSS")
			.unlockedBy("", new ImpossibleTrigger.TriggerInstance())
			.save(writer);
	}
}