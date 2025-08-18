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
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemManipulation {
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
	@SuppressWarnings({"DataFlowIssue", "unchecked"})
	public static ArrayList<Item> items(Object... objects) {
		var items = new ArrayList<Item>();
		for (var object : objects)
			if (object instanceof ItemStack stack) items.add(stack.getItem());
			else if (object instanceof Ingredient ingredient)
				items.addAll(Arrays.stream(ingredient.getItems()).map(ItemStack::getItem).toList());
			else if (object instanceof ItemLike item_like)
				items.add(item_like.asItem());
			else if (object instanceof TagKey<?> tag)
				items.addAll((ForgeRegistries.ITEMS.tags().getTag((TagKey<Item>) tag)).stream()
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
			else if (object instanceof List<?> list)
				items.addAll(items(list.toArray()));
			else Rsc.log("Could not merge " + object.getClass() + " into items!");
		return items;
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
	public static ArrayList<Item> minus_items(Object original, Object remove) {
		var original_items = items(original);
		var remove_items = items(remove);
		return original_items.stream().filter(original_item -> {
			for (var remove_item : remove_items)
				if (original_item == remove_item)
					return false;
			return true;
		}).collect(Collectors.toCollection(ArrayList::new));
	}
	public static ArrayList<ItemStack> copy_stacks(Function<ItemStack, ItemStack> function, Object... objects) {
		var stacks = stacks(objects);
		return stacks.stream().map(stack -> function.apply(stack.copy()))
			.collect(Collectors.toCollection(ArrayList::new));
	}
	public static Ingredient ingredient(Object... objects) {
		return Ingredient.of(items(objects).toArray(Item[]::new));
	}
}