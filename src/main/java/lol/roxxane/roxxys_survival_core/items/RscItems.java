package lol.roxxane.roxxys_survival_core.items;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

import java.util.function.Consumer;
import java.util.function.Function;

import static lol.roxxane.roxxys_survival_core.Rsc.REGISTRATE;

public class RscItems {
	public static final RegistryEntry<Item> IRON_PLATE = item("iron_plate",
		builder -> builder.tab(CreativeModeTabs.INGREDIENTS));
	public static final RegistryEntry<Item> SHOVEL_BASE = item("shovel_base",
		builder -> builder.tab(CreativeModeTabs.INGREDIENTS));
	public static final RegistryEntry<Item> PICKAXE_BASE = item("pickaxe_base",
		builder -> builder.tab(CreativeModeTabs.INGREDIENTS));
	public static final RegistryEntry<Item> AXE_BASE = item("axe_base",
		builder -> builder.tab(CreativeModeTabs.INGREDIENTS));
	public static final RegistryEntry<Item> HOE_BASE = item("hoe_base",
		builder -> builder.tab(CreativeModeTabs.INGREDIENTS));
	public static final RegistryEntry<Item> SWORD_BASE = item("sword_base",
		builder -> builder.tab(CreativeModeTabs.INGREDIENTS));
	private static <I extends Item> RegistryEntry<I> item(String path,
		Function<Properties, I> function, Consumer<ItemBuilder<I, Registrate>> consumer
	) {
		var builder = REGISTRATE.item(path, function::apply)
			.defaultLang().defaultModel();
		consumer.accept(builder);
		return builder.register();
	}
	private static RegistryEntry<Item> item(String path,
		Consumer<ItemBuilder<Item, Registrate>> consumer
	) {
		var builder = REGISTRATE.item(path, Item::new)
			.defaultLang().defaultModel();
		consumer.accept(builder);
		return builder.register();
	}
	private static RegistryEntry<Item> item(String path) {
		return REGISTRATE.item(path, Item::new)
			.defaultLang().defaultModel().register();
	}
	public static void register(){}
}
