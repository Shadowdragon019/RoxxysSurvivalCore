package lol.roxxane.roxxys_survival_core.jei;

import lol.roxxane.roxxys_survival_core.Rsc;
import lol.roxxane.roxxys_survival_core.blocks.RscBlocks;
import lol.roxxane.roxxys_survival_core.configs.RscClientConfig;
import lol.roxxane.roxxys_survival_core.jei.categories.RscSmithingCategory;
import lol.roxxane.roxxys_survival_core.jei.categories.SwitchingCategory;
import lol.roxxane.roxxys_survival_core.recipes.JeiOutputOverride;
import lol.roxxane.roxxys_survival_core.recipes.RscRecipeTypes;
import lol.roxxane.roxxys_survival_core.recipes.SimpleJeiRecipe;
import lol.roxxane.roxxys_survival_core.utils.Id;
import lol.roxxane.roxxys_survival_core.utils.ItemManipulation;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import org.jetbrains.annotations.NotNull;
import snownee.kiwi.customization.block.family.BlockFamilies;
import snownee.kiwi.util.KHolder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static lol.roxxane.roxxys_survival_core.recipes.FireworkStarRecipeCustomization.get_shape_ingredient;
import static lol.roxxane.roxxys_survival_core.utils.ItemManipulation.copy_stacks;
import static net.minecraft.network.chat.Component.translatable;
import static net.minecraft.tags.ItemTags.*;
import static net.minecraft.tags.ItemTags.TERRACOTTA;
import static net.minecraft.world.item.ItemStack.EMPTY;
import static net.minecraft.world.item.Items.*;
import static net.minecraftforge.common.Tags.Items.*;

@JeiPlugin
public class RscJeiPlugin implements IModPlugin {
	public static final Function<ItemStack, ItemStack> optional = stack ->
		stack.setHoverName(Component.translatable("jei.recipe.roxxys_survival_core.optional",
			stack.getHoverName()));
	public static final ResourceLocation ID = Id.rsc(Rsc.ID);
	public static final ResourceLocation SWITCHING_TEXTURE_ID = Id.rsc("textures/jei/category/switching.png");
	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return ID;
	}
	@Override
	public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
		var jei_helpers = registration.getJeiHelpers();
		var gui_helper = jei_helpers.getGuiHelper();
		registration.addRecipeCategories(new SwitchingCategory(gui_helper));
		registration.addRecipeCategories(new RscSmithingCategory(gui_helper));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(RscBlocks.SMITHING_TABLE.get(), RscJeiRecipeTypes.SMITHING);
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
		var glazed_terracottas = List.of(BROWN_GLAZED_TERRACOTTA, WHITE_GLAZED_TERRACOTTA,
			ORANGE_GLAZED_TERRACOTTA, MAGENTA_GLAZED_TERRACOTTA, LIGHT_BLUE_GLAZED_TERRACOTTA,
			YELLOW_GLAZED_TERRACOTTA, LIME_GLAZED_TERRACOTTA, PINK_GLAZED_TERRACOTTA, GRAY_GLAZED_TERRACOTTA,
			LIGHT_GRAY_GLAZED_TERRACOTTA, CYAN_GLAZED_TERRACOTTA, PURPLE_GLAZED_TERRACOTTA, BLUE_GLAZED_TERRACOTTA,
			GREEN_GLAZED_TERRACOTTA, RED_GLAZED_TERRACOTTA, BLACK_GLAZED_TERRACOTTA);
		var boat_planks = ItemManipulation.minus_stacks(PLANKS,
			List.of(WARPED_PLANKS, CRIMSON_PLANKS));
		var stained_glass_pane_glass = List.of(Items.GLASS, STAINED_GLASS);
		registration.addRecipes(RecipeTypes.CRAFTING, List.of(
			new SimpleJeiRecipe(Id.rsc("carpets"), false)
				.ingredients(WOOL, WOOL)
				.output_count(3, WOOL_CARPETS),
			new SimpleJeiRecipe(Id.rsc("beds"), false)
				.ingredients(EMPTY, EMPTY, EMPTY, WOOL, WOOL, WOOL, PLANKS, PLANKS, PLANKS)
				.output(BEDS),
			new SimpleJeiRecipe(Id.rsc("banners"), false)
				.ingredients(WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, EMPTY, PLANKS)
				.output(BANNERS),
			new SimpleJeiRecipe(Id.rsc("glazed_terracotta"), false)
				.ingredients(EMPTY, EMPTY, EMPTY, EMPTY, TERRACOTTA)
				.output(glazed_terracottas),
			new SimpleJeiRecipe(Id.rsc("terracotta"), false)
				.ingredients(EMPTY, EMPTY, EMPTY, EMPTY, glazed_terracottas)
				.output(TERRACOTTA),
			new SimpleJeiRecipe(Id.rsc("stained_glass"), false)
				.ingredients(EMPTY, EMPTY, EMPTY,
					stained_glass_pane_glass, stained_glass_pane_glass, stained_glass_pane_glass,
					stained_glass_pane_glass, stained_glass_pane_glass, stained_glass_pane_glass)
				.output_count(16, Items.GLASS_PANE, STAINED_GLASS_PANES),
			new SimpleJeiRecipe(Id.rsc("boats"), false)
				.ingredients(EMPTY, EMPTY, EMPTY,
					boat_planks, EMPTY, boat_planks,
					boat_planks, boat_planks, boat_planks)
				.output(ItemManipulation.minus_stacks(BOATS, CHEST_BOATS)),
			new SimpleJeiRecipe(Id.rsc("chest_boats"), false)
				.ingredients(EMPTY, EMPTY, EMPTY,
					boat_planks, CHESTS_WOODEN, boat_planks,
					boat_planks, boat_planks, boat_planks)
				.output(CHEST_BOATS),
			new SimpleJeiRecipe(Id.rsc("planks"), false)
				.ingredients(EMPTY, EMPTY, EMPTY, EMPTY, LOGS)
				.output(PLANKS),
			new SimpleJeiRecipe(Id.rsc("mossy_stone"), true)
				.ingredients(List.of(Items.COBBLESTONE, COBBLESTONE_STAIRS, COBBLESTONE_SLAB, COBBLESTONE_WALL,
						Items.STONE_BRICKS, STONE_BRICK_STAIRS, STONE_BRICK_SLAB, STONE_BRICK_WALL),
					List.of(VINE, MOSS_BLOCK))
				.output(MOSSY_COBBLESTONE, MOSSY_COBBLESTONE_STAIRS, MOSSY_COBBLESTONE_SLAB,
					MOSSY_COBBLESTONE_WALL, MOSSY_STONE_BRICKS, MOSSY_STONE_BRICK_STAIRS, MOSSY_STONE_BRICK_SLAB,
					MOSSY_STONE_BRICK_WALL),
			new SimpleJeiRecipe(Id.rsc("minecarts"), false)
				.ingredients(List.of(FURNACE, CHESTS_WOODEN, HOPPER, TNT), MINECART)
				.output(FURNACE_MINECART, CHEST_MINECART, HOPPER_MINECART, TNT_MINECART)
		));
		var all_switch_families = BlockFamilies.all().stream()
			.filter(holder ->
				holder.value().switchAttrs().enabled() &&
					holder.value().switchAttrs().cascading())
			.toList();
		/*if (all_switch_families.isEmpty())
			Rsc.log("Found 0 KSwitch block families");
		else {
			Rsc.log("Found " + all_switch_families.size() + " KSwitch block families:");
			for (var holder : BlockFamilies.all()) {
				var id = Id.stringify(holder.key());
				var family = holder.value();
				var items = new StringBuilder();
				for (var item : family.itemHolders())
					items.append(Id.stringify(item.key().location())).append(", ");
				if (!items.isEmpty())
					items.delete(items.length() - 2, items.length() - 1);
				Rsc.log(id + " with " + family.itemHolders().size() + " items " + items);
			}
		}*/
		registration.addRecipes(RscJeiRecipeTypes.SWITCHING,
			all_switch_families.stream().map(KHolder::value).toList());
		assert Minecraft.getInstance().level != null;
		registration.addRecipes(RscJeiRecipeTypes.SMITHING,
			Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(RscRecipeTypes.SMITHING));
		if (RscClientConfig.ADD_FIREWORK_STAR_RECIPES_TO_JEI.get()) {
			/*for (var diamond : List.of(AIR, DIAMOND)) {
				for (var glowstone : List.of(AIR, GLOWSTONE)) {
					for (var shape_item : List.of(get_shape_ingredient(), Ingredient.EMPTY)) {
						for (int dye_count = 0; dye_count < 8; dye_count++) {
							var ingredients = NonNullList.<Ingredient>create();
							ingredients.add(Ingredient.of(Items.GUNPOWDER));
							var path = new StringBuilder("firework_star");
							if (!shape_item.isEmpty()) {
								ingredients.add(shape_item);
								path.append("_shape");
							}
							if (diamond != AIR) {
								ingredients.add(Ingredient.of(diamond));
								path.append("_diamond");
							}
							if (glowstone != AIR) {
								ingredients.add(Ingredient.of(glowstone));
								path.append("_glowstone");
							}
							ingredients.add(Ingredient.of(DYES));
							if (ingredients.size() + dye_count <= 9) {
								for (int i = 0; i < dye_count; i++) {
									ingredients.add(Ingredient.of(DYES));
								}
								path.append("_dye_").append(dye_count + 1);
								registration.addRecipes(RecipeTypes.CRAFTING, List.of(
									new SimpleJeiRecipe(Id.rsc(path.toString()), true)
										.ingredients_list(ingredients)
										.result(FIREWORK_STAR)
								));
							}
						}
					}
				}
			}*/
			var dyes_1_8 = copy_stacks(stack ->
				stack.setHoverName(translatable("jei.recipe.roxxys_survival_core.dyes")), DYES);
			var firework_star_1_8 =copy_stacks(stack ->
					stack.setHoverName(translatable("jei.recipe.roxxys_survival_core.firework_stars")),
				FIREWORK_STAR);
			var gunpowder_1_3 = copy_stacks(stack ->
					stack.setHoverName(translatable("jei.recipe.roxxys_survival_core.gunpowder")),
				Items.GUNPOWDER);
			registration.addRecipes(RecipeTypes.CRAFTING, List.of(
				new SimpleJeiRecipe(Id.rsc("firework_star"), true)
					.ingredients(Items.GUNPOWDER, dyes_1_8,
						copy_stacks(optional, get_shape_ingredient()),
						copy_stacks(optional, GLOWSTONE_DUST),
						copy_stacks(optional, DIAMOND))
					.output(FIREWORK_STAR)
			));
			registration.addRecipes(RecipeTypes.CRAFTING, List.of(
				new SimpleJeiRecipe(Id.rsc("firework_star_fade"), true)
					.ingredients(FIREWORK_STAR, dyes_1_8)
					.output(FIREWORK_STAR)
			));
			var firework = FIREWORK_ROCKET.getDefaultInstance();
			firework.setTag(null);
			registration.addRecipes(RecipeTypes.CRAFTING, List.of(
				new SimpleJeiRecipe(Id.rsc("firework_from_star"), true)
					.ingredients(gunpowder_1_3, PAPER, firework_star_1_8)
					.output(firework)
			));
		}
	}
}
