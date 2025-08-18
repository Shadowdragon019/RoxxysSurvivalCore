package lol.roxxane.roxxys_survival_core.tags;

import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class RscItemTags {
	public static final TagKey<Item> PLATES = forge("plates");
	public static final TagKey<Item> IRON_PLATES = forge("plates/iron");

	private static TagKey<Item> rsc(String path) {
		return ItemTags.create(Id.rsc(path));
	}
	private static TagKey<Item> forge(String path) {
		return ItemTags.create(Id.forge(path));
	}
}
