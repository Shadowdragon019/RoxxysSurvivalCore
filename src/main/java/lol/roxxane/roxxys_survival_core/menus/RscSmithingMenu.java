package lol.roxxane.roxxys_survival_core.menus;

import lol.roxxane.roxxys_survival_core.blocks.RscBlocks;
import lol.roxxane.roxxys_survival_core.recipes.RscRecipeTypes;
import lol.roxxane.roxxys_survival_core.recipes.RscSmithingRecipe;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class RscSmithingMenu extends ItemCombinerMenu {
	private final Level level;
	private final List<RscSmithingRecipe> recipes;
	@Nullable private RscSmithingRecipe selectedRecipe;
	public RscSmithingMenu(int id, Inventory inventory) {
		this(id, inventory, ContainerLevelAccess.NULL);
	}
	public RscSmithingMenu(int id, Inventory inventory, ContainerLevelAccess access) {
		super(RscMenuTypes.SMITHING, id, inventory, access);
		level = inventory.player.level();
		recipes = level.getRecipeManager().getAllRecipesFor(RscRecipeTypes.SMITHING);
	}
	@Override
	protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
		return ItemCombinerMenuSlotDefinition.create()
			.withSlot(0, 8 + 36, 48, stack ->
				recipes.stream().anyMatch(recipe -> stack.is(recipe.base)))
			.withSlot(1, 26 + 36, 48, stack ->
				recipes.stream().anyMatch(recipe -> stack.is(recipe.material)))
			.withResultSlot(2, 98 + 18, 48).build();
	}
	@Override
	protected boolean isValidBlock(BlockState state) {
		return state.is(RscBlocks.SMITHING_TABLE.get());
	}
	@Override
	protected boolean mayPickup(Player player, boolean has_stack) {
		return selectedRecipe != null && selectedRecipe.matches(inputSlots, level);
	}
	@Override
	protected void onTake(Player player, ItemStack stack) {
		stack.onCraftedBy(player.level(), player, stack.getCount());
		resultSlots.awardUsedRecipes(player, List.of(inputSlots.getItem(0), inputSlots.getItem(1)));
		shrink_stack_in_slot(0);
		shrink_stack_in_slot(1);
		access.execute((_level, pos) ->
			_level.levelEvent(1044, pos, 0));
	}
	private void shrink_stack_in_slot(int index) {
		var itemstack = inputSlots.getItem(index);
		if (!itemstack.isEmpty()) {
			itemstack.shrink(1);
			inputSlots.setItem(index, itemstack);
		}
	}
	@Override
	public void createResult() {
		var list =
			level.getRecipeManager().getRecipesFor(RscRecipeTypes.SMITHING, inputSlots, level);
		if (list.isEmpty())
			resultSlots.setItem(0, ItemStack.EMPTY);
		else {
			var recipe = list.get(0);
			var itemstack = recipe.assemble(inputSlots, level.registryAccess());
			if (itemstack.isItemEnabled(level.enabledFeatures())) {
				selectedRecipe = recipe;
				resultSlots.setRecipeUsed(recipe);
				resultSlots.setItem(0, itemstack);
			}
		}
	}
	@Override
	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public int getSlotToQuickMoveTo(ItemStack stack) {
		return recipes.stream().map(recipe ->
			find_slot_matching_ingredient(recipe, stack))
			.filter(Optional::isPresent)
			.findFirst()
			.orElse(Optional.of(0)).get();
	}
	private static Optional<Integer> find_slot_matching_ingredient(RscSmithingRecipe recipe, ItemStack stack) {
		if (stack.is(recipe.base))
			return Optional.of(0);
		else if (stack.is(recipe.material))
			return Optional.of(1);
		else return Optional.empty();
	}
	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != resultSlots && super.canTakeItemForPickAll(stack, slot);
	}
	@Override
	public boolean canMoveIntoInputSlots(ItemStack stack) {
		return recipes.stream().map(recipe ->
			find_slot_matching_ingredient(recipe, stack))
			.anyMatch(Optional::isPresent);
	}
}
