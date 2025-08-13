package lol.roxxane.roxxys_survival_core;

import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class RscItemTags {
	public static final TagKey<Item> HIDE_FROM_JEI = rsc("hide_from_jei");

	private static TagKey<Item> rsc(String path) {
		return ItemTags.create(Id.rsc(path));
	}
	private static TagKey<Item> forge(String path) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", path));
	}
	private static TagKey<Item> mc(String path) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", path));
	}
}
