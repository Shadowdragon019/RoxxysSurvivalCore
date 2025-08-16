package lol.roxxane.roxxys_survival_core.utils;

import lol.roxxane.roxxys_survival_core.Rsc;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Merge {
	@SuppressWarnings({"unchecked", "DataFlowIssue"})
	public static ArrayList<ItemStack> stacks_count(int count, Object... objects) {
		var stacks = new ArrayList<ItemStack>();
		for (var object : objects)
			if (object instanceof ItemStack stack) stacks.add(stack.copyWithCount(count));
			else if (object instanceof Ingredient ingredient)
				stacks.addAll(Arrays.stream(ingredient.getItems())
					.map(stack -> stack.copyWithCount(count)).toList());
			else if (object instanceof ItemLike item_like)
				stacks.add(new ItemStack(item_like, count));
			else if (object instanceof TagKey<?> tag)
				stacks.addAll((ForgeRegistries.ITEMS.tags().getTag((TagKey<Item>) tag)).stream()
					.collect(ArrayList::new,
						(result_items, item) -> result_items.add(new ItemStack(item, count)),
						ArrayList::addAll));
			else if (object instanceof List<?> list)
				stacks.addAll(stacks_count(count, list.toArray()));
			else Rsc.log("Could not merge " + object.getClass() + " into stacks!");
		return stacks;
	}
	@SuppressWarnings({"DataFlowIssue", "unchecked"})
	public static ArrayList<ItemStack> stacks(Object... objects) {
		var stacks = new ArrayList<ItemStack>();
		for (var object : objects)
			if (object instanceof ItemStack stack) stacks.add(stack);
			else if (object instanceof Ingredient ingredient)
				stacks.addAll(Arrays.stream(ingredient.getItems()).toList());
			else if (object instanceof ItemLike item_like)
				stacks.add(item_like.asItem().getDefaultInstance());
			else if (object instanceof TagKey<?> tag)
				stacks.addAll((ForgeRegistries.ITEMS.tags().getTag((TagKey<Item>) tag)).stream()
					.collect(ArrayList::new,
						(result_items, item) -> result_items.add(item.getDefaultInstance()),
						ArrayList::addAll));
			else if (object instanceof List<?> list)
				stacks.addAll(stacks(list.toArray()));
			else Rsc.log("Could not merge " + object.getClass() + " into stacks!");
		return stacks;
	}
	public static NonNullList<Ingredient> ingredients_count(int count, Object... objects) {
		var ingredients = NonNullList.<Ingredient>create();
		for (var object : objects)
			ingredients.add(Ingredient.of(stacks_count(count, object).toArray(ItemStack[]::new)));
		return ingredients;
	}
	public static NonNullList<Ingredient> ingredients(Object... objects) {
		var ingredients = NonNullList.<Ingredient>create();
		for (var object : objects)
			ingredients.add(Ingredient.of(stacks(object).toArray(ItemStack[]::new)));
		return ingredients;
	}
	public static ArrayList<ItemStack> minus_stacks(Object original, Object remove) {
		var original_stacks = stacks(original);
		var remove_stacks = stacks(remove);
		return original_stacks.stream().filter(original_stack -> {
			for (var remove_stack : remove_stacks)
				if (ItemStack.matches(original_stack, remove_stack))
					return false;
			return true;
		}).collect(Collectors.toCollection(ArrayList::new));
	}
}